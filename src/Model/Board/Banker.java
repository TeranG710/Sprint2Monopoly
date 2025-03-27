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
import Model.Spaces.BoardSpace;

import java.util.*;

public class Banker {

    private int availableHouses;
    private int availableHotels;
    private static final int MAX_HOUSES = 32;
    private static final int MAX_HOTELS = 12;
    private static final int GO_MONEY = 200;
    private HashMap<Player, ArrayList<BoardSpace>> titleDeeds;
    private HashMap<Player, Integer> playerBalances;
    private ArrayList<BoardSpace> availableProperties;


    public Banker() {
        this.playerBalances = new HashMap<>();
        this.availableProperties = new ArrayList<>();
        this.availableHouses = MAX_HOUSES;
        this.availableHotels = MAX_HOTELS;
        this.titleDeeds = new HashMap<>();
    }


    /**
     * Initialize properties at the start of the game
     *
     * @param properties List of all properties on the board
     * Team member(s) responsible: Matt, Jamell
     */
    public void initializeProperties(List<BoardSpace> properties) {
        this.availableProperties.addAll(properties);
    }

    /**
     * Add a property to the banker's list of available properties
     * Team member(s) responsible: Jamell
     */
    public void addAvailableProperty(BoardSpace property) {
        availableProperties.add(property);
    }

    /**
     * Adds a player to the banker's list of players
     * if player is already in the list, throws PlayerNotFoundException
     *
     * @param player Player to add
     * Team member(s) responsible: Jamell
     */
    public void addPlayer(Player player) throws PlayerAlreadyExistsException {
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
     *               Team member(s) responsible: Jamell
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
     *               Team member(s) responsible: Jamell
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
    public void addTitleDeed(Player player, BoardSpace property) {
        titleDeeds.putIfAbsent(player, new ArrayList<>());
        titleDeeds.get(player).add(property);
        property.setOwner(player);
        availableProperties.remove(property);
    }

    /**
     * Remove title deeds of a players property
     * Team member(s) responsible: Jamell
     **/
    public void removeTitleDeed(Player player, BoardSpace property) throws PlayerNotFoundException {
        if (!titleDeeds.containsKey(player)) {
            throw new PlayerNotFoundException();
        }
        ArrayList<BoardSpace> properties = titleDeeds.get(player);
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
    public HashMap<Player, ArrayList<BoardSpace>> getTitleDeedsAll() {
        return titleDeeds;
    }

    /**
     * Get title deeds of a player
     * Team member(s) responsible: Jamell
     */
    public ArrayList<BoardSpace> getPlayerProperties(Player player) throws PlayerNotFoundException {
        if (!titleDeeds.containsKey(player)) {
            throw new PlayerNotFoundException();
        }
        return new ArrayList<>(titleDeeds.get(player));
    }

    /**
     * Transfer money from one player to another
     *
     * @param from   The player to transfer from
     * @param to     The player to transfer to
     * @param amount The amount to transfer
     * @throws PlayerNotFoundException     if player is not found
     * @throws InvalidTransactionException if transaction is invalid
     * @throws InsufficientFundsException  if player has insufficient funds
     *                                     Team member(s) responsible: Matt, Jamell
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
     *
     * @param player The player
     * @throws PlayerNotFoundException if player is not found
     *                                 Team member(s) responsible: Matt
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
    public void sellProperty(BoardSpace property, Player player) throws PlayerNotFoundException {
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
     *
     * @param property The property to buy
     * @param player   The player selling the property
     * @throws PlayerNotFoundException if player is not found
     *  Team member(s) responsible: Matt
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
     *                                    Team member(s) responsible: Matt
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
     *                                 Team member(s) responsible: Matt
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
     *
     * @param property The property to remove a hotel from
     * @param player   The player selling the hotel
     * @throws PlayerNotFoundException if player is not found
     *                                 Team member(s) responsible: Matt
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
     *                                    Team member(s) responsible: Matt
     */
    public void collectRent(BoardSpace property, Player player) throws PlayerNotFoundException {
        Player owner = property.getOwner();
        int rent = property.calculateRent(player);
        transferMoney(player, owner, rent);
    }

    /**
     * Get the number of available houses
     *
     * @return Number of houses
     * Team member(s) responsible: Matt
     */
    public int getAvailableHouses() {
        return availableHouses;
    }

    /**
     * Get the number of available hotels
     *
     * @return Number of hotels
     * Team member(s) responsible: Matt
     */
    public int getAvailableHotels() {
        return availableHotels;
    }

    
        /**
     * Conducts an auction for a property among a list of bidders.
     * @param property The property being auctioned
     * @param bidders The players participating in the auction
     * @return The winning player, or null if no one won the auction
     * Team member(s) responsible: Matt
     */
    public Player auctionProperty(BoardSpace property, List<Player> bidders) {
        // Validation
        if (property == null || bidders == null || bidders.isEmpty()) {
            System.out.println("Cannot conduct auction: Invalid property or no bidders.");
            return null;
        }
        
        System.out.println("\nAuction for " + property.getName() + " (Value: $" + property.getPurchasePrice() + ")");
        
        int highestBid = 0;
        Player highestBidder = null;
        
        // Keep track of active bidders
        Map<Player, Boolean> stillBidding = new HashMap<>();
        for (Player player : bidders) {
            stillBidding.put(player, true);
        }
        
        // Continue auction until only one player is left bidding or all pass
        boolean auctionActive = true;
        while (auctionActive) {
            // Count players still in the auction
            int activeBidders = 0;
            for (Boolean active : stillBidding.values()) {
                if (active) activeBidders++;
            }
            
            // If only one or zero active bidders remain, end the auction
            if (activeBidders <= 1) {
                auctionActive = false;
                break;
            }
            
            // Each player gets a chance to bid
            for (Player player : bidders) {
                // Skip players who have already passed
                if (!stillBidding.get(player)) {
                    continue;
                }
                
                try {
                    // Skip if player can't afford to bid higher than current bid
                    if (getBalance(player) <= highestBid) {
                        stillBidding.put(player, false);
                        System.out.println(player.getName() + " cannot afford to bid higher and is out of the auction.");
                        continue;
                    }
                    
                    // Get bid from player (simplified for now)
                    int maxPossibleBid = getBalance(player);
                    int minimumBid = highestBid + 1;
                    int bid = getBidFromPlayer(player, property, highestBid, maxPossibleBid);
                    
                    // Player passes
                    if (bid <= highestBid) {
                        stillBidding.put(player, false);
                        System.out.println(player.getName() + " passes on bidding for " + property.getName());
                        continue;
                    }
                    
                    // Valid bid
                    highestBid = bid;
                    highestBidder = player;
                    System.out.println(player.getName() + " bids $" + bid + " for " + property.getName());
                    
                } catch (PlayerNotFoundException e) {
                    System.out.println("Error: " + e.getMessage());
                    stillBidding.put(player, false);
                }
            }
            
            // Check if only one bidder remains active
            activeBidders = 0;
            for (Boolean active : stillBidding.values()) {
                if (active) activeBidders++;
            }
            
            if (activeBidders <= 1) {
                auctionActive = false;
            }
        }
        
        // Process the winning bid
        if (highestBidder != null && highestBid > 0) {
            try {
                // Deduct money from player
                withdraw(highestBidder, highestBid);
                
                // Update property ownership
                property.setOwner(highestBidder);
                addTitleDeed(highestBidder, property);
                
                System.out.println(highestBidder.getName() + " won the auction for " + 
                                property.getName() + " with a bid of $" + highestBid);
                
            } catch (PlayerNotFoundException e) {
                System.out.println("Error processing auction transaction: " + e.getMessage());
                return null;
            }
        } else {
            System.out.println("No bids were placed. " + property.getName() + " remains with the bank.");
        }
        
        return highestBidder;
    }

    /**
     * Gets a bid from a player in an auction.
     * For computer players, uses AI logic. For human players, gets input from console.
     * 
     * @param player The player making the bid
     * @param property The property being auctioned
     * @param currentHighestBid The current highest bid
     * @param maxPossibleBid The maximum the player can bid
     * @return The bid amount, or 0 to pass
     * Team member(s) responsible: Matt
     */
    private int getBidFromPlayer(Player player, BoardSpace property, int currentHighestBid, int maxPossibleBid) {
        // Computer player logic - simplified for testing
        if (player.getName().startsWith("Cpu")) {
            // Simple strategy: bid up to 80% of property value if they can afford it
            int propertyValue = property.getPurchasePrice();
            int maxWillingToBid = (int)(propertyValue * 0.8);
            maxWillingToBid = Math.min(maxWillingToBid, maxPossibleBid);
            
            // If current bid is already higher than willing to pay, pass
            if (currentHighestBid >= maxWillingToBid) {
                return 0; // Pass
            }
            
            // If first bid, start at 50% of property value
            if (currentHighestBid == 0) {
                return Math.min(propertyValue / 2, maxPossibleBid);
            }
            
            // Otherwise, bid minimum increment
            return currentHighestBid + 10;
        }
        
        // Human player logic - simplified for testing
        // In actual implementation, this would use a GUI
        // For testing, just bid 75% of property value if can afford it
        int propertyValue = property.getPurchasePrice();
        int suggestedBid = (int)(propertyValue * 0.75);
        
        // Make sure bid is higher than current highest bid
        suggestedBid = Math.max(suggestedBid, currentHighestBid + 10);
        
        // Ensure can't bid more than have
        suggestedBid = Math.min(suggestedBid, maxPossibleBid);
        
        // If can't afford to outbid, pass
        if (suggestedBid <= currentHighestBid) {
            return 0;
        }
        
        return suggestedBid;
    }
}