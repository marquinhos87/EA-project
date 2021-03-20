/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author joaomarques
 */
public class JsonKeyInFaultException extends Exception {
    
    /**
     * JsonKeyInFaultException parameterized constructor.
     * 
     * @param message Error message.
     */
    public JsonKeyInFaultException(String message) {
        super(message);
    }

    /**
     * JsonKeyInFaultException empty constructor.
     */
    public JsonKeyInFaultException() {

    }
}
