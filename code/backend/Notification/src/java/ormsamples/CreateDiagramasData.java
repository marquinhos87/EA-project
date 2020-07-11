/**
 * Licensee: Ricardo Petronilho(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class CreateDiagramasData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = notifications.DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			notifications.Notification notificationsNotification = notifications.NotificationDAO.createNotification();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : read
			notifications.NotificationDAO.save(notificationsNotification);
			notifications.Client notificationsClient = notifications.ClientDAO.createClient();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : username
			notifications.ClientDAO.save(notificationsClient);
			notifications.PersonalTrainer notificationsPersonalTrainer = notifications.PersonalTrainerDAO.createPersonalTrainer();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : username
			notifications.PersonalTrainerDAO.save(notificationsPersonalTrainer);
			notifications.User notificationsUser = notifications.UserDAO.createUser();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : username
			notifications.UserDAO.save(notificationsUser);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CreateDiagramasData createDiagramasData = new CreateDiagramasData();
			try {
				createDiagramasData.createTestData();
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
