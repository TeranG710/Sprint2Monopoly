/*
 * CSCI 234: Intro to Software Engineering
 * Group: Giovanny, Jamell, Matt, Deborah
 * Purpose: This class is responsible for managing the player's money and transactions
 * Team Member(s) responsible: Jamell
 */

package Model.Board;
import Model.Exceptions.InsufficientFundsException;
import Model.Exceptions.InvalidTransactionException;
import Model.Exceptions.PlayerAlreadyExistsException;
import Model.Exceptions.PlayerNotFoundException;
import Model.Property.Property;
import java.util.*;

public class Banker {

    private int availableHouses;
    private int availableHotels;
    private static final int MAX_HOUSES = 32;
    private static final int MAX_HOTELS = 12;
    private static final int GO_MONEY = 200;
    private HashMap<Player, ArrayList<Property>> titleDeeds;
    private HashMap<Player, Integer> playerBalances;
    private ArrayList<Property> availableProperties;



    public Banker() {
        this.playerBalances = new HashMap<>();
        this.availableProperties = new ArrayList<>();
        this.availableHouses = MAX_HOUSES;
        this.availableHotels = MAX_HOTELS;
        this.titleDeeds = new HashMap<>();
    }


    /**
     * Initialize properties at the start of the game
     * @param properties List of all properties on the board
     *Team member(s) responsible: Matt
     */
    public void initializeProperties(List<Property> properties) {
        this.availableProperties.addAll(properties);
    }

    /**
     * Add a property to the banker's list of available properties
     * Team member(s) responsible: Jamell
     */
    public void addAvailableProperty(Property property) {
        availableProperties.add(property);
    }

    /**
     * Adds a player to the banker's list of players
     * if player is already in the list, throws PlayerNotFoundException
     *
     * @param player Player to add
     * Team member(s) responsible: Jamell
     */
    public void addPlayer(Player player) throws PlayerAlreadyExistsException{
        if (playerBalances.containsKey(player)) {
            throw new PlayerAlreadyExistsException();
        }
        playerBalances.put(player, 1500);
    }

    /**
     * removes a player from the banker's list of players
     * if player is not in the list, throws PlayerNotFoundException
     *
     * @param player Player to remove
     * Team member(s) responsible: Jamell
     */
    public void removePlayer(Player player) throws PlayerNotFoundException {
        if (!playerBalances.containsKey(player)) {
            throw new PlayerNotFoundException();
        }
        playerBalances.remove(player);
        titleDeeds.remove(player);
    }

    /**
     * Gets a player's balance from the banker
     * if player is not in the list, throws PlayerNotFoundException
     *
     * @param player Player to get balance of
     * @return int Player's balance
     * Team member(s) responsible: Jamell
     */
    public int getBalance(Player player) throws PlayerNotFoundException {
        if (!playerBalances.containsKey(player)) {
            throw new PlayerNotFoundException();
        }
        return playerBalances.get(player);
    }

    /**
     * Deposits money into a player's account
     * if player is not in the list, throws PlayerNotFoundException
     *
     * @param player Player to deposit money into
     * @param amount Amount to deposit
     * Team member(s) responsible: Jamell
     */
    public void deposit(Player player, int amount) throws PlayerNotFoundException {
        if (!playerBalances.containsKey(player)) {
            throw new PlayerNotFoundException();
        }
        if (amount < 0) {
            throw new InvalidTransactionException();
        }
        playerBalances.put(player, playerBalances.get(player) + amount);
    }

    /**
     * Withdraws money from a player's account
     * if player is not in the list, throws PlayerNotFoundException
     *
     * @param player Player to withdraw money from
     * @param amount Amount to withdraw
     * Team member(s) responsible: Jamell
     */
    public void withdraw(Player player, int amount) throws PlayerNotFoundException, InvalidTransactionException, InsufficientFundsException {
        if (!playerBalances.containsKey(player)) {
            throw new PlayerNotFoundException();
        }
        if (amount < 0) {
            throw new InvalidTransactionException();
        }
        if (playerBalances.get(player) < amount) {
            throw new InsufficientFundsException();
        }
        playerBalances.put(player, playerBalances.get(player) - amount);
    }

    /**
     * Add title deeds of a players propertu
     * Team member(s) responsible: Jamell
     */
    public void addTitleDeed(Player player, Property property) {
        titleDeeds.putIfAbsent(player, new ArrayList<>());
        titleDeeds.get(player).add(property);
        property.setOwner(player);
        availableProperties.remove(property);
    }

    /**
     * Remove title deeds of a players property
     * Team member(s) responsible: Jamell
     * **/
    public void removeTitleDeed(Player player, Property property) throws PlayerNotFoundException {
        if (!titleDeeds.containsKey(player)) {
            throw new PlayerNotFoundException();
        }
        ArrayList<Property> properties = titleDeeds.get(player);
        properties.remove(property);
        property.setOwner(null);
        availableProperties.add(property);
        if (properties.isEmpty()) {
            titleDeeds.remove(player);
        }
    }

    /**
     * Get title deeds of a players property
     * Team member(s) responsible: Jamell
     */
    public HashMap<Player, ArrayList<Property>> getTitleDeedsAll() {
        return titleDeeds;
    }

    /**
     * Get title deeds of a player
     * Team member(s) responsible: Jamell
     */
    public ArrayList<Property> getPlayerProperties(Player player) throws PlayerNotFoundException {
        if (!titleDeeds.containsKey(player)) {
            throw new PlayerNotFoundException();
        }
        return new ArrayList<>(titleDeeds.get(player));
    }
    /**
     * Transfer money from one player to another
     * @param from   The player to transfer from
     * @param to     The player to transfer to
     * @param amount The amount to transfer
     * @throws PlayerNotFoundException     if player is not found
     * @throws InvalidTransactionException if transaction is invalid
     * @throws InsufficientFundsException  if player has insufficient funds
     * Team member(s) responsible: Matt, Jamell
     */
    public void transferMoney(Player from, Player to, int amount) throws PlayerNotFoundException {
        if (amount < 0) {
            throw new InvalidTransactionException();
        }
        if (getBalance(from) < amount) {
            throw new InsufficientFundsException();
        }
        withdraw(from, amount);
        deposit(to, amount);
    }


    /**
     * Pay GO money to a player
     * @param player The player
     * @throws PlayerNotFoundException if player is not found
     * Team member(s) responsible: Matt
     */
    public void payGoMoney(Player player) throws PlayerNotFoundException {
        deposit(player, GO_MONEY);
    }

    /**
     * Sell a property to a player
     *
     * @param property The property to sell
     * @param player   The player buying the property
     * @throws PlayerNotFoundException    if player is not found
     * @throws InsufficientFundsException if player has insufficient funds
     * Team member(s) responsible: Matt
     */
    public void sellProperty(Property property, Player player) throws PlayerNotFoundException {
        if (!availableProperties.contains(property)) {
            throw new InvalidTransactionException();
        }
        int price = property.getPurchasePrice();
        withdraw(player, price);
        availableProperties.remove(property);
        property.setOwner(player);
        addTitleDeed(player, property);
    }

    /**
     * Buy a property back from a player (for mortgaging)
     * @param property The property to buy
     * @param player   The player selling the property
     * @throws PlayerNotFoundException if player is not found
     * Team member(s) responsible: Matt
     */
    public void buyBackProperty(Property property, Player player) throws PlayerNotFoundException {
        if (property.getOwner() != player) {
            throw new InvalidTransactionException();
        }
        int mortgageValue = property.getMortgageValue();
        deposit(player, mortgageValue);
        property.setMortgaged(true);
    }

    /**
     * Sell a house to a player for a specific property
     *
     * @param property The property to add a house to
     * @param player   The player buying the house
     * @throws PlayerNotFoundException    if player is not found
     * @throws InsufficientFundsException if player has insufficient funds
     *                                    Team member(s) responsible: Matt
     */
    public void sellHouse(Property property, Player player) throws PlayerNotFoundException {
        if (availableHouses <= 0) {
            throw new InvalidTransactionException();
        }
        if (property.getOwner() != player) {
            throw new InvalidTransactionException();
        }
        int housePrice = property.getHousePrice();
        withdraw(player, housePrice);
        property.addHouse();
        availableHouses--;
    }

    /**
     * Sell a hotel to a player for a specific property
     *
     * @param property The property to add a hotel to
     * @param player   The player buying the hotel
     * @throws PlayerNotFoundException    if player is not found
     * @throws InsufficientFundsException if player has insufficient funds
     * Team member(s) responsible: Matt
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
        withdraw(player, hotelPrice);
        property.addHotel();
        availableHouses += 4;
        availableHotels--;
    }

    /**
     * Buy back a house from a player
     *
     * @param property The property to remove a house from
     * @param player   The player selling the house
     * @throws PlayerNotFoundException if player is not found
     *  Team member(s) responsible: Matt
     */
    public void buyBackHouse(Property property, Player player) throws PlayerNotFoundException {
        if (property.getOwner() != player || property.getNumHouses() <= 0) {
            throw new InvalidTransactionException();
        }
        int housePrice = property.getHousePrice();
        deposit(player, housePrice);
        property.removeHouse();
        availableHouses++;
    }

    /**
     * Buy back a hotel from a player
     * @param property The property to remove a hotel from
     * @param player   The player selling the hotel
     * @throws PlayerNotFoundException if player is not found
     * Team member(s) responsible: Matt
     */
    public void buyBackHotel(Property property, Player player) throws PlayerNotFoundException {
        if (property.getOwner() != player || !property.hasHotel()) {
            throw new InvalidTransactionException();
        }
        if (availableHouses < 4) {
            throw new InvalidTransactionException();
        }
        int hotelPrice = property.getHousePrice() / 2;
        deposit(player, hotelPrice);
        property.removeHotel();
        availableHotels++;
        availableHouses -= 4;
    }

    /**
     * Collect rent from a player and pay it to the property owner
     *
     * @param property The property
     * @param player   The player landing on the property
     * @throws PlayerNotFoundException    if player is not found
     * @throws InsufficientFundsException if player has insufficient funds
     * Team member(s) responsible: Matt
     */
    public void collectRent(Property property, Player player) throws PlayerNotFoundException {
        Player owner = property.getOwner();
        if (owner == null || owner == player || property.isMortgaged()) {
            return;
        }
        int rent = property.calculateRent();
        transferMoney(player, owner, rent);
    }

    /**
     * Get the number of available houses
     * @return Number of houses
     * Team member(s) responsible: Matt
     */
    public int getAvailableHouses() {
        return availableHouses;
    }

    /**
     * Get the number of available hotels
     * @return Number of hotels
     * Team member(s) responsible: Matt
     */
    public int getAvailableHotels() {
        return availableHotels;
    }


}

