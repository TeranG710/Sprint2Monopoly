package Model;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class BoardSpaceTest {
    private BoardSpace testSpace;
    private Token testToken;
    private Board board;

    private class TestBoardSpace extends BoardSpace {
        public TestBoardSpace(String name, int position) {
            super(name, position);
        }

        @Override
        public void onLanding(Player player) {
        }

        @Override
        public void onPassing(Player player) {
        }
    }

    @BeforeAll
    public void setUp() {
        board = new Board();
        testSpace = new TestBoardSpace("Test Space", 5);
        testToken = new Token("Car");
    }

    @Test
    public void testConstructor() {
        assertEquals("Test Space", testSpace.getName());
        assertEquals(5, testSpace.getPosition());
        assertTrue(testSpace.getTokens().isEmpty());
    }

    @Test
    public void testAddToken() {
        testSpace.addToken(testToken);
        assertEquals(1, testSpace.getTokens().size());
        assertTrue(testSpace.getTokens().contains(testToken));
    }

    @Test
    public void testRemoveToken() {
        testSpace.addToken(testToken);
        testSpace.removeToken(testToken);
        assertTrue(testSpace.getTokens().isEmpty());
    }

    @Test
    public void testGetTokensReturnsNewList() {
        testSpace.addToken(testToken);
        List<Token> tokens = testSpace.getTokens();
        tokens.clear();
        assertEquals(1, testSpace.getTokens().size());
    }

    @Test
    public void testMultipleTokens() {
        Token token2 = new Token("Dog");
        testSpace.addToken(testToken);
        testSpace.addToken(token2);
        assertEquals(2, testSpace.getTokens().size());
    }
}