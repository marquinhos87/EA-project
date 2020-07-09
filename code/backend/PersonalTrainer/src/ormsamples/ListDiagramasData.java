/**
 * Licensee: Ricardo Petronilho(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class ListDiagramasData {
	private static final int ROW_COUNT = 100;
	
	public void listTestData() throws PersistentException {
		System.out.println("Listing PersonalTrainer...");
		hrpersonaltrainer.PersonalTrainer[] hRPersonalTrainerPersonalTrainers = hrpersonaltrainer.PersonalTrainerDAO.listPersonalTrainerByQuery(null, null);
		int length = Math.min(hRPersonalTrainerPersonalTrainers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(hRPersonalTrainerPersonalTrainers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Client...");
		hrpersonaltrainer.Client[] hRPersonalTrainerClients = hrpersonaltrainer.ClientDAO.listClientByQuery(null, null);
		length = Math.min(hRPersonalTrainerClients.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(hRPersonalTrainerClients[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing User...");
		hrpersonaltrainer.User[] hRPersonalTrainerUsers = hrpersonaltrainer.UserDAO.listUserByQuery(null, null);
		length = Math.min(hRPersonalTrainerUsers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(hRPersonalTrainerUsers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing PersonalTrainer by Criteria...");
		hrpersonaltrainer.PersonalTrainerCriteria hRPersonalTrainerPersonalTrainerCriteria = new hrpersonaltrainer.PersonalTrainerCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//hRPersonalTrainerPersonalTrainerCriteria.username.eq();
		hRPersonalTrainerPersonalTrainerCriteria.setMaxResults(ROW_COUNT);
		hrpersonaltrainer.PersonalTrainer[] hRPersonalTrainerPersonalTrainers = hRPersonalTrainerPersonalTrainerCriteria.listPersonalTrainer();
		int length =hRPersonalTrainerPersonalTrainers== null ? 0 : Math.min(hRPersonalTrainerPersonalTrainers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(hRPersonalTrainerPersonalTrainers[i]);
		}
		System.out.println(length + " PersonalTrainer record(s) retrieved."); 
		
		System.out.println("Listing Client by Criteria...");
		hrpersonaltrainer.ClientCriteria hRPersonalTrainerClientCriteria = new hrpersonaltrainer.ClientCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//hRPersonalTrainerClientCriteria.username.eq();
		hRPersonalTrainerClientCriteria.setMaxResults(ROW_COUNT);
		hrpersonaltrainer.Client[] hRPersonalTrainerClients = hRPersonalTrainerClientCriteria.listClient();
		length =hRPersonalTrainerClients== null ? 0 : Math.min(hRPersonalTrainerClients.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(hRPersonalTrainerClients[i]);
		}
		System.out.println(length + " Client record(s) retrieved."); 
		
		System.out.println("Listing User by Criteria...");
		hrpersonaltrainer.UserCriteria hRPersonalTrainerUserCriteria = new hrpersonaltrainer.UserCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//hRPersonalTrainerUserCriteria.username.eq();
		hRPersonalTrainerUserCriteria.setMaxResults(ROW_COUNT);
		hrpersonaltrainer.User[] hRPersonalTrainerUsers = hRPersonalTrainerUserCriteria.listUser();
		length =hRPersonalTrainerUsers== null ? 0 : Math.min(hRPersonalTrainerUsers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(hRPersonalTrainerUsers[i]);
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
				hrpersonaltrainer.DiagramasPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
