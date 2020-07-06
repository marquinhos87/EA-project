package beans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import core.ConcretePlanBuilder;
import core.CoreFacade;
import core.PlanDirector;
import core.Client;
import core.ClientDAO;
import core.PersonalTrainer;
import core.PersonalTrainerDAO;
import core.Plan;
import core.Week;
import exceptions.*;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import utils.Utils;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.*;

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
        PersistentSession session = CoreFacade.getSession();
        Client c = gson.fromJson(usernameAsJson, Client.class);
        if (ClientDAO.getClientByORMID(session,c.getUsername()) != null)
            throw new ClientAlreadyExistsException(c.getUsername());
        ClientDAO.save(c);
        session.flush();
        System.err.println("Client saved to database...");
    }

    @Override
    public void createPersonalTrainer(String usernameAsJson) throws PersistentException, PersonalTrainerAlreadyExistsException {
        PersistentSession session = CoreFacade.getSession();
        PersonalTrainer pt = gson.fromJson(usernameAsJson, PersonalTrainer.class);
        if (PersonalTrainerDAO.getPersonalTrainerByORMID(session,pt.getUsername()) != null)
            throw new PersonalTrainerAlreadyExistsException(pt.getUsername());
        PersonalTrainerDAO.save(pt);
        session.flush();
        System.err.println("PersonalTrainer saved to database...");
    }

    @Override
    public void updateTokenClient(String usernameAndTokenAsJson) throws PersistentException, ClientDontExistsException, JsonKeyInFaultException, InvalidTokenException {
        JsonObject json = Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("oldToken", "newToken", "username"));

        Client client;
        if ((client = ClientDAO.getClientByORMID(CoreFacade.getSession(),json.get("username").getAsString())) == null)
            throw new ClientDontExistsException(json.get("username").getAsString());
        if (!client.getToken().equals(json.get("oldToken")))
            throw new InvalidTokenException(json.get("oldToken").getAsString());

        client.setToken(json.get("newToken").getAsString());
        ClientDAO.save(client);
        System.err.println("Client token updated");
    }

    @Override
    public void updateTokenPersonalTrainer(String usernameAndTokenAsJson) throws PersistentException, PersonalTrainerDontExistsException, JsonKeyInFaultException, InvalidTokenException {
        JsonObject json = Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("oldToken", "newToken", "username"));

        PersonalTrainer pt;
        if ((pt = PersonalTrainerDAO.getPersonalTrainerByORMID(CoreFacade.getSession(),json.get("username").getAsString())) == null)
            throw new PersonalTrainerDontExistsException(json.get("username").getAsString());
        if (!pt.getToken().equals(json.get("oldToken")))
            throw new InvalidTokenException(json.get("oldToken").getAsString());

        pt.setToken(json.get("newToken").getAsString());
        PersonalTrainerDAO.save(pt);
        System.err.println("PersonalTrainer token updated");
    }

    @Override
    public String getWeekByClient(String usernameAndWeekAsJSON) throws JsonKeyInFaultException, PersistentException, ClientDontExistsException, InvalidTokenException {
        JsonObject json = Utils.validateJson(gson, usernameAndWeekAsJSON, Arrays.asList("token", "username", "week"));

        Client c;
        if ((c = ClientDAO.getClientByORMID(CoreFacade.getSession(),json.get("username").getAsString())) == null)
            throw new ClientDontExistsException(json.get("username").getAsString());
        if (!c.getToken().equals(json.get("token").getAsString()))
            throw new InvalidTokenException(json.get("token").getAsString());

        Plan plan = c.getPlan();
        Iterator it = plan.weeks.getIterator();
        Week week = null;
        for( int i = 0 ; i < json.get("week").getAsInt() ; week = (Week) it.next());

        return gson.toJson(week);
    }

    @Override
    public String getWeekByPersonalTrainer(String usernameAndWeekAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerDontExistsException, InvalidTokenException {
        JsonObject json = Utils.validateJson(gson, usernameAndWeekAsJSON, Arrays.asList("token", "username", "week"));

        PersonalTrainer pt;
        if ((pt = PersonalTrainerDAO.getPersonalTrainerByORMID(CoreFacade.getSession(),json.get("username").getAsString())) == null)
            throw new PersonalTrainerDontExistsException(json.get("username").getAsString());
        if (!pt.getToken().equals(json.get("token").getAsString()))
            throw new InvalidTokenException(json.get("token").getAsString());

        // TODO
        return null;
    }

    @Override
    public void finishWorkout(String usernameAndWorkoutIdAsJSON) throws PersistentException, ClientDontExistsException, InvalidTokenException, JsonKeyInFaultException {
        JsonObject json = Utils.validateJson(gson, usernameAndWorkoutIdAsJSON, Arrays.asList("token", "username", "workoutId"));

        Client c;
        if ((c = ClientDAO.getClientByORMID(CoreFacade.getSession(),json.get("username").getAsString())) == null)
            throw new ClientDontExistsException(json.get("username").getAsString());
        if (!c.getToken().equals(json.get("token").getAsString()))
            throw new InvalidTokenException(json.get("token").getAsString());

        // TODO
    }

    @Override
    public void createWeek(String weekAsJson) throws JsonKeyInFaultException, PersonalTrainerDontExistsException, PersistentException, InvalidTokenException {
        JsonObject json = Utils.validateJson(gson, weekAsJson, Arrays.asList("token", "planId", "week"));

        PersonalTrainer pt;
        if ((pt = PersonalTrainerDAO.getPersonalTrainerByORMID(CoreFacade.getSession(),json.get("username").getAsString())) == null)
            throw new PersonalTrainerDontExistsException(json.get("username").getAsString());
        if (!pt.getToken().equals(json.get("token").getAsString()))
            throw new InvalidTokenException(json.get("token").getAsString());

        //planDirector.buildPlan(json.get("planId").getAsInt(),json.get("week").getAsJsonObject());
    }
}
