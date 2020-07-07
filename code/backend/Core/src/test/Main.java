package test;

import beans.CoreFacadeBean;
import core.CoreFacade;
import exceptions.*;
import org.orm.PersistentException;

public class Main {
    public static void main(String[] args) {
        CoreFacadeBean bean = new CoreFacadeBean();

        // Create UserToken
        // Works
        /*String user = "{ \"username\": \"marques\",\"token\":\"marquesapsiubvqpribv\"}";
        try {
            bean.createUserToken(user);
        } catch (UserAlreadyExistsException | PersistentException e) {
            e.printStackTrace();
        }*/

        // Update Token
        // Works
        /*String newToken = "{ \"username\": \"marques\",\"oldToken\":\"marquesapsiubvqpribv\", \"newToken\":\"marquesNewToken\"}";
        try {
            bean.updateToken(newToken);
        } catch (PersistentException | InvalidTokenException | UserTokenDontExistsException | JsonKeyInFaultException e) {
            e.printStackTrace();
        }*/

        // Create Plan
        // Testing
        /* String week = ;
        try {
            bean.createWeek(week);
        } catch (PersistentException | PersonalTrainerAlreadyExistsException e) {
            e.printStackTrace();
        }*/

        try {
            CoreFacade.getSession().close();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }
}
