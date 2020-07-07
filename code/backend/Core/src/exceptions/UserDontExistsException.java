package exceptions;

public class UserDontExistsException extends Exception {
    public UserDontExistsException(String message) {
        super(message);
    }

    public UserDontExistsException() {
        super();
    }
}
