/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hrclient;

import javax.ejb.Local;
import org.orm.PersistentException;

/**
 *
 * @author josepereira
 */
@Local
public interface HRClientFacadeBeanLocal {
    String sayHello(String name);
    void updateUserToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, TokenIsInvalidException, UserDoesNotExistException, PersistentException;
    String createClient(String infoClientAsJSON) throws JsonKeyInFaultException, PersistentException, ClientAlreadyExistsException, UserAlreadyExistsException;
    void createUser(String usernameAndTokenAsJSON) throws JsonKeyInFaultException, PersistentException, UserAlreadyExistsException;
    String loginClient(String infoAsJSON) throws JsonKeyInFaultException, PersistentException, ClientDoesNotExistException, InvalidPasswordException;
    String getClientProfileByClient(String usernameAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException, UserDoesNotExistException, BiometricDataDoesNotExistException;
    String getClientProfileByPersonalTrainer(String usernameAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException, PersonalTrainerDoesNotExistException, UserDoesNotExistException, BiometricDataDoesNotExistException;
    void editClientProfile(String infoAsJSON) throws JsonKeyInFaultException, ClientDoesNotExistException, TokenIsInvalidException, PersistentException, UserDoesNotExistException;
    String getBiometricData(String usernameAsJSON) throws JsonKeyInFaultException, PersonalTrainerDoesNotExistException, TokenIsInvalidException, PersistentException, ClientDoesNotExistException, UserDoesNotExistException, BiometricDataDoesNotExistException;

}
