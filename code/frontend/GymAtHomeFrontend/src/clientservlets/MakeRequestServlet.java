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

@WebServlet(name = "MakeRequestServlet", urlPatterns = "/MakeRequest")
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
            request.setAttribute("title","Login");
            Utils.forward(request,response,"/WEB-INF/Template.jsp","Login",null);
        }
        else {
            String[] weekDays = request.getParameterValues("weekDay[]");
            String personalTrainerUsername = request.getParameter("personalTrainerUsername");
            if (weekDays != null && weekDays.length != 0) {
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

                jo.addProperty("weekDays",sb.deleteCharAt(sb.length()-1).toString());

                Response responseHttp;

                System.err.println(jo.toString());

                try {
                    responseHttp = Http.post(Utils.SERVER + "submitRequest",jo.toString());
                }
                catch (IOException e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", Utils.CONNECTION_LOST_MSG);
                    request.getSession().setAttribute("username",null);
                    request.getSession().setAttribute("token",null);
                    request.getSession().setAttribute("userType",null);
                    request.setAttribute("title","Login");
                    Utils.forward(request, response, "/WEB-INF/Template.jsp", "Login", null);
                    return ;
                }

                String responseBody = responseHttp.body().string();
                ResponseJSON responseObject = gson.fromJson(responseBody, ResponseJSON.class);

                responseHttp.close();

                System.err.println(responseObject);

                if(responseObject.status.equals("success")) {
                    Utils.redirect(request,response,"/RequestsClient");
                }
                else {
                    request.setAttribute("personalTrainerUsername",personalTrainerUsername);
                    request.setAttribute("errorMessage",Utils.UNEXPECTED_ERROR_MSG);
                    request.setAttribute("title","Formulário");
                    Utils.forward(request, response, "/WEB-INF/Template.jsp", "MakeRequest", null);
                }
            }
            else {
                request.setAttribute("personalTrainerUsername",personalTrainerUsername);
                request.setAttribute("warningMessage","Neste momento não foi possível carregar os dados biométricos. Tente recarregar a página.");
                request.setAttribute("title","Formulário");
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
            request.setAttribute("title","Login");
            Utils.forward(request,response,"/WEB-INF/Template.jsp","Login",null);
        }
        else {
            String personalTrainerUsername = request.getParameter("PTUsername");
            System.err.println(personalTrainerUsername);
            if(personalTrainerUsername==null)
                Utils.redirect(request,response,"/MyProfileClient");
            else {
                request.setAttribute("personalTrainerUsername",personalTrainerUsername);
                request.setAttribute("title", "Formulário");
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "MakeRequest", null);
            }
        }
    }
}
