/**
 * Licensee: josepereira(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class CreateDiagramasData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = requests.DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			requests.Client lrequestsClient = requests.ClientDAO.createClient();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : requests, username
			requests.ClientDAO.save(lrequestsClient);
			requests.PersonalTrainer lrequestsPersonalTrainer = requests.PersonalTrainerDAO.createPersonalTrainer();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : requests, username
			requests.PersonalTrainerDAO.save(lrequestsPersonalTrainer);
			requests.Request lrequestsRequest = requests.RequestDAO.createRequest();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : state, level, workoutPerWeek, numberOfWeeks, client, personalTrainer
			requests.RequestDAO.save(lrequestsRequest);
			requests.User lrequestsUser = requests.UserDAO.createUser();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : username
			requests.UserDAO.save(lrequestsUser);
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
				requests.DiagramasPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
