package hrpersonaltrainer;

import org.orm.PersistentException;

@javax.ejb.Local
public interface HRPersonalTrainerFacadeBeanLocal {

	/**
	 * 
	 * @param infoPTAsJSON
	 */
	String createPersonalTrainer(String infoPTAsJSON) throws PersistentException, PersonalTrainerAlreadyExistsException, JsonKeyInFaultException;

	/**
	 * 
	 * @param infoAsJSON
	 */
	String loginPersonalTrainer(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerNotExistsException, InvalidPasswordException;

	/**
	 * 
	 * @param usernameAsJSON
	 */
	String getPersonalTrainerProfileByClient(String usernameAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerNotExistsException, TokenIsInvalidException;

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
	void createClient(String usernameAndTokenAsJson) throws JsonKeyInFaultException, PersistentException, ClientAlreadyExistsException;
}