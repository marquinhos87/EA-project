package core;

import beans.CoreFacadeBeanLocal;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoreFacade {

	private static CoreFacade coreFacade;
	private CoreFacadeBeanLocal coreFacadeBean = lookupCoreFacadeBeanLocal();

	private CoreFacade() {

	}

	private CoreFacadeBeanLocal lookupCoreFacadeBeanLocal() {
		try {
			Context c = new InitialContext();
			return (CoreFacadeBeanLocal) c.lookup("java:global/Core/CoreFacadeBean!beans.CoreBeanLocal");
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

	/**
	 * 
	 * @param usernameAsJson
	 */
	public void createClient(String usernameAsJson) {
		// TODO - implement core.CoreFacade.createClient
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJson
	 */
	public void createPersonalTrainer(String usernameAsJson) {
		// TODO - implement core.CoreFacade.createPersonalTrainer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	public void updateToken(String usernameAndTokenAsJson) {
		// TODO - implement core.CoreFacade.updateToken
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndWeekAsJSON
	 */
	public String getPlan(String usernameAndWeekAsJSON) {
		// TODO - implement core.CoreFacade.getPlan
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndWorkoutIdAsJSON
	 */
	public void finishWorkout(String usernameAndWorkoutIdAsJSON) {
		// TODO - implement core.CoreFacade.finishWorkout
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param weekAsJson
	 */
	public void createWeek(String weekAsJson) {
		// TODO - implement core.CoreFacade.createWeek
		throw new UnsupportedOperationException();
	}

}