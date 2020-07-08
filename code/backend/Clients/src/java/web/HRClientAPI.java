/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import hrclient.ClientAlreadyExistsException;
import hrclient.ClientDoesNotExistException;
import hrclient.HRClientFacade;
import hrclient.InvalidPasswordException;
import hrclient.JsonKeyInFaultException;
import hrclient.Utils;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            case "updateUserToken":
                try{
                    facade.updateUserToken(json);
                }catch (Exception e){
                    response.getWriter().print(Utils.makeSuccess(404,"Method not implemented yet."));
                }
                break;
            case "createClient":
            {
                try {
                    json = facade.createClient(json);
                } catch (PersistentException ex) {
                    response.getWriter().print(Utils.makeError(404, "Internal error."));
                    break;
                } catch (JsonKeyInFaultException ex) {
                    response.getWriter().print(Utils.makeError(404, "Json key in fault: " + ex.getMessage()));
                    break;
                } catch (ClientAlreadyExistsException ex) {
                    response.getWriter().print(Utils.makeError(404, "Client with username " + ex.getMessage() + " already exist."));
                    break;
                }
                response.getWriter().print(Utils.makeSuccess(200,json));
                break;
            }
            case "loginClient":
            {
                try {
                    json = facade.loginClient(json);
                } catch (InvalidPasswordException ex) {
                    response.getWriter().print(Utils.makeError(404, "Invalid password."));
                    break;
                } catch (PersistentException ex) {
                    response.getWriter().print(Utils.makeError(404, "Internal error."));
                    break;
                } catch (JsonKeyInFaultException ex) {
                    response.getWriter().print(Utils.makeError(404, "Json key in fault: " + ex.getMessage()));
                    break;
                } catch (ClientDoesNotExistException ex) {
                    response.getWriter().print(Utils.makeError(404, "Client with username " + ex.getMessage() + " does not exist."));
                    break;
                }
                response.getWriter().print(Utils.makeSuccess(200,json));
                break;
            }
            default:
                response.getWriter().print(Utils.makeSuccess(404,"There is no such method."));
                break;
        }
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
