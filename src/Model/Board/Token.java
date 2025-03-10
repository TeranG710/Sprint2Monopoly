package Model.Board;

import Model.Player;

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
     * */
    public void setOwner(Player player) {
        this.owner = player;
    }

    /**
     * Returns the owner of the token
     * */
    public Player getOwner() {
        return owner;
    }

    /**
     * Moves the token to a new position
     * */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Gets position of the token
     * */
    public int getPosition() {
        return position;
    }

    /**
     * Gets the type of the token
     * */
    public String getType() {
        return type;
    }
}