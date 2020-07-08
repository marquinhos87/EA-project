/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import hrclient.ClientAlreadyExistsException;
import hrclient.HRClientFacade;
import hrclient.JsonKeyInFaultException;
import hrclient.Utils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.orm.PersistentException;

/**
 *
 * @author josepereira
 */
@WebServlet(name="api", urlPatterns="api/*")
public class HRClientAPI extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] url = request.getRequestURI().toString().split("/");
        String target = url[url.length-1], line = "";

        response.setContentType("aplication/json");

        StringBuilder sb = new StringBuilder();

        while((line = request.getReader().readLine())!=null)sb.append(line);
        
        String json = sb.toString();
        
        HRClientFacade facade = HRClientFacade.getInstance();

        switch (target){
            case "createClient":
            {
                try {
                    json = facade.createClient(json);
                } catch (PersistentException ex) {
                    response.getWriter().print(Utils.makeError(404, "Internal error."));
                } catch (JsonKeyInFaultException ex) {
                    response.getWriter().print(Utils.makeError(404, "Json key in fault: " + ex.getMessage()));
                } catch (ClientAlreadyExistsException ex) {
                    response.getWriter().print(Utils.makeError(404, "Client with username " + ex.getMessage() + " already exist."));
                }
                break;
            }
        }
        
        response.getWriter().print(Utils.makeSuccess(200,json));
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
