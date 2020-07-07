package beans;

import exceptions.*;
import org.orm.PersistentException;

import javax.ejb.Local;

@Local
public interface CoreFacadeBeanLocal {

    /**
     * Create a User.
     *
     * @param usernameAsJson User username and token as a json string.
     * @throws UserAlreadyExistsException
     * @throws JsonKeyInFaultException
     */
    void createUserToken(String usernameAsJson) throws UserAlreadyExistsException, JsonKeyInFaultException;

    /**
     * Update a User token.
     *
     * @param usernameAndTokenAsJson User username and token as a json string.
     * @throws JsonKeyInFaultException
     * @throws InvalidTokenException
     * @throws UserDontExistsException
     */
    void updateToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, InvalidTokenException, UserDontExistsException;

    /**
     *
     * @param usernameAndWeekAsJSON
     * @throws JsonKeyInFaultException
     * @throws PersistentException
     * @throws ClientDontExistsException
     * @throws UserDontExistsException
     * @throws InvalidTokenException
     */
    String getWeekByClient(String usernameAndWeekAsJSON) throws JsonKeyInFaultException, PersistentException, ClientDontExistsException, InvalidTokenException, UserDontExistsException;

    /**
     *
     * @param usernameAndWeekAsJSON
     * @throws JsonKeyInFaultException
     * @throws PersistentException
     * @throws PersonalTrainerDontExistsException
     * @throws UserDontExistsException
     * @throws InvalidTokenException
     */
    String getWeekByPersonalTrainer(String usernameAndWeekAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerDontExistsException, InvalidTokenException, UserDontExistsException, ClientDontExistsException;

    /**
     *
     * @param usernameAndWorkoutIdAsJSON
     * @throws PersistentException
     * @throws ClientDontExistsException
     * @throws InvalidTokenException
     * @throws JsonKeyInFaultException
     * @throws UserDontExistsException
     */
    void finishWorkout(String usernameAndWorkoutIdAsJSON) throws PersistentException, ClientDontExistsException, InvalidTokenException, JsonKeyInFaultException, UserDontExistsException, WorkoutDontExistException, WorkoutDontBelongToUserException, WorkoutAlreadyDoneException;

    /**
     *
     * @param weekAsJson
     * @throws JsonKeyInFaultException
     * @throws PersonalTrainerDontExistsException
     * @throws PersistentException
     * @throws InvalidTokenException
     * @throws UserDontExistsException
     */
    void createWeek(String weekAsJson) throws JsonKeyInFaultException, PersonalTrainerDontExistsException, PersistentException, InvalidTokenException, UserDontExistsException;
}