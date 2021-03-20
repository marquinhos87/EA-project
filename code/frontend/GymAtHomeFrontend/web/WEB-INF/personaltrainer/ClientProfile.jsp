<%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="row">
    <div class="col-md-1"></div>
    <div class="col-md-5">
        <div class="row">
            <label><h3>Cliente: ${requestScope.name}</h3></label>
        </div>
        <table>
            <tbody>
                <tr>
                    <td>Idade: ${requestScope.age}</td>
                </tr>
                <tr>
                    <td>Género: ${requestScope.genre}</td>
                </tr>
                <tr>
                    <td>Email: ${requestScope.email}</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<div class="row justify-content-center">
    <div class="col-md-10">
        <div class="row">
            <table>
                <thead>
                    <tr>
                        <th style="width: 50%;"><h4>Dados Biométricos</h4></th>
                        <th style="width: 50%;"></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Altura (cm): ${requestScope.height}</td>
                        <td>Gémeo (cm): ${requestScope.twin!=0 ? requestScope.twin : "sem informação registada"}</td>
                    </tr>
                    <tr>
                        <td>Peso (kg): ${requestScope.weight}</td>
                        <td>Quadricep (cm): ${requestScope.quadricep!=0 ? requestScope.quadricep : "sem informação registada"}</td>
                    </tr>
                    <tr>
                        <td>Cintura (cm): ${requestScope.waist!=0 ? requestScope.waist : "sem informação registada"}</td>
                        <td>Tricep (cm): ${requestScope.tricep!=0 ? requestScope.tricep : "sem informação registada"}</td>
                    </tr>
                    <tr>
                        <td>Peito (cm): ${requestScope.chest!=0 ? requestScope.chest : "sem informação registada"}</td>
                        <td>Pulso (cm): ${requestScope.wrist!=0 ? requestScope.wrist : "sem informação registada"}</td>
                    </tr>
                    <tr>
                        <td>Índice de Massa Corporal: ${requestScope.bci}</td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </div>
        <!-- prints BMI categories on the progress bar -->
        <div class="mt-4 progress" style="height:25px">
            <div class="progress-bar <% if (biometricData.BMI < 18.5) out.print("progress-bar-striped progress-bar-animated"); %>" role="progressbar" style="width: 16.6%" aria-valuenow="16.6" aria-valuemin="0" aria-valuemax="100">subnutrido</div>
            <div class="progress-bar <% if (biometricData.BMI >= 18.5 && biometricData.BMI < 25) out.print("progress-bar-striped progress-bar-animated"); %> bg-success" role="progressbar" style="width: 16.6%" aria-valuenow="16.6" aria-valuemin="0" aria-valuemax="100">saudável</div>
            <div class="progress-bar <% if (biometricData.BMI >= 25 && biometricData.BMI < 30) out.print("progress-bar-striped progress-bar-animated"); %> bg-warning text-dark" role="progressbar" style="width: 16.6%" aria-valuenow="16.6" aria-valuemin="0" aria-valuemax="100">sobrepeso</div>
            <div class="progress-bar <% if (biometricData.BMI >= 30 && biometricData.BMI < 35) out.print("progress-bar-striped progress-bar-animated"); %>" role="progressbar" style="width: 16.6%; background-color: rgb(235, 100, 0);" aria-valuenow="16.6" aria-valuemin="0" aria-valuemax="100">obesidade grau I</div>
            <div class="progress-bar <% if (biometricData.BMI >= 35 && biometricData.BMI < 40) out.print("progress-bar-striped progress-bar-animated"); %>" role="progressbar" style="width: 16.6%; background-color: rgb(180, 50, 0);" aria-valuenow="16.6" aria-valuemin="0" aria-valuemax="100">obesidade grau II (severa)</div>
            <div class="progress-bar <% if (biometricData.BMI >= 40) out.print("progress-bar-striped progress-bar-animated"); %>" role="progressbar" style="width: 17%; background-color: rgb(255, 0, 0);" aria-valuenow="17" aria-valuemin="0" aria-valuemax="100">obesidade grau III (morbida)</div>
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
            <div class="col" style="width: 14.3%"><% if (biometricData.BMI < 18.5) out.print("<i style='font-size:24px' class='fas'>&#xf102;</i>"); %></div>
            <div class="col" style="width: 14.3%"><% if (biometricData.BMI >= 18.5 && biometricData.BMI < 25) out.print("<i style='font-size:24px' class='fas'>&#xf102;</i>"); %></div>
            <div class="col" style="width: 14.3%"><% if (biometricData.BMI >= 25 && biometricData.BMI < 30) out.print("<i style='font-size:24px' class='fas'>&#xf102;</i>"); %></div>
            <div class="col" style="width: 14.3%"><% if (biometricData.BMI >= 30 && biometricData.BMI < 35) out.print("<i style='font-size:24px' class='fas'>&#xf102;</i>"); %></div>
            <div class="col" style="width: 14.3%"><% if (biometricData.BMI >= 35 && biometricData.BMI < 40) out.print("<i style='font-size:24px' class='fas'>&#xf102;</i>"); %></div>
            <div class="col" style="width: 14.2%"><% if (biometricData.BMI >= 40) out.print("<i style='font-size:24px' class='fas'>&#xf102;</i>"); %></div>
        </div>
    </div>
</div>
<div class="row justify-content-end">
    <div class="col-md-3">
        <button type="submit" formmethod="get" formaction="${pageContext.request.contextPath}\MyRequestsPT" class="btn btn-danger btn-block text-white font-weight-normal border-0 mb-3">Voltar</button>
    </div>
</div>