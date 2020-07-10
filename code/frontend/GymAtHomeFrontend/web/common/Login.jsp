<%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-3"></div>
<div class="col-md-6">
    <form method="get">
        <div class="row">
            <input type="text"/>
        </div>
        <div class="row">
            <input type="password"/>
        </div>
        <div class="row">
            <button class="btn-block text-secondary font-weight-normal bg-light border-0" formaction="${pageContext.request.contextPath}\Login">Login</button>
        </div>
        <div class="row">
            <div class="col-md-6">
                <button class="btn-block text-secondary font-weight-normal bg-light border-0" formaction="${pageContext.request.contextPath}\ClientRegister">Registar Cliente</button>
            </div>
            <div class="col-md-6">
                <button class="btn-block text-secondary font-weight-normal bg-light border-0" formaction="${pageContext.request.contextPath}\PersonalTrainerRegister">Registar Personal Trainer</button>
            </div>
        </div>
    </form>
</div>
<div class="col-md-3"></div>