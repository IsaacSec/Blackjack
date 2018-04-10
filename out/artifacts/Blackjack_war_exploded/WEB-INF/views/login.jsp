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
        Please login with your nickname:
        <br>
        <form action="/logIn" method="POST">
            Nickname: <input type="text" name="nickname"><br>
            Password: <input type="text" name="password"><br>
            <button type="submit">Log In</button>
        </form>

        <br>
        Register an account:
        <br>
        <form action="/signIn" method="POST">
            Nickname: <input type="text" name="nickname"><br>
            Password: <input type="text" name="password"><br>
            Name: <input type="text" name="name"><br>
            <button type="submit">Sign In</button>
        </form>

        <br>
        ${errorCode}
        <br>
        ${errorMessage}
    </body>
</html>
