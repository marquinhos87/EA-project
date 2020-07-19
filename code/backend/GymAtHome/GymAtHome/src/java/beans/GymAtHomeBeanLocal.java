/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.IOException;
import javax.ejb.Local;

/**
 *
 * @author joaomarques
 */
@Local
public interface GymAtHomeBeanLocal {
    
    /**
     * Create a Client with the info submitted.
     * 
     * @param infoClientAsJSON All Client info as json string.
     * @return A Client in json string.
     * @throws IOException if something fails on trying contact with external services.
     */
    String createClient(String infoClientAsJSON) throws IOException;

    /**
     * Create a PersonalTrainer with the info submitted.
     * 
     * @param infoPTAsJSON All PersonalTrainer info as json string.
     * @return A PersonalTrainer in json string.
     * @throws IOException if something fails on trying contact with external services.
     */
    String createPersonalTrainer(String infoPTAsJSON) throws IOException;

    /**
     * Authenticate Client with the credentials provided.
     * 
     * @param infoAsJSON Client credentials as json string.
     * @return A string with login status.
     * @throws IOException if something fails on trying contact with external services.
     */
    String loginClient(String infoAsJSON) throws IOException;

    /**
     * Authenticate PersonalTrainer with the credentials provided.
     * 
     * @param infoAsJSON PersonalTrainer credentials as json string.
     * @return A string with login status.
     * @throws IOException if something fails on trying contact with external services.
     */
    String loginPersonalTrainer(String infoAsJSON) throws IOException;

    /**
     * Get a Client profile by Client username.
     * 
     * @param usernameAsJSON Client username as json string.
     * @return Client info.
     * @throws IOException if something fails on trying contact with external services.
     */
    String getClientProfileByClient(String usernameAsJSON) throws IOException;
    
    /**
     * Get a Client profile by Client username.
     * 
     * @param usernameAsJSON Client username as json string.
     * @return Client info.
     * @throws IOException if something fails on trying contact with external services.
     */
    String getClientProfileByPersonalTrainer(String usernameAsJSON) throws IOException;

    /**
     * Get a PersonalTrainer profile by PersonalTrainer username.
     * 
     * @param usernameAsJSON PersonalTrainer username as json string.
     * @return PersonalTrainer info.
     * @throws IOException if something fails on trying contact with external services.
     */
    String getPersonalTrainerProfileByClient(String usernameAsJSON) throws IOException;
    
    /**
     * Get a PersonalTrainer profile by PersonalTrainer username.
     * 
     * @param usernameAsJSON PersonalTrainer username as json string.
     * @return PersonalTrainer info.
     * @throws IOException if something fails on trying contact with external services.
     */
    String getPersonalTrainerProfileByPersonalTrainer(String usernameAsJSON) throws IOException;

    /**
     * Edit Client info with new info given.
     * 
     * @param infoAsJSON Client new infos as json string.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    String editClientProfile(String infoAsJSON) throws IOException;

    /**
     * Edit PersonalTrainer info with new info given.
     * 
     * @param usernameAsJSON PersonalTrainer new infos as json string.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    String editPersonalTrainerProfile(String usernameAsJSON) throws IOException;

    /**
     * Retrieves the current week of plan of that Client.
     * 
     * @param usernameAndWeekAsJSON Client username and current week of plan as json string.
     * @return Client plan (actual week of the plan).
     * @throws IOException if something fails on trying contact with external services.
     */
    String getWeekByClient(String usernameAndWeekAsJSON) throws IOException;

    /**
     * Retrieves the current week of plan of that Client.
     *
     * @param usernameAndWeekAsJSON Client username and current week of plan as json string.
     * @return Client plan (actual week of the plan).
     * @throws IOException if something fails on trying contact with external services.
     */
    String getWeekByPersonalTrainer(String usernameAndWeekAsJSON) throws IOException;

    /**
     * Retrieves the PersonalTrainers that corresponded to the filters given by Client.
     * 
     * @param filtersAsJSON Filters introduced by Client as json string.
     * @return PersonalTrainers filtered.
     * @throws IOException if something fails on trying contact with external services.
     */
    String getPersonalTrainers(String filtersAsJSON) throws IOException;

    /**
     * Retrieves the most recent BioMetric data from a Client.
     * 
     * @param usernameAsJSON Client username as json string.
     * @return Client BioMetric data.
     * @throws IOException if something fails on trying contact with external services.
     */
    String getBiometricData(String usernameAsJSON) throws IOException;

    /**
     * Submit a classification to a PersonalTrainer given by a Client.
     *
     * @param usernameAndClassificationAsJSON PersonalTrainer username and classification attributed by Client as a json string.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    String submitClassification(String usernameAndClassificationAsJSON) throws IOException;

    /**
     * Client submit that as completed a workout.
     * 
     * @param usernameAndWorkoutIdAsJSON Client username and Workout Id as a json string.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    String finishWorkout(String usernameAndWorkoutIdAsJSON) throws IOException;

    /**
     * Retrieves all Clients of a PersonalTrainer.
     * 
     * @param usernameAsJSON PersonalTrainer username.
     * @return PersonalTrainer clients.
     * @throws IOException if something fails on trying contact with external services.
     */
    String getPersonalTrainerClients(String usernameAsJSON) throws IOException;

    /**
     * Request submitted by a Client to a PersonalTrainer.
     * 
     * @param requestInfoAsJSON Request info submitted by Client.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    String submitRequest(String requestInfoAsJSON) throws IOException;

    /**
     * Create and add a week of workouts to a plan of a Client.
     * 
     * @param weekAsJson Week info created by PersonalTrainer.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    String createWeek(String weekAsJson) throws IOException;

    /**
     * Response given by a PersonalTrainer to a Request submitted to him by a Client.
     * 
     * @param requestIdAndResponseAsJSON Request Id and Response by PersonalTrainer to the request.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    String replyToRequest(String requestIdAndResponseAsJSON) throws IOException;
    
    /**
     * Get all notifications of a Client.
     * 
     * @param usernameAsJSON Client username.
     * @return All Client notifications.
     * @throws IOException if something fails on trying contact with external services.
     */
    String getNotificationsByClient(String usernameAsJSON) throws IOException;
    
    /**
     * Get all notifications of a Personal Trainer.
     * 
     * @param usernameAsJSON Personal Trainer username.
     * @return All Personal Trainer notifications.
     * @throws IOException if something fails on trying contact with external services.
     */
    String getNotificationsByPersonalTrainer(String usernameAsJSON) throws IOException;
    
    /**
     * Mark some client notifications as read.
     * 
     * @param usernameAndIdsAsJSON Client username and notifications ids.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    String markAsReadNotificationsByClient(String usernameAndIdsAsJSON) throws IOException;
    
    /**
     * Mark some personal trainer notifications as read.
     * 
     * @param usernameAndIdsAsJSON Personal Trainer username and notifications ids.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    String markAsReadNotificationsByPersonalTrainer(String usernameAndIdsAsJSON) throws IOException;
    
    /**
     * Create schemas of databases.
     * 
     * @param tokenAsJson Admin token.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     * @throws Exception if something fails.
     */
    String createdbs(String tokenAsJson) throws IOException, Exception;
    
    /**
     * Drop schemas of databases.
     * 
     * @param tokenAsJson Admin token.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     * @throws Exception if something fails.
     */
    String dropdbs(String tokenAsJson) throws IOException, Exception;
    
    /**
     * Get all pending requests of a Personal Trainer by Personal Trainer username.
     * 
     * @param usernameAsJson Personal Trainer username.
     * @return All pending requests of a Personal Trainer.
     * @throws IOException if something fails on trying contact with external services.
     */
    String listClientRequestsByPersonalTrainer(String usernameAsJSON) throws IOException;
    
    /**
     * Get all Client requests by Client username.
     * 
     * @param usernameAsJSON Client username.
     * @return All Client requests.
     * @throws IOException if something fails on trying contact with external services.
     */
    String listClientRequestsByClient(String usernameAsJSON) throws IOException;
    
    /**
     * Get Client username by Request id.
     * 
     * @param requestIdAsJson Request id.
     * @return Client username.
     * @throws IOException if something fails on trying contact with external services.
     */
    String getUsernameByRequestId(String requestIdAsJson) throws IOException;

    /**
     * The state of Client submitted a classification.
     * 
     * @param data Client username.
     * @return True if submitted classification, otherwise False. 
     * @throws IOException if something fails on trying contact with external services.
     */
    String hasSubmittedClassification(String data) throws IOException;
}
