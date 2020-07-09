/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import core.CoreFacade;
import exceptions.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.orm.PersistentException;
import redis.clients.jedis.exceptions.JedisConnectionException;
import static utils.Utils.makeError;
import static utils.Utils.makeSuccess;

/**
 *
 * @author joaomarques
 */
@WebServlet(name="api", urlPatterns="api/*")
public class CoreController extends HttpServlet {

    private CoreFacade coreFacade = CoreFacade.getInstance();

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
        CoreFacade facade = CoreFacade.getInstance();

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
                case "createUserToken":
                    facade.createUserToken(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                case "updateToken":
                    facade.updateToken(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                case "finishWorkout":
                    facade.finishWorkout(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                case "createWeek":
                    facade.createWeek(data);
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK,null);
                    break;
                case "getWeekByClient":
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK, facade.getWeekByClient(data));
                    break;
                case "getWeekByPersonalTrainer":
                    response.setStatus(HttpServletResponse.SC_OK);
                    res = makeSuccess(HttpServletResponse.SC_OK, facade.getWeekByPersonalTrainer(data));
                    break;
                default:
                    response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    res = makeError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method not allowed.");
                    break;
            }
        }
        catch (PersistentException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res = makeError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Hibernate internal error. " + e.getMessage());
        }
        catch (PersonalTrainerDontExistsException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res = makeError(HttpServletResponse.SC_NOT_FOUND,"PersonalTrainer " + e.getMessage() + " dont exists.");
        }
        catch (ClientDontExistsException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res = makeError(HttpServletResponse.SC_NOT_FOUND,"Client " + e.getMessage() + " dont exists.");
        }
        catch (JsonKeyInFaultException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res = makeError(HttpServletResponse.SC_BAD_REQUEST,"Key in fault on Post (" + e.getMessage() + ")");
        }
        catch (UserAlreadyExistsException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            res = makeError(HttpServletResponse.SC_CONFLICT,"User with username = " + e.getMessage() + " already registered.");
        }
        catch (WorkoutDontExistException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res = makeError(HttpServletResponse.SC_NOT_FOUND,"Invalid Workout Id (" + e.getMessage() + ").");
        }
        catch (UserDontExistsException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res = makeError(HttpServletResponse.SC_NOT_FOUND,"User " + e.getMessage() + " dont exists.");
        }
        catch (WorkoutDontBelongToUserException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            String[] aux = e.getMessage().split("\t");
            res = makeError(HttpServletResponse.SC_UNAUTHORIZED,"Workout (" + aux[0] + ") dont belong to user (" + aux[1] + ".");
        }
        catch (WorkoutAlreadyDoneException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            res = makeError(HttpServletResponse.SC_CONFLICT,"Workout (" + e.getMessage() + ") already completed.");
        }
        catch (InvalidTokenException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res = makeError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid token (" + e.getMessage() + ") for given user.");
        }
        catch (ClientAlreadyHasAnPlanException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            res = makeError(HttpServletResponse.SC_CONFLICT,"Client (" + e.getMessage() + ") already have a plan.");
        }
        catch (PlanDontExistException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            res = makeError(HttpServletResponse.SC_NOT_FOUND,"Plan with Id = " + e.getMessage() + " dont exists.");
        }
        catch (JedisConnectionException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            res = makeError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"Couldn't connect to Jedis server.");
        }
        catch (Exception e) {
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
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        response.setContentType("application/json");
        response.getWriter().print(makeError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,"Method not allowed."));
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
