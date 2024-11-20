import org.junit.jupiter.api.Test;
import ships.BattleshipType;
import ships.Ship;
import ships.ShipFactory;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    public void testPlaceCarrier() {
        Board board = new Board();
        Ship carrier = ShipFactory.createShip(BattleshipType.CARRIER, 2, 2, true); // Horizontal placement

        boolean result = board.placeShip(carrier);
        assertTrue(result, "Carrier should be placed successfully on the board");
        board.displayBoard();
    }

    @Test
    public void testPlaceBattleship() {
        Board board = new Board();
        Ship battleship = ShipFactory.createShip(BattleshipType.BATTLESHIP, 5, 5, false); // Vertical placement

        boolean result = board.placeShip(battleship);
        assertTrue(result, "Battleship should be placed successfully on the board");
        board.displayBoard();
    }

    @Test
    public void testOverlappingShips() {
        Board board = new Board();
        Ship carrier = ShipFactory.createShip(BattleshipType.CARRIER, 2, 2, true);
        Ship patrolBoat = ShipFactory.createShip(BattleshipType.PATROLBOAT, 2, 3, false);

        boolean carrierResult = board.placeShip(carrier);
        boolean patrolBoatResult = board.placeShip(patrolBoat); // Should fail due to overlap

        assertTrue(carrierResult, "Carrier should be placed successfully");
        assertFalse(patrolBoatResult, "PatrolBoat should fail to place due to overlap");
        board.displayBoard();
    }

    @Test
    public void testOutOfBoundsPlacement() {
        Board board = new Board();
        Ship submarine = ShipFactory.createShip(BattleshipType.SUBMARINE, 11, 11, true); // Horizontal out-of-bounds

        boolean result = board.placeShip(submarine);
        assertFalse(result, "Submarine should not be placed as it exceeds board boundaries");
        board.displayBoard();
    }

    @Test
    public void testAllShipsSunk() {
        Board board = new Board();
        Ship patrolBoat = ShipFactory.createShip(BattleshipType.PATROLBOAT, 0, 0, true);

        board.placeShip(patrolBoat);

        // Simulate hitting all cells of the PatrolBoat
        board.receiveGuess(0, 0); // Hit
        board.receiveGuess(0, 1); // Hit

        assertTrue(board.allShipsSunk(), "All ships should be sunk after hitting all cells");
    }

}
