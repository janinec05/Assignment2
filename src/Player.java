import ships.BattleshipType;
//import Board;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    protected String name;       // The player's name
    protected Board board;       // The player's game board
    protected List<BattleshipType> ships;  // List of ships for this player

    /**
     * Constructor to initialize a Player with a name and a board.
     *
     * @param name  The name of the player.
     * @param board The game board for the player.
     */
    public Player(String name, Board board) {
        this.name = name;
        this.board = board;
        this.ships = new ArrayList<>();  // Initialize the list of ships
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the board of the player.
     *
     * @return The game board for the player.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Abstract method to be implemented by subclasses for placing ships.
     */
    public abstract void placeShips();

    /**
     * Abstract method to be implemented by subclasses for making a guess.
     *
     * @return An array containing the x and y coordinates of the guess.
     */
    public abstract int[] makeGuess();

    public abstract void displayRadar(Board opponentBoard);
}
