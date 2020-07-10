/**
 * Licensee: josepereira(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class RetrieveAndUpdateDiagramasData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = hrclient.DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			hrclient.Client lhrclientClient = hrclient.ClientDAO.loadClientByQuery(null, null);
			// Update the properties of the persistent object
			hrclient.ClientDAO.save(lhrclientClient);
			hrclient.BiometricData lhrclientBiometricData = hrclient.BiometricDataDAO.loadBiometricDataByQuery(null, null);
			// Update the properties of the persistent object
			hrclient.BiometricDataDAO.save(lhrclientBiometricData);
			hrclient.User lhrclientUser = hrclient.UserDAO.loadUserByQuery(null, null);
			// Update the properties of the persistent object
			hrclient.UserDAO.save(lhrclientUser);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving Client by ClientCriteria");
		hrclient.ClientCriteria lhrclientClientCriteria = new hrclient.ClientCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lhrclientClientCriteria.username.eq();
		System.out.println(lhrclientClientCriteria.uniqueClient());
		
		System.out.println("Retrieving BiometricData by BiometricDataCriteria");
		hrclient.BiometricDataCriteria lhrclientBiometricDataCriteria = new hrclient.BiometricDataCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lhrclientBiometricDataCriteria.ID.eq();
		System.out.println(lhrclientBiometricDataCriteria.uniqueBiometricData());
		
		System.out.println("Retrieving User by UserCriteria");
		hrclient.UserCriteria lhrclientUserCriteria = new hrclient.UserCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lhrclientUserCriteria.username.eq();
		System.out.println(lhrclientUserCriteria.uniqueUser());
		
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
