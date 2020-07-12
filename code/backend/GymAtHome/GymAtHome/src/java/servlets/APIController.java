/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import backend.GymAtHome;
import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.GymAtHomeException;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                case "getPersonalTrainerProfileByClient":
                    res = gymAtHome.getPersonalTrainerProfileByClient(data);
                    break;
                case "getPersonalTrainerProfileByPersonalTrainer":
                    res = gymAtHome.getPersonalTrainerProfileByPersonalTrainer(data);
                    break;
                case "getPersonalTrainers":
                    res = gymAtHome.getPersonalTrainers(data);
                    break;
                case "editClientProfile":
                    res = gymAtHome.editClientProfile(data);
                    break;
                case "editPersonalTrainerProfile":
                    res = gymAtHome.editPersonalTrainertProfile(data);
                    break;
                case "getPlanByClient":
                    res = gymAtHome.getPlanByClient(data);
                    break;
                case "getPlanByPersonalTrainer":
                    res = gymAtHome.getPlanByPersonalTrainer(data);
                    break;
                case "getBiometricData":
                    res = gymAtHome.getBiometricData(data);
                    break;
                case "submitClassification":
                    res = gymAtHome.submitClassification(data);
                    break;
                case "finishWorkout":
                    res = gymAtHome.finishWorkout(data);
                    break;
                case "getPersonalTrainerClients":
                    res = gymAtHome.getPersonalTrainerClients(data);
                    break;
                case "submitRequest":
                    res = gymAtHome.submitRequest(data);
                    break;
                case "createWeek":
                    res = gymAtHome.createWeek(data);
                    break;
                case "replyToRequest":
                    res = gymAtHome.replyToRequest(data);
                    break;
                default:
                    response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    res = makeError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method not allowed.");
                    break;
            }
        }
        catch(IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res = makeError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Couldn't connect to external service.");
        }
        catch(GymAtHomeException e) {
            e.printStackTrace();
            // Use of ObjectMapper because is a lightweight unlike gson
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = mapper.readValue(e.getMessage(), Map.class);
            response.setStatus(Integer.parseInt(map.get("code")));
            res = e.getMessage();
        }
        catch(Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res = makeError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Unexcepted error. " + e.getMessage());
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
