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
public class NotificationDoesNotBelongToUser extends Exception {

    public NotificationDoesNotBelongToUser(String msg) {
        super(msg);
    }
    
}
