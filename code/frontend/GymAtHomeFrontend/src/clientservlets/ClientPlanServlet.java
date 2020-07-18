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
        /*
        session.setAttribute("username", "c0");
        session.setAttribute("token", "c0LCiPfPXPlsK4dXymOuw1WRUGBAozRg");
        session.setAttribute("userType", "client");
        // ----------------------------------------------------------------------------
         */
        username = (String) session.getAttribute("username");
        token = (String) session.getAttribute("token");
        if (username == null || token == null) { // NOT logged in
            Utils.redirect(request, response, "/Login");
        }

        int selectedWeek = -1;
        String selectedWeekStr = request.getParameter("week");
        if (selectedWeekStr != null) {
            try {
                selectedWeek = Integer.parseInt(selectedWeekStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        boolean res = getWeek(request, response, selectedWeek);
        if (!res) return;
        res = getBiometricData(request, response);
        if (!res) return;

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

    private boolean getWeek(HttpServletRequest request, HttpServletResponse response, int selectedWeek) throws ServletException, IOException {

        try {

            JsonObject jo = new JsonObject();
            jo.addProperty("username", username);
            jo.addProperty("token", token);
            if (selectedWeek != -1) jo.addProperty("week", selectedWeek);

            Response responseHttp;
            try {
                responseHttp = Http.post(Utils.SERVER + "getWeekByClient", jo.toString());
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", Utils.CONNECTION_LOST_MSG);
                request.setAttribute("title", "Conexão perdida");
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "-", null);
                return false;
            }
            String data = responseHttp.body().string();
            responseHttp.close();
            ResponseJSON rj = gson.fromJson(data, ResponseJSON.class);
            if (rj.status.equals("success")) {
                Week week = gson.fromJson(rj.data.getAsJsonObject(), Week.class);
                int numberOfWeeks = rj.data.getAsJsonObject().get("numberOfWeeks").getAsInt();
                int currentWeek = rj.data.getAsJsonObject().get("currentWeek").getAsInt();
                session.setAttribute("week", week);
                request.setAttribute("numberOfWeeks", numberOfWeeks);
                if (selectedWeek == -1 || currentWeek == selectedWeek) {
                    request.setAttribute("isCurrentWeek", true);
                    request.setAttribute("title", "Semana " + week.number + " (atual)");
                } else {
                    request.setAttribute("isCurrentWeek", false);
                    request.setAttribute("title", "Semana " + week.number);
                }
                return true;
            } else {
                if (rj.code == 400) { // Invalid week (< 0 or > MAX)
                    // return current week
                    return getWeek(request, response, -1);
                }
                request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                request.setAttribute("title", "Erro interno");
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "-", null);
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
            request.setAttribute("title", "Erro interno");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "-", null);
            return false;
        }
    }

    private boolean getBiometricData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            JsonObject jo = new JsonObject();
            jo.addProperty("username", username);
            jo.addProperty("token", token);
            jo.addProperty("clientUsername", username);

            Response responseHttp;
            try {
                responseHttp = Http.post(Utils.SERVER + "getBiometricData", jo.toString());
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", Utils.CONNECTION_LOST_MSG);
                request.setAttribute("title", "Conexão perdida");
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "-", null);
                return false;
            }
            String data = responseHttp.body().string();
            responseHttp.close();
            ResponseJSON rj = gson.fromJson(data, ResponseJSON.class);
            if (rj.status.equals("success")) {
                BiometricData biometricData = gson.fromJson(rj.data, BiometricData.class);
                request.setAttribute("biometricData", biometricData);
                return true;
            } else {
                request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                request.setAttribute("title", "Erro interno");
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "-", null);
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
            request.setAttribute("title", "Erro interno");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "-", null);
            return false;
        }
    }
}