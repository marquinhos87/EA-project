package tests;

import hrclient.Client;
import hrclient.ClientDAO;
import hrclient.ClientFactory;
import hrclient.IClient;
import org.orm.PersistentException;

import java.util.Date;

public class DatabaseTest {
    public static void main(String[] args) {
        /*ClientFactory factory = ClientFactory.getInstance();
        IClient c1 = factory.createIClient("Client");

        c1.setUsername("username");
        c1.setBirthday(new Date(new Date().getTime()));
        c1.setEmail("user@email.com");

        try {
            ClientDAO.save((Client) c1);
        } catch (PersistentException e) {
            e.printStackTrace();
        }*/
    }
}
