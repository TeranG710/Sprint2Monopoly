package Model.Board;

import Model.Property.PropertyColor;
import Model.Player;
public class Railroad extends BoardSpace {
    private static final int PURCHASE_PRICE = 200;
    private static final int BASE_RENT = 25;
    private static final double RENT_MULTIPLIER = 2;
    private Player owner;

    public Railroad(String name, int position) {
        super(name, position);
        this.owner = null;
    }

    @Override
    public void onLanding(Player player) {
        if (owner == null) {
            // Needs the UI logic
            System.out.println(player.getName() + " landed on " + getName() + " and bought it for $" + PURCHASE_PRICE);
        } else if (owner != player) {
            int rent = calculateRent();
            System.out.println(player.getName() + " landed on " + getName() + " and paid " + owner.getName() + " $" + rent + " in rent");
            player.decreaseMoney(rent);
            owner.increaseMoney(rent);
        }
    }
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

    private int calculateRent() {
        if (owner == null) {
            return 0;
        }
        int numRailroads = owner.getNumRailroads();
        return BASE_RENT * (int) Math.pow(RENT_MULTIPLIER, numRailroads - 1);

    }

}