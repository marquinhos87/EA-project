package rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import notifications.ClientNotExistsException;
import notifications.JsonKeyInFaultException;
import notifications.NotificationFacade;
import notifications.NotificationNotExistsException;
import notifications.PersonalTrainerNotExistsException;
import notifications.TokenIsInvalidException;
import notifications.UserAlreadyExistsException;
import notifications.UserNotExistsException;
import notifications.Utils;
import org.orm.PersistentException;


@WebServlet(name = "NotificatiosController", urlPatterns = {"/*"})
public class NotificationsController extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json"); // indicar que o conteúdo é json
        PrintWriter out = response.getWriter();
        response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        out.print(Utils.makeError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method is not allowed."));
    }

    /**
     * 
     * @param request
     * @param response
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            response.setContentType("application/json"); // indicar que o conteúdo é json
            String[] url = request.getRequestURI().split("/");
            String target = url[url.length-1];
            switch (target) {
                case "createNotificationToClient":
                    createNotificationToClient(response, getDataFromPost(request));
                    break;
                case "createNotificationToPersonalTrainer":
                    createNotificationToPersonalTrainer(response, getDataFromPost(request));
                    break;
                case "markAsReadNotificationsByClient":
                    markAsReadNotificationsByClient(response, getDataFromPost(request));
                    break;
                case "markAsReadNotificationsByPersonalTrainer":
                    markAsReadNotificationsByPersonalTrainer(response, getDataFromPost(request));
                    break;
                case "getNotificationsByClient":
                    getNotificationsByClient(response, getDataFromPost(request));
                    break;
                case "getNotificationsByPersonalTrainer":
                    getNotificationsByPersonalTrainer(response, getDataFromPost(request));
                    break;
                case "createClient":
                    createClient(response, getDataFromPost(request));
                    break;
                case "createPersonalTrainer":
                    createPersonalTrainer(response, getDataFromPost(request));
                    break;
                case "updateClientToken":
                    updateClientToken(response, getDataFromPost(request));
                    break;
                case "updatePersonalTrainerToken":
                    updatePersonalTrainerToken(response, getDataFromPost(request));
                    break;
                default:
                    response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
                    response.getWriter().print(Utils.makeError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "target is not allowed."));
            }
        } catch (JsonKeyInFaultException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(Utils.makeError(HttpServletResponse.SC_BAD_REQUEST, "json key in fault - " + e.getMessage() + "."));
        } catch (TokenIsInvalidException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.print(Utils.makeError(HttpServletResponse.SC_UNAUTHORIZED, "token is invalid - " + e.getMessage() +  "."));
        } catch (PersistentException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print(Utils.makeError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage()));
        } catch (ClientNotExistsException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print(Utils.makeError(HttpServletResponse.SC_NOT_FOUND, "client with specified username does not exist - " + e.getMessage() +  "."));
        } catch (PersonalTrainerNotExistsException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print(Utils.makeError(HttpServletResponse.SC_NOT_FOUND, "personal trainer with specified username does not exist - " + e.getMessage() +  "."));
        } catch (NotificationNotExistsException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print(Utils.makeError(HttpServletResponse.SC_NOT_FOUND, "notification with specified id does not exist - " + e.getMessage() +  "."));
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            out.print(Utils.makeError(HttpServletResponse.SC_CONFLICT, "user with specified username already exists - " + e.getMessage() +  "."));
        } catch (UserNotExistsException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print(Utils.makeError(HttpServletResponse.SC_NOT_FOUND, "user with specified username does not exist - " + e.getMessage() +  "."));
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print(Utils.makeError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "unexpected error occurred."));
        }
    }
    
    private static String getDataFromPost(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
                sb.append(line);
        }
        return sb.toString();
    }

    private void createNotificationToClient(HttpServletResponse response, String json) throws IOException, JsonKeyInFaultException, TokenIsInvalidException, PersistentException, ClientNotExistsException {
        PrintWriter out = response.getWriter();
        NotificationFacade.getInstance().createNotificationToClient(json);
        response.setStatus(HttpServletResponse.SC_OK);
        out.print(Utils.makeSuccess200(null));
    }

    private void createNotificationToPersonalTrainer(HttpServletResponse response, String json) throws JsonKeyInFaultException, TokenIsInvalidException, PersistentException, PersonalTrainerNotExistsException, IOException {
        PrintWriter out = response.getWriter();
        NotificationFacade.getInstance().createNotificationToPersonalTrainer(json);
        response.setStatus(HttpServletResponse.SC_OK);
        out.print(Utils.makeSuccess200(null));
    }

    private void markAsReadNotificationsByClient(HttpServletResponse response, String json) throws IOException, JsonKeyInFaultException, ClientNotExistsException, PersistentException, TokenIsInvalidException, NotificationNotExistsException {
        PrintWriter out = response.getWriter();
        NotificationFacade.getInstance().markAsReadNotificationsByClient(json);
        response.setStatus(HttpServletResponse.SC_OK);
        out.print(Utils.makeSuccess200(null));
    }

    private void markAsReadNotificationsByPersonalTrainer(HttpServletResponse response, String json) throws JsonKeyInFaultException, PersonalTrainerNotExistsException, PersistentException, IOException, TokenIsInvalidException, NotificationNotExistsException {
        PrintWriter out = response.getWriter();
        NotificationFacade.getInstance().markAsReadNotificationsByPersonalTrainer(json);
        response.setStatus(HttpServletResponse.SC_OK);
        out.print(Utils.makeSuccess200(null));
    }

    private void getNotificationsByClient(HttpServletResponse response, String json) throws IOException, PersistentException, JsonKeyInFaultException, ClientNotExistsException, TokenIsInvalidException {
        PrintWriter out = response.getWriter();
        String notifications = NotificationFacade.getInstance().getNotificationsByClient(json);
        response.setStatus(HttpServletResponse.SC_OK);
        out.print(Utils.makeSuccess200(notifications));
    }

    private void getNotificationsByPersonalTrainer(HttpServletResponse response, String json) throws IOException, PersistentException, JsonKeyInFaultException, ClientNotExistsException, TokenIsInvalidException {
        PrintWriter out = response.getWriter();
        String notifications = NotificationFacade.getInstance().getNotificationsByClient(json);
        response.setStatus(HttpServletResponse.SC_OK);
        out.print(Utils.makeSuccess200(notifications));
    }

    private void createClient(HttpServletResponse response, String json) throws IOException, JsonKeyInFaultException, PersistentException, UserAlreadyExistsException {
        PrintWriter out = response.getWriter();
        NotificationFacade.getInstance().createClient(json);
        response.setStatus(HttpServletResponse.SC_OK);
        out.print(Utils.makeSuccess200(null));
    }

    private void createPersonalTrainer(HttpServletResponse response, String json) throws IOException, JsonKeyInFaultException, PersistentException, UserAlreadyExistsException {
        PrintWriter out = response.getWriter();
        NotificationFacade.getInstance().createPersonalTrainer(json);
        response.setStatus(HttpServletResponse.SC_OK);
        out.print(Utils.makeSuccess200(null));
    }

    private void updateClientToken(HttpServletResponse response, String json) throws IOException, JsonKeyInFaultException, PersistentException, TokenIsInvalidException, UserNotExistsException {
        PrintWriter out = response.getWriter();
        NotificationFacade.getInstance().updateClientToken(json);
        response.setStatus(HttpServletResponse.SC_OK);
        out.print(Utils.makeSuccess200(null));
    }

    private void updatePersonalTrainerToken(HttpServletResponse response, String json) throws IOException, JsonKeyInFaultException, PersistentException, TokenIsInvalidException, UserNotExistsException {
        PrintWriter out = response.getWriter();
        NotificationFacade.getInstance().updatePersonalTrainerToken(json);
        response.setStatus(HttpServletResponse.SC_OK);
        out.print(Utils.makeSuccess200(null));
    }


}