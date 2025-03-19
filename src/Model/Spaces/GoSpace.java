package Model.Spaces;

import Model.Board.Banker;
import Model.Exceptions.PlayerNotFoundException;
import Model.Board.Player;

public class GoSpace extends BoardSpace {

    private static final int GO_MONEY = 200;
    Banker banker;

    /**
     * Constructor for GoSpace
     * Team member(s) responsible: Deborah
     */
    public GoSpace() {
        super("Go", 0);
    }

    /**
     * Set the banker for this space
     * @param banker The banker
     * Team member(s) responsible: Matt
     */
    public void setBanker(Banker banker) {
        this.banker = banker;
    }

    /**
     * Give the player $200 when they land on the Go space
     *
     * @param player The player who landed on the space
     *               Team member(s) responsible: Deborah, Updated latest by Matt
     */
    @Override
    public void onLanding(Player player) throws PlayerNotFoundException {
        if (banker != null) {
            banker.payGoMoney(player);
        } else {
            // Fallback if banker not set
            player.increaseMoney(GO_MONEY);
        }
    }


    /**
     * Give the player $200 when they pass over the Go space
     *
     * @param player The player who passed over the space
     *               Team member(s) responsible: Deborah, Updated latest by Matt
     */
    @Override
    public void onPassing(Player player) throws PlayerNotFoundException {
        if (banker != null) {
            banker.payGoMoney(player);
        } else {
            // Fallback if banker not set
            player.increaseMoney(GO_MONEY);
        }
    }}