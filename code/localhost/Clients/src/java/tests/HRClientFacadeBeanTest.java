package tests;

import hrclient.*;
import org.orm.PersistentException;

public class HRClientFacadeBeanTest {
    public static void main(String[] args) {
        /*String json = "{\n" +
                "  \"username\": \"josepereira\",\n" +
                "  \"password\": \"password\",\n" +
                "  \"name\": \"José Pereira\",\n" +
                "  \"email\": \"josepereira@email.com\",\n" +
                "  \"sex\": \"M\",\n" +
                "  \"birthday\": \"2000-06-19\",\n" +
                "  \"height\": \"150\",\n" +
                "  \"weight\": \"150\",\n" +
                "  \"twin\": \"150\"\n" +
                "}";

        String json2 = "{\n" +
                "  \"username\": \"ricardo\",\n" +
                "  \"password\": \"password\",\n" +
                "  \"name\": \"Ricardo Petronilho\",\n" +
                "  \"email\": \"ricardo@email.com\",\n" +
                "  \"sex\": \"M\",\n" +
                "  \"birthday\": \"1998-06-29\",\n" +
                "  \"height\": \"200\",\n" +
                "  \"weight\": \"200\",\n" +
                "  \"twin\": \"200\"\n" +
                "}";
        HRClientFacadeBean hrClientFacadeBean = new HRClientFacadeBeanBean();
        try {
            hrClientFacadeBean.createClient(json2);
        } catch (ClientAlreadyExistsException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        } catch (JsonKeyInFaultException e) {
            e.printStackTrace();
        }*/

        /*String json = "{\n" +
                "  \"username\": \"josepereira\",\n" +
                "  \"password\": \"password\",\n" +
                "  \"token\": \"password\"\n" +
                "}";

        HRClientFacadeBeanBean hrClientFacadeBeanBean = new HRClientFacadeBeanBean();
        try {
            System.out.println(hrClientFacadeBeanBean.loginClient(json));
        } catch (JsonKeyInFaultException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        } catch (ClientDoesNotExistException e) {
            e.printStackTrace();
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
        }*/

        /*String json = "{\n" +
                "  \"username\": \"ricardo\",\n" +
                "  \"name\": \"José Pereira\",\n" +
                "  \"password\": \"password2\",\n" +
                "  \"token\": \"ricardoExZTbZNxKdajM0MQ7NjkTAdHF\",\n" +
                "  \"height\": \"500000\"\n" +
                "}";

        HRClientFacadeBean bean = new HRClientFacadeBeanBean();
        try {
            bean.editClientProfile(json);
        } catch (JsonKeyInFaultException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        } catch (TokenIsInvalidException e) {
            e.printStackTrace();
        } catch (ClientDoesNotExistException e) {
            e.printStackTrace();
        }*/

        /*String json1 = "{\n" +
                "  \"username\": \"ricardo\",\n" +
                "  \"name\": \"José Pereira\",\n" +
                "  \"token\": \"ricardoExZTbZNxKdajM0MQ7NjkTAdHF\",\n" +
                "  \"sex\": \"F\"\n" +
                "}";

        HRClientFacadeBeanBean hrClientFacadeBeanBean = new HRClientFacadeBeanBean();

        try {
            System.out.println(hrClientFacadeBeanBean.getClientProfileByClient(json1));
        } catch (JsonKeyInFaultException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        } catch (TokenIsInvalidException e) {
            e.printStackTrace();
        } catch (ClientDoesNotExistException e) {
            e.printStackTrace();
        }*/

        /*String json2 = "{\n" +
                "  \"username\": \"josepereira\",\n" +
                "  \"clientUsername\": \"ricardo\",\n" +
                "  \"token\": \"josepereiran1E7CWYJH4E4gtfxTDlX3\",\n" +
                "  \"sex\": \"F\"\n" +
                "}";

        HRClientFacadeBean hrClientFacadeBeanBean = new HRClientFacadeBeanBean();

        try {
            System.out.println(hrClientFacadeBeanBean.getClientProfileByPersonalTrainer(json2));
        } catch (JsonKeyInFaultException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        } catch (TokenIsInvalidException e) {
            e.printStackTrace();
        } catch (ClientDoesNotExistException e) {
            e.printStackTrace();
        } catch (PersonalTrainerDoesNotExistException e) {
            e.printStackTrace();
        }*/

        /*String json3 = "{\n" +
                "  \"username\": \"josepereira\",\n" +
                "  \"clientUsername\": \"ricardo\",\n" +
                "  \"token\": \"josepereiraOuUVTuPC9Dg4lRhwJvvoJ\",\n" +
                "  \"sex\": \"F\"\n" +
                "}";

        HRClientFacadeBean hrClientFacadeBeanBean = new HRClientFacadeBeanBean();

        try {
            System.out.println(hrClientFacadeBeanBean.getBiometricData(json3));
        } catch (JsonKeyInFaultException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        } catch (TokenIsInvalidException e) {
            e.printStackTrace();
        } catch (ClientDoesNotExistException e) {
            e.printStackTrace();
        } catch (PersonalTrainerDoesNotExistException e) {
            e.printStackTrace();
        }*/
    }
}
