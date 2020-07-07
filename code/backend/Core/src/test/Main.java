package test;

import beans.CoreFacadeBean;
import core.CoreFacade;
import exceptions.*;
import org.orm.PersistentException;

public class Main {
    public static void main(String[] args) {
        CoreFacadeBean bean = new CoreFacadeBean();

        // Create UserToken
        // Testing
        String user = "{ \"username\": \"marques\",\"token\":\"marquesapsiubvqpribv\"}";
        try {
            bean.createUserToken(user);
        } catch (UserAlreadyExistsException | JsonKeyInFaultException e) {
            e.printStackTrace();
        }

        // Update Token
        // Testing
        /*String newToken = "{ \"username\": \"marques\",\"oldToken\":\"marquesapsiubvqpribv\", \"newToken\":\"marquesNewToken\"}";
        try {
            bean.updateToken(newToken);
        } catch (PersistentException | InvalidTokenException | UserDontExistsException | JsonKeyInFaultException e) {
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
