package servlets;

import core.CoreFacade;
import exceptions.*;
import org.orm.PersistentException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static utils.Utils.*;

@WebServlet(name = "CoreController", urlPatterns = "/api/v1/GymAtHome/Core")
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
            if (target.equals("createUserToken"))
                facade.createUserToken(data);
            else if (target.equals("updateToken"))
                facade.updateToken(data);
            else if (target.equals("finishWorkout"))
                facade.finishWorkout(data);
            else if (target.equals("createWeek"))
                facade.createWeek(data);
            else {
                res = makeError(404,"Not found");
            }
            if(res == null)
                res = makeSuccess(200,"");
        }
        catch (PersistentException e) {
            e.printStackTrace();
            res = makeError(500,"Internal Error.");
        }
        catch (PersonalTrainerDontExistsException e) {
            e.printStackTrace();
            res = makeError(404,"PersonalTrainer " + e.getMessage() + " dont exists.");
        }
        catch (ClientDontExistsException e) {
            e.printStackTrace();
            res = makeError(404,"Client " + e.getMessage() + " dont exists.");
        }
        catch (JsonKeyInFaultException e) {
            e.printStackTrace();
            res = makeError(404,"Key in fault on Post (" + e.getMessage() + ")");
        }
        catch (UserAlreadyExistsException e) {
            e.printStackTrace();
            res = makeError(404,"User with username = " + e.getMessage() + " already registered.");
        }
        catch (WorkoutDontExistException e) {
            e.printStackTrace();
            res = makeError(404,"Invalid Workout Id (" + e.getMessage() + ").");
        }
        catch (UserDontExistsException e) {
            e.printStackTrace();
            res = makeError(404,"User " + e.getMessage() + " dont exists.");
        }
        catch (WorkoutDontBelongToUserException e) {
            e.printStackTrace();
            String[] aux = e.getMessage().split("\t");
            res = makeError(403,"Workout (" + aux[0] + ") dont belong to user (" + aux[1] + ".");
        }
        catch (WorkoutAlreadyDoneException e) {
            e.printStackTrace();
            res = makeError(400,"Workout (" + e.getMessage() + ") already completed.");
        }
        catch (InvalidTokenException e) {
            e.printStackTrace();
            res = makeError(400,"Invalid token (" + e.getMessage() + ") for given user.");
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
        CoreFacade facade = CoreFacade.getInstance();

        // Obtain target
        String[] url = request.getRequestURI().split("/");
        String target = url[url.length-1];

        // Obtain url parameters
        Map<String,String[]> parameters = request.getParameterMap();

        // Create a json string with the parameters
        String data = parametersToJSON(parameters);
        String res = null;

        try {
            if(target.equals("getWeekByClient"))
                res = makeSuccess(200,facade.getWeekByClient(data));
            else if(target.equals("getWeekByPersonalTrainer"))
                res = makeSuccess(200,facade.getWeekByPersonalTrainer(data));
            else
                res = makeError(405,"Method not allowed");
        }
        catch (PersistentException e) {
            e.printStackTrace();
            res = makeError(500,"Internal Error.");
        }
        catch (JsonKeyInFaultException e) {
            e.printStackTrace();
            res = makeError(404,"Key in fault on Post (" + e.getMessage() + ")");
        }
        catch (PersonalTrainerDontExistsException e) {
            e.printStackTrace();
            res = makeError(404,"PersonalTrainer " + e.getMessage() + " dont exists.");
        }
        catch (UserDontExistsException e) {
            e.printStackTrace();
            res = makeError(404,"User " + e.getMessage() + " dont exists.");
        }
        catch (ClientDontExistsException e) {
            e.printStackTrace();
            res = makeError(404,"Client " + e.getMessage() + " dont exists.");
        }
        catch (InvalidTokenException e) {
            e.printStackTrace();
            res = makeError(400,"Invalid token (" + e.getMessage() + ") for given user.");
        }
        finally {
            response.setContentType("application/json");
            response.getWriter().print(res);
        }
    }
}
