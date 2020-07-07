package hrclient;

import org.orm.PersistentException;

@javax.ejb.Local
public interface HRClientFacadeBeanLocal {

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	void updateUserToken(String usernameAndTokenAsJson);

	/**
	 * 
	 * @param infoClientAsJSON
	 */
	String createClient(String infoClientAsJSON) throws JsonKeyInFaultException, PersistentException, ClientAlreadyExistsException;

	/**
	 * 
	 * @param infoAsJSON
	 */
	String loginClient(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, ClientDoesNotExistException, InvalidPasswordException;

	/**
	 * 
	 * @param usernameAsJSON
	 */
	String getClientProfileByClient(String usernameAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException;

	/**
	 * 
	 * @param usernameAsJSON
	 */
	String getClientProfileByPersonalTrainer(String usernameAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException, PersonalTrainerDoesNotExistException;

	/**
	 * 
	 * @param infoAsJSON
	 */
	void editClientProfile(String infoAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException;

	/**
	 * 
	 * @param usernameAsJSON
	 */
	String getBiometricData(String usernameAsJSON) throws JsonKeyInFaultException, PersonalTrainerDoesNotExistException, TokenIsInvalidException, PersistentException, ClientDoesNotExistException;
}