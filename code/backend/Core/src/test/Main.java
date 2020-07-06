package test;

import beans.CoreFacadeBean;
import core.CoreFacade;
import exceptions.ClientAlreadyExistsException;
import exceptions.PersonalTrainerAlreadyExistsException;
import org.orm.PersistentException;

public class Main {
    public static void main(String[] args) {
        CoreFacadeBean bean = new CoreFacadeBean();

        //Create Client
        String client = "{ \"username\": \"marques\",\"token\":\"marquesapsiubvqpribv\"}";
        try {
            bean.createClient(client);
        } catch (ClientAlreadyExistsException | PersistentException e) {
            e.printStackTrace();
        }

        //Create PersonalTrainer
        String personalTrainer = "{ \"username\": \"marques\",\"token\":\"marquesapsiubvqpribv\"}";
        try {
            bean.createPersonalTrainer(personalTrainer);
        } catch (PersistentException | PersonalTrainerAlreadyExistsException e) {
            e.printStackTrace();
        }

        try {
            CoreFacade.getSession().close();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }
}
