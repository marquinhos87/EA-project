/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrclient;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Date;

/**
 *
 * @author josepereira
 */
public class InfoClient implements JsonSerializer<Client> {

    @Override
    public JsonElement serialize(Client t, Type type, JsonSerializationContext jsc) {
        final JsonObject json = new JsonObject();
        json.addProperty("username", t.getUsername());
        json.addProperty("name", t.getName());
        json.addProperty("email", t.getEmail());
        json.addProperty("sex", t.getSex());
        json.addProperty("age", Utils.years(t.getBirthday(), new Date()));
        json.add("birthday", jsc.serialize(t.getBirthday()));
        
        int maxId = -1;
        BiometricData biometricData = null;
        BiometricData[] biometricDatas = t.biometricDatas.toArray();
        for(int i = 0; i < biometricDatas.length; i++){
            if(biometricDatas[i].getID() > maxId){
                maxId = i;
                biometricData = biometricDatas[i];
            }
        }
        
        json.add("biometricData" , jsc.serialize(biometricData));
        
        return json;
    }
    
}
