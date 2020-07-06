package tests;

import hrclient.*;
import org.orm.PersistentException;

public class HRClientFacadeBeanTest {
    public static void main(String[] args) {
        /*String json = "{\n" +
                "  \"username\": \"filipe1asdasdasd\",\n" +
                "  \"password\": \"password\",\n" +
                "  \"name\": \"Ricardo Pereira\",\n" +
                "  \"email\": \"ricardo@email.com\",\n" +
                "  \"sex\": \"M\",\n" +
                "  \"birthday\": \"2000-06-19\"\n" +
                "}";
        HRClientFacadeBean hrClientFacadeBean = new HRClientFacadeBeanBean();
        try {
            hrClientFacadeBean.createClient(json);
        } catch (ClientAlreadyExistsException e) {
            e.printStackTrace();
        } catch (PersistentException e) {
            e.printStackTrace();
        }*/

        //  test same size of tokens
        /*Client c1 = null, c2 = null;

        try {
            c1 = ClientDAO.getClientByORMID("filipe1");
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        try {
            c2 = ClientDAO.getClientByORMID("filipe1asdasdasd");
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        System.out.println(c1.getToken().length());
        System.out.println(c2.getToken().length());
        */

        HRClientFacadeBean hrClientFacadeBean = new HRClientFacadeBeanBean();

        try {
            String tokenGenerated = hrClientFacadeBean.updateToken("{\"username\": \"ricardo\", \"token\": \"ricardoWI2MgKtbnjoaBRR7HCU7AoybK\"}");
            System.out.println("TOKEN=" + tokenGenerated);
        } catch (PersistentException e) {
            e.printStackTrace();
        } catch (ClientDoesNotExistException e) {
            e.printStackTrace();
        } catch (TokenIsInvalidException e) {
            e.printStackTrace();
        } catch (TokenAndUsernameInFaultException e) {
            e.printStackTrace();
        } catch (InvalidJSONException e) {
            e.printStackTrace();
        }
    }
}
