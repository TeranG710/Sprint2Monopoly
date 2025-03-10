package Model.Spaces;

import Model.Player;

public class GoToJailSpace extends BoardSpace {
    public GoToJailSpace(int position) {
        super("Go To Jail", position);
    }

    @Override
    public void onLanding(Player player) {
        player.goToJail();
    }

    @Override
    public void onPassing(Player player) {
        // Do nothing
    }

}
