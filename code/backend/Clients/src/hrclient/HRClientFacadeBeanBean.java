package hrclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
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
	 * @param usernameAsJson
	 */
	public String updateToken(String usernameAsJson) throws PersistentException, ClientDoesNotExistException, TokenAndUsernameInFaultException, TokenIsInvalidException, InvalidJSONException {
		JsonObject json = gson.fromJson(usernameAsJson, JsonObject.class);
		//	TODO a verificação seguinte é um candidato a ser uma função genérica!
		if(!json.has("token") || !json.has("username"))
			throw new TokenAndUsernameInFaultException();
		String token = json.get("token").getAsString();									//	this is actual token to validate authentication
		String username = json.get("username").getAsString();							//	avoid calling gets 3 times

		Client client = null;
		if((client = ClientDAO.getClientByORMID(username)) == null) throw new ClientDoesNotExistException(username);
		System.out.println(client.getToken() + " - " + token + " - " + client.getToken().equals(token));
		if(!client.getToken().equals(token)) throw new TokenIsInvalidException(token);	//	validate authentication
		client.setToken(TokenGenerate.tokenGenerate(username));
		ClientDAO.save(client);
		return client.getToken();														//	to propagation of writes, because, token is generated inside this method
	}

	/**
	 *
	 * @param infoClientAsJSON
	 */
	public String createClient(String infoClientAsJSON) throws ClientAlreadyExistsException, PersistentException {
		Client client = gson.fromJson(infoClientAsJSON, Client.class);
		client.setToken(TokenGenerate.tokenGenerate(client.getUsername()));
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