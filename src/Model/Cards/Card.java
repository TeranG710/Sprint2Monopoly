package Model.Cards;

import java.util.ArrayList;

public abstract class Card {

    private String description;

    public Card(String description) {
        this.description = description;
    }

    /**
     * @return the cardType of a card
     * Team member(s) responsible: Jamell
     */
    public abstract CardType getCardType();


    /**
     * @return the card deck of a card type
     * Team member(s) responsible: Jamell
     */
    public abstract ArrayList<String> getCardDeck();
}

