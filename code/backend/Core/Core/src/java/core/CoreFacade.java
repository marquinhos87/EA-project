package core;

import beans.CoreBeanLocal;
import exceptions.*;
import org.orm.PersistentException;
import org.orm.PersistentSession;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoreFacade {

    private final CoreBeanLocal coreBean = lookupCoreBeanLocal();
    private static CoreFacade coreFacade;
    private static PersistentSession session;

    /**
     * CoreFacade empty Constructor.
     */
    private CoreFacade() {

    }

    /**
     * Make the lookup for the SessionBean CoreBean.
     * 
     * @return A local Bean about the Core
     */
    private CoreBeanLocal lookupCoreBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CoreBeanLocal) c.lookup("java:global/Core/CoreBean!beans.CoreBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    /**
     * Returns the instance itself.
     * 
     * @return The instance itself
     */
    public static CoreFacade getInstance() {
        if (coreFacade == null) {
            coreFacade = new CoreFacade();
        }
        return coreFacade;
    }

    /**
     * Returns a PersistentSession to control the number of database connections.
     * 
     * @return A PeristentSession
     * @throws PersistentException if some error occur with hibernate
     */
    public static PersistentSession getSession() throws PersistentException {
        if (session == null || !session.isConnected()) {
            session = DiagramasPersistentManager.instance().getSession();
        }
        return session;
    }

    /**
     * Create a User (Client).
     * 
     * @param usernameAsJson User username and token as a json string.
     * @throws PersistentException if some error occur with hibernate
     * @throws UserAlreadyExistsException if users token already register
     * @throws JsonKeyInFaultException if has some keys in fault 
     */
    public void createUserTokenClient(String usernameAsJson) throws UserAlreadyExistsException, JsonKeyInFaultException, PersistentException {
        coreBean.createUserTokenClient(usernameAsJson);
    }
    
    /**
     * Create a User (PersonalTrainer).
     * 
     * @param usernameAsJson User username and token as a json string.
     * @throws PersistentException if some error occur with hibernate
     * @throws UserAlreadyExistsException if users token already register
     * @throws JsonKeyInFaultException if has some keys in fault 
     */
    public void createUserTokenPersonalTrainer(String usernameAsJson) throws UserAlreadyExistsException, JsonKeyInFaultException, PersistentException {
        coreBean.createUserTokenPersonalTrainer(usernameAsJson);
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
    public void updateToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, InvalidTokenException, UserDontExistsException, PersistentException {
        coreBean.updateToken(usernameAndTokenAsJson);
    }

    /**
     * Get a week of a Client plan.
     * 
     * @param usernameAndWeekAsJSON Client's username and week as a json string.
     * @return A week as a json string.
     * @throws JsonKeyInFaultException if has some keys in fault 
     * @throws PersistentException if some error occur with hibernate
     * @throws ClientDontExistsException if Client dont exist
     * @throws UserDontExistsException if username given dont exists
     * @throws InvalidTokenException if for given user the token is invalid
     * @throws InvalidWeekNumberException if given number of the week is incorrect
     */
    public String getWeekByClient(String usernameAndWeekAsJSON) throws InvalidTokenException, PersistentException, JsonKeyInFaultException, ClientDontExistsException, UserDontExistsException, InvalidWeekNumberException {
        return coreBean.getWeekByClient(usernameAndWeekAsJSON);
    }

    /**
     * Get a week of a Client plan.
     *
     * @param usernameAndWeekAsJSON PersonalTrainer's username and week as a json string.
     * @return A week as a json string.
     * @throws JsonKeyInFaultException if has some keys in fault 
     * @throws PersistentException if some error occur with hibernate
     * @throws PersonalTrainerDontExistsException if PersonalTrainer dont exist
     * @throws UserDontExistsException if username given dont exists
     * @throws InvalidTokenException if for given user the token is invalid
     * @throws ClientDontExistsException if Client dont exist
     * @throws InvalidWeekNumberException if given number of the week is incorrect
     */
    public String getWeekByPersonalTrainer(String usernameAndWeekAsJSON) throws InvalidTokenException, PersistentException, JsonKeyInFaultException, PersonalTrainerDontExistsException, UserDontExistsException, ClientDontExistsException, InvalidWeekNumberException {
        return coreBean.getWeekByPersonalTrainer(usernameAndWeekAsJSON);
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
    public String finishWorkout(String usernameAndWorkoutIdAsJSON) throws InvalidTokenException, PersistentException, JsonKeyInFaultException, ClientDontExistsException, UserDontExistsException, WorkoutAlreadyDoneException, WorkoutDontExistException, WorkoutDontBelongToUserException {
        return coreBean.finishWorkout(usernameAndWorkoutIdAsJSON);
    }

    /**
     * Create a new week for an existing plan or create a new week for a new plan.
     * 
     * @param weekAsJson Week info as a json string.
     * @throws JsonKeyInFaultException if has some keys in fault 
     * @throws PersonalTrainerDontExistsException if given PersonalTrainer dont exist
     * @throws PersistentException if some error occur with hibernate
     * @throws InvalidTokenException if for given user the token is invalid
     * @throws UserDontExistsException if username given dont exists
     * @throws ClientAlreadyHasAnPlanException if Client already has a Plan
     * @throws PlanDontExistException if given Plan dont exist
     * @throws InvalidWeekNumberException if given number of the week is incorrect
     * @throws UsernameDontBelongToClientException if given username belong to a PersonalTrainer
     */
    public void createWeek(String weekAsJson) throws InvalidTokenException, PersistentException, JsonKeyInFaultException, PersonalTrainerDontExistsException, UserDontExistsException, ClientAlreadyHasAnPlanException, PlanDontExistException, InvalidWeekNumberException, UsernameDontBelongToClientException {
        coreBean.createWeek(weekAsJson);
    }
    
    /**
     * 
     * @param usernameAsJSON
     * @throws JsonKeyInFaultException
     * @throws PersistentException
     * @throws UserDontExistsException
     * @throws InvalidTokenException 
     */
    public void finishPlan(String usernameAsJSON) throws JsonKeyInFaultException, PersistentException, UserDontExistsException, InvalidTokenException, ClientDontExistsException {
        coreBean.finishPlan(usernameAsJSON);
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
    public void removeUserTokenClient(String usernameAsJson) throws PersistentException, JsonKeyInFaultException, UserDontExistsException, InvalidTokenException {
        coreBean.removeUserTokenClient(usernameAsJson);
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
    public void removeUserTokenPersonalTrainer(String usernameAsJson) throws PersistentException, JsonKeyInFaultException, UserDontExistsException, InvalidTokenException {
        coreBean.removeUserTokenPersonalTrainer(usernameAsJson);
    }
}