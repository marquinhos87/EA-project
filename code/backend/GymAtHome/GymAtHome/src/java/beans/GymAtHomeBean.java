/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import utils.Http;
import okhttp3.*;
import parseJSON.ResponseJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javax.ejb.Stateless;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joaomarques
 */
@Stateless
public class GymAtHomeBean implements GymAtHomeBeanLocal {

    private final Gson gson;

    public static final String clients = "http://client:8080/Clients/api/";
    public static final String pts = "http://pt:8080/PersonalTrainer/api/";
    public static final String core = "http://core:8080/Core/api/";
    public static final String requests = "http://request:8080/Request/api/";
    public static final String notifications = "http://notification:8080/Notification/api/";
    
    
    public GymAtHomeBean() {
        gson = new GsonBuilder().create();
    }

    /**
     * Create a Client with the info submitted.
     * 
     * @param infoClientAsJSON All Client info as json string.
     * @return A Client in json string.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String createClient(String infoClientAsJSON) throws IOException {
        String urlHRClient = clients + "createClient";
        Response responseHRClient = Http.post(urlHRClient,infoClientAsJSON);
        String initialBody = responseHRClient.body().string();
        String body = initialBody;
        // Test if something fails on external service
        if(responseHRClient.code() != HttpServletResponse.SC_OK) {
            responseHRClient.close();
            return body;
        }
        responseHRClient.close();
        
        ResponseJSON response = gson.fromJson(body, ResponseJSON.class);
        String token = response.data.getAsJsonObject().get("token").getAsString();
        String username = gson.fromJson(infoClientAsJSON, JsonObject.class).get("username").getAsString();
        
        // JSON to send to other services
        JsonObject jo = new JsonObject();
        jo.addProperty("username", username);
        jo.addProperty("token", token);
        
        String json = jo.toString();
        
        String urlCore = core + "createUserTokenClient";
        Response responseCore = Http.post(urlCore,json);
        body = responseCore.body().string();
        // Test if something fails on external service
        if(responseCore.code() != HttpServletResponse.SC_OK) {
            responseCore.close();
            return body;
        }
        responseCore.close();
        
        String urlHRPT = pts + "createClient";
        Response responseHRPT = Http.post(urlHRPT,json);
        body = responseHRPT.body().string();
        // Test if something fails on external service
        if(responseHRPT.code() != HttpServletResponse.SC_OK) {
            responseHRPT.close();
            return body;
        }
        responseHRPT.close();
        
        String urlRequest = requests + "createClient";
        Response responseRequest = Http.post(urlRequest,json);
        body = responseRequest.body().string();
        // Test if something fails on external service
        if(responseRequest.code() != HttpServletResponse.SC_OK) {
            responseRequest.close();
            return body;
        }
        responseRequest.close();
        
        String urlNotification = notifications + "createClient";
        Response responseNotification = Http.post(urlNotification,json);
        body = responseNotification.body().string();
        // Test if something fails on external service
        if(responseNotification.code() != HttpServletResponse.SC_OK) {
            responseNotification.close();
            return body;
        }
        responseNotification.close();
        
        return initialBody;
    }

    /**
     * Create a PersonalTrainer with the info submitted.
     * 
     * @param infoPTAsJSON All PersonalTrainer info as json string.
     * @return A PersonalTrainer in json string.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String createPersonalTrainer(String infoPTAsJSON) throws IOException {
        String urlHRPT = pts + "createPersonalTrainer";
        Response responseHRPT = Http.post(urlHRPT,infoPTAsJSON);
        String initialBody = responseHRPT.body().string();
        String body = initialBody;
        // Test if something fails on external service
        if(responseHRPT.code() != HttpServletResponse.SC_OK) {
            responseHRPT.close();
            return body;
        }
        responseHRPT.close();

        ResponseJSON response = gson.fromJson(body, ResponseJSON.class);
        String token = response.data.getAsJsonObject().get("token").getAsString();
        String username = gson.fromJson(infoPTAsJSON, JsonObject.class).get("username").getAsString();
        
        // JSON to send to other services
        JsonObject jo = new JsonObject();
        jo.addProperty("username", username);
        jo.addProperty("token", token);
        
        String json = jo.toString();
        
        String urlCore = core + "createUserTokenPersonalTrainer";
        Response responseCore = Http.post(urlCore,json);
        body = responseCore.body().string();
        // Test if something fails on external service
        if(responseCore.code() != HttpServletResponse.SC_OK) {
             responseCore.close();
            return body;
        }
        responseCore.close();
        
        String urlHRClient = clients + "createUser";
        Response responseHRClient = Http.post(urlHRClient,json);
        body = responseHRClient.body().string();
        // Test if something fails on external service
        if(responseHRClient.code() != HttpServletResponse.SC_OK) {
            responseHRClient.close();
            return body;
        }
        responseHRClient.close();
        
        String urlRequest = requests + "createPersonalTrainer";
        Response responseRequest = Http.post(urlRequest,json);
        body = responseRequest.body().string();
        // Test if something fails on external service
        if(responseRequest.code() != HttpServletResponse.SC_OK) {
            responseRequest.close();
            return body;
        }
        responseRequest.close();
        
        String urlNotification = notifications + "createPersonalTrainer";
        Response responseNotification = Http.post(urlNotification,json);
        body = responseNotification.body().string();
        // Test if something fails on external service
        if(responseNotification.code() != HttpServletResponse.SC_OK) {
            responseNotification.close();
            return body;
        }
        responseNotification.close();

        return initialBody;
    }

    /**
     * Authenticate Client with the credentials provided.
     * 
     * @param infoAsJSON Client credentials as json string.
     * @return A string with login status.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String loginClient(String infoAsJSON) throws IOException {
        String urlHRClient = clients + "loginClient";
        Response responseHRClient = Http.post(urlHRClient,infoAsJSON);
        String initialBody = responseHRClient.body().string();
        String body = initialBody;
        // Test if something fails on external service
        if(responseHRClient.code() != HttpServletResponse.SC_OK) {
            responseHRClient.close();
            return body;
        }
        responseHRClient.close();

        ResponseJSON response = gson.fromJson(body, ResponseJSON.class);
        String oldToken = response.data.getAsJsonObject().get("oldToken").getAsString();
        String newToken = response.data.getAsJsonObject().get("newToken").getAsString();
        String username = gson.fromJson(infoAsJSON, JsonObject.class).get("username").getAsString();
        
        // JSON to send to other services
        JsonObject jo = new JsonObject();
        jo.addProperty("username", username);
        jo.addProperty("oldToken", oldToken);
        jo.addProperty("newToken", newToken);
        
        // To send to other services
        String json = jo.toString();
        
        String urlCore = core + "updateToken";
        Response responseCore = Http.post(urlCore,json);
        body = responseCore.body().string();
        // Test if something fails on external service, reverse previous services
        if(responseCore.code() != HttpServletResponse.SC_OK) {
            responseCore.close();
            return body;
        }
        responseCore.close();
        
        String urlHRPT = pts + "updateClientToken";
        Response responseHRPT = Http.post(urlHRPT,json);
        body = responseHRPT.body().string();
        // Test if something fails on external service, reverse previous services
        if(responseHRPT.code() != HttpServletResponse.SC_OK) {
            responseHRPT.close();
            return body;
        }
        responseHRPT.close();
        
        String urlRequest = requests + "updateUserToken";
        Response responseRequest = Http.post(urlRequest,json);
        body = responseRequest.body().string();
        // Test if something fails on external service, reverse previous services
        if(responseRequest.code() != HttpServletResponse.SC_OK) {
            responseRequest.close();
            return body;
        }
        responseRequest.close();
        
        String urlNotification = notifications + "updateClientToken";
        Response responseNotification = Http.post(urlNotification,json);
        body = responseNotification.body().string();
        // Test if something fails on external service, reverse previous services
        if(responseNotification.code() != HttpServletResponse.SC_OK) {
            responseNotification.close();
            return body;
        }
        responseNotification.close();

        return initialBody;
    }

    /**
     * Authenticate PersonalTrainer with the credentials provided.
     * 
     * @param infoAsJSON PersonalTrainer credentials as json string.
     * @return A string with login status.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String loginPersonalTrainer(String infoAsJSON) throws IOException {
        String urlHRPT = pts + "login";
        Response responseHRPT = Http.post(urlHRPT,infoAsJSON);
        String initialBody = responseHRPT.body().string();
        String body = initialBody;
        // Test if something fails on external service
        if(responseHRPT.code() != HttpServletResponse.SC_OK) {
            responseHRPT.close();
            return body;
        }
        responseHRPT.close();

        ResponseJSON response = gson.fromJson(body, ResponseJSON.class);
        String oldToken = response.data.getAsJsonObject().get("oldToken").getAsString();
        String newToken = response.data.getAsJsonObject().get("newToken").getAsString();
        String username = gson.fromJson(infoAsJSON, JsonObject.class).get("username").getAsString();
        
        // JSON to send to other services
        JsonObject jo = new JsonObject();
        jo.addProperty("username", username);
        jo.addProperty("oldToken", oldToken);
        jo.addProperty("newToken", newToken);
        
        // To send to other services
        String json = jo.toString();
        
        String urlCore = core + "updateToken";
        Response responseCore = Http.post(urlCore,json);
        body = responseCore.body().string();
        // Test if something fails on external service, reverse previous services
        if(responseCore.code() != HttpServletResponse.SC_OK) {
            responseCore.close();
            return body;
        }
        responseCore.close();
        
        String urlHRClient = clients + "updateUserToken";
        Response responseHRClient = Http.post(urlHRClient,json);
        body = responseHRClient.body().string();
        // Test if something fails on external service, reverse previous services
        if(responseHRClient.code() != HttpServletResponse.SC_OK) {
            responseHRClient.close();
            return body;
        }
        responseHRClient.close();
        
        String urlRequest = requests + "updateUserToken";
        Response responseRequest = Http.post(urlRequest,json);
        body = responseRequest.body().string();
        // Test if something fails on external service, reverse previous services
        if(responseRequest.code() != HttpServletResponse.SC_OK) {
            responseRequest.close();
            return body;
        }
        responseRequest.close();
        
        String urlNotification = notifications + "updatePersonalTrainerToken";
        Response responseNotification = Http.post(urlNotification,json);
        body = responseNotification.body().string();
        // Test if something fails on external service, reverse previous services
        if(responseNotification.code() != HttpServletResponse.SC_OK) {
            responseNotification.close();
            return body;
        }
        responseNotification.close();

        return initialBody;
    }

    /**
     * Get a Client profile by Client username.
     * 
     * @param usernameAsJSON Client username as json string.
     * @return Client info.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String getClientProfileByClient(String usernameAsJSON) throws IOException {
        String url = clients + "getClientProfileByClient";
        Response response = Http.post(url,usernameAsJSON);
        String data = response.body().string();
        response.close();
        return data;
    }
    
    /**
     * Get a Client profile by Client username.
     * 
     * @param usernameAsJSON Client username as json string.
     * @return Client info.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String getClientProfileByPersonalTrainer(String usernameAsJSON) throws IOException {
        String url = clients + "getClientProfileByPersonalTrainer";
        Response response = Http.post(url,usernameAsJSON);
        String data = response.body().string();
        response.close();
        return data;
    }

    /**
     * Get a PersonalTrainer profile by PersonalTrainer username.
     * 
     * @param usernameAsJSON PersonalTrainer username as json string.
     * @return PersonalTrainer info.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String getPersonalTrainerProfileByClient(String usernameAsJSON) throws IOException {
        String url = pts + "getPersonalTrainerProfileByClient";
        Response response = Http.post(url,usernameAsJSON);
        String data = response.body().string();
        response.close();
        return data;
    }
    
    /**
     * Get a PersonalTrainer profile by PersonalTrainer username.
     * 
     * @param usernameAsJSON PersonalTrainer username as json string.
     * @return PersonalTrainer info.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String getPersonalTrainerProfileByPersonalTrainer(String usernameAsJSON) throws IOException {
        String url = pts + "getPersonalTrainerProfileByPersonalTrainer";
        Response response = Http.post(url,usernameAsJSON);
        String data = response.body().string();
        response.close();
        return data;
    }

    /**
     * Edit Client info with new info given.
     * 
     * @param infoAsJSON Client new infos as json string.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String editClientProfile(String infoAsJSON) throws IOException {
        String url = clients + "editClientProfile";
        Response response = Http.post(url,infoAsJSON);
        String data = response.body().string();
        response.close();
        return data;
    }

    /**
     * Edit PersonalTrainer info with new info given.
     * 
     * @param usernameAsJSON PersonalTrainer new infos as json string.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String editPersonalTrainerProfile(String usernameAsJSON) throws IOException {
        String url = pts + "editPersonalTrainerProfile";
        Response response = Http.post(url,usernameAsJSON);
        String data = response.body().string();
        response.close();
        return data;
    }

    /**
     * Retrieves tha actual week of plan of that Client.
     * 
     * @param usernameAndWeekAsJSON Client username and current week of plan as json string.
     * @return Client plan (actual week of the plan).
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String getWeekByClient(String usernameAndWeekAsJSON) throws IOException {
        String url = core + "getWeekByClient";
        Response response = Http.post(url,usernameAndWeekAsJSON);
        String data = response.body().string();
        response.close();
        return data;
    }

    /**
     * Retrieves tha actual week of plan of that Client.
     *
     * @param usernameAndWeekAsJSON Client username and current week of plan as json string.
     * @return Client plan (actual week of the plan).
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String getWeekByPersonalTrainer(String usernameAndWeekAsJSON) throws IOException {
        String url = core + "getWeekByPersonalTrainer";
        Response response = Http.post(url,usernameAndWeekAsJSON);
        String data = response.body().string();
        response.close();
        return data;
    }

    /**
     * Retrieves the PersonalTrainers that corresponded to the filters given by Client.
     * 
     * @param filtersAsJSON Filters introduced by Client as json string.
     * @return PersonalTrainers filtered.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String getPersonalTrainers(String filtersAsJSON) throws IOException {
        String url = pts + "getPersonalTrainers";
        Response response = Http.post(url,filtersAsJSON);
        String data = response.body().string();
        response.close();
        return data;
    }

    /**
     * Retrieves the most recent BioMetric data from a Client.
     * 
     * @param usernameAsJSON Client username as json string.
     * @return Client BioMetric data.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String getBiometricData(String usernameAsJSON) throws IOException {
        String url = clients + "getBiometricData";
        Response response = Http.post(url,usernameAsJSON);
        String data = response.body().string();
        response.close();
        return data;
    }

    /**
     * Submit a classification to a PersonalTrainer given by a Client.
     *
     * @param usernameAndClassificationAsJSON PersonalTrainer username and classification attributed by Client as a json string.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String submitClassification(String usernameAndClassificationAsJSON) throws IOException {
        String url = pts + "submitClassification";
        Response responseHRPT = Http.post(url,usernameAndClassificationAsJSON);
        String initialBody = responseHRPT.body().string();
        String body = initialBody;
        if(responseHRPT.code() != HttpServletResponse.SC_OK) {
            responseHRPT.close();
            return body;
        }
        responseHRPT.close();
        
        JsonObject jo = gson.fromJson(usernameAndClassificationAsJSON, JsonObject.class);
        String username = jo.get("username").getAsString();
        String token = jo.get("token").getAsString();
        String personalTrainerUsername = jo.get("personalTrainerUsername").getAsString();
        String classification = jo.get("classification").getAsString();
        
        // JSON to send to other services
        jo = new JsonObject();
        jo.addProperty("username", username);
        jo.addProperty("token", token);
        jo.addProperty("personalTrainerUsername",personalTrainerUsername);
        jo.addProperty("description","Cliente com o username " + username + " submeteu uma avaliação com o valor " + classification + ".");
        
        String json = jo.toString();
        
        String urlNotification = notifications + "createNotificationToPersonalTrainer";
        Response responseNotification = Http.post(urlNotification, json);
        body = responseNotification.body().string();
        if(responseNotification.code() != HttpServletResponse.SC_OK) {
            responseNotification.close();
            return body;
        }
        responseNotification.close();
        
        return initialBody;
    }

    /**
     * Client submit that as completed a workout.
     * 
     * @param usernameAndWorkoutIdAsJSON Client username and Workout Id as a json string.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String finishWorkout(String usernameAndWorkoutIdAsJSON) throws IOException {
        String urlCore = core + "finishWorkout";
        Response responseCore = Http.post(urlCore,usernameAndWorkoutIdAsJSON);
        String initialBody = responseCore.body().string();
        String body = initialBody;
        if(responseCore.code() != HttpServletResponse.SC_OK) {
            responseCore.close();
            return body;
        }
        responseCore.close();
        
        ResponseJSON rj = gson.fromJson(body, ResponseJSON.class);
        String personalTrainerUsername = rj.data.getAsJsonObject().get("username").getAsString();
        
        JsonObject jo = gson.fromJson(usernameAndWorkoutIdAsJSON, JsonObject.class);
        String username = jo.get("username").getAsString();
        String token = jo.get("token").getAsString();
             
        // JSON to send to other services
        jo = new JsonObject();
        jo.addProperty("username", username);
        jo.addProperty("token", token);
        jo.addProperty("personalTrainerUsername", personalTrainerUsername);
        jo.addProperty("description","Cliente com o username " + username + " terminou um workout.");
        
        String json = jo.toString();
        
        String urlNotification = notifications + "createNotificationToPersonalTrainer";
        Response responseNotification = Http.post(urlNotification, json);
        body = responseNotification.body().string();
        if(responseNotification.code() != HttpServletResponse.SC_OK) {
            responseNotification.close();
            return body;
        }
        responseNotification.close();
        
        return initialBody;
    }

    /**
     * Retrieves all Clients of a PersonalTrainer.
     * 
     * @param usernameAsJSON PersonalTrainer username.
     * @return PersonalTrainer clients.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String getPersonalTrainerClients(String usernameAsJSON) throws IOException {
        String url = pts + "getPersonalTrainerClients";
        Response response = Http.post(url,usernameAsJSON);
        String data = response.body().string();
        response.close();
        return data;
    }

    /**
     * Request submitted by a Client to a PersonalTrainer.
     * 
     * @param requestInfoAsJSON Request info submitted by Client.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String submitRequest(String requestInfoAsJSON) throws IOException {
        
        // TODO inverter ordem para um request ter uma notificação associada
        
        String urlRequest = requests + "submitRequest";
        Response responseRequest = Http.post(urlRequest,requestInfoAsJSON);
        String initialBody = responseRequest.body().string();
        String body = initialBody;
        if(responseRequest.code() != HttpServletResponse.SC_OK) {
            responseRequest.close();
            return body;
        }
        responseRequest.close();
        
        JsonObject jo = gson.fromJson(requestInfoAsJSON, JsonObject.class);
        String username = jo.get("username").getAsString();
        String token = jo.get("token").getAsString();
        String personalTrainerUsername = jo.get("personalTrainerUsername").getAsString();
        
        
        // JSON to send to other services
        jo = new JsonObject();
        jo.addProperty("username", username);
        jo.addProperty("token", token);
        jo.addProperty("personalTrainerUsername",personalTrainerUsername);
        jo.addProperty("description","Cliente com o username " + username + " submeteu um pedido.");
        
        String json = jo.toString();
        
        String urlNotification = notifications + "createNotificationToPersonalTrainer";
        Response responseNotification = Http.post(urlNotification, json);
        body = responseNotification.body().string();
        if(responseNotification.code() != HttpServletResponse.SC_OK) {
            responseNotification.close();
            return body;
        }
        responseNotification.close();
        
        return initialBody;
    }

    /**
     * Create and add a week of workouts to a plan of a Client.
     * 
     * @param weekAsJson Week info created by PersonalTrainer.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String createWeek(String weekAsJson) throws IOException {
        String url = core + "createWeek";
        Response responseCore = Http.post(url,weekAsJson);
        String initialBody = responseCore.body().string();
        String body = initialBody;
        if(responseCore.code() != HttpServletResponse.SC_OK) {
            responseCore.close();
            return body;
        }
        responseCore.close();
        
        JsonObject jo = gson.fromJson(weekAsJson, JsonObject.class);
        String username = jo.get("username").getAsString();
        String token = jo.get("token").getAsString();
        String clientUsername = jo.get("clientUsername").getAsString();
        
        // JSON to send to other services
        jo = new JsonObject();
        jo.addProperty("username", username);
        jo.addProperty("token", token);
        jo.addProperty("clientUsername",clientUsername);
        jo.addProperty("description","O seu Personal Trainer adicionou uma semana ao seu plano.");
        
        String json = jo.toString();
        
        String urlNotification = notifications + "createNotificationToClient";
        Response responseNotification = Http.post(urlNotification, json);
        body = responseNotification.body().string();
        if(responseNotification.code() != HttpServletResponse.SC_OK) {
            responseNotification.close();
            return body;
        }
        responseNotification.close();
        
        return initialBody;
    }

    /**
     * Response given by a PersonalTrainer to a Request submitted to him by a Client.
     * 
     * @param requestIdAndResponseAsJSON Request Id and Response by PersonalTrainer to the request.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String replyToRequest(String requestIdAndResponseAsJSON) throws IOException {
        String urlRequest = requests + "replyToRequest";
        Response responseRequest = Http.post(urlRequest,requestIdAndResponseAsJSON);
        String initialBody = responseRequest.body().string();
        String body = initialBody;
        if(responseRequest.code() != HttpServletResponse.SC_OK) {
            responseRequest.close();
            return body;
        }
        responseRequest.close();
        
        JsonObject jo = gson.fromJson(requestIdAndResponseAsJSON, JsonObject.class);
        String username = jo.get("username").getAsString();
        String token = jo.get("token").getAsString();
        String clientUsername = jo.get("clientUsername").getAsString();
        boolean accepted = jo.get("accepted").getAsBoolean();
        
        // JSON to send to other services
        jo = new JsonObject();
        jo.addProperty("username", username);
        jo.addProperty("token", token);
        jo.addProperty("clientUsername",clientUsername);
        
        String json = jo.toString();
        
        if(accepted) {
            String urlHRPT = pts + "addClientToPersonalTrainer";
            Response responseHRPT = Http.post(urlHRPT,json);
            body = initialBody;
            if(responseHRPT.code() != HttpServletResponse.SC_OK) {
                responseHRPT.close();
                return body;
            }
            responseHRPT.close();
            jo.addProperty("description","Personal Trainer com o username " + username + " aceitou o teu pedido.");
        }
        else {
            jo.addProperty("description","Personal Trainer com o username " + username + " recusou o teu pedido.");
        }
            
        
        json = jo.toString();
        
        String urlNotification = notifications + "createNotificationToClient";
        Response responseNotification = Http.post(urlNotification, json);
        body = responseNotification.body().string();
        if(responseNotification.code() != HttpServletResponse.SC_OK) {
            responseNotification.close();
            return body;
        }
        responseNotification.close();
        
        return initialBody;
    }
    
    /**
     * Get all notifications of a Client.
     * 
     * @param usernameAsJSON Client username.
     * @return All Client notifications.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String getNotificationsByClient(String usernameAsJSON) throws IOException {
        String url = notifications + "getNotificationsByClient";
        Response response = Http.post(url,usernameAsJSON);
        String data = response.body().string();
        response.close();
        return data;
    }
    
    /**
     * Get all notifications of a Personal Trainer.
     * 
     * @param usernameAsJSON Personal Trainer username.
     * @return All Personal Trainer notifications.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String getNotificationsByPersonalTrainer(String usernameAsJSON) throws IOException {
        String url = notifications + "getNotificationsByPersonalTrainer";
        Response response = Http.post(url,usernameAsJSON);
        String data = response.body().string();
        response.close();
        return data;
    }
    
    /**
     * Mark some client notifications as read.
     * 
     * @param usernameAndIdsAsJSON Client username and notifications ids.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String markAsReadNotificationsByClient(String usernameAndIdsAsJSON) throws IOException {
        String url = notifications + "markAsReadNotificationsByClient";
        Response response = Http.post(url,usernameAndIdsAsJSON);
        String data = response.body().string();
        response.close();
        return data;
    }
    
    /**
     * Mark some personal trainer notifications as read.
     * 
     * @param usernameAndIdsAsJSON Personal Trainer username and notifications ids.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String markAsReadNotificationsByPersonalTrainer(String usernameAndIdsAsJSON) throws IOException {
        String url = notifications + "markAsReadNotificationsByPersonalTrainer";
        Response response = Http.post(url,usernameAndIdsAsJSON);
        String data = response.body().string();
        response.close();
        return data;
    }
    
    /**
     * Drop schemas of databases.
     * 
     * @param tokenAsJson Admin token.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     * @throws Exception if something fails.
     */
    @Override
    public String dropdbs(String tokenAsJSON) throws IOException, Exception {
        String urlHRClient = clients + "dropdb";
        Response responseHRClient = Http.post(urlHRClient,tokenAsJSON);
        if(responseHRClient.code() != HttpServletResponse.SC_OK) {
            responseHRClient.close();
            throw new Exception("Couldn't drop db on client service.");
        }
        responseHRClient.close();
        
        String urlHRPT = pts + "dropdb";
        Response responseHRPT = Http.post(urlHRPT,tokenAsJSON);
        if(responseHRPT.code() != HttpServletResponse.SC_OK) {
            responseHRPT.close();
            throw new Exception("Couldn't drop db on personaltrainer service.");
        }
        responseHRPT.close();
        
        String urlCore = core + "dropdb";
        Response responseCore = Http.post(urlCore,tokenAsJSON);
        if(responseCore.code() != HttpServletResponse.SC_OK) {
            responseCore.close();
            throw new Exception("Couldn't drop db on core service.");
        }
        responseCore.close();
        
        String urlRequest = requests + "dropdb";
        Response responseRequest = Http.post(urlRequest,tokenAsJSON);
        if(responseRequest.code() != HttpServletResponse.SC_OK) {
            responseRequest.close();
            throw new Exception("Couldn't drop db on requests service.");
        }
        responseRequest.close();
        
        String urlNotification = notifications + "dropdb";
        Response responseNotification = Http.post(urlNotification,tokenAsJSON);
        if(responseNotification.code() != HttpServletResponse.SC_OK) {
            responseNotification.close();
            throw new Exception("Couldn't drop db on notifications service.");
        }
        responseNotification.close();
        
        return "\"Databases were dropped with success.\"";
    }

    /**
     * Create schemas of databases.
     * 
     * @param tokenAsJson Admin token.
     * @return The status of the operation.
     * @throws IOException if something fails on trying contact with external services.
     * @throws Exception if something fails.
     */
    @Override
    public String createdbs(String tokenAsJSON) throws IOException, Exception {
        String urlHRClient = clients + "createdb";
        Response responseHRClient = Http.post(urlHRClient,tokenAsJSON);
        if(responseHRClient.code() != HttpServletResponse.SC_OK) {
            responseHRClient.close();
            throw new Exception("Couldn't create db on client service.");
        }
        responseHRClient.close();
        
        String urlHRPT = pts + "createdb";
        Response responseHRPT = Http.post(urlHRPT,tokenAsJSON);
        if(responseHRPT.code() != HttpServletResponse.SC_OK) {
            responseHRPT.close();
            throw new Exception("Couldn't create db on personaltrainer service.");
        }
        responseHRPT.close();
        
        String urlCore = core + "createdb";
        Response responseCore = Http.post(urlCore,tokenAsJSON);
        if(responseCore.code() != HttpServletResponse.SC_OK) {
            responseCore.close();
            throw new Exception("Couldn't create db on core service.");
        }
        responseCore.close();
        
        String urlRequest = requests + "createdb";
        Response responseRequest = Http.post(urlRequest,tokenAsJSON);
        if(responseRequest.code() != HttpServletResponse.SC_OK) {
            responseRequest.code();
            throw new Exception("Couldn't create db on requests service.");
        }
        responseRequest.code();
        
        String urlNotification = notifications + "createdb";
        Response responseNotification = Http.post(urlNotification,tokenAsJSON);
        if(responseNotification.code() != HttpServletResponse.SC_OK) {
            responseNotification.close();
            throw new Exception("Couldn't create db on notifications service.");
        }
        responseNotification.close();
        
        return "\"Databases were created with success.\"";
    }
    
    /**
     * Get all pending requests of a Personal Trainer by Personal Trainer username.
     * 
     * @param usernameAsJson Personal Trainer username.
     * @return All pending requests of a Personal Trainer.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String listClientRequestsByPersonalTrainer(String usernameAsJSON) throws IOException{
        String url = requests + "listRequestsOfPersonalTrainer";
        Response response = Http.post(url,usernameAsJSON);
        String data = response.body().string();
        response.close();
        return data;
    }
    
    /**
     * Get all Client requests by Client username.
     * 
     * @param usernameAsJSON Client username.
     * @return All Client requests.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String listClientRequestsByClient(String usernameAsJSON) throws IOException{
        String url = requests + "listRequestsOfClient";
        Response response = Http.post(url,usernameAsJSON);
        String data = response.body().string();
        response.close();
        return data;
    }
    
    /**
     * Get Client username by Request id.
     * 
     * @param requestIdAsJson Request id.
     * @return Client username.
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String getUsernameByRequestId(String requestIdAsJson) throws IOException{
        String url = requests + "getUsernameByRequestId";
        Response response = Http.post(url,requestIdAsJson);
        String data = response.body().string();
        response.close();
        return data;
    }
    
    /**
     * The state of Client submitted a classification.
     * 
     * @param data Client username.
     * @return True if submitted classification, otherwise False. 
     * @throws IOException if something fails on trying contact with external services.
     */
    @Override
    public String hasSubmittedClassification(String json) throws IOException{
        String url = pts + "hasSubmittedClassification";
        Response response = Http.post(url, json);
        String data = response.body().string();
        response.close();
        return data;
    }
}
