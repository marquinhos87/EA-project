package beans;

import utils.Http;
import okhttp3.*;

import javax.ejb.Stateless;
import javax.ejb.Local;
import java.io.IOException;

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
    public String loginClient(String infoAsJSON) {
        return null;
    }

    @Override
    public String loginPersonalTrainer(String infoAsJSON) {
        return null;
    }

    @Override
    public String getClientProfile(String usernameAsJSON) {
        return null;
    }

    @Override
    public String getPersonalTrainerProfile(String usernameAsJSON) {
        return null;
    }

    @Override
    public void editClientProfile(String infoAsJSON) {

    }

    @Override
    public void editPersonalTrainerProfile(String usernameAsJSON) {

    }

    @Override
    public String getPlan(String usernameAndWeekAsJSON) {
        return null;
    }

    @Override
    public String getPersonalTrainers(String filtersAsJSON) {
        return null;
    }

    @Override
    public String getBiometricData(String usernameAsJSON) {
        return null;
    }

    @Override
    public String submitClassification(String usernameAndClassificationAsJSON) {

        return usernameAndClassificationAsJSON;
    }

    @Override
    public void finishWorkout(String usernameAndWorkoutIdAsJSON) {

    }

    @Override
    public String getPersonalTrainerClients(String usernameAsJSON) {
        return null;
    }

    @Override
    public void submitRequest(String requestInfoAsJSON) {

    }

    @Override
    public void createWeek(String weekAsJson) {

    }

    @Override
    public void replyToRequest(String requestIdAndResponseAsJSON) {

    }
}
