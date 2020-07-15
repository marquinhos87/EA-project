/**
 * Licensee: josepereira(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class ListDiagramasData {
	private static final int ROW_COUNT = 100;
	
	public void listTestData() throws PersistentException {
		System.out.println("Listing Client...");
		hrclient.Client[] hrclientClients = hrclient.ClientDAO.listClientByQuery(null, null);
		int length = Math.min(hrclientClients.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(hrclientClients[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing BiometricData...");
		hrclient.BiometricData[] hrclientBiometricDatas = hrclient.BiometricDataDAO.listBiometricDataByQuery(null, null);
		length = Math.min(hrclientBiometricDatas.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(hrclientBiometricDatas[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing User...");
		hrclient.User[] hrclientUsers = hrclient.UserDAO.listUserByQuery(null, null);
		length = Math.min(hrclientUsers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(hrclientUsers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing Client by Criteria...");
		hrclient.ClientCriteria lhrclientClientCriteria = new hrclient.ClientCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lhrclientClientCriteria.username.eq();
		lhrclientClientCriteria.setMaxResults(ROW_COUNT);
		hrclient.Client[] hrclientClients = lhrclientClientCriteria.listClient();
		int length =hrclientClients== null ? 0 : Math.min(hrclientClients.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(hrclientClients[i]);
		}
		System.out.println(length + " Client record(s) retrieved."); 
		
		System.out.println("Listing BiometricData by Criteria...");
		hrclient.BiometricDataCriteria lhrclientBiometricDataCriteria = new hrclient.BiometricDataCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lhrclientBiometricDataCriteria.ID.eq();
		lhrclientBiometricDataCriteria.setMaxResults(ROW_COUNT);
		hrclient.BiometricData[] hrclientBiometricDatas = lhrclientBiometricDataCriteria.listBiometricData();
		length =hrclientBiometricDatas== null ? 0 : Math.min(hrclientBiometricDatas.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(hrclientBiometricDatas[i]);
		}
		System.out.println(length + " BiometricData record(s) retrieved."); 
		
		System.out.println("Listing User by Criteria...");
		hrclient.UserCriteria lhrclientUserCriteria = new hrclient.UserCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lhrclientUserCriteria.username.eq();
		lhrclientUserCriteria.setMaxResults(ROW_COUNT);
		hrclient.User[] hrclientUsers = lhrclientUserCriteria.listUser();
		length =hrclientUsers== null ? 0 : Math.min(hrclientUsers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(hrclientUsers[i]);
		}
		System.out.println(length + " User record(s) retrieved."); 
		
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
