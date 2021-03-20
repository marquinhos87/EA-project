/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notifications;

/**
 *
 * @author ricardo
 */
public class UserNotExistsException extends Exception {

    public UserNotExistsException(String msg) {
        super(msg);
    }
    
}
