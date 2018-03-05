package data.model;

import data.model.states.RoomState;

public class Room {

    private String roomName;
    private int playerCounter;
    private RoomState state;
    private GameInfo game;

    private static String JSON_FORMAT =
            "{" +
                "\"%s\":\"%s\"," +
                "\"%s\":%s," +
                "\"%s\":\"%s\"," +
                "\"%s\":%s" +
            "}";

    private static String BASIC_JSON_FORMAT =
            "{" +
                    "\"%s\":\"%s\"," +
                    "\"%s\":%s," +
                    "\"%s\":\"%s\""+
                    "}";

    public Room(String roomName) {
        this.roomName = roomName;
        playerCounter = 0;
        state = RoomState.WAITING;
        game = new GameInfo();
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getPlayerCounter() {
        return playerCounter;
    }

    public void setPlayerCounter(int playerCounter) {
        this.playerCounter = playerCounter;
    }

    public RoomState getState() {
        return state;
    }

    public void setState(RoomState state) {
        this.state = state;
    }

    public GameInfo getGame() {
        return game;
    }

    public void setGame(GameInfo game) {
        this.game = game;
    }

    public void addPlayer(String nickname){
        playerCounter++;
        game.addPlayerInfo(nickname);
    }

    public void removePlayer(String nickname){
        playerCounter--;
        game.removePlayerInfo(nickname);
    }

    public String toBasicJSON(){

        String json = ""+BASIC_JSON_FORMAT;

        return String.format(
                json,
                "roomName", roomName,
                "playerCounter", playerCounter,
                "state", state
        );

    }

    public String toJSON(){

        String json = ""+JSON_FORMAT;

        return String.format(
            json,
            "roomName", roomName,
            "playerCounter", playerCounter,
            "state", state,
            "game",game.toJSON()
        );
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomName='" + roomName + '\'' +
                ", playerCounter=" + playerCounter +
                ", state=" + state +
                ", game=" + game +
                '}';
    }
}
