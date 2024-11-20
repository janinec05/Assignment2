import ships.Ship;
import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final int SIZE = 10; // Constant for the board size
    private String[][] grid;  // 2D array to represent the grid with String (for ship symbols)
    private List<Ship> ships;  // List to store ships on the board

    // Constructor to initialize the board
    public Board() {
        grid = new String[SIZE][SIZE];  // Initialize a 10x10 grid of Strings
        ships = new ArrayList<>();  // Initialize the list of ships
    }

    // Method to display the board
    public void displayBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == null) {
                    System.out.print("[ ] ");  // Empty space
                } else {
                    System.out.print("[" + grid[i][j] + "] ");  // Display ship symbol
                }
            }
            System.out.println();  // Move to the next row
        }
    }

    // Method to place a ship on the board
    public boolean placeShip(Ship ship) {
        int length = ship.getSize();  // Get the size of the ship
        int x = ship.getStartX();     // Get the starting row (x coordinate)
        int y = ship.getStartY();     // Get the starting column (y coordinate)
        boolean horizontal = ship.isHorizontal();  // Determine if the ship is placed horizontally

        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }

        // Check if the ship can fit based on its orientation
        if (horizontal) {
            if (y + length > SIZE+1) {  // Check if the ship fits horizontally
                return false;  // Ship doesn't fit horizontally
            }

            // Check if the space is free horizontally
            for (int i = 0; i < length; i++) {
                if (grid[y][x+i] != null) {  // Space is already occupied
                    return false;
                }
            }

            // Place the ship horizontally
            for (int i = 0; i < length; i++) {
                grid[y][x+i] = ship.getSymbol();  // Place each part of the ship
            }
        } else {
            if (x + length > SIZE+1) {  // Check if the ship fits vertically
                return false;  // Ship doesn't fit vertically
            }

            // Check if the space is free vertically
            for (int i = 0; i < length; i++) {
                if (grid[y][x] != null) {  // Space is already occupied
                    return false;
                }
            }

            x++;
            y--;

            // Place the ship vertically
            for (int i = 0; i < length; i++) {
                grid[x+i][y] = ship.getSymbol();  // Place each part of the ship
            }
        }

        return true;  // Ship placed successfully
    }


    // Method to check if a guess hits a ship
    public boolean receiveGuess(int x, int y) {
        // If the cell contains a ship, mark it as hit
        if (grid[x][y] != null) {
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
