package personaltrainerservlets;

import com.google.gson.*;
import core.Request;
import okhttp3.Response;
import parseJSON.ResponseJSON;
import utils.Http;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "MyRequestsServlet", urlPatterns = "/MyRequestsPT")
public class MyRequestsServlet extends HttpServlet {

    private HttpSession session = null;
    private final Gson gson = new GsonBuilder().create();
    private String action = null;
    private String username = null;
    private String token = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        action = request.getParameter("action");
        username = (String) session.getAttribute("username");
        token = (String) session.getAttribute("token");

        System.err.println("CHEGUEI AQUI!asdasdasd");

        String message = "";

        if(username == null || token == null){
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
            return ;
        }

        JsonObject jo = new JsonObject();

        Response responseHttp = null;

        jo.addProperty("username", username);
        jo.addProperty("token", token);

        /*if(action != null  && action.equals("getRequest")) {
            int id = Integer.valueOf(request.getParameter("id"));
            jo.addProperty("id", id);
            try {
                responseHttp = Http.post(Utils.SERVER + "getRequest", jo.toString());
            } catch (IOException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Não foi possível conectar ao servidor.");
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
                return;
            }
            String body = responseHttp.body().string();

            String responseBody = responseHttp.body().string();
            JsonObject responseJSON = null;
            try{
                Utils.validateJson(gson, responseBody, Arrays.asList("status", "code", "msg", "data"));
                responseJSON = gson.fromJson(responseBody, JsonObject.class);
            } catch (Exception e){
                e.printStackTrace();
                message = "Erro interno do sistema.";
                request.setAttribute("errorMessage", message);
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
                return ;
            }

            JsonObject data;

            if(responseJSON.get("status").getAsString().equals("success")){
                if((data = responseJSON.get("data").getAsJsonObject()) != null){
                    Request req = gson.fromJson(data.toString(), Request.class);
                    request.setAttribute("request", req);
                }
            }
        }*/

        try {
            responseHttp = Http.post(Utils.SERVER + "listRequestsOfPersonalTrainer",jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Não foi possível conectar ao servidor.");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
            return ;
        }

        String responseBody = responseHttp.body().string();
        ResponseJSON responseJSON = null;
        try{
            Utils.validateJson(gson, responseBody, Arrays.asList("status", "code", "msg", "data"));
            responseJSON = gson.fromJson(responseBody, ResponseJSON.class);
        } catch (Exception e){
            e.printStackTrace();
            message = "Erro interno do sistema.";
            request.setAttribute("errorMessage", message);
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
            return ;
        }

        System.err.println("CHEGUEI AQUI!");

        if(responseJSON.status.equals("success")){
            System.err.println("ENTREI AQUI 2");
            Request[] requests = gson.fromJson(responseJSON.data.toString(), Request[].class);
            List<Request> tmps = Arrays.asList(requests);
            request.setAttribute("requests", tmps);
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "MyRequests", null);
        }



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
}
