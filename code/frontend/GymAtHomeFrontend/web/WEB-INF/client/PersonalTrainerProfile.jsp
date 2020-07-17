<%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-md-2"></div>
    <div class="col-md-6">
        <div class="row d-flex flex-column">
            <label class="mb-3"><h4>Personal Trainer @${requestScope.personalTrainerUsername}</h4></label>
            <div class="col ml-1 d-flex flex-column">
                <label>Nome: ${requestScope.personalTrainerName}</label>
                <label>Idade: ${requestScope.personalTrainerAge}</label>
                <label>Género: ${requestScope.personalTrainerGenre}</label>
                <label>Especialidade: ${requestScope.personalTrainerSkill}</label>
                <label>Preço: ${requestScope.personalTrainerPrice}€</label>
            </div>
        </div>
        <div class="row mt-2">
            <div class="col ml-1 d-flex flex-column">
                <label><h4>Estatísticas PT</h4></label>
                <label>Classificação: ${requestScope.personalTrainerClassification}</label>
                <label>Nº de Avaliações: ${requestScope.personalTrainerNClassifications}</label>
                <label>Nº de Clientes: ${requestScope.personalTrainerNClients}</label>
                <label>Nº de Planos Criados: ${requestScope.personalTrainerNPlans}</label>
            </div>
        </div>
        <label class="mt-2"></label>
    </div>
</div>
<div class="row justify-content-end">
    <div class="col-md-3">
        <form method="get" action="${pageContext.request.contextPath}\MakeRequest">
            <button type="submit" formmethod="get" name="personalTrainerUsername" value="${requestScope.personalTrainerUsername}" class="btn btn-primary btn-block text-white font-weight-normal border-0 mb-3">Requisitar Plano</button>
        </form>
    </div>
</div>