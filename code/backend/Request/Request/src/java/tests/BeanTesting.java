package tests;

import org.orm.PersistentException;
import requests.*;

public class BeanTesting {
    public static void main(String[] args) {
        RequestFacadeBean bean = new RequestFacadeBean();

        String json1 = "{\n" +
                "  \"username\": \"josepereira\",\n" +
                "  \"token\": \"" + Utils.tokenGenerate("josepereira") + "\"\n" +
                "}";

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

        String json4 = "{\n" +
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
        }

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
