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
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "PersonalTrainerRegisterServlet", urlPatterns = "/PersonalTrainerRegister")
public class PersonalTrainerRegisterServlet extends HttpServlet {

    private final Gson gson = new GsonBuilder().create();

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
        String username = (String) request.getSession().getAttribute("username");
        String token = (String) request.getSession().getAttribute("token");
        if(username != null && token != null) {
            if(username.startsWith("u")) {
                request.getSession().setAttribute("userType","client");
                Utils.redirect(request,response,"/MyProfileClient");
            }
            else if(username.startsWith("pt")) {
                request.getSession().setAttribute("userType","pt");
                Utils.redirect(request,response,"/MyProfilePersonalTrainer");
            }
            else {
                request.getSession().setAttribute("username", null);
                request.getSession().setAttribute("token", null);
                request.getSession().setAttribute("userType", null);
                request.setAttribute("title","Login");
                Utils.forward(request,response,"/WEB-INF/Template.jsp","Login",null);
            }
        }
        else {
            String password = request.getParameter("password");
            String confirmationPassword = request.getParameter("cpassword");

            if(password != null && confirmationPassword != null && password.equals(confirmationPassword)) {
                try {
                    JsonObject jo = new JsonObject();
                    jo.addProperty("name", request.getParameter("name"));
                    jo.addProperty("username", "pt" + request.getParameter("username"));
                    jo.addProperty("email", request.getParameter("email"));
                    jo.addProperty("password", Utils.hashPassword(password));
                    jo.addProperty("birthday", request.getParameter("birthday"));
                    jo.addProperty("sex", request.getParameter("genre"));
                    jo.addProperty("skill", request.getParameter("skill"));
                    jo.addProperty("price", Float.parseFloat(request.getParameter("price")));

                    Response responseHttp;

                    try {
                        responseHttp = Http.post(Utils.SERVER + "createPersonalTrainer", jo.toString());
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                        request.setAttribute("errorMessage", Utils.CONNECTION_LOST_MSG);
                        request.getSession().setAttribute("username", null);
                        request.getSession().setAttribute("token", null);
                        request.getSession().setAttribute("userType", null);
                        request.setAttribute("title","Login");
                        Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
                        return;
                    }

                    String body = responseHttp.body().string();
                    System.err.println("RESPONSE="+body);
                    ResponseJSON responseJSON = gson.fromJson(body,ResponseJSON.class);

                    responseHttp.close();

                    if (responseJSON.status.equals("success")) {
                        JsonObject data = responseJSON.data.getAsJsonObject();
                        request.getSession().setAttribute("username", "pt" + request.getParameter("username"));
                        request.getSession().setAttribute("token",data.get("token").getAsString());
                        request.getSession().setAttribute("successMessage","O seu perfil foi criado com sucesso! Aqui pode ver o seu perfil onde pode alterar os seus dados a qualquer momento.");
                        request.getSession().setAttribute("userType", "pt");
                        Utils.redirect(request,response,"/MyProfilePersonalTrainer");
                    }
                    else {
                        request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                        request.setAttribute("title","Registar PT");
                        Utils.forward(request,response,"/WEB-INF/Template.jsp","PersonalTrainerRegister",null);
                    }

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                    request.setAttribute("title","Registar PT");
                    Utils.forward(request,response,"/WEB-INF/Template.jsp","PersonalTrainerRegister",null);
                }
            }
            else {
                request.setAttribute("errorMessage","Passwords não coincidem.");
                request.setAttribute("title","Registar PT");
                Utils.forward(request,response,"/WEB-INF/Template.jsp","PersonalTrainerRegister",null);
            }
        }
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
        String username = (String) request.getSession().getAttribute("username");
        String token = (String) request.getSession().getAttribute("token");
        if(username != null && token != null) {
            if(username.startsWith("u")) {
                request.getSession().setAttribute("userType","client");
                Utils.redirect(request,response,"/MyProfileClient");
            }
            else if(username.startsWith("pt")) {
                request.getSession().setAttribute("userType","pt");
                Utils.redirect(request,response,"/MyProfilePersonalTrainer");
            }
            else {
                request.getSession().setAttribute("username", null);
                request.getSession().setAttribute("token", null);
                request.getSession().setAttribute("userType", null);
                request.setAttribute("title","Login");
                Utils.forward(request,response,"/WEB-INF/Template.jsp","Login",null);
            }
        }
        else {
            request.setAttribute("title","Registar PT");
            Utils.forward(request,response,"/WEB-INF/Template.jsp","PersonalTrainerRegister",null);
        }
    }
}
