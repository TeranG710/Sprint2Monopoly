package Model.Cards;

import java.util.ArrayList;

public class CommunityChestDatabase extends Card
{
    private ArrayList<String> communityChanceDeck;

    public CommunityChestDatabase() {
        super("Community Chest Card");
        communityChanceDeck = new ArrayList<>();
        preloadCards();
    }

    /**
     * This method returns the card type
     * @return CardType
     */
    @Override
    public CardType getCardType() {
        return CardType.Community_Chest;
    }

    /**
     * This method returns the card deck
     * @return ArrayList<String>
     */
    @Override
    public ArrayList<String> getCardDeck() {
        return communityChanceDeck;
    }

    /**
     * This method preloads the cards
     */
    private void preloadCards() {
        communityChanceDeck.add("Advance to Go (Collect $200)");
        communityChanceDeck.add("Bank error in your favor. Collect $200");
        communityChanceDeck.add("Doctor’s fee. Pay $50");
        communityChanceDeck.add("From sale of stock you get $50");
        communityChanceDeck.add("Get Out of Jail Free");
        communityChanceDeck.add("Go to Jail. Go directly to jail, do not pass Go, do not collect $200");
        communityChanceDeck.add("Holiday fund matures. Receive $100");
        communityChanceDeck.add("Income tax refund. Collect $20");
        communityChanceDeck.add("It is your birthday. Collect $10 from every player");
        communityChanceDeck.add("Life insurance matures. Collect $100");
        communityChanceDeck.add("Pay hospital fees of $100");
        communityChanceDeck.add("Pay school fees of $50");
        communityChanceDeck.add("Receive $25 consultancy fee");
        communityChanceDeck.add("You are assessed for street repair. $40 per house. $115 per hotel");
        communityChanceDeck.add("You have won second prize in a beauty contest. Collect $10");
        communityChanceDeck.add("You inherit $100");
    }

}
