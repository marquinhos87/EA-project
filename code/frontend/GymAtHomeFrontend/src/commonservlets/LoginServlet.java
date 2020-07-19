package commonservlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import exceptions.JsonKeyInFaultException;
import okhttp3.Response;
import utils.Http;
import utils.Utils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@WebServlet(name = "LoginServlet", urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {

    private HttpSession session = null;
    private final Gson gson = new GsonBuilder().create();
    private String action = null;
    private String username = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        action = request.getParameter("action");
        username = (String) session.getAttribute("username");


        //  test if user is logged
        if(username != null && username.startsWith("c")) {
            session.setAttribute("userType","client");
            Utils.redirect(request, response, "/MyProfileClient");
            return ;
        }
        else if(username != null && username.startsWith("pt")) {
            session.setAttribute("userType","pt");
            Utils.redirect(request, response, "/MyProfilePersonalTrainer");
            return ;
        } else if (action == null){
            request.setAttribute("title","Login");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
            return ;
        }

        action = action.toLowerCase();

        if(action.equals("login")){
            String usernameParam = request.getParameter("username");

            if(usernameParam != null && usernameParam.startsWith("c")) { //   typeOfUser = 0 <=> Client
                login(request,response,"loginClient");
            }
            else if(usernameParam != null && usernameParam.startsWith("pt")) { //     typeOfUser = 1 <=> PersonalTrainer
                login(request,response,"loginPersonalTrainer");
            }
            else { //   error
                request.setAttribute("title","Login");
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
            }
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response, String target) throws ServletException, IOException {

        String username = request.getParameter("username"), message = "", password = request.getParameter("password");
        JsonObject jo = new JsonObject();
        jo.addProperty("username",username);
        try {
            jo.addProperty("password",Utils.hashPassword(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
            request.setAttribute("title","Login");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
            return;
        }

        Response responseHttp;

        try {
            responseHttp = Http.post(Utils.SERVER + target,jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", Utils.CONNECTION_LOST_MSG);
            request.setAttribute("title","Login");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
            return ;
        }

        String responseBody = responseHttp.body().string();
        JsonObject responseJSON = null;
        try{
            Utils.validateJson(gson, responseBody, Arrays.asList("status", "code", "msg", "data"));
            responseJSON = gson.fromJson(responseBody, JsonObject.class);
        } catch (Exception e){
            e.printStackTrace();
            request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
            request.setAttribute("title","Login");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
            return ;
        }

        if(responseJSON.get("status").getAsString().equals("success")) {
            JsonObject data = responseJSON.get("data").getAsJsonObject();
            try {
                Utils.validateJson(gson,data.toString(), Arrays.asList("oldToken", "newToken"));
            } catch (JsonKeyInFaultException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                request.setAttribute("title","Login");
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
                return ;
            }
            session.setAttribute("username",username);
            session.setAttribute("token",data.get("newToken").getAsString());

            //  redirect to different controllers
            if(username.startsWith("c")) { //  client
                session.setAttribute("userType","client");
                Utils.redirect(request, response, "/MyProfileClient");
            }else {                //  personal trainer
                session.setAttribute("userType","pt");
                Utils.redirect(request, response, "/MyRequestsPT");
            }
        }else{  //  error
            switch (responseJSON.get("code").getAsInt()){
                case 22:
                case 404:
                case 20:
                case 401:
                    message = "Credênciais inválidas.";
                    break;
                default:    //  other errors
                    message = Utils.UNEXPECTED_ERROR_MSG;
                    break;
            }
            request.setAttribute("userType", null);
            request.setAttribute("errorMessage", message);
            request.setAttribute("title","Login");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
