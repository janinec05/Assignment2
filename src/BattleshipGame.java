import java.util.Scanner;

public class BattleshipGame {

    public static void main(String[] args) {
        // Test with the absolute path for reading the configuration file
        Board compBoard = ConfigFileReader.parseBoard("data/config.txt");

        // Create the computer player with its board
        ComputerPlayer compPlayer = new ComputerPlayer("Computer", compBoard);

        // Initialize Player 1's board and human player
        Board player1Board = new Board();
        HumanPlayer player1 = new HumanPlayer("Player 1", player1Board);

        // Prompt the player to place ships
        player1.placeShips();

        // Continue the game loop after ships are placed
        boolean gameOver = false;
        while (!gameOver) {
            // Player 1's turn
            System.out.println("Player 1's turn:");
            int[] guess = player1.makeGuess();
            boolean hit = compPlayer.getBoard().receiveGuess(guess[0], guess[1]);
            if (hit) {
                System.out.println("Hit!");
            } else {
                System.out.println("Miss.");
            }

            // Check if the computer has sunk all ships
            if (compPlayer.getBoard().allShipsSunk()) {
                System.out.println("Player 1 wins!");
                gameOver = true;
                break;
            }

            // Computer's turn
            System.out.println("Computer's turn:");
            int[] computerGuess = compPlayer.makeGuess();
            hit = player1Board.receiveGuess(computerGuess[0], computerGuess[1]);
            if (hit) {
                System.out.println("Computer hit your ship!");
            } else {
                System.out.println("Computer missed.");
            }

            // Check if Player 1 has sunk all ships
            if (player1Board.allShipsSunk()) {
                System.out.println("Computer wins!");
                gameOver = true;
            }
        }
    }
}
