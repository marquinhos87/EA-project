/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import okhttp3.Response;
import parseJSON.ResponseJSON;
import utils.Http;

/**
 *
 * @author ricardo
 */
public class JMeter {
    
    private static final String url = "http://37.189.223.35:8081/GymAtHome/api/";
    public static final int N = 30;   
    private static final Gson gson = new Gson();
    
    public static void main(String[] args) throws IOException {
        
        Response response = Http.post(url + "createdbs", "{ \"token\": \"admin\" }");
        String data = response.body().string();
        response.close();
        if (gson.fromJson(data, ResponseJSON.class).code != HttpServletResponse.SC_OK) {
            System.err.println("Could not create DBs, HTTP status code != 200");
            System.err.println(gson.fromJson(data, ResponseJSON.class).msg);
            System.exit(1);
        } 
       
        File clientsFile = new File("../../../docker/jmeter/clients-data.csv");
        if (clientsFile.delete());
        FileWriter cf = new FileWriter(clientsFile);
        
        File ptsFile = new File("../../../docker/jmeter/pts-data.csv");
        if (ptsFile.delete());
        FileWriter ptf = new FileWriter(ptsFile);
       
        for(int i=0; i<N; i++) {
            WorkThread wh = new WorkThread(i, gson, cf, ptf);
            wh.run(); // eu sei que aqui está o run() que é diferente do start() - foi propositado (eu quero sequencial aqui)...
        } 
        

        cf.close();
        ptf.close();
        System.out.println("DONE!!!");
    
    }
}
