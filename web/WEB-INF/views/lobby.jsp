<%@ page import="data.model.User" %><%--
  Created by IntelliJ IDEA.
  User: isaac
  Date: 3/3/18
  Time: 5:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
<html>
<head>
    <title>iSac Lobby</title>
</head>
<body>

    <%
        ;
    %>

    <script>
        
        function load() {
            var xmlhttp = new XMLHttpRequest();
            var url = "/getRooms";
            xmlhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200){
                    //show rooms table
                    showRoomsTable(this);
                }
            };
            xmlhttp.open("GET",url,true);
            xmlhttp.send();
        }
        
        function showRoomsTable(xml) {

            var jsonResponse = JSON.parse(xml.responseText);
            var rooms = jsonResponse['rooms'];
            var htmlTable = "<form  method=\"post\"> <table class='table' border='1'> <thead><tr><th> Room Number</th><th> Room Name</th><th> # Players</th><th>Join Room</th></tr></thead><tbody>";

            for (var i = 0; i < rooms.length; i++) {
                htmlTable += "<tr><td> <input class='form-control-plaintext' name='rowNumber' readonly value='"+i+"'/></td><td> <input class='form-control-plaintext' readonly value='"+rooms[i].roomID +"'/></td><td>"+rooms[i].numberOfPlayers +"</td><td><div class=\"form-group mx-sm-3 mb-2\"><button type=\"submit\"  formaction=\"/BlackJack/joinRoom?roomID="+rooms[i].roomID+" \" class=\"btn btn-primary mb-2\" >Unirse</button></td></div></tr> " ;
            }

            htmlTable += "</tbody></table></form>";
            document.getElementById("table").innerHTML = htmlTable;
        }
        
        function createRoom() {

            var input = document.getElementById("roomName");
            var roomName = input.getAttribute("value");

            var xmlhttp = new XMLHttpRequest();
            var url = "/createRoom&roomName="+roomName;

            xmlhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200){
                    //show rooms table
                    showRoomsTable(this);
                }
            };

            xmlhttp.open("POST",url,true);
            xmlhttp.send();
        }
        
        load();
        window.setInterval(load, 1000);
    
    </script>

    Welcome to casino
    <br>
    <br>
    Create Room
    <br>
    Room Name:
    <input type="text" id="roomName">
    <button onclick="">Create</button>
</body>
</html>
