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
public class PlanDontExistException extends Exception {
    
    /**
     * PlanDontExistException parameterized constructor.
     * 
     * @param message Error message.
     */
    public PlanDontExistException(String message) {
        super(message);
    }

    /**
     * PlanDontExistException empty constructor.
     */
    public PlanDontExistException() {
        super();
    }
}
