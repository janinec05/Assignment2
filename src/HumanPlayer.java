import ships.*;
import java.util.Scanner;
import java.util.ArrayList;

public class HumanPlayer extends Player {
    private Scanner scanner;

    // A list of ship types that need to be placed
    private ArrayList<String> remainingShips;

    public HumanPlayer(String name, Board board) {
        super(name, board);
        this.scanner = new Scanner(System.in);
        this.remainingShips = new ArrayList<>();
        // Initialize the list with all ship types
        remainingShips.add("Battleship");
        remainingShips.add("Carrier");
        remainingShips.add("Submarine");
        remainingShips.add("Patrol Boat");
    }

    @Override
    public void displayRadar(Board opponentBoard) {
        // Display the radar grid (hit/miss info) of the opponent's board
        opponentBoard.displayRadar();
    }

    @Override
    public void placeShips() {
        // Loop through all ship types that need to be placed
        board.displayBoard();
        while (!remainingShips.isEmpty()) {
            // Display the current board state before each ship placement
//            board.displayBoard();

            // Display the remaining ships to place
            System.out.println("Ship Types that still need to be placed: ");
            for (String ship : remainingShips) {
                System.out.println(ship);
            }

            // Prompt the player for the ship type to place
            System.out.print("Enter ship type you would like to place: ");
            String shipType = scanner.nextLine();

            // Check if the entered ship type is valid
            if (!remainingShips.contains(shipType)) {
                System.out.println("Invalid ship type. Please choose from the remaining ships.");
                continue;  // Skip this iteration and prompt again
            }

            // Remove the chosen ship type from the list of remaining ships
            remainingShips.remove(shipType);

            // Input X and Y coordinates for placing the ship
            System.out.print("Enter the X coordinate (0-9) for the " + shipType + ": ");
            int x = scanner.nextInt();
            System.out.print("Enter the Y coordinate (0-9) for the " + shipType + ": ");
            int y = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character after the integer input

            // Input the orientation (H or V)
            System.out.print("Enter H to place ship horizontally or V to place ship vertically: ");
            String orientation = scanner.nextLine().toUpperCase();

            // Create the ship object (based on the chosen ship type)
            Ship newShip;
            switch (shipType) {
                case "Battleship":
                    newShip = new Battleship("Battleship", x, y, orientation.equals("H"));
                    break;
                case "Carrier":
                    newShip = new Carrier("Carrier", x, y, orientation.equals("H"));
                    break;
                case "Submarine":
                    newShip = new Submarine("Submarine", x, y, orientation.equals("H"));
                    break;
                case "Patrol Boat":
                    newShip = new PatrolBoat("Patrol Boat", x, y, orientation.equals("H"));
                    break;
                default:
                    System.out.println("Invalid ship type entered.");
                    continue;  // Skip if ship type is invalid (shouldn't happen if validation is correct)
            }

            // Place the ship on the board
            boolean placed = board.placeShip(newShip);

            if (placed) {
                System.out.println(shipType + " placed successfully!");
                board.displayBoard();
            } else {
                System.out.println("Cannot place " + shipType + " at that location. Try again.");
                remainingShips.add(shipType);  // Re-add ship to remaining list if placement fails
            }
        }
    }

    @Override
    public int[] makeGuess() {
        // Prompt the player for a guess
        System.out.println("Enter your guess coordinates:");

        // Input X and Y coordinates for the guess
        System.out.print("Enter the X coordinate: ");
        int x = scanner.nextInt();

        System.out.print("Enter the Y coordinate: ");
        int y = scanner.nextInt();

        // Return the guess as an array [x, y]
        return new int[]{x, y};
    }
}
