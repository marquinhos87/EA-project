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
import java.util.stream.Collectors;

@WebServlet(name = "MyRequestsServletPT", urlPatterns = "/MyRequestsPT")
public class MyRequestsServletPT extends HttpServlet {

    private HttpSession session = null;
    private final Gson gson = new GsonBuilder().create();
    private String action = null;
    private String username = null;
    private String token = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();


        // TODO remover isto daqui após testar -------------------------------------
        session.setAttribute("username", "pt0");
        session.setAttribute("token", "pt0Uzh5V5d6ZcMNrUBCwloAkKLUjLhcF");
        // -------------------------------------------------------------------------


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

        System.err.println(action);

        if(action != null && (action.equals("accepted") || action.equals("reject"))){
            JsonObject replyToRequest = new JsonObject();

            int requestId = Integer.valueOf(request.getParameter("requestId"));

            System.err.println(requestId);

            if(action.equals("accepted")){
                replyToRequest.addProperty("accepted", true);
                message = "O pedido foi aceite com sucesso.";
            }
            else {
                replyToRequest.addProperty("accepted", false);
                message = "O pedido foi rejeitado com sucesso.";
            }

            replyToRequest.addProperty("username", username);
            replyToRequest.addProperty("token", token);
            replyToRequest.addProperty("requestId", requestId);
            replyToRequest.addProperty("clientUsername", request.getParameter("clientUsername"));

            System.err.println(replyToRequest.toString());

            try {
                responseHttp = Http.post(Utils.SERVER + "replyToRequest",replyToRequest.toString());
            } catch (IOException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "MyRequests", null);
                return ;
            }
            responseBody = responseHttp.body().string();

            try{
                Utils.validateJson(gson, responseBody, Arrays.asList("status", "code", "msg", "data"));
                responseJSON = gson.fromJson(responseBody, ResponseJSON.class);
            } catch (Exception e){
                e.printStackTrace();
                request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "MyRequests", null);
                return ;
            }

            System.out.println(responseJSON);

            if(responseJSON.status.equals("success")){
                // request.setAttribute("errorMessage", message);
                Map<Integer, Request> requests = (Map<Integer, Request>) session.getAttribute("requests");
                Request r = requests.get(requestId);
                session.setAttribute("requests", null);
                session.setAttribute("request", r);
                Utils.redirect(request, response, "/CreateWeek");
                return ;
            } else{
                switch (responseJSON.code){
                    default:
                        request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                        Utils.forward(request, response, "/WEB-INF/Template.jsp", "MyRequests", null);
                        break;
                }
            }
        }

        JsonObject jo = new JsonObject();

        jo.addProperty("username", username);
        jo.addProperty("token", token);

        try {
            responseHttp = Http.post(Utils.SERVER + "listRequestsOfPersonalTrainer",jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "MyRequests", null);
            return ;
        }

        responseBody = responseHttp.body().string();
        try{
            Utils.validateJson(gson, responseBody, Arrays.asList("status", "code", "msg", "data"));
            responseJSON = gson.fromJson(responseBody, ResponseJSON.class);
        } catch (Exception e){
            e.printStackTrace();
            request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "MyRequests", null);
            return ;
        }

        if(responseJSON.status.equals("success")){
            Request[] requests = gson.fromJson(responseJSON.data.toString(), Request[].class);
            List<Request> tmps = Arrays.asList(requests);
            request.setAttribute("requests", tmps);
            Map<Integer, Request> map = tmps.stream().collect(Collectors.toMap(Request::getID, r -> r));
            session.setAttribute("requests",map); // CAUTION - it's suposed to save requests on session - DO NOT CHAMGE
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "MyRequests", null);
        }else{
            switch (responseJSON.code){
                default:
                    request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                    Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
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
