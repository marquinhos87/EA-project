<%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link href="css/bootstrap.css" rel="stylesheet">

        <title>Title</title>
    </head>
    <body>
        <div class="container">
            <!-- header and nav -->
            <div class="row">
                <div class="col-md-12">
                <c:choose>
                    <c:when test="${requestScope.user=='client'}">
                        <form method="get" class="navbar navbar-expand-lg navbar-light bg-light mb-3 border rounded py-0 px-0 d-flex align-content-center" style="overflow: hidden;">
                            <div class="navbar-collapse d-flex justify-content-end">
                                <button type="submit" class="py-3 px-1 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" ${requestScope.page=='SearchPersonalTrainer' ? "disabled" : "value=\"searchPersonalTrainer\""} formaction="${pageContext.request.contextPath}\SearchPersonalTrainer">Procurar PersonalTrainer</button>
                                <button type="submit" class="py-3 px-1 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" ${requestScope.page=='ClientPlan' ? "disabled" : "value=\"clientPlan\""} formaction="${pageContext.request.contextPath}\ClientPlan">Meu Plano</button>
                                <button type="submit" class="py-3 px-1 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" ${requestScope.page=='MyClientProfile' ? "disabled" : "value=\"myClientProfile\""} formaction="${pageContext.request.contextPath}\MyClientProfile">Meu Perfil</button>
                                <button type="submit" class="py-3 px-1 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" ${requestScope.page=='Notification' ? "disabled" : "value=\"notification\""} formaction="${pageContext.request.contextPath}\..\common\Notification">Notificações</button>
                                <button type="submit" class="py-3 px-1 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" value="logout" formaction="${pageContext.request.contextPath}\AddGame">Logout</button>
                            </div>
                        </form>
                    </c:when>
                    <c:when test="${requestScope.user=='personalTrainer'}">
                        <form method="get" class="navbar navbar-expand-lg navbar-light bg-light mb-3 border rounded py-0 px-0 d-flex align-content-center" style="overflow: hidden;">
                            <div class="navbar-collapse d-flex justify-content-end">
                                <button type="submit" class="py-3 px-1 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" ${requestScope.page=='SearchPersonalTrainer' ? "disabled" : "value=\"searchPersonalTrainer\""} formaction="${pageContext.request.contextPath}\MyRequests">Meus Pedidos</button>
                                <button type="submit" class="py-3 px-1 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" ${requestScope.page=='ClientPlan' ? "disabled" : "value=\"clientPlan\""} formaction="${pageContext.request.contextPath}\MyRequests">Meus Clientes</button>
                                <button type="submit" class="py-3 px-1 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" ${requestScope.page=='MyClientProfile' ? "disabled" : "value=\"myClientProfile\""} formaction="${pageContext.request.contextPath}\MyPersonalTrainerProfile">Meu Perfil</button>
                                <button type="submit" class="py-3 px-1 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" value="logout" formaction="${pageContext.request.contextPath}\AddGame">Logout</button>
                            </div>
                        </form>
                    </c:when>
                    <c:when test="${requestScope.page=='registerPersonalTrainer' || requestScope.page=='registerClient'}">
                        <form method="get" class="navbar navbar-expand-lg navbar-light bg-light mb-3 border rounded py-0 px-0 d-flex align-content-center" style="overflow: hidden;">
                            <div class="navbar-collapse d-flex justify-content-end">
                                <button type="submit" class="py-3 px-1 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" value="login" formaction="${pageContext.request.contextPath}\Login">Logout</button>
                            </div>
                        </form>
                    </c:when>
                    <c:when test="true">
                        <div class="d-flex justify-content-center my-5">
                            <h1>GymAtHome</h1>
                        </div>
                    </c:when>
                </c:choose>
                </div>
            </div>

            <!-- main -->
            <div class="row">
                <form method="post" action="${pageContext.request.contextPath}\ClientRegister">
                    <div class="row form-row">
                        <div class="col-md-6">
                            <label><h3>Dados Obrigatórios</h3></label>
                            <div class="row form-group">
                                <label class="col-md-6 col-form-label">Nome:</label>
                                <div class="col-md-6">
                                    <input type="text" class="form-control" name="name" placeholder="Ex: João Gervásio" required>
                                </div>
                            </div>
                            <div class="row form-group">
                                <label class="col-md-6 col-form-label">Username:</label>
                                <div class="col-md-6 d-inline-flex">
                                    <div class="input-group-prepend">
                                        <div class="input-group-text">u</div>
                                    </div>
                                    <input type="text" class="form-control" name="username" placeholder="Ex: gervasio98" required>
                                </div>
                            </div>
                            <div class="row form-group">
                                <label class="col-md-6 col-form-label">Email:</label>
                                <div class="col-md-6">
                                    <input type="email" class="form-control" name="email" placeholder="Ex: joaoGervasio@exemplo.com" required>
                                </div>
                            </div>
                            <div class="row form-group">
                                <label class="col-md-6 col-form-label">Password:</label>
                                <div class="col-md-6">
                                    <input type="password" class="form-control" name="password" placeholder="********" required>
                                </div>
                            </div>
                            <div class="row form-group">
                                <label class="col-md-6 col-form-label">Confirmar Password:</label>
                                <div class="col-md-6">
                                    <input type="password" class="form-control" name="cpassword" placeholder="********" required>
                                </div>
                            </div>
                            <div class="row form-group">
                                <label class="col-md-6 col-form-label">Data de Nascimento:</label>
                                <div class="col-md-6">
                                    <div id="date-picker-example" class="md-form md-outline input-with-post-icon datepicker" inline="true">
                                        <input type="text" class="form-control" name="birthday" placeholder="Selecione a data">
                                        <i class="fas fa-calendar input-prefix"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="row form-group">
                                <label class="col-md-6 col-form-label">Género:</label>
                                <div class="col-md-6">
                                    <select class="custom-select" name="genre" required>
                                        <option value="m">Masculino</option>
                                        <option value="f">Feminino</option>
                                        <option value="o">Outro</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row form-group">
                                <label class="col-md-6 col-form-label">Altura (cm):</label>
                                <div class="col-md-6">
                                    <input type="number" pattern="\d+" min="50" max="275" class="form-control" name="height" placeholder="Ex: 180" required>
                                </div>
                            </div>
                            <div class="row form-group">
                                <label class="col-md-6 col-form-label">Peso (kg):</label>
                                <div class="col-md-6">
                                    <input type="number" pattern="\d+" min="0" class="form-control" name="weight" placeholder="Ex: 75" required>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <label><h3>Dados Opcionais</h3></label>
                            <div class="row form-group">
                                <label class="col-md-6 col-form-label">Cintura (cm):</label>
                                <div class="col-md-6">
                                    <input type="number" pattern="\d+" min="40" class="form-control" name="waist" placeholder="Ex: 78">
                                </div>
                            </div>
                            <div class="row form-group">
                                <label class="col-md-6 col-form-label">Peito (cm):</label>
                                <div class="col-md-6">
                                    <input type="number" pattern="\d+" min="50" class="form-control" name="chest" placeholder="Ex: 87">
                                </div>
                            </div>
                            <div class="row form-group">
                                <label class="col-md-6 col-form-label">Gémeo (cm):</label>
                                <div class="col-md-6">
                                    <input type="number" pattern="\d+" min="15" class="form-control" name="twin" placeholder="Ex: 36">
                                </div>
                            </div>
                            <div class="row form-group">
                                <label class="col-md-6 col-form-label">Quadricep (cm):</label>
                                <div class="col-md-6">
                                    <input type="number" pattern="\d+" min="20" class="form-control" name="quadriceps" placeholder="Ex: 51">
                                </div>
                            </div>
                            <div class="row form-group">
                                <label class="col-md-6 col-form-label">Tricep (cm):</label>
                                <div class="col-md-6">
                                    <input type="number" pattern="\d+" min="10" class="form-control" name="triceps" placeholder="Ex: 28">
                                </div>
                            </div>
                            <div class="row form-group">
                                <label class="col-md-6 col-form-label">Pulso (cm):</label>
                                <div class="col-md-6">
                                    <input type="number" pattern="\d+" min="5" class="form-control" name="wrist" placeholder="Ex: 15">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row form-row justify-content-end">
                        <div class="col-md-3">
                            <button type="submit" class="btn btn-primary btn-block btn-outline-dark text-white font-weight-normal bg-light border-0">Registar</button>
                        </div>
                    </div>
                </form>
            </div>

            <!-- footer -->
            <footer class="main-footer fixed-bottom">
                <div class="container">
                    <div class="row align-items-center p-4 text-muted small bg-light">
                        <div class="col">
                            Developed by .
                        </div>
                        <div class="col-2 text-center">
                            &copy;opyright
                        </div>
                        <div class="col d-none d-md-block text-right">
                            The source code is publicly available on <a target="_blank" href="//github.com/marquinhos87/EA-project">Github</a>.
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    </body>
</html>
