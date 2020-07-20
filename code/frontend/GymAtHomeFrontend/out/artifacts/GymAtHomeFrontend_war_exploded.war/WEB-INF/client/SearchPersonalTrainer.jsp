<%@ page import="core.PersonalTrainer" %>
<%@ page import="java.util.Collection" %>
<%@ page import="utils.Utils" %>
<%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                <form class="d-inline-flex" method="get" action="${pageContext.request.contextPath}\MakeRequest">
                    <button type="button" class="btn btn-secondary mr-2" data-dismiss="modal">Voltar</button>
                    <button type="submit" id="personalTrainerUsername" name="PTUsername" value="" class="btn btn-primary text-white font-weight-normal border-0">Requisitar Plano</button>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <form class="mt-2" method="post" action="${pageContext.request.contextPath}\SearchPersonalTrainer">
            <div class="form-group row">
                <div class="col-md-3">
                    <input type="number" pattern="\d" min="18" class="form-control" name="minage" placeholder="Idade mínima">
                </div>
                <div class="col-md-3">
                    <input type="number" pattern="\d" min="18" class="form-control" name="minage" placeholder="Idade máxima">
                </div>
                <div class="col-md-3">
                    <input type="number" pattern="\d+(\.\d\d|\.\d|)" min="0" class="form-control" name="minprice" placeholder="Preço mínimo">
                </div>
                <div class="col-md-3">
                    <input type="number" pattern="\d+(.\d\d|\.\d|)" min="0" class="form-control" name="maxprice" placeholder="Preço máximo">
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-3">
                    <select class="custom-select" name="skill">
                        <option value="cardio">Cardio</option>
                        <option value="musculacao">Musculação</option>
                        <option value="ambos">Ambos</option>
                        <option value="q" selected>Categoria</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <select class="custom-select" name="genre">
                        <option value="m">Masculino</option>
                        <option value="f">Feminino</option>
                        <option value="o">Outro</option>
                        <option value="q" selected>Género</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <select class="custom-select" name="order">
                        <option value="pa">Preço ascendente</option>
                        <option value="pd">Preço descendente</option>
                        <option value="ca">Classificação ascendente</option>
                        <option value="cd">Classificação descendente</option>
                        <option value="ia">Idade ascendente</option>
                        <option value="id">Idade descendente</option>
                        <option value="q" selected>Ordenar por</option>
                    </select>
                </div>
                <div class="col-md-3">
                    <input type="number" pattern="\d" min="0" max="5" class="form-control" name="classification" placeholder="Classificação mínima (0-5)">
                </div>
            </div>
            <div class="form-group row justify-content-end">
                <div class="col-md-3">
                    <button type="submit" class="btn btn-primary btn-block text-white font-weight-normal border-0">Procurar</button>
                </div>
            </div>
        </form>
        <% if(request.getAttribute("personalTrainers") != null && ((Collection<PersonalTrainer>)request.getAttribute("personalTrainers")).size()!=0) {%>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <td style="width: 12.5%">Perfil do PersonalTrainer</td>
                        <td style="width: 10%">Username</td>
                        <td style="width: 18.5%">Nome</td>
                        <td style="width: 9%">Categoria</td>
                        <td style="width: 9%">Género</td>
                        <td style="width: 8%">Idade</td>
                        <td style="width: 9%">Classificação</td>
                        <td style="width: 8%">Preço</td>
                        <td style="width: 16%">Requisitar Plano</td>
                    </tr>
                </thead>
                <tbody>
                    <%
                        Collection<PersonalTrainer> pts = (Collection<PersonalTrainer>) request.getAttribute("personalTrainers");
                        for(PersonalTrainer pt: pts) {
                            out.print("<tr>");
                            out.print("<td><button onclick=\"getPT('" + Utils.PROTOCOL + "', '" + Utils.SERVER_URL + "', '" + Utils.SERVER_PORT + "', '" + Utils.SERVER_CONTROLLER + "','" + session.getAttribute("username") + "', '" + session.getAttribute("token") + "', '" + pt.username +"');\" type=\"button\" class=\"btn btn-primary\" data-toggle=\"modal\" data-target=\"#exampleModalCenter\">Perfil</button></td>");
                            out.print("<td>@" + pt.username + "</td>");
                            out.print("<td>" + pt.name + "</td>");
                            out.print("<td>" + pt.skill + "</td>");
                            out.print("<td>" + Utils.prettyPrintGenre(pt.sex) + "</td>");
                            out.print("<td>" + pt.age + "</td>");
                            out.print("<td>" + pt.classification + "</td>");
                            out.print("<td>" + pt.price + "€</td>");
                            out.print("<td><form class=\"d-inline-flex\" method=\"get\" action=\"" + request.getContextPath() + "\\MakeRequest\">\n" +
                                    "                    <button type=\"submit\" id=\"personalTrainerUsername\" name=\"PTUsername\" value=\"" + pt.username + "\" class=\"btn btn-primary text-white font-weight-normal border-0\">Requisitar Plano</button>\n" +
                                    "                </form></td>");
                            out.print("</tr>");
                        }
                    %>
                </tbody>
            </table>
        <%} else if(request.getAttribute("errorMessage") != null) {%>
            <div class="alert alert-warning" role="alert">
                <h4 class="alert-heading">Atualmente sem Personal Trainers.</h4>
                <p>${requestScope.error}</p>
            </div>
        <%}%>
    </div>
</div>

<script type="text/javascript">

    function getPT(protocol, ip, port, controller, username, token, personalTrainerUsername) {

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

                var PTUsername = document.getElementById("personalTrainerUsername")
                PTUsername.value = personalTrainerUsername
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