package beans;

import exceptions.ClientAlreadyExistsException;
import exceptions.ClientDontExistsException;
import exceptions.PersonalTrainerAlreadyExistsException;
import exceptions.PersonalTrainerDontExistsException;
import org.orm.PersistentException;

import javax.ejb.Local;

@Local
public interface CoreFacadeBeanLocal {

	/**
	 * 
	 * @param usernameAsJson
	 */
	void createClient(String usernameAsJson) throws PersistentException, ClientAlreadyExistsException;

	/**
	 * 
	 * @param usernameAsJson
	 */
	void createPersonalTrainer(String usernameAsJson) throws PersistentException, PersonalTrainerAlreadyExistsException;

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	void updateTokenClient(String usernameAndTokenAsJson) throws PersistentException, ClientDontExistsException;

	/**
	 *
	 * @param usernameAndTokenAsJson
	 */
	void updateTokenPersonalTrainer(String usernameAndTokenAsJson) throws PersistentException, PersonalTrainerDontExistsException;

	/**
	 * 
	 * @param usernameAndWeekAsJSON
	 */
	String getPlan(String usernameAndWeekAsJSON);

	/**
	 * 
	 * @param usernameAndWorkoutIdAsJSON
	 */
	void finishWorkout(String usernameAndWorkoutIdAsJSON);

	/**
	 * 
	 * @param weekAsJson
	 */
	void createWeek(String weekAsJson);
}