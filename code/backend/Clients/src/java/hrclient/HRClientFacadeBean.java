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
import javax.ejb.Stateless;
import org.orm.PersistentException;
import redis.clients.jedis.Jedis;

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
                            .create();
    private static final String REDIS_HOST = "localhost";
    private static final int REDIS_PORT = 6379;
    private final Jedis redis = new Jedis(REDIS_HOST, REDIS_PORT);
    
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }

    /**
     * Update token of other entities associated with Client, at the moment only Personal Trainer.
     * @param usernameAndTokenAsJson json with username, old token and new token of personal trainer
     */
    public void updateUserToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, TokenIsInvalidException, PersonalTrainerDoesNotExistException{
        JsonObject json = Utils.validateJson(gson , usernameAndTokenAsJson , Arrays.asList("username", "oldToken", "newToken"));
        String oldToken = json.get("oldToken").getAsString(), username = json.get("username").getAsString(), newToken = json.get("newToken").getAsString();
        Utils.validatePersonalTrainerToken(oldToken, username, redis);
        redis.set(username, newToken);
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
            biometricData.setDate(new Date());
            client.biometricDatas.add(biometricData);
            String token = Utils.tokenGenerate(client.getUsername());
            if(redis.exists(username) == true)
                throw new ClientAlreadyExistsException(username + "on redis database");
            redis.set(username, token);
            ClientDAO.save(client);
            return "{\"token\":\"" + token + "\"}";
    }

    /**
     * 
     * @param infoAsJSON
     */
    public String loginClient(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, ClientDoesNotExistException, InvalidPasswordException {
            JsonObject json = Utils.validateJson(gson, infoAsJSON, Arrays.asList("username", "password"));
            String username = json.get("username").getAsString(), password = json.get("password").getAsString(), oldToken;
            Client client = null;
            if ( (client = ClientDAO.getClientByORMID(HRClientFacade.getSession(),username)) == null) throw new ClientDoesNotExistException(username);
            if(!client.getPassword().equals(password)) throw new InvalidPasswordException(password);
            if(!redis.exists(username))
                    throw new ClientDoesNotExistException(username + "on redis database");
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
    public String getClientProfileByClient(String usernameAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException {
            JsonObject json = Utils.validateJson(gson, usernameAsJSON, Arrays.asList("username", "token"));
            String token = json.get("token").getAsString(), username = json.get("username").getAsString();
            Utils.validateClientToken(token, json.get("username").getAsString(), redis);
            Client client;
            if((client = ClientDAO.getClientByORMID(username)) == null) throw new ClientDoesNotExistException(username);

            //	get last biometric data using ID
            BiometricData biometricData ;
            int maxID = -1, elemID, index = 0, maxIDIndex = 0;
            BiometricData[] biometricDatas = client.biometricDatas.toArray();
            for(BiometricData elem : biometricDatas) {
                    if ((elemID = elem.getID()) > maxID) {
                            maxID = elemID;					//	save value of max id at the moment
                            maxIDIndex = index;				//	save current position at maxIDIndex (index of array with max Id at the moment)
                    }
                    index++;							//	increment array index
            }
            if(biometricDatas.length != 0)biometricData = biometricDatas[maxIDIndex];	//	get last register
            else biometricData = new BiometricData();									//	empty
            //	TODO neste momento esta forma não é a melhor de construir o JSON, mas vou avançar e depois melhoro isto
            return "{ \"username\": \"" + client.getUsername() + "\", " +
                            "\"password\": \"" + client.getPassword() + "\", " +
                            "\"name\": \"" + client.getName() + "\", " +
                            "\"sex\": \"" + client.getSex() + "\", " +
                            "\"height\": \"" + biometricData.getHeight() + "\", " +
                            "\"weight\": \"" + biometricData.getWeight() + "\", " +
                            "\"wrist\": \"" + biometricData.getWrist() + "\", " +
                            "\"chest\": \"" + biometricData.getChest() + "\", " +
                            "\"tricep\": \"" + biometricData.getTricep() + "\", " +
                            "\"waist\": \"" + biometricData.getWaist()+ "\", " +
                            "\"quadricep\": \"" + biometricData.getQuadricep() + "\", " +
                            "\"twin\": \"" + biometricData.getTwin() + "\"}";
    }

    /**
     * 
     * @param usernameAsJSON
     */
    public String getClientProfileByPersonalTrainer(String usernameAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException, PersonalTrainerDoesNotExistException {
            JsonObject json = Utils.validateJson(gson, usernameAsJSON, Arrays.asList("clientUsername", "username" , "token"));
            String token = json.get("token").getAsString(), username = json.get("username").getAsString(), clientUsername = json.get("clientUsername").getAsString();
            Utils.validatePersonalTrainerToken(token, username, redis);
            Client client;
            if((client = ClientDAO.getClientByORMID(clientUsername)) == null) throw new ClientDoesNotExistException(clientUsername);

            //	get last biometric data using ID
            BiometricData biometricData ;
            int maxID = -1, elemID, index = 0, maxIDIndex = 0;
            BiometricData[] biometricDatas = client.biometricDatas.toArray();
            for(BiometricData elem : biometricDatas) {
                    if ((elemID = elem.getID()) > maxID) {
                            maxID = elemID;					//	save value of max id at the moment
                            maxIDIndex = index;				//	save current position at maxIDIndex (index of array with max Id at the moment)
                    }
                    index++;							//	increment array index
            }
            if(biometricDatas.length != 0)biometricData = biometricDatas[maxIDIndex];	//	get last register
            else biometricData = new BiometricData();									//	empty
            //	TODO neste momento esta forma não é a melhor de construir o JSON, mas vou avançar e depois melhoro isto
            return "{\"name\": \"" + client.getName() + "\", " +
                            "\"email\": \"" + client.getEmail() + "\", " +
                            "\"sex\": \"" + client.getSex() + "\", " +
                            "\"age\": " + Utils.years(client.getBirthday(), new Date()) + ", " +
                            "\"height\": \"" + biometricData.getHeight() + "\", " +
                            "\"weight\": \"" + biometricData.getWeight() + "\", " +
                            "\"wrist\": \"" + biometricData.getWrist() + "\", " +
                            "\"chest\": \"" + biometricData.getChest() + "\", " +
                            "\"tricep\": \"" + biometricData.getTricep() + "\", " +
                            "\"waist\": \"" + biometricData.getWaist()+ "\", " +
                            "\"quadricep\": \"" + biometricData.getQuadricep() + "\", " +
                            "\"twin\": \"" + biometricData.getTwin() + "\"}";
    }

    /**
     * 
     * @param infoAsJSON
     */
    public void editClientProfile(String infoAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException {
            JsonObject json = Utils.validateJson(gson, infoAsJSON, Arrays.asList("username", "token"));
            String token = json.get("token").getAsString(), username = json.get("username").getAsString();
            Utils.validateClientToken(token, json.get("username").getAsString(), redis);
            Client client = null;
            if((client = ClientDAO.getClientByORMID(username)) == null) throw new ClientDoesNotExistException(username);

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
            client.biometricDatas.add(biometricData);
            ClientDAO.save(client);
    }

    /**
     * Returns biometric data fields
     * @param usernameAsJSON JSON with clientUsername of biometric data to search, username and token of requester
     */
    public String getBiometricData(String usernameAsJSON) throws JsonKeyInFaultException, PersonalTrainerDoesNotExistException, TokenIsInvalidException, PersistentException, ClientDoesNotExistException {
            JsonObject json = Utils.validateJson(gson, usernameAsJSON, Arrays.asList("clientUsername", "username" , "token"));
            String token = json.get("token").getAsString(), username = json.get("username").getAsString(), clientUsername = json.get("clientUsername").getAsString();
            Utils.validatePersonalTrainerToken(token, username, redis);
            Client client;
            //	TODO não está da melhor forma, pois vamos buscar o client à base de dados, depois verei uma forma mais eficiente
            if((client = ClientDAO.getClientByORMID(clientUsername)) == null) throw new ClientDoesNotExistException(clientUsername);

            //	get last biometric data using ID
            BiometricData biometricData ;
            int maxID = -1, elemID, index = 0, maxIDIndex = 0;
            BiometricData[] biometricDatas = client.biometricDatas.toArray();
            for(BiometricData elem : biometricDatas) {
                    if ((elemID = elem.getID()) > maxID) {
                            maxID = elemID;					//	save value of max id at the moment
                            maxIDIndex = index;				//	save current position at maxIDIndex (index of array with max Id at the moment)
                    }
                    index++;							//	increment array index
            }
            if(biometricDatas.length != 0)biometricData = biometricDatas[maxIDIndex];	//	get last register
            else biometricData = new BiometricData();									//	empty
            //	TODO neste momento esta forma não é a melhor de construir o JSON, mas vou avançar e depois melhoro isto
            return "{\"height\": \"" + biometricData.getHeight() + "\", " +
                            "\"weight\": \"" + biometricData.getWeight() + "\", " +
                            "\"wrist\": \"" + biometricData.getWrist() + "\", " +
                            "\"chest\": \"" + biometricData.getChest() + "\", " +
                            "\"tricep\": \"" + biometricData.getTricep() + "\", " +
                            "\"waist\": \"" + biometricData.getWaist()+ "\", " +
                            "\"quadricep\": \"" + biometricData.getQuadricep() + "\", " +
                            "\"twin\": \"" + biometricData.getTwin() + "\"}";
    }
}
