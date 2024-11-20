import java.util.Scanner;

public class BattleshipGame {

    public static void main(String[] args) {
        // Test with the absolute path for reading the configuration file
        Board compBoard = ConfigFileReader.parseBoard("data\\config.txt");

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
            // ** Player 1's Turn **
            System.out.println("Player 1's turn:");
            boolean playerTurn = true;

            while (playerTurn) {
                // Display the radar of the computer's known areas
                System.out.println("Your radar of the enemy's board so far: ");
                compPlayer.getBoard().displayRadar();

                // Player makes a guess
                int[] guess = player1.makeGuess();
                boolean hit = compPlayer.getBoard().receiveGuess(guess[0], guess[1]);

                if (hit) {
                    System.out.println("Hit!");
                    if (compPlayer.getBoard().checkIfShipSunk(guess[0], guess[1])) {
                        System.out.println("You sunk an enemy ship!");
                    }
                } else {
                    System.out.println("Miss.");
                    playerTurn = false; // End player's turn after a miss
                }

                if (compPlayer.getBoard().allShipsSunk(compPlayer.getBoard().getComputerShips())) {
                    System.out.println("Player 1 wins!");
                    gameOver = true;
                    break;
                }
            }

            if (gameOver) break;

            // ** Computer's Turn **
            System.out.println("Computer's radar of your board so far: ");
            player1Board.displayRadar();

            System.out.println("Computer's turn:");
            boolean computerTurn = true;

            while (computerTurn) {
                int[] computerGuess = compPlayer.makeGuess();
                boolean hit = player1Board.receiveGuess(computerGuess[0], computerGuess[1]);

                if (hit) {
                    System.out.println("Computer hit your ship!");
                    if (player1Board.checkIfShipSunk(computerGuess[0], computerGuess[1])) {
                        System.out.println("The enemy sunk your ship!");
                    }
                } else {
                    System.out.println("Computer missed.");
                    computerTurn = false; // End computer's turn after a miss
                }

                if (player1Board.allShipsSunk(player1Board.getPlayerShips())) {
                    System.out.println("Computer wins!");
                    gameOver = true;
                    break;
                }
            }
        }
    }
}
