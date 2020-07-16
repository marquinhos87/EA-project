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
        <table>
            <thead>
                <tr>
                    <th style="width: 50%;"><h4>Dados Biométricos</h4></th>
                    <th style="width: 50%;"></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>IMC: ${requestScope.bci}</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Altura (cm): ${requestScope.height}</td>
                    <td>Gémeo (cm): ${requestScope.twin}</td>
                </tr>
                <tr>
                    <td>Peso (kg): ${requestScope.weight}</td>
                    <td>Quadricep (cm): ${requestScope.quadricep}</td>
                </tr>
                <tr>
                    <td>Cintura (cm): ${requestScope.waist}</td>
                    <td>Tricep (cm): ${requestScope.tricep}</td>
                </tr>
                <tr>
                    <td>Peito (cm): ${requestScope.chest}</td>
                    <td>Pulso (cm): ${requestScope.wrist}</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<div class="row justify-content-end">
    <div class="col-md-3">
        <button type="submit" formmethod="get" formaction="${pageContext.request.contextPath}\MyRequests" class="btn btn-danger btn-block text-white font-weight-normal border-0 mb-3">Voltar</button>
    </div>
</div>