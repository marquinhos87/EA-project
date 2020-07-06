package hrclient;

public class TokenAndUsernameInFaultException extends Exception{
    public TokenAndUsernameInFaultException(){
        super();
    }

    public TokenAndUsernameInFaultException(String message){
        super(message);
    }
}
