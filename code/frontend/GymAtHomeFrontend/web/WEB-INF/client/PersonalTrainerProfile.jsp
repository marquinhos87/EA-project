<%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">

</div>
<div class="row justify-content-center">
    <div class="col-md-6">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th style="width: 25%;">Estatísticas PT</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Classificação</td>
                    <td>${requestScope.classification}</td>
                </tr>
                <tr>
                    <td>Nº de avaliações</td>
                    <td>${requestScope.nClassifications}</td>
                </tr>
                <tr>
                    <td>Nº de clientes</td>
                    <td>${requestScope.nClients}</td>
                </tr>
                <tr>
                    <td>Nº de Planos Criados</td>
                    <td>${requestScope.nPlans}</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<div class="row justify-content-end">
    <div class="col-md-3">
        <button type="submit" class="">Requisitar Plano</button>
    </div>
</div>