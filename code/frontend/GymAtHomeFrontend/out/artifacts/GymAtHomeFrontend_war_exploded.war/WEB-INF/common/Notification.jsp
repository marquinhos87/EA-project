<%@ page import="core.Notification" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.Date" %><%--
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
    <form method="post" action="${pageContext.request.contextPath}\Notification">
        <div class="row justify-content-end">
            <div class="col-md-3">
                    <button type="submit" class="btn btn-primary btn-block text-white font-weight-normal border-0 mb-3">Marcar como lidas</button>
            </div>
        </div>
    <div class="row justify-content-center">
        <div class="col-md-10">
            <table class="table">
                <thead>
                    <tr>
                        <td style="width: 10%"><div class="form-check"><input class="form-check-input" type="checkbox" onClick="selectAll(this,'notificationId[]')"></div></td>
                        <th style="width: 20%;">Data</th>
                        <th style="width: 20%;">Estado</th>
                        <th style="width: 70%;">Descrição</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        Collection<Notification> notificationss = (Collection<Notification>) request.getAttribute("notifications");
                        for(Notification notification: notificationss) {
                            if(notification.getRead()) {
                                out.print("<tr>");
                                out.print("<td><div class=\"form-check\"><input class=\"form-check-input\" type=\"checkbox\"></div></td>");
                                out.print("<td>" + new SimpleDateFormat("dd-MM-yyyy").format(notification.getDate()) + "</td>");
                                out.print("<td>lida</td>");
                                out.print("<td>" + notification.getDescription() + "</td>");
                                out.print("</tr>");
                            }
                            else{
                                out.print("<tr style=\"background-color: #E8E8E8;\">");
                                out.print("<td><div class=\"form-check\"><input class=\"form-check-input\" type=\"checkbox\" value=\"" + notification.getID() + "\" name=\"notificationId[]\"></div></td>");
                                out.print("<td>" + new SimpleDateFormat("dd-MM-yyyy").format(notification.getDate()) + "</td>");
                                out.print("<td>não lida</td>");
                                out.print("<td>" + notification.getDescription() + "</td>");
                                out.print("</tr>");
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
    </form>
<% } else { %>
    <div class="justify-content-center my-5"><h4 class="text-center">Não existem notificações de momento.</h4></div>
<% } %>