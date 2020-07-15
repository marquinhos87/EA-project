package commonservlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import exceptions.JsonKeyInFaultException;
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
            Utils.redirect(request, response, "/MyProfileClient", null, null);
            return ;
        }
        else if(username != null && username.startsWith("pt")) {
            Utils.redirect(request, response, "/MyProfilePersonalTrainer", null, null);
            return ;
        } else if (action == null){
            Utils.redirect(request, response, "/WEB-INF/Template.jsp", "Login", null);
            return ;
        }

        action = action.toLowerCase();

        if(action.equals("login")){
            String usernameParam = request.getParameter("username"), passwordParam = request.getParameter("password");

            if(usernameParam != null && usernameParam.startsWith("c")) { //   typeOfUser = 0 <=> Client
                login(request,response,"loginClient");
            }
            else if(usernameParam != null && usernameParam.startsWith("pt")) { //     typeOfUser = 1 <=> PersonalTrainer
                login(request,response,"loginPersonalTrainer");
            }
            else { //   error
                Utils.redirect(request, response, "/WEB-INF/Template.jsp", "Login", null);
                return;
            }
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response, String target) throws ServletException, IOException {

        String username = request.getParameter("username"), message = "", password = request.getParameter("password");
        JsonObject jo = new JsonObject();
        jo.addProperty("username",username);
        jo.addProperty("password",password);

        Response responseHttp = null;

        try {
            responseHttp = Http.post(Utils.SERVER + target,jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Não foi possível conectar ao servidor.");
            Utils.redirect(request, response, "/WEB-INF/Template.jsp", "Login", null);
            return ;
        }

        String responseBody = responseHttp.body().string();
        JsonObject responseJSON = null;
        try{
            Utils.validateJson(gson, responseBody, Arrays.asList("status", "code", "msg", "data"));
            responseJSON = gson.fromJson(responseBody, JsonObject.class);
        } catch (Exception e){
            e.printStackTrace();
            message = "Erro interno do sistema.";
            request.setAttribute("errorMessage", message);
            Utils.redirect(request, response, "/WEB-INF/Template.jsp", "Login", null);
            return ;
        }

        if(responseJSON.get("status").getAsString().equals("success")) {
            JsonObject data = responseJSON.get("data").getAsJsonObject();
            try {
                Utils.validateJson(gson,data.toString(), Arrays.asList("oldToken", "newToken"));
            } catch (JsonKeyInFaultException e) {
                e.printStackTrace();
                message = "Erro interno do sistema.";
                request.setAttribute("errorMessage", message);
                Utils.redirect(request, response, "/WEB-INF/Template.jsp", "Login", null);
                return ;
            }
            session.setAttribute("username",username);
            session.setAttribute("token",data.get("newToken").getAsString());
            if(username.startsWith("c"))
                session.setAttribute("userType", "client");
            else
                session.setAttribute("userType", "pt");
            //  redirect to different controllers
            if(username.startsWith("c")) { //  client
                Utils.redirect(request, response, "/MyProfileClient", null, null);
                return ;
            }else {                //  personal trainer
                Utils.redirect(request, response, "/MyProfilePersonalTrainer", null, null);
                return;
            }
        }else{  //  error
            switch (responseJSON.get("code").getAsInt()){
                case 22:
                case 404:
                case 20:
                    message = "Credênciais inválidas.";
                    break;
                default:    //  other errors
                    message = "Erro interno do sistema.";
                    break;
            }
            request.setAttribute("errorMessage", message);
            Utils.redirect(request, response, "/WEB-INF/Template.jsp", "Login", null);
            return ;
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
