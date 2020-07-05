package exceptions;

public class ClientAlreadyExistsException extends Exception {
    public ClientAlreadyExistsException(String message) {
        super(message);
    }

    public ClientAlreadyExistsException() {
        super();
    }
}
