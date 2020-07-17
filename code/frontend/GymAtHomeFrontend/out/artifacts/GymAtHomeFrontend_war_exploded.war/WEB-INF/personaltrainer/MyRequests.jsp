<%@ page import="core.Request" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.ref.ReferenceQueue" %><%--
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
                    <h5 class="modal-title" id="exampleModalLongTitle">Informações do João Costa</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <label>Peso (kg): 60 </label><br>
                    <label>Peso (kg): 60 </label><br>
                    <label>Peso (kg): 60 </label><br>
                    <label>Peso (kg): 60 </label><br>
                    <label>Peso (kg): 60 </label><br>
                    <label>Peso (kg): 60 </label><br>
                    <label>Peso (kg): 60 </label><br>
                    <label>Peso (kg): 60 </label><br>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Voltar</button>
                    <button type="button" class="btn btn-success">Aceitar</button>
                    <button type="button" class="btn btn-danger">Rejeitar</button>
                </div>
            </div>
        </div>
    </div>

    <table class="table table-striped">
    <thead>
        <tr>
            <!--<th scope="col">#RequestId</th>-->
            <th scope="col">Dados biométricos</th>
            <th scope="col">Semana disponíveis</th>
            <th scope="col">Objetivo</th>
            <th scope="col">Workouts por semana</th>
            <th scope="col">Disponibilidade (dias da semana)</th>
            <th scope="col">Nível de treino</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
    </thead>
        <tbody
        <%
            List<Request> requests = (List) request.getAttribute("requests");
            for(Request req : requests){
                %>
                <tr>
                    <th><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
                        Bio
                    </button></th>
                    <!-- <th><% out.print(req.ID); %></th> -->
                    <th><% out.print(req.numberOfWeeks); %></th>
                    <th><% out.print(req.objective); %></th>
                    <th><% out.print(req.workoutPerWeek); %></th>
                    <%
                        String[] subtr = req.weekDays.split(";");
                        StringBuilder sb = new StringBuilder("");
                        for(int j = 0; j < subtr.length; j++){
                            switch (subtr[j]){
                                case "1":
                                    sb.append("Dom");
                                    break;
                                case "2":
                                    sb.append("Seg");
                                    break;
                                case "3":
                                    sb.append("Ter");
                                    break;
                                case "4":
                                    sb.append("Qua");
                                    break;
                                case "5":
                                    sb.append("Qui");
                                    break;
                                case "6":
                                    sb.append("Sex");
                                    break;
                                case "7":
                                    sb.append("Sáb");
                                    break;
                                default:
                                    break;

                            }
                            if(j == subtr.length - 2)
                                sb.append(" e ");
                            else if(j != subtr.length - 1)
                                sb.append(", ");
                        }
                    %>
                    <th><% out.print(sb.toString()); %></th>
                    <th><% out.print(req.level); %></th>
                    <th><button type="button" class="btn btn-success">Aceitar</button></th>
                    <th><button type="button" class="btn btn-danger">Rejeitar</button></th>
             </tr>
            <%}%>

        </tbody>
    </table>
