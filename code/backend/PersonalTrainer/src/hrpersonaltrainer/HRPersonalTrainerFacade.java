package hrpersonaltrainer;

import org.orm.PersistentException;
import org.orm.PersistentSession;

public class HRPersonalTrainerFacade {

	private static HRPersonalTrainerFacade rhPersonalTrainerFacade;
	private static PersistentSession session = null;

	// TODO trocar isto para o código de obtenção do bean
	private final HRPersonalTrainerFacadeBean personalTrainerFacadeBean = new HRPersonalTrainerFacadeBeanBean();

	private HRPersonalTrainerFacade() {}

	public static HRPersonalTrainerFacade getInstance() {
		if (rhPersonalTrainerFacade == null) {
			rhPersonalTrainerFacade = new HRPersonalTrainerFacade();
		}
		return rhPersonalTrainerFacade;
	}

	public static PersistentSession getSession() throws PersistentException {
		if (session == null) session = DiagramasPersistentManager.instance().getSession();
		return session;
	}

	/**
	 * 
	 * @param infoPTAsJSON
	 */
	public String createPersonalTrainer(String infoPTAsJSON) throws PersistentException, PersonalTrainerAlreadyExistsException, JsonKeyInFaultException {
		return personalTrainerFacadeBean.createPersonalTrainer(infoPTAsJSON);
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public String loginPersonalTrainer(String infoAsJSON) throws PersistentException, PersonalTrainerNotExistsException, JsonKeyInFaultException {
		return personalTrainerFacadeBean.loginPersonalTrainer(infoAsJSON);
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getPersonalTrainerProfileByClient(String usernameAsJSON) throws PersistentException, PersonalTrainerNotExistsException, JsonKeyInFaultException, TokenIsInvalidException {
		return personalTrainerFacadeBean.getPersonalTrainerProfileByClient(usernameAsJSON);
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
	public void createClient(String usernameAndTokenAsJson) throws ClientAlreadyExistsException, PersistentException, JsonKeyInFaultException {
		personalTrainerFacadeBean.createClient(usernameAndTokenAsJson);
	}

}