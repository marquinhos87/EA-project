package requests;

public class UserDoesNotExistException extends Exception {
    public UserDoesNotExistException(){
        super();
    }

    public UserDoesNotExistException(String message){
        super(message);
    }
}
