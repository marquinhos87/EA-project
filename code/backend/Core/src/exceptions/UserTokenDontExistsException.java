package exceptions;

public class UserTokenDontExistsException extends Exception {
    public UserTokenDontExistsException(String message) {
        super(message);
    }

    public UserTokenDontExistsException() {
        super();
    }
}
