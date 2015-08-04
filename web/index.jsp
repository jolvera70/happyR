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
        <tr>
            </td>aqui
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
            <%=string%>
            <%
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }%>
            </td>
        </tr>
    </table>
</form>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
    var eventsList = <%=string%>;
    $(document).ready(function() {
        var obj = jQuery.parseJSON( '{ "name": "John" }' );
        alert( obj.name );
        var table = $('<table/>').appendTo($('#somediv'));
        $(eventsList).each(function(i, event) {
            alert(event.event.valueOf());
            var obj2 = jQuery.parseJSON( event.event.get(0) );
            alert(obj2.type);
            $('<tr/>').appendTo(table)
                    .append($('<td/>').text(obj2.type))
        });
    });
</script>
</head>

<div id="somediv"></div>
</body>
</html>
