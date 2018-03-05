package data.model;

import java.util.Vector;

public class Card {

    private int code;
    private String unicode;
    private int value;

    private static String JSON_FORMAT =
            "{" +
                "\"%s\":%s," +
                "\"%s\":\"%s\"," +
                "\"%s\":%s" +
            "}";


    private final static String[] unicodes = {
        "\uD83C\uDCA1", "\uD83C\uDCA2", "\uD83C\uDCA3", "\uD83C\uDCA4", "\uD83C\uDCA5", "\uD83C\uDCA6", "\uD83C\uDCA7", "\uD83C\uDCA8", "\uD83C\uDCA9", "\uD83C\uDCAA", "\uD83C\uDCAB", "\uD83C\uDCAD","\uD83C\uDCAE",
        "\uD83C\uDCB1", "\uD83C\uDCB2", "\uD83C\uDCB3", "\uD83C\uDCB4", "\uD83C\uDCB5", "\uD83C\uDCB6", "\uD83C\uDCB7", "\uD83C\uDCB8", "\uD83C\uDCB9", "\uD83C\uDCBA", "\uD83C\uDCBB", "\uD83C\uDCBD","\uD83C\uDCBE",
        "\uD83C\uDCC1", "\uD83C\uDCC2", "\uD83C\uDCC3", "\uD83C\uDCC4", "\uD83C\uDCC5", "\uD83C\uDCC6", "\uD83C\uDCC7", "\uD83C\uDCC8", "\uD83C\uDCC9", "\uD83C\uDCCA", "\uD83C\uDCCB", "\uD83C\uDCCD","\uD83C\uDCCE",
        "\uD83C\uDCD1", "\uD83C\uDCD2", "\uD83C\uDCD3", "\uD83C\uDCD4", "\uD83C\uDCD5", "\uD83C\uDCD6", "\uD83C\uDCD7", "\uD83C\uDCD8", "\uD83C\uDCD9", "\uD83C\uDCDA", "\uD83C\uDCDB", "\uD83C\uDCDD","\uD83C\uDCDE",
    };

    public static String getUnicode(int cardCode){
        return unicodes[cardCode];
    }

    public static Vector<Card> getFullDeck(){
        Vector<Card> deck = new Vector<>();
        for (int i = 0; i < unicodes.length; i++) {
            Card card = new Card(i);
            deck.add(card);
        }

        return deck;
    }

    public Card(int code) {
        this.code = code;
        unicode = unicodes[code];
        int number = code % 13;

        if (number < 9) {
            value = number+1;
        } else {
            value = 10;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUnicode() {
        return unicode;
    }

    public void setUnicode(String unicode) {
        this.unicode = unicode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toJSON(){

        String json = ""+JSON_FORMAT;

        return String.format(
            json,
            "code", code,
            "unicode", unicode,
            "value", value
        );
    }

    @Override
    public String toString() {
        return "Card{" +
                "code=" + code +
                ", unicode='" + unicode + '\'' +
                ", value=" + value +
                '}';
    }
}
