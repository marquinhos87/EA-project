/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import org.hibernate.Query;
import org.orm.PersistentException;
import org.orm.PersistentSession;

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
        PersistentSession session = RequestsFacade.getSession();
        JsonObject json = Utils.validateJson(gson , usernameAndTokenAsJson , Arrays.asList("username", "oldToken", "newToken"));
        String oldToken = json.get("oldToken").getAsString(), username = json.get("username").getAsString(), newToken = json.get("newToken").getAsString();
        User user = Utils.validateToken(oldToken, username, session);
        user.setToken(newToken);
        UserDAO.save(user);
        session.flush();
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
        PersistentSession session = RequestsFacade.getSession();
        JsonObject json = Utils.validateJson(gson , usernameAndTokenAsJSon , Arrays.asList("username", "token"));
        String username = json.get("username").getAsString(), token = json.get("token").getAsString();
        User user;Client client;
        if(Utils.registerExists("username", username, "User", session) == true) throw new UserAlreadyExistsException(username);
        if(Utils.registerExists("username", username, "Client", session) == true) throw new ClientAlreadyExistsException(username);
        user = new User();
        user.setUsername(username);
        user.setToken(token);
        UserDAO.save(user);
        client = new Client();
        client.setUsername(username);
        ClientDAO.save(client);
        session.flush();
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
        PersistentSession session = RequestsFacade.getSession();
        JsonObject json = Utils.validateJson(gson , usernameAndTokenAsJSon , Arrays.asList("username", "token"));
        String username = json.get("username").getAsString(), token = json.get("token").getAsString();
        User user;PersonalTrainer personalTrainer;
        if(Utils.registerExists("username", username, "User", session) == true) throw new UserAlreadyExistsException(username);
        if(Utils.registerExists("username", username, "PersonalTrainer", session) == true) throw new PersonalTrainerAlreadyExistsException(username);
        user = new User();
        user.setUsername(username);
        user.setToken(token);
        UserDAO.save(user);
        personalTrainer = new PersonalTrainer();
        personalTrainer.setUsername(username);
        PersonalTrainerDAO.save(personalTrainer);
        session.flush();
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
        PersistentSession session = RequestsFacade.getSession();
        JsonObject json = Utils.validateJson(gson, requestInfoAsJSON, Arrays.asList("token", "username", "personalTrainerUsername", "numberOfWeeks", "objective", "workoutPerWeek", "weekDays", "level"));
        String token = json.get("token").getAsString(), username = json.get("username").getAsString(), personalTrainer = json.get("personalTrainerUsername").getAsString();
        Utils.validateToken(token, username, session);
        Client client; PersonalTrainer pt;
        if((client = ClientDAO.getClientByORMID(session, username)) == null) throw new ClientDoesNotExistException(username);
        if((pt = PersonalTrainerDAO.getPersonalTrainerByORMID(session, personalTrainer)) == null) throw new PersonalTrainerDoesNotExistException(personalTrainer);
        Request request = gson.fromJson(requestInfoAsJSON, Request.class);
        request.setState(0);
        client.requests.add(request);
        pt.requests.add(request);
        ClientDAO.save(client);
        PersonalTrainerDAO.save(pt);
        session.flush();
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
        PersistentSession session = RequestsFacade.getSession();
        JsonObject json = Utils.validateJson(gson, requestIdAndResponseAsJSON, Arrays.asList("username", "token", "requestId", "state"));
        String token = json.get("token").getAsString(), username = json.get("username").getAsString();
        Utils.validateToken(token, username, session);
        int requestId = json.get("requestId").getAsInt();
        if(Utils.registerExists("username", username, "PersonalTrainer", session) == false) throw new PersonalTrainerDoesNotExistException(username);
        Request request;
        if((request = RequestDAO.getRequestByORMID(session, requestId)) == null) throw new RequestDoesNotExistException(String.valueOf(requestId));
        int state = json.get("state").getAsInt();
        request.setState(state);
        RequestDAO.save(request);
        session.flush();
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
        PersistentSession session = RequestsFacade.getSession();
        JsonObject json = Utils.validateJson(gson, usernameAsJSON, Arrays.asList("username", "token"));
        String token = json.get("token").getAsString(), username = json.get("username").getAsString();
        Utils.validateToken(token, username, session);
        PersonalTrainer personalTrainer;
        if(Utils.registerExists("username", username, "PersonalTrainer", session) == false) throw new PersonalTrainerDoesNotExistException(username);
        Query q = session.createQuery("from Request where PersonalTrainerUsername=\'" + username + "\' and state=0");
        return requestsToJson((List<Request>) q.list());
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
        PersistentSession session = RequestsFacade.getSession();
        JsonObject json = Utils.validateJson(gson, usernameAsJSON, Arrays.asList("username", "token"));
        String token = json.get("token").getAsString(), username = json.get("username").getAsString();
        Utils.validateToken(token, username, session);
        Client client;
        if(Utils.registerExists("username", username, "Client", session) == false) throw new ClientDoesNotExistException(username);
        Query q = session.createQuery("from Request where ClientUsername=\'" + username + "\'");
        return requestsToJson((List<Request>) q.list());
    }
    
    private static String requestsToJson(List<Request> list){
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < list.size(); i++){
            sb.append(list.get(i).toJson());
            if(i != list.size() - 1)
                sb.append(",");
        }
        sb.append("]");
        
        return sb.toString();
    }
}
