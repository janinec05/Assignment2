package ships;

public class Battleship extends Ship {
    public Battleship(String identifier, int startX, int startY, boolean horizontal) {
        // Call the constructor of the parent Ship class
        super("Battleship", identifier, startX, startY, 4, horizontal); // Battleship size is 4
    }

    @Override
    public void specialAbility() {
        // Implement the special ability for Battleship (if applicable)
        System.out.println("Battleship " + getIdentifier() + " activates its special ability: Heavy Armor!");
    }
}
