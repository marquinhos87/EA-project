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
public class UserAlreadyExistsException extends Exception {
    
    /**
     * UserAlreadyExistsException parameterized constructor.
     * 
     * @param message Error message.
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * UserAlreadyExistsException empty constructor.
     */
    public UserAlreadyExistsException() {

    }
}
