package Model.Cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CommunityChestDatabaseTest {

    @Test
    public void TestCommunityChestDatabaseCardType() {
        CommunityChestDatabase database = new CommunityChestDatabase();
        Assertions.assertEquals(CardType.Community_Chest, database.getCardType());
    }

    @Test
    public void TestCommunityChestDatabaseDeck() {
        CommunityChestDatabase database = new CommunityChestDatabase();
        ArrayList<String> deckTest = new ArrayList<>();
        deckTest.add("Advance to Go (Collect $200)");
        deckTest.add("You inherit $100");
        assertEquals(deckTest.get(0), database.getCardDeck().get(0));
        assertEquals(deckTest.get(deckTest.size() - 1), database.getCardDeck().get(database.getCardDeck().size() - 1));
        assertEquals(16, database.getCardDeck().size());

    }

}