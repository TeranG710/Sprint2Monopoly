package Model;

import Model.Board.*;
import Model.Cards.ChanceCard;
import Model.Cards.CommunityChestCard;
import Model.Exceptions.PlayerNotFoundException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Main class to run the game
 * This class is responsible for creating the game board, players, and running the game loop.
 * Team Member(s) responsible: Giovanny, Jamell
 */
public class Main {
    public static void main(String[] args) throws PlayerNotFoundException {

        //Player and token demo sprint 1
        Player player = new Player("Alice", new GameBoard());
        player.setToken(new Token("Car"));
        System.out.println(player.getName() + " has a " + player.getToken().getType() + " token.");
        System.out.println("");

        //Cards demo sprint 1
        CommunityChestCard communityChestCard = new CommunityChestCard();
        ChanceCard chanceCard = new ChanceCard();
        int chanceCardCounter = 0;
        int communityChestCounter = 0;
        for(int i = 0; i < chanceCard.getCardDeck().size(); i++){
            System.out.println("Community Chest Card: " + communityChestCard.drawCard());
            System.out.println("Chance Card: " + chanceCard.drawCard());
            chanceCardCounter++;
            communityChestCounter++;
        }
        System.out.println("Chance Card Counter: " + chanceCardCounter + '\n' + "Community Chest Card Counter: " + communityChestCounter);
        System.out.println("");

        //shuffle deck sprint 1
        communityChestCard.cardRestore();
        chanceCard.cardRestore();
        communityChestCard.shuffleDeck();
        chanceCard.shuffleDeck();
        int chanceCardCounter2 = 0;
        int communityChestCounter2 = 0;
        for(int i = 0; i < communityChestCard.getCardDeck().size(); i++){
            System.out.println("Community Chest Card: " + communityChestCard.drawCard());
            System.out.println("Chance Card: " + chanceCard.drawCard());
            chanceCardCounter2++;
            communityChestCounter2++;
        }
        System.out.println("Chance Card Counter: " + chanceCardCounter2 + '\n' + "Community Chest Card Counter: " + communityChestCounter2);
        System.out.println("");

        //Dice Roll Double Demo sprint 1
        Dice dice2 = new Dice();
        int doubleCount = 0;
        while (doubleCount < 3) {
            dice2.roll();
            if (dice2.isDouble()) {
                doubleCount++;
                System.out.println("Double rolled! Count: " + doubleCount);
            }
        }
        if (dice2.goToJail()) {
            System.out.println("Player goes to jail after rolling 3 doubles!");
        }

        //Banker demo sprint 2
        Banker banker = new Banker();
        banker.addPlayer(player);
        System.out.println("Player balance: " + banker.getBalance(player));
        banker.deposit(player, 100);
        System.out.println("Player balance after deposit: " + banker.getBalance(player));
        banker.withdraw(player, 50);
        System.out.println("Player balance after withdrawal: " + banker.getBalance(player));
        System.out.println("");

        //Turn manager demo sprint 1
        Player player1 = new Player("Alice", new GameBoard());
        Player player2 = new Player("Bob", new GameBoard());
        Player player3 = new Player("Charlie", new GameBoard());
        Player player4 = new Player("Dave", new GameBoard());
        List<Player> players = Arrays.asList(player1, player2, player3, player4);
        TurnManager turnManager = new TurnManager(players);
        for (int i = 0; i < 8; i++) {
            Player currentPlayer = turnManager.getCurrentPlayer();
            System.out.println("It's " + currentPlayer.getName() + "'s turn!");
            turnManager.nextTurn();
        }
        System.out.println("");

        //Dice demo sprint 1
        Dice dice = new Dice();
        Set<Integer> rolledSums = new HashSet<>();
        while (rolledSums.size() < 12) {
            dice.roll();
            int sum = dice.getSum();

            if (!rolledSums.contains(sum)) {
                rolledSums.add(sum);
                System.out.println("Die 1: " + dice.getDie1() + ", Die 2: " + dice.getDie2() + ", Sum: " + sum);
            }
        }
        System.out.println("");


//        GameBoard board = new GameBoard();
//        Dice dice = new Dice();
//
//        // Create players
//        Player player1 = new Player("Alice", board);
//        Player player2 = new Player("Bob", board);
//        Player player3 = new Player("Charlie", board);
//        Player player4 = new Player("Dave", board);
//
//        // Assign tokens to players
//        player1.setToken(new Token("Car"));
//        player2.setToken(new Token("Dog"));
//        player3.setToken(new Token("Ship"));
//        player4.setToken(new Token("Hat"));
//
//        // Add players to the game
//        List<Player> players = Arrays.asList(player1, player2, player3, player4);
//        TurnManager turnManager = new TurnManager(players);
//
//        // Run a few rounds of turns
//        for (int i = 0; i < 8; i++) { // Simulate 8 turns
//            Player currentPlayer = turnManager.getCurrentPlayer();
//            Token token = currentPlayer.getToken();
//            int consecutiveDoubles = 0; // Track doubles per turn
//
//            System.out.println("\nIt's " + currentPlayer.getName() + "'s turn!");
//
//            boolean rolledDoubles;
//            do {
//                dice.roll();
//                int rollResult = dice.getSum();
//                System.out.println(currentPlayer.getName() + " rolled a " + dice.getDie1() + " and a " + dice.getDie2() + " (Total: " + rollResult + ")");
//
//                rolledDoubles = dice.isDouble();
//
//                if (rolledDoubles) {
//                    consecutiveDoubles++;
//                    System.out.println(currentPlayer.getName() + " rolled doubles! They get another turn.");
//                } else {
//                    consecutiveDoubles = 0; // Reset if no double
//                }
//
//                // If rolled 3 doubles in a row, send to jail
//                if (consecutiveDoubles == 3) {
//                    currentPlayer.setInJail(true);
//                    System.out.println(currentPlayer.getName() + " has rolled 3 doubles in a row and is going to jail!");
//                    break; // End turn immediately
//                }
//
//                // Move player if not in jail
//                token.setPosition((token.getPosition() + rollResult) % 40);
//                System.out.println(currentPlayer.getName() + " moves to space " + token.getPosition());
//
//            } while (rolledDoubles);
//
//            // Advance to the next turn
//            turnManager.nextTurn();
//        }
    }
}
