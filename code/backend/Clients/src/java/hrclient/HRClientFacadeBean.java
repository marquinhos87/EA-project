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
        JsonObject json = Utils.validateJson(gson , usernameAndTokenAsJson , Arrays.asList("username", "oldToken", "newToken"));
        String oldToken = json.get("oldToken").getAsString(), username = json.get("username").getAsString(), newToken = json.get("newToken").getAsString();
        User user = Utils.validateUserToken(oldToken, username);
        user.setToken(newToken);
        UserDAO.save(user);     //  update new token
        
    }

    /**
     * 
     * @param infoClientAsJSON
     */
    public String createClient(String infoClientAsJSON) throws JsonKeyInFaultException, PersistentException, ClientAlreadyExistsException, UserAlreadyExistsException {
            JsonObject json = Utils.validateJson(gson , infoClientAsJSON , Arrays.asList("username", "password", "name", "email", "sex", "birthday", "height", "weight"));
            Client client = gson.fromJson(infoClientAsJSON, Client.class);
            String username = client.getUsername();
            if(ClientDAO.getClientByORMID(HRClientFacade.getSession(),  username) != null) throw new ClientAlreadyExistsException(username);
            if(UserDAO.getUserByORMID(HRClientFacade.getSession(),  username) != null) throw new UserAlreadyExistsException(username);
            
            BiometricData biometricData = gson.fromJson(infoClientAsJSON, BiometricData.class);
            biometricData.setDate(new Date());
            float height = (float) (json.get("height").getAsInt())/100; float weight = json.get("weight").getAsFloat();
            biometricData.setBMI((float) (weight/(height*height)));
            client.biometricDatas.add(biometricData);
            
            String token = Utils.tokenGenerate(client.getUsername());
            
            User user = new User();
            user.setUsername(username);
            user.setToken(token);
            
            UserDAO.save(user);
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
            User user;
            if((user = UserDAO.getUserByORMID(username)) == null)
                throw new ClientDoesNotExistException(username);
            oldToken = user.getToken();
            String newToken = Utils.tokenGenerate(client.getUsername());
            user.setToken(newToken);
            UserDAO.save(user);                     //  update token
            ClientDAO.save(client);                 //  update username of client
            return "{ \"oldToken\": \"" + oldToken + "\", " +
                            "\"newToken\": \"" + newToken + "\" }";
    }

    /**
     * 
     * @param usernameAsJSON
     */
    public String getClientProfileByClient(String usernameAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException, UserDoesNotExistException, BiometricDataDoesNotExistException {
            JsonObject json = Utils.validateJson(gson, usernameAsJSON, Arrays.asList("username", "token"));
            String token = json.get("token").getAsString(), username = json.get("username").getAsString();
            Utils.validateUserToken(token, json.get("username").getAsString());
            Client client;
            if((client = ClientDAO.getClientByORMID(username)) == null) throw new ClientDoesNotExistException(username);

            //	get last biometric data using ID
            Query q = HRClientFacade.getSession().createQuery("select MAX(id) from BiometricData where ClientUsername = \'" + username + "\'");
            Iterator it = q.list().iterator();
            BiometricData biometricData = null;
            int maxId = 0;
            if(it.hasNext()){
                maxId = (Integer) it.next();
                biometricData = BiometricDataDAO.getBiometricDataByORMID(maxId);
            }
            if(biometricData == null) throw new BiometricDataDoesNotExistException();
            
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
                            "\"twin\": \"" + biometricData.getTwin() + "\", " +
                            "\"date\": \"" + biometricData.getDate() + "\", " +
                            "\"BMI\": \"" + biometricData.getBMI() + "\"}";
    }

    /**
     * 
     * @param usernameAsJSON
     */
    public String getClientProfileByPersonalTrainer(String usernameAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException, PersonalTrainerDoesNotExistException, UserDoesNotExistException, BiometricDataDoesNotExistException {
            JsonObject json = Utils.validateJson(gson, usernameAsJSON, Arrays.asList("clientUsername", "username" , "token"));
            String token = json.get("token").getAsString(), username = json.get("username").getAsString(), clientUsername = json.get("clientUsername").getAsString();
            Utils.validateUserToken(token, username);
            Client client;
            if((client = ClientDAO.getClientByORMID(clientUsername)) == null) throw new ClientDoesNotExistException(clientUsername);

            //	get last biometric data using ID
            Query q = HRClientFacade.getSession().createQuery("select MAX(id) from BiometricData where ClientUsername = \'" + clientUsername + "\'");
            Iterator it = q.list().iterator();
            BiometricData biometricData = null;
            int maxId = 0;
            if(it.hasNext()){
                maxId = (Integer) it.next();
                biometricData = BiometricDataDAO.getBiometricDataByORMID(maxId);
            }
            if(biometricData == null) throw new BiometricDataDoesNotExistException();
            
            //  to unsend password
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
                            "\"twin\": \"" + biometricData.getTwin() + "\", " +
                            "\"date\": \"" + biometricData.getDate() + "\", " +
                            "\"BMI\": \"" + biometricData.getBMI() + "\"}";
    }

    /**
     * 
     * @param infoAsJSON
     */
    public void editClientProfile(String infoAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException, UserDoesNotExistException{
            JsonObject json = Utils.validateJson(gson, infoAsJSON, Arrays.asList("username", "token"));
            String token = json.get("token").getAsString(), username = json.get("username").getAsString();
            Utils.validateUserToken(token, json.get("username").getAsString());
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
            float height = (float) (json.get("height").getAsInt())/100; float weight = json.get("weight").getAsFloat();
            biometricData.setBMI((float) (weight/(height*height)));
            biometricData.setDate(new Date());
            client.biometricDatas.add(biometricData);
            ClientDAO.save(client);
    }

    /**
     * Returns biometric data fields
     * @param usernameAsJSON JSON with clientUsername of biometric data to search, username and token of requester
     */
    public String getBiometricData(String usernameAsJSON) throws JsonKeyInFaultException, PersonalTrainerDoesNotExistException, TokenIsInvalidException, PersistentException, ClientDoesNotExistException, UserDoesNotExistException, BiometricDataDoesNotExistException {
            JsonObject json = Utils.validateJson(gson, usernameAsJSON, Arrays.asList("clientUsername", "username" , "token"));
            String token = json.get("token").getAsString(), username = json.get("username").getAsString(), clientUsername = json.get("clientUsername").getAsString();
            Utils.validateUserToken(token, username);
            Client client;
            if((client = ClientDAO.getClientByORMID(clientUsername)) == null) throw new ClientDoesNotExistException(clientUsername);

            Query q = HRClientFacade.getSession().createQuery("select MAX(id) from BiometricData where ClientUsername = \'" + clientUsername + "\'");
            Iterator it = q.list().iterator();
            BiometricData biometricData = null;
            int maxId = 0;
            if(it.hasNext()){
                maxId = (Integer) it.next();
                biometricData = BiometricDataDAO.getBiometricDataByORMID(maxId);
            }
            if(biometricData == null) throw new BiometricDataDoesNotExistException();
            
            return gson.toJson(biometricData);
    }
}
