package Model.Cards;
import java.util.ArrayList;

public abstract class Card {

    private String description;

    public Card(String description) {
        this.description = description;
    }

    public abstract CardType getCardType();
    public abstract ArrayList<String> getCardDeck();
}

