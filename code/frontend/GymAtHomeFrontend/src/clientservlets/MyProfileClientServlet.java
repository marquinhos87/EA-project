package clientservlets;

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

@WebServlet(name = "MyProfileClientServlet", urlPatterns = "/MyProfileClient")
public class MyProfileClientServlet extends HttpServlet {

    private HttpSession session = null;
    private final Gson gson = new GsonBuilder().create();
    private String action = null;
    private String username = null;
    private String token = null;

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        action = request.getParameter("action");
        username = (String) request.getSession().getAttribute("username");
        token = (String) request.getSession().getAttribute("token");

        System.err.println(action);
        System.err.println(username);
        System.err.println(token);

        if(username == null || token == null){
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
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
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
        }
        else if(action.equals("editprofile")) {
            editProfile(request,response);
        }
    }

    public void editProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(username == null || token == null) {
            request.getSession().setAttribute("username",null);
            request.getSession().setAttribute("token",null);
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
                jo.addProperty("height", request.getParameter("height"));
                jo.addProperty("weight", request.getParameter("weight"));

                if(newPassword!=null)
                    jo.addProperty("password",newPassword);

                String tmp;
                if (!(tmp = request.getParameter("waist")).equals(""))
                    jo.addProperty("waist", Integer.parseInt(tmp));

                if (!(tmp = request.getParameter("chest")).equals(""))
                    jo.addProperty("chest", Integer.parseInt(tmp));

                if (!(tmp = request.getParameter("twin")).equals(""))
                    jo.addProperty("twin", Integer.parseInt(tmp));

                if (!(tmp = request.getParameter("quadricep")).equals(""))
                    jo.addProperty("quadricep", Integer.parseInt(tmp));

                if (!(tmp = request.getParameter("tricep")).equals(""))
                    jo.addProperty("tricep", Integer.parseInt(tmp));

                if (!(tmp = request.getParameter("wrist")).equals(""))
                    jo.addProperty("wrist", Integer.parseInt(tmp));

                Response responseHttp;

                try {
                    responseHttp = Http.post(Utils.SERVER + "editClientProfile", jo.toString());
                }
                catch (IOException e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", "Não foi possível conectar ao servidor.");
                    Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
                    return ;
                }

                String responseBody = responseHttp.body().string();
                ResponseJSON responseObject = gson.fromJson(responseBody, ResponseJSON.class);
                responseHttp.close();

                if (responseObject.status.equals("success")) {
                    request.getSession().setAttribute("successMessage","O seu perfil foi editado com sucesso!");
                    Utils.redirect(request, response, "/MyProfileClient");
                } else {
                    // TODO improve by checking the error (if it's a invalid token we have to send the client to login page)
                    request.setAttribute("errorMessage", "Erro interno.");
                    Utils.forward(request, response, "/WEB-INF/Template.jsp", "MyProfileClient", null);
                }
            }
            else {
                request.setAttribute("errorMessage","Passwords não coincidem.");
                Utils.forward(request,response,"/WEB-INF/Template.jsp","MyProfileClient",null);
            }
        }
    }

    public void viewProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject jo = new JsonObject();
        jo.addProperty("username",username);
        jo.addProperty("token",token);

        Response responseHttp;
        try {
            responseHttp = Http.post(Utils.SERVER + "getClientProfileByClient",jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Não foi possível conectar ao servidor.");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
            return ;
        }

        String responseBody = responseHttp.body().string();
        ResponseJSON responseObject = gson.fromJson(responseBody,ResponseJSON.class);
        responseHttp.close();

        System.err.println(responseBody);

        if(responseObject.status.equals("success")) {
            JsonObject responseJson = responseObject.data.getAsJsonObject();
            request.setAttribute("username",username);
            request.setAttribute("name",responseJson.get("name").getAsString());
            request.setAttribute("email",responseJson.get("email").getAsString());
            request.setAttribute("birthday",responseJson.get("birthday").getAsString());
            request.setAttribute("genre",responseJson.get("sex").getAsString());
            request.setAttribute("height",responseJson.get("height").getAsInt());
            request.setAttribute("weight",responseJson.get("weight").getAsFloat());
            request.setAttribute("bmi",responseJson.get("BMI").getAsFloat());

            int tmp;
            if(responseJson.has("waist") && (tmp = responseJson.get("waist").getAsInt())!=0)
                request.setAttribute("waist",tmp);
            if(responseJson.has("chest") && (tmp = responseJson.get("chest").getAsInt())!=0)
                request.setAttribute("chest",tmp);
            if(responseJson.has("twin") && (tmp = responseJson.get("twin").getAsInt())!=0)
                request.setAttribute("twin",tmp);
            if(responseJson.has("quadriceo") && (tmp = responseJson.get("quadricep").getAsInt())!=0)
                request.setAttribute("quadricep",tmp);
            if(responseJson.has("tricep") && (tmp = responseJson.get("tricep").getAsInt())!=0)
                request.setAttribute("tricep",tmp);
            if(responseJson.has("wrist") && (tmp = responseJson.get("wrist").getAsInt())!=0)
                request.setAttribute("wrist",tmp);
        }
        else request.setAttribute("errorMessage", "Não é possível consultar o perfil neste momento, volte mais tarde.");

        String msg;
        if((msg = (String) request.getSession().getAttribute("successMessage"))!=null){
            request.setAttribute("successMessage",msg);
            request.getSession().setAttribute("successMessage",null);
        }

        Utils.forward(request, response, "/WEB-INF/Template.jsp", "MyProfileClient", null);
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
