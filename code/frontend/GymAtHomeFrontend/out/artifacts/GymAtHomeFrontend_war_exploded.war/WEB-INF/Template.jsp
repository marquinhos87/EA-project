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
        <link href="css/our.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <script src='https://kit.fontawesome.com/a076d05399.js'></script>

        <title>${requestScope.title}</title>
    </head>
    <body>

        <!-- header and nav -->
        <header>
            <c:choose>
                <c:when test="${sessionScope.userType == 'client'}">
                    <form method="get" class="navbar navbar-expand-lg navbar-light bg-light mb-3 border py-0 px-0 d-flex align-content-center" style="overflow: hidden;" >
                        <label class="col-form-label ml-4 pl-4"><h4>Gym@Home</h4></label>
                        <div class="navbar-collapse d-flex justify-content-end">
                            <button type="submit" class="py-3 px-3 btn-link text-decoration-none text-secondary font-weight-normal border-0 ${requestScope.page=='SearchPersonalTrainer' ? "\" disabled style=\"background-color: #E8E8E8;\"" : "bg-light\""} formaction="${pageContext.request.contextPath}\SearchPersonalTrainer">Procurar PersonalTrainer</button>
                            <button type="submit" class="py-3 px-3 btn-link text-decoration-none text-secondary font-weight-normal border-0 ${requestScope.page=='RequestsClient' ? "\" disabled style=\"background-color: #E8E8E8;\"" : "bg-light\""} formaction="${pageContext.request.contextPath}\RequestsClient">Meus Pedidos</button>
                            <button type="submit" class="py-3 px-3 btn-link text-decoration-none text-secondary font-weight-normal border-0 ${requestScope.page=='ClientPlan' ? "\" disabled style=\"background-color: #E8E8E8;\"" : "bg-light\""} formaction="${pageContext.request.contextPath}\ClientPlan">Meu Plano</button>
                            <button type="submit" class="py-3 px-3 btn-link text-decoration-none text-secondary font-weight-normal border-0 ${requestScope.page=='MyProfileClient' ? "\" disabled style=\"background-color: #E8E8E8;\"" : "bg-light\""} formaction="${pageContext.request.contextPath}\MyProfileClient">Meu Perfil</button>
                            <button type="submit" class="py-3 px-3 btn-link text-decoration-none text-secondary font-weight-normal border-0 ${requestScope.page=='Notification' ? "\" disabled style=\"background-color: #E8E8E8;\"" : "bg-light\""} formaction="${pageContext.request.contextPath}\Notification">Notificações</button>
                            <button type="submit" class="py-3 px-3 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" name="action" value="logout" formaction="${pageContext.request.contextPath}\Logout">Logout</button>
                        </div>
                    </form>
                </c:when>
                <c:when test="${sessionScope.userType == 'pt'}">
                    <form method="get" class="navbar navbar-expand-lg navbar-light bg-light mb-3 border py-0 px-0 d-flex align-content-center" style="overflow: hidden;">
                        <label class="col-form-label ml-4 pl-4"><h4>Gym@Home</h4></label>
                        <div class="navbar-collapse d-flex justify-content-end">
                            <button type="submit" class="py-3 px-3 btn-link text-decoration-none text-secondary font-weight-normal border-0 ${requestScope.page=='MyRequests' ? "\" disabled style=\"background-color: #E8E8E8;\"" : "bg-light\""} formaction="${pageContext.request.contextPath}\MyRequestsPT">Meus Pedidos</button>
                            <button type="submit" class="py-3 px-3 btn-link text-decoration-none text-secondary font-weight-normal border-0 ${requestScope.page=='MyClients' ? "\" disabled style=\"background-color: #E8E8E8;\"" : "bg-light\""} formaction="${pageContext.request.contextPath}\MyClients">Meus Clientes</button>
                            <button type="submit" class="py-3 px-3 btn-link text-decoration-none text-secondary font-weight-normal border-0 ${requestScope.page=='MyProfilePersonalTrainer' ? "\" disabled style=\"background-color: #E8E8E8;\"" : "bg-light\""} formaction="${pageContext.request.contextPath}\MyProfilePersonalTrainer">Meu Perfil</button>
                            <button type="submit" class="py-3 px-3 btn-link text-decoration-none text-secondary font-weight-normal border-0 ${requestScope.page=='Notification' ? "\" disabled style=\"background-color: #E8E8E8;\"" : "bg-light\""} formaction="${pageContext.request.contextPath}\Notification">Notificações</button>
                            <button type="submit" class="py-3 px-3 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" name="action" value="logout" formaction="${pageContext.request.contextPath}\Logout">Logout</button>
                        </div>
                    </form>
                </c:when>
                <c:when test="${requestScope.page=='PersonalTrainerRegister' || requestScope.page=='ClientRegister'}">
                    <form method="get" class="navbar navbar-expand-lg navbar-light bg-light border py-0 px-0 mb-3 d-flex align-content-center" style="overflow: hidden;">
                        <label class="col-form-label ml-4 pl-4"><h4>Gym@Home</h4></label>
                        <div class="navbar-collapse d-flex justify-content-end mr-3 pr-3">
                            <button type="submit" class="py-3 px-3 btn-link text-decoration-none text-secondary font-weight-normal bg-light border-0" formaction="${pageContext.request.contextPath}\Login">Login</button>
                        </div>
                    </form>
                </c:when>
                <c:when test="true">
                    <div class="d-flex justify-content-center my-5">
                        <h1>Gym@Home</h1>
                    </div>
                </c:when>
            </c:choose>
        </header>

        <div class="container" style="padding-bottom: 80px;">
            <div class="row justify-content-center">
                <c:choose>
                    <c:when test="${requestScope.errorMessage!=null}">
                        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                            <strong>Erro:</strong> ${requestScope.errorMessage}
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${requestScope.successMessage!=null}">
                        <div class="alert alert-success alert-dismissible fade show" role="alert">
                            <strong>Sucesso:</strong> ${requestScope.successMessage}
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${requestScope.warningMessage!=null}">
                        <div class="alert alert-warning alert-dismissible fade show" role="alert">
                            <strong>Aviso:</strong> ${requestScope.warningMessage}
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                    </c:when>
                </c:choose>
            </div>
            <!-- main -->
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
                <c:when test="${requestScope.page=='ClientWorkout'}">
                    <jsp:include page="client/ClientWorkout.jsp" />
                </c:when>
                <c:when test="${requestScope.page=='RequestsClient'}">
                    <jsp:include page="client/MyRequestsClient.jsp" />
                </c:when>
                <c:when test="${requestScope.page=='MyClients'}">
                    <jsp:include page="personaltrainer/MyClients.jsp" />
                </c:when>
                <c:when test="${requestScope.page=='ClientPlanByPT'}">
                    <jsp:include page="personaltrainer/ClientPlanByPT.jsp" />
                </c:when>
                <c:when test="${requestScope.page=='ClientWorkoutByPT'}">
                    <jsp:include page="personaltrainer/ClientWorkoutByPT.jsp" />
                </c:when>
            </c:choose>

        </div>

        <!-- Footer -->
        <footer class="font-small bg-light border fixed-bottom py-1 align-content-center">
            <!-- Copyright -->
            <div class="text-center py-2">© 2020 Copyright:
                <label> Gym@Home</label>
            </div>
            <!-- Copyright -->
        </footer>

        <script src="https://code.jquery.com/jquery-latest.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
    </body>
</html>
