<%@ page import="core.Notification" %>
<%@ page import="java.util.Collection" %><%--
  Created by IntelliJ IDEA.
  User: joaomarques
  Date: 06/07/2020
  Time: 23:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script language="JavaScript">
    function selectAll(source,type) {
        checkboxes = document.getElementsByName(type);
        for(var i in checkboxes)
            checkboxes[i].checked = source.checked;
    }
</script>

<% if(request.getAttribute("notifications") != null && ((Collection<Notification>)request.getAttribute("notifications")).size() != 0) {%>
    <div class="row justify-content-end">
        <div class="col-md-3">
            <form method="post" action="${pageContext.request.contextPath}\Notification">
                <button type="submit" class="btn btn-primary btn-block text-white font-weight-normal border-0 mb-3">Marcar como lidas</button>
            </form>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-md-10">
            <label><h3>Notificações</h3></label>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th style="width: 10%"><input class="form-check-input" type="checkbox" onClick="selectAll(this,'notificationId[]')"></th>
                        <th style="width: 20%;">Data</th>
                        <th style="width: 70%;">Descrição</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        Collection<Notification> notificationss = (Collection<Notification>) request.getAttribute("notifications");
                        for(Notification notification: notificationss) {
                            if(notification.getRead())
                                out.print("<tr class=\"bg-light\">");
                            else
                                out.print("<tr>");
                            out.print("<td><input class=\"form-check-input\" type=\"checkbox\" value=\"" + notification.getID() + "\" name=\"notificationId[]\"></td>");
                            out.print("<td>" + notification.getDate() + "</td>");
                            out.print("<td>" + notification.getDescription() + "</td>");
                            out.print("</tr>");
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
<% } else { %>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="alert alert-warning" role="alert">
                <label><h4>Atualmente sem notificações.</h4></label>
            </div>
        </div>
    </div>
<% } %>