/**
 * Licensee: Ricardo Petronilho(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class ListDiagramasData {
	private static final int ROW_COUNT = 100;
	
	public void listTestData() throws PersistentException {
		System.out.println("Listing Notification...");
		notifications.Notification[] notificationsNotifications = notifications.NotificationDAO.listNotificationByQuery(null, null);
		int length = Math.min(notificationsNotifications.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(notificationsNotifications[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Client...");
		notifications.Client[] notificationsClients = notifications.ClientDAO.listClientByQuery(null, null);
		length = Math.min(notificationsClients.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(notificationsClients[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing PersonalTrainer...");
		notifications.PersonalTrainer[] notificationsPersonalTrainers = notifications.PersonalTrainerDAO.listPersonalTrainerByQuery(null, null);
		length = Math.min(notificationsPersonalTrainers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(notificationsPersonalTrainers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing User...");
		notifications.User[] notificationsUsers = notifications.UserDAO.listUserByQuery(null, null);
		length = Math.min(notificationsUsers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(notificationsUsers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing Notification by Criteria...");
		notifications.NotificationCriteria notificationsNotificationCriteria = new notifications.NotificationCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//notificationsNotificationCriteria.ID.eq();
		notificationsNotificationCriteria.setMaxResults(ROW_COUNT);
		notifications.Notification[] notificationsNotifications = notificationsNotificationCriteria.listNotification();
		int length =notificationsNotifications== null ? 0 : Math.min(notificationsNotifications.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(notificationsNotifications[i]);
		}
		System.out.println(length + " Notification record(s) retrieved."); 
		
		System.out.println("Listing Client by Criteria...");
		notifications.ClientCriteria notificationsClientCriteria = new notifications.ClientCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//notificationsClientCriteria.username.eq();
		notificationsClientCriteria.setMaxResults(ROW_COUNT);
		notifications.Client[] notificationsClients = notificationsClientCriteria.listClient();
		length =notificationsClients== null ? 0 : Math.min(notificationsClients.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(notificationsClients[i]);
		}
		System.out.println(length + " Client record(s) retrieved."); 
		
		System.out.println("Listing PersonalTrainer by Criteria...");
		notifications.PersonalTrainerCriteria notificationsPersonalTrainerCriteria = new notifications.PersonalTrainerCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//notificationsPersonalTrainerCriteria.username.eq();
		notificationsPersonalTrainerCriteria.setMaxResults(ROW_COUNT);
		notifications.PersonalTrainer[] notificationsPersonalTrainers = notificationsPersonalTrainerCriteria.listPersonalTrainer();
		length =notificationsPersonalTrainers== null ? 0 : Math.min(notificationsPersonalTrainers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(notificationsPersonalTrainers[i]);
		}
		System.out.println(length + " PersonalTrainer record(s) retrieved."); 
		
		System.out.println("Listing User by Criteria...");
		notifications.UserCriteria notificationsUserCriteria = new notifications.UserCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//notificationsUserCriteria.username.eq();
		notificationsUserCriteria.setMaxResults(ROW_COUNT);
		notifications.User[] notificationsUsers = notificationsUserCriteria.listUser();
		length =notificationsUsers== null ? 0 : Math.min(notificationsUsers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(notificationsUsers[i]);
		}
		System.out.println(length + " User record(s) retrieved."); 
		
	}
	
	public static void main(String[] args) {
		try {
			ListDiagramasData listDiagramasData = new ListDiagramasData();
			try {
				listDiagramasData.listTestData();
				//listDiagramasData.listByCriteria();
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
