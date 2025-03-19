package Model.Board;

import Model.Exceptions.InsufficientFundsException;
import Model.Exceptions.InvalidTransactionException;
import Model.Exceptions.PlayerNotFoundException;
import Model.Property.Property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the Banker in Monopoly game
 * The Banker manages money, properties, houses and hotels
 * Team member(s) responsible: Matt
 */
public class Banker {
    private static final int MAX_HOUSES = 32;
    private static final int MAX_HOTELS = 12;
    private static final int GO_MONEY = 200;
    
    // Maps to track player balances
    private Map<Player, Integer> playerBalances;
    
    // Available properties, houses, and hotels
    private List<Property> availableProperties;
    private int availableHouses;
    private int availableHotels;
    
    /**
     * Constructor for Banker
     */
    public Banker() {
        this.playerBalances = new HashMap<>();
        this.availableProperties = new ArrayList<>();
        this.availableHouses = MAX_HOUSES;
        this.availableHotels = MAX_HOTELS;
    }
    
    /**
     * Add a player to the banker's tracking
     * @param player The player to add
     */
    public void addPlayer(Player player) {
        playerBalances.put(player, 1500); // Starting money in Monopoly
    }
    
    /**
     * Get a player's balance
     * @param player The player
     * @return The player's balance
     * @throws PlayerNotFoundException if player is not found
     */
    public int getBalance(Player player) throws PlayerNotFoundException {
        if (!playerBalances.containsKey(player)) {
            throw new PlayerNotFoundException();
        }
        return playerBalances.get(player);
    }
    
    /**
     * Deposit money to a player's balance
     * @param player The player
     * @param amount The amount to deposit
     * @throws PlayerNotFoundException if player is not found
     * @throws InvalidTransactionException if transaction is invalid
     */
    public void deposit(Player player, int amount) throws PlayerNotFoundException {
        if (!playerBalances.containsKey(player)) {
            throw new PlayerNotFoundException();
        }
        if (amount <= 0) {
            throw new InvalidTransactionException();
        }
        
        int currentBalance = playerBalances.get(player);
        playerBalances.put(player, currentBalance + amount);
    }
    
    /**
     * Withdraw money from a player's balance
     * @param player The player
     * @param amount The amount to withdraw
     * @throws PlayerNotFoundException if player is not found
     * @throws InvalidTransactionException if transaction is invalid
     * @throws InsufficientFundsException if player has insufficient funds
     */
    public void withdraw(Player player, int amount) throws PlayerNotFoundException {
        if (!playerBalances.containsKey(player)) {
            throw new PlayerNotFoundException();
        }
        if (amount <= 0) {
            throw new InvalidTransactionException();
        }
        
        int currentBalance = playerBalances.get(player);
        if (currentBalance < amount) {
            throw new InsufficientFundsException();
        }
        
        playerBalances.put(player, currentBalance - amount);
    }
    
    /**
     * Transfer money from one player to another
     * @param from The player to transfer from
     * @param to The player to transfer to
     * @param amount The amount to transfer
     * @throws PlayerNotFoundException if player is not found
     * @throws InvalidTransactionException if transaction is invalid
     * @throws InsufficientFundsException if player has insufficient funds
     */
    public void transferMoney(Player from, Player to, int amount) throws PlayerNotFoundException {
        withdraw(from, amount);
        deposit(to, amount);
    }
    
    /**
     * Pay GO money to a player
     * @param player The player
     * @throws PlayerNotFoundException if player is not found
     */
    public void payGoMoney(Player player) throws PlayerNotFoundException {
        deposit(player, GO_MONEY);
    }
    
    /**
     * Sell a property to a player
     * @param property The property to sell
     * @param player The player buying the property
     * @throws PlayerNotFoundException if player is not found
     * @throws InsufficientFundsException if player has insufficient funds
     */
    public void sellProperty(Property property, Player player) throws PlayerNotFoundException {
        if (!availableProperties.contains(property)) {
            throw new InvalidTransactionException();
        }
        
        int price = property.getPurchasePrice();
        
        // Withdraw money from player
        withdraw(player, price);
        
        // Transfer property to player
        availableProperties.remove(property);
        player.addProperty(property);
        property.setOwner(player);
    }
    
    /**
     * Buy a property back from a player (for mortgaging)
     * @param property The property to buy
     * @param player The player selling the property
     * @throws PlayerNotFoundException if player is not found
     */
    public void buyBackProperty(Property property, Player player) throws PlayerNotFoundException {
        if (property.getOwner() != player) {
            throw new InvalidTransactionException();
        }
        
        int mortgageValue = property.getMortgageValue();
        
        // Pay the player the mortgage value
        deposit(player, mortgageValue);
        
        // Mark the property as mortgaged
        property.setMortgaged(true);
    }
    
    /**
     * Sell a house to a player for a specific property
     * @param property The property to add a house to
     * @param player The player buying the house
     * @throws PlayerNotFoundException if player is not found
     * @throws InsufficientFundsException if player has insufficient funds
     */
    public void sellHouse(Property property, Player player) throws PlayerNotFoundException {
        if (availableHouses <= 0) {
            throw new InvalidTransactionException();
        }
        if (property.getOwner() != player) {
            throw new InvalidTransactionException();
        }
        
        int housePrice = property.getHousePrice();
        
        // Withdraw money from player
        withdraw(player, housePrice);
        
        // Add house to property
        property.addHouse();
        availableHouses--;
    }
    
    /**
     * Sell a hotel to a player for a specific property
     * @param property The property to add a hotel to
     * @param player The player buying the hotel
     * @throws PlayerNotFoundException if player is not found
     * @throws InsufficientFundsException if player has insufficient funds
     */
    public void sellHotel(Property property, Player player) throws PlayerNotFoundException {
        if (availableHotels <= 0) {
            throw new InvalidTransactionException();
        }
        if (property.getOwner() != player) {
            throw new InvalidTransactionException();
        }
        if (property.getNumHouses() != 4) {
            throw new InvalidTransactionException();
        }
        
        int hotelPrice = property.getHousePrice();
        
        // Withdraw money from player
        withdraw(player, hotelPrice);
        
        // Add hotel to property and return houses to bank
        property.addHotel();
        availableHouses += 4;
        availableHotels--;
    }
    
    /**
     * Buy back a house from a player
     * @param property The property to remove a house from
     * @param player The player selling the house
     * @throws PlayerNotFoundException if player is not found
     */
    public void buyBackHouse(Property property, Player player) throws PlayerNotFoundException {
        if (property.getOwner() != player || property.getNumHouses() <= 0) {
            throw new InvalidTransactionException();
        }
        
        int housePrice = property.getHousePrice() / 2; // Half price when selling back
        
        // Pay the player
        deposit(player, housePrice);
        
        // Remove house from property
        property.removeHouse();
        availableHouses++;
    }
    
    /**
     * Buy back a hotel from a player
     * @param property The property to remove a hotel from
     * @param player The player selling the hotel
     * @throws PlayerNotFoundException if player is not found
     */
    public void buyBackHotel(Property property, Player player) throws PlayerNotFoundException {
        if (property.getOwner() != player || !property.hasHotel()) {
            throw new InvalidTransactionException();
        }
        if (availableHouses < 4) {
            throw new InvalidTransactionException(); // Can't return hotel if not enough houses available
        }
        
        int hotelPrice = property.getHousePrice() / 2; // Half price when selling back
        
        // Pay the player
        deposit(player, hotelPrice);
        
        // Remove hotel from property and give houses back to player
        property.removeHotel();
        availableHotels++;
        availableHouses -= 4;
    }
    
    /**
     * Collect rent from a player and pay it to the property owner
     * @param property The property
     * @param player The player landing on the property
     * @throws PlayerNotFoundException if player is not found
     * @throws InsufficientFundsException if player has insufficient funds
     */
    public void collectRent(Property property, Player player) throws PlayerNotFoundException {
        Player owner = property.getOwner();
        if (owner == null || owner == player || property.isMortgaged()) {
            return; // No rent to pay
        }
        
        int rent = property.calculateRent();
        
        // Transfer money from player to owner
        transferMoney(player, owner, rent);
    }
    
    /**
     * Get the number of available houses
     * @return Number of houses
     */
    public int getAvailableHouses() {
        return availableHouses;
    }
    
    /**
     * Get the number of available hotels
     * @return Number of hotels
     */
    public int getAvailableHotels() {
        return availableHotels;
    }
    
    /**
     * Initialize properties at the start of the game
     * @param properties List of all properties on the board
     */
    public void initializeProperties(List<Property> properties) {
        this.availableProperties.addAll(properties);
    }
}