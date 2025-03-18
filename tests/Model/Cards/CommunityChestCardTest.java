package Model.Cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommunityChestCardTest {

    @Test
    void drawCard() {
        CommunityChestCard communityChestCard = new CommunityChestCard();
        String card = communityChestCard.drawCard();
        assertNotNull(card);
    }

    @Test
    void shuffleDeck() {
        CommunityChestCard communityChestCard = new CommunityChestCard();
        communityChestCard.shuffleDeck();
        assertNotNull(communityChestCard);
    }

    @Test
    void cardRestore() {
        CommunityChestCard communityChestCard = new CommunityChestCard();
        communityChestCard.shuffleDeck();
        communityChestCard.drawCard();
        communityChestCard.cardRestore();
        assertNotNull(communityChestCard);
    }

}