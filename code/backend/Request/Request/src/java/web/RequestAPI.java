/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.orm.PersistentException;
import requests.ClientAlreadyExistsException;
import requests.ClientDoesNotExistException;
import requests.JsonKeyInFaultException;
import requests.PersonalTrainerAlreadyExistsException;
import requests.PersonalTrainerDoesNotExistException;
import requests.RequestDoesNotExistException;
import requests.RequestsFacade;
import requests.TokenIsInvalidException;
import requests.UserAlreadyExistsException;
import requests.UserDoesNotExistException;
import requests.Utils;

/**
 *
 * @author josepereira
 */
@WebServlet(name="api", urlPatterns="api/*")
public class RequestAPI extends HttpServlet {
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
        response.setContentType("application/json");
        response.getWriter().print(Utils.makeError(404, "Method GET not allowed."));
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

        response.setContentType("application/json");

        StringBuilder sb = new StringBuilder();

        while((line = request.getReader().readLine())!=null)sb.append(line);
        
        String json = sb.toString();
        
        RequestsFacade facade = RequestsFacade.getInstance();
        
        switch(target){
            case "updateUserToken":
            {
                try {
                    facade.updateUserToken(json);
                } catch (JsonKeyInFaultException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(11, "Json key in fault: " + ex.getMessage()) + ".");
                    break;
                } catch (UserDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(12, "User with username " + ex.getMessage() + " does not exist on database."));
                    break;
                } catch (TokenIsInvalidException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(13, "Token is invalid."));
                    break;
                } catch (PersistentException ex) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(14, "Error with session."));
                    break;
                }catch (Exception ex){
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(19, "Probably json doesn't have a valid format. Or other internal error"));
                    break;
                }
                response.setStatus(200);
                response.getWriter().print(Utils.makeSuccess(2,"\"User token updated with success.\""));
                break;
            }
            case "createClient":
            {
                try {
                    facade.createClient(json);
                } catch (JsonKeyInFaultException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(11, "Json key in fault: " + ex.getMessage() + "."));
                    break;
                } catch (PersistentException ex) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(14, "Error with session."));
                    break;
                } catch (UserAlreadyExistsException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(15, "User with username " + ex.getMessage() + " already exist."));
                    break;
                } catch (ClientAlreadyExistsException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(16, "Client with username " + ex.getMessage() + " already exist."));
                    break;
                }catch (Exception ex){
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(19, "Probably json doesn't have a valid format. Or other internal error"));
                    break;
                }
                response.setStatus(200);
                response.getWriter().print(Utils.makeSuccess(1,"\"Client created with success.\""));
                break;
            }
            case "createPersonalTrainer":
            {
                try {
                    facade.createPersonalTrainer(json);
                } catch (UserAlreadyExistsException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(15, "User with username " + ex.getMessage() + " already exist."));
                    break;
                } catch (PersonalTrainerAlreadyExistsException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(16, "Personal Trainer with username " + ex.getMessage() + " already exist."));
                    break;
                } catch (JsonKeyInFaultException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(11, "Json key in fault: " + ex.getMessage() + "."));
                    break;
                } catch (PersistentException ex) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(14, "Error with session."));
                    break;
                }catch (Exception ex){
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(19, "Probably json doesn't have a valid format. Or other internal error"));
                    break;
                }
                response.setStatus(200);
                response.getWriter().print(Utils.makeSuccess(1,"\"Personal Trainer created with success.\""));
                break;
            }
            case "submitRequest":
            {
                try {
                    facade.submitRequest(json);
                } catch (JsonKeyInFaultException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(11, "Json key in fault: " + ex.getMessage() + "."));
                    break;
                } catch (UserDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(12, "User with username " + ex.getMessage() + " does not exist on database."));
                    break;
                } catch (PersistentException ex) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(14, "Error with session."));
                    break;
                } catch (ClientDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(20, "Client with username " + ex.getMessage() + " does not exist on database."));
                    break;
                } catch (PersonalTrainerDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(17, "PersonalTrainer with username " + ex.getMessage() + " does not exist on database."));
                    break;
                } catch (TokenIsInvalidException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(13, "Token is invalid."));
                    break;
                }catch (Exception ex){
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(19, "Probably json doesn't have a valid format. Or other internal error"));
                    break;
                }
                response.setStatus(200);
                response.getWriter().print(Utils.makeSuccess(1,"\"Request was been created with success.\""));
                break;
            }
            case "replyToRequest":
            {
                try {
                    facade.replyToRequest(json);
                } catch (JsonKeyInFaultException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(11, "Json key in fault: " + ex.getMessage() + "."));
                    break;
                } catch (PersistentException ex) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(14, "Error with session."));
                    break;
                } catch (PersonalTrainerDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(17, "PersonalTrainer with username " + ex.getMessage() + " does not exist on database."));
                    break;
                } catch (RequestDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(21, "Request with id " + ex.getMessage() + " does not exist on database."));
                    break;
                } catch (UserDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(12, "User with username " + ex.getMessage() + " does not exist on database."));
                    break;
                } catch (TokenIsInvalidException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(13, "Token is invalid."));
                    break;
                }catch (Exception ex){
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(19, "Probably json doesn't have a valid format. Or other internal error"));
                    break;
                }
                response.setStatus(200);
                response.getWriter().print(Utils.makeSuccess(1,"\"Reply to request with success.\""));
                break;
            }
            case "listRequestsOfPersonalTrainer":
            {
                try {
                    json = facade.listClientRequestsByPersonalTrainer(json);
                } catch (JsonKeyInFaultException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(11, "Json key in fault: " + ex.getMessage() + "."));
                    break;
                } catch (TokenIsInvalidException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(13, "Token is invalid."));
                    break;
                } catch (UserDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(12, "User with username " + ex.getMessage() + " does not exist on database."));
                    break;
                } catch (PersistentException ex) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(14, "Error with session."));
                    break;
                } catch (PersonalTrainerDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(17, "PersonalTrainer with username " + ex.getMessage() + " does not exist on database."));
                    break;
                }catch (Exception ex){
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(19, "Probably json doesn't have a valid format. Or other internal error"));
                    break;
                }
                response.setStatus(200);
                response.getWriter().print(Utils.makeSuccess(1,json));
                break;
            }
            case "listRequestsOfClient":
            {
                try {
                    json = facade.listClientRequestsByClient(json);
                } catch (TokenIsInvalidException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(13, "Token is invalid."));
                    break;
                } catch (UserDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(12, "User with username " + ex.getMessage() + " does not exist on database."));
                    break;
                } catch (PersistentException ex) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(14, "Error with session."));
                    break;
                } catch (JsonKeyInFaultException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(11, "Json key in fault: " + ex.getMessage() + "."));
                    break;
                } catch (ClientDoesNotExistException ex) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    response.getWriter().print(Utils.makeError(20, "Client with username " + ex.getMessage() + " does not exist on database."));
                    break;
                }catch (Exception ex){
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().print(Utils.makeError(19, "Probably json doesn't have a valid format. Or other internal error"));
                    break;
                }
                response.setStatus(200);
                response.getWriter().print(Utils.makeSuccess(1,json));
                break;
            }
            
            default:
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().print(Utils.makeError(404,"There is no such target for " + target + "."));
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
    
    //  LANGUAGE OF SERVICE
    /**
     * SUCCESS: 1 to 10
     * 
     * 1 - normal success
     * 
     * 
     * ERRORS: 11 to ...
     * 
     * 11 - JsonKeyInFaultException
     * 12 - UserDoesNotExistException
     * 13 - TokenIsInvalidException
     * 14 - PersistentException
     * 15 - UserAlreadyExistsException
     * 16 - ClientAlreadyExistsException
     * 17 - PersonalTrainerDoesNotExistException
     * 18 - PersonalTrainerAlreadyExistsException
     * 19 - Probably json not validated format
     * 20 - ClientDoesNotExistException
     * 21 - RequestDoesNotExsistException
     */
}
