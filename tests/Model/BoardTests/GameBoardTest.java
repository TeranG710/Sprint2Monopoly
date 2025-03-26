package Model.BoardTests;

import Model.Board.Banker;
import Model.Board.GameBoard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    @Test
    public void testBoardInitialization() {
        Banker banker = new Banker();
        GameBoard gameBoard = new GameBoard(banker);
        assertNotNull(gameBoard);
        assertEquals(40, gameBoard.getBoardElements().length, "The board should have exactly 40 spaces.");
        assertEquals("Go", gameBoard.getBoardElements()[0].getName());
        assertEquals("Boardwalk", gameBoard.getBoardElements()[39].getName());
        assertEquals("Jail / Just Visiting", gameBoard.getBoardElements()[10].getName());
        assertEquals("Chance", gameBoard.getBoardElements()[36].getName());
    }

    

}
