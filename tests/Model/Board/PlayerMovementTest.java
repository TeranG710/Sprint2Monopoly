package Model.Board;

import static org.junit.jupiter.api.Assertions.*;

import Model.Exceptions.PlayerAlreadyExistsException;
import Model.Exceptions.PlayerNotFoundException;
import Model.Game;
import org.junit.jupiter.api.Test;


public class PlayerMovementTest {
    @Test
    void testMovePlayer() throws PlayerNotFoundException, PlayerAlreadyExistsException {
        Banker banker = new Banker();
        GameBoard board = new GameBoard(banker);
        Player player = new HumanPlayer("TestPlayer", board);
        Token token = new Token("TestToken");
        player.setTokenToPlayer(String.valueOf(token));
        banker.addPlayer(player);
        token.setPosition(0);


        PlayerMovement playerMovement = new PlayerMovement(player, board, banker);

        playerMovement.movePlayer(5);
        assertEquals(5, token.getPosition());

        playerMovement.movePlayer(5);
        assertEquals(10, token.getPosition());

        playerMovement.movePlayer(30);
        assertEquals(0, token.getPosition());
    }

}