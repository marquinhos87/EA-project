package notifications;

import beans.NotificationFacadeBean;
import beans.NotificationFacadeBeanBean;
import org.orm.PersistentException;
import org.orm.PersistentSession;

public class NotificationFacade {

    private static PersistentSession session = null; 
    private static NotificationFacade notificationFacade = null;

    private final NotificationFacadeBean notificationFacadeBean = new NotificationFacadeBeanBean();
    
    private NotificationFacade() {}
    
    public static NotificationFacade getInstance() {
        if (notificationFacade == null) {
            notificationFacade = new NotificationFacade();
        }
        return notificationFacade;
    }

    public static PersistentSession getSession() throws PersistentException {
        if (session == null) session = DiagramasPersistentManager.instance().getSession();
        return session;
    }

    /**
     * 
     * @param usernameAnddescriptionAsJson
     */
    public void createNotification(String usernameAnddescriptionAsJson) {

    }

    /**
     * 
     * @param usernameAndIdsAsJson
     */
    public String markAsReadNotifications(String usernameAndIdsAsJson) {
        return null;
    }

    /**
     * 
     * @param usernameAsJson
     */
    public String getNotifications(String usernameAsJson) {
        return null;
    }

    /**
     * 
     * @param usernameAndTokenAsJson
     */
    public void createClient(String usernameAndTokenAsJson) {

    }

    /**
     * 
     * @param usernameAndTokenAsJson
     */
    public void createPersonalTrainer(String usernameAndTokenAsJson) {

    }

    /**
     * 
     * @param usernameAndTokenAsJson
     */
    public void updateToken(String usernameAndTokenAsJson) {

    }

}