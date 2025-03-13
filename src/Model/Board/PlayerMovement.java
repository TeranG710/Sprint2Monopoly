package Model.Board;

import Model.Board.GameBoard;
import Model.Board.Dice;
import Model.Board.Token;
import Model.Exceptions.PlayerNotFoundException;
import Model.Player;


public class PlayerMovement {
    private final Player player;
    private final GameBoard board;
    private Banker banker;
    /**
     * Constructor for PlayerMovement
     *
     * @param player
     * @param board
     */
    public PlayerMovement(Player player, GameBoard board) {
        this.player = player;
        this.board = board;
    }

    public void movePlayer(int rollResult) throws PlayerNotFoundException {
        if (player.isInJail()) {
            if (!handleJailTurn()) {
                return;
            }
        }
        Token token = player.getToken();
        int currentPosition = token.getPosition();
        int newPosition = (currentPosition + rollResult) % 40;
        token.setPosition(newPosition);
        System.out.println(player.getName() + " moves to space " + newPosition);

        board.getBoardElements()[newPosition].onLanding(player);
    }

    private boolean handleJailTurn() throws PlayerNotFoundException {
        Dice dice = board.getDice();
        dice.roll();
        System.out.println(player.getName() + " rolled a " + dice.getDie1() + " and a " + dice.getDie2());
        if (dice.isDouble()) {
            System.out.println(player.getName() + " rolled doubles and is now out of jail!");
            player.setInJail(false);
            return true;
        }

        player.incrementTurnsInJail();

        if (player.getTurnsInJail() >= 3) {
            if (banker.getBalance(player) < 50) {
                System.out.println(player.getName() + " does not have enough money to pay the $50 fine and is now out of jail!");
                player.setInJail(false);
                return true;
            }
            System.out.println(player.getName() + " has been in jail for 3 turns and is now out of jail!");
            banker.withdraw(player,50);
            player.setInJail(false);
            player.resetTurnsInJail();
            return true;
        }
        System.out.println(player.getName() + " has been in jail for " + player.getTurnsInJail() + " turns");
        return false;
    }
}
