/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

/**
 *
 * @author josepereira
 */
public class IdDoesNotExistException extends Exception{
    public IdDoesNotExistException(){
        super();
    }
    
    public IdDoesNotExistException(String message){
        super(message);
    }
}
