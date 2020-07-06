package hrclient;

import org.orm.PersistentException;

@javax.ejb.Local
public interface HRClientFacadeBeanLocal {

	/**
	 * Generate/update a new token
	 * @param usernameAsJson username of client
	 */
	String updateToken(String usernameAsJson) throws PersistentException, ClientDoesNotExistException, TokenInFaultException, TokenIsInvalidException, InvalidJSONException;

	/**
	 * Create Client.
	 * @param infoClientAsJSON info of client.
	 */
	String createClient(String infoClientAsJSON) throws ClientAlreadyExistsException, PersistentException;

	/**
	 * 
	 * @param infoAsJSON
	 */
	String loginClient(String infoAsJSON);

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