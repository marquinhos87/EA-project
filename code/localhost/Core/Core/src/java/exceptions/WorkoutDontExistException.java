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
public class WorkoutDontExistException extends Exception {
    
    /**
     * WorkoutDontExistException parameterized constructor.
     * 
     * @param message Error message.
     */
    public WorkoutDontExistException(String message) {
        super(message);
    }

    /**
     * WorkoutDontExistException empty constructor.
     */
    public WorkoutDontExistException() {
        super();
    }
}
