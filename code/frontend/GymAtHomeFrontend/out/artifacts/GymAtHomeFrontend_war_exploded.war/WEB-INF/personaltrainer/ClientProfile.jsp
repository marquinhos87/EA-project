<%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-5">
        <div class="row">
            <label><h3>Cliente: ${requestScope.name}</h3></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-5">
        <div class="row">
            <label>Idade: ${requestScope.age}</label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-5">
        <div class="row">
            <label>Género: ${requestScope.genre}</label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-5">
        <div class="row">
            <label>Email: ${requestScope.email}</label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-5">
        <div class="row">
            <label><h4>Dados Biométricos</h4></label>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-5">
        <div class="row">
            <label>IMC: ${requestScope.bci}</label>
        </div>
        <div class="row">
            <label>Altura (cm): ${requestScope.height}</label>
        </div>
        <div class="row">
            <label>Peso (kg): ${requestScope.weight}</label>
        </div>
        <div class="row">
            <label>Cintura (cm): ${requestScope.waist}</label>
        </div>
        <div class="row">
            <label>Peito (cm): ${requestScope.chest}</label>
        </div>
    </div>
    <div class="col-md-5">
        <div class="row">
            <label>Gémeo (cm): ${requestScope.twin}</label>
        </div>
        <div class="row">
            <label>Quadricep (cm): ${requestScope.quadricep}</label>
        </div>
        <div class="row">
            <label>Tricep (cm): ${requestScope.tricep}</label>
        </div>
        <div class="row">
            <label>Pulso (cm): ${requestScope.wrist}</label>
        </div>
    </div>
    <div class="col-md-1"></div>
</div>
<div class="row justify-content-end">
    <div class="col-md-3">
        <button type="submit" formmethod="get" formaction="${pageContext.request.contextPath}\MyRequests" class="btn btn-danger btn-block text-white font-weight-normal border-0 mb-3">Voltar</button>
    </div>
</div>