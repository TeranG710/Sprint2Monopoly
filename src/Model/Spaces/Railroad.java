package Model.Spaces;

import Model.Property.Banker;
import Model.Exceptions.PlayerNotFoundException;
import Model.Board.Player;

public class Railroad extends BoardSpace {
    private static final int PURCHASE_PRICE = 200;
    private static final int BASE_RENT = 25;
    private static final double RENT_MULTIPLIER = 2;
    private Player owner;
    private Banker banker;

    /**
     * Constructor for Railroad
     *
     * @param name
     * @param position Team member(s) responsible: Deborah
     */
    public Railroad(String name, int position) {
        super(name, position);
        this.owner = null;
    }

    /**
     * Pay rent to the owner if the space is owned, otherwise buy the space
     *
     * @param player The player who landed on the space
     *               Team member(s) responsible: Deborah, Updated latest by Matt
     */
    @Override
public void onLanding(Player player) throws PlayerNotFoundException {
    if (owner == null) {
        // Offer to purchase the railroad
        if (banker != null) {
            // Use banker to handle purchase
            if (player.canAfford(PURCHASE_PRICE)) {
                banker.withdraw(player, PURCHASE_PRICE);
                this.owner = player;
                System.out.println(player.getName() + " landed on " + getName() + " and bought it for $" + PURCHASE_PRICE);
            }
        } else {
            System.out.println(player.getName() + " landed on " + getName() + " and bought it for $" + PURCHASE_PRICE);
        }
    } else if (owner != player) {
        // Pay rent
        int rent = calculateRent();
        if (banker != null) {
            banker.transferMoney(player, owner, rent);
        } else {
            banker.withdraw(player, rent);
            banker.deposit(owner, rent);
        }
        System.out.println(player.getName() + " landed on " + getName() + " and paid " + owner.getName() + " $" + rent + " in rent");
    }
}

    /**
     * Do nothing when a player passes over the space
     *
     * @param player The player who passed over the space
     *               Team member(s) responsible: Deborah
     */
    @Override
    public void onPassing(Player player) {
        // Do nothing
    }

    public void SetOwner(Player player) {
        owner = player;
    }

    public Player getOwner() {
        return owner;
    }

    public int getPurchasePrice() {
        return PURCHASE_PRICE;
    }

    /**
     * Calculate the rent based on the number of railroads owned by the owner
     *
     * @return
     */
    private int calculateRent() {
        if (owner == null) {
            return 0;
        }
        int numRailroads = owner.getNumRailroads();
        return BASE_RENT * (int) Math.pow(RENT_MULTIPLIER, numRailroads - 1);

    }

}