package hrclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.orm.PersistentException;
import redis.clients.jedis.Jedis;

import java.util.Arrays;

@javax.ejb.Stateless(name="HRClientFacadeBean")
@javax.ejb.Remote(HRClientFacadeBean.class)
@javax.ejb.Local(HRClientFacadeBeanLocal.class)
public class HRClientFacadeBeanBean implements HRClientFacadeBean, HRClientFacadeBeanLocal {

	private final Gson gson;
	private static final String REDIS_HOST = "localhost";
	private static final int REDIS_PORT = 6379;
	private final Jedis redis;

	public HRClientFacadeBeanBean() {
		gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.create();
		redis = new Jedis(REDIS_HOST, REDIS_PORT);
	}

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	public void updateUserToken(String usernameAndTokenAsJson) {
		// TODO - implement HRClientFacadeBean.updateUserToken
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoClientAsJSON
	 */
	public String createClient(String infoClientAsJSON) throws JsonKeyInFaultException, PersistentException, ClientAlreadyExistsException {
		Utils.validateJson(gson , infoClientAsJSON , Arrays.asList("username", "password", "name", "email", "sex", "birthday", "height", "weight"));
		Client client = gson.fromJson(infoClientAsJSON, Client.class);
		String username = client.getUsername();
		if(ClientDAO.getClientByORMID(HRClientFacade.getSession(),  username) != null) throw new ClientAlreadyExistsException(username);
		BiometricData biometricData = gson.fromJson(infoClientAsJSON, BiometricData.class);
		client.biometricDatas.add(biometricData);
		String token = Utils.tokenGenerate(client.getUsername());
		redis.set(username, token);
		ClientDAO.save(client);
		return token;
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public String loginClient(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, ClientDoesNotExistException {
		JsonObject json = Utils.validateJson(gson, infoAsJSON, Arrays.asList("username", "password"));
		String username = json.get("username").getAsString();
		Client client = null;
		if ( (client = ClientDAO.getClientByORMID(HRClientFacade.getSession(),username)) == null) throw new ClientDoesNotExistException(username);
		String oldToken;
		if(redis.exists(username) == false)
			throw new ClientDoesNotExistException(username + "does not exist on redis database");
		oldToken = redis.get(username);
		String newToken = Utils.tokenGenerate(client.getUsername());
		redis.set(username, newToken);
		ClientDAO.save(client);
		return "{ \"oldToken\": \"" + oldToken + "\", " +
				"\"newToken\": \"" + newToken + "\" }";
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getClientProfileByClient(String usernameAsJSON) {
		// TODO - implement HRClientFacadeBean.getClientProfileByClient
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getClientProfilePersonalTrainer(String usernameAsJSON) {
		// TODO - implement HRClientFacadeBean.getClientProfilePersonalTrainer
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