package hrpersonaltrainer;

import beans.HRPersonalTrainerFacadeBeanBean;
import beans.HRPersonalTrainerFacadeBeanLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.orm.PersistentException;
import org.orm.PersistentSession;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class HRPersonalTrainerFacade {

    private static HRPersonalTrainerFacade rhPersonalTrainerFacade = null;
    private static PersistentSession session = null;
    private final HRPersonalTrainerFacadeBeanLocal personalTrainerFacadeBean = lookupHRPersonalTrainerFacadeBeanLocal();
    
    public String hasSubmittedClassification(String json) throws JsonKeyInFaultException, ClientNotExistsException, TokenIsInvalidException, PersistentException, UserNotExistsException {
        return personalTrainerFacadeBean.hasSubmittedClassification(json);
    }

    private HRPersonalTrainerFacade() throws PersistentException {}

    public static HRPersonalTrainerFacade getInstance() throws PersistentException {
            if (rhPersonalTrainerFacade == null) {
                    rhPersonalTrainerFacade = new HRPersonalTrainerFacade();
            }
            return rhPersonalTrainerFacade;
    }

    public static PersistentSession getSession() throws PersistentException {
            if (session == null || session.isConnected() == false) session = DiagramasPersistentManager.instance().getSession();
            return session;
    }

    public String createPersonalTrainer(String infoPTAsJSON) throws PersistentException, PersonalTrainerAlreadyExistsException, JsonKeyInFaultException, UserAlreadyExistsException {
            return personalTrainerFacadeBean.createPersonalTrainer(infoPTAsJSON);
    }

    public String loginPersonalTrainer(String infoAsJSON) throws PersistentException, PersonalTrainerNotExistsException, JsonKeyInFaultException, InvalidPasswordException, UserNotExistsException {
            return personalTrainerFacadeBean.loginPersonalTrainer(infoAsJSON);
    }

    public String getPersonalTrainerProfileByClient(String usernameAsJSON) throws PersistentException, PersonalTrainerNotExistsException, JsonKeyInFaultException, TokenIsInvalidException, ClientNotExistsException, UserNotExistsException {
            return personalTrainerFacadeBean.getPersonalTrainerProfileByClient(usernameAsJSON);
    }

    public void editPersonalTrainertProfile(String infoAsJson) throws TokenIsInvalidException, PersonalTrainerNotExistsException, PersistentException, JsonKeyInFaultException, UserNotExistsException {
            personalTrainerFacadeBean.editPersonalTrainertProfile(infoAsJson);
    }

    public String getPersonalTrainers(String infoAsJson) throws TokenIsInvalidException, PersistentException, JsonKeyInFaultException, ClientNotExistsException, UserNotExistsException {
            return personalTrainerFacadeBean.getPersonalTrainers(infoAsJson);
    }

    public void submitClassification(String infoAsJSON) throws TokenIsInvalidException, PersonalTrainerNotExistsException, PersistentException, JsonKeyInFaultException, ClientNotExistsException, UserNotExistsException {
            personalTrainerFacadeBean.submitClassification(infoAsJSON);
    }

    public String getPersonalTrainerClients(String infoAsJson) throws TokenIsInvalidException, PersonalTrainerNotExistsException, PersistentException, JsonKeyInFaultException, UserNotExistsException {
            return personalTrainerFacadeBean.getPersonalTrainerClients(infoAsJson);
    }

    public void addClientToPersonalTrainer(String infoAsJson) throws ClientAlreadyExistsException, PersonalTrainerNotExistsException, PersistentException, JsonKeyInFaultException, TokenIsInvalidException, UserNotExistsException {
            personalTrainerFacadeBean.addClientToPersonalTrainer(infoAsJson);
    }

    public void updateClientToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, PersistentException, TokenIsInvalidException, UserNotExistsException {
            personalTrainerFacadeBean.updateClientToken(usernameAndTokenAsJson);
    }

    public String getPersonalTrainerProfileByPersonalTrainer(String infoAsJson) throws TokenIsInvalidException, PersonalTrainerNotExistsException, PersistentException, JsonKeyInFaultException, UserNotExistsException {
                return personalTrainerFacadeBean.getPersonalTrainerProfileByPersonalTrainer(infoAsJson);
    }


    public void createClient(String json) throws PersistentException, UserAlreadyExistsException, JsonKeyInFaultException {
            personalTrainerFacadeBean.createClient(json);
    }

    private HRPersonalTrainerFacadeBeanLocal lookupHRPersonalTrainerFacadeBeanLocal() {
        try {
            Context c = new InitialContext();
            return (HRPersonalTrainerFacadeBeanLocal) c.lookup("java:global/PersonalTrainer/HRPersonalTrainerFacadeBean!beans.HRPersonalTrainerFacadeBeanLocal");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
        
}