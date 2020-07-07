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
        String urlHRClient = "http://localhost:8080/api/v1/GymAtHome/HRClient/createClient";
        Response responseHRClient = Http.post(urlHRClient,infoClientAsJSON);

        // TODO concatenate
        String coreJSON = infoClientAsJSON + responseHRClient.body().string();
        String urlCore = "http://localhost:8080/api/v1/GymAtHome/Core/createUserToken";
        Response responseCore = Http.post(urlCore,coreJSON);

        return responseHRClient.body().string();
    }

    @Override
    public String createPersonalTrainer(String infoPTAsJSON) throws IOException {
        String urlHRPT = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/createPersonalTrainer";
        Response responseHRPT = Http.post(urlHRPT,infoPTAsJSON);

        // TODO concatenate
        String coreJSON = infoPTAsJSON + responseHRPT.body().string();
        String urlCore = "http://localhost:8080/api/v1/GymAtHome/Core/createUserToken";
        Response responseCore = Http.post(urlCore,coreJSON);

        return responseHRPT.body().string();
    }

    @Override
    public String loginClient(String infoAsJSON) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRClient/loginClient";
        Response responseHRClient = Http.post(url,infoAsJSON);

        // TODO concatenate
        String coreJSON = infoAsJSON + responseHRClient.body().string();
        String urlCore = "http://localhost:8080/api/v1/GymAtHome/Core/updateToken";
        Response responseCore = Http.post(urlCore,coreJSON);

        return responseHRClient.body().string();
    }

    @Override
    public String loginPersonalTrainer(String infoAsJSON) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/loginPersonalTrainer";
        Response responseHRPT = Http.post(url,infoAsJSON);

        // TODO concatenate
        String coreJSON = infoAsJSON + responseHRPT.body().string();
        String urlCore = "http://localhost:8080/api/v1/GymAtHome/Core/createUserToken";
        Response responseCore = Http.post(urlCore,coreJSON);

        return responseHRPT.body().string();
    }

    @Override
    public String getClientProfile(String usernameAsJSON) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRClient/getClient";
        Response response = Http.post(url,usernameAsJSON);

        return null;
    }

    @Override
    public String getPersonalTrainerProfile(String usernameAsJSON) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/getPersonalTrainer";
        Response response = Http.post(url,usernameAsJSON);
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
    public String getPlanByClient(String usernameAndWeekAsJSON) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/Core/getPlanByClient";
        Response response = Http.post(url,usernameAndWeekAsJSON);
        return response.body().string();
    }

    @Override
    public String getPlanByPersonalTrainer(String usernameAndWeekAsJSON) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/Core/getPlanByPersonalTrainer";
        Response response = Http.post(url,usernameAndWeekAsJSON);
        return response.body().string();
    }

    @Override
    public String getPersonalTrainers(String filtersAsJSON) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/getPersonalTrainers";
        Response response = Http.post(url,filtersAsJSON);
        return response.body().string();
    }

    @Override
    public String getBiometricData(String usernameAsJSON) throws IOException {
        String url = "http://localhost:8080/api/v1/GymAtHome/HRClient/getBiometricData";
        Response response = Http.post(url,usernameAsJSON);
        return response.body().string();
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
        String url = "http://localhost:8080/api/v1/GymAtHome/HRPersonalTrainer/getPersonalTrainerClients";
        Response response = Http.post(url,usernameAsJSON);
        return response.body().string();
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
        String url = "http://localhost:8080/api/v1/GymAtHome/";
        Response response = Http.post(url,requestIdAndResponseAsJSON);

    }
}
