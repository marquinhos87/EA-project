/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import okhttp3.Response;
import parseJSON.ResponseJSON;
import utils.Http;

/**
 *
 * @author ricardo
 */
public class WorkThread extends Thread { 
    
    private static final String url = "http://37.189.223.35:8081/GymAtHome/api/";
    private final int i;
    private final Gson gson;
    private final FileWriter cf;
    private final FileWriter ptf;

    public WorkThread(int i, Gson gson, FileWriter cf, FileWriter ptf) {
        this.i = i;
        this.gson = gson;
        this.cf = cf;
        this.ptf = ptf;
    }
        
    @Override
    public void run() {
        try {
            String clientUsername = "c" + i;
            String clientJSON =
                    "{" +
                    "\"username\": \"" + clientUsername + "\", " +
                    "\"password\": \"password\", " +
                    "\"name\": \"" + clientUsername + "\", " +
                    "\"email\": \"" + clientUsername + "@gmail.com\", " +
                    "\"sex\": \"M\", " +
                    "\"birthday\": \"1997-06-19\", " +
                    "\"height\": 190, " +
                    "\"weight\": 87 " +
                    "}";
            
            Response response = Http.post(url + "createClient", clientJSON);
            String data = response.body().string();
            response.close();
            if (gson.fromJson(data, ResponseJSON.class).code != 1) {
                System.err.println("Could not create client - " + clientUsername + " - HTTP status code != 200");
                System.exit(1);
            }
            String clientToken = gson.fromJson(data, ResponseJSON.class).data.getAsJsonObject().get("token").getAsString();
            //System.out.println("client token = " + clientToken);
            cf.write("{ \"username\": \"" + clientUsername + "\", \"password\": \"password\" }\n");
            
            String ptUsername= "pt" + i;
            String ptJSON =
                    "{ " +
                    "\"username\": \"" + ptUsername + "\", " +
                    "\"password\": \"password\", " +
                    "\"name\": \"" + ptUsername + "\", " +
                    "\"email\": \"" + ptUsername + "@gmail.com\", " +
                    "\"birthday\": \"1998-06-29\", " +
                    "\"sex\": \"m\", " +
                    "\"skill\": \"cardio\", " +
                    "\"price\": 155.99" +
                    "}";
            
            response = Http.post(url + "createPersonalTrainer", ptJSON);
            data = response.body().string();
            response.close();
            if (gson.fromJson(data, ResponseJSON.class).code != HttpServletResponse.SC_OK) {
                System.err.println("Could not create personal trainer - " + ptUsername + " - HTTP status code != 200");
                System.exit(1);
            }
            String ptToken = gson.fromJson(data, ResponseJSON.class).data.getAsJsonObject().get("token").getAsString();
            //System.out.println("pt token = " + ptToken);
            ptf.write("{ \"username\": \"" + ptUsername + "\", \"password\": \"password\" }\n");
            
            
            String fstJSON =
                    "{ " +
                    "\"username\" : \"" + ptUsername + "\", " +
                    "\"token\" : \"" + ptToken + "\", " +
                    "\"clientUsername\": \"" + clientUsername + "\", " +
                    "\"week\" : { " +
                    "\"workouts\": " +
                    "[ " +
                    
                    "{ " + // Workout 1
                    "\"designation\": \"cardio\", " +
                    "\"weekDay\": 2, " +
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
                    "}, " +
                    
                    "{ " + // Workout 2
                    "\"designation\": \"musculação\", " +
                    "\"weekDay\": 6, " +
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
            response.close();
            if (gson.fromJson(data, ResponseJSON.class).code != HttpServletResponse.SC_OK) {
                System.err.println("Could not create first week, HTTP status code != 200");
                System.err.println(gson.fromJson(data, ResponseJSON.class).msg.toString());
                System.exit(1);
            } 
            
           
            String weekJSON =
                    "{ " +
                    "\"username\" : \"" + ptUsername + "\", " +
                    "\"token\" : \"" + ptToken + "\", " +
                    "\"clientUsername\": \"" + clientUsername + "\", " +
                    "\"planId\": " + (i+1) + ", " +
                    "\"week\" : { " +
                    "\"workouts\": " +
                    "[ " +
                    
                    "{ " +  // workout 1
                    "\"designation\": \"musculação\", " +
                    "\"weekDay\": 4, " +
                    "\"tasks\": " +
                    "[ " +
                    
                    "{ " + // Task 1
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
                    "}, " +
                    
                    "{ " + // Task 2
                    "\"designation\": \"Correr Rápido\", " +
                    "\"rest\": \"2 min\", " +
                    "\"duration\": \"30 min\", " +
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
                    "}, " +
              
                    "{ " +  // workout 2
                    "\"designation\": \"musculação\", " +
                    "\"weekDay\": 7, " +
                    "\"tasks\": " +
                    "[ " +
                    
                    "{ " + // Task 1
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
                    "}, " +
                    
                    "{ " + // Task 2
                    "\"designation\": \"Correr Rápido\", " +
                    "\"rest\": \"2 min\", " +
                    "\"duration\": \"30 min\", " +
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
                    "}" +
                    
                    "] " +
                    "} " +
                    "}";
            
            for (int j=0; j<5; j++) {
                response = Http.post(url + "createWeek", weekJSON);
                data = response.body().string();
                response.close();
                if (gson.fromJson(data, ResponseJSON.class).code != HttpServletResponse.SC_OK) {
                    System.err.println("Could not create week on iteration - " + i + " - HTTP status code != 200");
                    System.err.println(gson.fromJson(data, ResponseJSON.class).msg.toString());
                    System.exit(1);
                }
            }
            
           
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
