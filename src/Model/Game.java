/*
 * CSCI 234: Intro to Software Engineering
 * Group: Giovanny, Jamell, Matt, Deborah
 * Purpose: Game class is the main class that controls the game. It has the banker, player, game board, chance card and community chest card objects.
 * It has methods to start the game, add player, declare winner and end the game.
 * Team Member(s) responsible: Jamell
 * */


package Model;

import Model.Board.Banker;
import Model.Board.GameBoard;
import Model.Board.Player;
import Model.Cards.ChanceCard;
import Model.Cards.CommunityChestCard;
import Model.Exceptions.*;

import java.util.ArrayList;




public class Game {

    private Banker banker;
    private Player player;
    private GameBoard board;
    private ChanceCard chanceCard;
    private CommunityChestCard communityChestCard;
    private ArrayList<Player> players;
    private boolean inProgress;


    public Game() {
        banker = new Banker();
        board = new GameBoard();
        chanceCard = new ChanceCard();
        communityChestCard = new CommunityChestCard();
        players = new ArrayList<>();
        inProgress = false;

    }


    public void startGame() {
        if (!inProgress) {
            throw new GameInProgressException();
        }


    }


    /**
     * This method is used to check if the game is in progress.
     * Team member(s) responsible: Jamell
     */
    public boolean gameInProgress() {
        return inProgress;
    }

    /**
     * This method is used to add a player to the game.
     * Team member(s) responsible: Jamell
     */
    public void addPlayer() throws PlayerAlreadyExistsException {
        if (!gameInProgress()) {
            throw new GameNotInProgressException();
        }
        if (players.size() > 4) {
            throw new MaximumPlayerReachedException();
        }
        if (players.contains(player)) {
            throw new PlayerAlreadyExistsException();
        }
        players.add(player);
    }

    /**
     * This method is used to declare the winner of the game.
     * It checks the balance of each player and the player with the highest balance is declared the winner.
     * If there are no players in the game, it throws a PlayerNotFoundException.
     * If the player is not in the game, it throws a PlayerNotFoundException.
     * Team member(s) responsible: Jamell
     */
    public Player winner(ArrayList<Player> players) throws PlayerNotFoundException {
        if(!gameInProgress()){
            throw new GameNotInProgressException();
        }
        if (players.isEmpty()) {
            throw new PlayerNotFoundException();
        }
        Player winner = players.get(0);
        for (int i = 0; i < players.size(); i++) {
            if(banker.getBalance(winner) < banker.getBalance(players.get(i))){
                winner = players.get(i);
            }
        }
        return winner;
    }


    public void endGame() {


    }

}
