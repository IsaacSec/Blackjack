package data;

import data.model.*;
import data.model.states.PlayerState;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class VirtualStorage {

    private static Hashtable <String,User> users = new Hashtable<>();
    private static Hashtable <String,Room> rooms = new Hashtable<>();

    private static String ROOMS_JSON_FORMAT =
        "{" +
            "\"%s\":[%s]" +
        "}";

    private static String GAME_JSON_FORMAT =
            "{" +
                "\"%s\":%s" +
            "}";

    public static void addNewUser(User user){
        users.put(user.getNickname(), user);
        System.out.println("Users: "+users.keySet().toString());
    }

    public static void removeUser(String nickname){
        System.out.println("Trying to remove: "+nickname);
        users.remove(nickname);
        System.out.println("Users: "+users.keySet().toString());
    }

    public static void addNewRoom(String roomName){
        rooms.put(roomName, new Room(roomName));
    }

    public static boolean isInUsers(String key){
        return users.containsKey(key);
    }

    public static void addPlayerToRoom(String roomName, String nickname){
        User user = users.get(nickname);
        Room room = rooms.get(roomName);

        user.setRoomName(roomName);
        room.addPlayer(nickname);
    }

    public static void removePlayerFromRoom(String roomName, String nickname){
        User user = users.get(nickname);
        Room room = rooms.get(roomName);

        user.setRoomName(null);
        room.removePlayer(nickname);
    }

    public static String allRoomsToJSON(){
        String json = ""+ROOMS_JSON_FORMAT;
        String roomsJson = "";
        Enumeration<Room> enumerator = rooms.elements();

        while (enumerator.hasMoreElements()){
            Room room = enumerator.nextElement();
            roomsJson += room.toBasicJSON();
            if (enumerator.hasMoreElements()){
                roomsJson += ",";
            }
        }

        return String.format(
            json,
            "rooms",roomsJson
        );
    }

    public static String gameToJSON(String roomName){
        String json = ""+GAME_JSON_FORMAT;
        GameInfo game = getRoom(roomName).getGame();

        return String.format(
                json,
                "game", game.toJSON()
        );
    }


    public static boolean hitSuccess(String roomName, String nickname){
        Room room = rooms.get(roomName);
        GameInfo game = room.getGame();

        int currentTurn = game.getCurrentTurn();
        PlayerInfo player = game.getPlayersInfo().get(currentTurn);

        if (player.getNickname().equals(nickname)){
            room.getGame().giveCardToPlayer(nickname);
            return true;
        } else {
            return false;
        }

    }

    public static int getHandValue(String roomName, String nickname){
        User user = users.get(nickname);
        Room room = rooms.get(roomName);

        for (PlayerInfo player : room.getGame().getPlayersInfo()) {
            if (player.getNickname().equals(nickname)) {
                return player.sumOfCards();
            }
        }

        return 0;
    }

    public static void nextTurn(String roomName){
        Room room = rooms.get(roomName);
        GameInfo game = room.getGame();

        PlayerInfo currentPlayer = game.getPlayersInfo().get(game.getCurrentTurn());

        if (currentPlayer.getState() == PlayerState.ON_TURN) {
            currentPlayer.setState(PlayerState.WAITING);
        }

        game.nextTurn();

        PlayerInfo nextPlayer = game.getPlayersInfo().get(game.getCurrentTurn());

        if (nextPlayer.getState() == PlayerState.WAITING) {
            nextPlayer.setState(PlayerState.ON_TURN);
        }

    }

    public static PlayerState getPlayerState(String roomName, String nickname){
        Room room = rooms.get(roomName);

        for (PlayerInfo player : room.getGame().getPlayersInfo()) {
            if (player.getNickname().equals(nickname)) {
                return player.getState();
            }
        }

        return PlayerState.EXIT;
    }

    public static PlayerInfo getPlaterInfo(String roomName, String nickname){
        Room room = rooms.get(roomName);

        for (PlayerInfo player : room.getGame().getPlayersInfo()) {
            if (player.getNickname().equals(nickname)) {
                return player;
            }
        }

        return null;
    }

    public static Room getRoom(String roomName){
        return rooms.get(roomName);
    }
}
