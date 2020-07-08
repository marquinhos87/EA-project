package exceptions;

public class WorkoutAlreadyDoneException extends Exception {
    public WorkoutAlreadyDoneException(String message) {
        super(message);
    }

    public WorkoutAlreadyDoneException() {
        super();
    }
}
