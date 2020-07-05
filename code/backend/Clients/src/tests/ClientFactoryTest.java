package tests;

import hrclient.ClientFactory;
import hrclient.IClient;

import java.util.Date;

public class ClientFactoryTest {
    public static void main(String[] args) {
        ClientFactory factory = ClientFactory.getInstance();

        IClient c1 = factory.createIClient("Client");

        c1.setUsername("username");
        c1.setBirthday(new Date(new Date().getTime()));
        c1.setEmail("user@email.com");

        System.out.println(c1);


        IClient c2 = factory.createIClient("ClientPremium");  //  este tipo não existe
        try {
            c2.setUsername("isNull");
        }catch (Exception e){
            System.err.println("Type of user does not exists!");
            e.printStackTrace();
        }
    }
}
