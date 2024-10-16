import java.util.Random;

public class ComputerPlayer extends Player { //Similar to HumanPlayer.class

    private Random random; // Random number generator for making guesses

    /**
     * Constructs a new ComputerPlayer object with the given name and board.
     * Initializes the random number generator.
     *
     * @param name The name of the computer player.
     * @param board The game board for the computer player.
     */
    public ComputerPlayer(String name, Board board) {
        super();
    }

    /**
     * Places the computer player's ships on the board.
     */
    @Override
    public void placeShips() {

    }

    /**
     * Makes a random guess on the board.
     * If the computer makes a correct guess, it should attempt to guess an adjacent square.
     *
     * @return An array containing the x and y coordinates of the guess.
     */
    @Override
    public int[] makeGuess() {
        int[] guess= {2,3};
        //TODO: change placeholder guess coordinates
        return guess;
    }
}
