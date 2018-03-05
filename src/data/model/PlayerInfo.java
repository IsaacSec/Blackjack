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

    public Vector<Card> getCards() {
        return cards;
    }

    public void setCards(Vector<Card> cards) {
        this.cards = cards;
    }

    private String getJsonFromCards(){
        String jsonCards = "";

        for (Card card : cards) {
            jsonCards += card.toJSON();
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
}
