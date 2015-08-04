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
    </table>
    <table>
        <tr>
            <td><input type="button" name="getEvents" value="getEvents Luis"></td>
        </tr>
    </table>
</form>
</body>
</html>
