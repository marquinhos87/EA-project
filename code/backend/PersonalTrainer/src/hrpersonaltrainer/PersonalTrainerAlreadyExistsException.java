package hrpersonaltrainer;

public class PersonalTrainerAlreadyExistsException extends Exception {
    public PersonalTrainerAlreadyExistsException(String username) {
        super(username);
    }
}
