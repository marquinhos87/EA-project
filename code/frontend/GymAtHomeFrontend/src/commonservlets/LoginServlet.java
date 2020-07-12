package commonservlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import okhttp3.Response;
import parseJSON.ResponseJSON;
import utils.Http;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {

    private final Gson gson = new GsonBuilder().create();

    private void login(HttpServletRequest request, HttpServletResponse response, String target) throws ServletException, IOException {
        String username = (String) request.getAttribute("username");
        String password = (String) request.getAttribute("password");
        JsonObject jo = new JsonObject();
        jo.addProperty("username",username);
        jo.addProperty("password",password);

        Response responseHttp = Http.post("http://localhost:8080/GymAtHome/" + target,jo.toString());

        String responseBody = responseHttp.body().string();
        ResponseJSON responseObject = gson.fromJson(responseBody,ResponseJSON.class);

        if(responseHttp.code() == HttpServletResponse.SC_OK) {
            JsonObject data = responseObject.data.getAsJsonObject();
            request.getSession().setAttribute("username",username);
            request.getSession().setAttribute("token",data.get("token").getAsString());
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
        String username = (String) request.getAttribute("username");

        if(username.startsWith("u")) {
            login(request,response,"loginClient");
        }
        else if(username.startsWith("pt")) {
            login(request,response,"loginPersonalTrainer");
        }
        else {
            request.setAttribute("page","Login");
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
        if(username == null) {
            request.setAttribute("page","Login");
        }
        else {
            if(username.startsWith("u")) {
                request.setAttribute("page","MyProfileClient");
            }
            else if(username.startsWith("pt")) {
                request.setAttribute("page","MyProfilePersonalTrainer");
            }
            else {
                request.setAttribute("page","Login");
            }
        }
        getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Template.jsp").forward(request, response);
    }
}
