<%@ page import="core.Week" %>
<%@ page import="core.Workout" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.resource.spi.work.Work" %>
<%@ page import="core.WorkoutComparatorByDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<h4 class="mt-4">Semana ${requestScope.week.number}</h4>

<table class="mt-5 table table-bordered">
    <thead>
        <tr class="table-active">
            <%
                Week week = (Week) request.getAttribute("week");
                Calendar cal = Calendar.getInstance(TimeZone.getDefault());
                cal.setTime(week.initialDate);

                week.workouts.sort(new WorkoutComparatorByDate()); // DO NOT REMOVE (very important!!!!!!!!)
                Workout[] workouts = new Workout[7];

                for(int i=0, k=0; i<7; i++) {
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    out.print("<th class=\"text-center\">Dia X - " + day + "/" + month + "</th>");

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

<h4 class="mt-5">Dados biométricos:</h4>
<table class="mt-4 table table-borderless">
    <tbody>
        <tr>
            <td>Altura (cm): ${requestScope.biometricData.height}</td>
            <td>Gémeo (cm): ${requestScope.biometricData.twin}</td>
        </tr>
        <tr>
            <td>Peso (Kg): ${requestScope.biometricData.weight}</td>
            <td>Quadrícep (cm): ${requestScope.biometricData.quadricep}</td>
        </tr>
        <tr>
            <td>Cintura (cm): ${requestScope.biometricData.waist}</td>
            <td>Trícep (cm): ${requestScope.biometricData.tricep}</td>
        </tr>
        <tr>
            <td>Peito (cm): ${requestScope.biometricData.chest}</td>
            <td>Pulso (cm): ${requestScope.biometricData.wrist}</td>
        </tr>
    </tbody>
</table>

<button type="button" class="mt-5 p-3 btn btn-primary">Avaliar Personal Trainer</button>