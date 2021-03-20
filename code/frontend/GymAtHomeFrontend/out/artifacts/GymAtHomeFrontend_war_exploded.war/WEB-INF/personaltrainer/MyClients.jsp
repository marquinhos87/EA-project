<%@ page import="core.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="utils.Utils" %><%--
  Created by IntelliJ IDEA.
  User: jose
  Date: 19/07/20
  Time: 15:28
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
                        <th>última atualização</th>
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
            </div>
        </div>
    </div>
</div>
<%
    List<Client> clients = (List) request.getAttribute("clients");
    if(clients.size() != 0){
%>
<table class="table table-striped">
    <thead>
    <tr>
        <!--<th scope="col">#RequestId</th>-->
        <th scope="col">Perfil do Cliente</th>
        <th scope="col">Username</th>
        <th scope="col">Avaliações</th>
        <th scope="col">Ver plano atual</th>
    </tr>
    </thead>
    <tbody>

    <%

        for(Client client : clients){
    %>
    <tr>
        <td>
            <button onclick="getClient('<%out.print(Utils.PROTOCOL);%>', '<%out.print(Utils.SERVER_URL);%>', '<%out.print(Utils.SERVER_PORT);%>', '<%out.print(Utils.SERVER_CONTROLLER);%>','${sessionScope.username}', '${sessionScope.token}', '<% out.print((client.username));%>');" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">perfil</button>
        </td>
        <td>@<% out.print(client.username); %></td>
        <td>
            <%
                if(client.submitedClassification)out.print("sim"); else out.print("não");
            %>
        </td>
        <%
            String viewPlanLink = "onclick=\"window.location.href='" + request.getContextPath() + "/ClientPlanByPT'\"";
            session.setAttribute("clientUsername", client.username);
        %>
        <td><button class="btn btn-success" <% out.print(viewPlanLink); %> >Ver plano</button></td>
    </tr>
    <%}
    }else{%>
    <div class="justify-content-center my-5"><h4 class="text-center">Não tem clientes ativos.</h4></div>
    <% }
    %>

    </tbody>
</table>

<script type="text/javascript">

    function getClient(protocol, ip, port, controller, username, token, clientUsername) {

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

                console.log(jsonResponse)

                var message = " --- "
                data = jsonResponse.data
                $('#title-pop-up').html("Dados de " + data.name + " (@" + clientUsername + ")")

                var bio = data.biometricData

                if(data.email == "" || data.email == null) $("#email").html(message)
                else $("#email").html(data.email)

                if(bio.BMI == 0 || bio.BMI == null)$("#bmi").html(message)
                else $("#bmi").html(bio.BMI.toFixed(2))

                if(data.sex == "" || data.sex == null)$("#sex").html(message)
                else {
                    if(data.sex == "m")$("#sex").html("Masculino")
                    else if(data.sex == "f")$("#sex").html("Feminino")
                    else if(data.sex == "o")$("#sex").html("Masculino")
                    else $("#sex").html("---")
                }

                if(data.age == 0 || data.age == null)$("#age").html(message)
                else $("#age").html(data.age + " anos")

                //  biometric data

                if(bio.date == "" || bio.date == null) $("#date").html(message)
                else $("#date").html(bio.date)

                if(bio.height == 0 || bio.height == null)$("#height").html(message)
                else $("#height").html(bio.height + " cm")

                if(bio.weight == 0 || bio.weight == null)$("#weight").html(message)
                else $("#weight").html(bio.weight + " Kg")

                if(bio.wrist == 0 || bio.wrist == null)$("#wrist").html(message)
                else $("#wrist").html(bio.wrist + " cm")

                if(bio.chest == 0 || bio.chest == null)$("#chest").html(message)
                else $("#chest").html(bio.chest + " cm")

                if(bio.tricep == 0 || bio.tricep == null)$("#tricep").html(message)
                else $("#tricep").html(bio.tricep + " cm")

                if(bio.waist == 0 || bio.waist == null)$("#waist").html(message)
                else $("#waist").html(bio.waist)

                if(bio.quadricep == 0 || bio.quadricep == null)$("#quadricep").html(message)
                else $("#quadricep").html(bio.quadricep)

                if(bio.twin == 0 || bio.twin == null)$("#twin").html(message)
                else $("#twin").html(bio.twin)
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

