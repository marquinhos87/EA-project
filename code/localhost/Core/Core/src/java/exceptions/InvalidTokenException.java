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
public class InvalidTokenException extends Exception {
    
    /**
     * InvalidTokenException parameterized constructor.
     * 
     * @param message Error message.
     */
    public InvalidTokenException(String message) {
        super(message);
    }

    /**
     * InvalidTokenException empty constructor.
     */
    public InvalidTokenException() {

    }
}
