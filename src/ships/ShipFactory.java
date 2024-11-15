package ships;

public class ShipFactory {
    /**
     * Creates a ship instance based on the provided type and placement details.
     *
     * @param type       The type of the ship (from the BattleshipType enum).
     * @param startX     The starting x-coordinate of the ship.
     * @param startY     The starting y-coordinate of the ship.
     * @param horizontal Whether the ship is placed horizontally.
     * @return A Ship instance corresponding to the given type.
     */
    public static Ship createShip(BattleshipType type, int startX, int startY, boolean horizontal) {
        switch (type) {
            case BATTLESHIP:
                return new Battleship("B", startX, startY, horizontal);
            case SUBMARINE:
                return new Submarine("S", startX, startY, horizontal);
            case PATROLBOAT:
                return new PatrolBoat("P", startX, startY, horizontal);
            case CARRIER:
                return new Carrier("C", startX, startY, horizontal);
            default:
                throw new IllegalArgumentException("Unknown ship type: " + type);
        }
    }
}