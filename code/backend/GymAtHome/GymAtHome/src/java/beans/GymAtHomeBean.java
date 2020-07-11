/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import exceptions.GymAtHomeException;
import utils.Http;
import utils.Utils;
import okhttp3.*;

import javax.ejb.Stateless;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joaomarques
 */
@Stateless
public class GymAtHomeBean implements GymAtHomeBeanLocal {

    public GymAtHomeBean() {
    }

    /**
     * Create a Client with the info submitted.
     * 
     * @param infoClientAsJSON All Client info as json string.
     * @return A Client in json string.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public String createClient(String infoClientAsJSON) throws IOException, GymAtHomeException {
        String urlHRClient = "http://localhost:8080/api/v1/GymAtHome/HRClient/createClient";
        Response responseHRClient = Http.post(urlHRClient,infoClientAsJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(responseHRClient,HttpServletResponse.SC_OK);

        // TODO concatenate
        String coreJSON = infoClientAsJSON + responseHRClient.body().string();
        String urlCore = "http://localhost:8080/api/v1/GymAtHome/Core/createUserToken";
        Response responseCore = Http.post(urlCore,coreJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(responseCore,HttpServletResponse.SC_OK);

        return responseHRClient.body().string();
    }

    /**
     * Create a PersonalTrainer with the info submitted.
     * 
     * @param infoPTAsJSON All PersonalTrainer info as json string.
     * @return A PersonalTrainer in json string.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public String createPersonalTrainer(String infoPTAsJSON) throws IOException, GymAtHomeException {
        String urlHRPT = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/createPersonalTrainer";
        Response responseHRPT = Http.post(urlHRPT,infoPTAsJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(responseHRPT,HttpServletResponse.SC_OK);

        // TODO concatenate
        String coreJSON = infoPTAsJSON + responseHRPT.body().string();
        String urlCore = "http://localhost:8080/api/v1/GymAtHome/Core/createUserToken";
        Response responseCore = Http.post(urlCore,coreJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(responseCore,HttpServletResponse.SC_OK);

        return responseHRPT.body().string();
    }

    /**
     * Authenticate Client with the credentials provided.
     * 
     * @param infoAsJSON Client credentials as json string.
     * @return A string with login status.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public String loginClient(String infoAsJSON) throws IOException, GymAtHomeException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRClient/loginClient";
        Response responseHRClient = Http.post(url,infoAsJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(responseHRClient,HttpServletResponse.SC_OK);

        // TODO concatenate
        String coreJSON = infoAsJSON + responseHRClient.body().string();
        String urlCore = "http://localhost:8080/api/v1/GymAtHome/Core/updateToken";
        Response responseCore = Http.post(urlCore,coreJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(responseCore,HttpServletResponse.SC_OK);

        return responseHRClient.body().string();
    }

    /**
     * Authenticate PersonalTrainer with the credentials provided.
     * 
     * @param infoAsJSON PersonalTrainer credentials as json string.
     * @return A string with login status.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public String loginPersonalTrainer(String infoAsJSON) throws IOException, GymAtHomeException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/loginPersonalTrainer";
        Response responseHRPT = Http.post(url,infoAsJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(responseHRPT,HttpServletResponse.SC_OK);

        // TODO concatenate
        String coreJSON = infoAsJSON + responseHRPT.body().string();
        String urlCore = "http://localhost:8080/api/v1/GymAtHome/Core/createUserToken";
        Response responseCore = Http.post(urlCore,coreJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(responseCore,HttpServletResponse.SC_OK);

        return responseHRPT.body().string();
    }

    /**
     * Get a Client profile by Client username.
     * 
     * @param usernameAsJSON Client username as json string.
     * @return Client info.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public String getClientProfile(String usernameAsJSON) throws IOException, GymAtHomeException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRClient/getClient";
        Response response = Http.post(url,usernameAsJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(response,HttpServletResponse.SC_OK);

        return null;
    }

    /**
     * Get a PersonalTrainer profile by PersonalTrainer username.
     * 
     * @param usernameAsJSON PersonalTrainer username as json string.
     * @return PersonalTrainer info.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public String getPersonalTrainerProfile(String usernameAsJSON) throws IOException, GymAtHomeException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/getPersonalTrainer";
        Response response = Http.post(url,usernameAsJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(response,HttpServletResponse.SC_OK);
        
        return response.body().string();
    }

    /**
     * Edit Client info with new info given.
     * 
     * @param infoAsJSON Client new infos as json string.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public void editClientProfile(String infoAsJSON) throws IOException, GymAtHomeException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRClient/editClientProfile";
        Response response = Http.post(url,infoAsJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(response,HttpServletResponse.SC_OK);
    }

    /**
     * Edit PersonalTrainer info with new info given.
     * 
     * @param usernameAsJSON PersonalTrainer new infos as json string.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public void editPersonalTrainerProfile(String usernameAsJSON) throws IOException, GymAtHomeException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/editPersonalTrainerProfile";
        Response response = Http.post(url,usernameAsJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(response,HttpServletResponse.SC_OK);
    }

    /**
     * Retrieves tha actual week of plan of that Client.
     * 
     * @param usernameAndWeekAsJSON Client username and current week of plan as json string.
     * @return Client plan (actual week of the plan).
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public String getPlanByClient(String usernameAndWeekAsJSON) throws IOException, GymAtHomeException {
        String url = "http://localhost:8080/api/v1/GymAtHome/Core/getPlanByClient";
        Response response = Http.post(url,usernameAndWeekAsJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(response,HttpServletResponse.SC_OK);
        
        return response.body().string();
    }

    /**
     * Retrieves tha actual week of plan of that Client.
     *
     * @param usernameAndWeekAsJSON Client username and current week of plan as json string.
     * @return Client plan (actual week of the plan).
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public String getPlanByPersonalTrainer(String usernameAndWeekAsJSON) throws IOException, GymAtHomeException {
        String url = "http://localhost:8080/api/v1/GymAtHome/Core/getPlanByPersonalTrainer";
        Response response = Http.post(url,usernameAndWeekAsJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(response,HttpServletResponse.SC_OK);
        
        return response.body().string();
    }

    /**
     * Retrieves the PersonalTrainers that corresponded to the filters given by Client.
     * 
     * @param filtersAsJSON Filters introduced by Client as json string.
     * @return PersonalTrainers filtered.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public String getPersonalTrainers(String filtersAsJSON) throws IOException, GymAtHomeException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/getPersonalTrainers";
        Response response = Http.post(url,filtersAsJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(response,HttpServletResponse.SC_OK);
        
        return response.body().string();
    }

    /**
     * Retrieves the most recent BioMetric data from a Client.
     * 
     * @param usernameAsJSON Client username as json string.
     * @return Client BioMetric data.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public String getBiometricData(String usernameAsJSON) throws IOException, GymAtHomeException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRClient/getBiometricData";
        Response response = Http.post(url,usernameAsJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(response,HttpServletResponse.SC_OK);
        
        return response.body().string();
    }

    /**
     * Submit a classification to a PersonalTrainer given by a Client.
     *
     * @param usernameAndClassificationAsJSON PersonalTrainer username and classification attributed by Client as a json string.
     * @return
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public String submitClassification(String usernameAndClassificationAsJSON) throws IOException, GymAtHomeException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/submitClassification";
        Response response = Http.post(url,usernameAndClassificationAsJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(response,HttpServletResponse.SC_OK);
        
        return null;
    }

    /**
     * Client submit that as completed a workout.
     * 
     * @param usernameAndWorkoutIdAsJSON Client username and Workout Id as a json string.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public void finishWorkout(String usernameAndWorkoutIdAsJSON) throws IOException, GymAtHomeException {
        String url = "http://localhost:8080/api/v1/GymAtHome/Core/finishWorkout";
        Response response = Http.post(url,usernameAndWorkoutIdAsJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(response,HttpServletResponse.SC_OK);
    }

    /**
     * Retrieves all Clients of a PersonalTrainer.
     * 
     * @param usernameAsJSON PersonalTrainer username.
     * @return PersonalTrainer clients.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public String getPersonalTrainerClients(String usernameAsJSON) throws IOException, GymAtHomeException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/getPersonalTrainerClients";
        Response response = Http.post(url,usernameAsJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(response,HttpServletResponse.SC_OK);
        
        return response.body().string();
    }

    /**
     * Request submitted by a Client to a PersonalTrainer.
     * 
     * @param requestInfoAsJSON Request info submitted by Client.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public void submitRequest(String requestInfoAsJSON) throws IOException, GymAtHomeException {
        String url = "http://localhost:8080/api/v1/GymAtHome/Requests/submitRequest";
        Response response = Http.post(url,requestInfoAsJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(response,HttpServletResponse.SC_OK);
    }

    /**
     * Create and add a week of workouts to a plan of a Client.
     * 
     * @param weekAsJson Week info created by PersonalTrainer.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public void createWeek(String weekAsJson) throws IOException, GymAtHomeException {
        String url = "http://localhost:8080/api/v1/GymAtHome/Core/createWeek";
        Response response = Http.post(url,weekAsJson);
        // Test if something fails on external service
        Utils.testHTTPCode(response,HttpServletResponse.SC_OK);
    }

    /**
     * Response given by a PersonalTrainer to a Request submitted to him by a Client.
     * 
     * @param requestIdAndResponseAsJSON Request Id and Response by PersonalTrainer to the request.
     * @throws IOException if something fails on trying contact with external services.
     * @throws GymAtHomeException Exceptions throwed by external services.
     */
    @Override
    public void replyToRequest(String requestIdAndResponseAsJSON) throws IOException, GymAtHomeException {
        String url = "http://localhost:8080/api/v1/GymAtHome/";
        Response response = Http.post(url,requestIdAndResponseAsJSON);
        // Test if something fails on external service
        Utils.testHTTPCode(response,HttpServletResponse.SC_OK);
    }
}
