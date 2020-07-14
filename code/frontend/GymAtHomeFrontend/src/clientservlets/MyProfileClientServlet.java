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
        String token = (String) request.getSession().getAttribute("token");
        if(username == null || token == null) {
            request.getSession().setAttribute("username",null);
            request.getSession().setAttribute("token",null);
            request.setAttribute("page","Login");
            getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Template.jsp").forward(request,response);
        }
        else {
            JsonObject jo = new JsonObject();
            jo.addProperty("username",username);
            jo.addProperty("token",token);
            jo.addProperty("name",(String)request.getAttribute("name"));
            jo.addProperty("birthday",(String)request.getAttribute("birthday"));
            jo.addProperty("sex",(String)request.getAttribute("genre"));
            jo.addProperty("email",(String)request.getAttribute("email"));
            jo.addProperty("height",(String)request.getAttribute("height"));
            jo.addProperty("weight",(String)request.getAttribute("weight"));

            // TODO improve password by type the older's one and new's confirmation
            String password = (String) request.getAttribute("password");
            if (password != null)
                jo.addProperty("password",password);

            String tmp = (String) request.getAttribute("waist");
            if(!tmp.equals(""))
                jo.addProperty("waist",Integer.parseInt(tmp));

            tmp = (String) request.getAttribute("chest");
            if(!tmp.equals(""))
                jo.addProperty("chest",Integer.parseInt(tmp));

            tmp = (String) request.getAttribute("twin");
            if(!tmp.equals(""))
                jo.addProperty("twin",Integer.parseInt(tmp));

            tmp = (String) request.getAttribute("quadricep");
            if(!tmp.equals(""))
                jo.addProperty("quadricep",Integer.parseInt(tmp));

            tmp = (String) request.getAttribute("tricep");
            if(!tmp.equals(""))
                jo.addProperty("tricep",Integer.parseInt(tmp));

            tmp = (String) request.getAttribute("wrist");
            if(!tmp.equals(""))
                jo.addProperty("wrist",Integer.parseInt(tmp));

            Response responseHttp = Http.post("http://gymathome:8081/GymAtHome/api/editClientProfile",jo.toString());

            request.setAttribute("page","MyProfileClient");

            if(responseHttp.code() == HttpServletResponse.SC_OK) {
                doGet(request,response);
            }
            else {
                String responseBody = responseHttp.body().string();
                ResponseJSON responseObject = gson.fromJson(responseBody,ResponseJSON.class);
                // TODO improve by checking the error (if it's a invalid token we have to send the client to login page)
                request.setAttribute("error",responseObject.msg);
                getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Template.jsp").forward(request,response);
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
        String action = (String) request.getAttribute("action");
        if(username == null || token == null) {
            request.getSession().setAttribute("username",null);
            request.getSession().setAttribute("token",null);
            request.setAttribute("page","Login");
        }
        else if(action!=null && action.equals("logout")){
            request.setAttribute("username",null);
            request.setAttribute("page","Login");
        }
        else {
            JsonObject jo = new JsonObject();
            jo.addProperty("username",username);
            jo.addProperty("token",token);

            Response responseHttp = Http.post("http://gymathome:8081/GymAtHome/api/getClientProfileByClient",jo.toString());

            String responseBody = responseHttp.body().string();
            ResponseJSON responseObject = gson.fromJson(responseBody,ResponseJSON.class);

            if(responseHttp.code() == HttpServletResponse.SC_OK) {
                JsonObject responseJson = responseObject.data.getAsJsonObject();
                request.setAttribute("username",responseJson.get("username").getAsString());
                request.setAttribute("name",responseJson.get("name").getAsString());
                request.setAttribute("email",responseJson.get("email").getAsString());
                request.setAttribute("birthday",responseJson.get("birthday").getAsString());
                request.setAttribute("genre",responseJson.get("sex").getAsString());
                request.setAttribute("height",responseJson.get("height").getAsInt());
                request.setAttribute("weight",responseJson.get("weight").getAsInt());
                request.setAttribute("bci",responseJson.get("imc").getAsInt());
                int tmp = 0;
                if(responseJson.has("waist") && (tmp = responseJson.get("waist").getAsInt())!=0)
                    request.setAttribute("waist",tmp);
                if(responseJson.has("chest") && (tmp = responseJson.get("chest").getAsInt())!=0)
                    request.setAttribute("chest",tmp);
                if(responseJson.has("twin") && (tmp = responseJson.get("twin").getAsInt())!=0)
                    request.setAttribute("twin",tmp);
                if(responseJson.has("quadriceo") && (tmp = responseJson.get("quadricep").getAsInt())!=0)
                    request.setAttribute("quadricep",tmp);
                if(responseJson.has("tricep") && (tmp = responseJson.get("tricep").getAsInt())!=0)
                    request.setAttribute("tricep",tmp);
                if(responseJson.has("wrist") && (tmp = responseJson.get("wrist").getAsInt())!=0)
                    request.setAttribute("wrist",tmp);
            }
            else {
                // TODO improve by checking the error (if it's a invalid token we have to send the client to login page)
                request.setAttribute("error",responseObject.msg);
            }
            request.setAttribute("page","MyProfileClient");
        }
        getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Template.jsp").forward(request,response);
    }
}
