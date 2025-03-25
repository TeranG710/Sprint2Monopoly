package Model.Board;

import static org.junit.jupiter.api.Assertions.*;

import Model.Exceptions.PlayerNotFoundException;
import Model.Game;
import org.junit.jupiter.api.Test;


public class PlayerMovementTest {
    @Test
    void testMovePlayer() throws PlayerNotFoundException {
        GameBoard board = new GameBoard();
        Player player = new HumanPlayer("TestPlayer", board);
        Token token = player.getToken();
        token.setPosition(0);
        PlayerMovement.movePlayer(5);
        assertEquals(5, token.getPosition());
        PlayerMovement.movePlayer(5);
        assertEquals(10, token.getPosition());
        PlayerMovement.movePlayer(30);
        assertEquals(0, token.getPosition());
    }
    @Test
    void testHandleJailTurn() throws PlayerNotFoundException {
        GameBoard board = new GameBoard();
        Player player = new HumanPlayer("TestPlayer", board);
        Token token = player.getToken();
        token.setPosition(10);
        player.setInJail(true);
        PlayerMovement.movePlayer(5);
        assertEquals(10, token.getPosition());
        PlayerMovement.handleJailTurn();
        assertEquals(10, token.getPosition());
        PlayerMovement.handleJailTurn();
        assertEquals(10, token.getPosition());
        PlayerMovement.handleJailTurn();
        assertEquals(10, token.getPosition());
        player.setInJail(false);
        PlayerMovement.handleJailTurn();
        assertEquals(10, token.getPosition());
    }


}