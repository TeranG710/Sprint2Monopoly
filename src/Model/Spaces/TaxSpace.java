package Model.Spaces;
import Model.Player;


public class TaxSpace extends BoardSpace {
    private static final int TAX_AMOUNT = 200;
    private static final double TAX_RATE = 0.1;
    public TaxSpace(int position) {
        super("Income Tax", position);
    }

    /**
     * Player can chose whether they want to pay a flat fee or 10% of their total assets
     * @param player The player who landed on the space
     * Team member(s) responsible: Deborah
     */
    @Override
    public void onLanding(Player player) {
        int calculatedTax = (int) (player.getMoney() * TAX_RATE);
        player.decreaseMoney(calculatedTax);

    }
    @Override
    public void onPassing(Player player) {
        // Do nothing
    }
}
