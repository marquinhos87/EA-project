package hrpersonaltrainer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.orm.PersistentException;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.Date;

@javax.ejb.Stateless(name="HRPersonalTrainerFacadeBean")
@javax.ejb.Remote(HRPersonalTrainerFacadeBean.class)
@javax.ejb.Local(HRPersonalTrainerFacadeBeanLocal.class)
public class HRPersonalTrainerFacadeBeanBean implements HRPersonalTrainerFacadeBean, HRPersonalTrainerFacadeBeanLocal {

	private static final String REDIS_HOST = "localhost";
	private static final int REDIS_PORT = 6379;
	private final Jedis redis;
	private final Gson gson;

	public HRPersonalTrainerFacadeBeanBean() {
		gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.create();
		redis = new Jedis(REDIS_HOST, REDIS_PORT);
	}

	private PersonalTrainerFactory personalTrainerFactory;

	/**
	 * Creates a PersonalTrainer and saves it to database based on the given information.
	 * @param infoPTAsJSON information of PersonalTrainer.
	 */
	public String createPersonalTrainer(String infoPTAsJSON) throws PersistentException, PersonalTrainerAlreadyExistsException, JsonKeyInFaultException {
		Utils.validateJson(gson, infoPTAsJSON, Arrays.asList("name", "username", "email", "password", "birthday", "sex", "skill", "price"));
		PersonalTrainer pt = gson.fromJson(infoPTAsJSON, PersonalTrainer.class);
		String token = Utils.tokenGenerate(pt.getUsername());
		redis.set(pt.getUsername(), token); // creates and saves token on redis
		System.err.println(pt);
		if (PersonalTrainerDAO.getPersonalTrainerByORMID(HRPersonalTrainerFacade.getSession(), pt.getUsername()) != null) throw new PersonalTrainerAlreadyExistsException(pt.getUsername());
		PersonalTrainerDAO.save(pt);
		System.err.println("PersonalTrainer saved to database...");
		return token;
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public String loginPersonalTrainer(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerNotExistsException, InvalidPasswordException {
		JsonObject jsonObject = Utils.validateJson(gson, infoAsJSON, Arrays.asList("username", "password"));
		String username = jsonObject.get("username").getAsString();
		PersonalTrainer pt;
		if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(HRPersonalTrainerFacade.getSession(), username)) == null) throw new PersonalTrainerNotExistsException(username);
		String password = jsonObject.get("password").getAsString();
		if (pt.getPassword().equals(password) == false) throw new InvalidPasswordException(password);
		String oldToken = redis.get(username);
		String newToken = Utils.tokenGenerate(username);
		System.err.println("PersonalTrainer's token updated...");
		return "{ \"oldToken\": \"" + oldToken + "\", " +
				"\"newToken\": \"" + newToken + "\" }";
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public String getPersonalTrainerProfileByClient(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerNotExistsException, TokenIsInvalidException {
		JsonObject jsonObject = Utils.validateJson(gson, infoAsJSON, Arrays.asList("clientToken", "clientUsername", "personalTrainerUsername"));
		String clientToken = jsonObject.get("clientToken").getAsString();
		String clientUsername = jsonObject.get("clientUsername").getAsString();
		//Utils.validateToken(clientToken, clientUsername,"Client");
		String personalTrainerUsername = jsonObject.get("personalTrainerUsername").getAsString();
		PersonalTrainer pt;
		if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(HRPersonalTrainerFacade.getSession(), personalTrainerUsername)) == null) throw new PersonalTrainerNotExistsException(personalTrainerUsername);
		return "{ \"username\": \"" + pt.getUsername() + "\", " +
				"\"name\": \"" + pt.getName() + "\", " +
				"\"age\": " + Utils.years(pt.getBirthday(), new Date()) + ", " +
				"\"sex\": \"" + pt.getSex() + "\", " +
				"\"price\": " + pt.getPrice() + ", " +
				"\"classification\": " + pt.getClassification() + ", " +
				"\"nClassifications\": " + pt.getNumberOfClassifications() + ", " +
				"\"nClients\": " + pt.clients.size() + ", " +
				"\"nPlans\": 0 }"; // TODO acrescentar este atributo derivado ao diagrama de classes
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public void editPersonalTrainertProfile(int usernameAsJSON) {
		// TODO - implement HRPersonalTrainerFacadeBean.editPersonalTrainertProfile
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param filtersAsJSON
	 */
	public String getPersonalTrainers(String filtersAsJSON) {
		// TODO - implement HRPersonalTrainerFacadeBean.getPersonalTrainers
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param usernameAndClassificationAsJSON
	 */
	public String submitClassification(int usernameAndClassificationAsJSON) {
		// TODO - implement HRPersonalTrainerFacadeBean.submitClassification
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getPersonalTrainerClients(int usernameAsJSON) {
		// TODO - implement HRPersonalTrainerFacadeBean.getPersonalTrainerClients
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoAsJson
	 */
	public void createClient(String infoAsJson) throws JsonKeyInFaultException, PersistentException, ClientAlreadyExistsException {
		Utils.validateJson(gson, infoAsJson, Arrays.asList("token", "username"));
		Client client = gson.fromJson(infoAsJson, Client.class);
		System.err.println(client);
		if ( ClientDAO.getClientByORMID(HRPersonalTrainerFacade.getSession(), client.getUsername()) != null) throw new ClientAlreadyExistsException(client.getUsername());
		ClientDAO.save(client);
		System.err.println("Client saved to database...");
	}

}