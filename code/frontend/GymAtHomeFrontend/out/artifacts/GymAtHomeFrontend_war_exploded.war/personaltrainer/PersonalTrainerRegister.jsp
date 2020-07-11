<%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form method="post" action="${pageContext.request.contextPath}\ClientRegister">
    <div class="row">
        <div class="col-md-6">
            <div class="row">
                <div class="col-md-6">
                    <label class="col-form-label">Nome:</label>
                </div>
                <div class="col-md-6">
                    <input type="text" class="form-control" name="name" placeholder="Ex: João Gervásio" required>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <label class="col-form-label">Username:</label>
                </div>
                <div class="col-md-6 d-inline-flex">
                    <div class="input-group-prepend">
                        <div class="input-group-text">pt</div>
                    </div>
                    <input type="text" class="form-control" name="username" placeholder="Ex: gervasio98" required>
                </div>
            </div>
            <div class="row">
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
                    <label class="col-form-label">Certificado:</label>
                </div>
                <div class="col-md-6 custom-file">
                    <input type="file" class="custom-file-input" name="certificate" onchange="document.getElementById('metodoA').innerHTML = this.value.substring(this.value.lastIndexOf('\\') + 1, this.value.length)" required>
                    <label class="custom-file-label">Ex: <span class="text-primary">cert-pt.cert</span></label>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <label class="col-form-label">Especialidade:</label>
                </div>
                <div class="col-md-6">
                    <select class="custom-select" name="skill" required>
                        <option value="c">Cardio</option>
                        <option value="m">Musculação</option>
                        <option value="a">Ambos</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <label class="col-form-label">Preço:</label>
                </div>
                <div class="col-md-6">
                    <input type="number" pattern="\d+.dd" class="form-control" name="price" placeholder="Ex: 9.99" required>
                </div>
                *O preço colocado será o preço por workout.
            </div>
        </div>
    </div>
    <div class="row justify-content-end">
        <div class="col-md-3">
            <button type="submit" class="btn-block">Registar</button>
        </div>
    </div>
</form>
