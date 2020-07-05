import hrpersonaltrainer.HRPersonalTrainerFacade;
import hrpersonaltrainer.PersonalTrainerAlreadyExistsException;
import org.orm.PersistentException;

public class Main {

    public static void main(String[] args) throws Exception {
        String pt = "{ \"name\": \"ricardo\", " +
                "\"username\": \"ricardo\", " +
                "\"email\": \"rpetronilho98@gmail.com\", " +
                "\"password\": \"password\", " +
                "\"birthday\": \"1998-06-29\", " +
                "\"sex\": \"m\", " +
                "\"skill\": \"cardio\", " +
                "\"price\": 155.99 }";
        String token = HRPersonalTrainerFacade.getInstance().createPersonalTrainer(pt);
        System.out.println(token);
    }
}
