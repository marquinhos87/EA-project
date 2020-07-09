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
public class WorkoutDontBelongToUserException extends Exception {
    
    /**
     * WorkoutDontBelongToUserException parameterized constructor.
     * 
     * @param message Error message.
     */
    public WorkoutDontBelongToUserException(String message) {
        super(message);
    }

    /**
     * WorkoutDontBelongToUserException empty constructor.
     */
    public WorkoutDontBelongToUserException() {
        super();
    }
}
