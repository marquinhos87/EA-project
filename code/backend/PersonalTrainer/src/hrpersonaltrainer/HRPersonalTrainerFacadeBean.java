package hrpersonaltrainer;

import org.orm.PersistentException;

@javax.ejb.Remote
public interface HRPersonalTrainerFacadeBean {

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	void updateToken(String usernameAndTokenAsJson);

	/**
	 * 
	 * @param infoPTAsJSON
	 */
	String createPersonalTrainer(String infoPTAsJSON) throws PersistentException, PersonalTrainerAlreadyExistsException;

	/**
	 * 
	 * @param infoAsJSON
	 */
	String loginPersonalTrainer(String infoAsJSON);

	/**
	 * 
	 * @param usernameAsJSON
	 */
	String getPersonalTrainerProfile(String usernameAsJSON);

	/**
	 * 
	 * @param usernameAsJSON
	 */
	void editPersonalTrainertProfile(int usernameAsJSON);

	/**
	 * 
	 * @param filtersAsJSON
	 */
	String getPersonalTrainers(String filtersAsJSON);

	/**
	 * 
	 * @param usernameAndClassificationAsJSON
	 */
	String submitClassification(int usernameAndClassificationAsJSON);

	/**
	 * 
	 * @param usernameAsJSON
	 */
	String getPersonalTrainerClients(int usernameAsJSON);

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	void createClient(String usernameAndTokenAsJson);
}