<%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% if(request.getAttribute("error") != null) {%>
    <div class="row my-3">
        <div class="col-md-12">
            <div class="alert alert-danger" role="alert">
                <h4 class="alert-heading">Error - Sorry for the inconvenience :(</h4>
                <p>${requestScope.error}</p>
            </div>
        </div>
    </div>
<%} else {%>
    <div class="col-md-1"></div>
    <div class="col-md-10">
        <form class="mt-2" method="post" action="${pageContext.request.contextPath}\MyProfileClient">
            <div class="form-group row">
                <label class="col-md-6 col-form-label"><h3>${requestScope.username!=null ? requestScope.username : "Username"}</h3></label>
            </div>
            <div class="form-group row">
                <label class="col-md-6 col-form-label"><h3>Dados Pessoais</h3></label>
                <label class="col-md-6 col-form-label"><h3>Dados Biométricos</h3></label>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Nome:</label>
                    <div class="col-md-7">
                        <input type="text" class="form-control" name="name" value="${requestScope.name!=null ? requestScope.name : ""}" placeholder="${requestScope.name!=null ? requestScope.name : "Ex: Gervásio"}">
                    </div>
                </div>
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">IMC:</label>
                    <label class="col-md-7 col-form-label">${requestScope.bci!=null ? requestScope.bci : ""}</label>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Email:</label>
                    <div class="col-md-7">
                        <input type="email" class="form-control" name="email" value="${requestScope.email!=null ? requestScope.email : ""}" placeholder="${requestScope.email!=null ? requestScope.email : "Ex: gervasio@exemplo.com"}">
                    </div>
                </div>
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Altura (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="50" max="275" class="form-control" name="height" value="${requestScope.height!=null ? requestScope.height : ""}" placeholder="${requestScope.height!=null ? requestScope.height : "Ex: 180"}">
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
                <div class="col-sm-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Peso (kg):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="0" class="form-control" name="weight" value="${requestScope.weight!=null ? requestScope.weight : ""}" placeholder="${requestScope.weight!=null ? requestScope.weight : "Ex: 75"}">
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
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Cintura (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="40" class="form-control" name="waist" value="${requestScope.waist!=null ? requestScope.waist : ""}" placeholder="${requestScope.waist!=null ? requestScope.waist : "Ex: 78"}">
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Password Antiga:</label>
                    <div class="col-md-7">
                        <input type="password" class="form-control" name="oldpassword" placeholder="********">
                    </div>
                </div>
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Peito (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="50" class="form-control" name="chest" value="${requestScope.chest!=null ? requestScope.chest : ""}" placeholder="${requestScope.chest!=null ? requestScope.chest : "Ex: 87"}">
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
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Gémeo (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="15" class="form-control" name="twin" value="${requestScope.twin!=null ? requestScope.twin : ""}" placeholder="${requestScope.twin!=null ? requestScope.twin : "Ex: 36"}">
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
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Quadricep (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="20" class="form-control" name="quadricep" value="${requestScope.quadricep!=null ? requestScope.quadricep : ""}" placeholder="${requestScope.quadricep!=null ? requestScope.quadricep : "Ex: 51"}">
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6">

                </div>
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Tricep (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="10" class="form-control" name="tricep" value="${requestScope.tricep!=null ? requestScope.tricep : ""}" placeholder="${requestScope.tricep!=null ? requestScope.tricep : "Ex: 28"}">
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6">

                </div>
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Pulso (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="5" class="form-control" name="wrist" value="${requestScope.wrist!=null ? requestScope.wrist : ""}" placeholder="${requestScope.wrist!=null ? requestScope.wrist : "Ex: 15"}">
                    </div>
                </div>
            </div>
            <div class="form-row row justify-content-end">
                <div class="col-md-3">
                    <button type="submit" class="btn btn-primary btn-block text-white font-weight-normal border-0 mb-3">Salvar Alterações</button>
                </div>
            </div>
        </form>
    </div>
    <div class="col-md-1"></div>
<%}%>