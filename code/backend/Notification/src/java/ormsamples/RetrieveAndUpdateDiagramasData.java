/**
 * Licensee: Ricardo Petronilho(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class RetrieveAndUpdateDiagramasData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = notifications.DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			notifications.Notification notificationsNotification = notifications.NotificationDAO.loadNotificationByQuery(null, null);
			// Update the properties of the persistent object
			notifications.NotificationDAO.save(notificationsNotification);
			notifications.Client notificationsClient = notifications.ClientDAO.loadClientByQuery(null, null);
			// Update the properties of the persistent object
			notifications.ClientDAO.save(notificationsClient);
			notifications.PersonalTrainer notificationsPersonalTrainer = notifications.PersonalTrainerDAO.loadPersonalTrainerByQuery(null, null);
			// Update the properties of the persistent object
			notifications.PersonalTrainerDAO.save(notificationsPersonalTrainer);
			notifications.User notificationsUser = notifications.UserDAO.loadUserByQuery(null, null);
			// Update the properties of the persistent object
			notifications.UserDAO.save(notificationsUser);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving Notification by NotificationCriteria");
		notifications.NotificationCriteria notificationsNotificationCriteria = new notifications.NotificationCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//notificationsNotificationCriteria.ID.eq();
		System.out.println(notificationsNotificationCriteria.uniqueNotification());
		
		System.out.println("Retrieving Client by ClientCriteria");
		notifications.ClientCriteria notificationsClientCriteria = new notifications.ClientCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//notificationsClientCriteria.username.eq();
		System.out.println(notificationsClientCriteria.uniqueClient());
		
		System.out.println("Retrieving PersonalTrainer by PersonalTrainerCriteria");
		notifications.PersonalTrainerCriteria notificationsPersonalTrainerCriteria = new notifications.PersonalTrainerCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//notificationsPersonalTrainerCriteria.username.eq();
		System.out.println(notificationsPersonalTrainerCriteria.uniquePersonalTrainer());
		
		System.out.println("Retrieving User by UserCriteria");
		notifications.UserCriteria notificationsUserCriteria = new notifications.UserCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//notificationsUserCriteria.username.eq();
		System.out.println(notificationsUserCriteria.uniqueUser());
		
	}
	
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateDiagramasData retrieveAndUpdateDiagramasData = new RetrieveAndUpdateDiagramasData();
			try {
				retrieveAndUpdateDiagramasData.retrieveAndUpdateTestData();
				//retrieveAndUpdateDiagramasData.retrieveByCriteria();
			}
			finally {
				notifications.DiagramasPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
