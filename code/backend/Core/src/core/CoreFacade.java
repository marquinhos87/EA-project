package core;

import beans.CoreFacadeBeanLocal;
import exceptions.ClientAlreadyExistsException;
import exceptions.ClientDontExistsException;
import exceptions.PersonalTrainerAlreadyExistsException;
import exceptions.PersonalTrainerDontExistsException;
import org.orm.PersistentException;

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
	public void createClient(String usernameAsJson) throws ClientAlreadyExistsException, PersistentException {
		coreFacadeBean.createClient(usernameAsJson);
	}

	/**
	 * 
	 * @param usernameAsJson
	 */
	public void createPersonalTrainer(String usernameAsJson) throws PersistentException, PersonalTrainerAlreadyExistsException {
		coreFacadeBean.createPersonalTrainer(usernameAsJson);
	}

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	public void updateTokenClient(String usernameAndTokenAsJson) throws ClientDontExistsException, PersistentException {
		coreFacadeBean.updateTokenClient(usernameAndTokenAsJson);
	}

	/**
	 *
	 * @param usernameAndTokenAsJson
	 */
	public void updateTokenPersonalTrainer(String usernameAndTokenAsJson) throws PersonalTrainerDontExistsException, PersistentException {
		coreFacadeBean.updateTokenPersonalTrainer(usernameAndTokenAsJson);
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