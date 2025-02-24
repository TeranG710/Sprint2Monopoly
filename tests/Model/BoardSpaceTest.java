package Model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardSpaceTest {
    private BoardSpace testSpace;
    private Token testToken;

    // Concrete implementation of BoardSpace for testing
    private class TestBoardSpace extends BoardSpace {
        public TestBoardSpace(String name, int position) {
            super(name, position);
        }

        @Override
        public void onLanding(Player player) {
            // Test implementation
        }

        @Override
        public void onPassing(Player player) {
            // Test implementation
        }
    }

    @Before
    public void setUp() {
        testSpace = new TestBoardSpace("Test Space", 5);
        testToken = new Token(); // Assuming Token has a default constructor
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
        tokens.clear(); // Modifying the returned list
        assertEquals(1, testSpace.getTokens().size()); // Original list should be unchanged
    }

    @Test
    public void testMultipleTokens() {
        Token token2 = new Token();
        testSpace.addToken(testToken);
        testSpace.addToken(token2);
        assertEquals(2, testSpace.getTokens().size());
    }
}