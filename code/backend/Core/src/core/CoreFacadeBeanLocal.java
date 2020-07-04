package core;

@javax.ejb.Local
public interface CoreFacadeBeanLocal {

	/**
	 * 
	 * @param usernameAsJson
	 */
	void createClient(String usernameAsJson);

	/**
	 * 
	 * @param usernameAsJson
	 */
	void createPersonalTrainer(String usernameAsJson);

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	void updateToken(String usernameAndTokenAsJson);

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