<%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-1"></div>
<div class="col-md-10">
    <form class="mt-2" method="post" action="${pageContext.request.contextPath}\ClientRegister">
        <div class="form-group row">
            <div class="col-md-6">
                <label><h3>Username</h3></label>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-md-6">
                <label><h3>Dados Pessoais</h3></label>
            </div>
            <div class="col-md-6">
                <label><h3>Dados Biométricos</h3></label>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-md-6 d-inline-flex">
                <label class="col-md-5 col-form-label">Nome:</label>
                <div class="col-md-7">
                    <input type="text" class="form-control" name="name" placeholder="Ex: Gervásio">
                </div>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-md-6 d-inline-flex">
                <label class="col-md-5 col-form-label">Email:</label>
                <div class="col-md-7">
                    <input type="email" class="form-control" name="email" placeholder="Ex: gervasio@exemplo.com">
                </div>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-md-6 d-inline-flex">
                <label class="col-md-5 col-form-label">Género:</label>
                <div class="col-md-7">
                    <select class="custom-select" name="genre">
                        <option value="m">Masculino</option>
                        <option value="f">Feminino</option>
                        <option value="o">Outro</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="form-group row">
            <div class="col-md-6 d-inline-flex">
                <label class="col-md-5 col-form-label">Data de Nascimento:</label>
                <div class="col-md-7">
                    <div id="date-picker-example" class="md-form md-outline input-with-post-icon datepicker" inline="true">
                        <input type="text" class="form-control" name="birthday" placeholder="Selecione a data">
                        <i class="fas fa-calendar input-prefix"></i>
                    </div>
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






        <div class="form-group row">
            <div class="col-sm-6 d-inline-flex">
                <label class="col-md-5 col-form-label">Cintura (cm):</label>
                <div class="col-md-7">
                    <input type="number" pattern="\d+" min="40" class="form-control" name="waist" placeholder="Ex: 78">
                </div>
            </div>
        </div>
        <div class="form-group row">

            <div class="col-md-6 d-inline-flex">
                <label class="col-md-5 col-form-label">Peito (cm):</label>
                <div class="col-md-7">
                    <input type="number" pattern="\d+" min="50" class="form-control" name="chest" placeholder="Ex: 87">
                </div>
            </div>
        </div>
        <div class="form-group row">

            <div class="col-md-6 d-inline-flex">
                <label class="col-md-5 col-form-label">Gémeo (cm):</label>
                <div class="col-md-7">
                    <input type="number" pattern="\d+" min="15" class="form-control" name="twin" placeholder="Ex: 36">
                </div>
            </div>
        </div>
        <div class="form-group row">

            <div class="col-md-6 d-inline-flex">
                <label class="col-md-5 col-form-label">Quadricep (cm):</label>
                <div class="col-md-7">
                    <input type="number" pattern="\d+" min="20" class="form-control" name="quadriceps" placeholder="Ex: 51">
                </div>
            </div>
        </div>
        <div class="form-group row">

            <div class="col-md-6 d-inline-flex">
                <label class="col-md-5 col-form-label">Tricep (cm):</label>
                <div class="col-md-7">
                    <input type="number" pattern="\d+" min="10" class="form-control" name="triceps" placeholder="Ex: 28">
                </div>
            </div>
        </div>
        <div class="form-group row">

            <div class="col-md-6 d-inline-flex">
                <label class="col-md-5 col-form-label">Pulso (cm):</label>
                <div class="col-md-7">
                    <input type="number" pattern="\d+" min="5" class="form-control" name="wrist" placeholder="Ex: 15">
                </div>
            </div>
        </div>
        <div class="form-group row">

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
<div class="col-md-1"></div>