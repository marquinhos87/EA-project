package core;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoreFacade {

	private static CoreFacade coreFacade;
	private CoreFacadeBean coreFacadeBean;

	private CoreFacade() {

	}

	private CoreFacadeBeanLocal lookupGymAtHomeBeanLocal() {
		try {
			Context c = new InitialContext();
			return (CoreFacadeBeanLocal) c.lookup("java:global/Core/CoreFacadeBean!core.CoreBeanLocal");
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
		// TODO - implement CoreFacade.createClient
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJson
	 */
	public void createPersonalTrainer(String usernameAsJson) {
		// TODO - implement CoreFacade.createPersonalTrainer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	public void updateToken(String usernameAndTokenAsJson) {
		// TODO - implement CoreFacade.updateToken
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndWeekAsJSON
	 */
	public String getPlan(String usernameAndWeekAsJSON) {
		// TODO - implement CoreFacade.getPlan
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndWorkoutIdAsJSON
	 */
	public void finishWorkout(String usernameAndWorkoutIdAsJSON) {
		// TODO - implement CoreFacade.finishWorkout
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param weekAsJson
	 */
	public void createWeek(String weekAsJson) {
		// TODO - implement CoreFacade.createWeek
		throw new UnsupportedOperationException();
	}

}