package Model.Spaces;

import Model.Cards.CommunityChestCard;
import Model.Player;
import Model.Spaces.BoardSpace;

public class CommunityChestSpace extends BoardSpace {
    private CommunityChestCard communityChestDeck;

    /**
     * Constructor for CommunityChestSpace
     * @param position
     * @param communityChestDeck
     * Team member(s) responsible: Deborah
     */
    public CommunityChestSpace(int position, CommunityChestCard communityChestDeck) {
        super("Community Chest", position);
        this.communityChestDeck = communityChestDeck;
    }

    /**
     * Draw a community chest card when a player lands on the space
     * @param player The player who landed on the space
     * Team member(s) responsible: Deborah
     */
    @Override
    public void onLanding(Player player) {
        String cardDrawn = communityChestDeck.drawCard();
        System.out.println(player.getName() + " drew a community chest card: " + cardDrawn);
    }

    /**
     * Do nothing when a player passes over the space
     * @param player The player who passed over the space
     * Team member(s) responsible: Deborah
     */
    @Override
    public void onPassing(Player player) {
        // Do nothing
    }

}