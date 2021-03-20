package exceptions;

public class JsonKeyInFaultException extends Exception {
    public JsonKeyInFaultException(){
        super();
    }

    public JsonKeyInFaultException(String message){
        super(message);
    }
}
