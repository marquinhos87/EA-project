
import java.util.logging.Level;
import java.util.logging.Logger;
import notifications.ClientNotExistsException;
import notifications.JsonKeyInFaultException;
import notifications.NotificationFacade;
import notifications.TokenIsInvalidException;
import notifications.UserAlreadyExistsException;
import org.orm.PersistentException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ricardo
 */
public class Main {
    
    public static void main(String[] args) {
        
        try {
            NotificationFacade.getInstance().createClient("{\"username\":\"ricardo\", \"token\":\"token\"}");
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        try {
            NotificationFacade.getInstance().createNotificationToClient("{\"username\":\"ricardo\", \"token\":\"token\", \"description\":\"Hello Ricardo!\"}");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } 
        
        try {
            String notifications = NotificationFacade.getInstance().getNotificationsByClient("{\"username\":\"ricardo\", \"token\":\"token\"}");
            System.err.println(notifications);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        try {
            NotificationFacade.getInstance().markAsReadNotificationsByClient("{\"username\":\"ricardo\", \"token\":\"token\", \"ids\":[1,2,3]}");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        // --------------------------------------
        
        try {
            NotificationFacade.getInstance().createPersonalTrainer("{\"username\":\"jose\", \"token\":\"token\"}");
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        try {
            NotificationFacade.getInstance().createNotificationToPersonalTrainer("{\"username\":\"jose\", \"token\":\"token\", \"description\":\"Hello Jose!\"}");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } 
        
        try {
            String notifications = NotificationFacade.getInstance().getNotificationsByPersonalTrainer("{\"username\":\"jose\", \"token\":\"token\"}");
            System.err.println(notifications);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        
    }
    
}
