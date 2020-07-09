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

    private CoreFacade() {

    }

    private CoreBeanLocal lookupCoreBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CoreBeanLocal) c.lookup("java:global/Core/CoreBean!beans.CoreBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    public static CoreFacade getInstance() {
        if (coreFacade == null) {
            coreFacade = new CoreFacade();
        }
        return coreFacade;
    }

    public static PersistentSession getSession() {
        if (session == null) {
            try {
                session = DiagramasPersistentManager.instance().getSession();
            } catch (PersistentException e) {
                e.printStackTrace();
            }
        }
        return session;
    }

    /**
     * 
     * @param usernameAsJson
     */
    public void createUserToken(String usernameAsJson) throws UserAlreadyExistsException, JsonKeyInFaultException, PersistentException {
        coreBean.createUserToken(usernameAsJson);
    }

    /**
     * 
     * @param usernameAndTokenAsJson
     */
    public void updateToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, InvalidTokenException, UserDontExistsException, PersistentException {
        coreBean.updateToken(usernameAndTokenAsJson);
    }

    /**
     * 
     * @param usernameAndWeekAsJSON
     */
    public String getWeekByClient(String usernameAndWeekAsJSON) throws InvalidTokenException, PersistentException, JsonKeyInFaultException, ClientDontExistsException, UserDontExistsException, InvalidWeekNumberException {
        return coreBean.getWeekByClient(usernameAndWeekAsJSON);
    }

    /**
     *
     * @param usernameAndWeekAsJSON
     */
    public String getWeekByPersonalTrainer(String usernameAndWeekAsJSON) throws InvalidTokenException, PersistentException, JsonKeyInFaultException, PersonalTrainerDontExistsException, UserDontExistsException, ClientDontExistsException, InvalidWeekNumberException {
        return coreBean.getWeekByPersonalTrainer(usernameAndWeekAsJSON);
    }

    /**
     * 
     * @param usernameAndWorkoutIdAsJSON
     */
    public void finishWorkout(String usernameAndWorkoutIdAsJSON) throws InvalidTokenException, PersistentException, JsonKeyInFaultException, ClientDontExistsException, UserDontExistsException, WorkoutAlreadyDoneException, WorkoutDontExistException, WorkoutDontBelongToUserException {
        coreBean.finishWorkout(usernameAndWorkoutIdAsJSON);
    }

    /**
     * 
     * @param weekAsJson
     */
    public void createWeek(String weekAsJson) throws InvalidTokenException, PersistentException, JsonKeyInFaultException, PersonalTrainerDontExistsException, UserDontExistsException, ClientAlreadyHasAnPlanException, PlanDontExistException, InvalidWeekNumberException {
        coreBean.createWeek(weekAsJson);
    }
}