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
public class GymAtHomeException extends Exception {
    
    /**
     * GymAtHomeException parameterized constructor.
     * 
     * @param message Error message.
     */
    public GymAtHomeException(String message) {
        super(message);
    }

    
    /**
     * ClientAlreadyHasAnPlanException empty constructor.
     */
    public GymAtHomeException() {
        super();
    }
}
