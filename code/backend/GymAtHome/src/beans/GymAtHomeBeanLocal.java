package beans;

import javax.ejb.Local;
import java.io.IOException;

@Local
public interface GymAtHomeBeanLocal {

	/**
	 * Create a Client with the info submitted.
	 * 
	 * @param infoClientAsJSON All Client info as json string.
	 * @return A Client in json string.
	 */
	String createClient(String infoClientAsJSON) throws IOException;

	/**
	 * Create a PersonalTrainer with the info submitted.
	 * 
	 * @param infoPTAsJSON All PersonalTrainer info as json string.
	 * @return A PersonalTrainer in json string.
	 */
	String createPersonalTrainer(String infoPTAsJSON) throws IOException;

	/**
	 * Authenticate Client with the credentials provided.
	 * 
	 * @param infoAsJSON Client credentials as json string.
	 * @return A string with login status, True if ok or False if something is wrong.
	 */
	String loginClient(String infoAsJSON);

	/**
	 * Authenticate PersonalTrainer with the credentials provided.
	 * 
	 * @param infoAsJSON PersonalTrainer credentials as json string.
	 * @return A string with login status, True if ok or False if something is wrong.
	 */
	String loginPersonalTrainer(String infoAsJSON);

	/**
	 * Get a Client profile by Client username.
	 * 
	 * @param usernameAsJSON Client username as json string.
	 * @return Client info.
	 */
	String getClientProfile(String usernameAsJSON);

	/**
	 * Get a PersonalTrainer profile by PersonalTrainer username.
	 * 
	 * @param usernameAsJSON PersonalTrainer username as json string.
	 * @return PersonalTrainer info.
	 */
	String getPersonalTrainerProfile(String usernameAsJSON);

	/**
	 * Edit Client info with new info given.
	 * 
	 * @param infoAsJSON Client new infos as json string.
	 */
	void editClientProfile(String infoAsJSON);

	/**
	 * Edit PersonalTrainer info with new info given.
	 * 
	 * @param usernameAsJSON PersonalTrainer new infos as json string.
	 */
	void editPersonalTrainerProfile(String usernameAsJSON);

	/**
	 * Retrieves tha actual week of plan of that Client.
	 * 
	 * @param usernameAndWeekAsJSON Client username and current week of plan as json string.
	 * @return Client plan (actual week of the plan).
	 */
	String getPlan(String usernameAndWeekAsJSON);

	/**
	 * Retrieves the PersonalTrainers that corresponded to the filters given by Client.
	 * 
	 * @param filtersAsJSON Filters introduced by Client as json string.
	 * @return PersonalTrainers filtered.
	 */
	String getPersonalTrainers(String filtersAsJSON);

	/**
	 * Retrieves the most recent BioMetric data from a Client.
	 * 
	 * @param usernameAsJSON Client username as json string.
	 * @return Client BioMetric data.
	 */
	String getBiometricData(String usernameAsJSON);

	/**
	 * Submit a classification to a PersonalTrainer given by a Client.
	 *
	 * @param usernameAndClassificationAsJSON PersonalTrainer username and classification attributed by Client as a json string.
	 * @return
	 */
	String submitClassification(String usernameAndClassificationAsJSON);

	/**
	 * Client submit that as completed a workout.
	 * 
	 * @param usernameAndWorkoutIdAsJSON Client username and Workout Id as a json string.
	 */
	void finishWorkout(String usernameAndWorkoutIdAsJSON);

	/**
	 * Retrieves all Clients of a PersonalTrainer.
	 * 
	 * @param usernameAsJSON PersonalTrainer username.
	 * @return PersonalTrainer clients.
	 */
	String getPersonalTrainerClients(String usernameAsJSON);

	/**
	 * Request submitted by a Client to a PersonalTrainer.
	 * 
	 * @param requestInfoAsJSON Request info submitted by Client.
	 */
	void submitRequest(String requestInfoAsJSON);

	/**
	 * Create and add a week of workouts to a plan of a Client.
	 * 
	 * @param weekAsJson Week info created by PersonalTrainer.
	 */
	void createWeek(String weekAsJson);

	/**
	 * Response given by a PersonalTrainer to a Request submitted to him by a Client.
	 * 
	 * @param requestIdAndResponseAsJSON Request Id and Response by PersonalTrainer to the request.
	 */
	void replyToRequest(String requestIdAndResponseAsJSON);
}