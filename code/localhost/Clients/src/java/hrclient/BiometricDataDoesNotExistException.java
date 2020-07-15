/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrclient;

/**
 *
 * @author josepereira
 */
public class BiometricDataDoesNotExistException extends Exception{
    public BiometricDataDoesNotExistException(){
        super();
    }
    
    public BiometricDataDoesNotExistException(String message){
        super(message);
    }
}
