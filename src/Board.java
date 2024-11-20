import ships.Ship;
import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final int SIZE = 10; // Constant for the board size
    private String[][] grid;  // 2D array to represent the grid with String (for ship symbols)
    private List<Ship> ships;  // List to store ships on the board
    private boolean[][] hits;   // 2D array for hits
    private boolean[][] misses; // 2D array for misses

    // Constructor to initialize the board
    public Board() {
        grid = new String[SIZE][SIZE];  // Initialize a 10x10 grid of Strings
        ships = new ArrayList<>();  // Initialize the list of ships
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

            x++;
            y--;

            // Place the ship vertically
            for (int i = 0; i < length; i++) {
                grid[x + i][y] = ship.getSymbol();  // Place each part of the ship
            }
        }

        addToShipsArrayList(ship);
        return true;  // Ship placed successfully
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


    // Method to check if a guess hits a ship
    public boolean receiveGuess(int x, int y) {
        // If the cell contains a ship, mark it as hit
        if (grid[x][y] != null) {
            System.out.println("Hit!");
            markHit(x, y);
            return true;
        } else {
            System.out.println("Miss.");
            markMiss(x, y);
            return false;
        }
    }

    public void addToShipsArrayList(Ship ship) {
        System.out.println("Adding ship: " + ship.getSymbol());
        ships.add(ship);
    }

    // Method to check if all ships have been sunk
    public boolean allShipsSunk() {
        System.out.println("Number of ships: " + ships.size());
        for (Ship s : ships) {
            System.out.println("Ship symbol: " + s.getSymbol());
        }

        for (Ship ship : ships) {
            int x = ship.getStartX();  // Get the starting x coordinate
            int y = ship.getStartY();  // Get the starting y coordinate
            int length = ship.getSize();  // Get the size of the ship
            boolean horizontal = ship.isHorizontal();  // Check if the ship is placed horizontally

            // Check if every part of the ship has been hit
            for (int i = 0; i < length; i++) {
                // If the ship is placed horizontally, check each x-coordinate
                if (horizontal) {
                    if (!isHit(x + i, y)) {  // If any part is not hit, return false
                        return false;
                    }
                } else {
                    // If the ship is placed vertically, check each y-coordinate
                    if (!isHit(x, y + i)) {  // If any part is not hit, return false
                        return false;
                    }
                }
            }
        }
        // If all ships are sunk, return true
        return true;
    }
}
