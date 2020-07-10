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
                case "createClient":
                    res = gymAtHome.createClient(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "createPersonalTrainer":
                    res = gymAtHome.createPersonalTrainer(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "loginClient":
                    res = gymAtHome.loginClient(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "loginPersonalTrainer":
                    res = gymAtHome.loginPersonalTrainer(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "getClientProfile":
                    res = gymAtHome.getClientProfile(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "getPersonalTrainerProfile":
                    res = gymAtHome.getPersonalTrainerProfile(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "getPersonalTrainers":
                    res = gymAtHome.getPersonalTrainers(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "editClientProfile":
                    gymAtHome.editClientProfile(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                case "editPersonalTrainerProfile":
                    gymAtHome.editPersonalTrainertProfile(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                case "getPlanByClient":
                    res = gymAtHome.getPlanByClient(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "getPlanByPersonalTrainer":
                    res = gymAtHome.getPlanByPersonalTrainer(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "getBiometricData":
                    res = gymAtHome.getBiometricData(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "submitClassification":
                    res = gymAtHome.submitClassification(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "finishWorkout":
                    gymAtHome.finishWorkout(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                case "getPersonalTrainerClients":
                    res = gymAtHome.getPersonalTrainerClients(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "submitRequest":
                    gymAtHome.submitRequest(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                case "createWeek":
                    gymAtHome.createWeek(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                case "replyToRequest":
                    gymAtHome.replyToRequest(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
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
