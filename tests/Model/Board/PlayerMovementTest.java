package Model.Board;

import static org.junit.jupiter.api.Assertions.*;

import Model.Exceptions.PlayerNotFoundException;
import org.junit.jupiter.api.Test;


public class PlayerMovementTest {

    @Test
    void testMovePlayer() throws PlayerNotFoundException
    {
        Banker banker = new Banker();
        GameBoard newBoard = new GameBoard(banker);
        Player player = new HumanPlayer("TestPlayer",newBoard);
        Token token = new Token("TestToken");
        PlayerMovement playerMovement = new PlayerMovement(player, newBoard, banker, token);
        banker.addPlayer(player);
        player.setTokenToPlayer(token);
        token.setOwner(player);
        token.setPosition(0);
        playerMovement.movePlayer(5);
        assertEquals(5, playerMovement.getPosition());
        playerMovement.movePlayer(5);
        assertEquals(10, playerMovement.getPosition());
        playerMovement.movePlayer(5);
        assertEquals(15, playerMovement.getPosition());
//        playerMovement.movePlayer(35);
//        assertEquals(25, playerMovement.getPosition());
        //System.out.println(banker.getBalance(player)); banker is referencing a different player object
    }

}