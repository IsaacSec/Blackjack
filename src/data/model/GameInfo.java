package data.model;

import data.model.states.GameState;
import java.util.Vector;

public class GameInfo {

    private int currentTurn;
    private GameState state;
    private Vector<Card> availableCards;
    private Vector<PlayerInfo> playersInfo;

    private static String JSON_FORMAT =
        "{" +
            "\"%s\":%s," +
            "\"%s\":\"%s\"," +
            "\"%s\":[%s]," +
            "\"%s\":[%s]" +
        "}";

    public GameInfo() {
        currentTurn = 0;
        state = GameState.READY;
        availableCards = Card.getFullDeck();
        playersInfo = new Vector<>();
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public Vector<Card> getAvailableCards() {
        return availableCards;
    }

    public void setAvailableCards(Vector<Card> availableCards) {
        this.availableCards = availableCards;
    }

    public Vector<PlayerInfo> getPlayersInfo() {
        return playersInfo;
    }

    public void setPlayersInfo(Vector<PlayerInfo> playersInfo) {
        this.playersInfo = playersInfo;
    }

    private String getJsonFromCards(){
        String jsonCards = "";

        for (Card card : availableCards) {
            jsonCards += card.toJSON();
        }

        return jsonCards;
    }

    private String getJsonFromPlayers(){
        String jsonPlayers = "";

        for (PlayerInfo player : playersInfo) {
            jsonPlayers += player.toJSON();
        }

        return jsonPlayers;
    }

    public String toJSON(){

        String json = ""+JSON_FORMAT;

        return String.format(
            json,
            "currentTurn",currentTurn,
            "state",state,
            "availableCards", getJsonFromCards(),
            "playersInfo", getJsonFromPlayers()
        );
    }
}
