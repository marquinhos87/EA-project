package hrclient;

@javax.ejb.Remote
public interface HRClientFacadeBean {

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	void updateToken(String usernameAndTokenAsJson);

	/**
	 * 
	 * @param infoClientAsJSON
	 */
	String createClient(String infoClientAsJSON);

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