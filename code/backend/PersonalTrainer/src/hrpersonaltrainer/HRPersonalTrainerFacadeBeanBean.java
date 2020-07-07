package hrpersonaltrainer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.orm.PersistentException;
import parseJSON.PersonalTrainerSerializer;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;

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
				.registerTypeAdapter(PersonalTrainer.class, new PersonalTrainerSerializer())
				.create();
		redis = new Jedis(REDIS_HOST, REDIS_PORT);
	}

	private PersonalTrainerFactory personalTrainerFactory;

	/**
	 * Creates a PersonalTrainer and saves it to database based on the given information. Returns a generated Token.
	 * @param infoAsJSON PersonalTrainer's information.
	 * @return
	 * @throws PersistentException
	 * @throws PersonalTrainerAlreadyExistsException
	 * @throws JsonKeyInFaultException
	 */
	public String createPersonalTrainer(String infoAsJSON) throws PersistentException, PersonalTrainerAlreadyExistsException, JsonKeyInFaultException {
		Utils.validateJson(gson, infoAsJSON, Arrays.asList("name", "username", "email", "password", "birthday", "sex", "skill", "price"));
		PersonalTrainer pt = gson.fromJson(infoAsJSON, PersonalTrainer.class);
		if (PersonalTrainerDAO.getPersonalTrainerByORMID(HRPersonalTrainerFacade.getSession(), pt.getUsername()) != null) throw new PersonalTrainerAlreadyExistsException(pt.getUsername());
		PersonalTrainerDAO.save(pt);
		String token = Utils.tokenGenerate(pt.getUsername());
		if (redis.exists(pt.getUsername())) throw new PersonalTrainerAlreadyExistsException(pt.getUsername() + " on redis");
		redis.set(pt.getUsername(), token); // creates and saves token on redis
		System.err.println(pt);
		System.err.println("PersonalTrainer saved to database. Token saved to redis...");
		return token;
	}

	/**
	 * Checks if PersonalTrainer's credentials are valid. Returns a generated Token.
	 * @param infoAsJSON username and password as json.
	 * @return
	 * @throws JsonKeyInFaultException
	 * @throws PersistentException
	 * @throws PersonalTrainerNotExistsException
	 * @throws InvalidPasswordException
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
		if (redis.exists(pt.getUsername()) == false) throw new PersonalTrainerNotExistsException(pt.getUsername() + " on redis");
		redis.set(pt.getUsername(), newToken); // creates and saves new token on redis
		System.err.println("PersonalTrainer's token updated...");
		return "{ \"oldToken\": \"" + oldToken + "\", " +
				"\"newToken\": \"" + newToken + "\" }";
	}

	/**
	 * Returns PersonalTrainer's profile. Authenticates client request with given token.
	 * @param infoAsJSON client's token, client's username and personal trainer's username as json.
	 * @return
	 * @throws JsonKeyInFaultException
	 * @throws PersistentException
	 * @throws PersonalTrainerNotExistsException
	 * @throws TokenIsInvalidException
	 * @throws ClientNotExistsException
	 */
	public String getPersonalTrainerProfileByClient(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerNotExistsException, TokenIsInvalidException, ClientNotExistsException {
		JsonObject jsonObject = Utils.validateJson(gson, infoAsJSON, Arrays.asList("clientToken", "clientUsername", "personalTrainerUsername"));
		String clientToken = jsonObject.get("clientToken").getAsString();
		String clientUsername = jsonObject.get("clientUsername").getAsString();
		Utils.validateClientToken(clientToken, clientUsername, redis);
		String personalTrainerUsername = jsonObject.get("personalTrainerUsername").getAsString();
		PersonalTrainer pt;
		if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(HRPersonalTrainerFacade.getSession(), personalTrainerUsername)) == null) throw new PersonalTrainerNotExistsException(personalTrainerUsername);
		return gson.toJson(pt);
	}

	/**
	 *
	 * @param infoAsJSON
	 * @return
	 * @throws JsonKeyInFaultException
	 * @throws PersistentException
	 * @throws PersonalTrainerNotExistsException
	 * @throws TokenIsInvalidException
	 */
	public String getPersonalTrainerProfileByPersonalTrainer(String infoAsJSON) throws PersistentException, JsonKeyInFaultException, PersonalTrainerNotExistsException, TokenIsInvalidException {
		JsonObject jsonObject = Utils.validateJson(gson, infoAsJSON, Arrays.asList("token", "username"));
		String token = jsonObject.get("token").getAsString();
		String username = jsonObject.get("username").getAsString();
		Utils.validatePersonalTrainerToken(token, username, redis);
		PersonalTrainer pt;
		if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(HRPersonalTrainerFacade.getSession(), username)) == null) throw new PersonalTrainerNotExistsException(username);
		return gson.toJson(pt);
	}

	/**
	 *
	 * @param infoAsJSON
	 * @throws JsonKeyInFaultException
	 * @throws PersistentException
	 * @throws PersonalTrainerNotExistsException
	 * @throws TokenIsInvalidException
	 */
	public void editPersonalTrainertProfile(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerNotExistsException, TokenIsInvalidException {
		JsonObject jsonObject = Utils.validateJson(gson, infoAsJSON, Arrays.asList("token", "username"));
		String token = jsonObject.get("token").getAsString();
		String username = jsonObject.get("username").getAsString();
		Utils.validatePersonalTrainerToken(token, username, redis);
		PersonalTrainer editedPt = gson.fromJson(infoAsJSON, PersonalTrainer.class);
		PersonalTrainer databasePt;
		if ( (databasePt = PersonalTrainerDAO.getPersonalTrainerByORMID(HRPersonalTrainerFacade.getSession(), editedPt.getUsername())) == null) throw new PersonalTrainerNotExistsException(editedPt.getUsername());
		System.err.println(editedPt);
		if (editedPt.getName() != null) databasePt.setName(editedPt.getName());
		if (editedPt.getPassword() != null) databasePt.setPassword(editedPt.getPassword());
		if (editedPt.getEmail() != null) databasePt.setEmail(editedPt.getEmail());
		if (editedPt.getSex() != null) databasePt.setSex(editedPt.getSex());
		if (editedPt.getSkill() != null) databasePt.setSkill(editedPt.getSkill());
		if (editedPt.getPrice() != 0) databasePt.setPrice(editedPt.getPrice());
		System.err.println("PersonalTrainer's info updated...");
	}

	/**
	 *
	 * @param infoAsJSON
	 * @return
	 * @throws JsonKeyInFaultException
	 * @throws ClientNotExistsException
	 * @throws TokenIsInvalidException
	 * @throws PersistentException
	 */
	public String getPersonalTrainers(String infoAsJSON) throws JsonKeyInFaultException, ClientNotExistsException, TokenIsInvalidException, PersistentException {
		JsonObject jsonObject = Utils.validateJson(gson, infoAsJSON, Arrays.asList("token", "username"));
		String token = jsonObject.get("token").getAsString();
		String username = jsonObject.get("username").getAsString();
		Utils.validateClientToken(token, username, redis);
		List<PersonalTrainer> personalTrainers = (List<PersonalTrainer>) HRPersonalTrainerFacade.getSession().createQuery("from PersonalTrainer").list();
		return gson.toJson(personalTrainers);
	}

	/**
	 *
	 * @param infoAsJSON
	 * @throws JsonKeyInFaultException
	 * @throws ClientNotExistsException
	 * @throws TokenIsInvalidException
	 * @throws PersistentException
	 * @throws PersonalTrainerNotExistsException
	 */
	public void submitClassification(String infoAsJSON) throws JsonKeyInFaultException, ClientNotExistsException, TokenIsInvalidException, PersistentException, PersonalTrainerNotExistsException {
		JsonObject jsonObject = Utils.validateJson(gson, infoAsJSON, Arrays.asList("clientToken", "clientUsername", "personalTrainerUsername", "classification"));
		String clientToken = jsonObject.get("clientToken").getAsString();
		String clientUsername = jsonObject.get("clientUsername").getAsString();
		Utils.validateClientToken(clientToken, clientUsername, redis);
		String personalTrainerUsername = jsonObject.get("personalTrainerUsername").getAsString();
		PersonalTrainer pt;
		if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(HRPersonalTrainerFacade.getSession(), personalTrainerUsername)) == null) throw new PersonalTrainerNotExistsException(personalTrainerUsername);
		int classification = jsonObject.get("classification").getAsInt();
		pt.setNumberOfClassifications(pt.getNumberOfClassifications() + 1);
		pt.setClassification(pt.getClassification() + classification);
	}

	/**
	 *
	 * @param infoAsJSON
	 * @return
	 * @throws JsonKeyInFaultException
	 * @throws PersonalTrainerNotExistsException
	 * @throws TokenIsInvalidException
	 * @throws PersistentException
	 */
	public String getPersonalTrainerClients(String infoAsJSON) throws JsonKeyInFaultException, PersonalTrainerNotExistsException, TokenIsInvalidException, PersistentException {
		JsonObject jsonObject = Utils.validateJson(gson, infoAsJSON, Arrays.asList("token", "username"));
		String token = jsonObject.get("token").getAsString();
		String username = jsonObject.get("username").getAsString();
		Utils.validatePersonalTrainerToken(token, username, redis);
		PersonalTrainer pt;
		if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(HRPersonalTrainerFacade.getSession(), username)) == null) throw new PersonalTrainerNotExistsException(username);
		return gson.toJson(pt.clients.toArray());
	}

	/**
	 *
	 * @param infoAsJson
	 * @throws JsonKeyInFaultException
	 * @throws PersistentException
	 * @throws ClientAlreadyExistsException
	 * @throws PersonalTrainerNotExistsException
	 * @throws TokenIsInvalidException
	 */
	public void addClientToPersonalTrainer(String infoAsJson) throws JsonKeyInFaultException, PersistentException, ClientAlreadyExistsException, PersonalTrainerNotExistsException, TokenIsInvalidException {
		JsonObject jsonObject = Utils.validateJson(gson, infoAsJson, Arrays.asList("personalTrainerToken", "personalTrainerUsername", "clientUsername"));
		String personalTrainerToken = jsonObject.get("personalTrainerToken").getAsString();
		String personalTrainerUsername = jsonObject.get("personalTrainerUsername").getAsString();
		Utils.validatePersonalTrainerToken(personalTrainerToken, personalTrainerUsername, redis);
		String clientUsername = jsonObject.get("clientUsername").getAsString();
		if ( ClientDAO.getClientByORMID(HRPersonalTrainerFacade.getSession(), clientUsername) != null) throw new ClientAlreadyExistsException(clientUsername);
		Client client = new Client();
		client.setUsername(clientUsername);
		PersonalTrainer pt;
		if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(HRPersonalTrainerFacade.getSession(), personalTrainerUsername)) == null) throw new PersonalTrainerNotExistsException(personalTrainerUsername);
		pt.clients.add(client);
		PersonalTrainerDAO.save(pt);
		System.err.println(client);
		System.err.println("Client saved to database...");
	}


	/**
	 *
	 * @param usernameAndTokenAsJson
	 * @throws JsonKeyInFaultException
	 */
	public void updateClientToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException {
		JsonObject jsonObject = Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("token", "username"));
		String token = jsonObject.get("token").getAsString();
		String username = jsonObject.get("username").getAsString();
		redis.set(username, token); // creates and saves client's token on redis
		System.err.println("Client's token updated...");
	}
}



/*
	String json = "{ \"username\": \"" + pt.getUsername() + "\", " +
			"\"name\": \"" + pt.getName() + "\", " +
			"\"email\": \"" + pt.getEmail() + "\", " +
			"\"age\": " + Utils.years(pt.getBirthday(), new Date()) + ", " +
			"\"sex\": \"" + pt.getSex() + "\", " +
			"\"skill\": " + pt.getSkill()+ ", " +
			"\"price\": " + pt.getPrice() + ", ";
	if (pt.getNumberOfClassifications() == 0) json += "\"classification\": 0, ";
	else json += "\"classification\": " + (pt.getClassification() / (float)pt.getNumberOfClassifications()) + ", ";
	json += "\"nClassifications\": " + pt.getNumberOfClassifications() + ", " +
			"\"nClients\": " + pt.clients.size() + ", " +
			"\"nPlans\": 0 }"; // TODO acrescentar este atributo derivado ao diagrama de classes
	return json;
*/