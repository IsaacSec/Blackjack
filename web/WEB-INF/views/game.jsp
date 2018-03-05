<%@ page import="data.model.User" %><%--
  Created by IntelliJ IDEA.
  User: isaac
  Date: 3/4/18
  Time: 3:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Game Playground</title>
</head>

<%
    User user = (User) session.getAttribute("user");
    
%>

<body>
    Room: <%=user.getRoomName()%>
    <br>
    <br>
    <button id="hit" onclick="">Hit</button>
    <button id="stand" onclick="">Stand</button>
    <br>
    <form action="/exitRoom" method="POST">
        <input type="text" name="nickname" hidden value="<%=user.getNickname()%>">
        <button type="submit">Exit</button>
    </form>
</body>
</html>
