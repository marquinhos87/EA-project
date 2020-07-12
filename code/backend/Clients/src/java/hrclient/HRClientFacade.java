package hrclient;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.orm.PersistentException;
import org.orm.PersistentSession;

public class HRClientFacade {

    HRClientFacadeBeanLocal hRClientFacadeBean = lookupHRClientFacadeBeanLocal();

	private HRClientFacadeBean clientFacadeBean;
	private static HRClientFacade rhClientFacade;
        private static PersistentSession session;

	public static HRClientFacade getInstance() {
            if(rhClientFacade == null)
                rhClientFacade = new HRClientFacade();
            return rhClientFacade;
	}
        
        public static PersistentSession getSession() throws PersistentException {
            if(session == null || !session.isConnected())
                    session = DiagramasPersistentManager.instance().getSession();
            return session;
    }

    /**
     * 
     * @param usernameAndTokenAsJson
     */
    public void updateUserToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, TokenIsInvalidException, UserDoesNotExistException, PersistentException {
        hRClientFacadeBean.updateUserToken(usernameAndTokenAsJson);
    }

    /**
     * 
     * @param infoClientAsJSON
     */
    public String createClient(String infoClientAsJSON) throws JsonKeyInFaultException, PersistentException, ClientAlreadyExistsException, UserAlreadyExistsException {
        return hRClientFacadeBean.createClient(infoClientAsJSON);
    }
    
    public void createUser(String usernameAndTokenAsJSON) throws JsonKeyInFaultException, PersistentException, UserAlreadyExistsException{
        hRClientFacadeBean.createUser(usernameAndTokenAsJSON);
    }
    /**
     * 
     * @param infoAsJSON
     */
    public String loginClient(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, ClientDoesNotExistException, InvalidPasswordException {
        return hRClientFacadeBean.loginClient(infoAsJSON);
    }

    /**
     * 
     * @param usernameAsJSON
     */
    public String getClientProfileByClient(String usernameAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException, UserDoesNotExistException, BiometricDataDoesNotExistException {
        return hRClientFacadeBean.getClientProfileByClient(usernameAsJSON);
    }

    /**
     * 
     * @param usernameAsJSON
     */
    public String getClientProfileByPersonalTrainer(String usernameAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException, PersonalTrainerDoesNotExistException, UserDoesNotExistException, BiometricDataDoesNotExistException {
        return hRClientFacadeBean.getClientProfileByPersonalTrainer(usernameAsJSON);
    }

    /**
     * 
     * @param infoAsJSON
     */
    public void editClientProfile(String infoAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException, UserDoesNotExistException {
        hRClientFacadeBean.editClientProfile(infoAsJSON);
    }

    /**
     * 
     * @param usernameAsJSON
     */
    public String getBiometricData(String usernameAsJSON) throws JsonKeyInFaultException, PersonalTrainerDoesNotExistException, TokenIsInvalidException, PersistentException, ClientDoesNotExistException, UserDoesNotExistException, BiometricDataDoesNotExistException {
        return hRClientFacadeBean.getBiometricData(usernameAsJSON);
    }

    private HRClientFacadeBeanLocal lookupHRClientFacadeBeanLocal() {
        try {
            Context c = new InitialContext();
            return (HRClientFacadeBeanLocal) c.lookup("java:global/Clients/HRClientFacadeBean!hrclient.HRClientFacadeBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}