package hrclient;

import org.orm.PersistentException;
import org.orm.PersistentSession;

public class HRClientFacade {

	private HRClientFacadeBean clientFacadeBean;
	private static HRClientFacade rhClientFacade;
	private static PersistentSession session;

	public static HRClientFacade getInstance() {
		// TODO - implement HRClientFacade.getInstance
		throw new UnsupportedOperationException();
	}

	public static PersistentSession getSession() throws PersistentException {
		if(session == null)
			session = DiagramasPersistentManager.instance().getSession();
		return session;
	}

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	public void updateUserToken(String usernameAndTokenAsJson) {
		// TODO - implement HRClientFacade.updateUserToken
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoClientAsJSON
	 */
	public String createClient(String infoClientAsJSON) {
		// TODO - implement HRClientFacade.createClient
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public String loginClient(String infoAsJSON) {
		// TODO - implement HRClientFacade.loginClient
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getClientProfileByClient(String usernameAsJSON) {
		// TODO - implement HRClientFacade.getClientProfileByClient
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getClientProfileByPersonalTrainer(String usernameAsJSON) {
		// TODO - implement HRClientFacade.getClientProfileByPersonalTrainer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public void editClientProfile(String infoAsJSON) {
		// TODO - implement HRClientFacade.editClientProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getBiometricData(String usernameAsJSON) {
		// TODO - implement HRClientFacade.getBiometricData
		throw new UnsupportedOperationException();
	}

}