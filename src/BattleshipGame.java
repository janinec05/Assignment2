import java.util.Scanner;

public class BattleshipGame {

    public static void main(String[] args) {
        // Test with the absolute path for reading the configuration file
        Board compBoard = ConfigFileReader.parseBoard("C:\\Users\\janin\\IdeaProjects\\Assignment2CPSC219\\src\\data\\config.txt");

        // Create the computer player with its board
        ComputerPlayer compPlayer = new ComputerPlayer("Computer", compBoard);

        // Initialize Player 1's board and human player
        Board player1Board = new Board();
        HumanPlayer player1 = new HumanPlayer("Player 1", player1Board);

        // Prompt the player to place ships
        player1.placeShips();

        // Display the computer's ships (before the game starts)
        System.out.println("Computer's ships' positions:");
        compPlayer.getBoard().displayComputerShips();

        // Continue the game loop after ships are placed
        boolean gameOver = false;
        while (!gameOver) {
            // Display the current radar of the opponent's board (Player 1's radar)
            System.out.println("Your radar of the enemy's board so far: ");
            compPlayer.getBoard().displayRadar();  // Adjusted to display computer's radar

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

            // Display the radar of the player's board (Computer's radar)
            System.out.println("Computer's radar of your board so far: ");
            player1Board.displayRadar();  // Adjusted to display player1's radar

            // Computer's turn
            System.out.println("Computer's turn:");
            int[] computerGuess = compPlayer.makeGuess();
            hit = player1Board.receiveGuess(computerGuess[0], computerGuess[1]);
            if (hit) {
                System.out.println("Computer hit your ship!");
            } else {
                System.out.println("Computer missed.");
            }

            if (player1Board.allShipsSunk()) {
                System.out.println("Computer wins!");
                gameOver = true;
            }
        }
    }
}
