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
			hrpersonaltrainer.PersonalTrainer lhrpersonaltrainerPersonalTrainer = hrpersonaltrainer.PersonalTrainerDAO.createPersonalTrainer();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : clients, numberOfCreatedPlans, numberOfClients, numberOfClassifications, classification, price, username
			hrpersonaltrainer.PersonalTrainerDAO.save(lhrpersonaltrainerPersonalTrainer);
			hrpersonaltrainer.Client lhrpersonaltrainerClient = hrpersonaltrainer.ClientDAO.createClient();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : username
			hrpersonaltrainer.ClientDAO.save(lhrpersonaltrainerClient);
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
