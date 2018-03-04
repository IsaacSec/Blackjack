package data.model;

import data.model.states.PlayerState;
import java.util.Vector;

public class PlayerInfo {

    private String nickname;
    private PlayerState state;
    private Vector<Card> cards;

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
}
