package beans;

import javax.ejb.Local;

@Local
public interface GymAtHomeBeanLocal {

	/**
	 * 
	 * @param infoClientAsJSON
	 */
	String createClient(String infoClientAsJSON);

	/**
	 * 
	 * @param infoPTAsJSON
	 */
	String createPersonalTrainer(String infoPTAsJSON);

	/**
	 * 
	 * @param infoAsJSON
	 */
	String loginClient(String infoAsJSON);

	/**
	 * 
	 * @param infoAsJSON
	 */
	String loginPersonalTrainer(String infoAsJSON);

	/**
	 * 
	 * @param usernameAsJSON
	 */
	String getClientProfile(String usernameAsJSON);

	/**
	 * 
	 * @param usernameAsJSON
	 */
	String getPersonalTrainerProfile(String usernameAsJSON);

	/**
	 * 
	 * @param infoAsJSON
	 */
	void editClientProfile(String infoAsJSON);

	/**
	 * 
	 * @param usernameAsJSON
	 */
	void editPersonalTrainertProfile(String usernameAsJSON);

	/**
	 * 
	 * @param usernameAndWeekAsJSON
	 */
	String getPlan(String usernameAndWeekAsJSON);

	/**
	 * 
	 * @param filtersAsJSON
	 */
	String getPersonalTrainers(String filtersAsJSON);

	/**
	 * 
	 * @param usernameAsJSON
	 */
	String getBiometricData(String usernameAsJSON);

	/**
	 * 
	 * @param usernameAndClassificationAsJSON
	 */
	String submitClassification(String usernameAndClassificationAsJSON);

	/**
	 * 
	 * @param usernameAndWorkoutIdAsJSON
	 */
	void finishWorkout(String usernameAndWorkoutIdAsJSON);

	/**
	 * 
	 * @param usernameAsJSON
	 */
	String getPersonalTrainerClients(String usernameAsJSON);

	/**
	 * 
	 * @param requestInfoAsJSON
	 */
	void submitRequest(String requestInfoAsJSON);

	/**
	 * 
	 * @param weekAsJson
	 */
	void createWeek(String weekAsJson);

	/**
	 * 
	 * @param requestIdAndResponseAsJSON
	 */
	void replyToRequest(String requestIdAndResponseAsJSON);
}