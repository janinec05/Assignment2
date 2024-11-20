package ships;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static ships.BattleshipType.CARRIER;

class ShipFactoryTest {

    @Test
    void testCreateShip() {
        ShipFactory factory = new ShipFactory();
        Ship ship = factory.createShip(CARRIER, 5, 5, true);

        assertNotNull(ship, "The ship should be created successfully.");
        assertEquals("Carrier", ship.getName(), "The ship name should be 'Carrier'.");
        assertEquals(5, ship.getSize(), "The carrier should have a length of 5.");
    }
}
