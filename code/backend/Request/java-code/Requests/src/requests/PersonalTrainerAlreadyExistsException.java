package requests;

public class PersonalTrainerAlreadyExistsException extends Exception {
    public PersonalTrainerAlreadyExistsException(){
        super();
    }

    public PersonalTrainerAlreadyExistsException(String message){
        super(message);
    }
}
