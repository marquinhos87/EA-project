package servlets;

import backend.GymAtHome;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import static utils.Utils.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@WebServlet(name = "APIController", urlPatterns = "/api/v1/GymAtHome/GymAtHome")
public class APIController extends HttpServlet {

    private GymAtHome gymAtHome = GymAtHome.getInstance();

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GymAtHome facade = GymAtHome.getInstance();

        // Obtain target
        String[] url = request.getRequestURI().split("/");
        String target = url[url.length-1];

        // Obtain json data from http post as a string
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = request.getReader().readLine())!=null) {
            sb.append(line);
        }
        String data = sb.toString();

        String res = null;

        try {
            switch (target) {
                case "createClient":
                    res = makeSuccess(200, facade.createClient(data));
                    break;
                case "createPersonalTrainer":
                    res = makeSuccess(200, facade.createPersonalTrainer(data));
                    break;
                case "editClientProfile":
                    facade.editClientProfile(data);
                    res = makeSuccess(200, "");
                    break;
                case "editPersonalTrainerProfile":
                    facade.editPersonalTrainertProfile(data);
                    res = makeSuccess(200, "");
                    break;
                case "submitClassification":
                    facade.submitClassification(data);
                    res = makeSuccess(200, "");
                    break;
                case "createWeek":
                    facade.createWeek(data);
                    res = makeSuccess(200, "");
                    break;
                case "loginClient":
                    res = makeSuccess(200, facade.loginClient(data));
                    break;
                case "loginPersonalTrainer":
                    res = makeSuccess(200, facade.loginPersonalTrainer(data));
                    break;
                case "getBiometricData":
                    res = makeSuccess(200, facade.getBiometricData(data));
                    break;
                case "getClientProfile":
                    res = makeSuccess(200, facade.getClientProfile(""));
                    break;
                case "getPersonalTrainerProfile":
                    res = makeSuccess(200, facade.getPersonalTrainerProfile(data));
                    break;
                case "getPersonalTrainers":
                    res = makeSuccess(200, facade.getPersonalTrainers(data));
                    break;
                case "getPersonalTrainerClients":
                    res = makeSuccess(200, facade.getPersonalTrainerClients(data));
                    break;
                case "getPlan":
                    res = makeSuccess(200, facade.getPlan(data));
                    break;
                default:
                    res = makeError(405, "Method not allowed.");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            res = makeError(500, "Internal error.");
        }
        finally {
            response.setContentType("application/json");
            response.getWriter().print(res);
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.getWriter().print(makeError(405,"Method not allowed."));
        /*GymAtHome facade = GymAtHome.getInstance();

        // Obtain target
        String[] url = request.getRequestURI().split("/");
        String target = url[url.length-1];

        // Obtain url parameters
        Map<String,String[]> parameters = request.getParameterMap();

        // Create a json string with the parameters
        String data = parametersToJSON(parameters);

        String res = null;

        try {
            if (target.equals("getBiometricData"))
                res = makeSuccess(200, facade.getBiometricData(data));
            else if (target.equals("getClientProfile"))
                res = makeSuccess(200, facade.getClientProfile(""));
            else if (target.equals("getPersonalTrainerProfile"))
                res = makeSuccess(200, facade.getPersonalTrainerProfile(data));
            else if (target.equals("getPersonalTrainers"))
                res = makeSuccess(200, facade.getPersonalTrainers(data));
            else if (target.equals("getPersonalTrainerClients"))
                res = makeSuccess(200, facade.getPersonalTrainerClients(data));
            else if (target.equals("getPlan"))
                res = makeSuccess(200, facade.getPlan(data));
            else
                res = makeError(405, "Method not allowed.");
        }
        catch (IOException e) {
            e.printStackTrace();
            res = makeError(500, "Internal error.");
        }
        finally {
            response.setContentType("application/json");
            response.getWriter().print(res);
        }*/
    }
}
