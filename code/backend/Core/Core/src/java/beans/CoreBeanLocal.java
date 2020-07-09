/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import exceptions.*;
import org.orm.PersistentException;

import javax.ejb.Local;

/**
 *
 * @author joaomarques
 */
@Local
public interface CoreBeanLocal {
    
    /**
     * Create a User.
     *
     * @param usernameAsJson User username and token as a json string.
     * @throws PersistentException if some error occur with hibernate
     * @throws UserAlreadyExistsException if users token already register
     * @throws JsonKeyInFaultException if has some keys in fault 
     */
    void createUserToken(String usernameAsJson) throws UserAlreadyExistsException, JsonKeyInFaultException, PersistentException;

    /**
     * Update a User token.
     *
     * @param usernameAndTokenAsJson User username and token as a json string.
     * @throws PersistentException if some error occur with hibernate
     * @throws JsonKeyInFaultException if has some keys in fault 
     * @throws InvalidTokenException if for given user the token is invalid
     * @throws UserDontExistsException if username given dont exists
     */
    void updateToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException, InvalidTokenException, UserDontExistsException, PersistentException;

    /**
     * Get a week of a Client plan.
     *
     * @param usernameAndWeekAsJSON Client's username and week as a json string.
     * @return The week asked by Client
     * @throws JsonKeyInFaultException if has some keys in fault 
     * @throws PersistentException if some error occur with hibernate
     * @throws ClientDontExistsException if Client dont exist
     * @throws UserDontExistsException if username given dont exists
     * @throws InvalidTokenException if for given user the token is invalid
     */
    String getWeekByClient(String usernameAndWeekAsJSON) throws JsonKeyInFaultException, PersistentException, ClientDontExistsException, InvalidTokenException, UserDontExistsException, InvalidWeekNumberException;

    /**
     * Get a week of a Client plan.
     *
     * @param usernameAndWeekAsJSON PersonalTrainer's username and week as a json string.
     * @return The week asked by PersonalTrainer
     * @throws JsonKeyInFaultException if has some keys in fault 
     * @throws PersistentException if some error occur with hibernate
     * @throws PersonalTrainerDontExistsException if PersonalTrainer dont exist
     * @throws UserDontExistsException if username given dont exists
     * @throws InvalidTokenException if for given user the token is invalid
     * @throws ClientDontExistsException if Client dont exist
     */
    String getWeekByPersonalTrainer(String usernameAndWeekAsJSON) throws JsonKeyInFaultException, PersistentException, PersonalTrainerDontExistsException, InvalidTokenException, UserDontExistsException, ClientDontExistsException, InvalidWeekNumberException;

    /**
     * Assign workouts as done.
     *
     * @param usernameAndWorkoutIdAsJSON Client's username and workout Id as a json string.
     * @throws PersistentException if some error occur with hibernate
     * @throws ClientDontExistsException if Client dont exist
     * @throws InvalidTokenException if for given user the token is invalid
     * @throws JsonKeyInFaultException if has some keys in fault 
     * @throws UserDontExistsException if username given dont exists
     * @throws WorkoutAlreadyDoneException if workout already done
     * @throws WorkoutDontBelongToUserException if workout dont belong to the given user
     * @throws WorkoutDontExistException if given workout dont exist
     */
    void finishWorkout(String usernameAndWorkoutIdAsJSON) throws PersistentException, ClientDontExistsException, InvalidTokenException, JsonKeyInFaultException, UserDontExistsException, WorkoutDontExistException, WorkoutDontBelongToUserException, WorkoutAlreadyDoneException;

    /**
     * Create a new week for an existing plan or create a new week for a new plan.
     *
     * @param weekAsJson Week info as a json string.
     * @throws JsonKeyInFaultException if has some keys in fault 
     * @throws PersonalTrainerDontExistsException if given PersonalTrainer dont exist
     * @throws PersistentException if some error occur with hibernate
     * @throws InvalidTokenException if for given user the token is invalid
     * @throws UserDontExistsException if username given dont exists
     * @throws ClientAlreadyHasAnPlanException if Client already has a Plan
     * @throws PlanDontExistException if given Plan dont exist
     * @throws InvalidWeekNumberException if given number of the week is incorrect
     */
    void createWeek(String weekAsJson) throws JsonKeyInFaultException, PersonalTrainerDontExistsException, PersistentException, InvalidTokenException, UserDontExistsException, ClientAlreadyHasAnPlanException, PlanDontExistException, InvalidWeekNumberException;
}
