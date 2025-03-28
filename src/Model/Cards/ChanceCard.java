/*
 * CSCI 234: Intro to Software Engineering
 * Group: Giovanny, Jamell, Matt, Deborah
 * Purpose: This class is responsible for drawing a chance card from the chance deck,
 * shuffling the chance deck, and restoring the chance deck to its original state.
 * Team Member(s) responsible: Jamell
 * */


package Model.Cards;

import java.util.Collections;

public class ChanceCard extends ChanceCardDatabase {

    ChanceCardDatabase chanceDeck;

    public ChanceCard() {
        chanceDeck = new ChanceCardDatabase();
        shuffleDeck();
    }

    /**
     * This method draws a card from the chance deck. Checks if card deck is empty first.
     *
     * @return String
     * Team member(s) responsible: Jamell
     */
    public String drawCard() {
        if (!chanceDeck.getCardDeck().isEmpty()) {
            return chanceDeck.getCardDeck().remove(0);
        }
        return "No more cards in the deck.";
    }


    /**
     * This method shuffles the chance deck.
     * Team member(s) responsible: Jamell
     */
    public void shuffleDeck() {
        Collections.shuffle(chanceDeck.getCardDeck());
    }

    /**
     * This method restores the chance deck to its original state.
     * Team member(s) responsible: Jamell
     */
    public void cardRestore() {
        chanceDeck = new ChanceCardDatabase();
        shuffleDeck();
    }

    public void useCard(String message){
        // for use cards that have a specific action
    }
}
