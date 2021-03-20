/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import backend.GymAtHome;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import parseJSON.ResponseJSON;
import static utils.Utils.makeError;
import static utils.Utils.makeSuccess;

/**
 *
 * @author joaomarques
 */
@WebServlet(name="api", urlPatterns="api/*")
public class APIController extends HttpServlet {
    
    private final GymAtHome gymAtHome = GymAtHome.getInstance();

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            switch(target) {
                    //TESTED
                case "createClient":
                    res = gymAtHome.createClient(data);
                    break;
                    //TESTED
                case "createPersonalTrainer":
                    res = gymAtHome.createPersonalTrainer(data);
                    break;
                    //TESTED
                case "loginClient":
                    res = gymAtHome.loginClient(data);
                    break;
                    //TESTED
                case "loginPersonalTrainer":
                    res = gymAtHome.loginPersonalTrainer(data);
                    break;
                    //TESTED
                case "getClientProfileByClient":
                    res = gymAtHome.getClientProfileByClient(data);
                    break;
                    //TESTED
                case "getClientProfileByPersonalTrainer":
                    res = gymAtHome.getClientProfileByPersonalTrainer(data);
                    break;
                    //TESTED
                case "getPersonalTrainerProfileByClient":
                    res = gymAtHome.getPersonalTrainerProfileByClient(data);
                    break;
                    //TESTED
                case "getPersonalTrainerProfileByPersonalTrainer":
                    res = gymAtHome.getPersonalTrainerProfileByPersonalTrainer(data);
                    break;
                    //TESTED
                case "getPersonalTrainers":
                    res = gymAtHome.getPersonalTrainers(data);
                    break;
                    //TESTED
                case "editClientProfile":
                    res = gymAtHome.editClientProfile(data);
                    break;
                    //TESTED
                case "editPersonalTrainerProfile":
                    res = gymAtHome.editPersonalTrainertProfile(data);
                    break;
                    //TESTED
                case "getWeekByClient":
                    res = gymAtHome.getWeekByClient(data);
                    break;
                    //TESTED
                case "getWeekByPersonalTrainer":
                    res = gymAtHome.getWeekByPersonalTrainer(data);
                    break;
                    //TESTED
                case "getBiometricData":
                    res = gymAtHome.getBiometricData(data);
                    break;
                    //TESTED
                case "submitClassification":
                    res = gymAtHome.submitClassification(data);
                    break;
                    //TESTED (no entanto falta mandar a notificação ao PT a informar que o cliente acabou o workout)
                case "finishWorkout":
                    res = gymAtHome.finishWorkout(data);
                    break;
                    //TESTED
                case "getPersonalTrainerClients":
                    res = gymAtHome.getPersonalTrainerClients(data);
                    break;
                    //TESTED
                case "submitRequest":
                    res = gymAtHome.submitRequest(data);
                    break;
                    //TESTED
                case "createWeek":
                    res = gymAtHome.createWeek(data);
                    break;
                    //TESTED
                case "replyToRequest":
                    res = gymAtHome.replyToRequest(data);
                    break;
                    //TESTED
                case "getNotificationsByClient":
                    res = gymAtHome.getNotificationsByClient(data);
                    break;
                    //TESTED
                case "getNotificationsByPersonalTrainer":
                    res = gymAtHome.getNotificationsByPersonalTrainer(data);
                    break;
                    //TESTED
                case "markAsReadNotificationsByClient":
                    res = gymAtHome.markAsReadNotificationsByClient(data);
                    break;
                    //TESTED
                case "markAsReadNotificationsByPersonalTrainer":
                    res = gymAtHome.markAsReadNotificationsByPersonalTrainer(data);
                    break;
                case "listRequestsOfPersonalTrainer":
                    res = gymAtHome.listClientRequestsByPersonalTrainer(data);
                    break;
                case "listRequestsOfClient":
                    res = gymAtHome.listClientRequestsByClient(data);
                    break;
                    //TESTED
                case "getUsernameByRequestId":
                    res = gymAtHome.getUsernameByRequestId(data);
                    break;
                case "createdbs":
                    gymAtHome.dropdbs(data);
                    res = makeSuccess(HttpServletResponse.SC_OK,gymAtHome.createdbs(data));
                    break;
                    //TESTED
                case "dropdbs":
                    res = makeSuccess(HttpServletResponse.SC_OK,gymAtHome.dropdbs(data));
                    break;
                case "hasSubmittedClassification":
                    res = gymAtHome.hasSubmittedClassification(data);
                    break;
                default:
                    //TESTED
                    response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    res = makeError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method not allowed - " + target + ".");
                    break;
            }
        }
        catch(IOException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString(); // stack trace as a string
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res = makeError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "I/O error.\n" + stackTrace);
        }
        catch(Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString(); // stack trace as a string
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res = makeError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpeted error.\n" + stackTrace);
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Method get is not support for this API
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        response.setContentType("application/json");
        response.getWriter().print(makeError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,"GET method is not allowed."));
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
