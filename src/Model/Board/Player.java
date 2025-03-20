package Model.Board;

import java.util.ArrayList;
import java.util.List;

import Model.Property.Property;  // Update import to match package

/**
 * Represents a player in a Monopoly game.
 * This is an abstract class that serves as the base for HumanPlayer and ComputerPlayer.
 * Team member(s) responsible: Matt
 */
public abstract class Player {
    private final String name;
    private final GameBoard board;
    private int money;
    private boolean inJail;
    private int jailTurns;
    private Token token;
    private List<Model.Property.Property> properties = new ArrayList<>();

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
        this.properties = new ArrayList<>();
        this.inJail = false;
        this.jailTurns = 0;
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
    public void resetTurnsInJail() {
        jailTurns = 0;
    }

    /**
     * Abstract method to define player-specific behavior.
     * This must be implemented by subclasses.
     * Team member(s) responsible: Matt
     */
    public abstract void takeTurn();
}
