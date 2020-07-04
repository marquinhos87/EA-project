package backend;

@javax.ejb.Stateless(name="GymAtHomeBean")
@javax.ejb.Local(GymAtHomeBeanLocal.class)
public class GymAtHomeBean implements GymAtHomeBeanLocal {

	/**
	 * 
	 * @param infoClientAsJSON
	 */
	public String createClient(String infoClientAsJSON) {
		// TODO - implement GymAtHomeBean.createClient
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoPTAsJSON
	 */
	public String createPersonalTrainer(String infoPTAsJSON) {
		// TODO - implement GymAtHomeBean.createPersonalTrainer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public String loginClient(String infoAsJSON) {
		// TODO - implement GymAtHomeBean.loginClient
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public String loginPersonalTrainer(String infoAsJSON) {
		// TODO - implement GymAtHomeBean.loginPersonalTrainer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getClientProfile(String usernameAsJSON) {
		// TODO - implement GymAtHomeBean.getClientProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getPersonalTrainerProfile(String usernameAsJSON) {
		// TODO - implement GymAtHomeBean.getPersonalTrainerProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public void editClientProfile(String infoAsJSON) {
		// TODO - implement GymAtHomeBean.editClientProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public void editPersonalTrainertProfile(String usernameAsJSON) {
		// TODO - implement GymAtHomeBean.editPersonalTrainertProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndWeekAsJSON
	 */
	public String getPlan(String usernameAndWeekAsJSON) {
		// TODO - implement GymAtHomeBean.getPlan
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param filtersAsJSON
	 */
	public String getPersonalTrainers(String filtersAsJSON) {
		// TODO - implement GymAtHomeBean.getPersonalTrainers
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getBiometricData(String usernameAsJSON) {
		// TODO - implement GymAtHomeBean.getBiometricData
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndClassificationAsJSON
	 */
	public String submitClassification(String usernameAndClassificationAsJSON) {
		// TODO - implement GymAtHomeBean.submitClassification
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndWorkoutIdAsJSON
	 */
	public void finishWorkout(String usernameAndWorkoutIdAsJSON) {
		// TODO - implement GymAtHomeBean.finishWorkout
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public void getPersonalTrainerClients(String usernameAsJSON) {
		// TODO - implement GymAtHomeBean.getPersonalTrainerClients
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param requestInfoAsJSON
	 */
	public void submitRequest(String requestInfoAsJSON) {
		// TODO - implement GymAtHomeBean.submitRequest
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param weekAsJson
	 */
	public void createWeek(String weekAsJson) {
		// TODO - implement GymAtHomeBean.createWeek
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param requestIdAndResponseAsJSON
	 */
	public void replyToRequest(String requestIdAndResponseAsJSON) {
		// TODO - implement GymAtHomeBean.replyToRequest
		throw new UnsupportedOperationException();
	}

}