package Model.Cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChanceCardTest {

    @Test
    void drawCard() {
        ChanceCard chanceCard = new ChanceCard();
        String card = chanceCard.drawCard();
        assertNotNull(card);
    }

    @Test
    void shuffleDeck() {
        ChanceCard chanceCard = new ChanceCard();
        chanceCard.shuffleDeck();
        assertNotNull(chanceCard);
    }

    @Test
    void cardRestore() {
        ChanceCard chanceCard = new ChanceCard();
        chanceCard.shuffleDeck();
        chanceCard.drawCard();
        chanceCard.cardRestore();
        assertNotNull(chanceCard);
    }

}