package ships;

public class Carrier extends Ship {
    public Carrier(String identifier, int startX, int startY, boolean horizontal) {
        // Call the constructor of the parent Ship class
        super(BattleshipType.CARRIER.getName(), identifier, startX, startY,
                BattleshipType.CARRIER.getSize(), horizontal);    }

    @Override
    public void specialAbility() {
        // Implement the special ability for Carrier (if applicable)
        System.out.println("Carrier " + getIdentifier() + " activates its special ability: Air Support!");
    }
}
