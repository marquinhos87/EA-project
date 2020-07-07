package hrclient;

import org.orm.PersistentException;

@javax.ejb.Remote
public interface HRClientFacadeBean {

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
	String loginClient(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, ClientDoesNotExistException;

	/**
	 * 
	 * @param usernameAsJSON
	 */
	String getClientProfileByClient(String usernameAsJSON);

	/**
	 * 
	 * @param usernameAsJSON
	 */
	String getClientProfilePersonalTrainer(String usernameAsJSON);

	/**
	 * 
	 * @param infoAsJSON
	 */
	void editClientProfile(String infoAsJSON);

	/**
	 * 
	 * @param usernameAsJSON
	 */
	String getBiometricData(String usernameAsJSON);
}