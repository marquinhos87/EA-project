<%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row justify-content-center">
    <div class="col-md-10">
        <form class="mt-2" method="post" action="${pageContext.request.contextPath}\MyProfilePersonalTrainer">
            <div class="form-group row">
                <div class="col-md-6">
                    <label><h3>${requestScope.username!=null ? requestScope.username : "Username"}</h3></label>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-6 col-form-label"><h3>Dados Pessoais</h3></label>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Nome:</label>
                    <div class="col-md-7">
                        <input type="text" class="form-control" name="name" value="${requestScope.name!=null ? requestScope.name : ""}" placeholder="${requestScope.name!=null ? requestScope.name : "Ex: Gervásio"}">
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Email:</label>
                    <div class="col-md-7">
                        <input type="email" class="form-control" name="email" value="${requestScope.email!=null ? requestScope.email : ""}" placeholder="${requestScope.email!=null ? requestScope.email : "Ex: gervasio@exemplo.com"}">
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Género:</label>
                    <div class="col-md-7">
                        <select class="custom-select" name="genre">
                            <c:choose>
                                <c:when test="${requestScope.genre=='m'}">
                                    <option value="m" selected>Masculino</option>
                                    <option value="f">Feminino</option>
                                    <option value="o">Outro</option>
                                </c:when>
                                <c:when test="${requestScope.genre=='f'}">
                                    <option value="m">Masculino</option>
                                    <option value="f" selected>Feminino</option>
                                    <option value="o">Outro</option>
                                </c:when>
                                <c:when test="${requestScope.genre=='o'}">
                                    <option value="m">Masculino</option>
                                    <option value="f">Feminino</option>
                                    <option value="o" selected>Outro</option>
                                </c:when>
                            </c:choose>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Data de Nascimento:</label>
                    <div class="col-md-7">
                        <div id="date-picker-example" class="md-form md-outline input-with-post-icon datepicker" inline="true">
                            <input type="text" class="form-control" name="birthday" value="${requestScope.birthday!=null ? requestScope.birthday : ""}" placeholder="${requestScope.birthday!=null ? requestScope.birthday : "Selecione a data"}">
                            <i class="fas fa-calendar input-prefix"></i>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Especialidade:</label>
                    <div class="col-md-7">
                        <select class="custom-select" name="skill">
                            <c:choose>
                                <c:when test="${requestScope.skill=='musculacao'}">
                                    <option value="musculacao" selected>Musculação</option>
                                    <option value="cardio">Cardio</option>
                                    <option value="ambos">Ambos</option>
                                </c:when>
                                <c:when test="${requestScope.skill=='cardio'}">
                                    <option value="musculacao">Musculação</option>
                                    <option value="cardio" selected>Cardio</option>
                                    <option value="ambos">Ambos</option>
                                </c:when>
                                <c:when test="${requestScope.skill=='ambos'}">
                                    <option value="musculacao">Musculação</option>
                                    <option value="cardio">Cardio</option>
                                    <option value="ambos" selected>Ambos</option>
                                </c:when>
                            </c:choose>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Nova Password:</label>
                    <div class="col-md-7">
                        <input type="password" class="form-control" name="newpassword" placeholder="********">
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Confirmar Password:</label>
                    <div class="col-md-7">
                        <input type="password" class="form-control" name="cpassword" placeholder="********">
                    </div>
                </div>
            </div>
            <div class="form-row row justify-content-end">
                <div class="col-md-3">
                    <button type="submit" class="btn btn-primary btn-block text-white font-weight-normal border-0 mb-3" name="action" value="editprofile">Salvar Alterações</button>
                </div>
            </div>
        </form>
    </div>
</div>