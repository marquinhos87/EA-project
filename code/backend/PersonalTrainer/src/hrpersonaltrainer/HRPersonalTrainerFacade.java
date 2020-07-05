package hrpersonaltrainer;

import org.orm.PersistentException;

public class HRPersonalTrainerFacade {

	private static HRPersonalTrainerFacade rhPersonalTrainerFacade;
	private final HRPersonalTrainerFacadeBean personalTrainerFacadeBean = new HRPersonalTrainerFacadeBeanBean();

	private HRPersonalTrainerFacade() {}

	public static HRPersonalTrainerFacade getInstance() {
		if (rhPersonalTrainerFacade == null) {
			rhPersonalTrainerFacade = new HRPersonalTrainerFacade();
		}
		return rhPersonalTrainerFacade;
	}

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	public void updateToken(String usernameAndTokenAsJson) {
		// TODO - implement HRPersonalTrainerFacade.updateToken
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param infoPTAsJSON
	 */
	public String createPersonalTrainer(String infoPTAsJSON) throws PersistentException, PersonalTrainerAlreadyExistsException {
		return personalTrainerFacadeBean.createPersonalTrainer(infoPTAsJSON);
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public String loginPersonalTrainer(String infoAsJSON) {
		// TODO - implement HRPersonalTrainerFacade.loginPersonalTrainer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getPersonalTrainerProfile(String usernameAsJSON) {
		// TODO - implement HRPersonalTrainerFacade.getPersonalTrainerProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public void editPersonalTrainertProfile(int usernameAsJSON) {
		// TODO - implement HRPersonalTrainerFacade.editPersonalTrainertProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param filtersAsJSON
	 */
	public String getPersonalTrainers(String filtersAsJSON) {
		// TODO - implement HRPersonalTrainerFacade.getPersonalTrainers
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndClassificationAsJSON
	 */
	public String submitClassification(int usernameAndClassificationAsJSON) {
		// TODO - implement HRPersonalTrainerFacade.submitClassification
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public void getPersonalTrainerClients(int usernameAsJSON) {
		// TODO - implement HRPersonalTrainerFacade.getPersonalTrainerClients
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	public void createClient(String usernameAndTokenAsJson) {
		// TODO - implement HRPersonalTrainerFacade.createClient
		throw new UnsupportedOperationException();
	}

}