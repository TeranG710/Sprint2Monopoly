/*
* CSCI 234: Intro to Software Engineering
* Group: Giovanny, Jamell, Matt, Deborah
* Purpose: This class represents a player's on the Monopoly board
* Team Member(s) responsible: Matt, Jamell
* */

package Model.Board;

import java.util.ArrayList;
import java.util.List;
import Model.Property.Property;


public abstract class Player {
    private final String name;
    private final GameBoard board;
    private boolean inJail;
    private int jailTurns;
    private Token token;
    private int getOutOfJailFreeCards = 0;


    /**
     * Constructor for Player.
     *
     * @param name  Player's name
     * @param board The game board
     * Team member(s) responsible: Matt
     */
    public Player(String name, GameBoard board) {
        this.name = name;
        this.board = board;
        this.inJail = false;
        this.jailTurns = 0;
    }


    /**
     * set player's token.
     * Team member(s) responsible: Jamell
     */
    public void setTokenToPlayer(String tokenType) {
        if (this.token == null) {
            this.token = new Token(tokenType);
        }
        token.setOwner(this);
    }

    /**
     * get player's token.
     *
     * @return Player's token
     * Team member(s) responsible: Jamell
     */

    public Token getToken() {
        return token;
    }

    /**
     * Get player's name.
     *
     * @return Player name
     * Team member(s) responsible: Matt
     */
    public String getName() {
        return name;
    }


    /**
     * Get the game board.
     *
     * @return The game board
     * Team member(s) responsible: Matt
     */
    public GameBoard getBoard() {
        return board;
    }

    /**
     * Check if player is in jail.
     *
     * @return true if player is in jail
     * Team member(s) responsible: Matt
     */
    public boolean isInJail() {
        return inJail;
    }

    /**
     * Set player's jail status.
     *
     * @param inJail New jail status
     * Team member(s) responsible: Matt
     */
    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    /**
     * Get the number of turns a player has been in jail.
     *
     * @return Number of turns in jail
     * Team member(s) responsible: Matt
     */
    public int getTurnsInJail() {
        return jailTurns;
    }

    /**
     * Increment the number of turns the player has spent in jail.
     * Team member(s) responsible: Matt
     */
    public void incrementTurnsInJail() {
        jailTurns++;
    }

    /**
     * Reset the number of turns in jail to 0.
     * Team member(s) responsible: Matt
     */
    public void addGetOutOfJailFreeCard() {
        getOutOfJailFreeCards++;
    }
    public void resetTurnsInJail() {
        jailTurns = 0;
    }

    public boolean hasGetOutOfJailFreeCard() {
        return getOutOfJailFreeCards > 0;
    }
    public boolean useGetOutOfJailFreeCard() {
        if (getOutOfJailFreeCards > 0) {
            getOutOfJailFreeCards--;
            return true;
        }
        return false;
    }
    public int getGetOutOfJailFreeCard() {
        return getOutOfJailFreeCards;
    }
}
