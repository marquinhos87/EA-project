package beans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.ConcretePlanBuilder;
import core.PlanDirector;
import core.data.client.Client;
import core.data.client.ClientDAO;
import core.data.personaltrainer.PersonalTrainer;
import core.data.personaltrainer.PersonalTrainerDAO;
import exceptions.ClientAlreadyExistsException;
import exceptions.ClientDontExistsException;
import exceptions.PersonalTrainerAlreadyExistsException;
import exceptions.PersonalTrainerDontExistsException;
import org.orm.PersistentException;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;

@Local(CoreFacadeBeanLocal.class)
@Stateless(name = "CoreFacadeEJB")
public class CoreFacadeBean implements CoreFacadeBeanLocal{

    private final Gson gson;
    private PlanDirector planDirector = new PlanDirector(new ConcretePlanBuilder());

    public CoreFacadeBean() {
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
    }

    @Override
    public void createClient(String usernameAsJson) throws PersistentException, ClientAlreadyExistsException {
        Client c = gson.fromJson(usernameAsJson, Client.class);
        if (ClientDAO.getClientByORMID(c.getUsername()) != null)
            throw new ClientAlreadyExistsException(c.getUsername());
        ClientDAO.save(c);
        System.err.println("Client saved to database...");
    }

    @Override
    public void createPersonalTrainer(String usernameAsJson) throws PersistentException, PersonalTrainerAlreadyExistsException {
        PersonalTrainer pt = gson.fromJson(usernameAsJson, PersonalTrainer.class);
        if (PersonalTrainerDAO.getPersonalTrainerByORMID(pt.getUsername()) != null)
            throw new PersonalTrainerAlreadyExistsException(pt.getUsername());
        PersonalTrainerDAO.save(pt);
        System.err.println("PersonalTrainer saved to database...");
    }

    @Override
    public void updateTokenClient(String usernameAndTokenAsJson) throws PersistentException, ClientDontExistsException {
        Client c = gson.fromJson(usernameAndTokenAsJson, Client.class);
        if (ClientDAO.getClientByORMID(c.getUsername()) == null)
            throw new ClientDontExistsException(c.getUsername());
        ClientDAO.save(c);
    }

    @Override
    public void updateTokenPersonalTrainer(String usernameAndTokenAsJson) throws PersistentException, PersonalTrainerDontExistsException {
        PersonalTrainer pt = gson.fromJson(usernameAndTokenAsJson, PersonalTrainer.class);
        if (PersonalTrainerDAO.getPersonalTrainerByORMID(pt.getUsername()) == null)
            throw new PersonalTrainerDontExistsException(pt.getUsername());
        PersonalTrainerDAO.save(pt);
    }

    @Override
    public String getPlan(String usernameAndWeekAsJSON) {
        return null;
    }

    @Override
    public void finishWorkout(String usernameAndWorkoutIdAsJSON) {

    }

    @Override
    public void createWeek(String weekAsJson) {

    }
}
