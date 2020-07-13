/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hrclient.BiometricDataDoesNotExistException;
import hrclient.ClientAlreadyExistsException;
import hrclient.ClientDoesNotExistException;
import hrclient.HRClientFacade;
import hrclient.InvalidPasswordException;
import hrclient.JsonKeyInFaultException;
import hrclient.PersonalTrainerDoesNotExistException;
import hrclient.TokenIsInvalidException;
import hrclient.UserAlreadyExistsException;
import hrclient.UserDoesNotExistException;
import hrclient.Utils;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.orm.ORMDatabaseInitiator;
import org.orm.PersistentException;

/**
 *
 * @author josepereira
 */
@WebServlet(name="api", urlPatterns="api/*")
public class HRClientAPI extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        response.setContentType("application/json");
        response.getWriter().print(Utils.makeError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method GET not allowed."));
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] url = request.getRequestURI().toString().split("/");
        String target = url[url.length-1], line = "";

        response.setContentType("application/json");

        StringBuilder sb = new StringBuilder();

        while((line = request.getReader().readLine())!=null)sb.append(line);
        
        String json = sb.toString();
        
        HRClientFacade facade = HRClientFacade.getInstance();

        switch (target){
            case "updateUserToken":
                try{
                    facade.updateUserToken(json);
                } catch (JsonKeyInFaultException ex){
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(11, "Json key in fault: " + ex.getMessage()) + ".");
                    break;
                } catch (TokenIsInvalidException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(13, "Token is invalid."));
                    break;
                } catch (UserDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(12, "User with username " + ex.getMessage() + " does not exist on database."));
                    break;
                }catch (Exception ex){
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    ex.printStackTrace(pw);
                    String stackTrace = sw.toString(); // stack trace as a string
                    ex.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(10, stackTrace));
                    break;
                }
                response.setStatus(200);
                response.getWriter().print(Utils.makeSuccess(1,"\"User token updated with success.\""));
                break;
            case "createClient":
            {
                try {
                    json = facade.createClient(json);
                } catch (JsonKeyInFaultException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(11, "Json key in fault: " + ex.getMessage()) + ".");
                    break;
                } catch (ClientAlreadyExistsException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(16, "Client with username " + ex.getMessage() + " already exist."));
                    break;
                } catch (UserAlreadyExistsException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(15, "User with username " + ex.getMessage() + " already exist."));
                    break;
                } catch (Exception ex){
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    ex.printStackTrace(pw);
                    String stackTrace = sw.toString(); // stack trace as a string
                    ex.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(10, stackTrace));
                    break;
                }
                response.setStatus(200);
                response.getWriter().print(Utils.makeSuccess(1,json));
                break;
            }
            case "createUser":
            {
                try {
                    facade.createUser(json);
                } catch (JsonKeyInFaultException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(11, "Json key in fault: " + ex.getMessage()) + ".");
                    break;
                } catch (UserAlreadyExistsException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(15, "User with username " + ex.getMessage() + " already exist."));
                    break;
                }catch (Exception ex){
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    ex.printStackTrace(pw);
                    String stackTrace = sw.toString(); // stack trace as a string
                    ex.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(10, stackTrace));
                    break;
                }
                response.setStatus(200);
                response.getWriter().print(Utils.makeSuccess(1,"\"User was created with success.\""));
                break;
            }
            case "loginClient":
            {
                try {
                    json = facade.loginClient(json);
                } catch (InvalidPasswordException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(22, "Invalid password."));
                    break;
                } catch (JsonKeyInFaultException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(11, "Json key in fault: " + ex.getMessage()) + ".");
                    break;
                } catch (ClientDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(20, "Client with username " + ex.getMessage() + " does not exist on database."));
                    break;
                }catch (Exception ex){
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    ex.printStackTrace(pw);
                    String stackTrace = sw.toString(); // stack trace as a string
                    ex.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(10, stackTrace));
                    break;
                }
                response.setStatus(200);
                response.getWriter().print(Utils.makeSuccess(1,json));
                break;
            }
            case "getClientProfileByClient":
            {
                try {
                    json = facade.getClientProfileByClient(json);
                } catch (TokenIsInvalidException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(13, "Token is invalid."));
                    break;
                } catch (JsonKeyInFaultException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(11, "Json key in fault: " + ex.getMessage()) + ".");
                    break;
                } catch (ClientDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(20, "Client with username " + ex.getMessage() + " does not exist on database."));
                    break;
                } catch (UserDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(12, "User with username " + ex.getMessage() + " does not exist."));
                    break;
                } catch (BiometricDataDoesNotExistException ex){
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(23, "BiometricData does not exist."));
                    break;
                } catch (Exception ex){
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    ex.printStackTrace(pw);
                    String stackTrace = sw.toString(); // stack trace as a string
                    ex.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(10, stackTrace));
                    break;
                }
                response.setStatus(200);
                response.getWriter().print(Utils.makeSuccess(1,json));
                break;
            }
            case "getClientProfileByPersonalTrainer":
            {
                try {
                    json = facade.getClientProfileByPersonalTrainer(json);
                } catch (TokenIsInvalidException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(13, "Token is invalid."));
                    break;
                } catch (PersonalTrainerDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(17, "PersonalTrainer with username " + ex.getMessage() + " does not exist on database."));
                    break;
                } catch (JsonKeyInFaultException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(11, "Json key in fault: " + ex.getMessage()) + ".");
                    break;
                } catch (ClientDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(20, "Client with username " + ex.getMessage() + " does not exist on database."));
                    break;
                } catch (UserDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(12, "User with username " + ex.getMessage() + " does not exist."));
                    break;
                } catch (BiometricDataDoesNotExistException ex){
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(23, "BiometricData does not exist."));
                    break;
                } catch (Exception ex){
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    ex.printStackTrace(pw);
                    String stackTrace = sw.toString(); // stack trace as a string
                    ex.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(10, stackTrace));
                    break;
                }
                response.setStatus(200);
                response.getWriter().print(Utils.makeSuccess(1,json));
                break;
            }
            case "editClientProfile":
            {
                try {
                    facade.editClientProfile(json);
                } catch (JsonKeyInFaultException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(11, "Json key in fault: " + ex.getMessage()) + ".");
                    break;
                } catch (ClientDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(20, "Client with username " + ex.getMessage() + " does not exist on database."));
                    break;
                } catch (TokenIsInvalidException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(13, "Token is invalid."));
                    break;
                } catch (UserDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(12, "User with username " + ex.getMessage() + " does not exist."));
                    break;
                } catch (Exception ex){
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    ex.printStackTrace(pw);
                    String stackTrace = sw.toString(); // stack trace as a string
                    ex.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(10, stackTrace));
                    break;
                }
                response.setStatus(200);
                response.getWriter().print(Utils.makeSuccess(1,"\"Profile edited with success.\""));
                break;
            }
            case "getBiometricData":
            {
                try {
                    json = facade.getBiometricData(json);
                } catch (TokenIsInvalidException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(13, "Token is invalid."));
                    break;
                } catch (ClientDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(20, "Client with username " + ex.getMessage() + " does not exist on database."));
                    break;
                } catch (PersonalTrainerDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(17, "PersonalTrainer with username " + ex.getMessage() + " does not exist on database."));
                    break;
                } catch (JsonKeyInFaultException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(11, "Json key in fault: " + ex.getMessage()) + ".");
                    break;
                }catch (UserDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(12, "User with username " + ex.getMessage() + " does not exist."));
                    break;
                } catch (BiometricDataDoesNotExistException ex){
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(23, "BiometricData does not exist."));
                    break;
                } catch (Exception e){
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    e.printStackTrace(pw);
                    String stackTrace = sw.toString(); // stack trace as a string
                    e.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(10, stackTrace));
                    break;
                }
                response.setStatus(200);
                response.getWriter().print(Utils.makeSuccess(1,json));
                break;
            }
            case "dropdb":
            {
                try {
                    HRClientAPI.dropdb(response, json);
                } catch (PersistentException ex) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(14, "Error with session."));
                    break;
                } catch (TokenIsInvalidException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(13, "Token is invalid."));
                    break;
                } catch (JsonKeyInFaultException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(11, "Json key in fault: " + ex.getMessage()) + ".");
                    break;
                } catch (Exception ex){
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    ex.printStackTrace(pw);
                    String stackTrace = sw.toString(); // stack trace as a string
                    ex.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(10, stackTrace));
                    break;
                }
                response.setStatus(200);
                response.getWriter().print(Utils.makeSuccess(1,"\"Database was droped with success.\""));
                break;
            }
            case "createdb":
            {
                try {
                    HRClientAPI.createdb(response, json);
                } catch (PersistentException ex) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(14, "Error with session."));
                    break;
                } catch (TokenIsInvalidException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(13, "Token is invalid."));
                    break;
                } catch (JsonKeyInFaultException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(11, "Json key in fault: " + ex.getMessage()) + ".");
                    break;
                } catch (Exception ex){
                    StringWriter sw = new StringWriter();
                    PrintWriter pw = new PrintWriter(sw);
                    ex.printStackTrace(pw);
                    String stackTrace = sw.toString(); // stack trace as a string
                    ex.printStackTrace();
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(10, stackTrace));
                    break;
                }
                response.setStatus(200);
                response.getWriter().print(Utils.makeSuccess(1,"\"Database was created with success.\""));
                break;
            }
            default:
                response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                response.getWriter().print(Utils.makeError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,"There is no such target for " + target + "."));
                break;
        }
    }
    
    private static void dropdb(HttpServletResponse response, String json) throws PersistentException, SQLException, IOException, TokenIsInvalidException, JsonKeyInFaultException {
        JsonObject jo = Utils.validateJson(new Gson(), json, Arrays.asList("token"));
        String token = jo.get("token").getAsString();
        if (token.equals("admin") == false)
            throw new TokenIsInvalidException(token);
        PrintWriter out = response.getWriter();
        ORMDatabaseInitiator.dropSchema(hrclient.DiagramasPersistentManager.instance());
        hrclient.DiagramasPersistentManager.instance().disposePersistentManager();
    }
    private static void createdb(HttpServletResponse response, String json) throws PersistentException, SQLException, IOException, TokenIsInvalidException, JsonKeyInFaultException {
        JsonObject jo = Utils.validateJson(new Gson(), json, Arrays.asList("token"));
        String token = jo.get("token").getAsString();
        if (token.equals("admin") == false)
            throw new TokenIsInvalidException(token);
        PrintWriter out = response.getWriter();
        ORMDatabaseInitiator.createSchema(hrclient.DiagramasPersistentManager.instance());
	hrclient.DiagramasPersistentManager.instance().disposePersistentManager();
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

    //  LANGUAGE OF SERVICE
    /**
     * SUCCESS: 1 to 10
     * 
     * 1 - normal success
     * 
     * 
     * ERRORS: 11 to ...
     * 
     * 11 - JsonKeyInFaultException
     * 12 - UserDoesNotExistException
     * 13 - TokenIsInvalidException
     * 14 - PersistentException
     * 15 - UserAlreadyExistsException
     * 16 - ClientAlreadyExistsException
     * 17 - PersonalTrainerDoesNotExistException
     * 18 - PersonalTrainerAlreadyExistsException
     * 19 - Probably json not validated format
     * 20 - ClientDoesNotExistException
     * 21 - RequestDoesNotExsistException
     * 22 - InvalidPasswordException
     * 23 - BiometricDataDoesNotExistException
     */
}
