package Model.Spaces;

import Model.Player;

public class JustVisitingAndFreeParking extends BoardSpace {
    public JustVisitingAndFreeParking(String name, int position) {
        super(name, position);
    }

    @Override
    public void onLanding(Player player) {
        // Do nothing
    }

    @Override
    public void onPassing(Player player) {
        // Do nothing
    }
}
