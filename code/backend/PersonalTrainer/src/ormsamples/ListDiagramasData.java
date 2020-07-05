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
		hrpersonaltrainer.PersonalTrainer[] hrpersonaltrainerPersonalTrainers = hrpersonaltrainer.PersonalTrainerDAO.listPersonalTrainerByQuery(null, null);
		int length = Math.min(hrpersonaltrainerPersonalTrainers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(hrpersonaltrainerPersonalTrainers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Client...");
		hrpersonaltrainer.Client[] hrpersonaltrainerClients = hrpersonaltrainer.ClientDAO.listClientByQuery(null, null);
		length = Math.min(hrpersonaltrainerClients.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(hrpersonaltrainerClients[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing PersonalTrainer by Criteria...");
		hrpersonaltrainer.PersonalTrainerCriteria lhrpersonaltrainerPersonalTrainerCriteria = new hrpersonaltrainer.PersonalTrainerCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lhrpersonaltrainerPersonalTrainerCriteria.username.eq();
		lhrpersonaltrainerPersonalTrainerCriteria.setMaxResults(ROW_COUNT);
		hrpersonaltrainer.PersonalTrainer[] hrpersonaltrainerPersonalTrainers = lhrpersonaltrainerPersonalTrainerCriteria.listPersonalTrainer();
		int length =hrpersonaltrainerPersonalTrainers== null ? 0 : Math.min(hrpersonaltrainerPersonalTrainers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(hrpersonaltrainerPersonalTrainers[i]);
		}
		System.out.println(length + " PersonalTrainer record(s) retrieved."); 
		
		System.out.println("Listing Client by Criteria...");
		hrpersonaltrainer.ClientCriteria lhrpersonaltrainerClientCriteria = new hrpersonaltrainer.ClientCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lhrpersonaltrainerClientCriteria.username.eq();
		lhrpersonaltrainerClientCriteria.setMaxResults(ROW_COUNT);
		hrpersonaltrainer.Client[] hrpersonaltrainerClients = lhrpersonaltrainerClientCriteria.listClient();
		length =hrpersonaltrainerClients== null ? 0 : Math.min(hrpersonaltrainerClients.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(hrpersonaltrainerClients[i]);
		}
		System.out.println(length + " Client record(s) retrieved."); 
		
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
