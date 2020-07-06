import hrpersonaltrainer.HRPersonalTrainerFacade;

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

        //HRPersonalTrainerFacade.getInstance().createPersonalTrainer(pt);
        String json = HRPersonalTrainerFacade.getInstance().loginPersonalTrainer("{ \"username\": \"ricardo\", \"password\": \"password\" }");
        System.out.println(json);
    }
}
