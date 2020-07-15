package tests;

import hrclient.*;
import org.orm.PersistentException;

import java.util.Set;
import java.util.TreeSet;

public class ClientAndBiometricDataRelationTest {
    public static void main(String[] args) {
        Client c = null;
        try {
            c = ClientDAO.getClientByORMID("username");
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        BiometricData bd = new BiometricData();
        bd.setWeight(50);

        c.biometricDatas.add(bd);

        c.setSex("M");

        try {
            ClientDAO.save(c);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }
}
