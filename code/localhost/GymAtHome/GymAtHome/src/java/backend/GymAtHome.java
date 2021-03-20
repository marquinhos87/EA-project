package backend;

import beans.GymAtHomeBeanLocal;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

public class GymAtHome {

    private final GymAtHomeBeanLocal gymAtHomeBean = lookupGymAtHomeBeanLocal();

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
     * @return
     * @throws IOException if something fails on trying contact with external services.
     */
    public String editClientProfile(String infoAsJSON) throws IOException {
        return gymAtHomeBean.editClientProfile(infoAsJSON);
    }

    /**
     * Retrieves tha actual week of plan of that Client.
     * 
     * @param usernameAsJSON
     * @return 
     * @throws IOException if something fails on trying contact with external services.
     */
    public String editPersonalTrainertProfile(String usernameAsJSON) throws IOException {
        return gymAtHomeBean.editPersonalTrainerProfile(usernameAsJSON);
    }

    /**
     * Retrieves tha actual week of plan of that Client.
     * 
     * @param usernameAndWeekAsJSON
     * @return
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getWeekByClient(String usernameAndWeekAsJSON) throws IOException {
        return gymAtHomeBean.getWeekByClient(usernameAndWeekAsJSON);
    }

    /**
     * Retrieves the PersonalTrainers that corresponded to the filters given by Client.
     *
     * @param usernameAndWeekAsJSON
     * @return 
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getWeekByPersonalTrainer(String usernameAndWeekAsJSON) throws IOException {
        return gymAtHomeBean.getWeekByPersonalTrainer(usernameAndWeekAsJSON);
    }

    /**
     * Retrieves the most recent BioMetric data from a Client.
     * 
     * @param usernameAsJSON
     * @return
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getBiometricData(String usernameAsJSON) throws IOException {
        return gymAtHomeBean.getBiometricData(usernameAsJSON);
    }

    /**
     * Submit a classification to a PersonalTrainer given by a Client.
     * 
     * @param usernameAndClassificationAsJSON
     * @return 
     * @throws IOException if something fails on trying contact with external services.
     */
    public String submitClassification(String usernameAndClassificationAsJSON) throws IOException {
        return gymAtHomeBean.submitClassification(usernameAndClassificationAsJSON);
    }

    /**
     * Client submit that as completed a workout.
     * 
     * @param usernameAndWorkoutIdAsJSON
     * @return 
     * @throws IOException if something fails on trying contact with external services.
     */
    public String finishWorkout(String usernameAndWorkoutIdAsJSON) throws IOException {
        return gymAtHomeBean.finishWorkout(usernameAndWorkoutIdAsJSON);
    }

    /**
     * Retrieves all Clients of a PersonalTrainer.
     * 
     * @param usernameAsJSON
     * @return 
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getPersonalTrainerClients(String usernameAsJSON) throws IOException {
        return gymAtHomeBean.getPersonalTrainerClients(usernameAsJSON);
    }

    /**
     * Request submitted by a Client to a PersonalTrainer.
     * 
     * @param requestInfoAsJSON
     * @return 
     * @throws IOException if something fails on trying contact with external services.
     */
    public String submitRequest(String requestInfoAsJSON) throws IOException {
        return gymAtHomeBean.submitRequest(requestInfoAsJSON);
    }

    /**
     * Create and add a week of workouts to a plan of a Client.
     * 
     * @param weekAsJson
     * @return 
     * @throws IOException if something fails on trying contact with external services.
     */
    public String createWeek(String weekAsJson) throws IOException {
        return gymAtHomeBean.createWeek(weekAsJson);
    }

    /**
     * Response given by a PersonalTrainer to a Request submitted to him by a Client.
     * 
     * @param requestIdAndResponseAsJSON
     * @return 
     * @throws IOException if something fails on trying contact with external services.
     */
    public String replyToRequest(String requestIdAndResponseAsJSON) throws IOException {
        return gymAtHomeBean.replyToRequest(requestIdAndResponseAsJSON);
    }
    
    /**
     * 
     * 
     * @param usernameAsJSON
     * @return
     * @throws IOException 
     */
    public String getNotificationsByClient(String usernameAsJSON) throws IOException {
        return gymAtHomeBean.getNotificationsByClient(usernameAsJSON);
    }
    
    /**
     * 
     * 
     * @param usernameAsJSON
     * @return
     * @throws IOException 
     */
    public String getNotificationsByPersonalTrainer(String usernameAsJSON) throws IOException {
        return gymAtHomeBean.getNotificationsByPersonalTrainer(usernameAsJSON);
    }
    
    /**
     * 
     * 
     * @param usernameAndIdsAsJSON
     * @return
     * @throws IOException 
     */
    public String markAsReadNotificationsByClient(String usernameAndIdsAsJSON) throws IOException {
        return gymAtHomeBean.markAsReadNotificationsByClient(usernameAndIdsAsJSON);
    }
    
    /**
     * 
     * 
     * @param usernameAndIdsAsJSON
     * @return
     * @throws IOException 
     */
    public String markAsReadNotificationsByPersonalTrainer(String usernameAndIdsAsJSON) throws IOException {
        return gymAtHomeBean.markAsReadNotificationsByPersonalTrainer(usernameAndIdsAsJSON);
    }

    /**
     * 
     * 
     * @param data
     * @return
     * @throws IOException
     * @throws Exception 
     */
    public String dropdbs(String data) throws IOException, Exception {
        return gymAtHomeBean.dropdbs(data);
    }

    /**
     * 
     * 
     * @param data
     * @return
     * @throws IOException
     * @throws Exception 
     */
    public String createdbs(String data) throws IOException, Exception {
        return gymAtHomeBean.createdbs(data);
    }
    
    public String listClientRequestsByPersonalTrainer(String usernameAsJSON) throws IOException{
        return gymAtHomeBean.listClientRequestsByPersonalTrainer(usernameAsJSON);
    }
    
    public String listClientRequestsByClient(String usernameAsJSON) throws IOException{
        return gymAtHomeBean.listClientRequestsByClient(usernameAsJSON);
    }
    
    public String getUsernameByRequestId(String requestIdAsJson) throws IOException{
        return gymAtHomeBean.getUsernameByRequestId(requestIdAsJson);
    }

    public String hasSubmittedClassification(String data) throws IOException {
        return gymAtHomeBean.hasSubmittedClassification(data);
    }
}