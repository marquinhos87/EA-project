package hrclient;

import org.hibernate.Session;
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
	public void updateToken(String usernameAndTokenAsJson) {
		// TODO - implement HRClientFacade.updateToken
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
	public String getClientProfile(String usernameAsJSON) {
		// TODO - implement HRClientFacade.getClientProfile
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