package Model;

import java.util.ArrayList;
import java.util.List;

import Model.Board.GameBoard;
import Model.Board.Token;
import Model.Property.Property;  // Update import to match package


/**
 * Represents a player in Monopoly game
 */
public class Player {
    private final String name;
    private final GameBoard board;
    private int money;

    private boolean inJail;
    private Token token;

    private List<Model.Property.Property> properties = new ArrayList<>();

    /**
     * Add a property to the player's portfolio
     * @param property Property to add
     * Team member(s) responsible: Matt
     */
    public void addProperty(Model.Property.Property property) {
        properties.add(property);
    }

    /**
     * Constructor for Player
     * @param name Player's name
     * @param board Game board
     * Team member(s) responsible: Matt
     */
    public Player(String name, GameBoard board) {
        this.name = name;
        this.board = board;
        this.money = 1500; // Starting money in Monopoly
        this.properties = new ArrayList<>();
        this.inJail = false;
    }

    /**
     * Remove a property from the player's portfolio
     * @param property Property to remove
     * Team member(s) responsible: Matt
     */
    public void removeProperty(Property property) {
        if (properties.remove(property)) {
            property.setOwner(null);
        }
    }

    /**
     * Check if player owns a specific property
     * @param property Property to check
     * @return true if player owns the property
     * Team member(s) responsible: Matt
     */
    public boolean ownsProperty(Property property) {
        return properties.contains(property);
    }

    /**
     * Get all properties owned by the player
     * @return List of owned properties
     * Team member(s) responsible: Matt
     */
    public List<Property> getProperties() {
        return new ArrayList<>(properties);
    }

    /**
     * Check if player can afford an amount
     * @param amount Amount to check
     * @return true if player has enough money
     * Team member(s) responsible: Matt
     */
    public boolean canAfford(int amount) {
        return money >= amount;
    }

    /**
     * Add money to player's balance
     * @param amount Amount to add
     * Team member(s) responsible: Matt
     */
    public void increaseMoney(int amount) {
        money += amount;
    }

    /**
     * Remove money from player's balance
     * @param amount Amount to remove
     *Team member(s) responsible: Matt
     */
    public void decreaseMoney(int amount) {
        if (canAfford(amount)) {
            money -= amount;
        }
    }

    /**
     * Get player's current money
     * @return Current balance
     * Team member(s) responsible: Matt
     */
    public int getMoney() {
        return money;
    }

    /**
     * Get player's name
     * @return Player name
     * Team member(s) responsible: Matt
     */
    public String getName() {
        return name;
    }

    /**
     * Check if player is in jail
     * @return true if player is in jail
     * Team member(s) responsible: Matt
     */
    public boolean isInJail() {
        return inJail;
    }

    /**
     * Set player's jail status
     * @param inJail New jail status
     * Team member(s) responsible: Matt
     */
    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    /**
     * Set player's token
     * @param token Player's game token
     * Team member(s) responsible: Matt
     */
    public void setToken(Token token) {
        this.token = token;
        if (token != null) {
            token.setOwner(this);
        }
    }

    /**
     * Get player's token
     * @return Player's game token
     * Team member(s) responsible: Matt
     */
    public Token getToken() {
        return token;
    }

    /**
     * Get the game board
     * @return The game board
     * Team member(s) responsible: Matt
     */
    public GameBoard getBoard() {
        return board;
    }

    /**
     * Get the number of railroads owned by the player
     * @return
     * Team member(s) responsible: Deborah
     */
    public int getNumRailroads() {
        // Needs implementation
        return 0;
    }

    public int getNumUtilities() {
        // Needs implementation
        return 0;
    }

    public void goToJail() {
        // Needs implementation
    }
}