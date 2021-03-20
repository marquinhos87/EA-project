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
    
    public static final String url = "http://localhost:8081/GymAtHome/api/";
    public static final int N = 10;   
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
       
        
        
        File f = new File("../../../docker/jmeter/login/clients-data.csv");
        if (f.delete());
        FileWriter cf_login = new FileWriter(f);
        
        f = new File("../../../docker/jmeter/register/clients-data.csv");
        if (f.delete());
        FileWriter cf_register = new FileWriter(f);
        
        
        
        f = new File("../../../docker/jmeter/login/pts-data.csv");
        if (f.delete());
        FileWriter ptf_login = new FileWriter(f);
        
        f = new File("../../../docker/jmeter/register/pts-data.csv");
        if (f.delete());
        FileWriter ptf_register = new FileWriter(f);
        
        
       
        for(int i=0; i<N; i++) {
            WorkThread wh = new WorkThread(i, gson, cf_login, cf_register, ptf_login, ptf_register);
            wh.run(); // eu sei que aqui está o run() que é diferente do start() - foi propositado (eu quero sequencial aqui)...
            if (i%50 == 0) System.out.println("written " + i + " clients/ pts");
        } 
        

        cf_login.close();
        cf_register.close();
        ptf_login.close();
        ptf_register.close();
        System.out.println("DONE!!!");
    
    }
}
