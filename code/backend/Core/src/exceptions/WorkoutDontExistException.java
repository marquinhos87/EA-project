package exceptions;

public class WorkoutDontExistException extends Exception {
    public WorkoutDontExistException(String message) {
        super(message);
    }

    public WorkoutDontExistException() {
        super();
    }
}
