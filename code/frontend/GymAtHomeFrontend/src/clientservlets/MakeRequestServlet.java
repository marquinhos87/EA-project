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

@WebServlet(name = "MakeRequestServlet", urlPatterns = "/api/v1/GymAtHomeFrontend/MakeRequest")
public class MakeRequestServlet extends HttpServlet {

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
            request.getSession().setAttribute("userType",null);
            Utils.forward(request,response,"/WEB-INF/Template.jsp","Login",null);
        }
        else {
            String[] weekDays = request.getParameterValues("weekDay");
            String personalTrainerUsername = request.getParameter("personalTrainerUsername");
            if (weekDays.length != 0) {
                JsonObject jo = new JsonObject();
                jo.addProperty("username", username);
                jo.addProperty("token", token);
                jo.addProperty("personalTrainerUsername", personalTrainerUsername);
                jo.addProperty("numberOfWeeks", request.getParameter("numberOfWeeks"));
                jo.addProperty("objective", request.getParameter("objective"));
                jo.addProperty("workoutPerWeek", Integer.parseInt(request.getParameter("workoutPerWeek")));
                jo.addProperty("level", request.getParameter("level"));

                StringBuilder sb = new StringBuilder();
                for(String weekDay: weekDays)
                    sb.append(weekDay+";");

                int sbtam = sb.length();
                jo.addProperty("weekDays", sb.toString().substring(0,sbtam-1));

                Response responseHttp;

                try {
                    responseHttp = Http.post(Utils.SERVER + "submitRequest",jo.toString());
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

                if(responseObject.status.equals("success")) {
                    Utils.redirect(request,response,"/MyProfileClient");
                }
                else {
                    request.setAttribute("personalTrainerUsername",personalTrainerUsername);
                    request.setAttribute("errorMessage","Erro interno.");
                    Utils.forward(request, response, "/WEB-INF/Template.jsp", "MakeRequest", null);
                }
            }
            else {
                request.setAttribute("personalTrainerUsername",personalTrainerUsername);
                request.setAttribute("warningMessage","Tem de selecionar pelo menos um dia da semana.");
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "MakeRequest", null);
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
        if(username == null || token == null) {
            request.getSession().setAttribute("username",null);
            request.getSession().setAttribute("token",null);
            request.getSession().setAttribute("userType",null);
            Utils.forward(request,response,"/WEB-INF/Template.jsp","Login",null);
        }
        else {
            String personalTrainerUsername = request.getParameter("personalTrainerUsername");
            if(personalTrainerUsername==null)
                Utils.redirect(request,response,"MyProfileClient");
            else {
                request.setAttribute("personalTrainerUsername",personalTrainerUsername);
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "MakeRequest", null);
            }
        }
    }
}
