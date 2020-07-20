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

@WebServlet(name = "MyProfilePersonalTrainerServlet", urlPatterns = "/MyProfilePersonalTrainer")
public class MyProfilePersonalTrainerServlet extends HttpServlet {

    private HttpSession session = null;
    private final Gson gson = new GsonBuilder().create();
    private String action = null;
    private String username = null;
    private String token = null;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        action = request.getParameter("action");
        username = (String) request.getSession().getAttribute("username");
        token = (String) request.getSession().getAttribute("token");

        System.err.println(action);
        System.err.println(username);
        System.err.println(token);

        if(username == null || token == null){
            request.setAttribute("title","Login");
            session.setAttribute("username", null);
            session.setAttribute("token", null);
            session.setAttribute("userType", null);
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
            return;
        }

        if(action == null){
            viewProfile(request, response);
            return;
        }

        action = action.toLowerCase();

        if(action.equals("editprofile")) {
            editProfile(request,response);
        }
    }

    public void editProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(username == null || token == null) {
            session.setAttribute("username",null);
            session.setAttribute("token",null);
            session.setAttribute("userType",null);
            request.setAttribute("title","Login");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
        }
        else {
            String newPassword = request.getParameter("newpassword");
            String confirmationPassword = request.getParameter("cpassword");

            if((newPassword==null && confirmationPassword==null) || newPassword.equals(confirmationPassword)) {
                JsonObject jo = new JsonObject();
                jo.addProperty("username", username);
                jo.addProperty("token", token);
                jo.addProperty("name", request.getParameter("name"));
                jo.addProperty("birthday", request.getParameter("birthday"));
                jo.addProperty("sex", request.getParameter("genre"));
                jo.addProperty("email", request.getParameter("email"));
                jo.addProperty("skill", request.getParameter("skill"));
                jo.addProperty("price", request.getParameter("price"));

                // TODO improve password by type the older's one and new's confirmation
                if (newPassword != null)
                    jo.addProperty("password", newPassword);

                Response responseHttp;

                try {
                    responseHttp = Http.post(Utils.SERVER + "editPersonalTrainerProfile", jo.toString());
                }
                catch (IOException e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", Utils.CONNECTION_LOST_MSG);
                    session.setAttribute("username", null);
                    session.setAttribute("token", null);
                    session.setAttribute("userType", null);
                    request.setAttribute("title","Login");
                    Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
                    return ;
                }

                String responseBody = responseHttp.body().string();
                ResponseJSON responseObject = gson.fromJson(responseBody, ResponseJSON.class);

                responseHttp.close();

                if (responseObject.status.equals("success")) {
                    request.getSession().setAttribute("successMessage","O seu perfil foi editado com sucesso!");
                    Utils.redirect(request, response, "/MyProfilePersonalTrainer");
                } else {
                    // TODO improve by checking the error (if it's a invalid token we have to send the client to login page)
                    request.setAttribute("errorMessage", responseObject.msg);
                    request.setAttribute("title","Perfil PT - " + username);
                    Utils.forward(request, response, "/WEB-INF/Template.jsp", "MyProfilePersonalTrainer", null);
                }
            }
            else {
                request.setAttribute("errorMessage","Passwords não coincidem.");
                request.setAttribute("title","Perfil PT - " + username);
                Utils.forward(request,response,"/WEB-INF/Template.jsp","MyProfilePersonalTrainer",null);
            }
        }
    }

    public void viewProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jo = new JsonObject();
        jo.addProperty("username",username);
        jo.addProperty("token",token);

        Response responseHttp;
        try {
            responseHttp = Http.post(Utils.SERVER + "getPersonalTrainerProfileByPersonalTrainer",jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", Utils.CONNECTION_LOST_MSG);
            session.setAttribute("username", null);
            session.setAttribute("token", null);
            session.setAttribute("userType", null);
            request.setAttribute("title","Login");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
            return;
        }

        String responseBody = responseHttp.body().string();
        ResponseJSON responseObject = gson.fromJson(responseBody,ResponseJSON.class);

        responseHttp.close();

        System.err.println(responseBody);

        if(responseObject.status.equals("success")) {
            JsonObject responseJson = responseObject.data.getAsJsonObject();
            request.setAttribute("username","@"+username);
            request.setAttribute("name",responseJson.get("name").getAsString());
            request.setAttribute("email",responseJson.get("email").getAsString());
            request.setAttribute("birthday",responseJson.get("birthday").getAsString());
            request.setAttribute("genre",responseJson.get("sex").getAsString());
            request.setAttribute("skill",responseJson.get("skill").getAsString());
            request.setAttribute("price",responseJson.get("price").getAsFloat());
        }
        else {
            request.setAttribute("errorMessage", "Não é possível o consultar o perfil neste momento, volte mais tarde.");
        }

        String msg;
        if((msg = (String) request.getSession().getAttribute("successMessage"))!=null){
            request.setAttribute("successMessage",msg);
            request.getSession().setAttribute("successMessage",null);
        }

        request.setAttribute("title","Perfil PT - " + username);
        Utils.forward(request, response, "/WEB-INF/Template.jsp", "MyProfilePersonalTrainer", null);
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
