/*
 * CSCI 234: Intro to Software Engineering
 * Group: Giovanny, Jamell, Matt, Deborah
 * Purpose: This class represents the Chance space on the board.
 * When a player lands on this space, they draw a chance card.
 * Team Member(s) responsible: Deborah
 * */


package Model.Spaces;

import Model.Cards.ChanceCard;
import Model.Board.Player;

public class ChanceSpace extends BoardSpace {
    private ChanceCard chanceDeck;

    /**
     * Constructor for ChanceSpace
     *
     * @param position
     * @param chanceDeck Team member(s) responsible: Deborah
     */
    public ChanceSpace(int position, ChanceCard chanceDeck) {
        super("Chance", position);
        this.chanceDeck = chanceDeck;
    }

    /**
     * Draw a chance card when a player lands on the space
     *
     * @param player The player who landed on the space
     *               Team member(s) responsible: Deborah
     */
    @Override
    public void onLanding(Player player) {
        String cardDrawn = chanceDeck.drawCard();
        System.out.println(player.getName() + " drew a chance card: " + cardDrawn);
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