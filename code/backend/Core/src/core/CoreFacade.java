package core;

import beans.CoreFacadeBeanLocal;
import exceptions.*;
import org.orm.PersistentException;
import org.orm.PersistentSession;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoreFacade {

	private static CoreFacade coreFacade;
	private CoreFacadeBeanLocal coreFacadeBean = lookupCoreFacadeBeanLocal();
	private static PersistentSession session;

	private CoreFacade() {

	}

	private CoreFacadeBeanLocal lookupCoreFacadeBeanLocal() {
		try {
			Context c = new InitialContext();
			return (CoreFacadeBeanLocal) c.lookup("java:global/Core/CoreFacadeBean!beans.CoreFacadeBeanLocal");
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
	public void createUserToken(String usernameAsJson) throws PersistentException, UserAlreadyExistsException {
		coreFacadeBean.createUserToken(usernameAsJson);
	}

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	public void updateToken(String usernameAndTokenAsJson) throws ClientDontExistsException, PersistentException, JsonKeyInFaultException, InvalidTokenException, UserTokenDontExistsException {
		coreFacadeBean.updateToken(usernameAndTokenAsJson);
	}

	/**
	 * 
	 * @param usernameAndWeekAsJSON
	 */
	public String getWeekByClient(String usernameAndWeekAsJSON) throws InvalidTokenException, PersistentException, JsonKeyInFaultException, ClientDontExistsException, PersonalTrainerDontExistsException, UserTokenDontExistsException {
		return coreFacadeBean.getWeekByClient(usernameAndWeekAsJSON);
	}

	/**
	 *
	 * @param usernameAndWeekAsJSON
	 */
	public String getWeekByPersonalTrainer(String usernameAndWeekAsJSON) throws InvalidTokenException, PersistentException, JsonKeyInFaultException, PersonalTrainerDontExistsException, UserTokenDontExistsException {
		return coreFacadeBean.getWeekByPersonalTrainer(usernameAndWeekAsJSON);
	}

	/**
	 * 
	 * @param usernameAndWorkoutIdAsJSON
	 */
	public void finishWorkout(String usernameAndWorkoutIdAsJSON) throws InvalidTokenException, PersistentException, JsonKeyInFaultException, ClientDontExistsException, UserTokenDontExistsException {
		coreFacadeBean.finishWorkout(usernameAndWorkoutIdAsJSON);
	}

	/**
	 * 
	 * @param weekAsJson
	 */
	public void createWeek(String weekAsJson) throws InvalidTokenException, PersistentException, JsonKeyInFaultException, PersonalTrainerDontExistsException, UserTokenDontExistsException {
		coreFacadeBean.createWeek(weekAsJson);
	}

}