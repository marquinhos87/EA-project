package hrclient;

@javax.ejb.Stateless(name="HRClientFacadeBean")
@javax.ejb.Remote(HRClientFacadeBean.class)
@javax.ejb.Local(HRClientFacadeBeanLocal.class)
public class HRClientFacadeBeanBean implements HRClientFacadeBean, HRClientFacadeBeanLocal {

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	public void updateToken(String usernameAndTokenAsJson) {
		// TODO - implement HRClientFacadeBean.updateToken
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoClientAsJSON
	 */
	public String createClient(String infoClientAsJSON) {
		// TODO - implement HRClientFacadeBean.createClient
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public String loginClient(String infoAsJSON) {
		// TODO - implement HRClientFacadeBean.loginClient
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getClientProfile(String usernameAsJSON) {
		// TODO - implement HRClientFacadeBean.getClientProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public void editClientProfile(String infoAsJSON) {
		// TODO - implement HRClientFacadeBean.editClientProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getBiometricData(String usernameAsJSON) {
		// TODO - implement HRClientFacadeBean.getBiometricData
		throw new UnsupportedOperationException();
	}

}