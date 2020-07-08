package exceptions;

public class PersonalTrainerDontExistsException extends Exception {
    public PersonalTrainerDontExistsException(String message) {
        super(message);
    }

    public PersonalTrainerDontExistsException() {
        super();
    }
}
