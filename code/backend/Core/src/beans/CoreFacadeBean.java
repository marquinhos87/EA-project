package beans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import core.*;
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
    public void createUserToken(String usernameAsJson) throws PersistentException, UserAlreadyExistsException {
        PersistentSession session = CoreFacade.getSession();

        UserToken ut = gson.fromJson(usernameAsJson, UserToken.class);
        if (UserTokenDAO.getUserTokenByORMID(session,ut.getUsername()) != null)
            throw new UserAlreadyExistsException(ut.getUsername());
        UserTokenDAO.save(ut);
        session.flush();

        System.err.println("User saved to database...");
    }

    @Override
    public void updateToken(String usernameAndTokenAsJson) throws PersistentException, JsonKeyInFaultException, InvalidTokenException, UserTokenDontExistsException {
        JsonObject json = Utils.validateJson(gson, usernameAndTokenAsJson, Arrays.asList("oldToken", "newToken", "username"));

        String username = json.get("username").getAsString();
        PersistentSession session = CoreFacade.getSession();
        UserToken ut = Utils.validateToken(session,username,json.get("oldToken").getAsString());

        ut.setToken(json.get("newToken").getAsString());
        UserTokenDAO.save(ut);
        session.flush();

        System.err.println("User token updated");
    }

    @Override
    public String getWeekByClient(String usernameAndWeekAsJSON) throws JsonKeyInFaultException, PersistentException, InvalidTokenException, UserTokenDontExistsException {
        JsonObject json = Utils.validateJson(gson, usernameAndWeekAsJSON, Arrays.asList("token", "username", "week"));

        String username = json.get("username").getAsString();
        PersistentSession session = CoreFacade.getSession();
        Utils.validateToken(session,username,json.get("token").getAsString());

        Client c = ClientDAO.getClientByORMID(session,username);
        Plan plan = c.getPlan();
        Iterator it = plan.weeks.getIterator();
        Week week = null;
        for( int i = 0 ; i < json.get("week").getAsInt() ; week = (Week) it.next());

        return gson.toJson(week);
    }

    @Override
    public String getWeekByPersonalTrainer(String usernameAndWeekAsJSON) throws JsonKeyInFaultException, PersistentException, InvalidTokenException, UserTokenDontExistsException {
        JsonObject json = Utils.validateJson(gson, usernameAndWeekAsJSON, Arrays.asList("token", "username", "week"));

        String username = json.get("username").getAsString();
        PersistentSession session = CoreFacade.getSession();
        Utils.validateToken(session,username,json.get("token").getAsString());

        // TODO
        return null;
    }

    @Override
    public void finishWorkout(String usernameAndWorkoutIdAsJSON) throws PersistentException, InvalidTokenException, JsonKeyInFaultException, UserTokenDontExistsException {
        JsonObject json = Utils.validateJson(gson, usernameAndWorkoutIdAsJSON, Arrays.asList("token", "username", "workoutId"));

        String username = json.get("username").getAsString();
        PersistentSession session = CoreFacade.getSession();
        Utils.validateToken(session,username,json.get("token").getAsString());

        // TODO
    }

    @Override
    public void createWeek(String weekAsJson) throws JsonKeyInFaultException, PersistentException, InvalidTokenException, UserTokenDontExistsException {
        JsonObject json = Utils.validateJson(gson, weekAsJson, Arrays.asList("token", "username", "week"));

        String username = json.get("username").getAsString();
        PersistentSession session = CoreFacade.getSession();
        Utils.validateToken(session,username,json.get("token").getAsString());

        // Verify if PersonalTrainer exists and if not create a new one
        PersonalTrainer pt;
        if((pt = PersonalTrainerDAO.getPersonalTrainerByORMID(session,username)) == null) {
            pt = new PersonalTrainer();
            pt.setUsername(username);
            PersonalTrainerDAO.save(pt);
            session.flush();
        }

        // TODO
        // Verify if createWeek has called to create a new plan or to add a new week to an existing plan
        if(json.has("planId")) {
            // planDirector.build(json.get("planId").getAsInt(),json.get("week").getAsString());
        } else {
            // Create Plan and associate to PersonalTrainer and Client
            // if(!json.has("clientUsername"))
            //     throws nem JsonKeyInFaultException("clientUsername");
            // Plan plan = planDirector.buildPlan(null,json.get("week").getAsJsonObject());
            // String clientUsername = json.get("clientUsername").getAsString();
            // Client client;
            // if((client = ClientDAO.getClientByORMID(session,clientUsername)) != null)
            //     throws new ClientAlreadyHasAnPlanException(clientUsername);
            // client = new Client();
            // client.setUsername(clientUsername);
            // client.setPlan(plan);
            // ClientDAO.save(client);
            // session.flush();
        }
    }
}
