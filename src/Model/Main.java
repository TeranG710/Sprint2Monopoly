package Model;

import Model.Board.Dice;
import Model.Board.GameBoard;
import Model.Board.Token;
import Model.Board.TurnManager;

import java.util.Arrays;
import java.util.List;


/**
 * Main class to run the game
 * This class is responsible for creating the game board, players, and running the game loop.
 * Team Member(s) responsible: Giovanny
 */
public class Main {
    public static void main(String[] args) {

        GameBoard board = new GameBoard(); // Assuming you have a Board class
        Dice dice = new Dice();

        // Create players
        Player player1 = new Player("Alice", board);
        Player player2 = new Player("Bob", board);
        Player player3 = new Player("Charlie", board);
        Player player4 = new Player("Dave", board);

        // Assign tokens to players
        player1.setToken(new Token("Car"));
        player2.setToken(new Token("Dog"));
        player3.setToken(new Token("Ship"));
        player4.setToken(new Token("Hat"));

        // Add players to the game
        List<Player> players = Arrays.asList(player1, player2, player3, player4);
        TurnManager turnManager = new TurnManager(players);

        // Run a few rounds of turns
        for (int i = 0; i < 8; i++) { // Simulate 8 turns
            Player currentPlayer = turnManager.getCurrentPlayer();
            Token token = currentPlayer.getToken();

            System.out.println("\nIt's " + currentPlayer.getName() + "'s turn!");

            // Handle roll and double rolls
            boolean rolledDoubles = false;
            int totalRoll = 0;
            do {
                dice.roll();
                int rollResult = dice.getSum();
                System.out.println(currentPlayer.getName() + " rolled a " + dice.getDie1() + " and a " + dice.getDie2() + " (Total: " + rollResult + ")");

                totalRoll += rollResult;
                token.setPosition((token.getPosition() + rollResult) % 40); // Move the player, assuming a 40-space board

                System.out.println(currentPlayer.getName() + " moves to space " + token.getPosition());

                rolledDoubles = dice.isDouble();
                if (rolledDoubles) {
                    System.out.println(currentPlayer.getName() + " rolled doubles! They get another turn.");
                }

            } while (rolledDoubles && !dice.goToJail()); // Continue if doubles were rolled and player has not rolled 3 doubles

            if (dice.goToJail()) {
                currentPlayer.setInJail(true);
                System.out.println(currentPlayer.getName() + " has rolled 3 doubles in a row and is going to jail!");
            }

            // Advance to the next turn
            turnManager.nextTurn();
        }
    }
}
