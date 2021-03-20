package hrpersonaltrainer;

public class PersonalTrainerFactory {

	private static PersonalTrainerFactory personalTrainerFactory;

	/**
	 * Private Constructor to make evocation impossible.
	 */
	private PersonalTrainerFactory() {}

	/**
	 * Returns the unique instance of PersonalTrainerFactory.
	 * @return instance of PersonalTrainerFactory.
	 */
	public static PersonalTrainerFactory getInstance() {
		if (personalTrainerFactory == null) {
			personalTrainerFactory = new PersonalTrainerFactory();
		}
		return personalTrainerFactory;
	}

	/**
	 * Creates a PersonalTrainer of specified type.
	 * @param type type of PersonalTrainer.
	 */
	public IPersonalTrainer createIPersonalTrainer(String type) {
		return new PersonalTrainer();
	}

}