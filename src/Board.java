import ships.Ship;
import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final int SIZE = 10; // Constant for the board size
    private String[][] grid;  // 2D array to represent the grid with String (for ship symbols)
    private List<Ship> ships;  // List to store ships on the board
    private List<Ship> computerShips;  // List for computer ships
    private boolean[][] hits;   // 2D array for hits
    private boolean[][] misses; // 2D array for misses

    // Constructor to initialize the board
    public Board() {
        grid = new String[SIZE][SIZE];  // Initialize a 10x10 grid of Strings
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

//            x++;
//            y--;

            // Place the ship vertically
            for (int i = 0; i < length; i++) {
                grid[x + i][y] = ship.getSymbol();  // Place each part of the ship
            }
        }

        ships.add(ship);
        return true;  // Ship placed successfully
    }

    // Method to place a computer ship
    public boolean placeComputerShip(Ship ship) {
        int length = ship.getSize();
        int x = ship.getStartX();
        int y = ship.getStartY();
        boolean horizontal = ship.isHorizontal();

        // Check if the ship fits within the grid
        if (horizontal) {
            if (y + length > SIZE+1) {
                return false;  // Ship doesn't fit horizontally
            }

            // Check if space is available
            for (int i = 0; i < length; i++) {
                if (grid[y][x + i] != null) {
                    return false;  // Space is already occupied
                }
            }

            // Place the ship horizontally
            for (int i = 0; i < length; i++) {
                grid[y][x + i] = ship.getSymbol();
            }
        } else {
            if (x + length > SIZE+1) {
                return false;  // Ship doesn't fit vertically
            }

            // Check if space is available vertically
            for (int i = 0; i < length; i++) {
                if (grid[y + i][x] != null) {
                    return false;  // Space is already occupied
                }
            }

            x++;
            y--;

            // Place the ship vertically
            for (int i = 0; i < length; i++) {
                grid[x + i][y] = ship.getSymbol();
            }
        }

        computerShips.add(ship);  // Add to the list of computer ships
        return true;  // Ship placed successfully
    }

    // Method to display only the computer's ships
    public void displayComputerShips() {
        System.out.println("Computer's Ship Positions:");
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                // Only print the computer's ship symbols, no hits or misses
                if (grid[y][x] != null && !isHit(x, y) && !isMiss(x, y)) {
                    System.out.print("[" + grid[y][x] + "] ");  // Ship symbol
                } else {
                    System.out.print("[ ] ");  // Empty space or hit/miss
                }
            }
            System.out.println();  // Move to the next row
        }
    }

    // Method to display the board with ship placements
    public void displayBoard() {
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

    // Display the board for the radar (hit/miss grid)
    public void displayRadar() {
        System.out.println("Radar:");
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (isHit(x, y)) {
                    System.out.print("[X] "); // Hit
                } else if (isMiss(x, y)) {
                    System.out.print("[O] "); // Miss
                } else {
                    if (grid[y][x] == null) {
                        System.out.print("[ ] ");  // Empty space
                    } else {
                        System.out.print("[" + grid[y][x] + "] ");
                    }
                }
            }
            System.out.println();
        }
    }

    // Method to check if all ships are sunk
    public boolean allShipsSunk() {
        for (Ship ship : ships) {
            int x = ship.getStartX();
            int y = ship.getStartY();
            int length = ship.getSize();
            boolean horizontal = ship.isHorizontal();

            // Check if every part of the ship has been hit
            for (int i = 0; i < length; i++) {
                if (horizontal) {
                    if (!isHit(x + i, y)) {
                        return false;
                    }
                } else {
                    if (!isHit(x, y + i)) {
                        return false;
                    }
                }
            }
        }
        return true;  // All ships are sunk
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

    // Method to check if a guess hits a ship
    public boolean receiveGuess(int x, int y) {
        // Ensure that the position hasn't been guessed before
        if (isHit(x, y) || isMiss(x, y)) {
            System.out.println("This position has already been guessed!");
            return false;
        }

        // If the cell contains a ship, mark it as hit
        if (grid[x][y] != null) {
            System.out.println("Hit!");
            markHit(x, y);  // Mark the position as a hit
            return true;
        } else {
            System.out.println("Miss.");
            markMiss(x, y);  // Mark the position as a miss
            return false;
        }
    }
}
