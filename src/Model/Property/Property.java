package Model.Property;

import Model.Board.Player;
import Model.Spaces.BoardSpace;

/**
 * Represents a property space on the Monopoly board.
 * Properties can be owned, mortgaged, and improved with houses/hotels.
 * Team member(s) responsible: Matt
 */
public class Property extends BoardSpace {
    private final int purchasePrice;
    private final int baseRent;
    private final int[] houseRents;  // Rents for 1-4 houses
    private final int hotelRent;
    private final int mortgageValue;
    private final PropertyColor color;
    private final ColorGroup colorGroup;
    
    private Player owner;
    private boolean isMortgaged;
    private int numHouses;
    private boolean hasHotel;
    private int housePrice; // Price for houses and hotels

    /**
     * Constructor for Property
     * @param name Property name
     * @param position Board position
     * @param purchasePrice Cost to buy the property
     * @param baseRent Rent with no houses
     * @param houseRents Array of rents for 1-4 houses
     * @param hotelRent Rent with a hotel
     * @param mortgageValue Mortgage value
     * @param color Color of the property
     * @param colorGroup Color group this property belongs to
     * Team member(s) responsible: Original implementation team
     */
    public Property(String name, int position, int purchasePrice, int baseRent, 
                    int[] houseRents, int hotelRent, int mortgageValue, 
                    PropertyColor color, ColorGroup colorGroup) {
        super(name, position);
        this.purchasePrice = purchasePrice;
        this.baseRent = baseRent;
        this.houseRents = houseRents;
        this.hotelRent = hotelRent;
        this.mortgageValue = mortgageValue;
        this.color = color;
        this.colorGroup = colorGroup;
        this.isMortgaged = false;
        this.numHouses = 0;
        this.hasHotel = false;
        // Set house price based on color group
        setHousePriceByColor();
    }

    /**
     * Set the house price based on the property color
     * Team member(s) responsible: Matt
     */
    private void setHousePriceByColor() {
        switch (color) {
            case BROWN:
            case LIGHT_BLUE:
                this.housePrice = 50;
                break;
            case PINK:
            case ORANGE:
                this.housePrice = 100;
                break;
            case RED:
            case YELLOW:
                this.housePrice = 150;
                break;
            case GREEN:
            case DARK_BLUE:
                this.housePrice = 200;
                break;
            default:
                this.housePrice = 0;
        }
    }

    /**
     * Defines what happens when a player lands on this property
     * @param player The player who landed on the space
     * Team member(s) responsible: Matt
     */
    @Override
    public void onLanding(Player player) {
        if (owner == null) {
            // Player can choose to buy property
            offerPurchase(player);
        } else if (owner != player && !isMortgaged) {
            // Player must pay rent
            collectRent(player);
        }
        // If mortgaged or player owns the property, nothing happens
    }

    /**
     * Defines what happens when a player passes over this property
     * @param player The player who passed over the space
     * Team member(s) responsible: Matt
     */
    @Override
    public void onPassing(Player player) {
        // Nothing happens when passing over a property
    }

    /**
     * Offers the property for purchase to the given player
     * @param player The player who has the option to buy
     * Team member(s) responsible: Matt
     */
    private void offerPurchase(Player player) {
        // This would interact with the UI to get player's decision
        // For now, assuming player decides to buy
        
        try {
            // Get the banker reference from the player's board
            Model.Board.Banker banker = player.getBoard().getBanker();
            
            // Use banker to handle the property purchase
            banker.sellProperty(this, player);
        } catch (Exception e) {
            // Fallback in case banker integration isn't complete
            if (player.canAfford(purchasePrice)) {
                player.decreaseMoney(purchasePrice);
                player.addProperty(this);
                this.owner = player;
            }
        }
    }

    /**
     * Calculates and collects rent from the player
     * @param player The player who must pay rent
     * Team member(s) responsible: Matt
     */
    private void collectRent(Player player) {
        if (owner == null || owner == player || isMortgaged) {
            return; // No rent to pay
        }
        
        try {
            // Get the banker reference from the player's board
            Model.Board.Banker banker = player.getBoard().getBanker();
            
            // Use banker to collect rent
            banker.collectRent(this, player);
        } catch (Exception e) {
            // Fallback in case banker integration isn't complete
            int rentAmount = calculateRent();
            player.decreaseMoney(rentAmount);
            owner.increaseMoney(rentAmount);
        }
    }

    /**
     * Calculates the current rent based on houses/hotels and color group ownership
     * @return The rent amount
     * Team member(s) responsible: Matt
     */
    public int calculateRent() {
        if (isMortgaged) {
            return 0;
        }

        int rent;
        if (hasHotel) {
            rent = hotelRent;
        } else if (numHouses > 0) {
            rent = houseRents[numHouses - 1];
        } else {
            rent = baseRent;
            // Double rent if owner has all properties in color group
            if (colorGroup.hasMonopoly(owner)) {
                rent *= 2;
            }
        }
        return rent;
    }

    /**
     * Adds a house to the property
     * @return true if house was successfully added
     * Team member(s) responsible: Matt
     */
    public boolean addHouse() {
        if (numHouses < 4 && !hasHotel && canAddHouse()) {
            numHouses++;
            return true;
        }
        return false;
    }

    /**
     * Upgrades the property to a hotel
     * @return true if hotel was successfully added
     * Team member(s) responsible: Original implementation team
     */
    public boolean addHotel() {
        if (numHouses == 4 && !hasHotel && canAddHotel()) {
            numHouses = 0;
            hasHotel = true;
            return true;
        }
        return false;
    }

    /**
     * Checks if a house can be added based on game rules
     * Team member(s) responsible: Matt
     */
    private boolean canAddHouse() {
        // Check if owner has monopoly
        if (!colorGroup.hasMonopoly(owner)) {
            return false;
        }
        // Check if building is even across color group
        return colorGroup.canAddHouse(this);
    }

    /**
     * Checks if a hotel can be added based on game rules
     * Team member(s) responsible: Matt
     */
    private boolean canAddHotel() {
        // Similar to canAddHouse, but for hotels
        return colorGroup.canAddHotel(this);
    }

    /**
     * Mortgages the property
     * @return true if property was successfully mortgaged
     * Team member(s) responsible:  Matt
     */
    public boolean mortgage() {
        if (!isMortgaged && numHouses == 0 && !hasHotel) {
            try {
                // Get the banker reference from the owner's board
                Model.Board.Banker banker = owner.getBoard().getBanker();
                
                // Use banker to mortgage property
                banker.buyBackProperty(this, owner);
                return true;
            } catch (Exception e) {
                // Fallback in case banker integration isn't complete
                isMortgaged = true;
                owner.increaseMoney(mortgageValue);
                return true;
            }
        }
        return false;
    }

    /**
     * Unmortgages the property
     * @return true if property was successfully unmortgaged
     * Team member(s) responsible: Matt
     */
    public boolean unmortgage() {
        if (isMortgaged) {
            int cost = (int)(mortgageValue * 1.1); // 10% interest
            
            try {
                // Get the banker reference from the owner's board
                Model.Board.Banker banker = owner.getBoard().getBanker();
                
                if (banker.getBalance(owner) >= cost) {
                    banker.withdraw(owner, cost);
                    isMortgaged = false;
                    return true;
                }
            } catch (Exception e) {
                // Fallback in case banker integration isn't complete
                if (owner.canAfford(cost)) {
                    owner.decreaseMoney(cost);
                    isMortgaged = false;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Remove a house from the property
     * @return true if house was successfully removed
     * Team member(s) responsible: Matt
     */
    public boolean removeHouse() {
        if (numHouses > 0) {
            numHouses--;
            return true;
        }
        return false;
    }

    /**
     * Remove a hotel from the property
     * Returns the property to having 4 houses
     * @return true if hotel was successfully removed
     * Team member(s) responsible: Matt
     */
    public boolean removeHotel() {
        if (hasHotel) {
            hasHotel = false;
            numHouses = 4; // When selling a hotel, you get 4 houses back
            return true;
        }
        return false;
    }

    // Getters and setters

    /**
     * Get the property owner
     * @return The owner of the property
     * Team member(s) responsible: Matt
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Set the property owner
     * @param owner The new owner
     * Team member(s) responsible: Matt
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * Check if property is mortgaged
     * @return true if the property is mortgaged
     * Team member(s) responsible: Matt
     */
    public boolean isMortgaged() {
        return isMortgaged;
    }

    /**
     * Set the mortgage status of the property
     * @param mortgaged New mortgage status
     * Team member(s) responsible: Matt
     */
    public void setMortgaged(boolean mortgaged) {
        this.isMortgaged = mortgaged;
    }

    /**
     * Get the number of houses on the property
     * @return Number of houses
     * Team member(s) responsible: Matt
     */
    public int getNumHouses() {
        return numHouses;
    }

    /**
     * Check if property has a hotel
     * @return true if the property has a hotel
     * Team member(s) responsible: Matt
     */
    public boolean hasHotel() {
        return hasHotel;
    }

    /**
     * Get the purchase price of the property
     * @return Purchase price
     * Team member(s) responsible: Matt
     */
    public int getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * Get the color group this property belongs to
     * @return Color group
     * Team member(s) responsible: Matt
     */
    public ColorGroup getColorGroup() {
        return colorGroup;
    }

    /**
     * Get the property color
     * @return Property color
     * Team member(s) responsible: Matt
     */
    public PropertyColor getColor() {
        return color;
    }

    /**
     * Get the mortgage value of the property
     * @return Mortgage value
     * Team member(s) responsible: Matt
     */
    public int getMortgageValue() {
        return mortgageValue;
    }

    /**
     * Get the price of a house for this property
     * @return House price
     * Team member(s) responsible: Matt
     */
    public int getHousePrice() {
        return housePrice;
    }
}