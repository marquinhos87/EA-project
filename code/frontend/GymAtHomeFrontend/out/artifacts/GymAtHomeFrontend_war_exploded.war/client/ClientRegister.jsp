<%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form method="post" action="${pageContext.request.contextPath}\ClientRegister">
    <div class="col-md-6 form-group">
        <label>Dados Obrigatórios</label>
        <div class="row form-group">
            <div class="col-md-6">
                <label class="col-form-label">Nome:</label>
            </div>
            <div class="col-md-6">
                <input type="text" class="form-control" name="name" placeholder="Ex: João Gervásio" required>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-6">
                <label class="col-form-label">Username:</label>
            </div>
            <div class="col-md-6">
                u<input type="text" class="form-control" name="username" placeholder="Ex: gervasio98" required>
            </div>
        </div>
        <div class="row form-group">
            <div class="col-md-6">
                <label class="col-form-label">Email:</label>
            </div>
            <div class="col-md-6">
                <input type="email" class="form-control" name="email" placeholder="Ex: joaoGervasio@exemplo.com" required>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <label class="col-form-label">Password:</label>
            </div>
            <div class="col-md-6">
                <input type="password" class="form-control" name="password" placeholder="********" required>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <label class="col-form-label">Confirmar Password:</label>
            </div>
            <div class="col-md-6">
                <input type="password" class="form-control" name="cpassword" placeholder="********" required>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <label class="col-form-label">Data de Nascimento:</label>
            </div>
            <div class="col-md-6">
                <div id="date-picker-example" class="md-form md-outline input-with-post-icon datepicker" inline="true">
                    <input type="text" class="form-control" name="birthday" placeholder="Selecione a data">
                    <i class="fas fa-calendar input-prefix"></i>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <label class="col-form-label">Género:</label>
            </div>
            <div class="col-md-6">
                <select class="custom-select" name="genre" required>
                    <option value="m">Masculino</option>
                    <option value="f">Feminino</option>
                    <option value="o">Outro</option>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <label class="col-form-label">Altura (cm):</label>
            </div>
            <div class="col-md-6">
                <input type="number" pattern="\d+" min="50" max="275" class="form-control" name="height" placeholder="Ex: 180" required>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <label class="col-form-label">Peso (kg):</label>
            </div>
        <div class="col-md-6">
            <input type="number" pattern="\d+" min="0" class="form-control" name="weight" placeholder="Ex: 75" required>
        </div></div>
    </div>
    <div class="col-md-6">
        <label>Dados Opcionais</label>
        <div class="row">
            <div class="col-md-6">
                <label class="col-form-label">Cintura (cm):</label>
            </div>
            <div class="col-md-6">
                <input type="number" pattern="\d+" min="40" class="form-control" name="waist" placeholder="Ex: 78">
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <label class="col-form-label">Peito (cm):</label>
            </div>
            <div class="col-md-6">
                <input type="number" pattern="\d+" min="50" class="form-control" name="chest" placeholder="Ex: 87">
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <label class="col-form-label">Gémeo (cm):</label>
            </div>
            <div class="col-md-6">
                <input type="number" pattern="\d+" min="15" class="form-control" name="twin" placeholder="Ex: 36">
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <label class="col-form-label">Quadricep (cm):</label>
            </div>
            <div class="col-md-6">
                <input type="number" pattern="\d+" min="20" class="form-control" name="quadriceps" placeholder="Ex: 51">
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <label class="col-form-label">Tricep (cm):</label>
            </div>
            <div class="col-md-6">
                <input type="number" pattern="\d+" min="10" class="form-control" name="triceps" placeholder="Ex: 28">
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <label class="col-form-label">Pulso (cm):</label>
            </div>
            <div class="col-md-6">
                <input type="number" pattern="\d+" min="5" class="form-control" name="wrist" placeholder="Ex: 15">
            </div>
        </div>
    </div>
    <div class="row justify-content-end">
        <div class="col-md-3">
            <button type="submit" class="btn-block">Registar</button>
        </div>
    </div>
</form>
