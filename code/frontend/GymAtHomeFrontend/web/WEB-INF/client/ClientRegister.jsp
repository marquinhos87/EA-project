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
        <form class="mt-2" method="post" action="${pageContext.request.contextPath}\ClientRegister">
            <div class="form-group row">
                <div class="col-md-6">
                    <label><h3>Dados Obrigatórios</h3></label>
                </div>
                <div class="col-md-6">
                    <label><h3>Dados Opcionais</h3></label>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-sm-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Nome:</label>
                    <div class="col-md-7">
                        <input type="text" class="form-control" name="name" placeholder="Ex: Gervásio" required>
                    </div>
                </div>
                <div class="col-sm-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Cintura (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="40" class="form-control" name="waist" placeholder="Ex: 78">
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Username:</label>
                    <div class="col-md-7 d-inline-flex">
                        <div class="input-group-prepend">
                            <div class="input-group-text">c</div>
                        </div>
                        <input type="text" class="form-control" name="username" placeholder="Ex: gervasio98" required>
                    </div>
                </div>
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Peito (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="50" class="form-control" name="chest" placeholder="Ex: 87">
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Email:</label>
                    <div class="col-md-7">
                        <input type="email" class="form-control" name="email" placeholder="Ex: gervasio@exemplo.com" required>
                    </div>
                </div>
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Gémeo (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="15" class="form-control" name="twin" placeholder="Ex: 36">
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Password:</label>
                    <div class="col-md-7">
                        <input type="password" minlength="6" class="form-control" name="password" placeholder="********" required>
                    </div>
                </div>
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Quadricep (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="20" class="form-control" name="quadricep" placeholder="Ex: 51">
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Confirmar Password:</label>
                    <div class="col-md-7">
                        <input type="password" minlength="6" class="form-control" name="cpassword" placeholder="********" required>
                    </div>
                </div>
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Tricep (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="10" class="form-control" name="tricep" placeholder="Ex: 28">
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Data de Nascimento:</label>
                    <div class="col-md-7">
                        <div id="date-picker-example" class="md-form md-outline input-with-post-icon datepicker" inline="true">
                            <input type="date" class="form-control" name="birthday" placeholder="Selecione a data" required>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Pulso (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="5" class="form-control" name="wrist" placeholder="Ex: 15">
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Género:</label>
                    <div class="col-md-7">
                        <select class="custom-select" name="genre" required>
                            <option value="m">Masculino</option>
                            <option value="f">Feminino</option>
                            <option value="o">Outro</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Altura (cm):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="50" max="275" class="form-control" name="height" placeholder="Ex: 180" required>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-6 d-inline-flex">
                    <label class="col-md-5 col-form-label">Peso (kg):</label>
                    <div class="col-md-7">
                        <input type="number" pattern="\d+" min="0" class="form-control" name="weight" placeholder="Ex: 75" required>
                    </div>
                </div>
            </div>
            <div class="form-row row justify-content-end">
                <div class="col-md-3">
                    <button type="submit" class="btn btn-primary btn-block text-white font-weight-normal border-0 mb-3">Registar</button>
                </div>
            </div>
        </form>
    </div>
</div>