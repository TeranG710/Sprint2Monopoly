package Model.Cards;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ChanceCardDatabaseTest {

    @Test
    public void TestChanceCardDatabaseCardType() {
        ChanceCardDatabase chanceCardDatabase = new ChanceCardDatabase();
        assertEquals(CardType.Chance, chanceCardDatabase.getCardType());
    }

    @Test
    public void TestChanceCardDatabaseDeck() {
        ChanceCardDatabase chanceCardDatabase = new ChanceCardDatabase();
        ArrayList<String> testDescriptions = new ArrayList<>();
        testDescriptions.add("Advance to Boardwalk.");
        testDescriptions.add("Your building loan matures. Collect $150.");
        assertEquals(testDescriptions.getFirst(),chanceCardDatabase.getCardDeck().getFirst());
        assertEquals(testDescriptions.getLast(),chanceCardDatabase.getCardDeck().getLast());
        assertEquals(16,chanceCardDatabase.getCardDeck().size());
    }


}