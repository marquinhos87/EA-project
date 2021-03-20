<%@ page import="core.Workout" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.TimeZone" %>
<%@ page import="core.Task" %>
<%@ page import="core.Serie" %><%--
  Created by IntelliJ IDEA.
  User: ricardo
  Date: 17/07/20
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h4 class="mt-4">Cliente: @${sessionScope.clientUsername}</h4>

<h4 class="mt-4">
    Workout - Dia (

    <%
        Workout workout = (Workout) request.getAttribute("workout");

        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.setTime(workout.date);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        out.print(day + "/" + month);

    %>
) -
    <%
        out.print(workout.designation);
    %>
</h4>

<h4 class="mt-5 table">
    Tarefa
    <%
        int selectedTask = (int) request.getAttribute("selectedTask");
        Task task = (Task) request.getAttribute("task");
        out.print( (selectedTask+1) + " - " + task.designation);
    %>
</h4>

<h4 class="mt-5 table">
    <i style='font-size:24px' class='far'>&#xf017;</i>&nbsp <% out.print(task.duration); %> &nbsp &nbsp &nbsp
    Equipamento necessário: <% out.print(task.equipment); %>
</h4>

<table class="mt-5 table table-bordered">
    <thead>
    <tr class="table-active">
        <th class="text-left">Descrição</th>
        <th class="text-left">Repetições ou tempo</th>
        <th class="text-left">Repouso entre séries</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (Serie serie: task.series) {
            out.print("<tr>");
            out.print("<td>" + serie.description + "</td>");
            out.print("<td>" + serie.repetitions + "</td>");
            out.print("<td>" + serie.rest + "</td>");
            out.print("</tr>");
        }
    %>
    </tbody>
</table>

<div class="mt-4 alert alert-warning" role="alert">
    Repouso até à próxima tarefa: <% out.print(task.rest); %>
</div>

<div class="mt-5 position-relative">
    <%
        out.print("<form action=\"" + request.getContextPath() + "/ClientWorkoutByPT\" method=\"POST\">");
            if (selectedTask != 0) out.print("<button type=\"button\" class=\"btn btn-primary\" onclick=\"window.location.href='" + request.getContextPath() + "/ClientWorkoutByPT?workout=" + workout.id + "&task=" + (selectedTask-1) + "'\">Tarefa Anterior</button>");
            if (selectedTask == workout.tasks.size()-1) {
                out.print("<button type=\"button\" class=\"mr-5 btn btn-secondary position-absolute\" style=\"right: 0;\" onclick=\"window.location.href='" + request.getContextPath() + "/ClientPlanByPT'\">Voltar ao plano</button>");
            }
            else {
                out.print("<button type=\"button\" class=\"mr-5 btn btn-secondary position-absolute\" style=\"right: 0; transform: translateX(-100%);\" onclick=\"window.location.href='" + request.getContextPath() + "/ClientPlanByPT'\">Voltar ao plano</button>");
                out.print("<button type=\"button\" class=\"btn btn-primary position-absolute\" style=\"right: 0\" onclick=\"window.location.href='" + request.getContextPath() + "/ClientWorkoutByPT?workout=" + workout.id + "&task=" + (selectedTask+1) + "'\">Próxima Tarefa</button>");
            }
        out.print("</form>");
    %>
</div>