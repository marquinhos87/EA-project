<%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row justify-content-center">
    <div class="col-md-10">
        <label class="my-2"><h3>Definir tipo de plano pretendido</h3></label>
        <form method="post" action="${pageContext.request.contextPath}\MakeRequest">
            <div class="form-group row">
                <div class="col-md-8 d-inline-flex">
                    <label class="col-form-label col-md-6">Nº de semanas (disponibilidade):</label>
                    <div class="col-md-6">
                        <select class="custom-select" name="numberOfWeeks" required>
                            <option value="1" selected>1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-8 d-inline-flex">
                    <label class="col-form-label col-md-6">Objetivo:</label>
                    <div class="col-md-6">
                        <select class="custom-select" name="objective" required>
                            <option value="Ganhar Musculo" selected>Ganhar Músculo</option>
                            <option value="Flexibilidade">Flexibilidade</option>
                            <option value="Perder Peso">Perder Peso</option>
                            <option value="Resistência">Resistência</option>
                            <option value="Transformar">Transformar</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-8 d-inline-flex">
                    <label class="col-form-label col-md-6">Nº de treinos semanais:</label>
                    <div class="col-md-6">
                        <select class="custom-select" name="workoutPerWeek" required>
                            <option value="1" selected>1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-12 d-inline-flex align-middle">
                    <label class="col-form-label col-md-4">Disponibilidade semanal:</label>
                    <div class="form-check pd-2 pr-3 align-middle">
                        <input class="form-check-input" type="checkbox" value="1" name="weekDay[]">
                        <label class="form-check-label">
                            2ª
                        </label>
                    </div>
                    <div class="form-check pr-3 align-middle">
                        <input class="form-check-input" type="checkbox" value="2" name="weekDay[]">
                        <label class="form-check-label">
                            3ª
                        </label>
                    </div>
                    <div class="form-check pr-3 align-middle">
                        <input class="form-check-input" type="checkbox" value="3" name="weekDay[]">
                        <label class="form-check-label">
                            4ª
                        </label>
                    </div>
                    <div class="form-check pr-3 align-middle">
                        <input class="form-check-input" type="checkbox" value="4" name="weekDay[]">
                        <label class="form-check-label">
                            5ª
                        </label>
                    </div>
                    <div class="form-check pr-3 align-middle">
                        <input class="form-check-input" type="checkbox" value="5" name="weekDay[]">
                        <label class="form-check-label">
                            6ª
                        </label>
                    </div>
                    <div class="form-check pr-3 align-middle">
                        <input class="form-check-input" type="checkbox" value="6" name="weekDay[]">
                        <label class="form-check-label">
                            Sab
                        </label>
                    </div>
                    <div class="form-check pr-3 align-middle">
                        <input class="form-check-input" type="checkbox" value="1" name="weekDay[]">
                        <label class="form-check-label">
                            Dom
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <div class="col-md-8 d-inline-flex">
                    <label class="col-form-label col-md-6">Dificuldade:</label>
                    <div class="col-md-6">
                        <select class="custom-select" name="dificulty" required>
                            <option value="Facil" selected>Fácil</option>
                            <option value="Normal">Normal</option>
                            <option value="Dificil">Díficil</option>
                            <option value="Extremo">Extremo</option>
                        </select>
                    </div>
                </div>
            </div>
            <div class="form-group row justify-content-end">
                <div class="col-md-3">
                    <button type="submit" class="btn btn-primary btn-block text-white font-weight-normal border-0 mb-3" name="personalTrainerUsername" value="${requestScope.personalTrainerUsername}">Submeter</button>
                </div>
            </div>
        </form>
    </div>
</div>