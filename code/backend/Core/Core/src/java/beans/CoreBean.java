/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import core.*;
import exceptions.*;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import redis.clients.jedis.Jedis;
import utils.Utils;

import javax.ejb.Stateless;
import java.util.*;

/**
 *
 * @author joaomarques
 */
@Stateless
public class CoreBean implements CoreBeanLocal {

   
    private static final String REDIS_HOST = "localhost";
    private static final int REDIS_PORT = 6379;
    private final Gson gson;
    private final Jedis jedis;
    private PlanDirector planDirector = new PlanDirector(new ConcretePlanBuilder());

    public CoreBean() {
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        jedis = new Jedis(REDIS_HOST,REDIS_PORT);
    }
    
    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }

    @Override
    public void createUserToken(String usernameAsJson) throws UserAlreadyExistsException, JsonKeyInFaultException {
        JsonObject json = Utils.validateJson(gson, usernameAsJson, Arrays.asList("token", "username"));

        if(jedis.exists(json.get("username").getAsString()))
            throw new UserAlreadyExistsException(json.get("username").getAsString());

        jedis.set(json.get("username").getAsString(),json.get("token").getAsString());
    }

    @Override
    public void updateToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, InvalidTokenException, UserDontExistsException {
        JsonObject json = Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("oldToken", "newToken", "username"));

        String username = json.get("username").getAsString();
        String token = json.get("oldToken").getAsString();
        Utils.validateToken(token, username, jedis);

        jedis.set(username,json.get("newToken").getAsString());
    }

    @Override
    public String getWeekByClient(String usernameAndWeekAsJSON) throws JsonKeyInFaultException, PersistentException, InvalidTokenException, UserDontExistsException, ClientDontExistsException {
        JsonObject json = Utils.validateJson(gson, usernameAndWeekAsJSON, Arrays.asList("token", "username"));

        String username = json.get("username").getAsString();
        String token = json.get("token").getAsString();
        Utils.validateToken(token, username, jedis);

        PersistentSession session = CoreFacade.getSession();
        Client client;
        if((client = ClientDAO.getClientByORMID(session,username)) == null)
            throw new ClientDontExistsException(username);
        Plan plan = client.getPlan();
        Week week = null;
        if(json.has("week")) {
            Iterator it = plan.weeks.getIterator();
            for( int i = 0 ; i < json.get("week").getAsInt() ; week = (Week) it.next());
        } else {
            week = plan.getCurrentWeek();
        }
        
        // TODO week to json

        return null;
    }

    @Override
    public String getWeekByPersonalTrainer(String usernameAndWeekAsJSON) throws JsonKeyInFaultException, InvalidTokenException, UserDontExistsException, PersistentException, ClientDontExistsException {
        JsonObject json = Utils.validateJson(gson, usernameAndWeekAsJSON, Arrays.asList("token", "username", "clientUsername"));

        String username = json.get("username").getAsString();
        String token = json.get("token").getAsString();
        Utils.validateToken(token, username, jedis);

        PersistentSession session = CoreFacade.getSession();
        Client client;
        if((client = ClientDAO.getClientByORMID(session,json.get("clientUsername").getAsString())) == null)
            throw new ClientDontExistsException(json.get("clientUsername").getAsString());

        Week week = null;
        Plan plan = client.getPlan();
        // get specific week
        if(json.has("week")) {
            Iterator it = plan.weeks.getIterator();
            for( int i = 0 ; i < json.get("week").getAsInt() ; week = (Week) it.next());
        } // get actual week
        else {
            week = plan.getCurrentWeek();
        }
        
        // TODO week to json

        return null;
    }

    @Override
    public void finishWorkout(String usernameAndWorkoutIdAsJSON) throws InvalidTokenException, JsonKeyInFaultException, UserDontExistsException, ClientDontExistsException, PersistentException, WorkoutDontExistException, WorkoutDontBelongToUserException, WorkoutAlreadyDoneException {
        JsonObject json = Utils.validateJson(gson, usernameAndWorkoutIdAsJSON, Arrays.asList("token", "username", "workoutId"));

        String username = json.get("username").getAsString();
        String token = json.get("token").getAsString();
        Utils.validateToken(token, username, jedis);

        PersistentSession session = CoreFacade.getSession();
        Client client;
        if((client = ClientDAO.getClientByORMID(session,username)) == null)
            throw new ClientDontExistsException(username);
        Plan plan = client.getPlan();

        // Check if the workout belong to the current plan of the client
        int workoutId = json.get("workoutId").getAsInt();
        Workout workout;
        if((workout = WorkoutDAO.getWorkoutByORMID(session,workoutId)) == null)
            throw new WorkoutDontExistException(Integer.toString(workoutId));

        Week week;
        for(Iterator it = plan.weeks.getIterator() ; it.hasNext() ; ) {
            week = (Week) it.next();
            if(week.workouts.contains(workout)) {
                if(workout.isDone())
                    throw new WorkoutAlreadyDoneException(Integer.toString(workoutId));
                workout.setDone(true);
                WorkoutDAO.save(workout);
                session.flush();
                return;
            }
        }

        throw new WorkoutDontBelongToUserException(json.get("workoutId").getAsString() + '\t' + username);
    }

    @Override
    public void createWeek(String weekAsJson) throws JsonKeyInFaultException, PersistentException, InvalidTokenException, UserDontExistsException, ClientAlreadyHasAnPlanException, PlanDontExistException {
        JsonObject json = Utils.validateJson(gson, weekAsJson, Arrays.asList("token", "username", "week"));

        String username = json.get("username").getAsString();
        String token = json.get("token").getAsString();
        Utils.validateToken(token, username, jedis);

        PersistentSession session = CoreFacade.getSession();
        // Verify if PersonalTrainer exists and if not create him
        PersonalTrainer pt;
        if((pt = PersonalTrainerDAO.getPersonalTrainerByORMID(session,username)) == null) {
            pt = new PersonalTrainer();
            pt.setUsername(username);
            PersonalTrainerDAO.save(pt);
            session.flush();
        }

        // Verify if createWeek has called to create a new plan or to add a new week to an existing plan
        if(json.has("planId")) {
            planDirector.buildPlan(json.get("planId").getAsInt(),json.get("week").getAsString());
        } else {
            // Create Plan and associate to PersonalTrainer and Client
            if(!json.has("clientUsername"))
                throw new JsonKeyInFaultException("clientUsername");
            String clientUsername = json.get("clientUsername").getAsString();
            if(ClientDAO.getClientByORMID(session,clientUsername) != null)
                throw new ClientAlreadyHasAnPlanException(clientUsername);
            Plan plan = planDirector.buildPlan(null,json.get("week").getAsString());
            Client client = new Client();
            client.setUsername(clientUsername);
            client.setPlan(plan);
            ClientDAO.save(client);
            session.flush();
        }
    }
}
