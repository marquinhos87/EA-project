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
public class UsernameDontBelongToClientException extends Exception {
    
    /**
     * UsernameDontBelongToClientException parameterized constructor.
     * 
     * @param message Error message.
     */
    public UsernameDontBelongToClientException(String message) {
        super(message);
    }

    /**
     * UsernameDontBelongToClientException empty constructor.
     */
    public UsernameDontBelongToClientException() {
        super();
    }
}
