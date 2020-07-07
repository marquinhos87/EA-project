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

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.*;

@Local(CoreFacadeBeanLocal.class)
@Stateless(name = "CoreFacadeBean")
public class CoreFacadeBean implements CoreFacadeBeanLocal{

    private static final String REDIS_HOST = "localhost";
    private static final int REDIS_PORT = 6379;
    private final Gson gson;
    private final Jedis jedis;
    private PlanDirector planDirector = new PlanDirector(new ConcretePlanBuilder());

    public CoreFacadeBean() {
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        jedis = new Jedis(REDIS_HOST,REDIS_PORT);
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
        JsonObject json = Utils.validateJson(gson, usernameAndWeekAsJSON, Arrays.asList("token", "username", "week"));

        String username = json.get("username").getAsString();
        String token = json.get("token").getAsString();
        Utils.validateToken(token, username, jedis);

        PersistentSession session = CoreFacade.getSession();
        Client client;
        if((client = ClientDAO.getClientByORMID(session,username)) == null)
            throw new ClientDontExistsException(username);
        Plan plan = client.getPlan();
        Iterator it = plan.weeks.getIterator();
        Week week = null;
        for( int i = 0 ; i < json.get("week").getAsInt() ; week = (Week) it.next());

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
        // get specific week
        if(json.has("week")) {
            Plan plan = client.getPlan();
            Iterator it = plan.weeks.getIterator();

            for( int i = 0 ; i < json.get("week").getAsInt() ; week = (Week) it.next());
        } // get actual week
        else {
            week = client.getPlan().getCurrentWeek();
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
    public void createWeek(String weekAsJson) throws JsonKeyInFaultException, PersistentException, InvalidTokenException, UserDontExistsException {
        JsonObject json = Utils.validateJson(gson, weekAsJson, Arrays.asList("token", "username", "week"));

        String username = json.get("username").getAsString();
        String token = json.get("token").getAsString();
        Utils.validateToken(token, username, jedis);

        PersistentSession session = CoreFacade.getSession();
        // Verify if PersonalTrainer exists and if not create a new one
        PersonalTrainer pt;
        if((pt = PersonalTrainerDAO.getPersonalTrainerByORMID(session,username)) == null) {
            pt = new PersonalTrainer();
            pt.setUsername(username);
            PersonalTrainerDAO.save(pt);
            session.flush();
        }

        // TODO
        // Verify if createWeek has called to create a new plan or to add a new week to an existing plan
        if(json.has("planId")) {
            // planDirector.build(json.get("planId").getAsInt(),json.get("week").getAsString());
        } else {
            // Create Plan and associate to PersonalTrainer and Client
            // if(!json.has("clientUsername"))
            //     throws nem JsonKeyInFaultException("clientUsername");
            // Plan plan = planDirector.buildPlan(null,json.get("week").getAsJsonObject());
            // String clientUsername = json.get("clientUsername").getAsString();
            // Client client;
            // if((client = ClientDAO.getClientByORMID(session,clientUsername)) != null)
            //     throws new ClientAlreadyHasAnPlanException(clientUsername);
            // client = new Client();
            // client.setUsername(clientUsername);
            // client.setPlan(plan);
            // ClientDAO.save(client);
            // session.flush();
        }
    }
}
