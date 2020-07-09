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
import static java.time.DayOfWeek.SUNDAY;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalAdjusters.next;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import utils.Utils;

import javax.ejb.Stateless;
import java.util.*;
import parseJSON.deserializer.TaskDeserializer;
import parseJSON.deserializer.WeekDeserializer;
import parseJSON.deserializer.WorkoutDeserializer;
import parseJSON.serializer.WeekSerializer;

/**
 *
 * @author joaomarques
 */
@Stateless
public class CoreBean implements CoreBeanLocal {

    private final Gson gson;
    private PlanDirector planDirector = new PlanDirector(new ConcretePlanBuilder());

    public CoreBean() {
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .registerTypeAdapter(Week.class, new WeekDeserializer())
                .registerTypeAdapter(Workout.class, new WorkoutDeserializer())
                .registerTypeAdapter(Task.class, new TaskDeserializer())
                .create();
    }

    @Override
    public void createUserToken(String usernameAsJson) throws UserAlreadyExistsException, JsonKeyInFaultException, PersistentException {
        JsonObject json = Utils.validateJson(gson, usernameAsJson, Arrays.asList("token", "username"));

        PersistentSession session = CoreFacade.getSession();
        if(UserDAO.getUserByORMID(session,json.get("username").getAsString()) != null)
            throw new UserAlreadyExistsException(json.get("username").getAsString());
        
        User user = new User();
        user.setUsername(json.get("username").getAsString());
        user.setToken(json.get("token").getAsString());
        UserDAO.save(user);
        
        session.flush();
    }

    @Override
    public void updateToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, InvalidTokenException, UserDontExistsException, PersistentException {
        JsonObject json = Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("oldToken", "newToken", "username"));

        String username = json.get("username").getAsString();
        String token = json.get("oldToken").getAsString();

        PersistentSession session = CoreFacade.getSession();
        User user = Utils.validateToken(token,username,session);
        user.setToken(json.get("newToken").getAsString());
        UserDAO.save(user);
        session.flush();
    }

    @Override
    public String getWeekByClient(String usernameAndWeekAsJSON) throws JsonKeyInFaultException, PersistentException, InvalidTokenException, UserDontExistsException, ClientDontExistsException {
        JsonObject json = Utils.validateJson(gson, usernameAndWeekAsJSON, Arrays.asList("token", "username"));

        String username = json.get("username").getAsString();
        String token = json.get("token").getAsString();

        PersistentSession session = CoreFacade.getSession();
        Utils.validateToken(token,username,session);
        
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

        PersistentSession session = CoreFacade.getSession();
        Utils.validateToken(token,username,session);
        
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

        PersistentSession session = CoreFacade.getSession();
        Utils.validateToken(token,username,session);
        
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

        PersistentSession session = CoreFacade.getSession();
        Utils.validateToken(token,username,session);
        
        // Verify if PersonalTrainer exists and if not create him
        PersonalTrainer pt;
        if((pt = PersonalTrainerDAO.getPersonalTrainerByORMID(session,username)) == null) {
            pt = new PersonalTrainer();
            pt.setUsername(username);
        }
        
        Plan plan;
        Week week = gson.fromJson(json.get("week").toString(), Week.class);
        // Verify if createWeek has called to create a new plan or to add a new week to an existing plan
        if(!json.has("planId")) {
            
            if(!json.has("clientUsername"))
                throw new JsonKeyInFaultException("clientUsername");
            String clientUsername = json.get("clientUsername").getAsString();
            if(ClientDAO.getClientByORMID(session,clientUsername) != null)
                throw new ClientAlreadyHasAnPlanException(clientUsername);
            
            plan = new Plan();
            plan.weeks.add(week);
            plan.setCurrentWeek(week);
            plan.setDone(false);
            plan.setModified(false);
            
            // This code purpose is to detect the next SUNDAY
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate localDate = LocalDate.now();
            if(localDate.getDayOfWeek() != SUNDAY)
                localDate = localDate.with(next(SUNDAY));
            plan.setInitialDate(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
            
            Client client = new Client();
            client.setUsername(clientUsername);
            client.setPlan(plan);
            
            pt.plans.add(plan);

            PersonalTrainerDAO.save(pt);
            PlanDAO.save(plan);
            ClientDAO.save(client);
        }
        else {
            if ((plan = PlanDAO.getPlanByORMID(CoreFacade.getSession(), json.get("planId").getAsInt())) == null)
                throw new PlanDontExistException(json.get("planId").getAsString());
            
            plan.weeks.add(week); 
            //TODO
            Week currentWeek = plan.getCurrentWeek();
           
            PlanDAO.save(plan);
        }
        session.flush();
    }
}
