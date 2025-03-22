/*
 * CSCI 234: Intro to Software Engineering
 * Group: Giovanny, Jamell, Matt, Deborah
 * Purpose: This class represents a space on the Monopoly board.
 * This can be extended to create specific types of spaces like properties,
 * Team Member(s) responsible: Deborah
 * */

package Model.Spaces;

import Model.Board.Token;
import Model.Exceptions.PlayerNotFoundException;
import Model.Board.Player;

import java.util.ArrayList;
import java.util.List;


public abstract class BoardSpace {
    private final String name;
    private final int position;
    private List<Token> tokens;

    /**
     * Constructor for BoardSpace
     *
     * @param name     The name of the space
     * @param position The position on the board (0-39)
     *                 Team member(s) responsible: Matt
     */
    public BoardSpace(String name, int position) {
        this.name = name;
        this.position = position;
        this.tokens = new ArrayList<>();
    }

    /**
     * Add a player token to this space
     *
     * @param token The token to add
     *              Team member(s) responsible: Matt
     */
    public void addToken(Token token) {
        tokens.add(token);
    }

    /**
     * Remove a player token from this space
     *
     * @param token The token to remove
     *              Team member(s) responsible: Matt
     */
    public void removeToken(Token token) {
        tokens.remove(token);
    }

    /**
     * Get all tokens currently on this space
     *
     * @return List of tokens
     * Team member(s) responsible: Matt
     */
    public List<Token> getTokens() {
        return new ArrayList<>(tokens);
    }

    /**
     * Get the name of the space
     *
     * @return The space name
     * Team member(s) responsible: Matt
     */
    public String getName() {
        return name;
    }

    /**
     * Get the position of the space on the board
     *
     * @return The position (0-39)
     * Team member(s) responsible: Matt
     */
    public int getPosition() {
        return position;
    }

    /**
     * Get the purchase price of the space
     *
     * @return The purchase price
     * Team member(s) responsible: Jamell
     */
    public abstract int getPurchasePrice();

    /**
     * Set the owner of the space
     * @param owner The player who owns the space
     * Team member(s) responsible: Jamell
     */
    public abstract void setOwner(Player owner);

    public abstract int calculateRent(Player player) throws PlayerNotFoundException;

    /**
     * Get the owner of the space
     *
     * @return The player who owns the space
     * Team member(s) responsible: Jamell
     */
    public abstract Player getOwner();

    /**
     * Abstract method that defines what happens when a player lands on this space
     *
     * @param player The player who landed on the space
     *               Team member(s) responsible: Matt
     */
    public abstract void onLanding(Player player) throws PlayerNotFoundException;

    /**
     * Abstract method that defines what happens when a player passes over this space
     *
     * @param player The player who passed over the space
     *               Team member(s) responsible: Matt
     */
    public abstract void onPassing(Player player) throws PlayerNotFoundException;
}