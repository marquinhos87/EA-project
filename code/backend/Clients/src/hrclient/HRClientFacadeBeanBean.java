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
		Utils.validateJson(gson , infoClientAsJSON ,Arrays.asList("username", "password", "name", "email", "sex", "birthday"));
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
	public String loginClient(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, ClientDoesNotExistException {
		JsonObject json = Utils.validateJson(gson, infoAsJSON, Arrays.asList("username", "password"));
		String username = json.get("username").getAsString();
		Client client = null;
		if ( (client = ClientDAO.getClientByORMID(username)) == null) throw new ClientDoesNotExistException(username);
		String oldToken = client.getToken();
		String newToken = Utils.tokenGenerate(client.getUsername());
		client.setToken(newToken);// updates new token
		ClientDAO.save(client);
		return "{ \"oldToken\": \"" + oldToken + "\", " +
				"\"newToken\": \"" + newToken + "\" }";
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