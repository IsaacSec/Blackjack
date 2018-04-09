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

    <script>

        var unicodes = [
            "🂡", "🂢", "🂣", "🂤", "🂥", "🂦", "🂧", "🂨", "🂩", "🂪", "🂫", "🂭", "🂮",
            "🂱", "🂲", "🂳", "🂴", "🂵", "🂶", "🂷", "🂸", "🂹", "🂺", "🂻", "🂽", "🂾",
            "🃁", "🃂", "🃃", "🃄", "🃅", "🃆", "🃇", "🃈", "🃉", "🃊", "🃋", "🃍", "🃎",
            "🃑", "🃒", "🃓", "🃔", "🃕", "🃖", "🃗", "🃘", "🃙", "🃚", "🃛", "🃝", "🃞"
        ];

        function load() {
            var xmlhttp = new XMLHttpRequest();
            var url = "/getGameInfo?roomName=<%=user.getRoomName()%>";

            xmlhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200){
                    //show rooms table
                    showGameTable(this);
                    //showRoomsTable(this);
                }
            };

            xmlhttp.open("GET",url,true);
            xmlhttp.send();
        }

        function hit() {
            var xmlhttp = new XMLHttpRequest();
            var url = "/hit?roomName=<%=user.getRoomName()%>";

            xmlhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200){
                    load();
                }
            };

            xmlhttp.open("POST",url,true);
            xmlhttp.send();
        }

        function stand() {
            var xmlhttp = new XMLHttpRequest();
            var url = "/stand?roomName=<%=user.getRoomName()%>";

            xmlhttp.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200){
                    load();
                }
            };

            xmlhttp.open("POST",url,true);
            xmlhttp.send();
        }

        function showGameTable(xml) {
            console.log(xml.responseText);
            var jsonResponse = JSON.parse(xml.responseText);
            var players = jsonResponse['game'].playersInfo;

            var htmlTable =
                "<table class='table' border='1'>" +
                "<thead>" +
                "<tr>" +
                "<th>  #  </th>" +
                "<th>  Nickname  </th>" +
                "<th>  Status  </th>" +
                "<th>  Cards  </th></tr>" +
                "</thead>" +
                "<tbody>";

            for (var i = 0; i < players.length; i++) {
                var cards = "";

                var playerCards = players[i].cards;

                for (var j = 0; j < playerCards.length; j++) {
                    console.log("Code: ["+playerCards[j].code+"]")
                    cards += unicodes[playerCards[j].code];
                }

                htmlTable +=
                "<tr>" +
                "<td>"+ i +"</td>" +
                "<td>"+ players[i].nickname +"</td>" +
                "<td>"+ players[i].state +"</td>" +
                "<td>" +cards+"</td>" +
                "</tr> " ;
            }

            htmlTable += "</tbody></table></form>";
            document.getElementById("gameTable").innerHTML = htmlTable;

            showWinner(players);

        }

        function showWinner(players) {

            for (var i = 0; i < players.length; i++) {
                if (players[i].state != "FINISHED"){
                    return;
                }
            }

            var winners = getWinner(players);
            //if (winners != undefined && winners != [])
                document.getElementById("winner").innerHTML = "The winner is: "+winners;
        }

        function getWinner(players){

            var handValues = [];

            for (var i = 0; i < players.length; i++) {
                var playerCards = players[i].cards;
                var handValue = 0;

                for (var j = 0; j < playerCards.length; j++) {
                    handValue += (playerCards[j].value/1);
                }

                handValues.push(handValue);
            }

            var winners = "";

            for (var i=0; i<handValues.length; i++) {
                if (handValues[i] == 21){
                    winners += ""+players[i].nickname;
                    return;
                }
            }

            var max = 0;
            var maxIndex = 0;

            for (var i=0; i<handValues.length; i++) {
                if (handValues[i] > max && handValues[i] < 22){
                    max = handValues[i];
                    maxIndex = i;
                }
            }

            console.log(handValues);

            winners += ""+players[maxIndex].nickname;
            return winners;

        }


        load();
        window.setInterval(load, 1000);

    </script>


    Room: <%=user.getRoomName()%>
    <br>
    <br>
    <button id="hit" onclick="hit()">Hit</button>
    <button id="stand" onclick="stand()">Stand</button>
    <br>
    <form action="/exitRoom" method="POST">
        <input type="text" name="roomName" hidden value="<%=user.getRoomName()%>">
        <button type="submit">Exit</button>
    </form>
    <br>
    <br>
    <div id="winner">

    </div>
    <br>
    <div id="gameTable">

    </div>
</body>
</html>
