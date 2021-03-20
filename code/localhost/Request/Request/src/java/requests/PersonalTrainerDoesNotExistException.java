package requests;

public class PersonalTrainerDoesNotExistException extends Exception{
    public PersonalTrainerDoesNotExistException(){
        super();
    }

    public PersonalTrainerDoesNotExistException(String message){
        super(message);
    }
}
