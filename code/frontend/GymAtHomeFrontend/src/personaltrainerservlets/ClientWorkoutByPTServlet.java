package personaltrainerservlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
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

@WebServlet(name = "ClientWorkoutByPTServlet", urlPatterns = "/ClientWorkoutByPT")
public class ClientWorkoutByPTServlet extends HttpServlet {

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
        username = (String) session.getAttribute("username");
        token = (String) session.getAttribute("token");
        if (username == null || token == null) { // NOT logged in
            Utils.redirect(request, response, "/Login");
            return;
        }

        String finishWorkout = request.getParameter("finishWorkout");
        if (finishWorkout != null) {
            int workoutId = Integer.parseInt(finishWorkout);

            JsonObject jo = new JsonObject();
            jo.addProperty("username", username);
            jo.addProperty("token", token);
            jo.addProperty("workoutId", workoutId);

            Response responseHttp;
            try {
                responseHttp = Http.post(Utils.SERVER + "finishWorkout", jo.toString());
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", Utils.CONNECTION_LOST_MSG);
                request.setAttribute("title", "Conexão perdida");
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "-", null);
                return;
            }
            String data = responseHttp.body().string();
            responseHttp.close();
            ResponseJSON rj = gson.fromJson(data, ResponseJSON.class);
            System.out.println(rj);
            if (rj.status.equals("success")) {
                Utils.redirect(request, response, "/ClientPlanByPT");
            } else {
                request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                request.setAttribute("title", "Erro interno");
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "-", null);
                return;
            }
        }

        Week week = (Week) session.getAttribute("week");
        if (week == null) {
            request.setAttribute("errorMessage", "Erro interno (week not in session)");
            request.setAttribute("title", "Erro interno (week not in session)");
            Utils.forward(request, response, "/WEB-INF/Template.jsp", "-", null);
            return;
        }

        int selectedWorkout = -1;
        String selectedWorkoutStr = request.getParameter("workout");
        if (selectedWorkoutStr != null) {
            try {
                selectedWorkout = Integer.parseInt(selectedWorkoutStr);
            } catch (Exception e) {
                e.printStackTrace();
                Utils.redirect(request, response, "/ClientPlanByPT");
                return;
            }
        }
        Workout workout = week.workouts.get(selectedWorkout);
        if (workout == null) {
            // workout = week.workoutsList.get(0); // por padrão mostra o Workout 0
            Utils.redirect(request, response, "/ClientPlanByPT");
            return;
        }
        request.setAttribute("workout", workout);

        int selectedTask = -1;
        String selectedTaskStr = request.getParameter("task");
        if (selectedTaskStr != null) {
            try {
                selectedTask = Integer.parseInt(selectedTaskStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (selectedTask < 0 || selectedTask >= workout.tasks.size())
            selectedTask = 0;  // por padrão mostra a Task 0

        Task task = workout.tasks.get(selectedTask);
        request.setAttribute("task", task);
        request.setAttribute("selectedTask", selectedTask);
        Utils.forward(request, response, "/WEB-INF/Template.jsp", "ClientWorkoutByPT", null);
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
}
