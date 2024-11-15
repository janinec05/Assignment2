import ships.Ship;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final int SIZE = 10; // Constant for the board size
    private Ship[][] grid;  // 2D array to represent the grid, with Ship objects
    private List<Ship> ships;  // List to store ships on the board

    // Constructor to initialize the board
    public Board() {
        grid = new Ship[SIZE][SIZE];  // Initialize a 10x10 grid
        ships = new ArrayList<>();  // Initialize the list of ships
    }

    // Method to display the board
    public void displayBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == null) {
                    System.out.print("[ ] ");  // Empty space
                } else {
                    // Display the ship's identifier (e.g., B for Battleship, C for Carrier, etc.)
                    System.out.print("[" + grid[i][j].getIdentifier().charAt(0) + "] ");
                }
            }
            System.out.println();  // Move to the next row
        }
    }

    // Method to place a ship on the board
    public boolean placeShip(Ship ship) {
        int startX = ship.getStartX();
        int startY = ship.getStartY();
        boolean horizontal = ship.isHorizontal();
        int shipSize = ship.getSize();

        // Check if the ship fits on the board and doesn't overlap with other ships
        if (horizontal) {
            if (startY + shipSize > SIZE) {
                System.out.println("Ship does not fit horizontally.");
                return false;
            }
            for (int i = startY; i < startY + shipSize; i++) {
                if (grid[startX][i] != null) {
                    System.out.println("Space already occupied by another ship.");
                    return false;
                }
            }

            // Place the ship on the grid
            for (int i = startY; i < startY + shipSize; i++) {
                grid[startX][i] = ship;
            }
        } else {
            if (startX + shipSize > SIZE) {
                System.out.println("Ship does not fit vertically.");
                return false;
            }
            for (int i = startX; i < startX + shipSize; i++) {
                if (grid[i][startY] != null) {
                    System.out.println("Space already occupied by another ship.");
                    return false;
                }
            }

            // Place the ship on the grid
            for (int i = startX; i < startX + shipSize; i++) {
                grid[i][startY] = ship;
            }
        }

        // Add the ship to the list of ships on the board
        ships.add(ship);

        System.out.println("Ship placed successfully!");
        return true;
    }


    // Method to check if a guess hits a ship
    public boolean receiveGuess(int x, int y) {
        // If the cell contains a ship, mark it as hit
        if (grid[x][y] != null) {
            grid[x][y].hit();
            System.out.println("Hit!");
            return true;
        } else {
            System.out.println("Miss.");
            return false;
        }
    }

    // Method to check if all ships have been sunk
    public boolean allShipsSunk() {
        // Check if all ships have been sunk
        for (Ship ship : ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;  // All ships are sunk
    }
}
