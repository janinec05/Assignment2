package ships;

/**
 * Enumerates the different types of battleships.
 */
public enum BattleshipType {
  CARRIER("Carrier", 5),
  BATTLESHIP("Battleship", 4),
  SUBMARINE("Submarine", 3),
  PATROLBOAT("PatrolBoat", 2);

  private final String name;
  private final int size;

  /**
   * Constructor for BattleshipType.
   *
   * @param name The name of the battleship type.
   * @param size The size (length) of the battleship.
   */
  BattleshipType(String name, int size) {
    this.name = name;
    this.size = size;
  }

  /**
   * Gets the name of the battleship type.
   *
   * @return The name of the battleship type.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the size (length) of the battleship type.
   *
   * @return The size of the battleship type.
   */
  public int getSize() {
    return size;
  }

  /**
   * Converts the string representation of a battleship type to the corresponding BattleshipType.
   *
   * @param type The string representation of the battleship type.
   * @return The corresponding BattleshipType.
   * @throws IllegalArgumentException if the string does not match any type.
   */
  public static BattleshipType fromString(String type) {
    switch (type.toLowerCase()) {
      case "carrier":
        return CARRIER;
      case "battleship":
        return BATTLESHIP;
      case "submarine":
        return SUBMARINE;
      case "patrol boat":
        return PATROLBOAT;
      default:
        throw new IllegalArgumentException("Unknown battleship type: " + type);
    }
  }

  public Ship createShip(int x, int y, boolean horizontal) {
    switch (this) {
      case BATTLESHIP:
        return new Battleship("Battleship", x, y, horizontal);
      case CARRIER:
        return new Carrier("Carrier", x, y, horizontal);
      case SUBMARINE:
        return new Submarine("Submarine", x, y, horizontal);
      case PATROLBOAT:
        return new PatrolBoat("Patrol Boat", x, y, horizontal);
      default:
        throw new IllegalArgumentException("Unknown ship type");
    }
  }
}
