package hrclient;

import org.orm.PersistentException;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        /*Client c = new Client();
        c.setUsername("josepereira");
        c.setPassword("password");
        c.setName("José Pereira");
        c.setEmail("jose@email.com");
        c.setBirthday(new Date(new Date().getTime()));
        c.setSex("m");
        c.setToken("sds2232sdsd2323sdsd2323ssds2323ds");
        try {
            ClientDAO.save(c);
        } catch (PersistentException e) {
            e.printStackTrace();
        }*/

        Client c = null;

        try {
            c = ClientDAO.getClientByORMID("josepereira");
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        System.out.println(c);
    }
}
