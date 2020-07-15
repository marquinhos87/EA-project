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

@WebServlet(name = "ClientProfileServlet", urlPatterns = "/api/v1/GymAtHomeFrontend/ClientProfile")
public class ClientProfileServlet extends HttpServlet {

    /*private final Gson gson = new GsonBuilder().create();

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        String token = (String) request.getSession().getAttribute("token");
        if(username == null || token == null) {
            request.getSession().setAttribute("username",null);
            request.getSession().setAttribute("token",null);
            Utils.redirect(request,response,"/WEB-INF/Template.jsp","Login", null);
        }
        else {
            String clientUsername = (String) request.getAttribute("clientUsername");
            JsonObject jo = new JsonObject();
            jo.addProperty("username",username);
            jo.addProperty("token",token);
            jo.addProperty("clientUsername",clientUsername);

            Response responseHttp = Http.post(Utils.SERVER + "getClientProfileByPersonalTrainer",jo.toString());

            String body = responseHttp.body().string();
            ResponseJSON responseJSON = gson.fromJson(body,ResponseJSON.class);

            if(responseJSON.status.equals("success")) {
                jo = responseJSON.data.getAsJsonObject();

                request.setAttribute("name",jo.get("name").getAsString());
                request.setAttribute("age",jo.get("age").getAsString());
                request.setAttribute("email",jo.get("email").getAsString());
                request.setAttribute("genre",jo.get("sex").getAsString());
                request.setAttribute("height",jo.get("height").getAsString());
                request.setAttribute("weight",jo.get("weight").getAsString());
                request.setAttribute("imc",jo.get("bci").getAsString());

                String tmp = jo.has("waist") ? jo.get("waist").getAsString() : "0";
                request.setAttribute("waist",tmp);

                tmp = jo.has("wrist") ? jo.get("wrist").getAsString() : "0";
                request.setAttribute("wrist",tmp);

                tmp = jo.has("twin") ? jo.get("twin").getAsString() : "0";
                request.setAttribute("twin",tmp);

                tmp = jo.has("quadricep") ? jo.get("quadricep").getAsString() : "0";
                request.setAttribute("quadricep",tmp);

                tmp = jo.has("tricep") ? jo.get("tricep").getAsString() : "0";
                request.setAttribute("tricep",tmp);

                tmp = jo.has("chest") ? jo.get("chest").getAsString() : "0";
                request.setAttribute("chest",tmp);

                Utils.redirect(request,response,"/WEB-INF/Template.jsp","ClientProfile",null);
            }
            else {
                request.setAttribute("errorMessage",responseJSON.msg);
                Utils.redirect(request,response,"/WEB-INF/Template.jsp",null,null);
            }
        }
    }*/

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
        //processRequest(request,response);
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
        //processRequest(request,response);
    }
}
