<%@ page import="core.Request" %>
<%@ page import="utils.Utils" %>
<%@ page import="core.Workout" %>
<%@ page import="core.Week" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Aviso</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Tem a certeza que deseja apagar o Workout? Não poderá reverter esta ação.
            </div>
            <div class="modal-footer">
                <form method="POST" action="${pageContext.request.contextPath}/CreateWeek">
                    <button type="button" class="btn btn-danger mr-2" data-dismiss="modal">Não</button>
                    <button id="removeWorkoutButton" type="submit" class="btn btn-success" name="removeWorkout">Sim</button>
                </form>
            </div>
        </div>
    </div>
</div>


<%
    Request r = (Request) session.getAttribute("request");
    Week week = (Week) session.getAttribute("newWeek");
    boolean semDias = false;
    if (week != null && week.workouts.size() == r.workoutPerWeek) {
        semDias = true;
    }
%>

<div class="row mt-5 position-relative">
    <h4 class="mt-4">Semana 1: @${sessionScope.request.clientUsername}
    <%
        out.print("<button type=\"button\" class=\"btn btn-danger position-absolute\" style=\"right: 0; transform: translateX(-110%);\">Cancelar Semana</button>");
        out.print("<button type=\"submit\" class=\"btn btn-success position-absolute\" style=\"right: 0\" disabled>Guardar Semana</button>");
    %>
    </h4>
</div>

<div class="mt-5 row ">
    <div class="col-5">
        <h5>Workouts já definidos:</h5>
            <table class="mt-4 table table-striped">
                <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">Dia</th>
                        <th scope="col">Workout</th>
                    </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${sessionScope.newWeek == null || sessionScope.newWeek.workouts.size() == 0}">
                        <tr>
                            <td><button disabled class="btn btn-danger text-white">X</button></td>
                            <td>---</td>
                            <td>---</td>
                        </tr>
                    </c:when>
                    <c:when test="true">
                        <%
                            for(Workout workout : ((Week) session.getAttribute("newWeek")).workouts.values()) {
                                out.print("<tr><td><button data-toggle=\"modal\" data-target=\"#exampleModalCenter\" class=\"btn btn-danger text-white\" onclick=\"document.getElementById('removeWorkoutButton').value='" + workout.id + "'\">X</button></td><td>" + Utils.prettyPrintWeekDay(workout.weekDay) + "</td><td>" + workout.designation + "</td></tr>");
                            }
                        %>
                    </c:when>
                </c:choose>
                </tbody>
            </table>
    </div>

    <div class="col-2"></div>

    <div class="col-5">
        <table class="table table-borderless">
            <tbody>
                <tr>
                    <td scope="row">Nº de semanas (disponibilidade):</td>
                    <td>${sessionScope.request.numberOfWeeks} semanas</td>
                </tr>
                <tr>
                    <td scope="row">Objetivo:</td>
                    <td>${sessionScope.request.objective}</td>
                </tr>
                <tr>
                    <td scope="row">Nº de treinos semanais:</td>
                    <%
                        if (!semDias) out.print("<td>" + r.workoutPerWeek + " dias por semana</td>");
                        else out.print("<td class=\"text-danger\"><b>" + r.workoutPerWeek + " dias por semana</b></td>");
                    %>
                </tr>
                <tr>
                    <td scope="row">Disponibilidade semanal:</td>
                    <td>
                        <%
                            out.print(Utils.prettyPrintWeekDays(r.weekDays));
                        %>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Dificuldade do plano:</td>
                    <td>Normal</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<%
    if (semDias == false) {
%>

<form method="POST" action="${pageContext.request.contextPath}/CreateWeek">
<div class="row mt-3">

    <div class="mb-4 col-4">
        <div class="row d-flex flex-column">
            <div class="col">
                <h5>Designação do Workout:</h5>
            </div>
            <div class="mt-2 col">
                <input type="text" class="form-control" name="designation" placeholder="Ex: Cardio" required>
            </div>
        </div>
    </div>

    <div class="mb-4 col-4">
        <div class="row d-flex flex-column">
            <div class="col">
                <h5>Dia da semana do Workout:</h5>
            </div>
            <div class="mt-2 col">
                <select class="custom-select" name="weekDay" required style="width: 50%">
                    <%

                        List<Integer> list = null;

                        if(week!= null)
                            list = week.workouts.values().stream().mapToInt(Workout::getWeekDay).boxed().collect(Collectors.toList());

                        for (String weekDayStr: r.weekDays.split(";")) {

                            if(list == null || !list.contains(Integer.parseInt(weekDayStr))) {
                                out.print("<option value=\"" + weekDayStr + "\">");
                                switch (weekDayStr) {
                                    case "1":
                                        out.print("Segunda");
                                        break;
                                    case "2":
                                        out.print("Terça");
                                        break;
                                    case "3":
                                        out.print("Quarta");
                                        break;
                                    case "4":
                                        out.print("Quinta");
                                        break;
                                    case "5":
                                        out.print("Sexta");
                                        break;
                                    case "6":
                                        out.print("Sábado");
                                        break;
                                    case "7":
                                        out.print("Domingo");
                                        break;
                                    default:
                                        out.print("NÃO É SUPOSTO ISTO ACONTECER - SÓ EXTISTEM 7 DIAS NA SEMANA");
                                        break;
                                }
                                out.print("</option>");
                            }
                        }
                    %>
                </select>
            </div>
        </div>
    </div>

    <div class="col-4">
        <%
            out.print("<button onclick=\"addRow();\" type=\"submit\" class=\"btn btn-success position-absolute\" style=\"right: 4%; bottom: 0\">Adicionar Linha</button>");
        %>
    </div>
</div>

    <table class="mt-4 table table-striped table-bordered" id="workoutTable">
        <thead>
        <tr>
            <th scope="col" style="width: 1%;"></th>
            <th scope="col" style="width: 21.5%;">Tarefa</th>
            <th scope="col" style="width: 10%;">Peso (Kg)</th>
            <th scope="col" style="width: 10%;">Séries</th>
            <th scope="col" style="width: 17.5%;">Repetições ou Tempo</th>
            <th scope="col" style="width: 17.5%;">Tempo entre Séries</th>
            <th scope="col" style="width: 22.5%;">Equipamento</th>
        </tr>
        </thead>
        <tbody id="workoutTableBody">
        <tr id="1">
            <td><button class="btn btn-danger text-white" onclick="removeRow(1)">X</button>
            <td>
                <select class="custom-select" name="task1" required>
                    <option value="corrida">Corrida</option>
                    <option value="levantarPesos">Levantar Pesos</option>
                    <option value="abdominais">Abdominais</option>
                </select>
            </td>
            <td><input type="number" pattern="[0-9]+(\.)*[0-9]*" min="0" class="form-control" name="weight1" placeholder="Ex: 5" required></td>
            <td><input type="number" pattern="[0-9]+" min="0" class="form-control" name="nSerie1" placeholder="Ex: 3" required></td>
            <td>
                <div class="d-inline-flex">
                    <input type="number" pattern="[0-9]+" min="0" class="form-control" name="duration1" placeholder="Ex: 5" required>
                    <select class="ml-1 custom-select" name="durationType" required>
                        <option value="seg">seg</option>
                        <option value="min">min</option>
                        <option value="vezes">vezes</option>
                    </select>
                </div>
            </td>
            <td>
                <div class="d-inline-flex">
                    <input type="number" pattern="[0-9]+" min="0" class="form-control" name="rest1" placeholder="Ex: 5" required>
                    <select class="ml-1 custom-select" name="restType" required>
                        <option value="seg">seg</option>
                        <option value="min">min</option>
                    </select>
                </div>
            </td>
            <td><input type="text" class="form-control" name="equipment1" placeholder="Ex: passadeira"></td>
        </tr>
        </tbody>
    </table>

    <div class="row mt-5 position-relative">
        <input id="tableSize" type="hidden" value="1" name="tableSize" />
            <%
                out.print("<button type=\"button\" class=\"btn btn-danger position-absolute\" onclick=\"clearWorkoutsTable()\" style=\"right: 0; transform: translateX(-115%);\">Cancelar Workout</button>");
                if (week != null && week.workouts.size() == r.workoutPerWeek)  out.print("<button disabled type=\"submit\" class=\"btn btn-success position-absolute\" style=\"right: 0\">Adicionar Workout</button>");
                else out.print("<button name=\"action\" value=\"addWorkout\" type=\"submit\" class=\"btn btn-success position-absolute\" style=\"right: 0\">Adicionar Workout</button>");
            %>
        </h4>
    </div>

</form>

<% }

    else {
        out.print("<div class=\"justify-content-center my-5\"><h4 class=\"text-center\">Já esgotou os dias disponíveis.</h4></div>");
    }

%>

<script>
    var index = 2;

    function clearWorkoutsTable() {
        // index = 2;
        //TODO falta fazer isto
    }

    function addRow() {
        var table = document.getElementById("workoutTable");
        var len = table.tBodies[0].rows.length + 1;
        var row = table.insertRow(len);

        //  max id to get params in controller
        var tableSize = document.getElementById("tableSize")
        tableSize.value = index

        row.id = index

        var cellRemove = row.insertCell(0)
        cellRemove.innerHTML = "<td><button class=\"btn btn-danger text-white\" onclick=\"removeRow(" + index + ")\">X</button>"

        var cellTaskName = row.insertCell(1);
        cellTaskName.innerHTML = "<select class=\"custom-select\" name=\"task" + index + "\"required>\n" +
            "                <option value=\"corrida\">Corrida</option>\n" +
            "                <option value=\"levantarPesos\">Levantar Pesos</option>\n" +
            "                <option value=\"abdominais\">Abdominais</option>\n" +
            "            </select>";
        
        var cellWeight = row.insertCell(2);
        cellWeight.innerHTML = "<input type=\"number\" pattern=\"[0-9]+(\\.)*[0-9]*\" min=\"0\" class=\"form-control\" name=\"weight" + index + "\" placeholder=\"Ex: 5\" required>";

        var cellNserie = row.insertCell(3);
        cellNserie.innerHTML = "<input type=\"number\" pattern=\"[0-9]+\" min=\"0\" class=\"form-control\" name=\"nSerie" + index + "\"placeholder=\"Ex: 3\" required>";

        var cellDuration = row.insertCell(4);
        cellDuration.innerHTML = "<div class=\"d-inline-flex\">\n" +
            "                <input type=\"number\" pattern=\"[0-9]+\" min=\"0\" class=\"form-control\" name=\"duration" + index + "\"placeholder=\"Ex: 5\" required>\n" +
            "                <select class=\"ml-1 custom-select\" name=\"durationType\" required>\n" +
            "                    <option value=\"seg\">seg</option>\n" +
            "                    <option value=\"min\">min</option>\n" +
            "                    <option value=\"vezes\">vezes</option>\n" +
            "                </select>\n" +
            "            </div>";

        var cellRest = row.insertCell(5);
        cellRest.innerHTML = "<div class=\"d-inline-flex\">\n" +
            "                <input type=\"number\" pattern=\"[0-9]+\" min=\"0\" class=\"form-control\" name=\"rest" + index + "\" placeholder=\"Ex: 5\" required>\n" +
            "                <select class=\"ml-1 custom-select\" name=\"restType\" required>\n" +
            "                    <option value=\"seg\">seg</option>\n" +
            "                    <option value=\"min\">min</option>\n" +
            "                </select>\n" +
            "            </div>";

        var cellEquipment = row.insertCell(6);
        cellEquipment.innerHTML = "<input type=\"text\" class=\"form-control\" name=\"equipment" + index + "\" placeholder=\"Ex: passadeira\">";
        index++
    }
    function removeRow(id) {
        if(document.getElementById("workoutTable").tBodies[0].rows.length != 1)
            $("#" + id).remove();
    }
</script>
