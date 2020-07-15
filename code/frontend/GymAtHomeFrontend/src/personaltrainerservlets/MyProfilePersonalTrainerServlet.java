package personaltrainerservlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
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

@WebServlet(name = "MyProfilePersonalTrainerServlet", urlPatterns = "MyProfilePersonalTrainer")
public class MyProfilePersonalTrainerServlet extends HttpServlet {

    private HttpSession session = null;
    private final Gson gson = new GsonBuilder().create();
    private String action = null;
    private String username = null;
    private String token = null;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        action = (String) request.getAttribute("action");
        username = (String) request.getSession().getAttribute("username");
        token = (String) request.getSession().getAttribute("token");

        System.err.println(session);
        System.err.println(action);
        System.err.println(username);
        System.err.println(token);

        if(username == null || token == null){
            Utils.redirect(request, response, "/WEB-INF/Template.jsp", "Login", null);
            return;
        }

        if(action == null){
            viewProfile(request, response);
            return;
        }

        action = action.toLowerCase();

        //  log out
        if(action.equals("logout")){
            session.setAttribute("username", null);
            session.setAttribute("token", null);
            Utils.redirect(request, response, "/WEB-INF/Template.jsp", "Login", null);
        }
        else if(action.equals("editprofile")) {
            editProfile(request,response);
        }
    }

    public void editProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        String token = (String) request.getSession().getAttribute("token");
        if(username == null || token == null) {
            request.getSession().setAttribute("username",null);
            request.getSession().setAttribute("token",null);
            Utils.redirect(request, response, "/WEB-INF/Template.jsp", "Login", null);
        }
        else {
            JsonObject jo = new JsonObject();
            jo.addProperty("username",username);
            jo.addProperty("token",token);
            jo.addProperty("name",(String)request.getAttribute("name"));
            jo.addProperty("birthday",(String)request.getAttribute("birthday"));
            jo.addProperty("sex",(String)request.getAttribute("genre"));
            jo.addProperty("email",(String)request.getAttribute("email"));
            jo.addProperty("skill",(String)request.getAttribute("skill"));

            // TODO improve password by type the older's one and new's confirmation
            String password = (String) request.getAttribute("password");
            if (password != null)
                jo.addProperty("password",password);

            Response responseHttp = Http.post(Utils.SERVER + "editPersonalTrainerProfile",jo.toString());

            String responseBody = responseHttp.body().string();
            ResponseJSON responseObject = gson.fromJson(responseBody,ResponseJSON.class);

            if(responseObject.status.equals("success")) {
                Utils.redirect(request,response,"MyProfilePersonalTrainer",null,null);
            }
            else {
                // TODO improve by checking the error (if it's a invalid token we have to send the client to login page)
                request.setAttribute("errorMessage",responseObject.msg);
                Utils.redirect(request,response,"/WEB-INF/Template.jsp","MyProfilePersonalTrainer",null);
            }
        }
    }

    public void viewProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jo = new JsonObject();
        jo.addProperty("username",username);
        jo.addProperty("token",token);

        Response responseHttp = null;
        try {
            responseHttp = Http.post(Utils.SERVER + "getPersonalTrainerProfileByPersonalTrainer",jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Não foi possível conectar ao servidor.");
            Utils.redirect(request, response, "/WEB-INF/Template.jsp", "Login", null);
            return;
        }

        String responseBody = responseHttp.body().string();
        ResponseJSON responseObject = gson.fromJson(responseBody,ResponseJSON.class);

        System.err.println(responseBody);

        if(responseObject.status.equals("success")) {
            JsonObject responseJson = responseObject.data.getAsJsonObject();
            request.setAttribute("username",responseJson.get("username").getAsString());
            request.setAttribute("name",responseJson.get("name").getAsString());
            request.setAttribute("email",responseJson.get("email").getAsString());
            request.setAttribute("birthday",responseJson.get("birthday").getAsString());
            request.setAttribute("genre",responseJson.get("sex").getAsString());
            request.setAttribute("skill",responseJson.get("skill").getAsString());
        }
        else request.setAttribute("errorMessage", "Não é possível o consultar o perfil neste momento, volte mais tarde.");
        Utils.redirect(request, response, "/WEB-INF/Template.jsp", "MyProfilePersonalTrainer", null);
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
        processRequest(request,response);
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
        processRequest(request,response);
    }
}
