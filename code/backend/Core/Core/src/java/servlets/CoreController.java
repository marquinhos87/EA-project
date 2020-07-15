/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import core.CoreFacade;
import exceptions.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.orm.ORMDatabaseInitiator;
import org.orm.PersistentException;
import static utils.Utils.makeError;
import static utils.Utils.makeSuccess;
import static utils.Utils.validateJson;

/**
 *
 * @author joaomarques
 */
@WebServlet(name="api", urlPatterns="api/*")
public class CoreController extends HttpServlet {

    private final CoreFacade coreFacade = CoreFacade.getInstance();

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
        
        // Execute the correct method
        try {
            switch (target) {
                case "createUserTokenClient":
                    coreFacade.createUserTokenClient(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                case "createUserTokenPersonalTrainer":
                    coreFacade.createUserTokenPersonalTrainer(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                case "updateToken":
                    coreFacade.updateToken(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                case "finishWorkout":
                    res = makeSuccess(HttpServletResponse.SC_OK, coreFacade.finishWorkout(data));
                    response.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "createWeek":
                    coreFacade.createWeek(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                case "getWeekByClient":
                    res = makeSuccess(HttpServletResponse.SC_OK, coreFacade.getWeekByClient(data));
                    response.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "getWeekByPersonalTrainer":
                    res = makeSuccess(HttpServletResponse.SC_OK, coreFacade.getWeekByPersonalTrainer(data));
                    response.setStatus(HttpServletResponse.SC_OK);
                    break;
                case "finishPlan":
                    coreFacade.finishPlan(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                case "removeUserTokenClient":
                    coreFacade.removeUserTokenClient(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                case "removeUserTokenPersonalTrainer":
                    coreFacade.removeUserTokenPersonalTrainer(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                case "createdb":
                    createdb(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                case "dropdb":
                    dropdb(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                default:
                    response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    res = makeError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method not allowed.");
                    break;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res = makeError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"I/O error. " + e.getMessage());
        }
        catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res = makeError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"SQL error. " + e.getMessage());
        }
        catch (PersistentException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res = makeError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Hibernate internal error. " + e.getMessage());
        }
        catch (ClientDontExistsException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res = makeError(HttpServletResponse.SC_NOT_FOUND,"Client " + e.getMessage() + " dont exists.");
        }
        catch (ClientAlreadyHasAnPlanException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            res = makeError(HttpServletResponse.SC_CONFLICT,"Client (" + e.getMessage() + ") already have a plan.");
        }
        catch (InvalidTokenException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res = makeError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid token (" + e.getMessage() + ") for given user.");
        }
        catch (InvalidWeekNumberException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res = makeError(HttpServletResponse.SC_BAD_REQUEST,"Week with invalid number = " + e.getMessage());
        }
        catch (JsonKeyInFaultException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res = makeError(HttpServletResponse.SC_BAD_REQUEST,"Key in fault on Post (" + e.getMessage() + ")");
        }
        catch (PersonalTrainerDontExistsException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res = makeError(HttpServletResponse.SC_NOT_FOUND,"PersonalTrainer " + e.getMessage() + " dont exists.");
        }
        catch (PlanDontExistException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res = makeError(HttpServletResponse.SC_NOT_FOUND,"Plan with Id = " + e.getMessage() + " dont exists.");
        }
        catch (UserAlreadyExistsException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            res = makeError(HttpServletResponse.SC_CONFLICT,"User with username = " + e.getMessage() + " already registered.");
        }
        catch (UserDontExistsException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res = makeError(HttpServletResponse.SC_NOT_FOUND,"User " + e.getMessage() + " dont exists.");
        }
        catch (UsernameDontBelongToClientException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            res = makeError(HttpServletResponse.SC_CONFLICT,"Username " + e.getMessage() + " dont belong to a Client.");
        }
        catch (WorkoutAlreadyDoneException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            res = makeError(HttpServletResponse.SC_CONFLICT,"Workout (" + e.getMessage() + ") already completed.");
        }
        catch (WorkoutDontBelongToUserException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            String[] aux = e.getMessage().split("\t");
            res = makeError(HttpServletResponse.SC_UNAUTHORIZED,"Workout (" + aux[0] + ") dont belong to user (" + aux[1] + ".");
        }
        catch (WorkoutDontExistException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res = makeError(HttpServletResponse.SC_NOT_FOUND,"Invalid Workout Id (" + e.getMessage() + ").");
        }
        catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res = makeError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Unexcepted error. " + e.getMessage());
        }
        finally {
            response.setContentType("application/json; charset=utf-8");
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
    
    private static void dropdb(String json) throws PersistentException, SQLException, IOException, InvalidTokenException, JsonKeyInFaultException {
        JsonObject jo = validateJson(new Gson(), json, Arrays.asList("token"));
        String token = jo.get("token").getAsString();
        if (token.equals("admin") == false)
            throw new InvalidTokenException(token);
        ORMDatabaseInitiator.dropSchema(core.DiagramasPersistentManager.instance());
        core.DiagramasPersistentManager.instance().disposePersistentManager();
    }
    private static void createdb(String json) throws PersistentException, SQLException, IOException, InvalidTokenException, JsonKeyInFaultException {
        JsonObject jo = validateJson(new Gson(), json, Arrays.asList("token"));
        String token = jo.get("token").getAsString();
        if (token.equals("admin") == false)
            throw new InvalidTokenException(token);
        ORMDatabaseInitiator.createSchema(core.DiagramasPersistentManager.instance());
	core.DiagramasPersistentManager.instance().disposePersistentManager();
    }
}
