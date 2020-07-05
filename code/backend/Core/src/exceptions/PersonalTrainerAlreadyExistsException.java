package exceptions;

public class PersonalTrainerAlreadyExistsException extends Exception {
    public PersonalTrainerAlreadyExistsException(String message) {
        super(message);
    }

    public PersonalTrainerAlreadyExistsException() {
        super();
    }
}
