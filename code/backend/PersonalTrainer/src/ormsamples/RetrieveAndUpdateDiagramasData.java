/**
 * Licensee: Ricardo Petronilho(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class RetrieveAndUpdateDiagramasData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = hrpersonaltrainer.DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			hrpersonaltrainer.PersonalTrainer hRPersonalTrainerPersonalTrainer = hrpersonaltrainer.PersonalTrainerDAO.loadPersonalTrainerByQuery(null, null);
			// Update the properties of the persistent object
			hrpersonaltrainer.PersonalTrainerDAO.save(hRPersonalTrainerPersonalTrainer);
			hrpersonaltrainer.Client hRPersonalTrainerClient = hrpersonaltrainer.ClientDAO.loadClientByQuery(null, null);
			// Update the properties of the persistent object
			hrpersonaltrainer.ClientDAO.save(hRPersonalTrainerClient);
			hrpersonaltrainer.User hRPersonalTrainerUser = hrpersonaltrainer.UserDAO.loadUserByQuery(null, null);
			// Update the properties of the persistent object
			hrpersonaltrainer.UserDAO.save(hRPersonalTrainerUser);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving PersonalTrainer by PersonalTrainerCriteria");
		hrpersonaltrainer.PersonalTrainerCriteria hRPersonalTrainerPersonalTrainerCriteria = new hrpersonaltrainer.PersonalTrainerCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//hRPersonalTrainerPersonalTrainerCriteria.username.eq();
		System.out.println(hRPersonalTrainerPersonalTrainerCriteria.uniquePersonalTrainer());
		
		System.out.println("Retrieving Client by ClientCriteria");
		hrpersonaltrainer.ClientCriteria hRPersonalTrainerClientCriteria = new hrpersonaltrainer.ClientCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//hRPersonalTrainerClientCriteria.username.eq();
		System.out.println(hRPersonalTrainerClientCriteria.uniqueClient());
		
		System.out.println("Retrieving User by UserCriteria");
		hrpersonaltrainer.UserCriteria hRPersonalTrainerUserCriteria = new hrpersonaltrainer.UserCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//hRPersonalTrainerUserCriteria.username.eq();
		System.out.println(hRPersonalTrainerUserCriteria.uniqueUser());
		
	}
	
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateDiagramasData retrieveAndUpdateDiagramasData = new RetrieveAndUpdateDiagramasData();
			try {
				retrieveAndUpdateDiagramasData.retrieveAndUpdateTestData();
				//retrieveAndUpdateDiagramasData.retrieveByCriteria();
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
