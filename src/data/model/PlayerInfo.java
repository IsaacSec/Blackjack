package data.model;

import data.model.states.PlayerState;
import java.util.Vector;

public class PlayerInfo {

    private String nickname;
    private PlayerState state;
    private Vector<Card> cards;

    private static String JSON_FORMAT =
            "{" +
                "\"%s\":\"%s\"," +
                "\"%s\":\"%s\"," +
                "\"%s\":[%s]" +
            "}";

    public PlayerInfo(String nickname) {
        this.nickname = nickname;
        state = PlayerState.WAITING;
        cards = new Vector<>();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    public Vector<Card> getCards() {
        return cards;
    }

    public void setCards(Vector<Card> cards) {
        this.cards = cards;
    }

    public int sumOfCards(){
        int sum = 0;
        for (Card card : cards) {
            sum += card.getValue();
        }

        return sum;
    }

    private String getJsonFromCards(){
        String jsonCards = "";

        for (int i = 0; i < cards.size(); i++) {
            jsonCards += cards.get(i).toJSON();
            if (i != cards.size()-1) {
                jsonCards += ",";
            }
        }

        return jsonCards;
    }

    public String toJSON(){
        String json = ""+JSON_FORMAT;

        return String.format(
                json,
                "nickname", nickname,
                "state", state,
                "cards", getJsonFromCards()
            );
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "nickname='" + nickname + '\'' +
                ", state=" + state +
                ", cards=" + cards +
                '}';
    }
}
