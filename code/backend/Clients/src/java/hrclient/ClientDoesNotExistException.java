package hrclient;

public class ClientDoesNotExistException extends Exception{
    public ClientDoesNotExistException(){
        super();
    }

    public ClientDoesNotExistException(String messsage){
        super(messsage);
    }
}
