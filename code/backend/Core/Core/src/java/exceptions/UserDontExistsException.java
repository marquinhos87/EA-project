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
public class UserDontExistsException extends Exception {
    
    /**
     * UserDontExistsException parameterized constructor.
     * 
     * @param message Error message.
     */
    public UserDontExistsException(String message) {
        super(message);
    }

    /**
     * UserDontExistsException empty constructor.
     */
    public UserDontExistsException() {
        super();
    }
}
