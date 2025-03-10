package Model.Board;

import Model.Player;

public class GoSpace extends BoardSpace {
    private static final int GO_MONEY = 200;
    public GoSpace() {
        super("Go", 0);
    }
    @Override
    public void onLanding(Player player) {
        player.increaseMoney(GO_MONEY);
    }
    @Override
    public void onPassing(Player player) {
        player.increaseMoney(GO_MONEY);
    }
}