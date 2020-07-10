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

        // Verify if workout belong to Client and if is already done or not
        Iterator weeks = plan.weeks.getIterator();
        Week week;
        while(weeks.hasNext()) {
            week = (Week) weeks.next();
            if(week.workouts.contains(workout)) {
                // Verify if the workout is already done
                if(workout.isDone())
                    throw new WorkoutAlreadyDoneException(Integer.toString(workoutId));
                workout.setDone(true);
                WorkoutDAO.save(workout);
                session.flush();
                
                if(plan.getCurrentWeek() == week.getNumber()) {
                    // Check if its the last workout of the week isDone
                    Iterator workouts = week.workouts.getIterator();
                    Workout lastWorkout = (Workout) workouts.next();
                    while(workouts.hasNext()) {
                        Workout work = (Workout) workouts.next();
                        if(lastWorkout.getDate().compareTo(work.getDate()) < 0)
                            lastWorkout = work;
                    }

                    // If lastWorkout isDone avance to the next week (if exists)
                    if(lastWorkout.isDone()) {
                        if(week.getNumber() != plan.weeks.size())
                            plan.setCurrentWeek(plan.getCurrentWeek()+1);
                    }

                    PlanDAO.save(plan);
                    session.flush();
                }
                return;
            }
        }
        
        //if reaches this is because the workout dont belong to the user
        throw new WorkoutDontBelongToUserException(json.get("workoutId").getAsString() + '\t' + username);
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
        
        // Verify if PersonalTrainer exists and if not create him (is the first
        // week of plan that builds)
        PersonalTrainer pt;
        if((pt = PersonalTrainerDAO.getPersonalTrainerByORMID(session,username)) == null) {
            pt = new PersonalTrainer();
            pt.setUsername(username);
        }
        
        Plan plan;
        Week week = gson.fromJson(json.get("week").toString(), Week.class);
        
        // Verify if createWeek has called to create a new plan or to add a new
        // week to an existing plan
        if(!json.has("planId")) {
            
            // Verify if Client's username is given
            if(!json.has("clientUsername"))
                throw new JsonKeyInFaultException("clientUsername");
            String clientUsername = json.get("clientUsername").getAsString();
            
            // Verify if Client's username belong to a PersonalTrainer
            Query query = session.createQuery("select PersonalTrainer.Username from PersonalTrainer where Username='" + clientUsername + "'");
            if(query.list().isEmpty())
                throw new UsernameDontBelongToClientException(clientUsername);
            
            // Verify if Client exists
            query = session.createQuery("select Client.Username from Client where Username='" + clientUsername + "'");
            if(!query.list().isEmpty())
                throw new ClientAlreadyHasAnPlanException(clientUsername);
            
            // Verify if the number of week is valid
            if(week.getNumber()!=1)
                throw new InvalidWeekNumberException(Integer.toString(week.getNumber()));
            
            // Create a new plan
            plan = new Plan();
            plan.weeks.add(week);
            plan.setCurrentWeek(1);
            plan.setDone(false);
            plan.setModified(false);
            
            // This code purpose is to detect the next SUNDAY (init of the week)
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate localDate = LocalDate.now();
            localDate = localDate.with(next(SUNDAY));
            plan.setInitialDate(Date.from(localDate.atStartOfDay(defaultZoneId).toInstant()));
            
            // Create Client to associate with the Plan
            Client client = new Client();
            client.setUsername(clientUsername);
            client.setPlan(plan);
            
            pt.plans.add(plan);

            // Only save PersonalTrainer and Client because plan belongs to
            // PersonalTrainer so when save PersonalTrainer the Plan is save too
            PersonalTrainerDAO.save(pt);
            session.flush();
            
            ClientDAO.save(client);
        }
        //Here dont need to associate Client and PersonalTrainer because
        //here adds a new week to an existing plan already associated with the previous ones
        else {
            
            // Verify if the planId is valid
            if ((plan = PlanDAO.getPlanByORMID(CoreFacade.getSession(), json.get("planId").getAsInt())) == null)
                throw new PlanDontExistException(json.get("planId").getAsString());
            
            // Verify if the number of week is valid
            if(week.getNumber()!=plan.weeks.size()+1)
                throw new InvalidWeekNumberException(Integer.toString(week.getNumber()));
            
            // Get the lastWeek to check if the new week becomes the currentWeek for Client
            Iterator weeks = plan.weeks.getIterator();
            Week lastWeek;
            while(weeks.hasNext()) {
                lastWeek = (Week) weeks.next();
                // Verify if is the current week
                if(lastWeek.getNumber() == plan.getCurrentWeek()) {
                    // Get the lastWorkout of the current Week
                    Iterator workouts = lastWeek.workouts.getIterator();
                    Workout lastWorkout = (Workout) workouts.next();
                    while(workouts.hasNext()) {
                        Workout work = (Workout) workouts.next();
                        if(lastWorkout.getDate().compareTo(work.getDate()) < 0)
                            lastWorkout = work;
                    }
                    // Verify if lastWorkout is Done
                    if (lastWorkout.isDone())
                        // Set new current Week
                        plan.setCurrentWeek(week.getNumber());
                    break;
                }
            }
            
            // Save the new state of plan
            plan.weeks.add(week);
            PlanDAO.save(plan);
        }
        session.flush();
    }
}
