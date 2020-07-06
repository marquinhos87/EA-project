package servlets;

import backend.GymAtHome;
import static utils.Utils.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

@WebServlet(name = "APIController", urlPatterns = "/api/v1/GymAtHome/GymAtHome")
public class APIController extends HttpServlet {

    private GymAtHome gymAtHome = GymAtHome.getInstance();

    private void processRequest(HttpServletResponse response, String target, String data) throws IOException {
        // Pass json data to correct function
        try {
            Method method = gymAtHome.getClass().getMethod(target, String.class);
            String sResponse = (String) method.invoke(gymAtHome,data);
            response.setContentType("application/json");
            response.getWriter().print(sResponse);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.getWriter().print(makeError(500,"Internal Error"));
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtain target
        String[] url = request.getRequestURI().split("/");
        String target = url[url.length-1];

        // Obtain json data from http post as a string
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = request.getReader().readLine())!=null) {
            sb.append(line);
        }
        String data = sb.toString();

        // Process requested method
        processRequest(response, target, data);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtain target
        String[] url = request.getRequestURI().split("/");
        String target = url[url.length-1];

        // Obtain url parameters
        Map<String,String[]> parameters = request.getParameterMap();

        // Create a json string with the parameters
        String data = parametersToJSON(parameters);

        // Process requested method
        processRequest(response, target, data);
    }
}
