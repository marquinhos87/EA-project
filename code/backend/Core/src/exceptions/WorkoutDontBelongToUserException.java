package exceptions;

public class WorkoutDontBelongToUserException extends Exception {
    public WorkoutDontBelongToUserException(String message) {
        super(message);
    }

    public WorkoutDontBelongToUserException() {
        super();
    }
}
