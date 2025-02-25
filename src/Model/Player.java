package Model;

import java.util.ArrayList;
import java.util.List;
import Model.Property.Property;  // Update import to match package
import Model.Property.ColorGroup;  // Update import to match package

/**
 * Represents a player in Monopoly game
 */
public class Player {
    private String name;
    private Board board;
    private int money;

    private boolean inJail;
    private Token token;

    private List<Model.Property.Property> properties = new ArrayList<>();

    /**
     * Add a property to the player's portfolio
     * @param property Property to add
     */
    public void addProperty(Model.Property.Property property) {
        properties.add(property);
    }
    /**
     * Constructor for Player
     * @param name Player's name
     * @param board Game board
     */
    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
        this.money = 1500; // Starting money in Monopoly
        this.properties = new ArrayList<>();
        this.inJail = false;
    }

    /**
     * Add a property to the player's portfolio
     * @param property Property to add
     */
    /**
     * Add a property to the player's portfolio
     * @param property Property to add
     */


    /**
     * Remove a property from the player's portfolio
     * @param property Property to remove
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
     */
    public boolean ownsProperty(Property property) {
        return properties.contains(property);
    }

    /**
     * Get all properties owned by the player
     * @return List of owned properties
     */
    public List<Property> getProperties() {
        return new ArrayList<>(properties);
    }

    /**
     * Check if player can afford an amount
     * @param amount Amount to check
     * @return true if player has enough money
     */
    public boolean canAfford(int amount) {
        return money >= amount;
    }

    /**
     * Add money to player's balance
     * @param amount Amount to add
     */
    public void increaseMoney(int amount) {
        money += amount;
    }

    /**
     * Remove money from player's balance
     * @param amount Amount to remove
     * @return true if transaction was successful
     */
    public boolean decreaseMoney(int amount) {
        if (canAfford(amount)) {
            money -= amount;
            return true;
        }
        return false;
    }

    /**
     * Get player's current money
     * @return Current balance
     */
    public int getMoney() {
        return money;
    }

    /**
     * Get player's name
     * @return Player name
     */
    public String getName() {
        return name;
    }

    /**
     * Check if player is in jail
     * @return true if player is in jail
     */
    public boolean isInJail() {
        return inJail;
    }

    /**
     * Set player's jail status
     * @param inJail New jail status
     */
    public void setInJail(boolean inJail) {
        this.inJail = inJail;
    }

    /**
     * Set player's token
     * @param token Player's game token
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
     */
    public Token getToken() {
        return token;
    }

    /**
     * Get the game board
     * @return The game board
     */
    public Board getBoard() {
        return board;
    }
}