package notifications;

import beans.NotificationFacadeBean;
import beans.NotificationFacadeBeanBean;
import beans.NotificationFacadeBeanLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.orm.PersistentException;
import org.orm.PersistentSession;

public class NotificationFacade {

    private static PersistentSession session = null; 
    private static NotificationFacade notificationFacade = null;

    private final NotificationFacadeBeanLocal notificationFacadeBean = lookupNotificationsFacadeBeanLocal();
    
    
    
    private NotificationFacade() {}
    
    public static NotificationFacade getInstance() {
        if (notificationFacade == null) {
            notificationFacade = new NotificationFacade();
        }
        return notificationFacade;
    }

    public static PersistentSession getSession() throws PersistentException {
        if (session == null || session.isConnected() == false) session = DiagramasPersistentManager.instance().getSession();
        return session;
    }

    /**
     * 
     * @param usernameAnddescriptionAsJson
     */
    public void createNotificationToClient(String usernameAnddescriptionAsJson) throws JsonKeyInFaultException, TokenIsInvalidException, PersistentException, ClientNotExistsException, PersonalTrainerNotExistsException, UserNotExistsException {
        notificationFacadeBean.createNotificationToClient(usernameAnddescriptionAsJson);
    }
    
    
    /**
     * 
     * @param usernameAnddescriptionAsJson
     */
    public void createNotificationToPersonalTrainer(String usernameAnddescriptionAsJson) throws JsonKeyInFaultException, TokenIsInvalidException, PersistentException, PersonalTrainerNotExistsException, ClientNotExistsException, UserNotExistsException {
        notificationFacadeBean.createNotificationToPersonalTrainer(usernameAnddescriptionAsJson);
    }
    
    /**
     * 
     * @param usernameAndIdsAsJson
     */
    public void markAsReadNotificationsByPersonalTrainer(String usernameAndIdsAsJson) throws JsonKeyInFaultException, PersonalTrainerNotExistsException, PersistentException, TokenIsInvalidException, NotificationNotExistsException, UserNotExistsException, NotificationDoesNotBelongToUser {
        notificationFacadeBean.markAsReadNotificationsByPersonalTrainer(usernameAndIdsAsJson);
    }

    /**
     * 
     * @param usernameAndIdsAsJson
     */
    public void markAsReadNotificationsByClient(String usernameAndIdsAsJson) throws JsonKeyInFaultException, JsonKeyInFaultException, ClientNotExistsException, PersistentException, TokenIsInvalidException, NotificationNotExistsException, UserNotExistsException, NotificationDoesNotBelongToUser {
        notificationFacadeBean.markAsReadNotificationsByClient(usernameAndIdsAsJson);
    }
    
    /**
     * 
     * @param usernameAsJson
     */
    public String getNotificationsByPersonalTrainer(String usernameAsJson) throws PersistentException, JsonKeyInFaultException, PersonalTrainerNotExistsException, TokenIsInvalidException, UserNotExistsException {
        return notificationFacadeBean.getNotificationsByPersonalTrainer(usernameAsJson);
    }

    /**
     * 
     * @param usernameAsJson
     */
    public String getNotificationsByClient(String usernameAsJson) throws PersistentException, JsonKeyInFaultException, ClientNotExistsException, TokenIsInvalidException, UserNotExistsException {
        return notificationFacadeBean.getNotificationsByClient(usernameAsJson);
    }

    /**
     * 
     * @param usernameAndTokenAsJson
     */
    public void createClient(String usernameAndTokenAsJson) throws JsonKeyInFaultException, JsonKeyInFaultException, PersistentException, UserAlreadyExistsException {
        notificationFacadeBean.createClient(usernameAndTokenAsJson);
    }

    /**
     * 
     * @param usernameAndTokenAsJson
     */
    public void createPersonalTrainer(String usernameAndTokenAsJson) throws JsonKeyInFaultException, PersistentException, UserAlreadyExistsException {
        notificationFacadeBean.createPersonalTrainer(usernameAndTokenAsJson);
    }

    /**
     * 
     * @param usernameAndTokenAsJson
     */
    public void updateClientToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, PersistentException, PersistentException, TokenIsInvalidException, TokenIsInvalidException, UserNotExistsException {
        notificationFacadeBean.updateClientToken(usernameAndTokenAsJson);
    }
    
    
    /**
     * 
     * @param usernameAndTokenAsJson
     */
    public void updatePersonalTrainerToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, PersistentException, TokenIsInvalidException, UserNotExistsException {
        notificationFacadeBean.updatePersonalTrainerToken(usernameAndTokenAsJson);
        
    }

    private NotificationFacadeBeanLocal lookupNotificationsFacadeBeanLocal() {
        try {
            Context c = new InitialContext();
            return (NotificationFacadeBeanLocal) c.lookup("java:global/Notification/NotificationsFacadeBean!beans.NotificationFacadeBeanLocal");
        } catch (NamingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}