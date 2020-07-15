/**
 * Licensee: Ricardo Petronilho(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class DeleteDiagramasData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = notifications.DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			notifications.Notification notificationsNotification = notifications.NotificationDAO.loadNotificationByQuery(null, null);
			// Delete the persistent object
			notifications.NotificationDAO.delete(notificationsNotification);
			notifications.Client notificationsClient = notifications.ClientDAO.loadClientByQuery(null, null);
			// Delete the persistent object
			notifications.ClientDAO.delete(notificationsClient);
			notifications.PersonalTrainer notificationsPersonalTrainer = notifications.PersonalTrainerDAO.loadPersonalTrainerByQuery(null, null);
			// Delete the persistent object
			notifications.PersonalTrainerDAO.delete(notificationsPersonalTrainer);
			notifications.User notificationsUser = notifications.UserDAO.loadUserByQuery(null, null);
			// Delete the persistent object
			notifications.UserDAO.delete(notificationsUser);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			DeleteDiagramasData deleteDiagramasData = new DeleteDiagramasData();
			try {
				deleteDiagramasData.deleteTestData();
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
