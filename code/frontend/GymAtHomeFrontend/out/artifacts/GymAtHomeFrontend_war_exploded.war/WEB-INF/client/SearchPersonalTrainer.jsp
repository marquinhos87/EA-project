<%@ page import="core.PersonalTrainer" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-md-12">
        <form class="mt-2" method="post" action="${pageContext.request.contextPath}\SearchPersonalTrainer">
            <div class="form-group row">
                <div class="col-md-2">
                    <select class="custom-select" name="skill">
                        <option value="c">Cardio</option>
                        <option value="m">Musculação</option>
                        <option value="a">Ambos</option>
                        <option value="q" selected>Categoria</option>
                    </select>
                </div>
                <div class="col-md-2">
                    <select class="custom-select" name="genre">
                        <option value="m">Masculino</option>
                        <option value="f">Feminino</option>
                        <option value="o">Outro</option>
                        <option value="q" selected>Género</option>
                    </select>
                </div>
                <div class="col-md-1">
                    <input type="number" pattern="\d" min="18" class="form-control" name="minage" placeholder="Idade mínima">
                </div>
                <div class="col-md-1">
                    <input type="number" pattern="\d" min="18" class="form-control" name="minage" placeholder="Idade máxima">
                </div>
                <div class="col-md-1">
                    <input type="number" pattern="\d" min="0" max="5" class="form-control" name="maxage" placeholder="Classificação mínima (0-5)">
                </div>
                <div class="col-md-1">
                    <input type="number" pattern="\d+(\.\d\d|\.\d|)" min="0" class="form-control" name="minprice" placeholder="Preço mínimo">
                </div>
                <div class="col-md-1">
                    <input type="number" pattern="\d+(.\d\d|\.\d|)" min="0" class="form-control" name="maxprice" placeholder="Preço máximo">
                </div>
                <div class="col-md-2">
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
                <div class="col-md-1">
                    <button type="submit" class="btn btn-primary btn-block text-white font-weight-normal border-0">Procurar</button>
                </div>
            </div>
        </form>
        <% if(request.getAttribute("personalTrainers") != null) {%>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <td style="width: 15%">Username</td>
                        <td style="width: 35%">Nome</td>
                        <td style="width: 10%">Categoria</td>
                        <td style="width: 10%">Género</td>
                        <td style="width: 10%">Idade</td>
                        <td style="width: 10%">Classificação</td>
                        <td style="width: 10%">Preço</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="pt" items="${requestScope.personalTrainers}">
                        <tr onclick="document.location='${pageContext.request.contextPath + "\PersonalTrainerProfile?personalTrainerUsername=" + pt.username}';">
                            <td>${pt.username}</td>
                            <td>${pt.name}</td>
                            <td>${pt.skill}</td>
                            <td>${pt.genre}</td>
                            <td>${pt.age}</td>
                            <td>${pt.classification}</td>
                            <td>${pt.price}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        <%} else {%>
            <div class="alert alert-warning" role="alert">
                <h4 class="alert-heading">Atualmente sem Personal Trainers.</h4>
                <p>${requestScope.error}</p>
            </div>
        <%}%>
    </div>
</div>