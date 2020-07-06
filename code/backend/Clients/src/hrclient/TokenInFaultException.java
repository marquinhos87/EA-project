package hrclient;

public class TokenInFaultException extends Exception{
    public TokenInFaultException(){
        super();
    }

    public TokenInFaultException(String message){
        super(message);
    }
}
