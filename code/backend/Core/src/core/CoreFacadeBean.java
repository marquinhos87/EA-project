package core;

@javax.ejb.Stateless(name="CoreFacadeBean")
@javax.ejb.Local(CoreFacadeBeanLocal.class)
public class CoreFacadeBeanBean implements CoreFacadeBeanLocal {

	private PlanDirector planDirector;

	/**
	 * 
	 * @param usernameAsJson
	 */
	public void createClient(String usernameAsJson) {
		// TODO - implement CoreFacadeBean.createClient
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJson
	 */
	public void createPersonalTrainer(String usernameAsJson) {
		// TODO - implement CoreFacadeBean.createPersonalTrainer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	public void updateToken(String usernameAndTokenAsJson) {
		// TODO - implement CoreFacadeBean.updateToken
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndWeekAsJSON
	 */
	public String getPlan(String usernameAndWeekAsJSON) {
		// TODO - implement CoreFacadeBean.getPlan
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndWorkoutIdAsJSON
	 */
	public void finishWorkout(String usernameAndWorkoutIdAsJSON) {
		// TODO - implement CoreFacadeBean.finishWorkout
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param weekAsJson
	 */
	public void createWeek(String weekAsJson) {
		// TODO - implement CoreFacadeBean.createWeek
		throw new UnsupportedOperationException();
	}

}