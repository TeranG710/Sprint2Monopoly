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
        Token token = player.getToken();
        int currentPosition = token.getPosition();
        int newPosition = (currentPosition + rollResult) % 40;

        if (newPosition < currentPosition || newPosition != 0) {
            if (board.getBoardElements()[0] instanceof GoSpace goSpace) {
                goSpace.onPassing(player);
                System.out.println(player.getName() + " passes Go and collects $200");
            }
        }
        token.setPosition(newPosition);
        System.out.println(player.getName() + " moves to space " + newPosition);
        board.getBoardElements()[newPosition].onLanding(player);
    }

}
