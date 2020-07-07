package exceptions;

public class ClientAlreadyHasAnPlanException extends Exception{
    public ClientAlreadyHasAnPlanException(String message) {
        super(message);
    }

    public ClientAlreadyHasAnPlanException() {
        super();
    }
}
