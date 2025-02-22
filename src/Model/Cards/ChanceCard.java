package Model.Cards;

import java.util.Collections;

public class ChanceCard extends ChanceCardDatabase{

    ChanceCardDatabase chanceDeck;

    public ChanceCard(){

        chanceDeck = new ChanceCardDatabase();
        shuffleDeck();
    }

    public String drawCard(){
        if(chanceDeck.getCardDeck().isEmpty()){
            return chanceDeck.getCardDeck().remove(0);
        }
        return "No more cards in the deck.";
    }


    public void shuffleDeck(){
        Collections.shuffle(chanceDeck.getCardDeck());
    }

}
