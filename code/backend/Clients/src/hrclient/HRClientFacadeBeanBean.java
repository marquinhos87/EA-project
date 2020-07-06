package hrclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.orm.PersistentException;

@javax.ejb.Stateless(name="HRClientFacadeBean")
@javax.ejb.Remote(HRClientFacadeBean.class)
@javax.ejb.Local(HRClientFacadeBeanLocal.class)
public class HRClientFacadeBeanBean implements HRClientFacadeBean, HRClientFacadeBeanLocal {

	private final Gson gson;

	public HRClientFacadeBeanBean() {
		gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.create();
	}

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	public void updateToken(String usernameAndTokenAsJson) {
		// TODO - implement HRClientFacadeBean.updateToken
		throw new UnsupportedOperationException();
	}

	/**
	 * Create Client.
	 * @param infoClientAsJSON info of client.
	 */
	public String createClient(String infoClientAsJSON) throws ClientAlreadyExistsException, PersistentException {
		Client client = gson.fromJson(infoClientAsJSON, Client.class);
		client.setToken(TokenGenerate.tokenGenerate());
		if(ClientDAO.getClientByORMID(client.getUsername()) != null) throw new ClientAlreadyExistsException(client.getUsername());
		ClientDAO.save(client);
		return client.getToken();
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public String loginClient(String infoAsJSON) {
		// TODO - implement HRClientFacadeBean.loginClient
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getClientProfile(String usernameAsJSON) {
		// TODO - implement HRClientFacadeBean.getClientProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public void editClientProfile(String infoAsJSON) {
		// TODO - implement HRClientFacadeBean.editClientProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getBiometricData(String usernameAsJSON) {
		// TODO - implement HRClientFacadeBean.getBiometricData
		throw new UnsupportedOperationException();
	}

}