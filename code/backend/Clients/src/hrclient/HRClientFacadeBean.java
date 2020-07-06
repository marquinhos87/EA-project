package hrclient;

import org.orm.PersistentException;

@javax.ejb.Remote
public interface HRClientFacadeBean {

	/**
	 * Create Client.
	 * @param infoClientAsJSON info of client.
	 */
	String createClient(String infoClientAsJSON) throws ClientAlreadyExistsException, PersistentException, JsonKeyInFaultException;

	/**
	 * 
	 * @param infoAsJSON
	 */
	String loginClient(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, ClientDoesNotExistException;

	/**
	 * 
	 * @param usernameAsJSON
	 */
	String getClientProfile(String usernameAsJSON);

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