package requests;

public class RequestDoesNotExistException extends Exception{
    public RequestDoesNotExistException(){
        super();
    }

    public RequestDoesNotExistException(String message){
        super(message);
    }
}
