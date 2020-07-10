package beans;

import notifications.*;
import org.orm.PersistentException;

@javax.ejb.Local
public interface NotificationFacadeBeanLocal {

	void createNotificationToPersonalTrainer(String infoAsJson) throws JsonKeyInFaultException, TokenIsInvalidException, PersistentException, PersonalTrainerNotExistsException;
        void createNotificationToClient(String infoAsJson) throws JsonKeyInFaultException, TokenIsInvalidException, PersistentException, ClientNotExistsException;

	void markAsReadNotificationsByPersonalTrainer(String infoAsJson) throws JsonKeyInFaultException, PersonalTrainerNotExistsException, PersistentException, TokenIsInvalidException, NotificationNotExistsException;
        void markAsReadNotificationsByClient(String infoAsJson) throws JsonKeyInFaultException, ClientNotExistsException, PersistentException, TokenIsInvalidException, NotificationNotExistsException;


	String getNotificationsByPersonalTrainer(String usernameAndTokenAsJson) throws PersistentException, JsonKeyInFaultException, PersonalTrainerNotExistsException, TokenIsInvalidException;
        
        String getNotificationsByClient(String usernameAndTokenAsJson) throws PersistentException, JsonKeyInFaultException, ClientNotExistsException, TokenIsInvalidException;
        
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
	void updateClientToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, PersistentException, TokenIsInvalidException, UserNotExistsException;
        
        /**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	void updatePersonalTrainerToken(String usernameAndTokenAsJson)  throws JsonKeyInFaultException, PersistentException, TokenIsInvalidException, UserNotExistsException;
}