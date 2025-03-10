package Model.Spaces;

import Model.Cards.CommunityChestCard;
import Model.Player;
import Model.Spaces.BoardSpace;

public class CommunityChestSpace extends BoardSpace {
    private CommunityChestCard communityChestDeck;
    public CommunityChestSpace(int position, CommunityChestCard communityChestDeck) {
        super("Community Chest", position);
        this.communityChestDeck = communityChestDeck;
    }

    @Override
    public void onLanding(Player player) {
        String cardDrawn = communityChestDeck.drawCard();
        System.out.println(player.getName() + " drew a community chest card: " + cardDrawn);
    }
    @Override
    public void onPassing(Player player) {
        // Do nothing
    }

}