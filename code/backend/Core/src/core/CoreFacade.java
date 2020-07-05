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
		coreFacadeBean.createClient(usernameAsJson);
	}

	/**
	 * 
	 * @param usernameAsJson
	 */
	public void createPersonalTrainer(String usernameAsJson) {
		coreFacadeBean.createPersonalTrainer(usernameAsJson);
	}

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	public void updateToken(String usernameAndTokenAsJson) {
		coreFacadeBean.updateToken(usernameAndTokenAsJson);
	}

	/**
	 * 
	 * @param usernameAndWeekAsJSON
	 */
	public String getPlan(String usernameAndWeekAsJSON) {
		return coreFacadeBean.getPlan(usernameAndWeekAsJSON);
	}

	/**
	 * 
	 * @param usernameAndWorkoutIdAsJSON
	 */
	public void finishWorkout(String usernameAndWorkoutIdAsJSON) {
		coreFacadeBean.finishWorkout(usernameAndWorkoutIdAsJSON);
	}

	/**
	 * 
	 * @param weekAsJson
	 */
	public void createWeek(String weekAsJson) {
		coreFacadeBean.createWeek(weekAsJson);
	}

}