<%--
  Created by IntelliJ IDEA.
  User: aswonder
  Date: 03.10.2019
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Привет, Амиго!</title>
  </head>
  <body>
  <h1>Вся власть роботам!</h1>
  <hr>
  <%
    //А тут можно писать Java-код

    String s = "Вся власть роботам!";
    for(int i=0; i<10; i++)
    {
  %>
      <p>Амиго N <%= i%> </p>
  <%
    }
  %>
  </body>
</html>
