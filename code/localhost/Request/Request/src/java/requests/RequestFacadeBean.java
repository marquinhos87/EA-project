/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.util.Arrays;
import javax.ejb.Stateless;
import org.orm.PersistentException;

/**
 *
 * @author josepereira
 */
@Stateless
public class RequestFacadeBean implements RequestFacadeBeanLocal {
    
    private final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .create();

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
    
    /**
     *  Updates user token given the old one.
     * @param usernameAndTokenAsJson Json with username, old token and new token
     * @exception JsonKeyInFaultException Necessary json key/tag in fault
     * @exception TokenIsInvalidException Token does not exist on database
     * @exception UserDoesNotExistException User does not exist in database
     * @exception PersistentException Problem with database session
     */
    @Override
    public void updateUserToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, TokenIsInvalidException, PersistentException, UserDoesNotExistException {
        JsonObject json = Utils.validateJson(gson , usernameAndTokenAsJson , Arrays.asList("username", "oldToken", "newToken"));
        String oldToken = json.get("oldToken").getAsString(), username = json.get("username").getAsString(), newToken = json.get("newToken").getAsString();
        User user = Utils.validateToken(oldToken, username);
        user.setToken(newToken);
        UserDAO.save(user);
    }

    /**
     * Create a Client and User register on database to Client. User with token and Client to relations with Requests.
     * @param usernameAndTokenAsJSon Json with username and token of Client
     * @throws JsonKeyInFaultException Necessary json key/tag in fault
     * @throws PersistentException Problem with database session
     * @throws UserAlreadyExistsException User (token information) has already been registered in the database
     * @throws ClientAlreadyExistsException Client (username) has already been registered in the database
     */
    @Override
    public void createClient(String usernameAndTokenAsJSon) throws JsonKeyInFaultException, PersistentException, UserAlreadyExistsException, ClientAlreadyExistsException {
        JsonObject json = Utils.validateJson(gson , usernameAndTokenAsJSon , Arrays.asList("username", "token"));
        String username = json.get("username").getAsString(), token = json.get("token").getAsString();
        User user;Client client;
        if(Utils.registerExists("username", username, "User") == true) throw new UserAlreadyExistsException(username);
        if(Utils.registerExists("username", username, "Client") == true) throw new ClientAlreadyExistsException(username);
        user = new User();
        user.setUsername(username);
        user.setToken(token);
        UserDAO.save(user);
        client = new Client();
        client.setUsername(username);
        ClientDAO.save(client);
    }

    /**
     * Create a PersonalTrainer and User register on database to Personal Trainer. User with token and PersonalTrainer to relations with Requests.
     * @param usernameAndTokenAsJSon
     * @throws JsonKeyInFaultException Necessary json key/tag in fault
     * @throws PersistentException Problem with database session
     * @throws UserAlreadyExistsException User (token information) has already been registered in the database
     * @throws PersonalTrainerDoesNotExistException PersonalTrainer (username) does not exist in the database
     * @throws PersonalTrainerAlreadyExistsException PersonalTrainer (username) has already been registered in the database
     */
    @Override
    public void createPersonalTrainer(String usernameAndTokenAsJSon) throws JsonKeyInFaultException, PersistentException, UserAlreadyExistsException, PersonalTrainerAlreadyExistsException {
        JsonObject json = Utils.validateJson(gson , usernameAndTokenAsJSon , Arrays.asList("username", "token"));
        String username = json.get("username").getAsString(), token = json.get("token").getAsString();
        User user;PersonalTrainer personalTrainer;
        if(Utils.registerExists("username", username, "User") == true) throw new UserAlreadyExistsException(username);
        if(Utils.registerExists("username", username, "PersonalTrainer") == true) throw new PersonalTrainerAlreadyExistsException(username);
        user = new User();
        user.setUsername(username);
        user.setToken(token);
        UserDAO.save(user);
        personalTrainer = new PersonalTrainer();
        personalTrainer.setUsername(username);
        PersonalTrainerDAO.save(personalTrainer);
    }

    /**
     * Submission of request to personal trainer by client.
     * @param requestInfoAsJSON Json with client token and username (p.e. username="Billie"), personal trainer username (p.e. personalTrainerUsername="John"), number of weeks (p.e. numberOfWeeks=2), objective (p.e. objective=cardio), workout per Week (p.e. workoutPerWeek=3), days in week (p.e. weekDays="3;4;5"), level of exercise (p.e. level=3) and state of request as accepted (p.e. accepted = true).
     * @throws JsonKeyInFaultException Necessary json key/tag in fault
     * @throws TokenIsInvalidException Token does not exist on database
     * @throws UserDoesNotExistException User does not exist in database
     * @throws PersistentException Problem with database session
     * @throws ClientDoesNotExistException Client (username) does not exist in the database
     * @throws PersonalTrainerDoesNotExistException PersonalTrainer (username) does not exist in the database
     */
    @Override
    public void submitRequest(String requestInfoAsJSON) throws JsonKeyInFaultException, TokenIsInvalidException, UserDoesNotExistException, PersistentException, ClientDoesNotExistException, PersonalTrainerDoesNotExistException {
        JsonObject json = Utils.validateJson(gson, requestInfoAsJSON, Arrays.asList("token", "username", "personalTrainerUsername", "numberOfWeeks", "objective", "workoutPerWeek", "weekDays", "level"));
        String token = json.get("token").getAsString(), username = json.get("username").getAsString(), personalTrainer = json.get("personalTrainerUsername").getAsString();
        Utils.validateToken(token, username);
        Client client; PersonalTrainer pt;
        if((client = ClientDAO.getClientByORMID(RequestsFacade.getSession(), username)) == null) throw new ClientDoesNotExistException(username);
        if((pt = PersonalTrainerDAO.getPersonalTrainerByORMID(RequestsFacade.getSession(), personalTrainer)) == null) throw new PersonalTrainerDoesNotExistException(personalTrainer);
        Request request = gson.fromJson(requestInfoAsJSON, Request.class);
        request.setAccepted(false);
        client.requests.add(request);
        pt.requests.add(request);
        ClientDAO.save(client);
        PersonalTrainerDAO.save(pt);
    }

    /**
     * Reply to request, accept or reject (accepted = true or false).
     * @param requestIdAndResponseAsJSON Json with personal trainer username and token, request id and accepted value (p.e true or false)
     * @throws JsonKeyInFaultException Necessary json key/tag in fault
     * @throws PersistentException Problem with database session
     * @throws PersonalTrainerDoesNotExistException PersonalTrainer (username) received  does not exist on the database
     * @throws RequestDoesNotExistException When request id received does not exist on database
     * @throws UserDoesNotExistException Username received does not exist on User table
     * @throws TokenIsInvalidException Token received does not match with username
     */
    @Override
    public void replyToRequest(String requestIdAndResponseAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerDoesNotExistException, RequestDoesNotExistException, UserDoesNotExistException, TokenIsInvalidException {
        JsonObject json = Utils.validateJson(gson, requestIdAndResponseAsJSON, Arrays.asList("username", "token", "requestId", "accepted"));
        String token = json.get("token").getAsString(), username = json.get("username").getAsString();
        Utils.validateToken(token, username);
        int requestId = json.get("requestId").getAsInt();
        if(Utils.registerExists("username", username, "PersonalTrainer") == false) throw new PersonalTrainerDoesNotExistException(username);
        Request request;
        if((request = RequestDAO.getRequestByORMID(RequestsFacade.getSession(), requestId)) == null) throw new RequestDoesNotExistException(String.valueOf(requestId));
        boolean accepted = json.get("accepted").getAsBoolean();
        request.setAccepted(accepted);
        RequestDAO.save(request);
    }

    /**
     * List requests sent to personal trainer.
     * @param usernameAsJSON Json with username and token of Personal Trainer
     * @return return a json with list of requests sent to personal trainer
     * @throws JsonKeyInFaultException Necessary json key/tag in fault
     * @throws TokenIsInvalidException Token received does not match with username
     * @throws UserDoesNotExistException Username received does not exist on User table
     * @throws PersistentException Problem with database session
     * @throws PersonalTrainerDoesNotExistException PersonalTrainer (username) received  does not exist on the database
     */
    @Override
    public String listClientRequestsByPersonalTrainer(String usernameAsJSON) throws JsonKeyInFaultException, TokenIsInvalidException, UserDoesNotExistException, PersistentException, PersonalTrainerDoesNotExistException {
        JsonObject json = Utils.validateJson(gson, usernameAsJSON, Arrays.asList("username", "token"));
        String token = json.get("token").getAsString(), username = json.get("username").getAsString();
        Utils.validateToken(token, username);
        PersonalTrainer personalTrainer;
        if((personalTrainer = PersonalTrainerDAO.getPersonalTrainerByORMID(RequestsFacade.getSession(), username)) == null) throw new PersonalTrainerDoesNotExistException(username);
        return gson.toJson(personalTrainer.requests.toArray());
    }

    /**
     * List requests sented by client.
     * @param usernameAsJSON Json with username and token of Client
     * @return return a json with list of requests sent by client
     * @throws TokenIsInvalidException Token received does not match with username
     * @throws UserDoesNotExistException Username received does not exist on User table
     * @throws PersistentException Problem with database session
     * @throws JsonKeyInFaultException Necessary json key/tag in fault
     * @throws ClientDoesNotExistException Client (username) received  does not exist on the database
     */
    @Override
    public String listClientRequestsByClient(String usernameAsJSON) throws TokenIsInvalidException, UserDoesNotExistException, PersistentException, JsonKeyInFaultException, ClientDoesNotExistException {
        JsonObject json = Utils.validateJson(gson, usernameAsJSON, Arrays.asList("username", "token"));
        String token = json.get("token").getAsString(), username = json.get("username").getAsString();
        Utils.validateToken(token, username);
        Client client;
        if((client = ClientDAO.getClientByORMID(RequestsFacade.getSession(), username)) == null) throw new ClientDoesNotExistException(username);
        return gson.toJson(client.requests.toArray());
    }
}
