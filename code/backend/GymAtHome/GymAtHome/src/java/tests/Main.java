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
            System.err.println("Could not create DBs, HTTP status code != 200");
            System.exit(1);
        } 

        String clientUsername = "cjosepereira";
        String clientJSON = 
        "{" + 
            "\"username\": \"" + clientUsername + "\", " +
            "\"password\": \"password\", " +
            "\"name\": \"José Pereira\", " +
            "\"email\": \"jose@email.com\", " +
            "\"sex\": \"M\", " +
            "\"birthday\": \"1997-06-19\", " +
            "\"height\": 190, " +
            "\"weight\": 87 " +
        "}";

        String ptUsername= "ptricardao";
        String ptJSON =  
        "{ " +
            "\"username\": \"" + ptUsername + "\", " +
            "\"name\": \"ricardo\", " +
            "\"email\": \"rpetronilho98@gmail.com\", " +
            "\"password\": \"password\", " +
            "\"birthday\": \"1998-06-29\", " +
            "\"sex\": \"m\", " +
            "\"skill\": \"cardio\", " +
            "\"price\": 155.99" +
        "}";
        
        
        Gson gson = new Gson();
        
        response = Http.post(url + "createClient", clientJSON);
        String data = response.body().string();
        if (gson.fromJson(data, ResponseJSON.class).code != 1) {
            System.err.println("Could not create client, HTTP status code != 200");
            System.exit(1);
        } 
        String clientToken = gson.fromJson(data, ResponseJSON.class).data.getAsJsonObject().get("token").getAsString();
        System.out.println("client token = " + clientToken);
        
        response = Http.post(url + "createPersonalTrainer", ptJSON);
        data = response.body().string();
        if (gson.fromJson(data, ResponseJSON.class).code != HttpServletResponse.SC_OK) {
            System.err.println("Could not create personal trainer, HTTP status code != 200");
            System.exit(1);
        } 
        String ptToken = gson.fromJson(data, ResponseJSON.class).data.getAsJsonObject().get("token").getAsString();
        System.out.println("pt token = " + ptToken);       
    
        String fstJSON = 
        "{ " +
            "\"username\" : \"" + ptUsername + "\", " +
            "\"token\" : \"" + ptToken + "\", " +
            "\"clientUsername\": \"" + clientUsername + "\", " +
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
        data = response.body().string();
        if (gson.fromJson(data, ResponseJSON.class).code != HttpServletResponse.SC_OK) {
            System.err.println("Could not create week, HTTP status code != 200");
            System.exit(1);
        } 
        
        String weekJSON = 
        "{ " +
            "\"username\" : \"" + ptUsername + "\", " +
            "\"token\" : \"" + ptToken + "\", " +
            "\"clientUsername\": \"" + clientUsername + "\", " +
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
            data = response.body().string();
            if (gson.fromJson(data, ResponseJSON.class).code != HttpServletResponse.SC_OK) {
                System.err.println("Could not create week on iteration - " + i + " - HTTP status code != 200");
                System.exit(1);
            } 
        }

        System.out.println("DONE!!!");
    
    }
    
    
}
