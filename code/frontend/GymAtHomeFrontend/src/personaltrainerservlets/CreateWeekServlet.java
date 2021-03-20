package personaltrainerservlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import core.*;
import okhttp3.Response;
import parseJSON.ResponseJSON;
import parseJSON.deserializer.TaskDeserializer;
import parseJSON.deserializer.WeekDeserializer;
import parseJSON.deserializer.WorkoutDeserializer;
import parseJSON.serializer.TaskSerializer;
import parseJSON.serializer.WeekSerializer;
import parseJSON.serializer.WorkoutSerializer;
import utils.Http;
import utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CreateWeekServlet", urlPatterns = "/CreateWeek")
public class CreateWeekServlet extends HttpServlet {

    private final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .registerTypeAdapter(Week.class, new WeekDeserializer())
            .registerTypeAdapter(Workout.class, new WorkoutDeserializer())
            .registerTypeAdapter(Task.class, new TaskDeserializer())
            .registerTypeAdapter(Week.class, new WeekSerializer())
            .registerTypeAdapter(Workout.class, new WorkoutSerializer())
            .registerTypeAdapter(Task.class, new TaskSerializer())
            .create();
    private HttpSession session;
    private String username;
    private String token;

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        username = (String) session.getAttribute("username");
        token = (String) session.getAttribute("token");
        if (username == null || token == null) { // NOT logged in
            Utils.redirect(request, response, "/Login");
            return;
        }

        Request r = (Request) session.getAttribute("request");

        //TODO fix Request not available after fst week
        String clientUsername = (String) session.getAttribute("clientUsername");
        // NOT fst week
        if (r == null) {
            r = new Request();
            r.setID(-1);
            r.level = 1000;
            r.clientUsername = clientUsername;
            r.workoutPerWeek = 7;
            r.numberOfWeeks = 12;
            r.weekDays = "1;2;3;4;5;6;7";
            r.objective = "--";
            session.setAttribute("request", r);
        }

        String action = request.getParameter("action");
        String removeWorkout = request.getParameter("removeWorkout");

        if(removeWorkout != null) {
            Week week = (Week) session.getAttribute("newWeek");
            int workoutId = Integer.parseInt(removeWorkout);
            week.workouts.remove(workoutId);
        }
        else if(action != null) {

            if (action.equals("addWorkout")) {
                Week week = (Week) session.getAttribute("newWeek");
                int maxId = Integer.parseInt(request.getParameter("tableSize"));

                if (week == null) {
                    week = new Week();
                }

                Workout workout = new Workout();
                workout.tasks = new ArrayList<>();
                workout.designation = request.getParameter("designation");
                workout.weekDay = Integer.parseInt(request.getParameter("weekDay"));
                workout.id = workout.weekDay;

                for (int i = 1; i <= maxId; i++) {
                    String taskName;
                    if ((taskName = request.getParameter("task" + i)) != null) {
                        Task task = new Task();
                        task.designation = taskName;
                        task.equipment = request.getParameter("equipment" + i) != null ? request.getParameter("equipment" + i) : "--";

                        int taskRest = Integer.parseInt(request.getParameter("taskRest" + i));
                        String taskRestType = request.getParameter("taskRestType" + i);
                        task.rest = taskRest + " " + taskRestType;

                        int timeInSeconds = 0;
                        if (taskRestType.equals("segundos")) {
                            timeInSeconds = taskRest;
                        } else if (taskRestType.equals("minuto(s)")) {
                            timeInSeconds = taskRest * 60;
                        }

                        int numberOfSeries = Integer.parseInt(request.getParameter("nSerie" + i));

                        List<Serie> series = new ArrayList<>();
                        for (int j = 0; j < numberOfSeries; j++) {
                            Serie serie = new Serie();
                            serie.description = taskName;

                            int repetitions = Integer.parseInt( request.getParameter("duration" + i));
                            String repetitionsType = request.getParameter("durationType" + i);
                            serie.repetitions = repetitions + " " + repetitionsType;

                            if (repetitionsType.equals("segundos")) {
                                timeInSeconds += repetitions;
                            } else if (repetitionsType.equals("minuto(s)")) {
                                timeInSeconds += repetitions * 60;
                            } else if (repetitionsType.equals("vezes")) {
                                timeInSeconds += repetitions * 5;
                            }

                            int serieRest = Integer.parseInt(request.getParameter("rest" + i));
                            String serieRestType = request.getParameter("restType" + i);
                            serie.rest = serieRest + " " + serieRestType;

                            if (serieRestType.equals("segundos")) {
                                timeInSeconds += serieRest;
                            } else if (serieRestType.equals("minuto(s)")) {
                                timeInSeconds += serieRest * 60;
                            }

                            series.add(serie);
                        }

                        task.series = series;
                        task.duration = String.format("%.01f", (float)timeInSeconds / (float)60) + " minutos";
                        workout.totalTime += timeInSeconds;
                        workout.tasks.add(task);
                    }

                }

                week.workouts.put(workout.id, workout);
                session.setAttribute("newWeek", week);
                request.setAttribute("title", "Criar 1ª semana");
                Utils.forward(request, response, "/WEB-INF/Template.jsp", "CreateWeek", null);
                return;
            }

            else if (action.equals("cancelWeek")) {
                session.setAttribute("newWeek", null);
                session.setAttribute("request", null);
                Utils.redirect(request, response, "/MyRequestsPT");
                return;
            }

            else if (action.equals("saveWeek")) {

                // if it's fst week, reply to request as accepted
                if (r.getID() != -1) {

                    JsonObject replyToRequest = new JsonObject();

                    replyToRequest.addProperty("username", username);
                    replyToRequest.addProperty("token", token);
                    replyToRequest.addProperty("requestId", r.getID());
                    replyToRequest.addProperty("clientUsername", r.getClientUsername());
                    replyToRequest.addProperty("state", 1);
                    replyToRequest.addProperty("accepted", true);

                    Response responseHttp;
                    try {
                        responseHttp = Http.post(Utils.SERVER + "replyToRequest",replyToRequest.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                        request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                        Utils.forward(request, response, "/WEB-INF/Template.jsp", "-", null);
                        return ;
                    }
                    String responseBody = responseHttp.body().string();
                    responseHttp.close();
                    ResponseJSON responseJSON = gson.fromJson(responseBody, ResponseJSON.class);

                    if(!responseJSON.status.equals("success")){
                        switch (responseJSON.code){
                            default:
                                request.setAttribute("errorMessage", Utils.UNEXPECTED_ERROR_MSG);
                                Utils.forward(request, response, "/WEB-INF/Template.jsp", "-", null);
                                return;
                        }
                    }
                }

                Week week = (Week) session.getAttribute("newWeek");

                JsonObject jo = gson.toJsonTree(week, Week.class).getAsJsonObject();
                jo.addProperty("username", username);
                jo.addProperty("token", token);
                jo.addProperty("clientUsername", r.clientUsername);
                if (session.getAttribute("planId") != null) {
                    jo.addProperty("planId", (int) session.getAttribute("planId"));
                }

                Response responseHttp;
                try {
                    responseHttp = Http.post(Utils.SERVER + "createWeek", jo.toString());
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
                    session.setAttribute("newWeek", null);
                    session.setAttribute("request", null);
                    request.setAttribute("successMessage", "A Semana foi guardada com sucesso.");
                    Utils.redirect(request, response, "/MyRequestsPT");
                    return;
                } else {
                    request.setAttribute("errorMessage", "Não foi possível guardar a Semana. " + Utils.UNEXPECTED_ERROR_MSG);
                    request.setAttribute("title", "Erro interno");
                    Utils.forward(request, response, "/WEB-INF/Template.jsp", "CreateWeek", null);
                    return;
                }
            }
        }

        request.setAttribute("title", "Criar 1ª semana");
        Utils.forward(request, response, "/WEB-INF/Template.jsp", "CreateWeek", null);
    }
}
