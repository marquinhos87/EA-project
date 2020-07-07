package beans;

import exceptions.*;
import org.orm.PersistentException;

import javax.ejb.Local;

@Local
public interface CoreFacadeBeanLocal {

    /**
     * Create a Client.
     *
     * @param usernameAsJson User username and token as a json string.
     * @throws PersistentException
     * @throws UserAlreadyExistsException
     */
    void createUserToken(String usernameAsJson) throws PersistentException, UserAlreadyExistsException;

    /**
     *
     * @param usernameAndTokenAsJson User username and token as a json string.
     * @throws PersistentException
     * @throws JsonKeyInFaultException
     * @throws InvalidTokenException
     * @throws UserTokenDontExistsException
     */
    void updateToken(String usernameAndTokenAsJson) throws PersistentException, JsonKeyInFaultException, InvalidTokenException, UserTokenDontExistsException;

    /**
     *
     * @param usernameAndWeekAsJSON
     * @throws JsonKeyInFaultException
     * @throws PersistentException
     * @throws ClientDontExistsException
     * @throws UserTokenDontExistsException
     * @throws InvalidTokenException
     */
    String getWeekByClient(String usernameAndWeekAsJSON) throws JsonKeyInFaultException, PersistentException, ClientDontExistsException, InvalidTokenException, UserTokenDontExistsException;

    /**
     *
     * @param usernameAndWeekAsJSON
     * @throws JsonKeyInFaultException
     * @throws PersistentException
     * @throws PersonalTrainerDontExistsException
     * @throws UserTokenDontExistsException
     * @throws InvalidTokenException
     */
    String getWeekByPersonalTrainer(String usernameAndWeekAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerDontExistsException, InvalidTokenException, UserTokenDontExistsException;

    /**
     *
     * @param usernameAndWorkoutIdAsJSON
     * @throws PersistentException
     * @throws ClientDontExistsException
     * @throws InvalidTokenException
     * @throws JsonKeyInFaultException
     * @throws UserTokenDontExistsException
     */
    void finishWorkout(String usernameAndWorkoutIdAsJSON) throws PersistentException, ClientDontExistsException, InvalidTokenException, JsonKeyInFaultException, UserTokenDontExistsException;

    /**
     *
     * @param weekAsJson
     * @throws JsonKeyInFaultException
     * @throws PersonalTrainerDontExistsException
     * @throws PersistentException
     * @throws InvalidTokenException
     * @throws UserTokenDontExistsException
     */
    void createWeek(String weekAsJson) throws JsonKeyInFaultException, PersonalTrainerDontExistsException, PersistentException, InvalidTokenException, UserTokenDontExistsException;
}