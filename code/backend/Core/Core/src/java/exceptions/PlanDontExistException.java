package exceptions;

public class PlanDontExistException extends Exception {
    public PlanDontExistException(String message) {
        super(message);
    }

    public PlanDontExistException() {
        super();
    }
}
