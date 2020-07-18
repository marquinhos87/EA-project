package personaltrainerservlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.*;
import parseJSON.deserializer.TaskDeserializer;
import parseJSON.deserializer.WeekDeserializer;
import parseJSON.deserializer.WorkoutDeserializer;
import utils.Utils;

import javax.resource.spi.work.Work;
import javax.rmi.CORBA.Util;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebServlet(name = "CreateWeekServlet", urlPatterns = "/CreateWeek")
public class CreateWeekServlet extends HttpServlet {

    private final Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .registerTypeAdapter(Week.class, new WeekDeserializer())
            .registerTypeAdapter(Workout.class, new WorkoutDeserializer())
            .registerTypeAdapter(Task.class, new TaskDeserializer())
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

        String action = request.getParameter("action");

        if(action != null && action.equals("addWorkout")){
            Week week = (Week) session.getAttribute("newWeek");
            int maxId = Integer.parseInt(request.getParameter("tableSize"));

            if(week == null){
                week = new Week();
            }

            Workout workout = new Workout();
            workout.tasks = new ArrayList<>();
            workout.designation = request.getParameter("designation");
            workout.weekDay = Integer.parseInt(request.getParameter("weekDay"));

            for(int i = 1; i <= maxId; i++){
                Task task = new Task();
                String taskName;
                if((taskName = request.getParameter("task" + i)) != null){
                    task.designation = taskName;
                    task.duration = request.getParameter("duration" + i);
                    task.equipment = request.getParameter("equipment" + i) != null ? request.getParameter("equipment" + i) : "";
                    task.rest = request.getParameter("restTask" + i);
                    int numberOfSeries = Integer.parseInt(request.getParameter("nSerie" + i));

                    List<Serie> series = new ArrayList<>();
                    for(int j = 0; j < numberOfSeries; j++){
                        Serie serie = new Serie();
                        serie.description = taskName;
                        serie.repetitions = request.getParameter("duration" + i) + " " + request.getParameter("durationType" + i);
                        serie.rest = request.getParameter("rest" + i) + " " + request.getParameter("restType" + i);
                        series.add(serie);
                    }
                    task.series = series;
                }
                workout.tasks.add(task);
            }
            week.workoutsList.add(workout);
            System.err.println(week);
            session.setAttribute("newWeek", week);
        }

        Request r = (Request) session.getAttribute("request");

        request.setAttribute("title", "Criar 1ª semana");
        Utils.forward(request, response, "/WEB-INF/Template.jsp", "CreateWeek", null);

    }
}
