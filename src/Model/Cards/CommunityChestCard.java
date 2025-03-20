/*
 * CSCI 234: Intro to Software Engineering
 * Group: Giovanny, Jamell, Matt, Deborah
 * Purpose: This class is used to draw a card from the community chest deck,
 * shuffle the deck, and restore the deck to its original state.
 * Team Member(s) responsible: Jamell
 * */


package Model.Cards;

import java.util.Collections;

public class CommunityChestCard extends CommunityChestDatabase {

    CommunityChestDatabase communityChestDeck;

    public CommunityChestCard() {

        communityChestDeck = new CommunityChestDatabase();
        shuffleDeck();
    }

    /**
     * This method is used to draw a card from the community chest deck. Checks if the deck is empty first.
     *
     * @return String
     * Team member(s) responsible: Jamell
     */
    public String drawCard() {
        if (!communityChestDeck.getCardDeck().isEmpty()) {
            return communityChestDeck.getCardDeck().remove(0);
        }
        return "No more cards in the deck.";
    }

    /**
     * This method is used to shuffle the community chest deck.
     * Team member(s) responsible: Jamell
     */
    public void shuffleDeck() {
        Collections.shuffle(communityChestDeck.getCardDeck());
    }

    /**
     * This method is used to restore the community chest deck to its original state.
     * Team member(s) responsible: Jamell
     */
    public void cardRestore() {
        communityChestDeck = new CommunityChestDatabase();
        shuffleDeck();
    }

    public void useCard(String message){
        // for use cards that have a specific action
    }
}
