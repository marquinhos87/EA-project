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
		requests.Client[] requestsClients = requests.ClientDAO.listClientByQuery(null, null);
		int length = Math.min(requestsClients.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(requestsClients[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing PersonalTrainer...");
		requests.PersonalTrainer[] requestsPersonalTrainers = requests.PersonalTrainerDAO.listPersonalTrainerByQuery(null, null);
		length = Math.min(requestsPersonalTrainers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(requestsPersonalTrainers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Request...");
		requests.Request[] requestsRequests = requests.RequestDAO.listRequestByQuery(null, null);
		length = Math.min(requestsRequests.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(requestsRequests[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing User...");
		requests.User[] requestsUsers = requests.UserDAO.listUserByQuery(null, null);
		length = Math.min(requestsUsers.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(requestsUsers[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing Client by Criteria...");
		requests.ClientCriteria lrequestsClientCriteria = new requests.ClientCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lrequestsClientCriteria.username.eq();
		lrequestsClientCriteria.setMaxResults(ROW_COUNT);
		requests.Client[] requestsClients = lrequestsClientCriteria.listClient();
		int length =requestsClients== null ? 0 : Math.min(requestsClients.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(requestsClients[i]);
		}
		System.out.println(length + " Client record(s) retrieved."); 
		
		System.out.println("Listing PersonalTrainer by Criteria...");
		requests.PersonalTrainerCriteria lrequestsPersonalTrainerCriteria = new requests.PersonalTrainerCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lrequestsPersonalTrainerCriteria.username.eq();
		lrequestsPersonalTrainerCriteria.setMaxResults(ROW_COUNT);
		requests.PersonalTrainer[] requestsPersonalTrainers = lrequestsPersonalTrainerCriteria.listPersonalTrainer();
		length =requestsPersonalTrainers== null ? 0 : Math.min(requestsPersonalTrainers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(requestsPersonalTrainers[i]);
		}
		System.out.println(length + " PersonalTrainer record(s) retrieved."); 
		
		System.out.println("Listing Request by Criteria...");
		requests.RequestCriteria lrequestsRequestCriteria = new requests.RequestCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lrequestsRequestCriteria.ID.eq();
		lrequestsRequestCriteria.setMaxResults(ROW_COUNT);
		requests.Request[] requestsRequests = lrequestsRequestCriteria.listRequest();
		length =requestsRequests== null ? 0 : Math.min(requestsRequests.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(requestsRequests[i]);
		}
		System.out.println(length + " Request record(s) retrieved."); 
		
		System.out.println("Listing User by Criteria...");
		requests.UserCriteria lrequestsUserCriteria = new requests.UserCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lrequestsUserCriteria.username.eq();
		lrequestsUserCriteria.setMaxResults(ROW_COUNT);
		requests.User[] requestsUsers = lrequestsUserCriteria.listUser();
		length =requestsUsers== null ? 0 : Math.min(requestsUsers.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(requestsUsers[i]);
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
				requests.DiagramasPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
