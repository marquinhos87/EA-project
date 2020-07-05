package hrpersonaltrainer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.orm.PersistentException;

import java.util.Date;

@javax.ejb.Stateless(name="HRPersonalTrainerFacadeBean")
@javax.ejb.Remote(HRPersonalTrainerFacadeBean.class)
@javax.ejb.Local(HRPersonalTrainerFacadeBeanLocal.class)
public class HRPersonalTrainerFacadeBeanBean implements HRPersonalTrainerFacadeBean, HRPersonalTrainerFacadeBeanLocal {

	private final Gson gson;

	public HRPersonalTrainerFacadeBeanBean() {
		gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd")
				.create();
	}

	private PersonalTrainerFactory personalTrainerFactory;

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	public void updateToken(String usernameAndTokenAsJson) {
		// TODO - implement HRPersonalTrainerFacadeBean.updateToken
		throw new UnsupportedOperationException();
	}

	/**
	 * Creates a PersonalTrainer and saves it to database based on the given information.
	 * @param infoPTAsJSON information of PersonalTrainer.
	 */
	public String createPersonalTrainer(String infoPTAsJSON) throws PersistentException, PersonalTrainerAlreadyExistsException {
		PersonalTrainer pt = gson.fromJson(infoPTAsJSON, PersonalTrainer.class);
		pt.setToken(pt.getUsername() + pt.getPassword());
		System.err.println(pt);
		if (PersonalTrainerDAO.getPersonalTrainerByORMID(pt.getUsername()) != null) throw new PersonalTrainerAlreadyExistsException(pt.getUsername());
		PersonalTrainerDAO.save(pt);
		System.err.println("PersonalTrainer saved to database...");
		return pt.getToken();
	}

	/**
	 * 
	 * @param infoAsJSON
	 */
	public String loginPersonalTrainer(String infoAsJSON) {
		// TODO - implement HRPersonalTrainerFacadeBean.loginPersonalTrainer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getPersonalTrainerProfile(String usernameAsJSON) {
		// TODO - implement HRPersonalTrainerFacadeBean.getPersonalTrainerProfile
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public void editPersonalTrainertProfile(int usernameAsJSON) {
		// TODO - implement HRPersonalTrainerFacadeBean.editPersonalTrainertProfile
		throw new UnsupportedOperationException();
	}

	/**
	 *
	 * @param filtersAsJSON
	 */
	public String getPersonalTrainers(String filtersAsJSON) {
		// TODO - implement HRPersonalTrainerFacadeBean.getPersonalTrainers
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndClassificationAsJSON
	 */
	public String submitClassification(int usernameAndClassificationAsJSON) {
		// TODO - implement HRPersonalTrainerFacadeBean.submitClassification
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAsJSON
	 */
	public String getPersonalTrainerClients(int usernameAsJSON) {
		// TODO - implement HRPersonalTrainerFacadeBean.getPersonalTrainerClients
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param usernameAndTokenAsJson
	 */
	public void createClient(String usernameAndTokenAsJson) {
		// TODO - implement HRPersonalTrainerFacadeBean.createClient
		throw new UnsupportedOperationException();
	}

}