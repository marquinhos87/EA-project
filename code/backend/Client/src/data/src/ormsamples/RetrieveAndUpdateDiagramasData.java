/**
 * Licensee: jose(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class RetrieveAndUpdateDiagramasData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = hrclient.DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			hrclient.Client hRClientClient = hrclient.ClientDAO.loadClientByQuery(null, null);
			// Update the properties of the persistent object
			hrclient.ClientDAO.save(hRClientClient);
			hrclient.BiometricData hRClientBiometricData = hrclient.BiometricDataDAO.loadBiometricDataByQuery(null, null);
			// Update the properties of the persistent object
			hrclient.BiometricDataDAO.save(hRClientBiometricData);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving Client by ClientCriteria");
		hrclient.ClientCriteria hRClientClientCriteria = new hrclient.ClientCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//hRClientClientCriteria.username.eq();
		System.out.println(hRClientClientCriteria.uniqueClient());
		
		System.out.println("Retrieving BiometricData by BiometricDataCriteria");
		hrclient.BiometricDataCriteria hRClientBiometricDataCriteria = new hrclient.BiometricDataCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//hRClientBiometricDataCriteria.ID.eq();
		System.out.println(hRClientBiometricDataCriteria.uniqueBiometricData());
		
	}
	
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateDiagramasData retrieveAndUpdateDiagramasData = new RetrieveAndUpdateDiagramasData();
			try {
				retrieveAndUpdateDiagramasData.retrieveAndUpdateTestData();
				//retrieveAndUpdateDiagramasData.retrieveByCriteria();
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
