<%@ page import="core.Request" %>
<%@ page import="utils.Utils" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: jose
  Date: 19/07/20
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="title-pop-up"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p id="message"></p>
                <table id="bio" class="table table-striped">
                    <tr>
                        <th>Email</th>
                        <td id="email"></td>
                    </tr>
                    <tr>
                        <th>Género</th>
                        <td id="sex"></td>
                    </tr>
                    <tr>
                        <th>Especialidade</th>
                        <td id="skill"></td>
                    </tr>
                    <tr>
                        <th>Preço</th>
                        <td id="price"></td>
                    </tr>
                    <tr>
                        <th>Classificação</th>
                        <td id="classification"></td>
                    </tr>
                    <tr>
                        <th>Nº de Classificações</th>
                        <td id="nClassifications"></td>
                    </tr>
                    <tr>
                        <th>Nº de Clientes</th>
                        <td id="nClients"></td>
                    </tr>
                    <tr>
                        <th>Nº de Planos</th>
                        <td id="nPlans"></td>
                    </tr>

                </table>
            </div>
            <div class="modal-footer justify-content-center">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Voltar</button>
            </div>
        </div>
    </div>
</div>
<%
    List<Request> requests = (List) request.getAttribute("requests");
    if(requests != null && requests.size() != 0){%>
<table class="table table-striped">
    <thead>
    <tr>
        <!--<th scope="col">#RequestId</th>-->
        <th scope="col">Perfil do PersonalTrainer</th>
        <th scope="col">Username</th>
        <th scope="col">Semanas disponíveis</th>
        <th scope="col">Objetivo</th>
        <th scope="col">Workouts por semana</th>
        <th scope="col">Disponibilidade (dias da semana)</th>
        <th scope="col">Nível de treino</th>
        <th scope="col">Estado</th>
    </tr>
    </thead>
    <tbody>

    <%

        for(Request req : requests){
    %>
    <tr>
        <td>
            <button onclick="getPT('<%out.print(Utils.PROTOCOL);%>', '<%out.print(Utils.SERVER_URL);%>', '<%out.print(Utils.SERVER_PORT);%>', '<%out.print(Utils.SERVER_CONTROLLER);%>','${sessionScope.username}', '${sessionScope.token}', <% out.print((req.ID));%>, '<% out.print((req.personalTrainerUsername));%>');" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">perfil</button>
        </td>
        <td>@<% out.print(req.personalTrainerUsername); %></td>
        <td><% out.print(req.numberOfWeeks); %></td>
        <td><% out.print(req.objective); %></td>
        <td><% out.print(req.workoutPerWeek); %></td>
        <td><% out.print(Utils.prettyPrintWeekDays(req.weekDays)); %></td>
        <td><%
            switch(req.level){
                case 0:
                    out.print("Fácil");
                    break;
                case 1:
                    out.print("Normal");
                    break;
                case 2:
                    out.print("Díficil");
                    break;
                case 3:
                    out.print("Extremo");
                    break;
            }
            %>
        </td>
            <% if(req.state == -1) {
                out.print("<td style=\"color:red;\">Rejeitado</td>");
            }else if (req.state == 1){
                out.print("<td style=\"color:green;\">Aceite</td>");
            }else if (req.state == 0){
                out.print("<td style=\"color:orange;\">Pendente</td>");
            }%>
    </tr>
    <%}
    }else{%>
    <div class="justify-content-center my-5"><h4 class="text-center">Não existem pedidos para planos de treino.</h4></div>
    <% }
    %>

    </tbody>
</table>

<script type="text/javascript">

    function getPT(protocol, ip, port, controller, username, token, id, personalTrainerUsername) {

        var path = "/" + controller + "/api/"

        $.ajax({
            url: protocol + '://' + ip + ':' + port + path + 'getPersonalTrainerProfileByClient',
            cache: false,
            async: false,
            method: 'POST',
            dataType: 'json',
            data: JSON.stringify({
                username: username,
                token: token,
                personalTrainerUsername: personalTrainerUsername
            }),
            success: function(jsonResponse) {
                var message = " --- "
                data = jsonResponse.data
                $('#title-pop-up').html("Dados de " + data.name + " (@" + personalTrainerUsername + ")")

                if(data.email == 0 || data.email == null)$("#email").html(message)
                else $("#email").html(data.email)

                if(data.sex == "" || data.sex == null)$("#sex").html(message)
                else $("#sex").html(data.sex)

                if(data.skill == 0 || data.skill == null)$("#skill").html(message)
                else $("#skill").html(data.skill)

                if(data.price == 0 || data.price == null)$("#price").html(message)
                else $("#price").html(data.price + "€")

                if(data.classification == null)$("#classifications").html(message)
                else $("#classification").html(data.classification)

                if(data.nClassifications == null)$("#nClassifications").html(message)
                else $("#nClassifications").html(data.nClassifications)

                if(data.nClients == null)$("#nClients").html(message)
                else $("#nClients").html(data.nClients)

                if(data.nPlans == null)$("#nPlans").html(message)
                else $("#nPlans").html(data.nPlans)
            },
            error: function () {
                $("#aceitar").css("display", "none")
                $("#rejeitar").css("display", "none")
                $("#bio").css("display", "none")
                $("#title-pop-up").html("Erro interno do sistema")
                $("#message").html("Não foi possível obter o perfil do personal trainer. Tente mais tarde ou contacte o suporte.")
            }
        })
    }
</script>

