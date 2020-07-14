/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import okhttp3.Response;
import parseJSON.ResponseJSON;
import utils.Http;

/**
 *
 * @author ricardo
 */
public class Main {
    
    private static final String url = "http://37.189.223.35:8081/GymAtHome/api/";
       
    public static void main(String[] args) throws IOException {
        
        Response response = Http.post(url + "createdbs", "{ \"token\": \"admin\" }");
        if (response.code() != HttpServletResponse.SC_OK) {
            System.err.println("Could now create DBs, HTTP status code != 200");
            System.exit(1);
        } 

        String clientJSON = 
        "{" + 
            "\"username\": \"josepereira\", " +
            "\"password\": \"password\", " +
            "\"name\": \"José Pereira\", " +
            "\"email\": \"jose@email.com\", " +
            "\"sex\": \"M\", " +
            "\"birthday\": \"1997-06-19\", " +
            "\"height\": 190, " +
            "\"weight\": 87 " +
        "}";

        String ptJSON = 
        "{ " +
            "\"username\": \"ricardao\", " +
            "\"name\": \"ricardo\", " +
            "\"email\": \"rpetronilho98@gmail.com\", " +
            "\"password\": \"password\", " +
            "\"birthday\": \"1998-06-29\", " +
            "\"sex\": \"m\", " +
            "\"skill\": \"cardio\", " +
            "\"price\": 155.99" +
        "}";
        
        
        Gson gson = new Gson();
        String clientToken = gson.fromJson(Http.post(url + "createClient", clientJSON).body().string(), ResponseJSON.class).data.getAsJsonObject().get("token").getAsString();
        System.out.println("client token = " + clientToken);
        String ptToken = gson.fromJson(Http.post(url + "createPersonalTrainer", ptJSON).body().string(), ResponseJSON.class).data.getAsJsonObject().get("token").getAsString();
        System.out.println("pt token = " + ptToken);       
    
        String fstJSON = 
        "{ " +
            "\"username\" : \"ricardao\", " +
            "\"token\" : \"" + ptToken + "\", " +
            "\"clientUsername\": \"josepereira\", " +
            "\"week\" : { " +
                "\"workouts\": " +
                    "[ " +
                    "{ " +
                        "\"designation\": \"cardio\", " +
                        "\"weekDay\": 2, " +
                        "\"done\": false, " +
                        "\"tasks\": " +
                            "[ " +
                            "{ " +
                                "\"designation\": \"Correr\", " +
                                "\"rest\": \"2 min\", " +
                                "\"duration\": \"15 min\", " +
                                "\"equipment\": \"passadeira\", " +
                                "\"series\": " +
                                    "[ " +
                                    "{ " +
                                        "\"description\": \"Correr\", " +
                                        "\"repetitions\": \"10 min\", " +
                                        "\"rest\": \"2 min\" " +
                                    "} " +
                                    "] " +
                            "} " +
                            "] " +
                    "} " +
                    "] " +
                "} " + 
        "}";
        
        response = Http.post(url + "createWeek", fstJSON);
        if (response.code() != HttpServletResponse.SC_OK) {
            System.err.println("Could now create week, HTTP status code != 200");
            System.exit(1);
        } 
        
        String weekJSON = 
        "{ " +
            "\"username\" : \"ricardao\", " +
            "\"token\" : \"" + ptToken + "\", " +
            "\"planId\": 1, " +
            "\"week\" : { " +
                "\"workouts\": " +
                    "[ " +
                    "{ " +
                        "\"designation\": \"cardio\", " +
                        "\"weekDay\": 2, " +
                        "\"done\": false, " +
                        "\"tasks\": " +
                            "[ " +
                            "{ " +
                                "\"designation\": \"Correr\", " +
                                "\"rest\": \"2 min\", " +
                                "\"duration\": \"15 min\", " +
                                "\"equipment\": \"passadeira\", " +
                                "\"series\": " +
                                    "[ " +
                                    "{ " +
                                        "\"description\": \"Correr\", " +
                                        "\"repetitions\": \"10 min\", " +
                                        "\"rest\": \"2 min\" " +
                                    "} " +
                                    "] " +
                            "} " +
                            "] " +
                    "} " +
                    "] " +
                "} " + 
        "}";
        
        for (int i=0; i<5; i++) {
            response = Http.post(url + "createWeek", weekJSON);
            if (response.code() != HttpServletResponse.SC_OK) {
                System.err.println("Could now create week on iteration - " + i + " - , HTTP status code != 200");
                System.exit(1);
            } 
        }

        System.out.println("DONE!!!");
    
    }
    
    
}
