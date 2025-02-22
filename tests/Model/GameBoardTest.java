package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
        @Test
        public void testBoardInitialization() {
            GameBoard gameBoard = new GameBoard();

            // Ensure the board has 40 elements
            assertNotNull(gameBoard);
            assertEquals(40, gameBoard.getBoardElements().length, "The board should have exactly 40 spaces.");

            // Check specific board positions
            assertEquals("GO", gameBoard.getBoardElements()[0].getName());

            assertEquals("Boardwalk", gameBoard.getBoardElements()[39].getName());

            assertEquals("Jail / Just Visiting", gameBoard.getBoardElements()[10].getName());

            assertEquals("Chance", gameBoard.getBoardElements()[36].getName());
        }
    }
