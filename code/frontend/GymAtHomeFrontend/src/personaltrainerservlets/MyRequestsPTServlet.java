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

@WebServlet(name = "MyRequestsPTServlet", urlPatterns = "/MyRequestsPT")
public class MyRequestsPTServlet extends HttpServlet {

    private HttpSession session = null;
    private final Gson gson = new GsonBuilder().create();
    private String action = null;
    private String username = null;
    private String token = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();

        // TODO remover isto daqui após testar -------------------------------------
        /*session.setAttribute("username", "pt4");
        session.setAttribute("token", "pt4u3oYseyiXrDkp8xpFKTNaEVH2Ydgq");
        session.setAttribute("userType", "pt");*/
        // -------------------------------------------------------------------------

        action = request.getParameter("action");
        username = (String) session.getAttribute("username");
        token = (String) session.getAttribute("token");

        if(username == null || token == null){
            Utils.redirect(request, response, "/Login");
            return ;
        }

        Response responseHttp;
        String responseBody;
        ResponseJSON responseJSON;

        if (action != null && action.equals("reject")) {

            JsonObject replyToRequest = new JsonObject();

            replyToRequest.addProperty("username", username);
            replyToRequest.addProperty("token", token);
            replyToRequest.addProperty("requestId", Integer.parseInt(request.getParameter("requestId")));
            replyToRequest.addProperty("clientUsername", request.getParameter("clientUsername"));
            replyToRequest.addProperty("state", -1);
            replyToRequest.addProperty("accepted", false);

            try {
                responseHttp = Http.post(Utils.SERVER + "replyToRequest",replyToRequest.toString());
            } catch (IOException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "-", null);
                return ;
            }
            responseBody = responseHttp.body().string();
            responseHttp.close();
            responseJSON = gson.fromJson(responseBody, ResponseJSON.class);

            if(!responseJSON.status.equals("success")){
                switch (responseJSON.code){
                    default:
                        request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                        Utils.forward(request, response, "/WEB-INF/Template.jsp", "-", null);
                        return;
                }
            }

            request.setAttribute("successMessage", "O pedido foi rejeitado com sucesso.");
        }

        else if (action != null && action.equals("accepted")) {
            int requestId = Integer.parseInt(request.getParameter("requestId"));
            Map<Integer, Request> requests = (Map<Integer, Request>) session.getAttribute("requests");
            Request r = requests.get(requestId);
            session.setAttribute("requests", null);
            session.setAttribute("request", r);
            Utils.redirect(request, response, "/CreateWeek");
            return ;
        }

        JsonObject jo = new JsonObject();

        jo.addProperty("username", username);
        jo.addProperty("token", token);

        try {
            responseHttp = Http.post(Utils.SERVER + "listRequestsOfPersonalTrainer",jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "-", null);
            return ;
        }
        responseBody = responseHttp.body().string();
        responseHttp.close();

        try{
            Utils.validateJson(gson, responseBody, Arrays.asList("status", "code", "msg", "data"));
            responseJSON = gson.fromJson(responseBody, ResponseJSON.class);
        } catch (Exception e){
            e.printStackTrace();
            request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "-", null);
            return ;
        }

        if(responseJSON.status.equals("success")){
            Request[] requests = gson.fromJson(responseJSON.data.toString(), Request[].class);
            List<Request> tmps = Arrays.asList(requests);
            request.setAttribute("requests", tmps);
            Map<Integer, Request> map = tmps.stream().collect(Collectors.toMap(Request::getID, r -> r));
            session.setAttribute("requests",map); // CAUTION - it's suposed to save requests on session - DO NOT CHAMGE
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "MyRequests", null);
        } else{
            switch (responseJSON.code){
                default:
                    request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                    Utils.forward(request, response, "/WEB-INF/Template.jsp", "-", null);
                    return;
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
