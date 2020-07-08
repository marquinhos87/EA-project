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
		clientFacadeBean.updateUserToken(usernameAndTokenAsJson);
	}

	/**
	 * 
	 * @param infoClientAsJSON
	 */
	public String createClient(String infoClientAsJSON) throws PersistentException, JsonKeyInFaultException, ClientAlreadyExistsException {
		return clientFacadeBean.createClient(infoClientAsJSON);
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public String loginClient(String infoAsJSON) throws InvalidPasswordException, PersistentException, JsonKeyInFaultException, ClientDoesNotExistException {
		return clientFacadeBean.loginClient(infoAsJSON);
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getClientProfileByClient(String usernameAsJSON) throws TokenIsInvalidException, PersistentException, JsonKeyInFaultException, ClientDoesNotExistException {
		return clientFacadeBean.getClientProfileByClient(usernameAsJSON);
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getClientProfileByPersonalTrainer(String usernameAsJSON) throws TokenIsInvalidException, PersonalTrainerDoesNotExistException, PersistentException, JsonKeyInFaultException, ClientDoesNotExistException {
		return clientFacadeBean.getClientProfileByPersonalTrainer(usernameAsJSON);
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public void editClientProfile(String infoAsJSON) throws TokenIsInvalidException, PersistentException, JsonKeyInFaultException, ClientDoesNotExistException {
		clientFacadeBean.editClientProfile(infoAsJSON);
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getBiometricData(String usernameAsJSON) throws TokenIsInvalidException, ClientDoesNotExistException, PersonalTrainerDoesNotExistException, JsonKeyInFaultException, PersistentException {
		return clientFacadeBean.getBiometricData(usernameAsJSON);
	}

}