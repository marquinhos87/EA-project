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
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "ClientRegisterServlet", urlPatterns = "/ClientRegister")
public class ClientRegisterServlet extends HttpServlet {

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
                Utils.redirect(request,response,"MyProfileClient",null,null);
            }
            else if(username.startsWith("pt")) {
                Utils.redirect(request,response,"MyProfilePersonalTrainer",null,null);
            }
            else {
                request.getSession().setAttribute("username", null);
                request.getSession().setAttribute("token", null);
                Utils.redirect(request,response,"/WEB-INF/Template.jsp","Login",null);
            }
        }
        else {
            String password = (String)request.getAttribute("password");
            String confirmationPassword = (String)request.getAttribute("cpassword");

            if(password.equals(confirmationPassword)) {
                try {
                    JsonObject jo = new JsonObject();
                    jo.addProperty("name", (String) request.getAttribute("name"));
                    jo.addProperty("username", (String) request.getAttribute("username"));
                    jo.addProperty("email", (String) request.getAttribute("email"));
                    jo.addProperty("password", Utils.hashPassword(password));
                    jo.addProperty("birthday",(String)request.getAttribute("birthday"));
                    jo.addProperty("sex",(String)request.getAttribute("genre"));
                    jo.addProperty("height",(String)request.getAttribute("height"));
                    jo.addProperty("weight",(String)request.getAttribute("weight"));

                    String tmp = (String) request.getAttribute("waist");
                    if(tmp!= null && !tmp.equals(""))
                        jo.addProperty("waist",Integer.parseInt(tmp));

                    tmp = (String) request.getAttribute("chest");
                    if(tmp!= null && !tmp.equals(""))
                        jo.addProperty("chest",Integer.parseInt(tmp));

                    tmp = (String) request.getAttribute("twin");
                    if(tmp!= null && !tmp.equals(""))
                        jo.addProperty("twin",Integer.parseInt(tmp));

                    tmp = (String) request.getAttribute("quadricep");
                    if(tmp!= null && !tmp.equals(""))
                        jo.addProperty("quadricep",Integer.parseInt(tmp));

                    tmp = (String) request.getAttribute("tricep");
                    if(tmp!= null && !tmp.equals(""))
                        jo.addProperty("tricep",Integer.parseInt(tmp));

                    tmp = (String) request.getAttribute("wrist");
                    if(tmp!= null && !tmp.equals(""))
                        jo.addProperty("wrist",Integer.parseInt(tmp));

                    Response responseHttp = Http.post(Utils.SERVER + "createClient",jo.toString());

                    String body = responseHttp.body().string();
                    ResponseJSON responseJSON = gson.fromJson(body,ResponseJSON.class);

                    if (responseJSON.status.equals("success")) {
                        JsonObject data = responseJSON.data.getAsJsonObject();
                        request.getSession().setAttribute("username",request.getAttribute("username"));
                        request.getSession().setAttribute("token",data.get("token").getAsString());
                        Utils.redirect(request,response,"MyProfileClient",null,null);
                    }
                    else {
                        request.setAttribute("errorMessage", responseJSON.msg);
                        Utils.redirect(request,response,"/WEB-INF/Template.jsp","ClientRegister",null);
                    }

                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", "An internal error occurred.");
                    Utils.redirect(request,response,"/WEB-INF/Template.jsp","ClientRegister",null);
                }
            }
            else {
                request.setAttribute("errorMessage","Passwords não coincidem.");
                Utils.redirect(request,response,"/WEB-INF/Template.jsp","ClientRegister",null);
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
                request.setAttribute("page","MyProfileClient");
                Utils.redirect(request,response,"MyProfileClient",null,null);
            }
            else if(username.startsWith("pt")) {
                request.setAttribute("page","MyProfilePersonalTrainer");
                Utils.redirect(request,response,"MyProfilePersonalTrainer",null,null);
            }
            else {
                request.getSession().setAttribute("username", null);
                request.getSession().setAttribute("token", null);
                Utils.redirect(request,response,"/WEB-INF/Template.jsp","Login",null);
            }
        }
        else {
            request.getSession().setAttribute("username", null);
            request.getSession().setAttribute("token", null);
            Utils.redirect(request,response,"/WEB-INF/Template.jsp","ClientRegister",null);
        }
    }
}
