/*
 * CSCI 234: Intro to Software Engineering
 * Group: Giovanny, Jamell, Matt, Deborah
 * Purpose: This class represents a player's token on the Monopoly board
 * Team Member(s) responsible: Matt
 * */

package Model.Board;

/**
 * Represents a player's token on the Monopoly board
 */
public class Token {

    private final String type;
    private int position;
    private Player owner;

    public Token(String type) {
        this.type = type;
        this.position = 0;
    }

    /**
     * Sets the owner of the token
     * Team member(s) responsible: Matt
     */
    public void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * Returns the owner of the token
     * Team member(s) responsible: Matt
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Moves the token to a new position
     * Team member(s) responsible: Matt
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Gets position of the token
     * Team member(s) responsible: Matt
     */
    public int getPosition() {
        return position;
    }

    /**
     * Gets the type of the token
     * Team member(s) responsible: Matt
     */
    public String getType() {
        return type;
    }
}