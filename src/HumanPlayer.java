import java.util.Scanner;

public class HumanPlayer extends Player { //basically copies everything from Player.class and makes it accessible to human

    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a new HumanPlayer object with the given name and board.
     *
     * @param name The name of the human player.
     * @param board The game board for the human player.
     */
    public HumanPlayer(String name, Board board) {
        super();
    }

    /**
     * Places the human player's ships on the board.
     */
    @Override
    public void placeShips() {

    }

    /**
     * Makes a guess based on user input.
     *
     * @return An array containing the x and y coordinates of the guess.
     */
    @Override
    public int[] makeGuess() {
        int[] guess= {4,5};
        return guess;
    }
}
