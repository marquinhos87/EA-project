package beans;

import notifications.UserAlreadyExistsException;
import notifications.UserNotExistsException;
import notifications.JsonKeyInFaultException;
import notifications.TokenIsInvalidException;
import org.orm.PersistentException;

@javax.ejb.Remote
public interface NotificationFacadeBean {

	/**
	 * 
	 * @param usernameAnddescriptionAsJson
	 */
	void createNotification(String usernameAnddescriptionAsJson);

	/**
	 * 
	 * @param usernameAndIdsAsJson
	 */
	String markAsReadNotifications(String usernameAndIdsAsJson);

	String getNotificationsByPersonalTrainer(String usernameAndTokenAsJson) throws PersistentException, JsonKeyInFaultException;
        
        String getNotificationsByClient(String usernameAndTokenAsJson) throws PersistentException, JsonKeyInFaultException;
        
        /**
         * 
         * @param json
         * @throws JsonKeyInFaultException
         * @throws PersistentException
         * @throws UserAlreadyExistsException 
         */
	void createClient(String json) throws JsonKeyInFaultException, PersistentException, UserAlreadyExistsException;
        
        /**
         * 
         * @param json
         * @throws JsonKeyInFaultException
         * @throws PersistentException
         * @throws UserAlreadyExistsException 
         */
        void createPersonalTrainer(String json) throws JsonKeyInFaultException, PersistentException, UserAlreadyExistsException;
        

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	void updateClientToken(String usernameAndTokenAsJson)  throws JsonKeyInFaultException, PersistentException, TokenIsInvalidException, UserNotExistsException;
        
        /**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	void updatePersonalTrainerToken(String usernameAndTokenAsJson)  throws JsonKeyInFaultException, PersistentException, TokenIsInvalidException, UserNotExistsException;
}