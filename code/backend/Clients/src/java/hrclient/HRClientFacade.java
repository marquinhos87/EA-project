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

    private static HRClientFacade hrClientFacade;
    private static PersistentSession session;

    public static HRClientFacade getInstance() {
            if(hrClientFacade == null)
                hrClientFacade = new HRClientFacade();
            return hrClientFacade;
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
            hRClientFacadeBean.updateUserToken(usernameAndTokenAsJson);
    }

    /**
     * 
     * @param infoClientAsJSON
     */
    public String createClient(String infoClientAsJSON) throws PersistentException, JsonKeyInFaultException, ClientAlreadyExistsException {
            return hRClientFacadeBean.createClient(infoClientAsJSON);
    }

    /**
     * 
     * @param infoAsJSON
     */
    public String loginClient(String infoAsJSON) throws InvalidPasswordException, PersistentException, JsonKeyInFaultException, ClientDoesNotExistException {
            return hRClientFacadeBean.loginClient(infoAsJSON);
    }

    /**
     * 
     * @param usernameAsJSON
     */
    public String getClientProfileByClient(String usernameAsJSON) throws TokenIsInvalidException, PersistentException, JsonKeyInFaultException, ClientDoesNotExistException {
            return hRClientFacadeBean.getClientProfileByClient(usernameAsJSON);
    }

    /**
     * 
     * @param usernameAsJSON
     */
    public String getClientProfileByPersonalTrainer(String usernameAsJSON) throws TokenIsInvalidException, PersonalTrainerDoesNotExistException, PersistentException, JsonKeyInFaultException, ClientDoesNotExistException {
            return hRClientFacadeBean.getClientProfileByPersonalTrainer(usernameAsJSON);
    }

    /**
     * 
     * @param infoAsJSON
     */
    public void editClientProfile(String infoAsJSON) throws TokenIsInvalidException, PersistentException, JsonKeyInFaultException, ClientDoesNotExistException {
            hRClientFacadeBean.editClientProfile(infoAsJSON);
    }

    /**
     * 
     * @param usernameAsJSON
     */
    public String getBiometricData(String usernameAsJSON) throws TokenIsInvalidException, ClientDoesNotExistException, PersonalTrainerDoesNotExistException, JsonKeyInFaultException, PersistentException {
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