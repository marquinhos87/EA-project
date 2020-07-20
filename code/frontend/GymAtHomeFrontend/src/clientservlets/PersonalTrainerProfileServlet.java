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

@WebServlet(name = "PersonalTrainerProfileServlet", urlPatterns = "/PersonalTrainerProfile")
public class PersonalTrainerProfileServlet extends HttpServlet {

    private final Gson gson = new GsonBuilder().create();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        String token = (String) request.getSession().getAttribute("token");
        if(username == null || token == null) {
            request.getSession().setAttribute("username",null);
            request.getSession().setAttribute("token",null);
            request.getSession().setAttribute("userType",null);
            request.setAttribute("title","Login");
            Utils.forward(request,response,"/WEB-INF/Template.jsp","Login",null);
        }
        else {
            String personalTrainerUsername = request.getParameter("personalTrainerUsername");

            JsonObject jo = new JsonObject();
            jo.addProperty("username",username);
            jo.addProperty("token",token);
            jo.addProperty("personalTrainerUsername",personalTrainerUsername);

            Response responseHttp;
            try {
                responseHttp = Http.post(Utils.SERVER + "getPersonalTrainerProfileByClient", jo.toString());
            }
            catch (IOException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", Utils.CONNECTION_LOST_MSG);
                request.getSession().setAttribute("username", null);
                request.getSession().setAttribute("token", null);
                request.getSession().setAttribute("userType", null);
                request.setAttribute("title","Login");
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
                return ;
            }

            String body = responseHttp.body().string();
            ResponseJSON responseJSON = gson.fromJson(body,ResponseJSON.class);

            responseHttp.close();

            if(responseJSON.status.equals("success")) {
                jo = responseJSON.data.getAsJsonObject();

                request.setAttribute("personalTrainerUsername",jo.get("username").getAsString());
                request.setAttribute("personalTrainerName",jo.get("name").getAsString());
                request.setAttribute("personalTrainerAge",jo.get("age").getAsInt());
                request.setAttribute("personalTrainerSkill",jo.get("skill").getAsString());
                request.setAttribute("personalTrainerPrice",jo.get("price").getAsString());
                request.setAttribute("personalTrainerGenre",jo.get("sex").getAsString());
                request.setAttribute("personalTrainerNClients",jo.get("nClients").getAsInt());
                request.setAttribute("personalTrainerNPlans",jo.get("nPlans").getAsInt());
                request.setAttribute("personalTrainerNClassifications",jo.get("nClassifications").getAsInt());
                request.setAttribute("personalTrainerClassification",jo.get("classification").getAsFloat());

                request.setAttribute("title","Perfil PT - " + jo.get("name").getAsString());
                Utils.forward(request,response,"/WEB-INF/Template.jsp","PersonalTrainerProfile",null);
            }
            else {
                request.setAttribute("errorMessage",Utils.UNEXPECTED_ERROR_MSG);
                request.getSession().setAttribute("username", null);
                request.getSession().setAttribute("token", null);
                request.getSession().setAttribute("userType", null);
                request.setAttribute("title","Login");
                Utils.forward(request,response,"/WEB-INF/Template.jsp","Login",null);
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
