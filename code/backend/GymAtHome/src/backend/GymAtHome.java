package backend;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GymAtHome {

	private static GymAtHome gymAtHome;
	private GymAtHomeBeanLocal gymAtHomeBean = lookupGymAtHomeBeanLocal();

	private GymAtHome() {

	}

	private GymAtHomeBeanLocal lookupGymAtHomeBeanLocal() {
		try {
			Context c = new InitialContext();
			return (GymAtHomeBeanLocal) c.lookup("java:global/GymAtHome/GymAtHomeBean!backend.GymAtHomeBeanLocal");
		} catch (NamingException ne) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
			throw new RuntimeException(ne);
		}
	}

	public static GymAtHome getInstance() {
		if (gymAtHome == null) {
			gymAtHome = new GymAtHome();
		}
		return gymAtHome;
	}

	/**
	 * 
	 * @param infoClientAsJSON
	 */
	public String createClient(String infoClientAsJSON) {
		// TODO - implement GymAtHome.createClient
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public String loginClient(String infoAsJSON) {
		// TODO - implement GymAtHome.loginClient
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getClientProfile(String usernameAsJSON) {
		// TODO - implement GymAtHome.getClientProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param filtersAsJSON
	 */
	public String getPersonalTrainers(String filtersAsJSON) {
		// TODO - implement GymAtHome.getPersonalTrainer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoPTAsJSON
	 */
	public String createPersonalTrainer(String infoPTAsJSON) {
		// TODO - implement GymAtHome.createPersonalTrainer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public String loginPersonalTrainer(String infoAsJSON) {
		// TODO - implement GymAtHome.loginPersonalTrainer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getPersonalTrainerProfile(String usernameAsJSON) {
		// TODO - implement GymAtHome.getPersonalTrainerProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public void editClientProfile(String infoAsJSON) {
		// TODO - implement GymAtHome.editClientProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public void editPersonalTrainertProfile(String usernameAsJSON) {
		// TODO - implement GymAtHome.editPersonalTrainertProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndWeekAsJSON
	 */
	public String getPlan(String usernameAndWeekAsJSON) {
		// TODO - implement GymAtHome.getPlan
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getBiometricData(String usernameAsJSON) {
		// TODO - implement GymAtHome.getBiometricData
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndClassificationAsJSON
	 */
	public String submitClassification(String usernameAndClassificationAsJSON) {
		// TODO - implement GymAtHome.submitClassification
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndWorkoutIdAsJSON
	 */
	public void finishWorkout(String usernameAndWorkoutIdAsJSON) {
		// TODO - implement GymAtHome.finishWorkout
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public void getPersonalTrainerClients(String usernameAsJSON) {
		// TODO - implement GymAtHome.getPersonalTrainerClients
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param requestInfoAsJSON
	 */
	public void submitRequest(String requestInfoAsJSON) {
		// TODO - implement GymAtHome.submitRequest
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param weekAsJson
	 */
	public void createWeek(String weekAsJson) {
		// TODO - implement GymAtHome.createWeek
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param requestIdAndResponseAsJSON
	 */
	public void replyToRequest(String requestIdAndResponseAsJSON) {
		// TODO - implement GymAtHome.replyToRequest
		throw new UnsupportedOperationException();
	}

}