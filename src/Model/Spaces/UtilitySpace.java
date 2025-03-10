package Model.Spaces;

import Model.Player;
import Model.Board.Dice;

public class UtilitySpace extends BoardSpace {
    private static final int PURCHASE_PRICE = 150;
    private static final int RENT_FOR_ONE_OWNED = 4;
    private static final int RENT_FOR_TWO_OWNED = 10;
    private Player owner;

    public UtilitySpace(String name, int position) {
        super(name, position);
        this.owner = null;
    }

    @Override
    public void onLanding(Player player) {
        Dice dice = player.getBoard().getDice();
        if (owner == null) {
            // Needs the UI logic
            System.out.println(player.getName() + " landed on " + getName() + " and bought it for $" + PURCHASE_PRICE);
            player.decreaseMoney(PURCHASE_PRICE);
            owner = player;
        } else if (owner != player) {
            int rent = calculateRent(player);
            System.out.println(player.getName() + " landed on " + getName() + " and paid " + owner.getName() + " $" + rent + " in rent");
            player.decreaseMoney(rent);
            owner.increaseMoney(rent);
        }
    }

    private int calculateRent(Player player) {
        int numOwnedByOwner = owner.getNumUtilities();
        int diceRoll = player.getBoard().getDice().getSum();

        if (numOwnedByOwner == 1) {
            return RENT_FOR_ONE_OWNED * diceRoll;
        } else if (numOwnedByOwner == 2) {
            return RENT_FOR_TWO_OWNED * diceRoll;
        } else {
            return 0;
        }
    }
    @Override
    public void onPassing(Player player) {
        // Do nothing
    }

}
