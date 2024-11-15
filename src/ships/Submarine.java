package ships;

public class Submarine extends Ship {
    public Submarine(String identifier, int startX, int startY, boolean horizontal) {
        // Call the constructor of the parent Ship class
        super(BattleshipType.SUBMARINE.getName(), identifier, startX, startY,
                BattleshipType.SUBMARINE.getSize(), horizontal);    }

    @Override
    public void specialAbility() {
        // Implement the special ability for Submarine (if applicable)
        System.out.println("Submarine " + getIdentifier() + " activates its special ability: Stealth Mode!");
    }
}
