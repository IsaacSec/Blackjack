<%@ page import="data.model.User" %><%--
  Created by IntelliJ IDEA.
  User: isaac
  Date: 3/3/18
  Time: 5:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true"%>
<html>
<head>
    <title>iSac Lobby</title>
</head>
<body>
    <%
        User user = (User) session.getAttribute("user");
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
            console.log(xml.responseText);
            var jsonResponse = JSON.parse(xml.responseText);

            var rooms = jsonResponse['rooms'];
            var htmlTable =
                "<table class='table' border='1'>" +
                    "<thead>" +
                        "<tr>" +
                            "<th>  #  </th>" +
                            "<th>  Room Name  </th>" +
                            "<th>  Players  </th>" +
                            "<th>  Join Room  </th></tr>" +
                    "</thead>" +
                "<tbody>";

            for (var i = 0; i < rooms.length; i++) {
                var joinUrl = "\"/join?roomName="+rooms[i].roomName+"\"";
                console.log(joinUrl);
                htmlTable +=
                    "<tr>" +
                        "<td>"+ i +"</td>" +
                        "<td>"+ rooms[i].roomName +"</td>" +
                        "<td>"+ rooms[i].playerCounter +"</td>" +
                        "<td>" +
                            "<form action="+joinUrl+" method=\"POST\">" +
                                "<button type=\"submit\">Join</button>" +
                            "</form>" +
                        "</td>" +
                    "</tr> " ;
            }

            htmlTable += "</tbody></table></form>";
            document.getElementById("roomTable").innerHTML = htmlTable;

        }
        
        function createRoom() {

            var input = document.getElementById("roomName");
            var roomName = input.value;

            var xmlhttp = new XMLHttpRequest();
            var url = "/createRoom?roomName="+roomName;

            xmlhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200){
                    //show rooms table
                    console.log("Lobby creation request sent")
                }
            };

            xmlhttp.open("POST",url,true);
            xmlhttp.send();
        }
        
        load();
        window.setInterval(load, 1000);
    
    </script>

    Welcome to casino <%=user.getNickname()%>
    <br>
    <br>
    Create Room
    <br>
    Room Name:
    <input type="text" id="roomName">
    <button onclick="createRoom()">Create</button>
    <br>
    <form action="/signOut" method="POST">
        <button type="submit">Exit</button>
    </form>
    <br>
    <br>
    <div id="roomTable">

    </div>
</body>
</html>
