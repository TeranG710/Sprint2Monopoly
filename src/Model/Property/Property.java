package Model.Property;
import Model.Spaces.BoardSpace;
import Model.Player;
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
    private final PropertyColor color;
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
     * @param color Color of the property
     * @param colorGroup Color group this property belongs to
     * Team member(s) responsible: Matt
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
    }

    /**
     * Called when a player lands on this space
     * Team member(s) responsible: Matt
     * */
    @Override
    public void onLanding(Player player) {
        if (owner == null) {
            offerPurchase(player);
        } else if (owner != player && !isMortgaged) {
            collectRent(player);
        }
    }

    /**
     * Called when a player passes this space
     * Team member(s) responsible: Matt
     * */
    @Override
    public void onPassing(Player player) {
    }

    /**
     * Offers the property for purchase to the given player
     * @param player The player who has the option to buy
     * Team member(s) responsible: Matt
     */
    private void offerPurchase(Player player) {

    }

    /**
     * Calculates and collects rent from the player
     * @param player The player who must pay rent
     * Team member(s) responsible: Matt
     */
    private void collectRent(Player player) {
        int rentAmount = calculateRent();
        player.decreaseMoney(rentAmount);
        owner.increaseMoney(rentAmount);
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
     * Team member(s) responsible: Matt
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
        if (!colorGroup.hasMonopoly(owner)) {
            return false;
        }
        return colorGroup.canAddHouse(this);
    }

    /**
     * Checks if a hotel can be added based on game rules
     * Team member(s) responsible: Matt
     */
    private boolean canAddHotel() {
        return colorGroup.canAddHotel(this);
    }

    /**
     * Mortgages the property
     * @return true if property was successfully mortgaged
     * Team member(s) responsible: Matt
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
     * Team member(s) responsible: Matt
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

    /**
     * returns the owner of the property
     * Team member(s) responsible: Matt
     * */
    public Player getOwner() {
        return owner;
    }

    /**
     * sets the owner of the property
     * Team member(s) responsible: Matt
     * */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * returns the mortgage value of the property
     * Team member(s) responsible: Matt
     * */
    public boolean isMortgaged() {
        return isMortgaged;
    }

    /**
     * returns the number of houses on the property
     * Team member(s) responsible: Matt
     * */
    public int getNumHouses() {
        return numHouses;
    }

    /**
     * returns if the property has a hotel
     * Team member(s) responsible: Matt
     * */
    public boolean hasHotel() {
        return hasHotel;
    }

    /**
     * returns the purchase price of the property
     * Team member(s) responsible: Matt
     * */
    public int getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * returns the mortgage value of the property
     * Team member(s) responsible: Matt
     * */
    public ColorGroup getColorGroup() {
        return colorGroup;
    }

    /**
     * returns the color of the property
     * Team member(s) responsible: Matt
     * */
    public PropertyColor getColor() {
        return color;
    }
}