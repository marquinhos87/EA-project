package clientservlets;

import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ClientRegisterServlet", urlPatterns = "/ClientRegister")
public class ClientRegisterServlet extends HttpServlet {

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
                request.setAttribute("page","MyProfileClient");
            }
            else if(username.startsWith("pt")) {
                request.setAttribute("page","MyProfilePersonalTrainer");
            }
            else {
                request.getSession().setAttribute("username", null);
                request.getSession().setAttribute("token", null);
                request.setAttribute("page", "Login");
            }
            getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Template.jsp").forward(request,response);
        }
        else {
            String password = (String)request.getAttribute("password");
            String confirmationPassword = (String)request.getAttribute("cpassword");

            if(password.equals(confirmationPassword)) {
                //TODO encrypt password
                JsonObject jo = new JsonObject();
                jo.addProperty("name", (String) request.getAttribute("name"));
                jo.addProperty("username", (String) request.getAttribute("username"));
                jo.addProperty("email", (String) request.getAttribute("email"));
                jo.addProperty("password",password);
                jo.addProperty("birthday",(String)request.getAttribute("birthday"));
                jo.addProperty("sex",(String)request.getAttribute("genre"));
                jo.addProperty("height",(String)request.getAttribute("height"));
                jo.addProperty("weight",(String)request.getAttribute("weight"));

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

                getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Template.jsp").forward(request,response);
            }
            else {
                request.setAttribute("error","Passwords não coincidem.");
                doGet(request,response);
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
            }
            else if(username.startsWith("pt")) {
                request.setAttribute("page","MyProfilePersonalTrainer");
            }
            else {
                request.getSession().setAttribute("username", null);
                request.getSession().setAttribute("token", null);
                request.setAttribute("page", "Login");
            }
        }
        else {
            request.getSession().setAttribute("username", null);
            request.getSession().setAttribute("token", null);
            request.setAttribute("page","ClientRegister");
        }
        getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Template.jsp").forward(request,response);
    }
}
