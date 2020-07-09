package requests;

public class TokenIsInvalidException extends Exception {
    public TokenIsInvalidException(){
        super();
    }

    public TokenIsInvalidException(String message){
        super(message);
    }
}
