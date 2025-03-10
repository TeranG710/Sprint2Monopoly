package Model.Board;

import Model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract base class representing a space on the Monopoly board.
 * This can be extended to create specific types of spaces like properties,
 * railroads, utilities, and special spaces (GO, Jail, etc.)
 */
public abstract class BoardSpace {
    private final String name;
    private final int position;
    private List<Token> tokens;

    /**
     * Constructor for BoardSpace
     * @param name The name of the space
     * @param position The position on the board (0-39)
     * Team member(s) responsible: Matt
     */
    public BoardSpace(String name, int position) {
        this.name = name;
        this.position = position;
        this.tokens = new ArrayList<>();
    }

    /**
     * Add a player token to this space
     * @param token The token to add
     * Team member(s) responsible: Matt
     */
    public void addToken(Token token) {
        tokens.add(token);
    }

    /**
     * Remove a player token from this space
     * @param token The token to remove
     * Team member(s) responsible: Matt
     */
    public void removeToken(Token token) {
        tokens.remove(token);
    }

    /**
     * Get all tokens currently on this space
     * @return List of tokens
     * Team member(s) responsible: Matt
     */
    public List<Token> getTokens() {
        return new ArrayList<>(tokens);
    }

    /**
     * Get the name of the space
     * @return The space name
     * Team member(s) responsible: Matt
     */
    public String getName() {
        return name;
    }

    /**
     * Get the position of the space on the board
     * @return The position (0-39)
     * Team member(s) responsible: Matt
     */
    public int getPosition() {
        return position;
    }

    /**
     * Abstract method that defines what happens when a player lands on this space
     * @param player The player who landed on the space
     * Team member(s) responsible: Matt
     */
    public abstract void onLanding(Player player);

    /**
     * Abstract method that defines what happens when a player passes over this space
     * @param player The player who passed over the space
     * Team member(s) responsible: Matt
     */
    public abstract void onPassing(Player player);
}