/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.next;
import java.time.LocalDate;
import java.time.ZoneId;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import javax.ejb.Stateless;

import utils.Utils;
import core.*;
import exceptions.*;
import java.time.DayOfWeek;
import java.time.Instant;
import java.util.*;
import org.hibernate.Query;
import parseJSON.deserializer.*;
import parseJSON.serializer.*;

/**
 *
 * @author joaomarques
 */
@Stateless
public class CoreBean implements CoreBeanLocal {

    private final Gson gson;
    private final PlanDirector planDirector = new PlanDirector(new ConcretePlanBuilder());

    /**
     * CoreBean empty constructor.
     */
    public CoreBean() {
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .registerTypeAdapter(Week.class, new WeekSerializer())
                .registerTypeAdapter(Week.class, new WeekDeserializer())
                .registerTypeAdapter(Workout.class, new WorkoutSerializer())
                .registerTypeAdapter(Workout.class, new WorkoutDeserializer())
                .registerTypeAdapter(Task.class, new TaskSerializer())
                .registerTypeAdapter(Task.class, new TaskDeserializer())
                .create();
    }
    
    /**
     * Create a User.
     *
     * @param usernameAsJson User username and token as a json string.
     * @throws PersistentException if some error occur with hibernate
     * @throws UserAlreadyExistsException if users token already register
     * @throws JsonKeyInFaultException if has some keys in fault 
     */
    private String createUserToken(String usernameAsJson) throws UserAlreadyExistsException, JsonKeyInFaultException, PersistentException {
        JsonObject json = Utils.validateJson(gson, usernameAsJson, Arrays.asList("token", "username"));

        // Verify if User is already registered
        PersistentSession session = CoreFacade.getSession();
        if(UserDAO.getUserByORMID(session,json.get("username").getAsString()) != null)
            throw new UserAlreadyExistsException(json.get("username").getAsString());
        
        // Add new user to database
        User user = new User();
        user.setUsername(json.get("username").getAsString());
        user.setToken(json.get("token").getAsString());
        UserDAO.save(user);
        session.flush();
        
        return json.get("username").getAsString();
    }

    /**
     * Create a User (Client).
     *
     * @param usernameAsJson User username and token as a json string.
     * @throws PersistentException if some error occur with hibernate
     * @throws UserAlreadyExistsException if users token already register
     * @throws JsonKeyInFaultException if has some keys in fault 
     */
    @Override
    public void createUserTokenClient(String usernameAsJson) throws UserAlreadyExistsException, JsonKeyInFaultException, PersistentException {
        createUserToken(usernameAsJson);
    }
    
    /**
     * Create a User (PersonalTrainer).
     *
     * @param usernameAsJson User username and token as a json string.
     * @throws PersistentException if some error occur with hibernate
     * @throws UserAlreadyExistsException if users token already register
     * @throws JsonKeyInFaultException if has some keys in fault 
     */
    @Override
    public void createUserTokenPersonalTrainer(String usernameAsJson) throws UserAlreadyExistsException, JsonKeyInFaultException, PersistentException {        
        PersistentSession session = CoreFacade.getSession();
        
        // Add PersonalTrainer to database
        PersonalTrainer pt = new PersonalTrainer();
        pt.setUsername(createUserToken(usernameAsJson));
        PersonalTrainerDAO.save(pt);
        session.flush();
    }

    /**
     * Update a User token.
     *
     * @param usernameAndTokenAsJson User username and token as a json string.
     * @throws PersistentException if some error occur with hibernate
     * @throws JsonKeyInFaultException if has some keys in fault 
     * @throws InvalidTokenException if for given user the token is invalid
     * @throws UserDontExistsException if username given dont exists
     */
    @Override
    public void updateToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, InvalidTokenException, UserDontExistsException, PersistentException {
        JsonObject json = Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("oldToken", "newToken", "username"));

        String username = json.get("username").getAsString();
        String token = json.get("oldToken").getAsString();

        // Validate given token
        PersistentSession session = CoreFacade.getSession();
        User user = Utils.validateToken(token,username,session);
        
        // Update token
        user.setToken(json.get("newToken").getAsString());
        UserDAO.save(user);
        session.flush();
    }

    /**
     * Get a week of a Client plan.
     *
     * @param usernameAndWeekAsJSON Client's username and week as a json string.
     * @return The week asked by Client
     * @throws JsonKeyInFaultException if has some keys in fault 
     * @throws PersistentException if some error occur with hibernate
     * @throws ClientDontExistsException if Client dont exist
     * @throws UserDontExistsException if username given dont exists
     * @throws InvalidTokenException if for given user the token is invalid
     * @throws InvalidWeekNumberException if given number of the week is incorrect
     */
    @Override
    public String getWeekByClient(String usernameAndWeekAsJSON) throws JsonKeyInFaultException, PersistentException, InvalidTokenException, UserDontExistsException, ClientDontExistsException, InvalidWeekNumberException {
        JsonObject json = Utils.validateJson(gson, usernameAndWeekAsJSON, Arrays.asList("token", "username"));

        String username = json.get("username").getAsString();
        String token = json.get("token").getAsString();

        // Validate given token
        PersistentSession session = CoreFacade.getSession();
        Utils.validateToken(token,username,session);
        
        // Verify if Client exists
        Client client;
        if((client = ClientDAO.getClientByORMID(session,username)) == null)
            throw new ClientDontExistsException(username);
        
        // Get week to return to Client
        Plan plan = client.getPlan();
        
        int weekNumber = json.has("week") ? json.get("week").getAsInt() : plan.getCurrentWeek();
        
        if(weekNumber > plan.weeks.size())
            throw new InvalidWeekNumberException(Integer.toString(weekNumber));
        
        Week week = null;
        Iterator weeks = plan.weeks.getIterator();
        while(weeks.hasNext()) {
            week = (Week) weeks.next();
            if (week.getNumber() == weekNumber)
                break;
        }

        return gson.toJson(week,Week.class);
    }

    /**
     * Get a week of a Client plan.
     *
     * @param usernameAndWeekAsJSON PersonalTrainer's username and week as a json string.
     * @return The week asked by PersonalTrainer
     * @throws JsonKeyInFaultException if has some keys in fault 
     * @throws PersistentException if some error occur with hibernate
     * @throws UserDontExistsException if username given dont exists
     * @throws InvalidTokenException if for given user the token is invalid
     * @throws ClientDontExistsException if Client dont exist
     * @throws InvalidWeekNumberException if given number of the week is incorrect
     */
    @Override
    public String getWeekByPersonalTrainer(String usernameAndWeekAsJSON) throws JsonKeyInFaultException, InvalidTokenException, UserDontExistsException, PersistentException, ClientDontExistsException, InvalidWeekNumberException {
        JsonObject json = Utils.validateJson(gson, usernameAndWeekAsJSON, Arrays.asList("token", "username", "clientUsername"));

        String username = json.get("username").getAsString();
        String token = json.get("token").getAsString();

        // Validate given token
        PersistentSession session = CoreFacade.getSession();
        Utils.validateToken(token,username,session);
        
        // Verify if Client exists
        Client client;
        if((client = ClientDAO.getClientByORMID(session,json.get("clientUsername").getAsString())) == null)
            throw new ClientDontExistsException(json.get("clientUsername").getAsString());

        // Get week to return to PersonalTrainer
        Plan plan = client.getPlan();
        
        int weekNumber = json.has("week") ? json.get("week").getAsInt() : plan.getCurrentWeek();
        
        if(weekNumber > plan.weeks.size())
            throw new InvalidWeekNumberException(Integer.toString(weekNumber));
        
        Week week = null;
        Iterator weeks = plan.weeks.getIterator();
        while(weeks.hasNext()) {
            week = (Week) weeks.next();
            if (week.getNumber() == weekNumber)
                break;
        }

        return gson.toJson(week,Week.class);
    }

    /**
     * Assign workouts as done.
     *
     * @param usernameAndWorkoutIdAsJSON Client's username and workout Id as a json string.
     * @throws PersistentException if some error occur with hibernate
     * @throws ClientDontExistsException if Client dont exist
     * @throws InvalidTokenException if for given user the token is invalid
     * @throws JsonKeyInFaultException if has some keys in fault 
     * @throws UserDontExistsException if username given dont exists
     * @throws WorkoutAlreadyDoneException if workout already done
     * @throws WorkoutDontBelongToUserException if workout dont belong to the given user
     * @throws WorkoutDontExistException if given workout dont exist
     */
    @Override
    public void finishWorkout(String usernameAndWorkoutIdAsJSON) throws InvalidTokenException, JsonKeyInFaultException, UserDontExistsException, ClientDontExistsException, PersistentException, WorkoutDontExistException, WorkoutDontBelongToUserException, WorkoutAlreadyDoneException {
        JsonObject json = Utils.validateJson(gson, usernameAndWorkoutIdAsJSON, Arrays.asList("token", "username", "workoutId"));

        String username = json.get("username").getAsString();
        String token = json.get("token").getAsString();

        // Validate given token
        PersistentSession session = CoreFacade.getSession();
        Utils.validateToken(token,username,session);
        
        // Verify if Client exists
        Client client;
        if((client = ClientDAO.getClientByORMID(session,username)) == null)
            throw new ClientDontExistsException(username);
        
        Plan plan = client.getPlan();

        // Check if workout exists
        int workoutId = json.get("workoutId").getAsInt();
        Workout workout;
        if((workout = WorkoutDAO.getWorkoutByORMID(session,workoutId)) == null)
            throw new WorkoutDontExistException(Integer.toString(workoutId));

        boolean workOutBelongToUser = false;
        
        // Verify if workout belong to Client and if is already done or not
        Iterator weeks = plan.weeks.getIterator();
        while(weeks.hasNext()) {
            
            Week week = (Week) weeks.next();
            if(week.workouts.contains(workout)) {
                
                workOutBelongToUser = true;
                
                // Verify if the workout is already done
                if(workout.isDone())
                    throw new WorkoutAlreadyDoneException(Integer.toString(workoutId));
                workout.setDone(true);
                WorkoutDAO.save(workout);
                session.flush();
                
                boolean isLastWeek = true;
                
                if(week.getNumber() == plan.getCurrentWeek()) {
                    Iterator workouts = week.workouts.getIterator();
                    while(workouts.hasNext()) {
                        Workout lastWorkout = (Workout) workouts.next();
                         // Verify if there is any workout not done
                        if (lastWorkout.isDone() == false) {
                            isLastWeek = false;
                            break;
                        }
                    }
                }
                           
                if (isLastWeek && week.getNumber() != plan.weeks.size())
                    plan.setCurrentWeek(week.getNumber()+1);    
                
                PlanDAO.save(plan);
                session.flush();
                break;
            }
        }
        
        if (workOutBelongToUser == false) {
            throw new WorkoutDontBelongToUserException(json.get("workoutId").getAsString() + '\t' + username);
        }
    }

    /**
     * Create a new week for an existing plan or create a new week for a new plan.
     *
     * @param weekAsJson Week info as a json string.
     * @throws JsonKeyInFaultException if has some keys in fault 
     * @throws PersistentException if some error occur with hibernate
     * @throws InvalidTokenException if for given user the token is invalid
     * @throws UserDontExistsException if username given dont exists
     * @throws ClientAlreadyHasAnPlanException if Client already has a Plan
     * @throws PlanDontExistException if given Plan dont exist
     * @throws InvalidWeekNumberException if given number of the week is incorrect
     * @throws UsernameDontBelongToClientException if given username belong to a PersonalTrainer
     */
    @Override
    @SuppressWarnings("empty-statement")
    public void createWeek(String weekAsJson) throws JsonKeyInFaultException, PersistentException, InvalidTokenException, UserDontExistsException, ClientAlreadyHasAnPlanException, PlanDontExistException, InvalidWeekNumberException, UsernameDontBelongToClientException {
        JsonObject json = Utils.validateJson(gson, weekAsJson, Arrays.asList("token", "username", "week"));

        String username = json.get("username").getAsString();
        String token = json.get("token").getAsString();

        // Validate given token
        PersistentSession session = CoreFacade.getSession();
        Utils.validateToken(token,username,session);
        
        // no need to check if exists because we create it before
        PersonalTrainer pt = PersonalTrainerDAO.getPersonalTrainerByORMID(session,username);
        
        Plan plan;
        Week week = gson.fromJson(json.get("week").toString(), Week.class); // evoques custom serializer from package parseJSON
        
        // Verify if createWeek has been called to create a new plan (planId doesn't exist yet)
        // or to add a new week to an existing plan
        if(!json.has("planId")) {
            
            // Verify if Client's username is given
            if(!json.has("clientUsername"))
                throw new JsonKeyInFaultException("clientUsername");
            String clientUsername = json.get("clientUsername").getAsString();
            
            // Verify if Client's username belong to a PersonalTrainer
            Query query = session.createQuery("from PersonalTrainer where username='" + clientUsername + "'");
            if(query.list().isEmpty() == false) // it's a PersonalTrainer username
                throw new UsernameDontBelongToClientException(clientUsername);
            
            // Verify if Client exists
            query = session.createQuery("from Client where username='" + clientUsername + "'");
            if(!query.list().isEmpty())
                throw new ClientAlreadyHasAnPlanException(clientUsername);
            
             // This code purpose is to detect the next SUNDAY (init of the week)
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Date firstDay = Date.from(LocalDate.now().with(next(DayOfWeek.MONDAY)).atStartOfDay(defaultZoneId).toInstant());
                    
            week.setNumber(1); // first plan week
            week.setInitialDate(firstDay);
            week.setFinalDate(Date.from(LocalDate.now().with(next(DayOfWeek.MONDAY)).with(next(SUNDAY)).atStartOfDay(defaultZoneId).toInstant()));
            
            Iterator workouts = week.workouts.getIterator();
            while(workouts.hasNext()) {
                Workout workout = (Workout) workouts.next();
                workout.setDate( new Date(week.getInitialDate().getTime()+(60*60*24*1000*(workout.getWeekDay()-1))) );
            }
            
            // Create a new plan
            plan = new Plan();
            plan.weeks.add(week);
            plan.setCurrentWeek(1);
            plan.setDone(false);
            plan.setModified(false);
            plan.setInitialDate(firstDay);
            
            // Create Client to associate with the Plan
            Client client = new Client();
            client.setUsername(clientUsername);
            client.setPlan(plan);
            
            pt.plans.add(plan);

            // Only save PersonalTrainer and Client because plan belongs to
            // PersonalTrainer so when we save PersonalTrainer the Plan is saved too
            PersonalTrainerDAO.save(pt);
            session.flush();
            
            ClientDAO.save(client);
        }
        //Here we don't need to associate Client and PersonalTrainer to Plan because
        // it adds a new week to an existing plan already associated with the previous ones
        else {
            
            // Verify if the plan exists in database
            if ((plan = PlanDAO.getPlanByORMID(CoreFacade.getSession(), json.get("planId").getAsInt())) == null)
                throw new PlanDontExistException(json.get("planId").getAsString());
            
            week.setNumber(plan.weeks.size()+1);
            
            boolean isLastWeek = true;
            
            // Get the lastWeek to check if the new week becomes the currentWeek for Client
            Iterator weeks = plan.weeks.getIterator();
            Week lastWeek = null;
            while(weeks.hasNext()) {
                Week tmp = (Week) weeks.next();
                // Verify if it's the current week
                if(tmp.getNumber() == plan.getCurrentWeek()) {
                    Iterator workouts = tmp.workouts.getIterator();
                    while(workouts.hasNext()) {
                        Workout workout = (Workout) workouts.next();
                         // Verify if there is any workout not done
                        if (workout.isDone() == false) {
                            isLastWeek = false;
                            break;
                        }
                    }
                    // break; // caso seja a última semana (mais atual), no entanto NEM todos os workout estão feitos
                } 
                if (tmp.getNumber() == plan.weeks.size())
                    lastWeek = tmp;
            }    

            week.setInitialDate( new Date(lastWeek.getFinalDate().getTime()+(60*60*24*1000)) );
            week.setFinalDate( new Date(lastWeek.getFinalDate().getTime()+(60*60*24*1000*7)) );
            
            Iterator workouts = week.workouts.getIterator();
            while(workouts.hasNext()) {
                Workout workout = (Workout) workouts.next();
                workout.setDate( new Date(week.getInitialDate().getTime()+(60*60*24*1000*(workout.getWeekDay()-1))) );
            }
            
            
            if (isLastWeek) plan.setCurrentWeek(week.getNumber());           
            
            // Save the new state of plan
            plan.weeks.add(week);
            PlanDAO.save(plan);
        }
        session.flush();
    }
    
    /**
     * Remove a User (Client).
     * 
     * @param usernameAsJson User username and token as a json string.
     * @throws PersistentException if some error occur with hibernate
     * @throws JsonKeyInFaultException if has some keys in fault 
     * @throws UserDontExistsException if username given dont exists
     * @throws InvalidTokenException if for given user the token is invalid
     */
    @Override
    public void removeUserTokenClient(String usernameAsJson) throws PersistentException, JsonKeyInFaultException, UserDontExistsException, InvalidTokenException {
        PersistentSession session = CoreFacade.getSession();
        removeUserToken(usernameAsJson, session);
    }
    
    /**
     * Remove a User (PersonalTrainer).
     * 
     * @param usernameAsJson User username and token as a json string.
     * @throws PersistentException if some error occur with hibernate
     * @throws JsonKeyInFaultException if has some keys in fault 
     * @throws UserDontExistsException if username given dont exists
     * @throws InvalidTokenException if for given user the token is invalid
     */
    @Override
    public void removeUserTokenPersonalTrainer(String usernameAsJson) throws PersistentException, JsonKeyInFaultException, UserDontExistsException, InvalidTokenException {
        PersistentSession session = CoreFacade.getSession();
        String username = removeUserToken(usernameAsJson, session);
        PersonalTrainerDAO.delete(PersonalTrainerDAO.getPersonalTrainerByORMID(session,username));
        session.flush();
    }
    
    /**
     * Remove a User.
     * 
     * @param usernameAsJson User username and token as a json string.
     * @param session Persistent Session
     * @return The user's username
     * @throws PersistentException if some error occur with hibernate
     * @throws JsonKeyInFaultException if has some keys in fault 
     * @throws UserDontExistsException if username given dont exists
     * @throws InvalidTokenException if for given user the token is invalid
     */
    private String removeUserToken(String usernameAsJson, PersistentSession session) throws PersistentException, JsonKeyInFaultException, UserDontExistsException, InvalidTokenException {
        JsonObject json = Utils.validateJson(gson, usernameAsJson, Arrays.asList("token", "username"));

        String username = json.get("username").getAsString();
        String token = json.get("token").getAsString();

        // Validate given token
        User user = Utils.validateToken(token,username,session);
        
        UserDAO.delete(user);
        session.flush();
        
        return username;
    }
}
