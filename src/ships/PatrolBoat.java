package ships;

public class PatrolBoat extends Ship {
    public PatrolBoat(String identifier, int startX, int startY, boolean horizontal) {
//        super("PatrolBoat", identifier, startX, startY, 4, horizontal);
        super();
    }

    @Override
    public void specialAbility() {
        // Implement the special ability for Battleship
        // For example, it could have a reinforced section that takes an extra hit to destroy
//        System.out.println("PatrolBoat " + getIdentifier() + " activates its special ability!");
        System.out.println("specialAbility Patrol Boat");
    }
}
