package clientservlets;

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
            request.setAttribute("page","ClientRegister");
        }
        getServletConfig().getServletContext().getRequestDispatcher("/WEB-INF/Template.jsp").forward(request,response);
    }
}
