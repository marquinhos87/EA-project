package clientservlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
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
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "MyRequestsClientServlet", urlPatterns = "/RequestsClient")
public class MyRequestsClientServlet extends HttpServlet {

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

        if(username == null || token == null){
            Utils.redirect(request, response, "/Login");
            return ;
        }

        String message = "";

        Response responseHttp = null;
        String responseBody;
        ResponseJSON responseJSON;

        JsonObject jo = new JsonObject();

        jo.addProperty("username", username);
        jo.addProperty("token", token);


        try {
            responseHttp = Http.post(Utils.SERVER + "listRequestsOfClient",jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "RequestsClient", null);
            return ;
        }

        responseBody = responseHttp.body().string();
        try{
            Utils.validateJson(gson, responseBody, Arrays.asList("status", "code", "msg", "data"));
            responseJSON = gson.fromJson(responseBody, ResponseJSON.class);
        } catch (Exception e){
            e.printStackTrace();
            request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "RequestsClient", null);
            return ;
        }

        if(responseJSON.status.equals("success")){
            Request[] requests = gson.fromJson(responseJSON.data.toString(), Request[].class);
            List<Request> tmps = Arrays.asList(requests);

            System.err.println(tmps);
            request.setAttribute("requests", tmps);
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "RequestsClient", null);
        }else{
            switch (responseJSON.code){
                default:
                    request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                    Utils.forward(request, response, "/WEB-INF/Template.jsp", "RequestsClient", null);
                    break;
            }
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
