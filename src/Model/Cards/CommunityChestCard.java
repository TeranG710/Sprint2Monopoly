package Model.Cards;

import java.util.Collections;

public class CommunityChestCard extends CommunityChestDatabase{

    CommunityChestDatabase communityChestDeck;

    public CommunityChestCard(){

        communityChestDeck = new CommunityChestDatabase();
        shuffleDeck();
    }

    /**
     * This method is used to draw a card from the community chest deck. Checks if the deck is empty first.
     * @return String
     */
    public String drawCard(){
        if(!communityChestDeck.getCardDeck().isEmpty()){
            return communityChestDeck.getCardDeck().remove(0);
        }
        return "No more cards in the deck.";
    }

    /**
     * This method is used to shuffle the community chest deck.
     */
    public void shuffleDeck(){
        Collections.shuffle(communityChestDeck.getCardDeck());
    }

    /**
     * This method is used to restore the community chest deck to its original state.
     */
    public void cardRestore(){
        communityChestDeck = new CommunityChestDatabase();
        shuffleDeck();
    }
}
