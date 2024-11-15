package ships;

public class PatrolBoat extends Ship {
    /**
     * Constructs a PatrolBoat with its specific properties.
     *
     * @param identifier  A unique identifier for the ship (e.g., "P1").
     * @param startX      The starting x-coordinate of the ship.
     * @param startY      The starting y-coordinate of the ship.
     * @param horizontal  Whether the ship is placed horizontally.
     */
    public PatrolBoat(String identifier, int startX, int startY, boolean horizontal) {
        super(BattleshipType.PATROLBOAT.getName(), identifier, startX, startY,
                BattleshipType.PATROLBOAT.getSize(), horizontal);
    }



    /**
     * Defines the special ability of the PatrolBoat.
     */
    @Override
    public void specialAbility() {
        System.out.println("PatrolBoat " + getIdentifier() + " activates its special ability: Quick Maneuver!");
    }
}
