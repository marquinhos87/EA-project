/**
 * Licensee: jose(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class CreateDiagramasData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = hrclient.DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			hrclient.Client hRClientClient = hrclient.ClientDAO.createClient();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : biometricDatas, username
			hrclient.ClientDAO.save(hRClientClient);
			hrclient.BiometricData hRClientBiometricData = hrclient.BiometricDataDAO.createBiometricData();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : BMI, twin, quadricep, waist, tricep, chest, wrist, weight, height
			hrclient.BiometricDataDAO.save(hRClientBiometricData);
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
				hrclient.DiagramasPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
