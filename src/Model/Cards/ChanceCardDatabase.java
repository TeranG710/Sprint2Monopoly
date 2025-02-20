package Model.Cards;

import java.util.ArrayList;

public class ChanceCardDatabase extends Card {

    private ArrayList<String> chanceCardsDeck;

    public ChanceCardDatabase() {
        super("Chance Card");
        chanceCardsDeck = new ArrayList<>();
        preloadCards();
    }

    @Override
    public CardType getCardType() {
        return CardType.Chance;
    }


    @Override
    public ArrayList<String> getCardDeck() {
        return chanceCardsDeck;
    }

    private void preloadCards() {
        chanceCardsDeck.add("Advance to Boardwalk.");
        chanceCardsDeck.add("Advance to Go (Collect $200).");
        chanceCardsDeck.add("Advance to Illinois Avenue. If you pass Go, collect $200.");
        chanceCardsDeck.add("Advance to St. Charles Place. If you pass Go, collect $200.");
        chanceCardsDeck.add("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental to which they are otherwise entitled.");
        chanceCardsDeck.add("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay owner twice the rental to which they are otherwise entitled.");
        chanceCardsDeck.add("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown.");
        chanceCardsDeck.add("Bank pays you dividend of $50.");
        chanceCardsDeck.add("Get Out of Jail Free.");
        chanceCardsDeck.add("Go Back 3 Spaces.");
        chanceCardsDeck.add("Go to Jail. Go directly to Jail, do not pass Go, do not collect $200.");
        chanceCardsDeck.add("Make general repairs on all your property. For each house pay $25. For each hotel pay $100.");
        chanceCardsDeck.add("Speeding fine $15.");
        chanceCardsDeck.add("Take a trip to Reading Railroad. If you pass Go, collect $200.");
        chanceCardsDeck.add("You have been elected Chairman of the Board. Pay each player $50.");
        chanceCardsDeck.add("Your building loan matures. Collect $150.");
    }


}
