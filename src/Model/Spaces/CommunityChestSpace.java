/*
 * CSCI 234: Intro to Software Engineering
 * Group: Giovanny, Jamell, Matt, Deborah
 * Purpose: This class represents the Community Chest space on the board
 * This class is a subclass of the BoardSpace class
 * This class has a CommunityChestCard object that represents the deck of community chest cards
 * Team Member(s) responsible: Deborah
 * */


package Model.Spaces;

import Model.Cards.CommunityChestCard;
import Model.Board.Player;

public class CommunityChestSpace extends BoardSpace {
    private CommunityChestCard communityChestDeck;

    /**
     * Constructor for CommunityChestSpace
     *
     * @param position
     * @param communityChestDeck Team member(s) responsible: Deborah
     */
    public CommunityChestSpace(int position, CommunityChestCard communityChestDeck) {
        super("Community Chest", position);
        this.communityChestDeck = communityChestDeck;
    }

    /**
     * Draw a community chest card when a player lands on the space
     *
     * @param player The player who landed on the space
     *               Team member(s) responsible: Deborah
     */
    @Override
    public void onLanding(Player player) {
        String cardDrawn = communityChestDeck.drawCard();
        System.out.println(player.getName() + " drew a community chest card: " + cardDrawn);
    }

    /**
     * Do nothing when a player passes over the space
     *
     * @param player The player who passed over the space
     *               Team member(s) responsible: Deborah
     */
    @Override
    public void onPassing(Player player) {
        // Do nothing
    }

}