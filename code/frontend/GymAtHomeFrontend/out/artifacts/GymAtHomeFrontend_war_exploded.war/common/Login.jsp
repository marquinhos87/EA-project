<%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-4"></div>
<div class="col-md-4 my-3">
    <form method="get">
        <div class="form-group row">
            <div class="col-md-12">
                <input required="required" type="text" class="form-control" placeholder="username" name="name">
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
    <div class="form-group row">
        <div class="col-md-12">
            <button type="submit" class="btn btn-primary btn-block btn-outline-dark text-secondary font-weight-normal bg-light border-0" formaction="${pageContext.request.contextPath}\ClientRegister">Registar Cliente</button>
            <button type="submit" class="btn btn-primary btn-block btn-outline-dark text-secondary font-weight-normal bg-light border-0" formaction="${pageContext.request.contextPath}\PersonalTrainerRegister">Registar Personal Trainer</button>
        </div>
    </div>
</div>
<div class="col-md-4"></div>