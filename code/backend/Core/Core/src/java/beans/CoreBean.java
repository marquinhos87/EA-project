/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
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
    
    private static final int DAY = 1000*60*60*24; // 1 day in miliseconds
    private static final int WEEK = 7 * DAY;      // 1 week (7 days) in miliseconds

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
        
        List<Week> weeks = (List<Week>) session.createQuery("from Week where PlanID=" + plan.getID() + " and Number=" + weekNumber).list();
        if (weeks.isEmpty())
            throw new InvalidWeekNumberException(Integer.toString(weekNumber));
       
        JsonElement je = gson.toJsonTree(weeks.get(0), Week.class);
        je.getAsJsonObject().addProperty("numberOfWeeks", plan.weeks.size());
        je.getAsJsonObject().addProperty("currentWeek", plan.getCurrentWeek());
        je.getAsJsonObject().addProperty("personalTrainerUsername", plan.getPersonalTrainer().getUsername());
        
        return gson.toJson(je);
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
        
        List<Week> weeks = (List<Week>) session.createQuery("from Week where PlanID=" + plan.getID() + " and Number=" + weekNumber).list();
        if (weeks.isEmpty())
            throw new InvalidWeekNumberException(Integer.toString(weekNumber));

        JsonElement je = gson.toJsonTree(weeks.get(0), Week.class);
        je.getAsJsonObject().addProperty("numberOfWeeks", plan.weeks.size());
        je.getAsJsonObject().addProperty("currentWeek", plan.getCurrentWeek());
        je.getAsJsonObject().addProperty("planId", plan.getID());
        
        return gson.toJson(je);
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
    public String finishWorkout(String usernameAndWorkoutIdAsJSON) throws InvalidTokenException, JsonKeyInFaultException, UserDontExistsException, ClientDontExistsException, PersistentException, WorkoutDontExistException, WorkoutDontBelongToUserException, WorkoutAlreadyDoneException {
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
        
        if(workout.isDone())
            throw new WorkoutAlreadyDoneException(Integer.toString(workoutId));
        
        // verify if workout belogns to client
        Week workoutWeek = workout.getWeek();
        if (plan.weeks.contains(workoutWeek) == false) {
            throw new WorkoutDontBelongToUserException(json.get("workoutId").getAsString() + '\t' + username);
        }
        
        workout.setDone(true);
       
        // if all workouts from the given workout week are all done, then go to the next following week if exits
        boolean hasDoneAllWorkouts = true;
        Iterator workouts = workoutWeek.workouts.getIterator();
        while(workouts.hasNext()) {
            Workout tmp = (Workout) workouts.next();
             // Verify if there is any workout not done
            if (tmp.isDone() == false) {
                hasDoneAllWorkouts = false;
                break;
            }
        }
        if (hasDoneAllWorkouts) {
            if (workoutWeek.getNumber() < plan.weeks.size()) 
                plan.setCurrentWeek(workoutWeek.getNumber()+1);
            else if (workoutWeek.getNumber() == plan.weeks.size())
                plan.setCurrentWeek(workoutWeek.getNumber());
        }  
        
        PlanDAO.save(plan);
        session.flush();
        
        return "{ \"username\": \"" + plan.getPersonalTrainer().getUsername() + "\" }";
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
        
        // no need to check if exists because we created it before
        PersonalTrainer pt = PersonalTrainerDAO.getPersonalTrainerByORMID(session,username);
        
        Plan plan;
        Week week = gson.fromJson(json.get("week").toString(), Week.class); // evoques custom serializer from package parseJSON
        // we need to create a week copy to iterate and at the same time modify Workouts inside
        Week weekCopy = gson.fromJson(json.get("week").toString(), Week.class); // evoques custom serializer from package parseJSON
        
        // Verify if createWeek has been called to create a new plan (planId doesn't exist yet)
        // or to add a new week to an existing plan
        if(!json.has("planId")) { // first week of the plan
            
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
            
             // This code purpose is to detect the week's first and last day
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate nextMonday = LocalDate.now().with(next(DayOfWeek.MONDAY));
            Date firstDay = Date.from(nextMonday.atStartOfDay(defaultZoneId).toInstant());
            Date lastDay = Date.from(nextMonday.with(next(SUNDAY)).atStartOfDay(defaultZoneId).toInstant());
                    
            week.setNumber(1); // first plan week
            week.setInitialDate(firstDay);
            week.setFinalDate(lastDay);
            
            // this code need to be here (order is important!) because only now we have set the week's initalDay
            Iterator workouts = weekCopy.workouts.getIterator();
            week.workouts.clear();
            while(workouts.hasNext()) {
                Workout workout = (Workout) workouts.next();
                Date day = new Date(firstDay.getTime() + (DAY *(workout.getWeekDay()-1)));
                Workout w = workout.clone();
                w.setDate(day);
                w.setWeek(week);
                w.setDone(false);
                week.workouts.add(w);
            }
            
            // Create a new plan
            plan = new Plan();
            plan.weeks.add(week);
            plan.setCurrentWeek(1);
            plan.setDone(false);
            plan.setModified(false);
            plan.setInitialDate(firstDay);
            plan.setPersonalTrainer(pt);
            
            // Create Client to associate with the Plan
            Client client = new Client();
            client.setUsername(clientUsername);
            client.setPlan(plan);
            
            pt.plans.add(plan);

            // we only save PersonalTrainer and Client because plan belongs to
            // PersonalTrainer so when we save PersonalTrainer the Plan is saved too
            // PersonalTrainerDAO.save(pt);
            
            ClientDAO.save(client);
            session.flush();
        }
        //Here we don't need to associate Client and PersonalTrainer to Plan because
        // it adds a new week to an existing plan already associated with the previous ones
        else {
            
            int planID = json.get("planId").getAsInt();
            
            // Verify if the plan exists in database
            if ((plan = PlanDAO.getPlanByORMID(CoreFacade.getSession(), planID)) == null)
                throw new PlanDontExistException(Integer.toString(planID));
            
            week.setNumber(plan.weeks.size()+1);                     
            Week lastWeek = (Week) session.createQuery("from Week where PlanID=" + planID + " and Number=" + plan.weeks.size()).list().get(0);
            
            boolean hasDoneAllWorkouts = true;
            Iterator workouts = lastWeek.workouts.getIterator();
            while(workouts.hasNext()) {
                Workout workout = (Workout) workouts.next();
                 // Verify if there is any workout not done
                if (workout.isDone() == false) {
                    hasDoneAllWorkouts = false;
                    break;
                }
            }
            if (hasDoneAllWorkouts) plan.setCurrentWeek(week.getNumber());  
                       
            Date firstDay = new Date(lastWeek.getFinalDate().getTime()+DAY);
            Date lastDay = new Date(lastWeek.getFinalDate().getTime()+WEEK);
            week.setInitialDate(firstDay);
            week.setFinalDate(lastDay);
            
            // this code need to be here (order is important!) because only now we have set the week's initalDay
            workouts = weekCopy.workouts.getIterator();
            week.workouts.clear();
            while(workouts.hasNext()) {
                Workout workout = (Workout) workouts.next();
                Date day = new Date(firstDay.getTime() + (DAY *(workout.getWeekDay()-1)));
                Workout w = workout.clone();
                w.setDate(day);
                w.setWeek(week);
                w.setDone(false);
                week.workouts.add(w);
            }                         
    
            // Save the new state of plan
            plan.weeks.add(week);
            PlanDAO.save(plan);
            session.flush();
        }
    }
    
    /**
     * 
     * @param usernameAsJSON
     * @throws JsonKeyInFaultException
     * @throws PersistentException
     * @throws UserDontExistsException
     * @throws InvalidTokenException 
     */
    @Override
    public void finishPlan(String usernameAsJSON) throws JsonKeyInFaultException, PersistentException, UserDontExistsException, InvalidTokenException, ClientDontExistsException {
        JsonObject json = Utils.validateJson(gson, usernameAsJSON, Arrays.asList("token", "username"));

        String username = json.get("username").getAsString();
        String token = json.get("token").getAsString();

        // Validate given token
        PersistentSession session = CoreFacade.getSession();
        Utils.validateToken(token,username,session);
        
        Client client;
        if((client = ClientDAO.getClientByORMID(session,username)) == null)
            throw new ClientDontExistsException(username);

        ClientDAO.delete(client);
        
        //Query q = session.createQuery("delete Client where username=" + username);
        //q.executeUpdate();
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
