<%@ page import="core.Request" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.ref.ReferenceQueue" %>
<%@ page import="utils.Utils" %><%--
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
                    <h5 class="modal-title" id="title-pop-up"></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p id="message"></p>
                    <table id="bio" class="table table-striped">
                        <tr>
                            <th>dados atualizados em</th>
                            <td id="date"></td>
                        </tr>
                        <tr>
                            <th>email</th>
                            <td id="email"></td>
                        </tr>
                        <tr>
                            <th>IMC</th>
                            <td id="bmi"></td>
                        </tr>
                        <tr>
                            <th>sex</th>
                            <td id="sex"></td>
                        </tr>
                        <tr>
                            <th>idade</th>
                            <td id="age"></td>
                        </tr>
                        <tr>
                            <th>altura</th>
                            <td id="height"></td>
                        </tr>
                        <tr>
                            <th>peso</th>
                            <td id="weight"></td>
                        </tr>
                        <tr>
                            <th>pulso</th>
                            <td id="wrist"></td>
                        </tr>
                        <tr>
                            <th>peito</th>
                            <td id="chest"></td>
                        </tr>
                        <tr>
                            <th>tricep</th>
                            <td id="tricep"></td>
                        </tr>
                        <tr>
                            <th>cintura</th>
                            <td id="waist"></td>
                        </tr>
                        <tr>
                            <th>quadricep</th>
                            <td id="quadricep"></td>
                        </tr>
                        <tr>
                            <th>gémeo</th>
                            <td id="twin"></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer justify-content-center">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Voltar</button>
                    <form>
                        <button id="aceitar" type="button" class="btn btn-success" formaction="${pageContext.request.contextPath}\MyRequestsPT" name="action" value="accepted">Aceitar</button>
                        <button id="rejeitar" type="button" class="btn btn-danger" formaction="${pageContext.request.contextPath}\MyRequestsPT" name="action" value="reject">Rejeitar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
        <%
            List<Request> requests = (List) request.getAttribute("requests");
            if(requests.size() != 0){%>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <!--<th scope="col">#RequestId</th>-->
                        <th scope="col">Perfil do Cliente</th>
                        <th scope="col">Username</th>
                        <th scope="col">Semanas disponíveis</th>
                        <th scope="col">Objetivo</th>
                        <th scope="col">Workouts por semana</th>
                        <th scope="col">Disponibilidade (dias da semana)</th>
                        <th scope="col">Nível de treinoo</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>

                <%

                for(Request req : requests){
                %>
                <tr>
                    <th>
                        <button onclick="getClient('<%out.print(Utils.PROTOCOL);%>', '<%out.print(Utils.SERVER_URL);%>', '<%out.print(Utils.SERVER_PORT);%>', '<%out.print(Utils.SERVER_CONTROLLER);%>','${sessionScope.username}', '${sessionScope.token}', <% out.print((req.ID));%>, '<% out.print((req.clientUsername));%>');" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">perfil</button>
                    </th>
                    <th>@<% out.print(req.clientUsername); %></th>
                    <th><% out.print(req.numberOfWeeks); %></th>
                    <th><% out.print(req.objective); %></th>
                    <th><% out.print(req.workoutPerWeek); %></th>
                    <th><% out.print(Utils.prettyPrintWeekDays(req.weekDays)); %></th>
                    <th><% out.print(req.level); %></th>
                    <form method="POST" action="${pageContext.request.contextPath}/MyRequestsPT">
                        <input type="hidden" value="<% out.print(req.ID);%>" name="requestId" />
                        <input type="hidden" value="<% out.print(req.clientUsername);%>" name="clientUsername" />
                        <th><button type="submit" class="btn btn-success" name="action" value="accepted">Aceitar</button></th>
                        <th><button type="submit" class="btn btn-danger" name="action" value="reject">Rejeitar</button></th>
                    </form>
                </tr>
            <%}
            }else{%>
            <div class="justify-content-center my-5"><h4 class="text-center">Não existem pedidos para planos de treino.</h4></div>
        <% }
            %>

        </tbody>
    </table>

    <script type="text/javascript">

        function getClient(protocol, ip, port, controller, username, token, id, clientUsername) {

            var path = "/" + controller + "/api/"

            $.ajax({
                url: protocol + '://' + ip + ':' + port + path + 'getClientProfileByPersonalTrainer',
                cache: false,
                async: false,
                method: 'POST',
                dataType: 'json',
                data: JSON.stringify({
                    username: username,
                    token: token,
                    clientUsername: clientUsername
                }),
                success: function(jsonResponse) {
                    var message = " --- "
                    data = jsonResponse.data
                    $('#title-pop-up').html("Dados de " + data.name + " (@" + clientUsername + ")")
                    $("#date").html(data.date)

                    if(data.email == "" || data.email == null) $("#email").html(message)
                    else $("#email").html(data.email)

                    if(data.BMI == 0 || data.BMI == null)$("#bmi").html(message)
                    else $("#bmi").html(data.BMI)

                    if(data.sex == "" || data.sex == null)$("#sex").html(message)
                    else $("#sex").html(data.sex)

                    if(data.age == 0 || data.age == null)$("#age").html(message)
                    else $("#age").html(data.age)

                    if(data.height == 0 || data.height == null)$("#height").html(message)
                    else $("#height").html(data.height)

                    if(data.weight == 0 || data.weight == null)$("#weight").html(message)
                    else $("#weight").html(data.weight)

                    if(data.wrist == 0 || data.wrist == null)$("#wrist").html(message)
                    else $("#wrist").html(data.wrist)

                    if(data.chest == 0 || data.chest == null)$("#chest").html(message)
                    else $("#chest").html(data.chest)

                    if(data.tricep == 0 || data.tricep == null)$("#tricep").html(message)
                    else $("#tricep").html(data.tricep)

                    if(data.waist == 0 || data.waist == null)$("#waist").html(message)
                    else $("#waist").html(data.waist)

                    if(data.quadricep == 0 || data.quadricep == null)$("#quadricep").html(message)
                    else $("#quadricep").html(data.quadricep)

                    if(data.twin == 0 || data.twin == null)$("#twin").html(message)
                    else $("#twin").html(data.twin)

                    if(data.BMI == 0 || data.BMI == null)$("#bmi").html(message)
                    else $("#bmi").html(data.IMC)
                },
                error: function () {
                    $("#aceitar").css("display", "none")
                    $("#rejeitar").css("display", "none")
                    $("#bio").css("display", "none")
                    $("#title-pop-up").html("Erro interno do sistema")
                    $("#message").html("Não foi possível obter o perfil do cliente. Tente mais tarde ou contacte o suporte.")
                }
            })
        }
    </script>
