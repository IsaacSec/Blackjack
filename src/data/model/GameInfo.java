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

    public void addPlayerInfo(String nickname){
        PlayerInfo player = new PlayerInfo(nickname);
        playersInfo.add(player);
    }

    public void removePlayerInfo(String nickname){
        for (PlayerInfo p : playersInfo) {
            if (p.getNickname().equals(nickname)){
                playersInfo.remove(p);
                break;
            }
        }
    }

    public void giveCardToPlayer(String nickname){
        for (PlayerInfo player : playersInfo) {

            if (nickname.equals(player.getNickname())){

                Card card = takeRandomCard();
                player.getCards().add(card);
            }
        }
    }

    private Card takeRandomCard(){
        int randomInt = (int) (Math.random()*availableCards.size());
        return availableCards.remove(randomInt);
    }

    private String getJsonFromCards(){
        String jsonCards = "";

        for (int i = 0; i < availableCards.size(); i++) {
            jsonCards += availableCards.get(i).toJSON();
            if (i != availableCards.size()-1) {
                jsonCards += ",";
            }
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

    @Override
    public String toString() {
        return "GameInfo{" +
                "currentTurn=" + currentTurn +
                ", state=" + state +
                ", availableCards=" + availableCards +
                ", playersInfo=" + playersInfo +
                '}';
    }
}
