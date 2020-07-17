<%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-6 d-flex flex-column">
        <label class="mb-3"><h4>Personal Trainer ${requestScope.personalTrainerUsername}</h4></label>
        <label>Nome: ${requestScope.personalTrainerName}</label>
        <label>Idade: ${requestScope.personalTrainerAge}</label>
        <label>Género: ${requestScope.personalTrainerGenre}</label>
        <label>Especialidade: ${requestScope.personalTrainerSkill}</label>
        <label>Preço: ${requestScope.personalTrainerPrice}€</label>
    </div>
</div>
<div class="row justify-content-center">
    <div class="col-md-8">
        <table class="table table-borderless">
            <thead>
                <tr>
                    <th style="width: 50%;">Estatísticas PT</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Classificação</td>
                    <td>${requestScope.personalTrainerClassification}</td>
                </tr>
                <tr>
                    <td>Nº de avaliações</td>
                    <td>${requestScope.personalTrainerNClassifications}</td>
                </tr>
                <tr>
                    <td>Nº de clientes</td>
                    <td>${requestScope.personalTrainerNClients}</td>
                </tr>
                <tr>
                    <td>Nº de Planos Criados</td>
                    <td>${requestScope.personalTrainerNPlans}</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<div class="row justify-content-end">
    <div class="col-md-3">
        <button type="submit" formmethod="get" formaction="${pageContext.request.contextPath}\MakeRequest" name="personalTrainerUsername" value="${requestScope.personalTrainerUsername}" class="btn btn-primary btn-block text-white font-weight-normal border-0 mb-3">Requisitar Plano</button>
    </div>
</div>