<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 06/08/2015
  Time: 11:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.net.URL" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
    String event = "";
    boolean existEvents = false;
    try {

        URL oracle = new URL("http://localhost:9998/happy/event/view/detail?name=" + request.getParameter("name") + "&id=" + request.getParameter("id"));
        BufferedReader br = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String line;
        while ((line = br.readLine()) != null) {
            event += line + "\n";
        }
        if (event.indexOf("error") == -1) {
            existEvents = true;
        }
%>
<%
        br.close();
    } catch (Exception e) {
        e.printStackTrace();
    }%>
<form action="http://localhost:9998/happy/event/update" id="formAdd" name="formAdd">
</form>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
    var Listevent = <%=event%>;
    var existEvents = <%=existEvents%>;
    $(document).ready(function () {
        if (existEvents) {
            var table = $('<table/>').appendTo($('#formAdd')).attr('border', '0');
            $(Listevent).each(function (i, event) {
                $(event.event).each(function (i, element) {
                    var idInt = parseInt(element.id_e);
                    $('<tr/>').appendTo(table)
                            .append($('<td/>').prepend('<input type="hidden" name="id" value="' + idInt + '">'));
                    $('<tr/>').appendTo(table)
                            .append($('<td/>').text('name'))
                            .append($('<td/>').prepend('<input type="text" name="name" value=<%=request.getParameter("name")%> readonly">'));
                    $('<tr/>').appendTo(table)
                            .append($('<td/>').text('type'))
                            .append($('<td/>').prepend('<input type="text" name="type" value="' + element.type_e + '">'));
                    $('<tr/>').appendTo(table)
                            .append($('<td/>').text('comment'))
                            .append($('<td/>').prepend('<input type="text" name="comment" value="' + element.comment_e + '">'));
                    $('<tr/>').appendTo(table)
                            .append($('<td/>').text('image'))
                            .append($('<td/>').prepend('<input type="text" name="image" value="' + element.image_e + '">'));
                    $('<tr/>').appendTo(table)
                            .append($('<td/>').prepend('<input type="submit" name="enviar" value="enviar">'));
                });
            });
        }
        var table = $('<table/>').appendTo($('#eventsdiv'));
        $(eventsList).each(function (i, event) {
            $(event.event).each(function (i, element) {
                $('<tr/>').appendTo(table)
                        .append($('<td/>').text(element.error))
            });
        });
    });
</script>

</head>
<div id="eventsdiv"></div>
</body>
</html>
