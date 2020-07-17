<%@ page import="core.Week" %>
<%@ page import="core.Workout" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.resource.spi.work.Work" %>
<%@ page import="core.WorkoutComparatorByDate" %>
<%@ page import="core.BiometricData" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<h4 class="mt-4">Semana ${requestScope.week.number}</h4>

<table class="mt-5 table table-bordered">
    <thead>
        <tr class="table-active">
            <%
                Week week = (Week) request.getAttribute("week");
                BiometricData biometricData = (BiometricData) request.getAttribute("biometricData");

                Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                cal.setTime(week.initialDate);
                week.workouts.sort(new WorkoutComparatorByDate()); // DO NOT REMOVE (very important!!!!!!!!)
                Workout[] workouts = new Workout[7];

                for(int i=0, k=0; i<7; i++) {
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    out.print("<th class=\"text-center\">Dia " + ((week.number-1) * 7 + (i+1)) + " - " + day + "/" + month + "</th>");

                    /* this code creates an array of workouts with each workout in the right index position related to it's week day
                     * this makes the next step (printing table body) much easier */
                    Workout workout;
                    if (k < week.workouts.size()) workout = week.workouts.get(k);
                    else workout = null;
                    if (workout != null && workout.date.equals(cal.getTime())) {
                        workouts[i] = workout;
                        k++;
                    }
                    else workouts[i] = null;
                    cal.add(Calendar.DATE, 1); // adds 1 day to current Calendar
                }
            %>
        </tr>
    </thead>
    <tbody>

        <%
            // prints workout designation
            out.print("<tr>");
            for(Workout workout: workouts) {
                if (workout != null) out.print("<td class=\"text-center\">" + workout.designation + "</td>");
                else out.print("<td class=\"text-center\">-</td>");
            }
            out.print("</tr>");

            // prints buttons
            out.print("<tr>");
            for(Workout workout: workouts) {
                if (workout != null) {
                    if (workout.done) out.print("<td class=\"text-center\"><button type=\"button\" class=\"btn btn-danger\">workout feito</button></td>");
                    else out.print("<td class=\"text-center\"><button type=\"button\" class=\"btn btn-primary\">consultar workout</button></td>");
                }
                else out.print("<td class=\"text-center\">-</td>");
            }
            out.print("</tr>");
        %>

    </tbody>
</table>

<div class="row mt-5">
    <h4>Dados biométricos:</h4>
    <span class="mt-1">&nbsp; (atualizados em
    <%
        cal.setTime(biometricData.date);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);
        out.print(day + "/" + month + "/" + year);
    %>)
    </span>
</div>

<table class="mt-4 table table-borderless">
    <tbody>
        <tr>
            <td>Altura (cm):
                <%
                    String msg = "sem informação registada";
                    if (biometricData.height == 0) out.print(msg);
                    else out.print(biometricData.height);
                %>
            </td>
            <td>Gémeo (cm):
                <%
                    if (biometricData.twin == 0) out.print(msg);
                    else out.print(biometricData.twin);
                %>
            </td>
        </tr>
        <tr>
            <td>Peso (Kg):
                <%
                    if (biometricData.weight == 0) out.print(msg);
                    else out.print(biometricData.weight);
                %>
            </td>
            <td>Quadrícep (cm):
                <%
                    if (biometricData.quadricep == 0) out.print(msg);
                    else out.print(biometricData.quadricep);
                %>
            </td>
        </tr>
        <tr>
            <td>Cintura (cm):
                <%
                    if (biometricData.waist == 0) out.print(msg);
                    else out.print(biometricData.waist);
                %>
            </td>
            <td>Trícep (cm):
                <%
                    if (biometricData.tricep == 0) out.print(msg);
                    else out.print(biometricData.tricep);
                %>
            </td>
        </tr>
        <tr>
            <td>Peito (cm):
                <%
                    if (biometricData.chest == 0) out.print(msg);
                    else out.print(biometricData.chest);
                %>
            </td>
            <td>Pulso (cm):
                <%
                    if (biometricData.wrist == 0) out.print(msg);
                    else out.print(biometricData.wrist) ;
                %>
            </td>
        </tr>
        <tr>
            <td colspan="2">Índice de Massa Corporal:
                <%
                    out.print(String.format("%.01f", biometricData.BMI));
                %>

                <!-- prints BMI categories on the progress bar -->
                <div class="mt-4 progress" style="height:25px">
                    <div class="progress-bar <% if (biometricData.BMI < 18.5) out.print("progress-bar-striped progress-bar-animated"); %>" role="progressbar" style="width: 16.6%" aria-valuenow="16.6" aria-valuemin="0" aria-valuemax="100">subnutrido</div>
                    <div class="progress-bar <% if (biometricData.BMI >= 18.5 && biometricData.BMI < 25) out.print("progress-bar-striped progress-bar-animated"); %> bg-success" role="progressbar" style="width: 16.6%" aria-valuenow="16.6" aria-valuemin="0" aria-valuemax="100">saudável</div>
                    <div class="progress-bar <% if (biometricData.BMI >= 25 && biometricData.BMI < 30) out.print("progress-bar-striped progress-bar-animated"); %> bg-warning text-dark" role="progressbar" style="width: 16.6%" aria-valuenow="16.6" aria-valuemin="0" aria-valuemax="100">sobrepeso</div>
                    <div class="progress-bar <% if (biometricData.BMI >= 30 && biometricData.BMI < 35) out.print("progress-bar-striped progress-bar-animated"); %>" role="progressbar" style="width: 16.6%; background-color: rgb(235, 100, 0);" aria-valuenow="16.6" aria-valuemin="0" aria-valuemax="100">obesidade grau I</div>
                    <div class="progress-bar <% if (biometricData.BMI >= 35 && biometricData.BMI < 40) out.print("progress-bar-striped progress-bar-animated"); %>" role="progressbar" style="width: 16.6%; background-color: rgb(180, 50, 0);" aria-valuenow="16.6" aria-valuemin="0" aria-valuemax="100">obesidade grau II (severa)</div>
                    <div class="progress-bar <% if (biometricData.BMI >= 40) out.print("progress-bar-striped progress-bar-animated"); %>" role="progressbar" style="width: 17%; background-color: rgb(255, 0, 0);" aria-valuenow="17" aria-valuemin="0" aria-valuemax="100">obesidade grau III (morbida)</div>
                </div>

                <!-- prints BMI values -->
                <div class="row mt-2 ml-4 text-right" style="width: 100%">
                    <div class="col" style="width: 14.3%">18.5</div>
                    <div class="col" style="width: 14.3%">25</div>
                    <div class="col" style="width: 14.3%">30</div>
                    <div class="col" style="width: 14.3%">35</div>
                    <div class="col" style="width: 14.3%">40</div>
                    <div class="col" style="width: 14.2%"></div>
                </div>

            </td>
        </tr>
    </tbody>
</table>


<div class="row mt-5">
    <button type="button" class="p-3 btn btn-primary" disabled>Avaliar Personal Trainer</button>
    <span class="ml-4 mt-3">*Poderá avaliar o Personal Trainer após a semana X.</span>
</div>