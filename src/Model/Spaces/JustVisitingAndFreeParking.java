/*
 * CSCI 234: Intro to Software Engineering
 * Group: Giovanny, Jamell, Matt, Deborah
 * Purpose: This class represents the Just Visiting and Free Parking space on the board
 * This class is a subclass of the BoardSpace class
 * Team Member(s) responsible: Deborah
 * */

package Model.Spaces;

import Model.Board.Player;

public class JustVisitingAndFreeParking extends BoardSpace {
    /**
     * Constructor for JustVisitingAndFreeParking
     *
     * @param name
     * @param position Team member(s) responsible: Deborah
     */
    public JustVisitingAndFreeParking(String name, int position) {
        super(name, position);
    }

    /**
     * Do nothing when a player lands on the space
     *
     * @param player The player who landed on the space
     *               Team member(s) responsible: Deborah
     */
    @Override
    public void onLanding(Player player) {
        // Do nothing
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
