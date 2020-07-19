package beans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import hrpersonaltrainer.*;
import org.orm.PersistentException;
import parseJSON.PersonalTrainerSerializer;

import java.util.Arrays;
import java.util.List;

@javax.ejb.Stateless(name="HRPersonalTrainerFacadeBean")
@javax.ejb.Remote(HRPersonalTrainerFacadeBean.class)
@javax.ejb.Local(HRPersonalTrainerFacadeBeanLocal.class)
public class HRPersonalTrainerFacadeBeanBean implements HRPersonalTrainerFacadeBean, HRPersonalTrainerFacadeBeanLocal {

	private final Gson gson;

	private PersonalTrainerFactory personalTrainerFactory;

	public HRPersonalTrainerFacadeBeanBean() throws PersistentException {
            gson = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd")
                            .registerTypeAdapter(PersonalTrainer.class, new PersonalTrainerSerializer())
                            .create();
	}

	/**
	 * Creates a personal trainer and saves it to database based on the given information. Returns a generated Token.
	 * @param infoAsJSON PersonalTrainer's information.
	 * @return a generated token.
	 * @throws PersistentException if any error related to hibernate occurs.
	 * @throws PersonalTrainerAlreadyExistsException if there is already a personal trainer with specified username in database.
	 * @throws JsonKeyInFaultException if any json key is in fault.
	 */
	public String createPersonalTrainer(String infoAsJSON) throws PersistentException, PersonalTrainerAlreadyExistsException, JsonKeyInFaultException, UserAlreadyExistsException {
            Utils.validateJson(gson, infoAsJSON, Arrays.asList("name", "username", "email", "password", "birthday", "sex", "skill", "price"));
            PersonalTrainer pt = gson.fromJson(infoAsJSON, PersonalTrainer.class);
            if (Utils.exists("username", pt.getUsername(), "PersonalTrainer")) throw new PersonalTrainerAlreadyExistsException(pt.getUsername());
            if (Utils.exists("username", pt.getUsername(), "User")) throw new UserAlreadyExistsException(pt.getUsername());
            PersonalTrainerDAO.save(pt); // saves PersonalTrainer
            String token = Utils.tokenGenerate(pt.getUsername());
            User user = new User();
            user.setUsername(pt.getUsername());
            user.setToken(token);
            UserDAO.save(user); // saves token information
            System.err.println("PersonalTrainer and token saved to database...");
            return "{ \"token\": \"" + token + "\" }";
	}

	/**
	 * Checks if personal trainer's credentials are valid. Returns the old and a new generated Token.
	 * @param infoAsJSON username and password as json.
	 * @return the old and new token.
	 * @throws JsonKeyInFaultException if any json key is in fault.
	 * @throws PersistentException if any error related to hibernate occurs.
	 * @throws PersonalTrainerNotExistsException if there isn't any personal trainer with specified username in database.
	 * @throws InvalidPasswordException if password is invalid.
	 */
	public String loginPersonalTrainer(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerNotExistsException, InvalidPasswordException, UserNotExistsException {
            JsonObject jsonObject = Utils.validateJson(gson, infoAsJSON, Arrays.asList("username", "password"));
            String username = jsonObject.get("username").getAsString();
            PersonalTrainer pt;
            if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(HRPersonalTrainerFacade.getSession(), username)) == null) throw new PersonalTrainerNotExistsException(username);
            String password = jsonObject.get("password").getAsString();
            if (pt.getPassword().equals(password) == false) throw new InvalidPasswordException(password);
            User user;
            if ((user = UserDAO.getUserByORMID(HRPersonalTrainerFacade.getSession(), pt.getUsername()) ) == null) throw new UserNotExistsException(pt.getUsername());
            String oldToken = user.getToken();
            String newToken = Utils.tokenGenerate(username);
            user.setToken(newToken); // creates new token
            UserDAO.save(user); // saves new token
            System.err.println("PersonalTrainer logged in and token updated...");
            return "{ \"oldToken\": \"" + oldToken + "\", " +
                    "\"newToken\": \"" + newToken + "\" }";
	}

	/**
	 * Returns personal trainer's profile. Authenticates client's request with the given token.
	 * @param infoAsJSON client's token, client's username and personal trainer's username as json.
	 * @return personal trainer's profile.
	 * @throws JsonKeyInFaultException if any json key is in fault.
	 * @throws PersistentException if any error related to hibernate occurs.
	 * @throws PersonalTrainerNotExistsException if there isn't any personal trainer with specified username in database.
	 * @throws TokenIsInvalidException if client's token is invalid.
	 * @throws ClientNotExistsException if there isn't any client with specified username in redis.
	 */
	public String getPersonalTrainerProfileByClient(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerNotExistsException, TokenIsInvalidException, ClientNotExistsException, UserNotExistsException {
            JsonObject jsonObject = Utils.validateJson(gson, infoAsJSON, Arrays.asList("token", "username", "personalTrainerUsername"));
            String clientToken = jsonObject.get("token").getAsString();
            String clientUsername = jsonObject.get("username").getAsString();
            Utils.validateToken(clientToken, clientUsername);
            String personalTrainerUsername = jsonObject.get("personalTrainerUsername").getAsString();
            PersonalTrainer pt;
            if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(HRPersonalTrainerFacade.getSession(), personalTrainerUsername)) == null) throw new PersonalTrainerNotExistsException(personalTrainerUsername);
            return gson.toJson(pt);
	}

	/** Returns personal trainer's profile. Authenticates personal trainer's request with the given token.
	 * @param infoAsJSON personal trainer's token and username as json.
	 * @return personal trainer's profile.
	 * @throws JsonKeyInFaultException if any json key is in fault.
	 * @throws PersistentException if any error related to hibernate occurs.
	 * @throws PersonalTrainerNotExistsException if there isn't any personal trainer with specified username in database or redis.
	 * @throws TokenIsInvalidException if personal trainer's token is invalid.
	 */
	public String getPersonalTrainerProfileByPersonalTrainer(String infoAsJSON) throws PersistentException, JsonKeyInFaultException, PersonalTrainerNotExistsException, TokenIsInvalidException, UserNotExistsException {
		JsonObject jsonObject = Utils.validateJson(gson, infoAsJSON, Arrays.asList("token", "username"));
		String token = jsonObject.get("token").getAsString();
		String username = jsonObject.get("username").getAsString();
		Utils.validateToken(token, username);
		PersonalTrainer pt;
		if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(HRPersonalTrainerFacade.getSession(), username)) == null) throw new PersonalTrainerNotExistsException(username);
		return gson.toJson(pt);
	}

	/**
	 * Edits personal trainer's profile. Authenticates personal trainer's request with the given token.
	 * @param infoAsJSON personal trainer's token and username as json.
	 * @throws JsonKeyInFaultException if any json key is in fault.
	 * @throws PersistentException if any error related to hibernate occurs.
	 * @throws PersonalTrainerNotExistsException if there isn't any personal trainer with specified username in database or redis.
	 * @throws TokenIsInvalidException if personal trainer's token is invalid.
	 */
	public void editPersonalTrainertProfile(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerNotExistsException, TokenIsInvalidException, UserNotExistsException {
		JsonObject jsonObject = Utils.validateJson(gson, infoAsJSON, Arrays.asList("token", "username"));
		String token = jsonObject.get("token").getAsString();
		String username = jsonObject.get("username").getAsString();
		Utils.validateToken(token, username);
		PersonalTrainer editedPt = gson.fromJson(infoAsJSON, PersonalTrainer.class);
		PersonalTrainer databasePt;
		if ( (databasePt = PersonalTrainerDAO.getPersonalTrainerByORMID(HRPersonalTrainerFacade.getSession(), editedPt.getUsername())) == null) throw new PersonalTrainerNotExistsException(editedPt.getUsername());
		if (editedPt.getName() != null) databasePt.setName(editedPt.getName());
		if (editedPt.getPassword() != null) databasePt.setPassword(editedPt.getPassword());
		if (editedPt.getEmail() != null) databasePt.setEmail(editedPt.getEmail());
		if (editedPt.getSex() != null) databasePt.setSex(editedPt.getSex());
		if (editedPt.getSkill() != null) databasePt.setSkill(editedPt.getSkill());
		if (editedPt.getPrice() != 0) databasePt.setPrice(editedPt.getPrice());
                if (editedPt.getBirthday() != null) databasePt.setBirthday(editedPt.getBirthday());
		PersonalTrainerDAO.save(databasePt);
		System.err.println("PersonalTrainer's info updated...");
	}

	/**
	 * Returns a list of personal trainers based on the given filters. Authenticates client's request with the given token.
	 * @param infoAsJSON client's token and username as json.
	 * @return list of personal trainers.
	 * @throws JsonKeyInFaultException if any json key is in fault.
	 * @throws ClientNotExistsException if there isn't any client with specified username in redis.
	 * @throws TokenIsInvalidException if personal trainer's token is invalid.
	 * @throws PersistentException if any error related to hibernate occurs.
	 */
	public String getPersonalTrainers(String infoAsJSON) throws JsonKeyInFaultException, ClientNotExistsException, TokenIsInvalidException, PersistentException, UserNotExistsException {
		JsonObject jsonObject = Utils.validateJson(gson, infoAsJSON, Arrays.asList("token", "username"));
		String token = jsonObject.get("token").getAsString();
		String username = jsonObject.get("username").getAsString();
		Utils.validateToken(token, username);
		List<PersonalTrainer> personalTrainers = (List<PersonalTrainer>) HRPersonalTrainerFacade.getSession().createQuery("from PersonalTrainer").list();
		return gson.toJson(personalTrainers);
	}

	/**
	 * Associates client's classification to a personal trainer. Authenticates client's request with the given token.
	 * @param infoAsJSON client's token, username and classification and personal trainer's username as json.
	 * @throws JsonKeyInFaultException if any json key is in fault.
	 * @throws ClientNotExistsException if there isn't any client with specified username in redis.
	 * @throws TokenIsInvalidException if personal trainer's token is invalid.
	 * @throws PersistentException if any error related to hibernate occurs.
	 * @throws PersonalTrainerNotExistsException if there isn't any personal trainer with specified username in database.
	 */
	public void submitClassification(String infoAsJSON) throws JsonKeyInFaultException, ClientNotExistsException, TokenIsInvalidException, PersistentException, PersonalTrainerNotExistsException, UserNotExistsException {
            JsonObject jsonObject = Utils.validateJson(gson, infoAsJSON, Arrays.asList("token", "username", "personalTrainerUsername", "classification"));
            String clientToken = jsonObject.get("token").getAsString();
            String clientUsername = jsonObject.get("username").getAsString();
            Utils.validateToken(clientToken, clientUsername);
            Client client;
            if ( (client = ClientDAO.getClientByORMID(HRPersonalTrainerFacade.getSession(), clientUsername)) == null) throw new ClientNotExistsException(clientUsername);
            client.setSubmitedClassification(true);
            ClientDAO.save(client);
            String personalTrainerUsername = jsonObject.get("personalTrainerUsername").getAsString();
            PersonalTrainer pt;
            if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(HRPersonalTrainerFacade.getSession(), personalTrainerUsername)) == null) throw new PersonalTrainerNotExistsException(personalTrainerUsername);
            int classification = jsonObject.get("classification").getAsInt();
            pt.setNumberOfClassifications(pt.getNumberOfClassifications() + 1);
            pt.setClassification(pt.getClassification() + classification);
            PersonalTrainerDAO.save(pt);
	}
        
         
	public String hasSubmittedClassification(String infoAsJSON) throws JsonKeyInFaultException, ClientNotExistsException, TokenIsInvalidException, PersistentException, UserNotExistsException {
            JsonObject jsonObject = Utils.validateJson(gson, infoAsJSON, Arrays.asList("token", "username"));
            String clientToken = jsonObject.get("token").getAsString();
            String clientUsername = jsonObject.get("username").getAsString();
            Utils.validateToken(clientToken, clientUsername);
            Client client;
            if ( (client = ClientDAO.getClientByORMID(HRPersonalTrainerFacade.getSession(), clientUsername)) == null) throw new ClientNotExistsException(clientUsername);
            boolean hasSubmittedClassification = client.getSubmitedClassification();
            return "{ \"hasSubmittedClassification\": " + hasSubmittedClassification + " }";
	}
        

	/**
	 * Returns personal trainer's clients. Authenticates personal trainer's request with the given token.
	 * @param infoAsJSON personal trainer's token and username.
	 * @return personal trainer's clients.
	 * @throws JsonKeyInFaultException if any json key is in fault.
	 * @throws PersonalTrainerNotExistsException if there isn't any personal trainer with specified username in database or redis.
	 * @throws TokenIsInvalidException if personal trainer's token is invalid.
	 * @throws PersistentException if any error related to hibernate occurs.
	 */
	public String getPersonalTrainerClients(String infoAsJSON) throws JsonKeyInFaultException, PersonalTrainerNotExistsException, TokenIsInvalidException, PersistentException, UserNotExistsException {
		JsonObject jsonObject = Utils.validateJson(gson, infoAsJSON, Arrays.asList("token", "username"));
		String token = jsonObject.get("token").getAsString();
		String username = jsonObject.get("username").getAsString();
		Utils.validateToken(token, username);
		PersonalTrainer pt;
		if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(HRPersonalTrainerFacade.getSession(), username)) == null) throw new PersonalTrainerNotExistsException(username);
		return gson.toJson(pt.clients.toArray());
	}

	/**
	 * Associates a client to a personal trainer. Authenticates personal trainer's request with the given token.
	 * @param infoAsJson personal trainer's token and username and client's username as json.
	 * @throws JsonKeyInFaultException if any json key is in fault.
	 * @throws PersistentException if any error related to hibernate occurs.
	 * @throws ClientAlreadyExistsException if there is already a client with specified username in redis.
	 * @throws PersonalTrainerNotExistsException if there isn't any personal trainer with specified username in database or redis.
	 * @throws TokenIsInvalidException if personal trainer's token is invalid.
	 */
	public void addClientToPersonalTrainer(String infoAsJson) throws JsonKeyInFaultException, PersistentException, ClientAlreadyExistsException, PersonalTrainerNotExistsException, TokenIsInvalidException, UserNotExistsException {
            JsonObject jsonObject = Utils.validateJson(gson, infoAsJson, Arrays.asList("token", "username", "clientUsername"));
            String personalTrainerToken = jsonObject.get("token").getAsString();
            String personalTrainerUsername = jsonObject.get("username").getAsString();
            Utils.validateToken(personalTrainerToken, personalTrainerUsername);
            String clientUsername = jsonObject.get("clientUsername").getAsString();
            if ( ClientDAO.getClientByORMID(HRPersonalTrainerFacade.getSession(), clientUsername) != null) {
                throw new ClientAlreadyExistsException(clientUsername);
            }
            Client client = new Client();
            client.setUsername(clientUsername);
            PersonalTrainer pt;
            if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(HRPersonalTrainerFacade.getSession(), personalTrainerUsername)) == null) throw new PersonalTrainerNotExistsException(personalTrainerUsername);
            pt.clients.add(client);
            pt.setNumberOfCreatedPlans(pt.getNumberOfCreatedPlans()+1);
            pt.setNumberOfClients(pt.getNumberOfClients()+1);
            PersonalTrainerDAO.save(pt);
            System.err.println(client);
            System.err.println("Client saved to database...");
	}

	/**
	 *
	 * @param usernameAndTokenAsJson
	 * @throws JsonKeyInFaultException
	 * @throws PersistentException
	 * @throws UserAlreadyExistsException
	 */
	public void createClient(String usernameAndTokenAsJson) throws JsonKeyInFaultException, PersistentException, UserAlreadyExistsException  {
            Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("token", "username"));
            User user = gson.fromJson(usernameAndTokenAsJson, User.class);
            if (Utils.exists("username", user.getUsername(), "User")) throw new UserAlreadyExistsException(user.getUsername());
            UserDAO.save(user);
            System.err.println("Client created...");
	}

	/**
	 * Updates client's token.
	 * @param usernameAndTokenAsJson client's username, old token and current token as json.
	 * @throws JsonKeyInFaultException if any json key is in fault.
	 */
	public void updateClientToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, PersistentException, TokenIsInvalidException, UserNotExistsException, UserNotExistsException {
            JsonObject jsonObject = Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("oldToken", "newToken", "username"));
            String oldToken = jsonObject.get("oldToken").getAsString();
            String username = jsonObject.get("username").getAsString();
            Utils.validateToken(oldToken, username);
            User user = UserDAO.getUserByORMID(HRPersonalTrainerFacade.getSession(), username);
            String currentToken = jsonObject.get("newToken").getAsString();
            user.setToken(currentToken);
            UserDAO.save(user);
            System.err.println("Client's token updated...");
	}
        
}