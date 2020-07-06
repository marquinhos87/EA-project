import hrpersonaltrainer.HRPersonalTrainerFacade;
import hrpersonaltrainer.Utils;

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

        System.out.println(Utils.makeSuccess200(pt));
        System.out.println(Utils.makeError(404, "token not found"));

        //HRPersonalTrainerFacade.getInstance().createPersonalTrainer(pt);
        //HRPersonalTrainerFacade.getInstance().updateToken("new-token");
    }
}
