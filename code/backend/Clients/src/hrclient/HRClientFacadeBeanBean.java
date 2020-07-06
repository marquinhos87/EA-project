package hrclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.orm.PersistentException;

import java.util.Arrays;

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
	 * @param infoClientAsJSON
	 */
	public String createClient(String infoClientAsJSON) throws ClientAlreadyExistsException, PersistentException, JsonKeyInFaultException {
		Utils.validateJson(Arrays.asList("username", "password", "name", "email", "sex", "birthday"), gson.fromJson(infoClientAsJSON, JsonObject.class));
		Client client = gson.fromJson(infoClientAsJSON, Client.class);
		String username = client.getUsername();
		if(ClientDAO.getClientByORMID(username) != null) throw new ClientAlreadyExistsException(username);
		client.setToken(Utils.tokenGenerate(client.getUsername()));
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