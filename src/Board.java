import ships.Ship;
import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final int SIZE = 10; // Constant for the board size
    private String[][] grid;  // 2D array to represent the grid with String (for ship symbols)
    private String[][] computerShipGrid; // Grid that holds only the computer's ship positions
    private List<Ship> ships;  // List to store ships on the board
    private List<Ship> computerShips;  // List for computer ships
    private boolean[][] hits;   // 2D array for hits
    private boolean[][] misses; // 2D array for misses

    // Constructor to initialize the board
    public Board() {
        grid = new String[SIZE][SIZE];  // Initialize a 10x10 grid of Strings
        computerShipGrid = new String[SIZE][SIZE]; // Initialize the computer's ship grid
        ships = new ArrayList<>();  // Initialize the list of ships
        computerShips = new ArrayList<>();
        hits = new boolean[SIZE][SIZE];
        misses = new boolean[SIZE][SIZE];
    }

    public boolean placeShip(Ship ship) {
        int length = ship.getSize();  // Get the size of the ship
        int x = ship.getStartX();     // Get the starting row (x coordinate)
        int y = ship.getStartY();     // Get the starting column (y coordinate)
        boolean horizontal = ship.isHorizontal();  // Determine if the ship is placed horizontally

        // Check if the ship can fit based on its orientation
        if (horizontal) {
            if (y + length > SIZE + 1) {  // Check if the ship fits horizontally
                return false;  // Ship doesn't fit horizontally
            }

            // Check if the space is free horizontally
            for (int i = 0; i < length; i++) {
                if (grid[y][x + i] != null) {  // Space is already occupied
                    return false;
                }
            }

            // Place the ship horizontally
            for (int i = 0; i < length; i++) {
                grid[y][x + i] = ship.getSymbol();  // Place each part of the ship
            }
        } else {
            if (x + length > SIZE + 1) {  // Check if the ship fits vertically
                return false;  // Ship doesn't fit vertically
            }

            // Check if the space is free vertically
            for (int i = 0; i < length; i++) {
                if (grid[y][x] != null) {  // Space is already occupied
                    return false;
                }
            }

            // Place the ship vertically
            for (int i = 0; i < length; i++) {
                grid[x + i][y] = ship.getSymbol();  // Place each part of the ship
            }
        }

        ships.add(ship);
        return true;  // Ship placed successfully
    }

    public boolean placeComputerShip(Ship ship) {
        int length = ship.getSize();  // Get the size of the ship
        int x = ship.getStartX();     // Get the starting row (x coordinate)
        int y = ship.getStartY();     // Get the starting column (y coordinate)
        boolean horizontal = ship.isHorizontal();  // Determine if the ship is placed horizontally

        // Check if the ship can fit based on its orientation
        if (horizontal) {
            if (y + length > SIZE + 1) {  // Check if the ship fits horizontally
                return false;  // Ship doesn't fit horizontally
            }

            // Check if the space is free horizontally
            for (int i = 0; i < length; i++) {
                if (grid[y][x + i] != null) {  // Space is already occupied
                    return false;
                }
            }

            // Place the ship horizontally
            for (int i = 0; i < length; i++) {
                grid[y][x + i] = ship.getSymbol();  // Place each part of the ship
                computerShipGrid[y][x + i] = ship.getSymbol();  // Also mark it in the computer ship grid
            }
        } else {
            if (x + length > SIZE + 1) {  // Check if the ship fits vertically
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
                grid[x + i][y] = ship.getSymbol();  // Place each part of the ship
                computerShipGrid[x + i][y] = ship.getSymbol();  // Also mark it in the computer ship grid
            }
        }

        computerShips.add(ship);  // Add to the list of computer ships
        return true;  // Ship placed successfully
    }

    // Method to receive a guess from the player
    public boolean receiveGuess(int x, int y) {
        // Ensure the coordinates are within bounds of the grid
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            System.out.println("Guess out of bounds!");
            return false;  // Invalid guess
        }

        // Check if the cell contains a ship symbol on the computer's ship grid (indicating a hit)
        if (computerShipGrid[y][x] != null &&
                (computerShipGrid[y][x].equals("C") ||
                        computerShipGrid[y][x].equals("S") ||
                        computerShipGrid[y][x].equals("P") ||
                        computerShipGrid[y][x].equals("B"))) {
//            System.out.println("Hit! Ship symbol: " + computerShipGrid[x][y]);
            markHit(x, y);  // Mark the position as a hit
            return true;
        } else {
//            System.out.println("Miss.");
            markMiss(x, y);  // Mark the position as a miss
            return false;
        }
    }

    // Mark a hit on the board
    public void markHit(int x, int y) {
        hits[x][y] = true;
    }

    // Mark a miss on the board
    public void markMiss(int x, int y) {
        misses[x][y] = true;
    }

    public boolean isHit(int x, int y) {
        return hits[x][y];
    }

    public boolean isMiss(int x, int y) {
        return misses[x][y];
    }

    // Display the board for the radar (hit/miss grid)
    public void displayRadar() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (isHit(x, y)) {
                    System.out.print("[X] "); // Hit
                } else if (isMiss(x, y)) {
                    System.out.print("[O] "); // Miss
                } else {
                    System.out.print("[ ] ");  // Empty space (no ship visible)
                }
            }
            System.out.println();
        }
    }

    // Method to display only the computer's ships (without hits/misses)
    public void displayComputerShips() {
        System.out.println("Computer's Ship Positions:");
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                // Only print the computer's ship symbols if not hit or missed
                if (grid[y][x] != null && !isHit(x, y) && !isMiss(x, y)) {
                    System.out.print("[" + grid[y][x] + "] ");  // Ship symbol
                } else {
                    System.out.print("[ ] ");  // Empty space or hit/miss
                }
            }
            System.out.println();  // Move to the next row
        }
    }
    // Method to check if all ships are sunk
    public boolean allShipsSunk() {
        return false;
//        for (Ship ship : ships) {
//            int x = ship.getStartX();
//            int y = ship.getStartY();
//            int length = ship.getSize();
//            boolean horizontal = ship.isHorizontal();
//
//            // Check if every part of the ship has been hit
//            for (int i = 0; i < length; i++) {
//                if (horizontal) {
//                    if (!isHit(x + i, y)) {
//                        return false;
//                    }
//                } else {
//                    if (!isHit(x, y + i)) {
//                        return false;
//                    }
//                }
//            }
//        }
//        return true;  // All ships are sunk
    }

    public void displayBoard() {
        System.out.println("Current Board:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == null) {
                    System.out.print("[ ] ");  // Empty space
                } else {
                    System.out.print("[" + grid[i][j] + "] ");  // Ship symbol
                }
            }
            System.out.println();  // Move to the next row
        }
    }
}
