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
			hrpersonaltrainer.PersonalTrainer lhrpersonaltrainerPersonalTrainer = hrpersonaltrainer.PersonalTrainerDAO.loadPersonalTrainerByQuery(null, null);
			// Update the properties of the persistent object
			hrpersonaltrainer.PersonalTrainerDAO.save(lhrpersonaltrainerPersonalTrainer);
			hrpersonaltrainer.Client lhrpersonaltrainerClient = hrpersonaltrainer.ClientDAO.loadClientByQuery(null, null);
			// Update the properties of the persistent object
			hrpersonaltrainer.ClientDAO.save(lhrpersonaltrainerClient);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving PersonalTrainer by PersonalTrainerCriteria");
		hrpersonaltrainer.PersonalTrainerCriteria lhrpersonaltrainerPersonalTrainerCriteria = new hrpersonaltrainer.PersonalTrainerCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lhrpersonaltrainerPersonalTrainerCriteria.username.eq();
		System.out.println(lhrpersonaltrainerPersonalTrainerCriteria.uniquePersonalTrainer());
		
		System.out.println("Retrieving Client by ClientCriteria");
		hrpersonaltrainer.ClientCriteria lhrpersonaltrainerClientCriteria = new hrpersonaltrainer.ClientCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lhrpersonaltrainerClientCriteria.username.eq();
		System.out.println(lhrpersonaltrainerClientCriteria.uniqueClient());
		
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
