package ships;

public abstract class Ship {
    private String name;
    private String identifier; // Unique identifier for the ship
    private int startX;
    private int startY;
    private int size;
    private boolean horizontal;
    private int hitCount;

    /**
     * Constructor for Ship class
     *
     * @param name       The name of the ship (e.g., "Battleship", "Carrier").
     * @param startX     The starting x-coordinate of the ship.
     * @param startY     The starting y-coordinate of the ship.
     * @param size       The length of the ship.
     * @param horizontal Whether the ship is placed horizontally (true) or vertically (false).
     */
    public Ship(String name, String identifier, int startX, int startY, int size, boolean horizontal) {
        this.name = name;
        this.identifier = identifier;
        this.startX = startX;
        this.startY = startY;
        this.size = size;
        this.horizontal = horizontal;
    }

    public String getStartCoordinate() {
        char col = (char) ('A' + startY);  // Converts the Y value to a letter (A, B, C, ...)
        return col + String.valueOf(startX); // Returns a string like "A0", "B1", etc.
    }

    // Getters for Ship properties


    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getSize() {
        return size;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    // Methods to manage ship's state
    /**
     * Records a hit on the ship.
     */
    public void hit() {
        hitCount++;
    }

    /**
     * Checks if the ship is completely sunk.
     *
     * @return true if the ship is sunk, false otherwise.
     */
    public boolean isSunk() {
        return hitCount >= size;
    }

    /**
     * Returns the symbol for this ship to be displayed on the board.
     *
     * @return The symbol representing the ship.
     */
    public String getSymbol() {
        switch (name.toLowerCase()) {
            case "carrier":
                return "C";
            case "battleship":
                return "B";
            case "submarine":
                return "S";
            case "patrolboat":
                return "P";
            default:
                throw new IllegalArgumentException("Unknown ship type: " + name);
        }
    }

    /**
     * Abstract method for special ability (to be implemented by subclasses).
     */
    public abstract void specialAbility();

    @Override
    public String toString() {
        return String.format("%s (Identifier: %s, Size: %d, Position: [%d, %d], Horizontal: %b)",
                name, identifier, size, startX, startY, horizontal);
    }
}
