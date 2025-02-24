package Model;

/**
 * Represents a property space on the Monopoly board.
 * Properties can be owned, mortgaged, and improved with houses/hotels.
 */
public class Property extends BoardSpace {
    private final int purchasePrice;
    private final int baseRent;
    private final int[] houseRents;  // Rents for 1-4 houses
    private final int hotelRent;
    private final int mortgageValue;
    private final ColorGroup colorGroup;
    
    private Player owner;
    private boolean isMortgaged;
    private int numHouses;
    private boolean hasHotel;

    /**
     * Constructor for Property
     * @param name Property name
     * @param position Board position
     * @param purchasePrice Cost to buy the property
     * @param baseRent Rent with no houses
     * @param houseRents Array of rents for 1-4 houses
     * @param hotelRent Rent with a hotel
     * @param mortgageValue Mortgage value
     * @param colorGroup Color group this property belongs to
     */
    public Property(String name, int position, int purchasePrice, int baseRent, 
                   int[] houseRents, int hotelRent, int mortgageValue, ColorGroup colorGroup) {
        super(name, position);
        this.purchasePrice = purchasePrice;
        this.baseRent = baseRent;
        this.houseRents = houseRents;
        this.hotelRent = hotelRent;
        this.mortgageValue = mortgageValue;
        this.colorGroup = colorGroup;
        this.isMortgaged = false;
        this.numHouses = 0;
        this.hasHotel = false;
    }

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

    @Override
    public void onPassing(Player player) {
        // Nothing happens when passing over a property
    }

    /**
     * Offers the property for purchase to the given player
     * @param player The player who has the option to buy
     */
    private void offerPurchase(Player player) {
        // In the future, this would interact with the UI
        // For now, we'll just provide the method signature
    }

    /**
     * Calculates and collects rent from the player
     * @param player The player who must pay rent
     */
    private void collectRent(Player player) {
        int rentAmount = calculateRent();
        player.decreaseMoney(rentAmount);
        owner.increaseMoney(rentAmount);
    }

    /**
     * Calculates the current rent based on houses/hotels and color group ownership
     * @return The rent amount
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
     */
    private boolean canAddHotel() {
        // Similar to canAddHouse, but for hotels
        return colorGroup.canAddHotel(this);
    }

    /**
     * Mortgages the property
     * @return true if property was successfully mortgaged
     */
    public boolean mortgage() {
        if (!isMortgaged && numHouses == 0 && !hasHotel) {
            isMortgaged = true;
            owner.increaseMoney(mortgageValue);
            return true;
        }
        return false;
    }

    /**
     * Unmortgages the property
     * @return true if property was successfully unmortgaged
     */
    public boolean unmortgage() {
        if (isMortgaged) {
            int cost = (int)(mortgageValue * 1.1); // 10% interest
            if (owner.canAfford(cost)) {
                owner.decreaseMoney(cost);
                isMortgaged = false;
                return true;
            }
        }
        return false;
    }

    // Getters and setters

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isMortgaged() {
        return isMortgaged;
    }

    public int getNumHouses() {
        return numHouses;
    }

    public boolean hasHotel() {
        return hasHotel;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public ColorGroup getColorGroup() {
        return colorGroup;
    }
}