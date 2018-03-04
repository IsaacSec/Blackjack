package data.model;

import data.model.states.RoomState;

public class Room {

    private String roomName;
    private int playerCounter;
    private RoomState state;
    private GameInfo game;

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
}
