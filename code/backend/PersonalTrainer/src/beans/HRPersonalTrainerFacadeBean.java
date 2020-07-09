package beans;

import hrpersonaltrainer.*;
import org.orm.PersistentException;

@javax.ejb.Remote
public interface HRPersonalTrainerFacadeBean {

	/**
	 * 
	 * @param infoPTAsJSON
	 */
	String createPersonalTrainer(String infoPTAsJSON) throws PersistentException, PersonalTrainerAlreadyExistsException, JsonKeyInFaultException, UserAlreadyExistsException;

	/**
	 * 
	 * @param infoAsJSON
	 */
	String loginPersonalTrainer(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerNotExistsException, InvalidPasswordException, UserNotExistsException;

	/**
	 * 
	 * @param usernameAsJSON
	 */
	String getPersonalTrainerProfileByClient(String usernameAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerNotExistsException, TokenIsInvalidException, ClientNotExistsException;

	/**
	 *
	 * @param infoAsJSON
	 */
	String getPersonalTrainerProfileByPersonalTrainer(String infoAsJSON) throws PersistentException, JsonKeyInFaultException, PersonalTrainerNotExistsException, TokenIsInvalidException;

	/**
	 * 
	 * @param usernameAsJSON
	 */
	void editPersonalTrainertProfile(String usernameAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerNotExistsException, TokenIsInvalidException;

	/**
	 * 
	 * @param filtersAsJSON
	 */
	String getPersonalTrainers(String filtersAsJSON) throws JsonKeyInFaultException, ClientNotExistsException, TokenIsInvalidException, PersistentException;

	/**
	 * 
	 * @param usernameAndClassificationAsJSON
	 */
	void submitClassification(String usernameAndClassificationAsJSON) throws JsonKeyInFaultException, ClientNotExistsException, TokenIsInvalidException, PersistentException, PersonalTrainerNotExistsException;

	/**
	 * 
	 * @param usernameAsJSON
	 */
	String getPersonalTrainerClients(String usernameAsJSON) throws JsonKeyInFaultException, PersonalTrainerNotExistsException, TokenIsInvalidException, PersistentException;

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	void addClientToPersonalTrainer(String usernameAndTokenAsJson) throws JsonKeyInFaultException, PersistentException, ClientAlreadyExistsException, PersonalTrainerNotExistsException, TokenIsInvalidException;

	void updateClientToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, PersistentException, TokenIsInvalidException, UserNotExistsException;

	void createClient(String json) throws JsonKeyInFaultException, PersistentException, UserAlreadyExistsException;
}