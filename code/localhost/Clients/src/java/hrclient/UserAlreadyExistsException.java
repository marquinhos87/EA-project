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
public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(){
        super();
    }
    
    public UserAlreadyExistsException(String message){
        super(message);
    }
    
}
