package tests;

import hrclient.BiometricData;
import hrclient.Client;
import hrclient.ClientDAO;
import hrclient.ClientFactory;
import hrclient.DiagramasPersistentManager;
import hrclient.IClient;
import org.orm.PersistentException;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.orm.PersistentSession;

public class DatabaseTest {
    public static void main(String[] args) {
        try {
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
            
            PersistentSession session = DiagramasPersistentManager.instance().getSession();
            
            Query q = session.createQuery("select C.username from Client C where username = :username");
            q.setParameter("username", "josepereira");
            Client results = (Client) q.list().get(0);
            
            System.err.println(results.getUsername());
            
            
            /*for(Client c : results){
                //System.err.println(c.biometricDatas);
                for(BiometricData d : c.biometricDatas.toArray()){
                    System.out.println(d.getBMI());
                }
            }*/
           
        } catch (PersistentException ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
