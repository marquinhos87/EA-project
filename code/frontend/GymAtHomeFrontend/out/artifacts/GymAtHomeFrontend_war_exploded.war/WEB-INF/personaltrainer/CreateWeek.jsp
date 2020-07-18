<%@ page import="core.Request" %>
<%@ page import="utils.Utils" %><%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="row mt-5 position-relative">
    <h4 class="mt-4">Semana 1: @${sessionScope.request.clientUsername}
    <%
        out.print("<button type=\"button\" class=\"btn btn-danger position-absolute\" style=\"right: 0; transform: translateX(-70%);\">Cancelar Semana</button>");
        out.print("<button type=\"submit\" class=\"btn btn-success position-absolute\" style=\"right: 0\" disabled>Finalizar</button>");
    %>
    </h4>
</div>

<div class="mt-5 row ">
    <div class="col-5">
        <h5>Workouts já definidos:</h5>
        <table class="mt-4 table table-striped">
            <thead>
                <tr>
                    <th scope="col">Dia</th>
                    <th scope="col">Workout</th>
                </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">---</th>
                <td>---</td>
            </tr>
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
                    <td>${sessionScope.request.workoutPerWeek} dias por semana</td>
                </tr>
                <tr>
                    <td scope="row">Disponibilidade semanal:</td>
                    <td>
                        <%
                            Request r = (Request) session.getAttribute("request");
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

<div class="row mt-3">

    <div class="mb-4 col-4">
        <div class="row d-flex flex-column">
            <div class="col">
                <h5>Designação do Workout:</h5>
            </div>
            <div class="mt-2 col">
                <input type="text" class="form-control" name="weight" placeholder="Ex: Cardio">
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
                        for (String weekDayStr: r.weekDays.split(";")) {
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
                    %>
                </select>
            </div>
        </div>
    </div>

    <div class="col-4">
        <%
            out.print("<button type=\"button\" class=\"btn btn-danger position-absolute\" style=\"right: 4%; bottom: 0; transform: translateX(-115%);\">Remover Linha</button>");
            out.print("<button onclick=\"addRow();\" type=\"submit\" class=\"btn btn-success position-absolute\" style=\"right: 4%; bottom: 0\">Adicionar Linha</button>");
        %>
    </div>
</div>


<table class="mt-4 table table-striped table-bordered" id="workoutTable">
    <thead>
    <tr>
        <th scope="col" style="width: 22.5%;">Tarefa</th>
        <th scope="col" style="width: 10%;">Peso (Kg)</th>
        <th scope="col" style="width: 10%;">Séries</th>
        <th scope="col" style="width: 17.5%;">Repetições ou Tempo</th>
        <th scope="col" style="width: 17.5%;">Tempo entre Séries</th>
        <th scope="col" style="width: 22.5%;">Equipamento</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>
            <select class="custom-select" name="task" required>
                <option value="corrida">Corrida</option>
                <option value="levantarPesos">Levantar Pesos</option>
                <option value="abdominais">Abdominais</option>
            </select>
        </td>
        <td><input type="number" pattern="[0-9]+(\.)*[0-9]*" min="0" class="form-control" name="weight" placeholder="Ex: 5"></td>
        <td><input type="number" pattern="[0-9]+" min="0" class="form-control" name="nSerie" placeholder="Ex: 3"></td>
        <td>
            <div class="d-inline-flex">
                <input type="number" pattern="[0-9]+" min="0" class="form-control" name="duration" placeholder="Ex: 5">
                <select class="ml-1 custom-select" name="durationType" required>
                    <option value="seg">seg</option>
                    <option value="min">min</option>
                    <option value="vezes">vezes</option>
                </select>
            </div>
        </td>
        <td>
            <div class="d-inline-flex">
                <input type="number" pattern="[0-9]+" min="0" class="form-control" name="rest" placeholder="Ex: 5">
                <select class="ml-1 custom-select" name="restType" required>
                    <option value="seg">seg</option>
                    <option value="min">min</option>
                </select>
            </div>
        </td>
        <td><input type="text" class="form-control" name="equipment" placeholder="Ex: passadeira"></td>
    </tr>
    </tbody>
</table>

<div class="row mt-5 position-relative">
        <%
            out.print("<button type=\"button\" class=\"btn btn-danger position-absolute\" style=\"right: 0; transform: translateX(-110%);\">Cancelar Workout</button>");
            out.print("<button type=\"submit\" class=\"btn btn-success position-absolute\" style=\"right: 0\" disabled>Guardar Workout</button>");
        %>
    </h4>
</div>

<script>
    function addRow() {
        var table = document.getElementById("workoutTable");

        var len = table.tBodies[0].rows.length + 1;
        var row = table.insertRow(len);

        var cellTaskName = row.insertCell(0);
        cellTaskName.innerHTML = "<select class=\"custom-select\" name=\"task\" required>\n" +
            "                <option value=\"corrida\">Corrida</option>\n" +
            "                <option value=\"levantarPesos\">Levantar Pesos</option>\n" +
            "                <option value=\"abdominais\">Abdominais</option>\n" +
            "            </select>";
        
        var cellWeight = row.insertCell(1);
        cellWeight.innerHTML = "<input type=\"number\" pattern=\"[0-9]+(\\.)*[0-9]*\" min=\"0\" class=\"form-control\" name=\"weight\" placeholder=\"Ex: 5\">";

        var cellNserie = row.insertCell(2);
        cellNserie.innerHTML = "<input type=\"number\" pattern=\"[0-9]+\" min=\"0\" class=\"form-control\" name=\"nSerie\" placeholder=\"Ex: 3\">";

        var cellDuration = row.insertCell(3);
        cellDuration.innerHTML = "<div class=\"d-inline-flex\">\n" +
            "                <input type=\"number\" pattern=\"[0-9]+\" min=\"0\" class=\"form-control\" name=\"duration\" placeholder=\"Ex: 5\">\n" +
            "                <select class=\"ml-1 custom-select\" name=\"durationType\" required>\n" +
            "                    <option value=\"seg\">seg</option>\n" +
            "                    <option value=\"min\">min</option>\n" +
            "                    <option value=\"vezes\">vezes</option>\n" +
            "                </select>\n" +
            "            </div>";

        var cellRest = row.insertCell(4);
        cellRest.innerHTML = "<div class=\"d-inline-flex\">\n" +
            "                <input type=\"number\" pattern=\"[0-9]+\" min=\"0\" class=\"form-control\" name=\"rest\" placeholder=\"Ex: 5\">\n" +
            "                <select class=\"ml-1 custom-select\" name=\"restType\" required>\n" +
            "                    <option value=\"seg\">seg</option>\n" +
            "                    <option value=\"min\">min</option>\n" +
            "                </select>\n" +
            "            </div>";

        var cellEquipment = row.insertCell(5);
        cellEquipment.innerHTML = "<input type=\"text\" class=\"form-control\" name=\"equipment\" placeholder=\"Ex: passadeira\">";
    }
</script>