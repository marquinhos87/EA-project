package beans;

import notifications.NotificationDoesNotBelongToUser;
import notifications.NotificationNotExistsException;
import notifications.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.Date;
import org.orm.PersistentException;

// ADORO O NESTOR

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
    public void createNotificationToPersonalTrainer(String infoAsJson) throws JsonKeyInFaultException, TokenIsInvalidException, PersistentException, PersonalTrainerNotExistsException, ClientNotExistsException, UserNotExistsException {
        JsonObject jo = Utils.validateJson(gson, infoAsJson, Arrays.asList("token", "username", "personalTrainerUsername", "description"));
        User user = gson.fromJson(infoAsJson, User.class);
        Utils.validateToken(user.getToken(), user.getUsername());
        String personalTrainerUsername = jo.get("personalTrainerUsername").getAsString();
        PersonalTrainer pt;
        if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(NotificationFacade.getSession(), personalTrainerUsername)) == null)  {
            throw new PersonalTrainerNotExistsException(personalTrainerUsername);
        }
        Notification notification = new Notification();
        notification.setDate(new Date());
        notification.setDescription(jo.get("description").getAsString());
        notification.setRead(false);
        pt.notifications.add(notification);
        PersonalTrainerDAO.save(pt);
        System.err.println("notification added to personal trainer...");
        //client.notifications.add(notification);
        //ClientDAO.save(client);
        //System.err.println("notification added to client...");
    }
    
    /**
     * 
     * @param infoAsJson
     */
    public void createNotificationToClient(String infoAsJson) throws JsonKeyInFaultException, TokenIsInvalidException, PersistentException, ClientNotExistsException, PersonalTrainerNotExistsException, UserNotExistsException {
        JsonObject jo = Utils.validateJson(gson, infoAsJson, Arrays.asList("token", "username", "clientUsername", "description"));
        User user = gson.fromJson(infoAsJson, User.class);
        Utils.validateToken(user.getToken(), user.getUsername());
        String clientUsername = jo.get("clientUsername").getAsString();
        Client client;
        if ( (client = ClientDAO.getClientByORMID(NotificationFacade.getSession(), clientUsername)) == null)  {
            throw new ClientNotExistsException(clientUsername);
        }
        Notification notification = new Notification();
        notification.setDate(new Date());
        notification.setDescription(jo.get("description").getAsString());
        notification.setRead(false);
        client.notifications.add(notification);
        ClientDAO.save(client);
        System.err.println("notification added to client...");
        //pt.notifications.add(notification);
        //PersonalTrainerDAO.save(pt);
        //System.err.println("notification added to personal trainer...");
    }

    /**
     * 
     * @param infoAsJson
     */
    public void markAsReadNotificationsByClient(String infoAsJson) throws JsonKeyInFaultException, ClientNotExistsException, PersistentException, TokenIsInvalidException, NotificationNotExistsException, UserNotExistsException, NotificationDoesNotBelongToUser {
        JsonObject jo = Utils.validateJson(gson, infoAsJson, Arrays.asList("token", "username", "ids"));
        User user = gson.fromJson(infoAsJson, User.class);
        Utils.validateToken(user.getToken(), user.getUsername());
        if (Utils.exists("username", user.getUsername(), "Client") == false) throw new ClientNotExistsException(user.getUsername());
        JsonArray arr = jo.get("ids").getAsJsonArray();
        for (JsonElement je: arr) {
            int id = je.getAsInt();
            System.err.print(id);
            Notification n;
            if ( (n = NotificationDAO.getNotificationByORMID(id)) == null) {
                throw new NotificationNotExistsException(Integer.toString(id));
            }
            if (NotificationFacade.getSession().createQuery("from Notification where id=" + n.getID() + " and clientUsername='" + user.getUsername() + "'").list().isEmpty()) 
                throw new NotificationDoesNotBelongToUser("notification with id - " + n.getID() + " - does not belong to user with username - " + user.getUsername() + ".");
            System.err.print(n);
            n.setRead(true);
            NotificationDAO.save(n);
        }
    }
    
    /**
     * 
     * @param infoAsJson
     */
    public void markAsReadNotificationsByPersonalTrainer(String infoAsJson) throws JsonKeyInFaultException, PersonalTrainerNotExistsException, PersistentException, TokenIsInvalidException, NotificationNotExistsException, UserNotExistsException, NotificationDoesNotBelongToUser {
        JsonObject jo = Utils.validateJson(gson, infoAsJson, Arrays.asList("token", "username", "ids"));
        User user = gson.fromJson(infoAsJson, User.class);
        Utils.validateToken(user.getToken(), user.getUsername());
        if (Utils.exists("username", user.getUsername(), "PersonalTrainer") == false) throw new PersonalTrainerNotExistsException(user.getUsername());
        JsonArray arr = jo.get("ids").getAsJsonArray();
        for (JsonElement je: arr) {
            int id = je.getAsInt();
            Notification n;
            if ( (n = NotificationDAO.getNotificationByORMID(id)) == null) {
                throw new NotificationNotExistsException(Integer.toString(id));
            }
            if (NotificationFacade.getSession().createQuery("from Notification where id=" + n.getID() + " and personalTrainerUsername='" + user.getUsername() + "'").list().isEmpty()) 
                throw new NotificationDoesNotBelongToUser("notification with id - " + n.getID() + " - does not belong to user with username - " + user.getUsername() + ".");
            n.setRead(true);
            NotificationDAO.save(n);
        }
    }
    
    /**
     * 
     * @param usernameAsJson
     */
    public String getNotificationsByPersonalTrainer(String usernameAndTokenAsJson) throws PersistentException, JsonKeyInFaultException, PersonalTrainerNotExistsException, TokenIsInvalidException, UserNotExistsException {
        Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("token", "username"));
        User user = gson.fromJson(usernameAndTokenAsJson, User.class);
        Utils.validateToken(user.getToken(), user.getUsername());
        PersonalTrainer pt;
        if ( (pt = PersonalTrainerDAO.getPersonalTrainerByORMID(user.getUsername())) == null)  {
            throw new PersonalTrainerNotExistsException(user.getUsername());
        }
        /*
        List<Notification> notifications = new ArrayList<>();
        for (Notification n: pt.notifications.toArray()) {
            if (n.getRead() == false) {
                notifications.add(n);
            }
        }
        */
        //List<Notification> notifications = NotificationFacade.getSession().createQuery("from Notification where personalTrainerUsername =\'" + pt.getUsername() + "\' and read=false" ).list();
        return gson.toJson( pt.notifications.toArray() );
    }

    /**
     * 
     * @param usernameAsJson
     */
    public String getNotificationsByClient(String usernameAndTokenAsJson) throws PersistentException, JsonKeyInFaultException, ClientNotExistsException, TokenIsInvalidException, UserNotExistsException {
        Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("token", "username"));
        User user = gson.fromJson(usernameAndTokenAsJson, User.class);
        Utils.validateToken(user.getToken(), user.getUsername());
        Client client;
        if ( (client = ClientDAO.getClientByORMID(user.getUsername())) == null)  {
            throw new ClientNotExistsException(user.getUsername());
        }
        /*
        List<Notification> notifications = new ArrayList<>();
        for (Notification n: client.notifications.toArray()) {
            if (n.getRead() == false) {
                notifications.add(n);
            }
        }
        */
        //List<Notification> notifications = NotificationFacade.getSession().createQuery("from Notification where clientUsername =\'" + client.getUsername() + "\' and read=false" ).list();
        return gson.toJson( client.notifications.toArray() );
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
        JsonObject jsonObject = Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("oldToken", "newToken", "username"));
        String oldToken = jsonObject.get("oldToken").getAsString();
        String username = jsonObject.get("username").getAsString();
        Utils.validateToken(oldToken, username);
        User user = UserDAO.getUserByORMID(NotificationFacade.getSession(), username);
        String currentToken = jsonObject.get("newToken").getAsString();
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
        JsonObject jsonObject = Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("oldToken", "newToken", "username"));
        String oldToken = jsonObject.get("oldToken").getAsString();
        String username = jsonObject.get("username").getAsString();
        Utils.validateToken(oldToken, username);
        User user = UserDAO.getUserByORMID(NotificationFacade.getSession(), username);
        String currentToken = jsonObject.get("newToken").getAsString();
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