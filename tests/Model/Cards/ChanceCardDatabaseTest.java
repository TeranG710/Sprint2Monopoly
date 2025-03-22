package Model.Cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ChanceCardDatabaseTest {

    @Test
    public void TestChanceCardDatabaseCardType() {
        ChanceCardDatabase chanceCardDatabase = new ChanceCardDatabase();
        Assertions.assertEquals(CardType.Chance, chanceCardDatabase.getCardType());
    }

    @Test
    public void TestChanceCardDatabaseDeck() {
        ChanceCardDatabase chanceCardDatabase = new ChanceCardDatabase();
        ArrayList<String> testDescriptions = new ArrayList<>();
        testDescriptions.add("Advance to Boardwalk.");
        testDescriptions.add("Your building loan matures. Collect $150.");
        assertEquals(testDescriptions.get(0), chanceCardDatabase.getCardDeck().get(0));
        assertEquals(testDescriptions.get(testDescriptions.size() - 1), chanceCardDatabase.getCardDeck().get(chanceCardDatabase.getCardDeck().size() - 1));        assertEquals(16, chanceCardDatabase.getCardDeck().size());
    }


}