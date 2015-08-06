<%@ page import="java.net.URL" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 03/08/2015
  Time: 04:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Event form</title>
</head>


<body>
<form action="http://localhost:9998/happy/client/add">
    <table>
        <tr>
            <td><input name="id" type="hidden" value="0"></td>
        </tr>
        <tr>
            <td>
                name
            </td>
            <td><input name="name" type="text" value=""></td>
        </tr>
        <tr>
            <td>
                <input type="submit" name="enviar" value="enviar">
            </td>
        </tr>
        <%
            String clients = "";
            boolean existClients = false;
            try {

                URL oracle = new URL("http://localhost:9998/happy/client/view");
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(oracle.openStream()));

                String line;
                while ((line = br.readLine()) != null) {
                    clients += line + "\n";
                }
                if (clients.indexOf("error") == -1) {
                    existClients = true;
                }
        %>
        <%
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }%>
    </table>
</form>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
    var clientsList = <%=clients%>;
    var existClients = <%=existClients%>;
    $(document).ready(function () {
        if (existClients) {
            var tableClients = $('<table/>').appendTo($('#clientsdiv')).attr('border', '1');
            $(clientsList).each(function (i, clientes) {
                $(clientes.client).each(function (i, element) {
                    $('<tr/>').appendTo(tableClients)
                            .append($('<td/>').text(element.name))
                            .append($('<td/>').prepend('<a href="/detailClient.jsp?name=' + element.name + '">detalle</a href>'))
                            .append($('<td/>').prepend('<a href="http://localhost:9998/happy/client/delete?name=' + element.name + '&id=' + element.id_e + '">borrar</a href>'));

                });
            });
        }
        var tableClients = $('<table/>').appendTo($('#clientsdiv'));
        $(clientsList).each(function (i, clientes) {
            $(clientes.client).each(function (i, element) {
                $('<tr/>').appendTo(tableClients)
                        .append($('<td/>').text(element.error))
            });
        });
    });
</script>
</head>
<div id="clientsdiv"></div>
</body>
</html>
