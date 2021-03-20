package clientservlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import core.PersonalTrainer;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@WebServlet(name = "SearchPersonalTrainersServlet", urlPatterns = "/SearchPersonalTrainer")
public class SearchPersonalTrainersServlet extends HttpServlet {

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
            JsonObject jo = new JsonObject();

            jo.addProperty("username",username);
            jo.addProperty("token",token);

            String tmp;
            if((tmp = request.getParameter("minage")) != null)
                jo.addProperty("minAge",Integer.parseInt(tmp));

            if((tmp = request.getParameter("maxage")) != null)
                jo.addProperty("maxAge",Integer.parseInt(tmp));

            if((tmp = request.getParameter("minprice")) != null)
                jo.addProperty("minPrice",Float.parseFloat(tmp));

            if((tmp = request.getParameter("maxprice")) != null)
                jo.addProperty("maxPrice",Float.parseFloat(tmp));

            if((tmp = request.getParameter("classification")) != null)
                jo.addProperty("classification",Integer.parseInt(tmp));

            if((tmp = request.getParameter("skill")) != null && !tmp.equals("q"))
                jo.addProperty("skill",tmp);

            if((tmp = request.getParameter("genre")) != null && !tmp.equals("q"))
                jo.addProperty("sex",tmp);

            if((tmp = request.getParameter("order")) != null && !tmp.equals("q"))
                jo.addProperty("order",tmp);

            Response responseHttp;

            try {
                responseHttp = Http.post(Utils.SERVER + "getPersonalTrainers",jo.toString());
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
                PersonalTrainer[] tmps = gson.fromJson(responseJSON.data.toString(),PersonalTrainer[].class);

                Collection<PersonalTrainer> pts = new ArrayList<>(Arrays.asList(tmps));
                request.setAttribute("personalTrainers",pts);
            }
            else {
                request.setAttribute("errorMessage", "Não é possível consultar os personal trainers disponíveis neste momento, volte mais tarde.");
            }
            Utils.forward(request,response,"/WEB-INF/Template.jsp","SearchPersonalTrainer",null);
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
            JsonObject jo = new JsonObject();
            jo.addProperty("username",username);
            jo.addProperty("token",token);

            Response responseHttp;

            try {
                responseHttp = Http.post(Utils.SERVER + "getPersonalTrainers",jo.toString());
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
            ResponseJSON responseJSON = gson.fromJson(body,ResponseJSON.class);

            responseHttp.close();

            if(responseJSON.status.equals("success")) {
                PersonalTrainer[] tmp = gson.fromJson(responseJSON.data.toString(),PersonalTrainer[].class);

                Collection<PersonalTrainer> pts = new ArrayList<>(Arrays.asList(tmp));
                request.setAttribute("personalTrainers",pts);
            }
            else {
                request.setAttribute("errorMessage", "Não é possível consultar os personal trainers disponíveis neste momento, volte mais tarde.");
            }
            request.setAttribute("title","Procurar PT");
            Utils.forward(request,response,"/WEB-INF/Template.jsp","SearchPersonalTrainer",null);
        }
    }
}
