/**
 * Licensee: Ricardo Petronilho(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class CreateDiagramasData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = hrpersonaltrainer.DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			hrpersonaltrainer.PersonalTrainer hRPersonalTrainerPersonalTrainer = hrpersonaltrainer.PersonalTrainerDAO.createPersonalTrainer();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : clients, certified, numberOfCreatedPlans, numberOfClients, numberOfClassifications, classification, price, username
			hrpersonaltrainer.PersonalTrainerDAO.save(hRPersonalTrainerPersonalTrainer);
			hrpersonaltrainer.Client hRPersonalTrainerClient = hrpersonaltrainer.ClientDAO.createClient();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : submitedClassification, username
			hrpersonaltrainer.ClientDAO.save(hRPersonalTrainerClient);
			hrpersonaltrainer.User hRPersonalTrainerUser = hrpersonaltrainer.UserDAO.createUser();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : username
			hrpersonaltrainer.UserDAO.save(hRPersonalTrainerUser);
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
				hrpersonaltrainer.DiagramasPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
