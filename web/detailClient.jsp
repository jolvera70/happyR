  <%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 05/08/2015
  Time: 06:12 PM
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
<form action="http://localhost:9998/happy/event/add" name="addEvent">
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
      <td><input name="name" type="text" value="<%=request.getParameter("name")%>" readonly></td>
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
      <td>
        <input name="imageName" type="text" value="<%=request.getParameter("imageName")%>" readonly></td>
      <input name="image" type="hidden" value="" readonly></td>
    </tr>
    <tr>
      <td>
        <input type="submit" name="enviar" value="enviar">
      </td>
    </tr>
    <%
      String events = "";
      boolean existEvents = false;
      try {

        URL oracle = new URL("http://localhost:9998/happy/event/view?name="+request.getParameter("name"));
        BufferedReader br = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String line;
        while ((line = br.readLine()) != null) {
          events += line + "\n";
        }
        if (events.indexOf("error") == -1) {
          existEvents = true;
        }
    %>
    <%
        br.close();
      } catch (Exception e) {
        e.printStackTrace();
      }%>
  </table>
</form>

<form method="post" name="loadImage" id="loadImage" action="UploadServlet?name=<%=request.getParameter("name")%>" enctype="multipart/form-data">
  Select file to upload:
  <input type="file" name="dataFile" id="fileChooser"/><br/><br/>
  <input type="submit" value="Upload" />
</form>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
  var eventsList = <%=events%>;
  var existEvents = <%=existEvents%>;
  $(document).ready(function () {
    if (existEvents) {
      var table = $('<table/>').appendTo($('#eventsdiv')).attr('border', '1');
      $(eventsList).each(function (i, event) {
        $(event.event).each(function (i, element) {
          $('<tr/>').appendTo(table)
                  .append($('<td/>').text(element.id_e))
                  .append($('<td/>').text(element.type_e))
                  .append($('<td/>').text(element.comment_e))
                  .append($('<td/>').prepend('<img src="data:image/png;base64,' + element.image_e + '"/>'))
                  .append($('<td/>').prepend('<a href="/detailEvent.jsp?name=<%=request.getParameter("name")%>&id=' + element.id_e + '">editar</a href>'))
                  .append($('<td/>').prepend('<a href="http://localhost:9998/happy/event/delete?name=<%=request.getParameter("name")%>&id=' + element.id_e + '">borrar</a href>'));
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
