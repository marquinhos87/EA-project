package commonservlets;

import core.Notification;
import parseJSON.ResponseJSON;
import utils.Http;
import utils.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import okhttp3.Response;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@WebServlet(name = "NotificationServlet", urlPatterns = "/Notification")
public class NotificationServlet extends HttpServlet {

    private final Gson gson = new GsonBuilder().create();

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
        String username = (String) request.getSession().getAttribute("username");
        String token = (String) request.getSession().getAttribute("token");
        if(username == null || token == null) {
            request.getSession().setAttribute("username",null);
            request.getSession().setAttribute("token",null);
            request.getSession().setAttribute("userType",null);
            request.setAttribute("title","Login");
            Utils.forward(request,response,"/WEB-INF/Template.jsp","Login",null);
        }
        else {
            String[] notificationIds = request.getParameterValues("notificationId[]");

            if(notificationIds != null && notificationIds.length != 0) {

                JsonObject jo = new JsonObject();
                jo.addProperty("username", username);
                jo.addProperty("token", token);

                int[] notificationsIds = new int[notificationIds.length];
                for(int i = 0 ; i < notificationIds.length ; i++)
                    notificationsIds[i] = Integer.parseInt(notificationIds[i]);

                jo.add("ids",gson.toJsonTree(notificationsIds));

                Response responseHttp;

                System.err.println(jo.toString());

                try {
                    if (username.startsWith("c"))
                        responseHttp = Http.post(Utils.SERVER + "markAsReadNotificationsByClient", jo.toString());
                    else if (username.startsWith("pt"))
                        responseHttp = Http.post(Utils.SERVER + "markAsReadNotificationsByPersonalTrainer", jo.toString());
                    else {
                        request.getSession().setAttribute("username", null);
                        request.getSession().setAttribute("token", null);
                        request.getSession().setAttribute("userType", null);
                        request.setAttribute("title", "Login");
                        Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", Utils.CONNECTION_LOST_MSG);
                    request.getSession().setAttribute("username", null);
                    request.getSession().setAttribute("token", null);
                    request.getSession().setAttribute("userType", null);
                    request.setAttribute("title", "Login");
                    Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
                    return;
                }

                String body = responseHttp.body().string();
                ResponseJSON responseJSON = gson.fromJson(body, ResponseJSON.class);

                responseHttp.close();

                System.err.println(body);

                if (responseJSON.status.equals("success")) {
                    doGet(request, response);
                } else {
                    request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                    request.setAttribute("title", "Notificações");
                    Utils.forward(request, response, "/WEB-INF/Template.jsp", "Notification", null);
                }
            }
            else {
                request.setAttribute("warningMessage","Não selecionou nenhuma notificação para marcar como lida.");
                request.setAttribute("title","Notificações");
                Utils.forward(request,response,"/WEB-INF/Template.jsp","Notification",null);
            }
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
        String username = (String) request.getSession().getAttribute("username");
        String token = (String) request.getSession().getAttribute("token");
        if(username == null || token == null) {
            request.getSession().setAttribute("username",null);
            request.getSession().setAttribute("token",null);
            request.getSession().setAttribute("userType",null);
            request.setAttribute("title","Login");
            Utils.forward(request,response,"/WEB-INF/Template.jsp","Login",null);
        }
        else {
            JsonObject jo = new JsonObject();
            jo.addProperty("username",username);
            jo.addProperty("token",token);

            Response responseHttp;

            try {
                if(username.startsWith("c"))
                    responseHttp = Http.post(Utils.SERVER + "getNotificationsByClient",jo.toString());
                else if(username.startsWith("pt"))
                    responseHttp = Http.post(Utils.SERVER + "getNotificationsByPersonalTrainer",jo.toString());
                else {
                    request.getSession().setAttribute("username",null);
                    request.getSession().setAttribute("token",null);
                    request.getSession().setAttribute("userType",null);
                    request.setAttribute("title","Login");
                    Utils.forward(request,response,"/WEB-INF/Template.jsp","Login",null);
                    return;
                }
            }
            catch (IOException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage",Utils.CONNECTION_LOST_MSG);
                request.getSession().setAttribute("username",null);
                request.getSession().setAttribute("token",null);
                request.getSession().setAttribute("userType",null);
                request.setAttribute("title","Login");
                Utils.forward(request,response,"/WEB-INF/Template.jsp","Login",null);
                return;
            }

            String body = responseHttp.body().string();
            System.err.println(body);
            ResponseJSON responseJSON = gson.fromJson(body,ResponseJSON.class);

            responseHttp.close();

            if (responseJSON.status.equals("success")) {
                Notification[] tmps = gson.fromJson(responseJSON.data,Notification[].class);

                Collection<Notification> notifications = new ArrayList<>(Arrays.asList(tmps));
                request.setAttribute("notifications", notifications);
            }
            else {
                request.setAttribute("errorMessage",Utils.UNEXPECTED_ERROR_MSG);
            }

            request.setAttribute("title","Notificações");
            Utils.forward(request,response,"/WEB-INF/Template.jsp","Notification",null);
        }
    }
}
