/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import javax.ejb.Stateless;
import org.hibernate.Query;
import org.orm.PersistentException;
import org.orm.PersistentSession;

/**
 *
 * @author josepereira
 */
@Stateless
public class HRClientFacadeBean implements HRClientFacadeBeanLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    private final Gson gson = new GsonBuilder()
                            .setDateFormat("yyyy-MM-dd")
                            .registerTypeAdapter(Client.class, new InfoClient())
                            .create();
    
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    /**
     * Update token of other entities associated with Client, at the moment only Personal Trainer.
     * @param usernameAndTokenAsJson json with username, old token and new token of personal trainer
     */
    public void updateUserToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, TokenIsInvalidException, UserDoesNotExistException, PersistentException{
        PersistentSession session = HRClientFacade.getSession();
        JsonObject json = Utils.validateJson(gson , usernameAndTokenAsJson , Arrays.asList("username", "oldToken", "newToken"));
        String oldToken = json.get("oldToken").getAsString(), username = json.get("username").getAsString(), newToken = json.get("newToken").getAsString();
        User user = Utils.validateUserToken(oldToken, username, session);
        user.setToken(newToken);
        UserDAO.save(user);     //  update new token
        session.flush();
    }

    /**
     * 
     * @param infoClientAsJSON
     */
    public String createClient(String infoClientAsJSON) throws JsonKeyInFaultException, PersistentException, ClientAlreadyExistsException, UserAlreadyExistsException {
        PersistentSession session = HRClientFacade.getSession();
        JsonObject json = Utils.validateJson(gson , infoClientAsJSON , Arrays.asList("username", "password", "name", "email", "sex", "birthday", "height", "weight"));
        Client client = gson.fromJson(infoClientAsJSON, Client.class);
        String username = client.getUsername();
        if(Utils.registerExists("username", username, "Client", session) == true) throw new ClientAlreadyExistsException(username);
        if(Utils.registerExists("username", username, "User", session) == true) throw new UserAlreadyExistsException(username);

        BiometricData biometricData = gson.fromJson(infoClientAsJSON, BiometricData.class);

        if(biometricData != null){
            if(json.has("height") && json.has("weight")){
                float height = (float) (json.get("height").getAsInt())/100;
                float weight = json.get("weight").getAsFloat();
                biometricData.setBMI((float) (weight/(height*height)));
            }
            biometricData.setDate(new Date());
            client.biometricDatas.add(biometricData);
        }

        String token = Utils.tokenGenerate(client.getUsername());

        User user = new User();
        user.setUsername(username);
        user.setToken(token);

        UserDAO.save(user);
        ClientDAO.save(client);
        session.flush();
        return "{\"token\":\"" + token + "\"}";
    }
    
    public void createUser(String usernameAndTokenAsJSON) throws JsonKeyInFaultException, PersistentException, UserAlreadyExistsException{
        PersistentSession session = HRClientFacade.getSession();
        JsonObject json = Utils.validateJson(gson , usernameAndTokenAsJSON , Arrays.asList("username", "token"));
        String username = json.get("username").getAsString(), token = json.get("token").getAsString();
        User user;
        if((user = UserDAO.getUserByORMID(session, username)) != null) throw new UserAlreadyExistsException(username);
        user = new User();
        user.setUsername(username);
        user.setToken(token);
        UserDAO.save(user);
        session.flush();
    }

    /**
     * 
     * @param infoAsJSON
     */
    public String loginClient(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, ClientDoesNotExistException, InvalidPasswordException {
        PersistentSession session = HRClientFacade.getSession();
        JsonObject json = Utils.validateJson(gson, infoAsJSON, Arrays.asList("username", "password"));
        String username = json.get("username").getAsString(), password = json.get("password").getAsString(), oldToken;
        Client client = null;
        if ((client = ClientDAO.getClientByORMID(session,username)) == null) throw new ClientDoesNotExistException(username);
        if(!client.getPassword().equals(password)) throw new InvalidPasswordException(password);
        User user;
        if((user = UserDAO.getUserByORMID(username)) == null)
            throw new ClientDoesNotExistException(username);
        oldToken = user.getToken();
        String newToken = Utils.tokenGenerate(username);
        user.setToken(newToken);
        UserDAO.save(user);                     //  update token
        session.flush();
        return "{ \"oldToken\": \"" + oldToken + "\", " +
                        "\"newToken\": \"" + newToken + "\" }";
    }

    /**
     * 
     * @param usernameAsJSON
     */
    public String getClientProfileByClient(String usernameAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException, UserDoesNotExistException, BiometricDataDoesNotExistException {
        PersistentSession session = HRClientFacade.getSession();
        JsonObject json = Utils.validateJson(gson, usernameAsJSON, Arrays.asList("username", "token"));
        String token = json.get("token").getAsString(), username = json.get("username").getAsString();
        Utils.validateUserToken(token, json.get("username").getAsString(), session);
        Client client;
        if((client = ClientDAO.getClientByORMID(session, username)) == null) throw new ClientDoesNotExistException(username);

        return gson.toJson(client);
    }

    /**
     * 
     * @param usernameAsJSON
     */
    public String getClientProfileByPersonalTrainer(String usernameAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException, PersonalTrainerDoesNotExistException, UserDoesNotExistException, BiometricDataDoesNotExistException {
        PersistentSession session = HRClientFacade.getSession();
        JsonObject json = Utils.validateJson(gson, usernameAsJSON, Arrays.asList("clientUsername", "username" , "token"));
        String token = json.get("token").getAsString(), username = json.get("username").getAsString(), clientUsername = json.get("clientUsername").getAsString();
        Utils.validateUserToken(token, username, session);
        Client client;
        if((client = ClientDAO.getClientByORMID(session, clientUsername)) == null) throw new ClientDoesNotExistException(clientUsername);

        return gson.toJson(client);
    }

    /**
     * 
     * @param infoAsJSON
     */
    public void editClientProfile(String infoAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException, UserDoesNotExistException{
        PersistentSession session = HRClientFacade.getSession();
        JsonObject json = Utils.validateJson(gson, infoAsJSON, Arrays.asList("username", "token"));
        String token = json.get("token").getAsString(), username = json.get("username").getAsString();
        Utils.validateUserToken(token, username, session);
        Client client = null;
        if((client = ClientDAO.getClientByORMID(session, username)) == null) throw new ClientDoesNotExistException(username);

        //	update fields
        if(json.has("name"))
                client.setName(json.get("name").getAsString());
        if(json.has("password"))
                client.setPassword(json.get("password").getAsString());
        if(json.has("email"))
                client.setEmail(json.get("email").getAsString());
        if(json.has("sex"))
                client.setSex(json.get("sex").getAsString());

        BiometricData biometricData = gson.fromJson(infoAsJSON, BiometricData.class);

        if(biometricData != null){
            if(json.has("height") && json.has("weight")){
                float height = (float) (json.get("height").getAsInt())/100;
                float weight = json.get("weight").getAsFloat();
                biometricData.setBMI((float) (weight/(height*height)));
            }
            biometricData.setDate(new Date());
            client.biometricDatas.add(biometricData);
        }
        session.update(client);
        session.flush();
    }

    /**
     * Returns biometric data fields
     * @param usernameAsJSON JSON with clientUsername of biometric data to search, username and token of requester
     */
    public String getBiometricData(String usernameAsJSON) throws JsonKeyInFaultException, PersonalTrainerDoesNotExistException, TokenIsInvalidException, PersistentException, ClientDoesNotExistException, UserDoesNotExistException, BiometricDataDoesNotExistException {
        PersistentSession session = HRClientFacade.getSession();
        JsonObject json = Utils.validateJson(gson, usernameAsJSON, Arrays.asList("clientUsername", "username" , "token"));
        String token = json.get("token").getAsString(), username = json.get("username").getAsString(), clientUsername = json.get("clientUsername").getAsString();
        Utils.validateUserToken(token, username, session);

        if(Utils.registerExists("username", clientUsername, "Client", session) == false) throw new ClientDoesNotExistException(clientUsername);

        Query q = session.createQuery("select MAX(id) from BiometricData where ClientUsername = \'" + clientUsername + "\'");
        Iterator it = q.list().iterator();
        BiometricData biometricData = null;
        int maxId = 0;
        if(it.hasNext()){
            maxId = (Integer) it.next();
            biometricData = BiometricDataDAO.getBiometricDataByORMID(session, maxId);
        }
        if(biometricData == null) throw new BiometricDataDoesNotExistException();

        return gson.toJson(biometricData);
    }
}
