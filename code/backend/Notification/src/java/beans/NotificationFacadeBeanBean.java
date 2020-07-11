package beans;

import notifications.NotificationNotExistsException;
import notifications.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import notifications.*;
import org.orm.PersistentException;

@javax.ejb.Stateless(name="NotificationsFacadeBean")
@javax.ejb.Remote(NotificationFacadeBean.class)
@javax.ejb.Local(NotificationFacadeBeanLocal.class)
public class NotificationFacadeBeanBean implements NotificationFacadeBean, NotificationFacadeBeanLocal {

    private final Gson gson;

    public NotificationFacadeBeanBean() {
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
    }

    /**
     * 
     * @param infoAsJson
     */
    public void createNotificationToPersonalTrainer(String infoAsJson) throws JsonKeyInFaultException, TokenIsInvalidException, PersistentException, PersonalTrainerNotExistsException {
        JsonObject jo = Utils.validateJson(gson, infoAsJson, Arrays.asList("token", "username", "description"));
        User user = gson.fromJson(infoAsJson, User.class);
        PersonalTrainer pt;
        if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(NotificationFacade.getSession(), user.getUsername())) == null)  {
            throw new PersonalTrainerNotExistsException(user.getUsername());
        }
        //Utils.validateToken(user.getToken(), user.getUsername());
        Notification notification = new Notification();
        notification.setDate(new Date());
        notification.setDescription(jo.get("description").getAsString());
        notification.setRead(false);
        pt.notifications.add(notification);
        PersonalTrainerDAO.save(pt);
        System.err.println("notification added to personal trainer...");
    }
    
    /**
     * 
     * @param infoAsJson
     */
    public void createNotificationToClient(String infoAsJson) throws JsonKeyInFaultException, TokenIsInvalidException, PersistentException, ClientNotExistsException {
        JsonObject jo = Utils.validateJson(gson, infoAsJson, Arrays.asList("token", "username", "description"));
        User user = gson.fromJson(infoAsJson, User.class);
        Client client;
        if ( (client = ClientDAO.getClientByORMID(NotificationFacade.getSession(), user.getUsername())) == null)  {
            throw new ClientNotExistsException(user.getUsername());
        }
        //Utils.validateToken(user.getToken(), user.getUsername());
        Notification notification = new Notification();
        notification.setDate(new Date());
        notification.setDescription(jo.get("description").getAsString());
        notification.setRead(false);
        client.notifications.add(notification);
        ClientDAO.save(client);
        System.err.println("notification added to client...");
    }

    /**
     * 
     * @param infoAsJson
     */
    public void markAsReadNotificationsByClient(String infoAsJson) throws JsonKeyInFaultException, ClientNotExistsException, PersistentException, TokenIsInvalidException, NotificationNotExistsException {
        JsonObject jo = Utils.validateJson(gson, infoAsJson, Arrays.asList("token", "username", "ids"));
        User user = gson.fromJson(infoAsJson, User.class);
        Client client;
        if ( (client = ClientDAO.getClientByORMID(NotificationFacade.getSession(), user.getUsername())) == null)  {
            throw new ClientNotExistsException(user.getUsername());
        }
        Utils.validateToken(user.getToken(), user.getUsername());
        JsonArray arr = jo.get("ids").getAsJsonArray();
        for (JsonElement je: arr) {
            int id = je.getAsInt();
            Notification n;
            if ( (n = NotificationDAO.getNotificationByORMID(id)) == null) {
                throw new NotificationNotExistsException(Integer.toString(id));
            }
            n.setRead(true);
            NotificationDAO.save(n);
        }
    }
    
    /**
     * 
     * @param infoAsJson
     */
    public void markAsReadNotificationsByPersonalTrainer(String infoAsJson) throws JsonKeyInFaultException, PersonalTrainerNotExistsException, PersistentException, TokenIsInvalidException, NotificationNotExistsException {
        JsonObject jo = Utils.validateJson(gson, infoAsJson, Arrays.asList("token", "username", "ids"));
        User user = gson.fromJson(infoAsJson, User.class);
        PersonalTrainer pt;
        if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(NotificationFacade.getSession(), user.getUsername())) == null)  {
            throw new PersonalTrainerNotExistsException(user.getUsername());
        }
        Utils.validateToken(user.getToken(), user.getUsername());
        JsonArray arr = jo.get("ids").getAsJsonArray();
        for (JsonElement je: arr) {
            int id = je.getAsInt();
            Notification n;
            if ( (n = NotificationDAO.getNotificationByORMID(id)) == null) {
                throw new NotificationNotExistsException(Integer.toString(id));
            }
            n.setRead(true);
            NotificationDAO.save(n);
        }
    }
    
    /**
     * 
     * @param usernameAsJson
     */
    public String getNotificationsByPersonalTrainer(String usernameAndTokenAsJson) throws PersistentException, JsonKeyInFaultException, PersonalTrainerNotExistsException, TokenIsInvalidException {
        Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("token", "username"));
        User user = gson.fromJson(usernameAndTokenAsJson, User.class);
        PersonalTrainer pt;
        if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(user.getUsername())) == null)  {
            throw new PersonalTrainerNotExistsException(user.getUsername());
        }
        Utils.validateToken(user.getToken(), user.getUsername());
        List<Notification> notifications = new ArrayList<>();
        for (Notification n: pt.notifications.toArray()) {
            if (n.getRead() == false) {
                notifications.add(n);
            }
        }
        return gson.toJson( notifications.toArray(new Notification[notifications.size()]) );
    }

    /**
     * 
     * @param usernameAsJson
     */
    public String getNotificationsByClient(String usernameAndTokenAsJson) throws PersistentException, JsonKeyInFaultException, ClientNotExistsException, TokenIsInvalidException {
        Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("token", "username"));
        User user = gson.fromJson(usernameAndTokenAsJson, User.class);
        Client client;
        if ( (client = ClientDAO.getClientByORMID(user.getUsername())) == null)  {
            throw new ClientNotExistsException(user.getUsername());
        }
        Utils.validateToken(user.getToken(), user.getUsername());
        List<Notification> notifications = new ArrayList<>();
        for (Notification n: client.notifications.toArray()) {
            if (n.getRead() == false) {
                notifications.add(n);
            }
        }
        return gson.toJson( notifications.toArray(new Notification[notifications.size()]) );
    }

    /**
     * 
     * @param usernameAndTokenAsJson
     * @throws JsonKeyInFaultException
     * @throws PersistentException
     * @throws TokenIsInvalidException
     * @throws UserNotExistsException 
     */
    public void updateClientToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, PersistentException, TokenIsInvalidException, UserNotExistsException {
        JsonObject jsonObject = Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("oldToken", "currentToken", "username"));
        String oldToken = jsonObject.get("oldToken").getAsString();
        String username = jsonObject.get("username").getAsString();
        User user;
        if ((user = UserDAO.getUserByORMID(NotificationFacade.getSession(), username) ) == null) throw new UserNotExistsException(username);
        Utils.validateToken(oldToken, username);
        String currentToken = jsonObject.get("currentToken").getAsString();
        user.setToken(currentToken);
        UserDAO.save(user);
        System.err.println("Client's token updated...");
    }
    
    /**
     * 
     * @param usernameAndTokenAsJson
     * @throws JsonKeyInFaultException
     * @throws PersistentException
     * @throws TokenIsInvalidException
     * @throws UserNotExistsException 
     */
    @Override
    public void updatePersonalTrainerToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, PersistentException, TokenIsInvalidException, UserNotExistsException  {
        JsonObject jsonObject = Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("oldToken", "currentToken", "username"));
        String oldToken = jsonObject.get("oldToken").getAsString();
        String username = jsonObject.get("username").getAsString();
        User user;
        if ((user = UserDAO.getUserByORMID(NotificationFacade.getSession(), username) ) == null) throw new UserNotExistsException(username);
        Utils.validateToken(oldToken, username);
        String currentToken = jsonObject.get("currentToken").getAsString();
        user.setToken(currentToken);
        UserDAO.save(user);
        System.err.println("Personal Trainer's token updated...");
    }

    /**
     * 
     * @param usernameAndTokenAsJson
     * @throws JsonKeyInFaultException
     * @throws PersistentException
     * @throws UserAlreadyExistsException 
     */
    @Override
    public void createClient(String usernameAndTokenAsJson) throws JsonKeyInFaultException, PersistentException, UserAlreadyExistsException {
        Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("token", "username"));
        User user = gson.fromJson(usernameAndTokenAsJson, User.class);
        if (UserDAO.getUserByORMID(NotificationFacade.getSession(), user.getUsername()) != null || ClientDAO.getClientByORMID(NotificationFacade.getSession(), user.getUsername()) != null) throw new UserAlreadyExistsException(user.getUsername());
        UserDAO.save(user);
        Client client = new Client();
        client.setUsername(user.getUsername());
        ClientDAO.save(client);
        System.err.println("Client created...");
    }

    /**
     * 
     * @param usernameAndTokenAsJson
     * @throws JsonKeyInFaultException
     * @throws PersistentException
     * @throws UserAlreadyExistsException 
     */
    @Override
    public void createPersonalTrainer(String usernameAndTokenAsJson) throws JsonKeyInFaultException, PersistentException, UserAlreadyExistsException {
        Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("token", "username"));
        User user = gson.fromJson(usernameAndTokenAsJson, User.class);
        if (UserDAO.getUserByORMID(NotificationFacade.getSession(), user.getUsername()) != null || PersonalTrainerDAO.getPersonalTrainerByORMID(NotificationFacade.getSession(), user.getUsername()) != null) throw new UserAlreadyExistsException(user.getUsername());
        UserDAO.save(user);
        PersonalTrainer pt = new PersonalTrainer();
        pt.setUsername(user.getUsername());
        PersonalTrainerDAO.save(pt);
        System.err.println("Personal Trainer created...");
    }


}