/**
 * Licensee: Ricardo Petronilho(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class DeleteDiagramasData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = hrpersonaltrainer.DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			hrpersonaltrainer.PersonalTrainer lhrpersonaltrainerPersonalTrainer = hrpersonaltrainer.PersonalTrainerDAO.loadPersonalTrainerByQuery(null, null);
			// Delete the persistent object
			hrpersonaltrainer.PersonalTrainerDAO.delete(lhrpersonaltrainerPersonalTrainer);
			hrpersonaltrainer.Client lhrpersonaltrainerClient = hrpersonaltrainer.ClientDAO.loadClientByQuery(null, null);
			// Delete the persistent object
			hrpersonaltrainer.ClientDAO.delete(lhrpersonaltrainerClient);
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
				hrpersonaltrainer.DiagramasPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
