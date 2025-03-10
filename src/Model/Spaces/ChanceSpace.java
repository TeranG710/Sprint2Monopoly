package Model.Spaces;

import Model.Cards.ChanceCard;
import Model.Player;
import Model.Spaces.BoardSpace;

public class ChanceSpace extends BoardSpace {
    private ChanceCard chanceDeck;
    public ChanceSpace(int position, ChanceCard chanceDeck) {
        super("Chance", position);
        this.chanceDeck = chanceDeck;
    }

    @Override
    public void onLanding(Player player) {
        String cardDrawn = chanceDeck.drawCard();
        System.out.println(player.getName() + " drew a chance card: " + cardDrawn);
    }
    @Override
    public void onPassing(Player player) {
        // Do nothing
    }
}