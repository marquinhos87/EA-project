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
public class PersonalTrainerDontExistsException extends Exception {
    
    /**
     * PersonalTrainerDontExistsException parameterized constructor.
     * 
     * @param message Error message.
     */
    public PersonalTrainerDontExistsException(String message) {
        super(message);
    }

    /**
     * PersonalTrainerDontExistsException empty constructor.
     */
    public PersonalTrainerDontExistsException() {
        super();
    }
}
