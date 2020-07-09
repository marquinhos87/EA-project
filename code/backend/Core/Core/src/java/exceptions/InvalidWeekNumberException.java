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
public class InvalidWeekNumberException extends Exception {

    /**
     * InvalidWeekNumberException parameterized constructor.
     * 
     * @param message Error message.
     */
    public InvalidWeekNumberException(String message) {
        super(message);
    }
    
    /**
     * InvalidWeekNumberException empty constructor.
     */
    public InvalidWeekNumberException() {
        super();
    }
    
}
