package backend;

import beans.GymAtHomeBean;
import static beans.GymAtHomeBean.clients;
import beans.GymAtHomeBeanLocal;
import exceptions.GymAtHomeException;
import exceptions.JsonKeyInFaultException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import okhttp3.Response;
import utils.Http;

public class GymAtHome {

    GymAtHomeBeanLocal gymAtHomeBean = lookupGymAtHomeBeanLocal();

    private static GymAtHome gymAtHome;

    /**
     * GymAtHome empty Constructor.
     */
    private GymAtHome() {

    }

    /**
     * Returns the instance itself.
     * 
     * @return The instance itself
     */
    public static GymAtHome getInstance() {
            if (gymAtHome == null) {
                    gymAtHome = new GymAtHome();
            }
            return gymAtHome;
    }
    
    /**
     * Make the lookup for the SessionBean GymAtHomeBean.
     * 
     * @return A local Bean about the GymAtHome
     */
    private GymAtHomeBeanLocal lookupGymAtHomeBeanLocal() {
        try {
            Context c = new InitialContext();
            return (GymAtHomeBeanLocal) c.lookup("java:global/GymAtHome/GymAtHomeBean!beans.GymAtHomeBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    /**
     * Create a Client with the info submitted.
     * 
     * @param infoClientAsJSON
     * @return 
     * @throws IOException if something fails on trying contact with external services.
     */
    public String createClient(String infoClientAsJSON) throws IOException {
            return gymAtHomeBean.createClient(infoClientAsJSON);
    }

    /**
     * Create a PersonalTrainer with the info submitted.
     * 
     * @param infoAsJSON
     * @return 
     * @throws IOException if something fails on trying contact with external services.
     */
    public String loginClient(String infoAsJSON) throws IOException {
            return gymAtHomeBean.loginClient(infoAsJSON);
    }

    /**
     * Authenticate Client with the credentials provided.
     * 
     * @param usernameAsJSON
     * @return
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getClientProfileByClient(String usernameAsJSON) throws IOException {
            return gymAtHomeBean.getClientProfileByClient(usernameAsJSON);
    }
    
    /**
     * Authenticate Client with the credentials provided.
     * 
     * @param usernameAsJSON
     * @return
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getClientProfileByPersonalTrainer(String usernameAsJSON) throws IOException {
            return gymAtHomeBean.getClientProfileByPersonalTrainer(usernameAsJSON);
    }

    /**
     * Authenticate PersonalTrainer with the credentials provided.
     * 
     * @param filtersAsJSON
     * @return 
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getPersonalTrainers(String filtersAsJSON) throws IOException {
            return gymAtHomeBean.getPersonalTrainers(filtersAsJSON);
    }

    /**
     * Get a Client profile by Client username.
     * 
     * @param infoPTAsJSON
     * @return 
     * @throws IOException if something fails on trying contact with external services.
     */
    public String createPersonalTrainer(String infoPTAsJSON) throws IOException {
            return gymAtHomeBean.createPersonalTrainer(infoPTAsJSON);
    }

    /**
     * Get a PersonalTrainer profile by PersonalTrainer username.
     * 
     * @param infoAsJSON
     * @return 
     * @throws IOException if something fails on trying contact with external services.
     */
    public String loginPersonalTrainer(String infoAsJSON) throws IOException {
            return gymAtHomeBean.loginPersonalTrainer(infoAsJSON);
    }

    /**
     * Edit Client info with new info given.
     * 
     * @param usernameAsJSON
     * @return
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getPersonalTrainerProfileByClient(String usernameAsJSON) throws IOException {
            return gymAtHomeBean.getPersonalTrainerProfileByClient(usernameAsJSON);
    }
    
    /**
     * Edit Client info with new info given.
     * 
     * @param usernameAsJSON
     * @return
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getPersonalTrainerProfileByPersonalTrainer(String usernameAsJSON) throws IOException {
            return gymAtHomeBean.getPersonalTrainerProfileByPersonalTrainer(usernameAsJSON);
    }

    /**
     * Edit PersonalTrainer info with new info given.
     * 
     * @param infoAsJSON
     * @throws IOException if something fails on trying contact with external services.
     */
    public String editClientProfile(String infoAsJSON) throws IOException {
            return gymAtHomeBean.editClientProfile(infoAsJSON);
    }

    /**
     * Retrieves tha actual week of plan of that Client.
     * 
     * @param usernameAsJSON
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    public String editPersonalTrainertProfile(String usernameAsJSON) throws IOException, GymAtHomeException {
            return gymAtHomeBean.editPersonalTrainerProfile(usernameAsJSON);
    }

    /**
     * Retrieves tha actual week of plan of that Client.
     * 
     * @param usernameAndWeekAsJSON
     * @return
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    public String getPlanByClient(String usernameAndWeekAsJSON) throws IOException, GymAtHomeException {
            return gymAtHomeBean.getPlanByClient(usernameAndWeekAsJSON);
    }

    /**
     * Retrieves the PersonalTrainers that corresponded to the filters given by Client.
     *
     * @param usernameAndWeekAsJSON
     * @return 
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    public String getPlanByPersonalTrainer(String usernameAndWeekAsJSON) throws IOException, GymAtHomeException {
            return gymAtHomeBean.getPlanByPersonalTrainer(usernameAndWeekAsJSON);
    }

    /**
     * Retrieves the most recent BioMetric data from a Client.
     * 
     * @param usernameAsJSON
     * @return
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    public String getBiometricData(String usernameAsJSON) throws IOException, GymAtHomeException {
            return gymAtHomeBean.getBiometricData(usernameAsJSON);
    }

    /**
     * Submit a classification to a PersonalTrainer given by a Client.
     * 
     * @param usernameAndClassificationAsJSON
     * @return 
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    public String submitClassification(String usernameAndClassificationAsJSON) throws IOException, GymAtHomeException {
            return gymAtHomeBean.submitClassification(usernameAndClassificationAsJSON);
    }

    /**
     * Client submit that as completed a workout.
     * 
     * @param usernameAndWorkoutIdAsJSON
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    public String finishWorkout(String usernameAndWorkoutIdAsJSON) throws IOException, GymAtHomeException {
            return gymAtHomeBean.finishWorkout(usernameAndWorkoutIdAsJSON);
    }

    /**
     * Retrieves all Clients of a PersonalTrainer.
     * 
     * @param usernameAsJSON
     * @return 
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    public String getPersonalTrainerClients(String usernameAsJSON) throws IOException, GymAtHomeException {
            return gymAtHomeBean.getPersonalTrainerClients(usernameAsJSON);
    }

    /**
     * Request submitted by a Client to a PersonalTrainer.
     * 
     * @param requestInfoAsJSON
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    public String submitRequest(String requestInfoAsJSON) throws IOException, GymAtHomeException {
            return gymAtHomeBean.submitRequest(requestInfoAsJSON);
    }

    /**
     * Create and add a week of workouts to a plan of a Client.
     * 
     * @param weekAsJson
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    public String createWeek(String weekAsJson) throws IOException, GymAtHomeException {
            return gymAtHomeBean.createWeek(weekAsJson);
    }

    /**
     * Response given by a PersonalTrainer to a Request submitted to him by a Client.
     * 
     * @param requestIdAndResponseAsJSON
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    public String replyToRequest(String requestIdAndResponseAsJSON) throws IOException, GymAtHomeException {
            return gymAtHomeBean.replyToRequest(requestIdAndResponseAsJSON);
    }

    public String dropdbs(String data) throws IOException, Exception {
        return gymAtHomeBean.dropdbs(data);
    }

    public String createdbs(String data) throws IOException, Exception {
        return gymAtHomeBean.createdbs(data);
    }
}