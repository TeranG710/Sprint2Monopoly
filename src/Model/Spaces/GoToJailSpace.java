/*
 * CSCI 234: Intro to Software Engineering
 * Group: Giovanny, Jamell, Matt, Deborah
 * Purpose: This class represents the GoTo Jail space on the board
 * This space sends the player to jail when they land on it
 * Team Member(s) responsible: Deborah
 * */

package Model.Spaces;

import Model.Board.Player;

public class GoToJailSpace extends BoardSpace {
    /**
     * Constructor for GoToJailSpace
     *
     * @param position Team member(s) responsible: Deborah
     */
    public GoToJailSpace(int position) {
        super("Go To Jail", position);
    }

    /**
     * Send the player to jail when they land on the Go To Jail space
     *
     * @param player The player who landed on the space
     *               Team member(s) responsible: Deborah
     */
    @Override
    public void onLanding(Player player) {
        player.setInJail(true);
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
