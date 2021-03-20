package personaltrainerservlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import core.*;
import okhttp3.Response;
import parseJSON.ResponseJSON;
import parseJSON.deserializer.TaskDeserializer;
import parseJSON.deserializer.WeekDeserializer;
import parseJSON.deserializer.WorkoutDeserializer;
import utils.Http;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "MyClients", urlPatterns = "/MyClients")
public class MyClientsServlet extends HttpServlet {
    private final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .registerTypeAdapter(Week.class, new WeekDeserializer())
            .registerTypeAdapter(Workout.class, new WorkoutDeserializer())
            .registerTypeAdapter(Task.class, new TaskDeserializer())
            .create();
    private HttpSession session;
    private String username;
    private String token;

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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        username = (String) session.getAttribute("username");
        token = (String) session.getAttribute("token");

        if (username == null || token == null) { // NOT logged in
            Utils.redirect(request, response, "/Login");
            return;
        }

        Response responseHttp = null;
        String responseBody;
        ResponseJSON responseJSON;
        JsonObject jo = new JsonObject();

        jo.addProperty("username", username);
        jo.addProperty("token", token);

        try {
            responseHttp = Http.post(Utils.SERVER + "getPersonalTrainerClients",jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
            request.setAttribute("title", "Meus clientes");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "MyClients", null);
            return ;
        }

        responseBody = responseHttp.body().string();
        try{
            Utils.validateJson(gson, responseBody, Arrays.asList("status", "code", "msg", "data"));
            responseJSON = gson.fromJson(responseBody, ResponseJSON.class);
        } catch (Exception e){
            e.printStackTrace();
            request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
            request.setAttribute("title", "Meus clientes");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "MyClients", null);
            return ;
        }

        if(responseJSON.status.equals("success")){
            Client[] clients = gson.fromJson(responseJSON.data.toString(), Client[].class);
            List<Client> tmps = Arrays.asList(clients);
            request.setAttribute("clients", tmps);
            request.setAttribute("title", "Meus clientes");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "MyClients", null);
        }else{
            switch (responseJSON.code){
                default:
                    request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                    request.setAttribute("title", "Meus clientes");
                    Utils.forward(request, response, "/WEB-INF/Template.jsp", "MyClients", null);
                    break;
            }
        }
    }
}
