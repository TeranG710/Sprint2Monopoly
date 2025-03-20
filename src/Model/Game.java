/*
 * CSCI 234: Intro to Software Engineering
 * Group: Giovanny, Jamell, Matt, Deborah
 * Purpose: Game class is the main class that controls the game. It has the banker, player, game board, chance card and community chest card objects.
 * It has methods to start the game, add player, declare winner and end the game.
 * Team Member(s) responsible: Jamell, Deborah
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
    private GameBoard board;
    private ChanceCard chanceCard;
    private CommunityChestCard communityChestCard;
    private ArrayList<Player> players;
    private boolean inProgress;


    public Game() {
        this.banker = new Banker();
        this.chanceCard = new ChanceCard();
        this.communityChestCard = new CommunityChestCard();
        this.board = new GameBoard(chanceCard, communityChestCard);
        this.players = new ArrayList<>();
        this.inProgress = false;
    }
    public GameBoard getBoard() {
        return board;
    }
    public ChanceCard getChanceCard() {
        return chanceCard;
    }
    public CommunityChestCard getCommunityChestCard() {
        return communityChestCard;
    }

    /**
     * If the game is in progress already, throws a GameInProgressException.
     * Otherwise, sets the game to in progress.
     * Team member(s) responsible: Giovanny and Deborah
     */
    public void startGame() {
        if (gameInProgress()) {
            throw new GameInProgressException();
        }
        inProgress = true;
    }


    /**
     * This method is used to check if the game is in progress.
     * Team member(s) responsible: Deborah
     */
    public boolean gameInProgress() {
        return inProgress;
    }

    /**
     * This method is used to output the game state.
     * @throws PlayerNotFoundException
     * Team member(s) responsible: Deborah
     */
    public void outputGameState() throws PlayerNotFoundException {
        System.out.println("Game in progress: " + gameInProgress());
        if (players.isEmpty()) {
            throw new PlayerNotFoundException();
        }
        System.out.println("Players: ");
        for (Player p : players) {
            try {
                System.out.println("Player: " + p.getName() + " Balance: $" + banker.getBalance(p));
            } catch (PlayerNotFoundException e) {
                System.out.println("Player: " + p.getName() + " Balance: Not found");
            }
        }
    }

    /**
     * This method is used to add a player to the game.
     * Team member(s) responsible: Jamell and Deborah
     */
    public void addPlayer(Player newPlayer) throws PlayerAlreadyExistsException {
        if (!gameInProgress()) {
            throw new GameNotInProgressException();
        }
        if (players.size() >= 4) {
            throw new MaximumPlayerReachedException();
        }
        if (players.contains(newPlayer)) {
            throw new PlayerAlreadyExistsException();
        }
        players.add(newPlayer);
    }

    /**
     * This method is used to declare the winner of the game.
     * It checks the balance of each player and the player with the highest balance is declared the winner.
     * If there are no players in the game, it throws a PlayerNotFoundException.
     * If the player is not in the game, it throws a PlayerNotFoundException.
     * Team member(s) responsible: Jamell
     */
    public Player winner() throws PlayerNotFoundException {
        if(!gameInProgress()){
            throw new GameNotInProgressException();
        }
        if (players.isEmpty()) {
            throw new PlayerNotFoundException();
        }
        Player winner = players.get(0);
        for (Player p : players) {
            if(banker.getBalance(winner) < banker.getBalance(p)){
                winner = p;
            }
        }
        return winner;
    }

    /**
     * This method is used to end the game.
     * If the game is ended early, it throws a GameEndedEarlyException.
     * @throws PlayerNotFoundException
     * Teamm member(s) responsible: Deborah
     */
    public void endGame() throws PlayerNotFoundException {
        inProgress = false;
        if (players.isEmpty()) {
            throw new GameEndedEarlyException();
        }
        try {
            System.out.println("Game over! The winner is: " + winner().getName());
        } catch (PlayerNotFoundException e) {
            throw new PlayerNotFoundException();
        }
    }

}
