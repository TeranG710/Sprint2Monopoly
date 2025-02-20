package Model.Cards;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CommunityChestDatabaseTest {

    @Test
    public void TestCommunityChestDatabaseCardType(){
        CommunityChestDatabase database = new CommunityChestDatabase();
        Assertions.assertEquals(CardType.Community_Chest, database.getCardType());
    }

    @Test
    public void TestCommunityChestDatabaseDeck(){
        CommunityChestDatabase database = new CommunityChestDatabase();
        ArrayList<String> deckTest = new ArrayList<>();
        deckTest.add("Advance to Go (Collect $200)");
        deckTest.add("You inherit $100");
        assertEquals(deckTest.getFirst(),database.getCardDeck().getFirst());
        assertEquals(deckTest.getLast(),database.getCardDeck().getLast());
        assertEquals(16,database.getCardDeck().size());
    }
}