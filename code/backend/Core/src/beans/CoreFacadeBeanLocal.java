package beans;

import exceptions.*;
import org.orm.PersistentException;

import javax.ejb.Local;

@Local
public interface CoreFacadeBeanLocal {

    /**
     * Create a Client.
     *
     * @param usernameAsJson Client username and token as a json string.
     */
    void createClient(String usernameAsJson) throws PersistentException, ClientAlreadyExistsException;

    /**
     * Create a PersonalTrainer.
     *
     * @param usernameAsJson PersonalTrainer username and token as a json string.
     */
    void createPersonalTrainer(String usernameAsJson) throws PersistentException, PersonalTrainerAlreadyExistsException;

    /**
     *
     * @param usernameAndTokenAsJson Client username and token as a json string.
     */
    void updateTokenClient(String usernameAndTokenAsJson) throws PersistentException, ClientDontExistsException, JsonKeyInFaultException, InvalidTokenException;

    /**
     *
     * @param usernameAndTokenAsJson PersonalTrainer username and token as a json string.
     */
    void updateTokenPersonalTrainer(String usernameAndTokenAsJson) throws PersistentException, PersonalTrainerDontExistsException, JsonKeyInFaultException, InvalidTokenException;

    /**
     *
     * @param usernameAndWeekAsJSON
     */
    String getWeekByClient(String usernameAndWeekAsJSON) throws JsonKeyInFaultException, PersistentException, ClientDontExistsException, InvalidTokenException;

    /**
     *
     * @param usernameAndWeekAsJSON
     */
    String getWeekByPersonalTrainer(String usernameAndWeekAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerDontExistsException, InvalidTokenException;

    /**
     *
     * @param usernameAndWorkoutIdAsJSON
     */
    void finishWorkout(String usernameAndWorkoutIdAsJSON) throws PersistentException, ClientDontExistsException, InvalidTokenException, JsonKeyInFaultException;

    /**
     *
     * @param weekAsJson
     */
    void createWeek(String weekAsJson) throws JsonKeyInFaultException, PersonalTrainerDontExistsException, PersistentException, InvalidTokenException;
}