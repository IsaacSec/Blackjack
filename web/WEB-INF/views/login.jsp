<%--
  Created by IntelliJ IDEA.
  User: isaac
  Date: 3/3/18
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
    </head>

    <body>
        Welcome to iSac Casino
        <br>
        <br>
        Please introduce a nickname:
        <br>
        <form action="/lobby">
            <input type="text" name="nickname">
            <button type="submit">Enter</button>
        </form>
    </body>
</html>
