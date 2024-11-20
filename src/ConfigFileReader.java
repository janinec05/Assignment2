import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import ships.BattleshipType;
import ships.Ship;
import ships.ShipFactory;

public class ConfigFileReader {

    /**
     * Parses a configuration file and creates a board with the ships specified in the file.
     *
     * @param filename The name of the configuration file.
     * @return A Board object with the ships placed according to the configuration file.
     */
    public static Board parseBoard(String filename) {
        Board board = new Board();  // Initialize the board
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");  // Split the line into components
                if (parts.length != 4) {
                    System.out.println("Invalid line format: " + line);
                    continue;  // Skip invalid lines
                }

                String shipName = parts[0];  // Ship name
                int startX = Integer.parseInt(parts[1]);  // Starting X coordinate
                int startY = Integer.parseInt(parts[2]);  // Starting Y coordinate
                boolean horizontal = parts[3].equalsIgnoreCase("H");  // Check if horizontal

                // Map the ship name to a BattleshipType enum
                BattleshipType shipType = mapShipNameToType(shipName);

                if (shipType != null) {
                    // Use ShipFactory to create the ship
                    Ship ship = ShipFactory.createShip(shipType, startX, startY, horizontal);

                    if (ship != null && board.placeComputerShip(ship)) {
                        System.out.println(shipName + " placed at (" + startX + ", " + startY + "), " + (horizontal ? "horizontal" : "vertical"));
                    } else {
                        System.out.println("Failed to place " + shipName);
                    }
                } else {
                    System.out.println("Unknown ship type: " + shipName);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return board;  // Return the board with placed ships
    }

    /**
     * Maps a ship name from the configuration file to the corresponding BattleshipType enum.
     *
     * @param shipName The name of the ship.
     * @return The corresponding BattleshipType enum, or null if not recognized.
     */
    private static BattleshipType mapShipNameToType(String shipName) {
        switch (shipName.toLowerCase()) {
            case "submarine":
                return BattleshipType.SUBMARINE;
            case "battleship":
                return BattleshipType.BATTLESHIP;
            case "patrolboat":
                return BattleshipType.PATROLBOAT;
            case "carrier":
                return BattleshipType.CARRIER;
            default:
                return null;  // Unknown ship type
        }
    }
}
