package backend;

import beans.GymAtHomeBeanLocal;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GymAtHome {

    GymAtHomeBeanLocal gymAtHomeBean = lookupGymAtHomeBeanLocal();

    private static GymAtHome gymAtHome;

    private GymAtHome() {

    }

    public static GymAtHome getInstance() {
            if (gymAtHome == null) {
                    gymAtHome = new GymAtHome();
            }
            return gymAtHome;
    }
        
    private GymAtHomeBeanLocal lookupGymAtHomeBeanLocal() {
        try {
            Context c = new InitialContext();
            return (GymAtHomeBeanLocal) c.lookup("java:global/GymAtHome/GymAtHomeBean!beans.GymAtHomeBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    /**
     * 
     * @param infoClientAsJSON
     */
    public String createClient(String infoClientAsJSON) throws IOException {
            return gymAtHomeBean.createClient(infoClientAsJSON);
    }

    /**
     * 
     * @param infoAsJSON
     */
    public String loginClient(String infoAsJSON) throws IOException {
            return gymAtHomeBean.loginClient(infoAsJSON);
    }

    /**
     * 
     * @param usernameAsJSON
     */
    public String getClientProfile(String usernameAsJSON) throws IOException {
            return gymAtHomeBean.getClientProfile(usernameAsJSON);
    }

    /**
     * 
     * @param filtersAsJSON
     */
    public String getPersonalTrainers(String filtersAsJSON) throws IOException {
            return gymAtHomeBean.getPersonalTrainers(filtersAsJSON);
    }

    /**
     * 
     * @param infoPTAsJSON
     */
    public String createPersonalTrainer(String infoPTAsJSON) throws IOException {
            return gymAtHomeBean.createPersonalTrainer(infoPTAsJSON);
    }

    /**
     * 
     * @param infoAsJSON
     */
    public String loginPersonalTrainer(String infoAsJSON) throws IOException {
            return gymAtHomeBean.loginPersonalTrainer(infoAsJSON);
    }

    /**
     * 
     * @param usernameAsJSON
     */
    public String getPersonalTrainerProfile(String usernameAsJSON) throws IOException {
            return gymAtHomeBean.getPersonalTrainerProfile(usernameAsJSON);
    }

    /**
     * 
     * @param infoAsJSON
     */
    public void editClientProfile(String infoAsJSON) throws IOException {
            gymAtHomeBean.editClientProfile(infoAsJSON);
    }

    /**
     * 
     * @param usernameAsJSON
     */
    public void editPersonalTrainertProfile(String usernameAsJSON) throws IOException {
            gymAtHomeBean.editPersonalTrainerProfile(usernameAsJSON);
    }

    /**
     * 
     * @param usernameAndWeekAsJSON
     */
    public String getPlanByClient(String usernameAndWeekAsJSON) throws IOException {
            return gymAtHomeBean.getPlanByClient(usernameAndWeekAsJSON);
    }

    /**
     *
     * @param usernameAndWeekAsJSON
     */
    public String getPlanByPersonalTrainer(String usernameAndWeekAsJSON) throws IOException {
            return gymAtHomeBean.getPlanByPersonalTrainer(usernameAndWeekAsJSON);
    }

    /**
     * 
     * @param usernameAsJSON
     */
    public String getBiometricData(String usernameAsJSON) throws IOException {
            return gymAtHomeBean.getBiometricData(usernameAsJSON);
    }

    /**
     * 
     * @param usernameAndClassificationAsJSON
     */
    public String submitClassification(String usernameAndClassificationAsJSON) throws IOException {
            return gymAtHomeBean.submitClassification(usernameAndClassificationAsJSON);
    }

    /**
     * 
     * @param usernameAndWorkoutIdAsJSON
     */
    public void finishWorkout(String usernameAndWorkoutIdAsJSON) throws IOException {
            gymAtHomeBean.finishWorkout(usernameAndWorkoutIdAsJSON);
    }

    /**
     * 
     * @param usernameAsJSON
     */
    public String getPersonalTrainerClients(String usernameAsJSON) throws IOException {
            return gymAtHomeBean.getPersonalTrainerClients(usernameAsJSON);
    }

    /**
     * 
     * @param requestInfoAsJSON
     */
    public void submitRequest(String requestInfoAsJSON) throws IOException {
            gymAtHomeBean.submitRequest(requestInfoAsJSON);
    }

    /**
     * 
     * @param weekAsJson
     */
    public void createWeek(String weekAsJson) throws IOException {
            gymAtHomeBean.createWeek(weekAsJson);
    }

    /**
     * 
     * @param requestIdAndResponseAsJSON
     */
    public void replyToRequest(String requestIdAndResponseAsJSON) throws IOException {
            gymAtHomeBean.replyToRequest(requestIdAndResponseAsJSON);
    }
}