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
public class PersonalTrainerNotExistsException extends Exception {
    public PersonalTrainerNotExistsException(String msg) {
        super(msg);
    }
}
