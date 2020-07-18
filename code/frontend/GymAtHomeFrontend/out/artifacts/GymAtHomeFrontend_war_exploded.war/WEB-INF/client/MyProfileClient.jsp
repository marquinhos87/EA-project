<%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row justify-content-center">
    <div class="col-md-10">
        <form class="mt-2" method="post" action="${pageContext.request.contextPath}\MyProfileClient">
            <div class="form-group row">
                <label class="col-md-6 col-form-label"><h3>${requestScope.username!=null ? requestScope.username : "Username"}</h3></label>
                <div class="col-md-3"></div>
                <div class="col-md-3">
                    <button type="submit" class="btn btn-primary btn-block text-white font-weight-normal border-0 mb-3" name="action" value="editprofile">Salvar Alterações</button>
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
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Nova Password:</label>
                    <div class="col-md-7">
                        <input type="password" class="form-control" name="newpassword" placeholder="********">
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Data de Nascimento:</label>
                    <div class="col-md-7">
                        <div id="date-picker-example" class="md-form md-outline input-with-post-icon datepicker" inline="true">
                            <input type="date" class="form-control" name="birthday" value="${requestScope.birthday!=null ? requestScope.birthday : ""}" placeholder="${requestScope.birthday!=null ? requestScope.birthday : "Selecione a data"}">
                        </div>
                    </div>
                </div>
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Confirmar Password:</label>
                    <div class="col-md-7">
                        <input type="password" class="form-control" name="cpassword" placeholder="********">
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-md-6 col-form-label"><h3>Dados Biométricos</h3></label>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Peso (kg):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+(\.|)" min="0" class="form-control" name="weight" value="${requestScope.weight!=null ? requestScope.weight : ""}" placeholder="${requestScope.weight!=null ? requestScope.weight : "Ex: 75"}">
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
                    <label class="col-md-5 col-form-label">Altura (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="50" max="275" class="form-control" name="height" value="${requestScope.height!=null ? requestScope.height : ""}" placeholder="${requestScope.height!=null ? requestScope.height : "Ex: 180"}">
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
                    <label class="col-md-5 col-form-label">Quadricep (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="20" class="form-control" name="quadricep" value="${requestScope.quadricep!=null ? requestScope.quadricep : ""}" placeholder="${requestScope.quadricep!=null ? requestScope.quadricep : "Ex: 51"}">
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
                    <label class="col-md-5 col-form-label">Tricep (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="10" class="form-control" name="tricep" value="${requestScope.tricep!=null ? requestScope.tricep : ""}" placeholder="${requestScope.tricep!=null ? requestScope.tricep : "Ex: 28"}">
                    </div>
                </div>
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Pulso (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="5" class="form-control" name="wrist" value="${requestScope.wrist!=null ? requestScope.wrist : ""}" placeholder="${requestScope.wrist!=null ? requestScope.wrist : "Ex: 15"}">
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">IMC:</label>
                    <label class="col-md-7 col-form-label">${requestScope.bmi!=null ? requestScope.bmi : ""}</label>
                </div>
            </div>
        </form>

        <!-- prints BMI categories on the progress bar -->
        <div class="mt-4 progress" style="height:25px">
            <div class="progress-bar <% if ((float)request.getAttribute("bmi") < 18.5) out.print("progress-bar-striped progress-bar-animated"); %>" role="progressbar" style="width: 16.6%" aria-valuenow="16.6" aria-valuemin="0" aria-valuemax="100">subnutrido</div>
            <div class="progress-bar <% if ((float)request.getAttribute("bmi") >= 18.5 && (float)request.getAttribute("bmi") < 25) out.print("progress-bar-striped progress-bar-animated"); %> bg-success" role="progressbar" style="width: 16.6%" aria-valuenow="16.6" aria-valuemin="0" aria-valuemax="100">saudável</div>
            <div class="progress-bar <% if ((float)request.getAttribute("bmi") >= 25 && (float)request.getAttribute("bmi") < 30) out.print("progress-bar-striped progress-bar-animated"); %> bg-warning text-dark" role="progressbar" style="width: 16.6%" aria-valuenow="16.6" aria-valuemin="0" aria-valuemax="100">sobrepeso</div>
            <div class="progress-bar <% if ((float)request.getAttribute("bmi") >= 30 && (float)request.getAttribute("bmi") < 35) out.print("progress-bar-striped progress-bar-animated"); %>" role="progressbar" style="width: 16.6%; background-color: rgb(235, 100, 0);" aria-valuenow="16.6" aria-valuemin="0" aria-valuemax="100">obesidade grau I</div>
            <div class="progress-bar <% if ((float)request.getAttribute("bmi") >= 35 && (float)request.getAttribute("bmi") < 40) out.print("progress-bar-striped progress-bar-animated"); %>" role="progressbar" style="width: 16.6%; background-color: rgb(180, 50, 0);" aria-valuenow="16.6" aria-valuemin="0" aria-valuemax="100">obesidade grau II (severa)</div>
            <div class="progress-bar <% if ((float)request.getAttribute("bmi") >= 40) out.print("progress-bar-striped progress-bar-animated"); %>" role="progressbar" style="width: 17%; background-color: rgb(255, 0, 0);" aria-valuenow="17" aria-valuemin="0" aria-valuemax="100">obesidade grau III (morbida)</div>
        </div>

        <!-- prints BMI values -->
        <div class="row mt-2 ml-4 text-right" style="width: 100%">
            <div class="col" style="width: 14.3%">18.5</div>
            <div class="col" style="width: 14.3%">25</div>
            <div class="col" style="width: 14.3%">30</div>
            <div class="col" style="width: 14.3%">35</div>
            <div class="col" style="width: 14.3%">40</div>
            <div class="col" style="width: 14.2%"></div>
        </div>

        <!-- prints Arrow pointing to the respective BMI category -->
        <div class="row ml-1 text-center" style="width: 100%">
            <div class="col" style="width: 14.3%"><% if ((float)request.getAttribute("bmi") < 18.5) out.print("<i style='font-size:24px' class='fas'>&#xf102;</i>"); %></div>
            <div class="col" style="width: 14.3%"><% if ((float)request.getAttribute("bmi") >= 18.5 && (float)request.getAttribute("bmi") < 25) out.print("<i style='font-size:24px' class='fas'>&#xf102;</i>"); %></div>
            <div class="col" style="width: 14.3%"><% if ((float)request.getAttribute("bmi") >= 25 && (float)request.getAttribute("bmi") < 30) out.print("<i style='font-size:24px' class='fas'>&#xf102;</i>"); %></div>
            <div class="col" style="width: 14.3%"><% if ((float)request.getAttribute("bmi") >= 30 && (float)request.getAttribute("bmi") < 35) out.print("<i style='font-size:24px' class='fas'>&#xf102;</i>"); %></div>
            <div class="col" style="width: 14.3%"><% if ((float)request.getAttribute("bmi") >= 35 && (float)request.getAttribute("bmi") < 40) out.print("<i style='font-size:24px' class='fas'>&#xf102;</i>"); %></div>
            <div class="col" style="width: 14.2%"><% if ((float)request.getAttribute("bmi") >= 40) out.print("<i style='font-size:24px' class='fas'>&#xf102;</i>"); %></div>
        </div>

    </div>
</div>
