package Model.Spaces;
import Model.Player;

// Needs to be modified to allow user choice between paying a flat fee or 10% of their total assets
public class TaxSpace extends BoardSpace {
    private static final int INCOME_TAX_AMOUNT = 200;
    private static final int LUXURY_TAX_AMOUNT = 75;
    private static final double TAX_RATE = 0.1;
    public TaxSpace(String name, int position) {
        super(name, position);
    }

    /**
     * Player can chose whether they want to pay a flat fee or 10% of their total assets
     * @param player The player who landed on the space
     * Team member(s) responsible: Deborah
     */
    @Override
    public void onLanding(Player player) {
        if (getName().equals("Income Tax")) {
            int percentageTax = (int) (player.getMoney() * TAX_RATE);
            int taxToPay = getUserChoice(player, INCOME_TAX_AMOUNT, percentageTax);
            System.out.println(player.getName() + " chose to pay $" + taxToPay + " in Income Tax.");
        } else if (getName().equals("Luxury Tax")) {
            player.decreaseMoney(LUXURY_TAX_AMOUNT);
            System.out.println(player.getName() + " landed on Luxury Tax and paid $" + LUXURY_TAX_AMOUNT);
        }
    }
    // Temporary method that should be moved into a game manager class
    private int getUserChoice(Player player, int flatTax, int percentageTax) {
        // Needs the UI logic
        return percentageTax;
    }

    @Override
    public void onPassing(Player player) {
        // Do nothing
    }
}
