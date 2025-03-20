/*
* CSCI 234: Intro to Software Engineering
* Group: Giovanny, Jamell, Matt, Deborah
* Purpose: This class is responsible for creating the game board, players, and running the game loop.
* Team Member(s) responsible: Giovanny, Jamell
* */

package Model;
import Model.Board.*;
import Model.Cards.ChanceCard;
import Model.Cards.CommunityChestCard;
import Model.Exceptions.PlayerNotFoundException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws PlayerNotFoundException {

//        //Player and token demo sprint 1
//        Player humanPlayer = new HumanPlayer("Alice", new GameBoard());
//        humanPlayer.setTokenToPlayer(new Token("Car"));
//        System.out.println(humanPlayer.getName() + " has a " + humanPlayer.getToken().getType() + " token.");
//        System.out.println("");
//
//        //Cards demo sprint 1
//        CommunityChestCard communityChestCard = new CommunityChestCard();
//        ChanceCard chanceCard = new ChanceCard();
//        int chanceCardCounter = 0;
//        int communityChestCounter = 0;
//        for(int i = 0; i < chanceCard.getCardDeck().size(); i++){
//            System.out.println("Community Chest Card: " + communityChestCard.drawCard());
//            System.out.println("Chance Card: " + chanceCard.drawCard());
//            chanceCardCounter++;
//            communityChestCounter++;
//        }
//        System.out.println("Chance Card Counter: " + chanceCardCounter + '\n' + "Community Chest Card Counter: " + communityChestCounter);
//        System.out.println("");
//
//        //shuffle deck sprint 1
//        communityChestCard.cardRestore();
//        chanceCard.cardRestore();
//        communityChestCard.shuffleDeck();
//        chanceCard.shuffleDeck();
//        int chanceCardCounter2 = 0;
//        int communityChestCounter2 = 0;
//        for(int i = 0; i < communityChestCard.getCardDeck().size(); i++){
//            System.out.println("Community Chest Card: " + communityChestCard.drawCard());
//            System.out.println("Chance Card: " + chanceCard.drawCard());
//            chanceCardCounter2++;
//            communityChestCounter2++;
//        }
//        System.out.println("Chance Card Counter: " + chanceCardCounter2 + '\n' + "Community Chest Card Counter: " + communityChestCounter2);
//        System.out.println("");
//
//        //Dice Roll Double Demo sprint 1
//        Dice dice2 = new Dice();
//        int doubleCount = 0;
//        while (doubleCount < 3) {
//            dice2.roll();
//            if (dice2.isDouble()) {
//                doubleCount++;
//                System.out.println("Double rolled! Count: " + doubleCount);
//            }
//        }
//        if (dice2.goToJail()) {
//            System.out.println("Player goes to jail after rolling 3 doubles!");
//        }
//
//        //Banker demo sprint 2
//        Banker banker = new Banker();
//        banker.addPlayer(player);
//        System.out.println("Player balance: " + banker.getBalance(player));
//        banker.deposit(player, 100);
//        System.out.println("Player balance after deposit: " + banker.getBalance(player));
//        banker.withdraw(player, 50);
//        System.out.println("Player balance after withdrawal: " + banker.getBalance(player));
//        System.out.println("");
//
//        //Turn manager demo sprint 1
//        Player player1 = new Player("Alice", new GameBoard());
//        Player player2 = new Player("Bob", new GameBoard());
//        Player player3 = new Player("Charlie", new GameBoard());
//        Player player4 = new Player("Dave", new GameBoard());
//        List<Player> players = Arrays.asList(player1, player2, player3, player4);
//        TurnManager turnManager = new TurnManager(players);
//        for (int i = 0; i < 8; i++) {
//            Player currentPlayer = turnManager.getCurrentPlayer();
//            System.out.println("It's " + currentPlayer.getName() + "'s turn!");
//            turnManager.nextTurn();
//        }
//        System.out.println("");
//
//        //Dice demo sprint 1
//        Dice dice = new Dice();
//        Set<Integer> rolledSums = new HashSet<>();
//        while (rolledSums.size() < 12) {
//            dice.roll();
//            int sum = dice.getSum();
//
//            if (!rolledSums.contains(sum)) {
//                rolledSums.add(sum);
//                System.out.println("Die 1: " + dice.getDie1() + ", Die 2: " + dice.getDie2() + ", Sum: " + sum);
//            }
//        }
//        System.out.println("");
//

    }
}
