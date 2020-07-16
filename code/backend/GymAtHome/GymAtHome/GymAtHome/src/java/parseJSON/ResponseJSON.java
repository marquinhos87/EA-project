/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parseJSON;

import com.google.gson.JsonElement;

/**
 *
 * @author joaomarques
 */
public class ResponseJSON {
    public String status;
    public int code;
    public String msg;
    public JsonElement data;
}
