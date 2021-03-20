/**
 * Licensee: josepereira(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class DeleteDiagramasData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = requests.DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			requests.Client lrequestsClient = requests.ClientDAO.loadClientByQuery(null, null);
			// Delete the persistent object
			requests.ClientDAO.delete(lrequestsClient);
			requests.PersonalTrainer lrequestsPersonalTrainer = requests.PersonalTrainerDAO.loadPersonalTrainerByQuery(null, null);
			// Delete the persistent object
			requests.PersonalTrainerDAO.delete(lrequestsPersonalTrainer);
			requests.Request lrequestsRequest = requests.RequestDAO.loadRequestByQuery(null, null);
			// Delete the persistent object
			requests.RequestDAO.delete(lrequestsRequest);
			requests.User lrequestsUser = requests.UserDAO.loadUserByQuery(null, null);
			// Delete the persistent object
			requests.UserDAO.delete(lrequestsUser);
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
				requests.DiagramasPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
