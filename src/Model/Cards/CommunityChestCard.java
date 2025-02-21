package Model.Cards;

import java.util.Collections;

public class CommunityChestCard extends CommunityChestDatabase{

    CommunityChestDatabase communityChestDeck;

    public CommunityChestCard(){

        communityChestDeck = new CommunityChestDatabase();
        shuffleDeck();
    }

    public String drawCard(){
        if(communityChestDeck.getCardDeck().isEmpty()){
            return communityChestDeck.getCardDeck().remove(0);
        }
        return "No more cards in the deck.";
    }

    public void shuffleDeck(){
        Collections.shuffle(communityChestDeck.getCardDeck());
    }
}
