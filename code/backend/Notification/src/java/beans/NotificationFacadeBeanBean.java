package beans;

import notifications.UserAlreadyExistsException;
import notifications.UserNotExistsException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import notifications.*;
import org.orm.PersistentException;

@javax.ejb.Stateless(name="NotificationsFacadeBean")
@javax.ejb.Remote(NotificationFacadeBean.class)
@javax.ejb.Local(NotificationFacadeBeanLocal.class)
public class NotificationFacadeBeanBean implements NotificationFacadeBean, NotificationFacadeBeanLocal {

    private final Gson gson;

    public NotificationFacadeBeanBean() {
        gson = new GsonBuilder().create();
    }

    /**
     * 
     * @param usernameAnddescriptionAsJson
     */
    public void createNotification(String usernameAnddescriptionAsJson) {
            // TODO - implement NotificationsFacadeBean.createNotification
            throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param usernameAndIdsAsJson
     */
    public String markAsReadNotifications(String usernameAndIdsAsJson) {
            // TODO - implement NotificationsFacadeBean.markAsReadNotifications
            throw new UnsupportedOperationException();
    }
    
    /**
     * 
     * @param usernameAsJson
     */
    public String getNotificationsByPersonalTrainer(String usernameAndTokenAsJson) throws PersistentException, JsonKeyInFaultException {
        Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("token", "username"));
        User user = gson.fromJson(usernameAndTokenAsJson, User.class);
        List<Notification> notifications = (List<Notification>) NotificationFacade.getSession().createQuery("from Notification where PersonalTrainerUsername='" + user.getUsername() + "'").list();
        return gson.toJson(notifications);
    }

    /**
     * 
     * @param usernameAsJson
     */
    public String getNotificationsByClient(String usernameAndTokenAsJson) throws PersistentException, JsonKeyInFaultException {
        Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("token", "username"));
        User user = gson.fromJson(usernameAndTokenAsJson, User.class);
        List<Notification> notifications = (List<Notification>) NotificationFacade.getSession().createQuery("from Notification where ClientUsername='" + user.getUsername() + "'").list();
        return gson.toJson(notifications);
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
        if (UserDAO.getUserByORMID(NotificationFacade.getSession(), user.getUsername()) != null) throw new UserAlreadyExistsException(user.getUsername());
        UserDAO.save(user);
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
        if (UserDAO.getUserByORMID(NotificationFacade.getSession(), user.getUsername()) != null) throw new UserAlreadyExistsException(user.getUsername());
        UserDAO.save(user);
        System.err.println("Personal Trainer created...");
    }


}