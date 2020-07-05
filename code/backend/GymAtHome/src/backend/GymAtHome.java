package backend;

import beans.GymAtHomeBeanLocal;

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
			return (GymAtHomeBeanLocal) c.lookup("java:global/GymAtHome/GymAtHomeBean!beans.GymAtHomeBeanLocal");
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
		return gymAtHomeBean.createClient(infoClientAsJSON);
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public String loginClient(String infoAsJSON) {
		return gymAtHomeBean.loginClient(infoAsJSON);
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getClientProfile(String usernameAsJSON) {
		return gymAtHomeBean.getClientProfile(usernameAsJSON);
	}

	/**
	 * 
	 * @param filtersAsJSON
	 */
	public String getPersonalTrainers(String filtersAsJSON) {
		return gymAtHomeBean.getPersonalTrainers(filtersAsJSON);
	}

	/**
	 * 
	 * @param infoPTAsJSON
	 */
	public String createPersonalTrainer(String infoPTAsJSON) {
		return gymAtHomeBean.createPersonalTrainer(infoPTAsJSON);
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public String loginPersonalTrainer(String infoAsJSON) {
		return gymAtHomeBean.loginPersonalTrainer(infoAsJSON);
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getPersonalTrainerProfile(String usernameAsJSON) {
		return gymAtHomeBean.getPersonalTrainerProfile(usernameAsJSON);
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public void editClientProfile(String infoAsJSON) {
		gymAtHomeBean.editClientProfile(infoAsJSON);
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public void editPersonalTrainertProfile(String usernameAsJSON) {
		gymAtHomeBean.editPersonalTrainerProfile(usernameAsJSON);
	}

	/**
	 * 
	 * @param usernameAndWeekAsJSON
	 */
	public String getPlan(String usernameAndWeekAsJSON) {
		return gymAtHomeBean.getPlan(usernameAndWeekAsJSON);
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getBiometricData(String usernameAsJSON) {
		return gymAtHomeBean.getBiometricData(usernameAsJSON);
	}

	/**
	 * 
	 * @param usernameAndClassificationAsJSON
	 */
	public String submitClassification(String usernameAndClassificationAsJSON) {
		return gymAtHomeBean.submitClassification(usernameAndClassificationAsJSON);
	}

	/**
	 * 
	 * @param usernameAndWorkoutIdAsJSON
	 */
	public void finishWorkout(String usernameAndWorkoutIdAsJSON) {
		gymAtHomeBean.finishWorkout(usernameAndWorkoutIdAsJSON);
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getPersonalTrainerClients(String usernameAsJSON) {
		return gymAtHomeBean.getPersonalTrainerClients(usernameAsJSON);
	}

	/**
	 * 
	 * @param requestInfoAsJSON
	 */
	public void submitRequest(String requestInfoAsJSON) {
		gymAtHomeBean.submitRequest(requestInfoAsJSON);
	}

	/**
	 * 
	 * @param weekAsJson
	 */
	public void createWeek(String weekAsJson) {
		gymAtHomeBean.createWeek(weekAsJson);
	}

	/**
	 * 
	 * @param requestIdAndResponseAsJSON
	 */
	public void replyToRequest(String requestIdAndResponseAsJSON) {
		gymAtHomeBean.replyToRequest(requestIdAndResponseAsJSON);
	}

}