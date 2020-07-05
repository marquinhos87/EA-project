package exceptions;

public class ClientDontExistsException extends Exception {
    public ClientDontExistsException(String message) {
        super(message);
    }

    public ClientDontExistsException() {
        super();
    }
}
