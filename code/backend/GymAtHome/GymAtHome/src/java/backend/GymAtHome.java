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
     * @param infoClientAsJSON All Client info as json string.
     * @return A Client in json string.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String createClient(String infoClientAsJSON) throws IOException {
        return gymAtHomeBean.createClient(infoClientAsJSON);
    }

    /**
     * Authenticate Client with the credentials provided.
     * 
     * @param infoAsJSON Client credentials as json string.
     * @return A string with login status.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String loginClient(String infoAsJSON) throws IOException {
        return gymAtHomeBean.loginClient(infoAsJSON);
    }

    /**
     * Get a Client profile by Client username.
     * 
     * @param usernameAsJSON Client username as json string.
     * @return Client info.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getClientProfileByClient(String usernameAsJSON) throws IOException {
        return gymAtHomeBean.getClientProfileByClient(usernameAsJSON);
    }
    
    /**
     * Get a Client profile by Client username.
     * 
     * @param usernameAsJSON Client username as json string.
     * @return Client info.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getClientProfileByPersonalTrainer(String usernameAsJSON) throws IOException {
        return gymAtHomeBean.getClientProfileByPersonalTrainer(usernameAsJSON);
    }

    /**
     * Retrieves the PersonalTrainers that corresponded to the filters given by Client.
     * 
     * @param filtersAsJSON Filters introduced by Client as json string.
     * @return PersonalTrainers filtered.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getPersonalTrainers(String filtersAsJSON) throws IOException {
        return gymAtHomeBean.getPersonalTrainers(filtersAsJSON);
    }

    /**
     * Create a PersonalTrainer with the info submitted.
     * 
     * @param infoPTAsJSON All PersonalTrainer info as json string.
     * @return A PersonalTrainer in json string.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String createPersonalTrainer(String infoPTAsJSON) throws IOException {
        return gymAtHomeBean.createPersonalTrainer(infoPTAsJSON);
    }

    /**
     * Authenticate PersonalTrainer with the credentials provided.
     * 
     * @param infoAsJSON PersonalTrainer credentials as json string.
     * @return A string with login status.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String loginPersonalTrainer(String infoAsJSON) throws IOException {
        return gymAtHomeBean.loginPersonalTrainer(infoAsJSON);
    }

    /**
     * Get a PersonalTrainer profile by PersonalTrainer username.
     * 
     * @param usernameAsJSON PersonalTrainer username as json string.
     * @return PersonalTrainer info.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getPersonalTrainerProfileByClient(String usernameAsJSON) throws IOException {
        return gymAtHomeBean.getPersonalTrainerProfileByClient(usernameAsJSON);
    }
    
    /**
     * Get a PersonalTrainer profile by PersonalTrainer username.
     * 
     * @param usernameAsJSON PersonalTrainer username as json string.
     * @return PersonalTrainer info.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getPersonalTrainerProfileByPersonalTrainer(String usernameAsJSON) throws IOException {
        return gymAtHomeBean.getPersonalTrainerProfileByPersonalTrainer(usernameAsJSON);
    }

    /**
     * Edit Client info with new info given.
     * 
     * @param infoAsJSON Client new infos as json string.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String editClientProfile(String infoAsJSON) throws IOException {
        return gymAtHomeBean.editClientProfile(infoAsJSON);
    }

    /**
     * Edit PersonalTrainer info with new info given.
     * 
     * @param usernameAsJSON PersonalTrainer new infos as json string.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String editPersonalTrainertProfile(String usernameAsJSON) throws IOException {
        return gymAtHomeBean.editPersonalTrainerProfile(usernameAsJSON);
    }

    /**
     * Retrieves the current week of plan of that Client.
     * 
     * @param usernameAndWeekAsJSON Client username and current week of plan as json string.
     * @return Client plan (actual week of the plan).
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getWeekByClient(String usernameAndWeekAsJSON) throws IOException {
        return gymAtHomeBean.getWeekByClient(usernameAndWeekAsJSON);
    }

    /**
     * Retrieves the current week of plan of that Client.
     *
     * @param usernameAndWeekAsJSON Client username and current week of plan as json string.
     * @return Client plan (actual week of the plan).
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getWeekByPersonalTrainer(String usernameAndWeekAsJSON) throws IOException {
        return gymAtHomeBean.getWeekByPersonalTrainer(usernameAndWeekAsJSON);
    }

    /**
     * Retrieves the most recent BioMetric data from a Client.
     * 
     * @param usernameAsJSON Client username as json string.
     * @return Client BioMetric data.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getBiometricData(String usernameAsJSON) throws IOException {
        return gymAtHomeBean.getBiometricData(usernameAsJSON);
    }

    /**
     * Submit a classification to a PersonalTrainer given by a Client.
     *
     * @param usernameAndClassificationAsJSON PersonalTrainer username and classification attributed by Client as a json string.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String submitClassification(String usernameAndClassificationAsJSON) throws IOException {
        return gymAtHomeBean.submitClassification(usernameAndClassificationAsJSON);
    }

    /**
     * Client submit that as completed a workout.
     * 
     * @param usernameAndWorkoutIdAsJSON Client username and Workout Id as a json string.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String finishWorkout(String usernameAndWorkoutIdAsJSON) throws IOException {
        return gymAtHomeBean.finishWorkout(usernameAndWorkoutIdAsJSON);
    }

    /**
     * Retrieves all Clients of a PersonalTrainer.
     * 
     * @param usernameAsJSON PersonalTrainer username.
     * @return PersonalTrainer clients.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getPersonalTrainerClients(String usernameAsJSON) throws IOException {
        return gymAtHomeBean.getPersonalTrainerClients(usernameAsJSON);
    }

    /**
     * Request submitted by a Client to a PersonalTrainer.
     * 
     * @param requestInfoAsJSON Request info submitted by Client.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String submitRequest(String requestInfoAsJSON) throws IOException {
        return gymAtHomeBean.submitRequest(requestInfoAsJSON);
    }

    /**
     * Create and add a week of workouts to a plan of a Client.
     * 
     * @param weekAsJson Week info created by PersonalTrainer.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String createWeek(String weekAsJson) throws IOException {
        return gymAtHomeBean.createWeek(weekAsJson);
    }

    /**
     * Response given by a PersonalTrainer to a Request submitted to him by a Client.
     * 
     * @param requestIdAndResponseAsJSON Request Id and Response by PersonalTrainer to the request.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String replyToRequest(String requestIdAndResponseAsJSON) throws IOException {
        return gymAtHomeBean.replyToRequest(requestIdAndResponseAsJSON);
    }
    
    /**
     * Get all notifications of a Client.
     * 
     * @param usernameAsJSON Client username.
     * @return All Client notifications.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getNotificationsByClient(String usernameAsJSON) throws IOException {
        return gymAtHomeBean.getNotificationsByClient(usernameAsJSON);
    }
    
    /**
     * Get all notifications of a Personal Trainer.
     * 
     * @param usernameAsJSON Personal Trainer username.
     * @return All Personal Trainer notifications.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getNotificationsByPersonalTrainer(String usernameAsJSON) throws IOException {
        return gymAtHomeBean.getNotificationsByPersonalTrainer(usernameAsJSON);
    }
    
    /**
     * Mark some client notifications as read.
     * 
     * @param usernameAndIdsAsJSON Client username and notifications ids.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String markAsReadNotificationsByClient(String usernameAndIdsAsJSON) throws IOException {
        return gymAtHomeBean.markAsReadNotificationsByClient(usernameAndIdsAsJSON);
    }
    
    /**
     * Mark some personal trainer notifications as read.
     * 
     * @param usernameAndIdsAsJSON Personal Trainer username and notifications ids.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String markAsReadNotificationsByPersonalTrainer(String usernameAndIdsAsJSON) throws IOException {
        return gymAtHomeBean.markAsReadNotificationsByPersonalTrainer(usernameAndIdsAsJSON);
    }

    /**
     * Drop schemas of databases.
     * 
     * @param tokenAsJson Admin token.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     * @throws Exception if something fails.
     */
    public String dropdbs(String data) throws IOException, Exception {
        return gymAtHomeBean.dropdbs(data);
    }

    /**
     * Create schemas of databases.
     * 
     * @param tokenAsJson Admin token.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     * @throws Exception if something fails.
     */
    public String createdbs(String data) throws IOException, Exception {
        return gymAtHomeBean.createdbs(data);
    }
    
    /**
     * Get all pending requests of a Personal Trainer by Personal Trainer username.
     * 
     * @param usernameAsJson Personal Trainer username.
     * @return All pending requests of a Personal Trainer.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String listClientRequestsByPersonalTrainer(String usernameAsJSON) throws IOException{
        return gymAtHomeBean.listClientRequestsByPersonalTrainer(usernameAsJSON);
    }
    
    /**
     * Get all Client requests by Client username.
     * 
     * @param usernameAsJSON Client username.
     * @return All Client requests.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String listClientRequestsByClient(String usernameAsJSON) throws IOException{
        return gymAtHomeBean.listClientRequestsByClient(usernameAsJSON);
    }
    
    /**
     * Get Client username by Request id.
     * 
     * @param requestIdAsJson Request id.
     * @return Client username.
     * @throws IOException if something fails on trying contact with external services.
     */
    public String getUsernameByRequestId(String requestIdAsJson) throws IOException{
        return gymAtHomeBean.getUsernameByRequestId(requestIdAsJson);
    }

    /**
     * The state of Client submitted a classification.
     * 
     * @param data Client username.
     * @return True if submitted classification, otherwise False. 
     * @throws IOException if something fails on trying contact with external services.
     */
    public String hasSubmittedClassification(String data) throws IOException {
        return gymAtHomeBean.hasSubmittedClassification(data);
    }
}