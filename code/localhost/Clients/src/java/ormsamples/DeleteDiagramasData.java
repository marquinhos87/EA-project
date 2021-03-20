/**
 * Licensee: josepereira(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class DeleteDiagramasData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = hrclient.DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			hrclient.Client lhrclientClient = hrclient.ClientDAO.loadClientByQuery(null, null);
			// Delete the persistent object
			hrclient.ClientDAO.delete(lhrclientClient);
			hrclient.BiometricData lhrclientBiometricData = hrclient.BiometricDataDAO.loadBiometricDataByQuery(null, null);
			// Delete the persistent object
			hrclient.BiometricDataDAO.delete(lhrclientBiometricData);
			hrclient.User lhrclientUser = hrclient.UserDAO.loadUserByQuery(null, null);
			// Delete the persistent object
			hrclient.UserDAO.delete(lhrclientUser);
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
				hrclient.DiagramasPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
