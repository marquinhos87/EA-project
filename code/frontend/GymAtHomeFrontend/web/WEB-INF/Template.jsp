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
            <header class="page-header">
                <c:choose>
                    <c:when test="${requestScope.user=='client'}">
                        <form method="get" class="navbar navbar-expand-lg navbar-light bg-light mb-3 border py-0 px-0 d-flex align-content-center" style="overflow: hidden;">
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
                        <form method="get" class="navbar navbar-expand-lg navbar-light bg-light mb-3 border py-0 px-0 d-flex align-content-center" style="overflow: hidden;">
                            <div class="navbar-collapse d-flex justify-content-end">
                                <button type="submit" class="py-3 px-1 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" ${requestScope.page=='SearchPersonalTrainer' ? "disabled" : "value=\"searchPersonalTrainer\""} formaction="${pageContext.request.contextPath}\MyRequests">Meus Pedidos</button>
                                <button type="submit" class="py-3 px-1 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" ${requestScope.page=='ClientPlan' ? "disabled" : "value=\"clientPlan\""} formaction="${pageContext.request.contextPath}\MyRequests">Meus Clientes</button>
                                <button type="submit" class="py-3 px-1 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" ${requestScope.page=='MyClientProfile' ? "disabled" : "value=\"myClientProfile\""} formaction="${pageContext.request.contextPath}\MyPersonalTrainerProfile">Meu Perfil</button>
                                <button type="submit" class="py-3 px-1 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" value="logout" formaction="${pageContext.request.contextPath}\AddGame">Logout</button>
                            </div>
                        </form>
                    </c:when>
                    <c:when test="${requestScope.page=='PersonalTrainerRegister' || requestScope.page=='ClientRegister'}">
                        <form method="get" class="navbar navbar-expand-lg navbar-light bg-light border py-0 px-0 mb-3 d-flex align-content-center" style="overflow: hidden;">
                            <label class="col-form-label ml-4"><h4>Gym@Home</h4></label>
                            <div class="navbar-collapse d-flex justify-content-end mr-3">
                                <button type="submit" class="py-3 px-1 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" value="login" formaction="${pageContext.request.contextPath}\Login">Login</button>
                            </div>
                        </form>
                    </c:when>
                    <c:when test="true">
                        <div class="d-flex justify-content-center my-5">
                            <h1>GymAtHome</h1>
                        </div>
                    </c:when>
                </c:choose>
            </header>

            <!-- main -->
            <div class="row">
                <c:choose>
                    <c:when test="${requestScope.page==null ||requestScope.page=='Login'}">
                        <jsp:include page="common/Login.jsp" />
                    </c:when>
                    <c:when test="${requestScope.page=='Notification'}">
                        <jsp:include page="common/Notification.jsp" />
                    </c:when>
                    <c:when test="${requestScope.page=='ClientRegister'}">
                        <jsp:include page="client/ClientRegister.jsp" />
                    </c:when>
                    <c:when test="${requestScope.page=='ClientPlan'}">
                        <jsp:include page="client/ClientPlan.jsp" />
                    </c:when>
                    <c:when test="${requestScope.page=='MakeRequest'}">
                        <jsp:include page="client/MakeRequest.jsp" />
                    </c:when>
                    <c:when test="${requestScope.page=='MyProfileClient'}">
                        <jsp:include page="client/MyProfileClient.jsp" />
                    </c:when>
                    <c:when test="${requestScope.page=='PersonalTrainerProfile'}">
                        <jsp:include page="client/PersonalTrainerProfile.jsp" />
                    </c:when>
                    <c:when test="${requestScope.page=='SearchPersonalTrainer'}">
                        <jsp:include page="client/SearchPersonalTrainer.jsp" />
                    </c:when>
                    <c:when test="${requestScope.page=='Task'}">
                        <jsp:include page="client/Task.jsp" />
                    </c:when>
                    <c:when test="${requestScope.page=='ClientProfile'}">
                        <jsp:include page="personaltrainer/ClientProfile.jsp" />
                    </c:when>
                    <c:when test="${requestScope.page=='CreateWeek'}">
                        <jsp:include page="personaltrainer/CreateWeek.jsp" />
                    </c:when>
                    <c:when test="${requestScope.page=='MyProfilePersonalTrainer'}">
                        <jsp:include page="personaltrainer/MyProfilePersonalTrainer.jsp" />
                    </c:when>
                    <c:when test="${requestScope.page=='MyRequests'}">
                        <jsp:include page="personaltrainer/MyRequests.jsp" />
                    </c:when>
                    <c:when test="${requestScope.page=='PersonalTrainerRegister'}">
                        <jsp:include page="personaltrainer/PersonalTrainerRegister.jsp" />
                    </c:when>
                </c:choose>
            </div>

            <!-- Footer -->
            <footer class="page-footer font-small bg-light mt-2 pt-2 fixed-bottom border">
                <!-- Copyright -->
                <div class="footer-copyright text-center py-2">© 2020 Copyright:
                    <label> Gym@Home</label>
                </div>
                <!-- Copyright -->
            </footer>
        </div>
    </body>
</html>
