import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hrpersonaltrainer.*;

public class Main {

    public static void main(String[] args) {

        String pt = "{ " +
                "\"username\": \"ricardo\", " +
                "\"name\": \"ricardo\", " +
                "\"email\": \"rpetronilho98@gmail.com\", " +
                "\"password\": \"password\", " +
                "\"birthday\": \"1998-06-29\", " +
                "\"sex\": \"m\", " +
                "\"skill\": \"cardio\", " +
                "\"price\": 155.99" +
                "}";

        System.out.println(pt);
        System.exit(1);
            
        try {
            System.out.println( HRPersonalTrainerFacade.getInstance().createPersonalTrainer(pt) );
        } catch (Exception e) {
            e.printStackTrace();
        }

        String ptToken = null;
        try {
            String oldNewToken = HRPersonalTrainerFacade.getInstance().loginPersonalTrainer("{ \"username\": \"ricardo\", \"password\": \"password\" }");
            System.out.println(oldNewToken);
            JsonObject jsonObject = new Gson().fromJson(oldNewToken, JsonObject.class);
            ptToken = jsonObject.get("newToken").getAsString();
        } catch (InvalidPasswordException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            HRPersonalTrainerFacade.getInstance().createClient("{ \"token\": \"token\", \"username\": \"jose\" }");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            HRPersonalTrainerFacade.getInstance().updateClientToken("{ \"currentToken\": \"token\", \"oldToken\": \"token\", \"username\": \"jose\" }");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        try {
            System.out.println( HRPersonalTrainerFacade.getInstance().getPersonalTrainerProfileByClient("{ \"personalTrainerUsername\": \"ricardo\", \"clientUsername\": \"jose\", \"clientToken\": \"token\" }") );
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            HRPersonalTrainerFacade.getInstance().addClientToPersonalTrainer("{ \"personalTrainerToken\": \"" + ptToken + "\", \"personalTrainerUsername\": \"ricardo\", \"clientUsername\": \"jose\" }");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println( HRPersonalTrainerFacade.getInstance().getPersonalTrainerProfileByPersonalTrainer("{ \"username\": \"ricardo\", \"token\": \"" + ptToken + "\" }") );
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            HRPersonalTrainerFacade.getInstance().submitClassification("{ \"personalTrainerUsername\": \"ricardo\", \"classification\": 5, \"clientToken\": \"token\", \"clientUsername\": \"jose\"  }");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            HRPersonalTrainerFacade.getInstance().submitClassification("{ \"personalTrainerUsername\": \"ricardo\", \"classification\": 3, \"clientToken\": \"token\", \"clientUsername\": \"jose\"  }");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println( HRPersonalTrainerFacade.getInstance().getPersonalTrainerProfileByClient("{ \"personalTrainerUsername\": \"ricardo\", \"clientUsername\": \"jose\", \"clientToken\": \"token\" }") );
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println( HRPersonalTrainerFacade.getInstance().getPersonalTrainerClients("{ \"username\": \"ricardo\", \"token\": \"" + ptToken + "\" }") );
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println( HRPersonalTrainerFacade.getInstance().getPersonalTrainers("{ \"username\": \"jose\", \"token\": \"token\" }") );
        } catch (Exception e) {
            e.printStackTrace();
        }

        String newPtInfo = "{ " +
                "\"username\": \"ricardo\", " +
                "\"token\": \"" + ptToken + "\", " +
                "\"sex\": \"f\", " +
                "\"skill\": \"muscle\", " +
                "\"price\": 200" +
                "}";

        try {
            HRPersonalTrainerFacade.getInstance().editPersonalTrainertProfile(newPtInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println( HRPersonalTrainerFacade.getInstance().getPersonalTrainerProfileByClient("{ \"personalTrainerUsername\": \"ricardo\", \"clientUsername\": \"jose\", \"clientToken\": \"token\" }") );
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.exit(1);
    }
}
