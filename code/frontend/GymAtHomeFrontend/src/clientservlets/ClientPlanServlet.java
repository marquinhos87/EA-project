package clientservlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import core.BiometricData;
import core.Task;
import core.Week;
import core.Workout;
import okhttp3.Response;
import parseJSON.ResponseJSON;
import parseJSON.deserializer.TaskDeserializer;
import parseJSON.deserializer.WeekDeserializer;
import parseJSON.deserializer.WorkoutDeserializer;
import utils.Http;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ClientPlanServlet", urlPatterns = "/ClientPlan")
public class ClientPlanServlet extends HttpServlet {

    private final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .registerTypeAdapter(Week.class, new WeekDeserializer())
            .registerTypeAdapter(Workout.class, new WorkoutDeserializer())
            .registerTypeAdapter(Task.class, new TaskDeserializer())
            .create();
    private HttpSession session;
    private String username;
    private String token;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        // TODO remover os setters() daqui
        session.setAttribute("username", "c0");
        session.setAttribute("token", "c0xcn7qDz5OL4TZqJwp3R0pgVdeV5Rzc");
        session.setAttribute("userType", "client");
        // ----------------------------------------------------------------------------
        username = (String) session.getAttribute("username");
        token = (String) session.getAttribute("token");

        Week week = getWeek(request, response, 1);
        BiometricData biometricData = getBiometricData(request, response);

        request.setAttribute("week", week);
        request.setAttribute("biometricData", biometricData);
        Utils.forward(request, response, "/WEB-INF/Template.jsp", "ClientPlan", null);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
    }

    private Week getWeek(HttpServletRequest request, HttpServletResponse response, int weekNumber) throws ServletException, IOException {

        JsonObject jo = new JsonObject();
        jo.addProperty("username", username);
        jo.addProperty("token", token);
        jo.addProperty("week", weekNumber);

        Response responseHttp = null;
        try {
            responseHttp = Http.post(Utils.SERVER + "getWeekByClient", jo.toString());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Could not connect to server! Try refreshing this page. If it doesn't work please contact support.");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "ClientPlan", null);
        }
        String data = responseHttp.body().string();
        responseHttp.close();
        ResponseJSON rj = gson.fromJson(data, ResponseJSON.class);
        if (rj.status.equals("success")) {
            return gson.fromJson(rj.data, Week.class);
        } else {
            request.setAttribute("errorMessage", "Something went wrong while requesting current plan week! Try refreshing this page. If it doesn't work please contact support.");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "ClientPlan", null);
            return null;
        }
    }

    private BiometricData getBiometricData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        JsonObject jo = new JsonObject();
        jo.addProperty("username", username);
        jo.addProperty("token", token);
        jo.addProperty("clientUsername", username);

        Response responseHttp = null;
        try {
            responseHttp = Http.post(Utils.SERVER + "getBiometricData", jo.toString());
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Could not connect to server! Try refreshing this page. If it doesn't work please contact support.");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "ClientPlan", null);
        }
        String data = responseHttp.body().string();
        responseHttp.close();
        ResponseJSON rj = gson.fromJson(data, ResponseJSON.class);
        if (rj.status.equals("success")) {
            return gson.fromJson(rj.data, BiometricData.class);
        } else {
            request.setAttribute("errorMessage", "Something went wrong while requesting biometric data! Try refreshing this page. If it doesn't work please contact support.");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "ClientPlan", null);
            return null;
        }
    }
}
