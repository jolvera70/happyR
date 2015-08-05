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
<form action="http://localhost:9998/event/add">
    <table>
        <tr>
            <td>
                id
            </td>
            <td><input name="id" type="text" value=""></td>
        </tr>
        <tr>
            <td>
                name
            </td>
            <td><input name="name" type="text" value=""></td>
        </tr>
        <tr>
            <td>
                type
            </td>
            <td><input name="type" type="text" value=""></td>
        </tr>
        <tr>
            <td>
                comment
            </td>
            <td><input name="comment" type="text" value=""></td>
        </tr>
        <tr>
            <td>
                image
            </td>
            <td><input name="image" type="text" value=""></td>
        </tr>
        <tr>
            <td>
                <input type="submit" name="enviar" value="enviar">
            </td>
        </tr>
        <%
            String string = "";
            try {

                URL oracle = new URL("http://localhost:9998/event/view/client?name=Luis");
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(oracle.openStream()));

                String line;
                while ((line = br.readLine()) != null) {
                    string += line + "\n";
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
    var eventsList = <%=string%>;
    $(document).ready(function () {
        var table = $('<table/>').appendTo($('#somediv')).attr('border', '1');
        $(eventsList).each(function (i, event) {
            $(event.event).each(function (i, element) {
                $('<tr/>').appendTo(table)
                        .append($('<td/>').text(element.id_e))
                        .append($('<td/>').text(element.type_e))
                        .append($('<td/>').text(element.comment_e))
                        .append($('<td/>').prepend('<img src="data:image/png;base64,' + element.image_e + '"/>'));
            });
        });
        var table = $('<table/>').appendTo($('#somediv'));
        $(eventsList).each(function (i, event) {
            $('<tr/>').appendTo(table)
                    .append($('<td/>').text(event.error))
        });
    });
</script>
</head>

<div id="somediv"></div>
</body>
</html>
