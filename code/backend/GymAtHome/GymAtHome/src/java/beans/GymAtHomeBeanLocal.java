/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import exceptions.GymAtHomeException;
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
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    String createClient(String infoClientAsJSON) throws IOException, GymAtHomeException;

    /**
     * Create a PersonalTrainer with the info submitted.
     * 
     * @param infoPTAsJSON All PersonalTrainer info as json string.
     * @return A PersonalTrainer in json string.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    String createPersonalTrainer(String infoPTAsJSON) throws IOException, GymAtHomeException;

    /**
     * Authenticate Client with the credentials provided.
     * 
     * @param infoAsJSON Client credentials as json string.
     * @return A string with login status.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    String loginClient(String infoAsJSON) throws IOException, GymAtHomeException;

    /**
     * Authenticate PersonalTrainer with the credentials provided.
     * 
     * @param infoAsJSON PersonalTrainer credentials as json string.
     * @return A string with login status.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    String loginPersonalTrainer(String infoAsJSON) throws IOException, GymAtHomeException;

    /**
     * Get a Client profile by Client username.
     * 
     * @param usernameAsJSON Client username as json string.
     * @return Client info.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    String getClientProfile(String usernameAsJSON) throws IOException, GymAtHomeException;

    /**
     * Get a PersonalTrainer profile by PersonalTrainer username.
     * 
     * @param usernameAsJSON PersonalTrainer username as json string.
     * @return PersonalTrainer info.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    String getPersonalTrainerProfile(String usernameAsJSON) throws IOException, GymAtHomeException;

    /**
     * Edit Client info with new info given.
     * 
     * @param infoAsJSON Client new infos as json string.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    void editClientProfile(String infoAsJSON) throws IOException, GymAtHomeException;

    /**
     * Edit PersonalTrainer info with new info given.
     * 
     * @param usernameAsJSON PersonalTrainer new infos as json string.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    void editPersonalTrainerProfile(String usernameAsJSON) throws IOException, GymAtHomeException;

    /**
     * Retrieves tha actual week of plan of that Client.
     * 
     * @param usernameAndWeekAsJSON Client username and current week of plan as json string.
     * @return Client plan (actual week of the plan).
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    String getPlanByClient(String usernameAndWeekAsJSON) throws IOException, GymAtHomeException;

    /**
     * Retrieves tha actual week of plan of that Client.
     *
     * @param usernameAndWeekAsJSON Client username and current week of plan as json string.
     * @return Client plan (actual week of the plan).
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    String getPlanByPersonalTrainer(String usernameAndWeekAsJSON) throws IOException, GymAtHomeException;

    /**
     * Retrieves the PersonalTrainers that corresponded to the filters given by Client.
     * 
     * @param filtersAsJSON Filters introduced by Client as json string.
     * @return PersonalTrainers filtered.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    String getPersonalTrainers(String filtersAsJSON) throws IOException, GymAtHomeException;

    /**
     * Retrieves the most recent BioMetric data from a Client.
     * 
     * @param usernameAsJSON Client username as json string.
     * @return Client BioMetric data.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    String getBiometricData(String usernameAsJSON) throws IOException, GymAtHomeException;

    /**
     * Submit a classification to a PersonalTrainer given by a Client.
     *
     * @param usernameAndClassificationAsJSON PersonalTrainer username and classification attributed by Client as a json string.
     * @return
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    String submitClassification(String usernameAndClassificationAsJSON) throws IOException, GymAtHomeException;

    /**
     * Client submit that as completed a workout.
     * 
     * @param usernameAndWorkoutIdAsJSON Client username and Workout Id as a json string.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    void finishWorkout(String usernameAndWorkoutIdAsJSON) throws IOException, GymAtHomeException;

    /**
     * Retrieves all Clients of a PersonalTrainer.
     * 
     * @param usernameAsJSON PersonalTrainer username.
     * @return PersonalTrainer clients.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    String getPersonalTrainerClients(String usernameAsJSON) throws IOException, GymAtHomeException;

    /**
     * Request submitted by a Client to a PersonalTrainer.
     * 
     * @param requestInfoAsJSON Request info submitted by Client.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    void submitRequest(String requestInfoAsJSON) throws IOException, GymAtHomeException;

    /**
     * Create and add a week of workouts to a plan of a Client.
     * 
     * @param weekAsJson Week info created by PersonalTrainer.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    void createWeek(String weekAsJson) throws IOException, GymAtHomeException;

    /**
     * Response given by a PersonalTrainer to a Request submitted to him by a Client.
     * 
     * @param requestIdAndResponseAsJSON Request Id and Response by PersonalTrainer to the request.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    void replyToRequest(String requestIdAndResponseAsJSON) throws IOException, GymAtHomeException;
}
