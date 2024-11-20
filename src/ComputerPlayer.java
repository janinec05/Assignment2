import ships.BattleshipType;
import ships.Ship;

import java.util.Random;

public class ComputerPlayer extends Player {
    private Random random;

    public ComputerPlayer(String name, Board board) {
        super(name, board); // Call the parent class constructor
        this.random = new Random();
    }

    //add ifHit, will guess differently method

    @Override
    public void displayRadar(Board opponentBoard) {
        opponentBoard.displayRadar();
    }

    @Override
    public void placeShips() {
        // Automatically place ships on the board (randomly)
        for (BattleshipType shipType : BattleshipType.values()) {
            int x = random.nextInt(Board.SIZE);
            int y = random.nextInt(Board.SIZE);
            boolean horizontal = random.nextBoolean();
            Ship ship = shipType.createShip(x, y, horizontal);
            if (board.placeShip(ship)) {
                System.out.println(shipType.getName() + " placed at (" + x + ", " + y + ")");
            } else {
                System.out.println("Failed to place " + shipType.getName());
            }
        }
    }

    @Override
    public int[] makeGuess() {
        // Generate random guesses for computer
        int x = random.nextInt(Board.SIZE);
        int y = random.nextInt(Board.SIZE);
        System.out.println("Computer guesses: (" + x + ", " + y + ")");
        return new int[]{x, y};    }
}
