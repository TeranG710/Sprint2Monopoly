package Model.Spaces;

import Model.Player;
import Model.Board.Dice;
import Model.Spaces.BoardSpace;

public class UtilitySpace extends BoardSpace {
    private static final int PURCHASE_PRICE = 150;
    private static final int RENT_FOR_ONE_OWNED = 4;
    private static final int RENT_FOR_TWO_OWNED = 10;
    private Player owner;

    /**
     * Constructor for UtilitySpace
     *
     * @param name
     * @param position Team member(s) responsible: Deborah
     */
    public UtilitySpace(String name, int position) {
        super(name, position);
        this.owner = null;
    }

    /**
     * Pay rent to the owner if the space is owned, otherwise buy the space
     *
     * @param player The player who landed on the space
     *               Team member(s) responsible: Deborah
     */
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

    /**
     * Calculate the rent to be paid by the player
     *
     * @param player
     * @return Team member(s) responsible: Deborah
     */
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


}
