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

@WebServlet(name = "PersonalTrainerProfileServlet", urlPatterns = "/api/v1/GymAtHomeFrontend/PersonalTrainerProfile")
public class PersonalTrainerProfileServlet extends HttpServlet {

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
        if(username == null || token == null) {
            request.getSession().setAttribute("username",null);
            request.getSession().setAttribute("token",null);
            request.setAttribute("page","Login");
            getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Template.jsp").forward(request,response);
        }
        else {
            //TODO
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
        if(username == null || token == null) {
            request.getSession().setAttribute("username",null);
            request.getSession().setAttribute("token",null);
            request.getSession().setAttribute("userType",null);
            Utils.forward(request,response,"/WEB-INF/Template.jsp","Login",null);
        }
        else {
            String personalTrainerUsername = request.getParameter("username");

            JsonObject jo = new JsonObject();
            jo.addProperty("username",username);
            jo.addProperty("token",token);
            jo.addProperty("personalTrainerUsername",personalTrainerUsername);

            Response responseHttp;
            try {
                responseHttp = Http.post("http://gymathome:8081/GymAtHome/api/getPersonalTrainerProfileByClient", jo.toString());
            }
            catch (IOException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Não foi possível conectar ao servidor.");
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
                return ;
            }

            String body = responseHttp.body().string();
            ResponseJSON responseJSON = gson.fromJson(body,ResponseJSON.class);

            if(responseJSON.status.equals("success")) {
                jo = responseJSON.data.getAsJsonObject();
            }
            else {
                request.setAttribute("errorMessage", "Não é possível consultar o perfil do personal trainer neste momento, volte mais tarde.");
            }
        }
    }
}
