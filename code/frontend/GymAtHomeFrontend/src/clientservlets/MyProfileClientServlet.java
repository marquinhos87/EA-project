package clientservlets;

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

@WebServlet(name = "MyProfileClientServlet", urlPatterns = "/MyProfileClient")
public class MyProfileClientServlet extends HttpServlet {

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
        if(username == null) {
            request.setAttribute("logged", false);
            getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Template.jsp").forward(request,response);
        }
        else {
            // TODO
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
            request.setAttribute("logged", false);
            getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Template.jsp").forward(request,response);
        }
        else {
            String token = (String) request.getSession().getAttribute("token");
            JsonObject jo = new JsonObject();
            jo.addProperty("username",username);
            jo.addProperty("token",token);

            Response responseHttp = Http.post("http://localhost:8080/GymAtHome/api/getClientProfileByClient",jo.toString());

            String responseBody = responseHttp.body().string();
            ResponseJSON responseObject = gson.fromJson(responseBody,ResponseJSON.class);

            if(responseHttp.code() == HttpServletResponse.SC_OK) {
                // TODO
            }
            else {
                // TODO
            }
        }
    }
}
