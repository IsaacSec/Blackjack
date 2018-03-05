package data;

import data.model.Room;
import data.model.User;

import java.util.Enumeration;
import java.util.Hashtable;

public class VirtualStorage {

    private static Hashtable <String,User> users = new Hashtable<>();
    private static Hashtable <String,Room> rooms = new Hashtable<>();

    public static String ROOMS_JSON_FORMAT =
        "{" +
            "\"%s\":[%s]" +
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

    public static String allRoomsToJSON(){
        String json = ""+ROOMS_JSON_FORMAT;
        String roomsJson = "";
        Enumeration<Room> enumerator = rooms.elements();

        while (enumerator.hasMoreElements()){
            Room room = enumerator.nextElement();
            roomsJson += room.toBasicJSON();
        }

        return String.format(
            json,
            "rooms",roomsJson
        );
    }

    public static Room getRoom(String roomName){
        return rooms.get(roomName);
    }
}
