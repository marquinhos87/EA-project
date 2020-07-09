/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.Task;
import core.Week;
import core.Workout;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import parseJSON.deserializer.*;

/**
 *
 * @author joaomarques
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .registerTypeAdapter(Week.class, new WeekDeserializer())
                .registerTypeAdapter(Workout.class, new WorkoutDeserializer())
                .registerTypeAdapter(Task.class, new TaskDeserializer())
                .create();
        
        String json = new String(Files.readAllBytes(Paths.get("/Users/joaomarques/Universidade/MIEI/4º ano/2º semestre/Projeto EA/EA-project/code/backend/Core/Core/src/java/parseJSON/week.json")));
        
        Week week = gson.fromJson(json, Week.class);
       
        System.out.println(week);
    }
}
