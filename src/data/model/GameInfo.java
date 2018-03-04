package data.model;

import data.model.states.GameState;
import java.util.Vector;

public class GameInfo {

    private int currentTurn;
    private GameState state;
    private Vector<Card> availableCards;
    private Vector<PlayerInfo> playersInfo;

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
}
