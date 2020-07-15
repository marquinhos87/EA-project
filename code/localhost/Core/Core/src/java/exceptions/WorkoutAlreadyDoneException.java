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
public class WorkoutAlreadyDoneException extends Exception {
    
    /**
     * WorkoutAlreadyDoneException parameterized constructor.
     * 
     * @param message Error message.
     */
    public WorkoutAlreadyDoneException(String message) {
        super(message);
    }

    /**
     * WorkoutAlreadyDoneException empty constructor.
     */
    public WorkoutAlreadyDoneException() {
        super();
    }
}
