/*
 * CSCI 234: Intro to Software Engineering
 * Group: Giovanny, Jamell, Matt, Deborah
 * Purpose: This class handles the movement of a player on the board.
 * It moves the player based on the roll result and handles the player's turn while in jail.
 * Team Member(s) responsible: Deborah
 * */


package Model.Board;
import Model.Spaces.GoSpace;

import Model.Exceptions.PlayerNotFoundException;

public class PlayerMovement {
    private final Player player;
    private final GameBoard board;
    private final Banker banker;

    /**
     * Constructor for PlayerMovement
     *
     * @param player The player whose movement is being handled
     * @param board  The game board
     * Team member(s) responsible: Deborah
     */
    public PlayerMovement(Player player, GameBoard board, Banker banker) {
        this.player = player;
        this.board = board;
        this.banker = banker;
    }


    /**
     * Moves the player based on the roll result.
     * If the player is in jail, handles the jail turn.
     *
     * @param rollResult The result of the dice roll
     * @throws PlayerNotFoundException if the player is not found
     * Team member(s) responsible: Deborah
     */
    public void movePlayer(int rollResult) throws PlayerNotFoundException {
        if (player.isInJail()) {
            if (!handleJailTurn()) {
                return;
            }
        }
        Token token = player.getToken();
        int currentPosition = token.getPosition();
        int newPosition = (currentPosition + rollResult) % 40;

        if (newPosition < currentPosition || newPosition != 0) {
            if (board.getBoardElements()[0] instanceof GoSpace) {
                GoSpace goSpace = (GoSpace) board.getBoardElements()[0];
                goSpace.onPassing(player);

                banker.deposit(player, 200);
            }
        }
        token.setPosition(newPosition);
        System.out.println(player.getName() + " moves to space " + newPosition);

        board.getBoardElements()[newPosition].onLanding(player);
    }


    /**
     * Handles the player's turn while in jail.
     * Rolls the dice and checks if the player rolls doubles to get out of jail.
     * If the player has been in jail for 3 turns, they pay a fine to get out.
     *
     * @return true if the player gets out of jail, false otherwise
     * @throws PlayerNotFoundException if the player is not found
     *   Team member(s) responsible: Deborah
     */
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
