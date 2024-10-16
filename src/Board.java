import java.util.List;
import java.util.Map;

import ships.Ship;

/**
 * Represents a game board for placing and tracking ships.
 */
public class Board {
    private Map<Coordinate, Ship> grid;
    private List<Ship> ships;
    private static final int size = 10;

    /**
     * Constructs a new Board object with an empty grid and no ships.
     */
    public Board() {
    }

    /**
     * Places a ship on the board if it can be placed without overlapping with other ships.
     *
     * @param ship The ship to be placed on the board.
     * @return true if the ship was successfully placed, false otherwise.
     */
    public boolean placeShip(Ship ship) {
//        int x = ship.getStartX();
//        int y = ship.getStartY();
//        int length = ship.getSize();
//        boolean horizontal = ship.isHorizontal();
        return true;
    }

    /**
     * Checks if a ship can be placed on the board without overlapping with other ships.
     *
     * @param x The x-coordinate of the ship's starting position.
     * @param y The y-coordinate of the ship's starting position.
     * @param length The length of the ship.
     * @param horizontal Whether the ship is placed horizontally or vertically.
     * @return true if the ship can be placed without overlapping, false otherwise.
     */
    private boolean canPlaceShip(int x, int y, int length, boolean horizontal) {
        return true;
    }

    /**
     * Displays the current state of the board, showing the positions of ships.
     */
    public void displayBoard() {
    }

    /**
     * Receives a guess from the player and checks if it hits a ship.
     * If a ship is hit, it updates the ship's status and prints a message if the ship is sunk.
     *
     * @param x The x-coordinate of the guess.
     * @param y The y-coordinate of the guess.
     * @return true if the guess hits a ship, false otherwise.
     */
    public boolean receiveGuess(int x, int y) {
        return true;
    }

    /**
     * Checks if all ships on the board have been sunk.
     *
     * @return true if all ships have been sunk, false otherwise.
     */
    public boolean allShipsSunk() {
        return false;
    }

    /**
     * Gets the size of the board.
     *
     * @return The size of the board.
     */
    public int getSize() {
        return 0;
    }
}
