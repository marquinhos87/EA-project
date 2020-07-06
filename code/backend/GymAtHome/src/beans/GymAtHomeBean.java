package beans;

import utils.Http;
import okhttp3.*;

import javax.ejb.Stateless;
import javax.ejb.Local;
import java.io.IOException;

import static utils.Utils.JsonToURLParameters;

@Local(GymAtHomeBeanLocal.class)
@Stateless(name = "GymAtHomeEJB")
public class GymAtHomeBean implements GymAtHomeBeanLocal{
    public GymAtHomeBean() {
    }

    @Override
    public String createClient(String infoClientAsJSON) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRClient/createClient";
        Response response = Http.post(url,infoClientAsJSON);
        return null;
    }

    @Override
    public String createPersonalTrainer(String infoPTAsJSON) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/createPersonalTrainer";
        Response response = Http.post(url,infoPTAsJSON);

        return null;
    }

    @Override
    public String loginClient(String infoAsJSON) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRClient/loginClient";
        Response response = Http.post(url,infoAsJSON);

        return null;
    }

    @Override
    public String loginPersonalTrainer(String infoAsJSON) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/loginPersonalTrainer";
        Response response = Http.post(url,infoAsJSON);

        return null;
    }

    @Override
    public String getClientProfile(String usernameAsJSON) throws IOException {
        String parameters = JsonToURLParameters(usernameAsJSON);
        String url = "http://localhost:8080/api/v1/GymAtHome/HRClient/getClient?" + parameters;
        Response response = Http.get(url);
        return response.body().string();
    }

    @Override
    public String getPersonalTrainerProfile(String usernameAsJSON) throws IOException {
        String parameters = JsonToURLParameters(usernameAsJSON);
        String url = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/getPersonalTrainer?" + parameters;
        Response response = Http.get(url);
        return response.body().string();
    }

    @Override
    public void editClientProfile(String infoAsJSON) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRClient/editClientProfile";
        Response response = Http.post(url,infoAsJSON);
    }

    @Override
    public void editPersonalTrainerProfile(String usernameAsJSON) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/editPersonalTrainerProfile";
        Response response = Http.post(url,usernameAsJSON);
    }

    @Override
    public String getPlan(String usernameAndWeekAsJSON) throws IOException {
        String parameters = JsonToURLParameters(usernameAndWeekAsJSON);
        String url = "http://localhost:8080/api/v1/GymAtHome/Core/getPlanByClient?" + parameters;
        Response response = Http.get(url);
        return response.body().string();
    }

    @Override
    public String getPersonalTrainers(String filtersAsJSON) throws IOException {
        String parameters = JsonToURLParameters(filtersAsJSON);
        String url = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/getPersonalTrainers?" + parameters;
        Response response = Http.get(url);

        return null;
    }

    @Override
    public String getBiometricData(String usernameAsJSON) throws IOException {
        String parameters = JsonToURLParameters(usernameAsJSON);
        String url = "http://localhost:8080/api/v1/GymAtHome/HRClient/getBiometricData?" + parameters;
        Response response = Http.get(url);

        return null;
    }

    @Override
    public String submitClassification(String usernameAndClassificationAsJSON) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/submitClassification";
        Response response = Http.post(url,usernameAndClassificationAsJSON);

        return null;
    }

    @Override
    public void finishWorkout(String usernameAndWorkoutIdAsJSON) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/Core/finishWorkout";
        Response response = Http.post(url,usernameAndWorkoutIdAsJSON);
    }

    @Override
    public String getPersonalTrainerClients(String usernameAsJSON) throws IOException {
        String parameters = JsonToURLParameters(usernameAsJSON);
        String url = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/getPersonalTrainerClients?" + parameters;
        Response response = Http.get(url);

        return null;
    }

    @Override
    public void submitRequest(String requestInfoAsJSON) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/Requests/submitRequest";
        Response response = Http.post(url,requestInfoAsJSON);
    }

    @Override
    public void createWeek(String weekAsJson) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/Core/createWeek";
        Response response = Http.post(url,weekAsJson);
    }

    @Override
    public void replyToRequest(String requestIdAndResponseAsJSON) throws IOException {

    }
}
