/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package requests;

import java.util.List;
import javax.ejb.Local;
import org.orm.PersistentException;

/**
 *
 * @author josepereira
 */
@Local
public interface RequestFacadeBeanLocal {
    String sayHello(String name);
    void updateUserToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, TokenIsInvalidException, UserDoesNotExistException, PersistentException;
    void createClient(String usernameAndTokenAsJSon) throws JsonKeyInFaultException, PersistentException, UserAlreadyExistsException, ClientAlreadyExistsException;
    void createPersonalTrainer(String usernameAndTokenAsJSon) throws JsonKeyInFaultException, PersistentException, UserAlreadyExistsException, PersonalTrainerDoesNotExistException, PersonalTrainerAlreadyExistsException;
    void submitRequest(String requestInfoAsJSON) throws JsonKeyInFaultException, TokenIsInvalidException, UserDoesNotExistException, PersistentException, ClientDoesNotExistException, PersonalTrainerDoesNotExistException;
    void replyToRequest(String requestIdAndResponseAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerDoesNotExistException, RequestDoesNotExistException, UserDoesNotExistException, TokenIsInvalidException;
    String listClientRequestsByPersonalTrainer(String usernameAsJSON) throws JsonKeyInFaultException, TokenIsInvalidException, UserDoesNotExistException, PersistentException, PersonalTrainerDoesNotExistException;
    String listClientRequestsByClient(String usernameAsJSON) throws TokenIsInvalidException, UserDoesNotExistException, PersistentException, JsonKeyInFaultException, ClientDoesNotExistException;
}
