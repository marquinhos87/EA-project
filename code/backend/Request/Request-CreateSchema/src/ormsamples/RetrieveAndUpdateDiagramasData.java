/**
 * Licensee: josepereira(Universidade do Minho)
 * License Type: Academic
 */
package ormsamples;

import org.orm.*;
public class RetrieveAndUpdateDiagramasData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = requests.DiagramasPersistentManager.instance().getSession().beginTransaction();
		try {
			requests.Client lrequestsClient = requests.ClientDAO.loadClientByQuery(null, null);
			// Update the properties of the persistent object
			requests.ClientDAO.save(lrequestsClient);
			requests.PersonalTrainer lrequestsPersonalTrainer = requests.PersonalTrainerDAO.loadPersonalTrainerByQuery(null, null);
			// Update the properties of the persistent object
			requests.PersonalTrainerDAO.save(lrequestsPersonalTrainer);
			requests.Request lrequestsRequest = requests.RequestDAO.loadRequestByQuery(null, null);
			// Update the properties of the persistent object
			requests.RequestDAO.save(lrequestsRequest);
			requests.User lrequestsUser = requests.UserDAO.loadUserByQuery(null, null);
			// Update the properties of the persistent object
			requests.UserDAO.save(lrequestsUser);
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving Client by ClientCriteria");
		requests.ClientCriteria lrequestsClientCriteria = new requests.ClientCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lrequestsClientCriteria.username.eq();
		System.out.println(lrequestsClientCriteria.uniqueClient());
		
		System.out.println("Retrieving PersonalTrainer by PersonalTrainerCriteria");
		requests.PersonalTrainerCriteria lrequestsPersonalTrainerCriteria = new requests.PersonalTrainerCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lrequestsPersonalTrainerCriteria.username.eq();
		System.out.println(lrequestsPersonalTrainerCriteria.uniquePersonalTrainer());
		
		System.out.println("Retrieving Request by RequestCriteria");
		requests.RequestCriteria lrequestsRequestCriteria = new requests.RequestCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lrequestsRequestCriteria.ID.eq();
		System.out.println(lrequestsRequestCriteria.uniqueRequest());
		
		System.out.println("Retrieving User by UserCriteria");
		requests.UserCriteria lrequestsUserCriteria = new requests.UserCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lrequestsUserCriteria.username.eq();
		System.out.println(lrequestsUserCriteria.uniqueUser());
		
	}
	
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateDiagramasData retrieveAndUpdateDiagramasData = new RetrieveAndUpdateDiagramasData();
			try {
				retrieveAndUpdateDiagramasData.retrieveAndUpdateTestData();
				//retrieveAndUpdateDiagramasData.retrieveByCriteria();
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
