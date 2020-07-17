package tests;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.orm.PersistentException;
import requests.*;

public class BeanTesting {
    public static void main(String[] args) {
        RequestFacadeBean bean = new RequestFacadeBean();
        /*PersonalTrainer pt = null;
        try {
            pt = PersonalTrainerDAO.getPersonalTrainerByORMID("ptricardo");
        } catch (PersistentException ex) {
            Logger.getLogger(BeanTesting.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Request[] requests = pt.requests.toArray();
        
        for(int i = 0; i < requests.length; i++){
            System.err.println(requests[i]);
        }*/
        
        String json155 = "{\n" +
                "  \"username\": \"ptricardo\",\n" +
                "  \"token\": \"123\"\n" +
                "}";
        
        /*String json1 = "{\n" +
                "  \"username\": \"joaquina\",\n" +
                "  \"token\": \"ksaijohufaakdasoidu4382r9huiea7d\",\n" +
                "  \"id\": 1\n" +
                "}";
        
        System.err.print(json1);
        
        try {
            bean.getUsernameByRequestId(json1);
        } catch (JsonKeyInFaultException ex) {
            Logger.getLogger(BeanTesting.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TokenIsInvalidException ex) {
            Logger.getLogger(BeanTesting.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PersistentException ex) {
            Logger.getLogger(BeanTesting.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UserDoesNotExistException ex) {
            Logger.getLogger(BeanTesting.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RequestDoesNotExistException ex) {
            Logger.getLogger(BeanTesting.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        /*String json2 = "{\n" +
                "  \"username\": \"josepereira\",\n" +
                "  \"newToken\": \"" + Utils.tokenGenerate("josepereira") + "\",\n" +
                "  \"oldToken\": \"josepereiraTPGUSOLbbk9qUbc6y5AhL\",\n" +
                "  \"oldToken\": \"josepereiraTPGUSOLbbk9qUbc6y5AhL\"\n" +
                "}";

        try {
            bean.updateUserToken(json2);
        } catch (JsonKeyInFaultException e) {
            e.printStackTrace();
        } catch (TokenIsInvalidException e) {
            e.printStackTrace();
        } catch (UserDoesNotExistException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }*/

        /*String json3 = "{\n" +
                "  \"username\": \"marques\",\n" +
                "  \"token\": \"" + Utils.tokenGenerate("marques") + "\"\n" +
                "}";

        try {
            bean.createClient(json3);
        } catch (JsonKeyInFaultException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        } catch (ClientDoesNotExistException e) {
            e.printStackTrace();
        } catch (ClientAlreadyExistsException e) {
            e.printStackTrace();
        }*/

        /*String json4 = "{\n" +
                "  \"username\": \"ricardo\",\n" +
                "  \"token\": \"" + Utils.tokenGenerate("ricardo") + "\"\n" +
                "}";

        try {
            bean.createPersonalTrainer(json4);
        } catch (JsonKeyInFaultException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        } catch (PersonalTrainerDoesNotExistException e) {
            e.printStackTrace();
        } catch (PersonalTrainerAlreadyExistsException e) {
            e.printStackTrace();
        }*/

        /*String json4 = "{\n" +
                "  \"username\": \"marques\",\n" +
                "  \"token\": \"marquestdE1kJrMVOQJVpmtlOtxEgxEr\",\n" +
                "  \"personalTrainerUsername\": \"ricardo\",\n" +
                "  \"numberOfWeeks\": \"3\",\n" +
                "  \"objective\": \"Perder peso\",\n" +
                "  \"workoutPerWeek\": \"3\",\n" +
                "  \"weekDays\": \"3;4;5;6\",\n" +
                "  \"level\": \"3\",\n" +
                "  \"accepted\": \"false\"\n" +
                "}";

        try {
            bean.submitRequest(json4);
        } catch (JsonKeyInFaultException e) {
            e.printStackTrace();
        } catch (TokenIsInvalidException e) {
            e.printStackTrace();
        } catch (UserDoesNotExistException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        } catch (ClientDoesNotExistException e) {
            e.printStackTrace();
        } catch (PersonalTrainerDoesNotExistException e) {
            e.printStackTrace();
        }*/

        /*String json5 = "{\n" +
                "  \"username\": \"ricardo\",\n" +
                "  \"token\": \"ricardoAToaqkSIMXrIcoTtp2ZL9UgAR\",\n" +
                "  \"requestId\": \"1\",\n" +
                "  \"accepted\": \"true\"\n" +
                "}";

        try {
            bean.replyToRequest(json5);
        } catch (JsonKeyInFaultException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        } catch (PersonalTrainerDoesNotExistException e) {
            e.printStackTrace();
        } catch (RequestDoesNotExistException e) {
            e.printStackTrace();
        } catch (UserDoesNotExistException e) {
            e.printStackTrace();
        } catch (TokenIsInvalidException e) {
            e.printStackTrace();
        }*/

        /*String json6 = "{\n" +
                "  \"username\": \"ricardo\",\n" +
                "  \"token\": \"ricardoAToaqkSIMXrIcoTtp2ZL9UgAR\",\n" +
                "  \"requestId\": \"1\",\n" +
                "  \"accepted\": \"true\"\n" +
                "}";

        try {
            System.err.println(bean.listClientRequestsByPersonalTrainer(json6));
        } catch (JsonKeyInFaultException e) {
            e.printStackTrace();
        } catch (TokenIsInvalidException e) {
            e.printStackTrace();
        } catch (UserDoesNotExistException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        } catch (PersonalTrainerDoesNotExistException e) {
            e.printStackTrace();
        }*/

        System.err.println();

        String json7 = "{\n" +
                "  \"username\": \"josepereira\",\n" +
                "  \"token\": \"josepereirabYgnSp8kG3t2JHhbWlY9L\",\n" +
                "  \"requestId\": \"1\",\n" +
                "  \"accepted\": \"true\"\n" +
                "}";

        /*try {
            System.err.println(bean.listClientRequestsByClient(json7));
        } catch (TokenIsInvalidException e) {
            e.printStackTrace();
        } catch (UserDoesNotExistException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        } catch (JsonKeyInFaultException e) {
            e.printStackTrace();
        } catch (ClientDoesNotExistException e) {
            e.printStackTrace();
        }*/
    }
}
