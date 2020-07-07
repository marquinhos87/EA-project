import hrpersonaltrainer.HRPersonalTrainerFacade;

public class Main {

    public static void main(String[] args) {

        String pt = "{ \"name\": \"ricardo\", " +
                "\"username\": \"ricardo\", " +
                "\"email\": \"rpetronilho98@gmail.com\", " +
                "\"password\": \"password\", " +
                "\"birthday\": \"1998-06-29\", " +
                "\"sex\": \"m\", " +
                "\"skill\": \"cardio\", " +
                "\"price\": 155.99 }";

        String client = "{ \"username\": \"jose\", " +
                "\"token\": \"token\" }";

        try {
            System.out.println( HRPersonalTrainerFacade.getInstance().createPersonalTrainer(pt) );
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println( HRPersonalTrainerFacade.getInstance().loginPersonalTrainer("{ \"username\": \"ricardo\", \"password\": \"password\" }") );
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        //TODO mudar a coluna PersonalTrainerUsername (FK) na tabela Client para possibilitar valores NULL
        try {
            HRPersonalTrainerFacade.getInstance().createClient(client);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println( HRPersonalTrainerFacade.getInstance().getPersonalTrainerProfileByClient("{ \"personalTrainerUsername\": \"ricardo\", \"clientUsername\": \"jose\", \"clientToken\": \"token\" }") );
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }
}
