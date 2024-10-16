package ships;

public class ShipFactory {
    public static Ship createShip(BattleshipType type, int startX, int startY, boolean horizontal) {
//        switch (type){
//            case BattleshipType.BATTLESHIP: //refer to enums
//                return new Battleship("B", startX, startY, horizontal);
//            case BattleshipType.SUBMARINE:
//                return new Submarine("S", startX, startY, horizontal);
//            case BattleshipType.PATROL_BOAT:
//                return new PatrolBoat("P", startX, startY, horizontal);
//            case BattleshipType.CARRIER:
//                return new Carrier("C", startX, startY, horizontal);
//            default:
//                throw new IllegalArgumentException("Unknown ship type: " + type);
//        }
        return new PatrolBoat("P",startX,startY,horizontal);
    }
}
