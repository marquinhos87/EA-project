<%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-4"></div>
<div class="col-md-4 my-3">
    <form method="post">
        <div class="form-group row">
            <div class="col-md-12">
                <input required="required" pattern="(u|pt).*" type="text" class="form-control" placeholder="username" name="username">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-md-12">
                <input required="required" type="password" class="form-control" placeholder="******" name="password">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-md-12">
                <button type="submit" class="btn btn-primary btn-block text-white font-weight-normal border-0" formaction="${pageContext.request.contextPath}\Login">Login</button>
            </div>
        </div>
    </form>
    <form method="get">
        <div class="form-group row">
            <div class="col-md-12">
                <button type="submit" class="btn btn-primary btn-block btn-outline-dark text-secondary font-weight-normal bg-light border-0" formaction="${pageContext.request.contextPath}\ClientRegister">Registar Cliente</button>
                <button type="submit" class="btn btn-primary btn-block btn-outline-dark text-secondary font-weight-normal bg-light border-0" formaction="${pageContext.request.contextPath}\PersonalTrainerRegister">Registar Personal Trainer</button>
            </div>
        </div>
    </form>
</div>
<div class="col-md-4"></div>