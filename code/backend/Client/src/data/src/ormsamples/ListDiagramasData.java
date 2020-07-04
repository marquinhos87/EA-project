/**
 * Licensee: jose(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class ListDiagramasData {
	private static final int ROW_COUNT = 100;
	
	public void listTestData() throws PersistentException {
		System.out.println("Listing Client...");
		hrclient.Client[] hRClientClients = hrclient.ClientDAO.listClientByQuery(null, null);
		int length = Math.min(hRClientClients.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(hRClientClients[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing BiometricData...");
		hrclient.BiometricData[] hRClientBiometricDatas = hrclient.BiometricDataDAO.listBiometricDataByQuery(null, null);
		length = Math.min(hRClientBiometricDatas.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(hRClientBiometricDatas[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing Client by Criteria...");
		hrclient.ClientCriteria hRClientClientCriteria = new hrclient.ClientCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//hRClientClientCriteria.username.eq();
		hRClientClientCriteria.setMaxResults(ROW_COUNT);
		hrclient.Client[] hRClientClients = hRClientClientCriteria.listClient();
		int length =hRClientClients== null ? 0 : Math.min(hRClientClients.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(hRClientClients[i]);
		}
		System.out.println(length + " Client record(s) retrieved."); 
		
		System.out.println("Listing BiometricData by Criteria...");
		hrclient.BiometricDataCriteria hRClientBiometricDataCriteria = new hrclient.BiometricDataCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//hRClientBiometricDataCriteria.ID.eq();
		hRClientBiometricDataCriteria.setMaxResults(ROW_COUNT);
		hrclient.BiometricData[] hRClientBiometricDatas = hRClientBiometricDataCriteria.listBiometricData();
		length =hRClientBiometricDatas== null ? 0 : Math.min(hRClientBiometricDatas.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(hRClientBiometricDatas[i]);
		}
		System.out.println(length + " BiometricData record(s) retrieved."); 
		
	}
	
	public static void main(String[] args) {
		try {
			ListDiagramasData listDiagramasData = new ListDiagramasData();
			try {
				listDiagramasData.listTestData();
				//listDiagramasData.listByCriteria();
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
