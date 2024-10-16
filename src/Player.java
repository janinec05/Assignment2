import java.util.List;

import ships.Ship;

public abstract class Player { //See inheritance
    //Abstract class, cannot be instantiated, so uses HumanPlayer.class
    protected String name;
    protected Board board;
    protected List<Ship> ships;

    public void PstartXlayer(String name, Board board) {
        this.name = name;
        this.board = board;
    }

    public String getName() {

        return name;
    }

    public Board getBoard() {

        return board;
    }

    public abstract void placeShips();

    public abstract int[] makeGuess();
}
