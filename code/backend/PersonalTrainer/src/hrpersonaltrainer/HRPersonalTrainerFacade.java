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

	public String createPersonalTrainer(String infoPTAsJSON) throws PersistentException, PersonalTrainerAlreadyExistsException, JsonKeyInFaultException {
		return personalTrainerFacadeBean.createPersonalTrainer(infoPTAsJSON);
	}

	public String loginPersonalTrainer(String infoAsJSON) throws PersistentException, PersonalTrainerNotExistsException, JsonKeyInFaultException, InvalidPasswordException {
		return personalTrainerFacadeBean.loginPersonalTrainer(infoAsJSON);
	}

	public String getPersonalTrainerProfileByClient(String usernameAsJSON) throws PersistentException, PersonalTrainerNotExistsException, JsonKeyInFaultException, TokenIsInvalidException, ClientNotExistsException {
		return personalTrainerFacadeBean.getPersonalTrainerProfileByClient(usernameAsJSON);
	}

	public void editPersonalTrainertProfile(String infoAsJson) throws TokenIsInvalidException, PersonalTrainerNotExistsException, PersistentException, JsonKeyInFaultException {
		personalTrainerFacadeBean.editPersonalTrainertProfile(infoAsJson);
	}

	public String getPersonalTrainers(String infoAsJson) throws TokenIsInvalidException, PersistentException, JsonKeyInFaultException, ClientNotExistsException {
		return personalTrainerFacadeBean.getPersonalTrainers(infoAsJson);
	}

	public void submitClassification(String infoAsJSON) throws TokenIsInvalidException, PersonalTrainerNotExistsException, PersistentException, JsonKeyInFaultException, ClientNotExistsException {
		personalTrainerFacadeBean.submitClassification(infoAsJSON);
	}

	public String getPersonalTrainerClients(String infoAsJson) throws TokenIsInvalidException, PersonalTrainerNotExistsException, PersistentException, JsonKeyInFaultException {
		return personalTrainerFacadeBean.getPersonalTrainerClients(infoAsJson);
	}

	public void addClientToPersonalTrainer(String infoAsJson) throws ClientAlreadyExistsException, PersonalTrainerNotExistsException, PersistentException, JsonKeyInFaultException, TokenIsInvalidException {
		personalTrainerFacadeBean.addClientToPersonalTrainer(infoAsJson);
	}

	public void updateClientToken(String usernameAndTokenAsJson) throws JsonKeyInFaultException {
		personalTrainerFacadeBean.updateClientToken(usernameAndTokenAsJson);
	}

    public String getPersonalTrainerProfileByPersonalTrainer(String infoAsJson) throws TokenIsInvalidException, PersonalTrainerNotExistsException, PersistentException, JsonKeyInFaultException {
		return personalTrainerFacadeBean.getPersonalTrainerProfileByPersonalTrainer(infoAsJson);
    }
}