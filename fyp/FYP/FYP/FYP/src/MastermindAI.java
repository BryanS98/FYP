import java.util.Scanner;

public class MastermindAI {
    public static void main(String[] Args) {
        C4.ConnectFour C4 = new C4.ConnectFour();
        TTT.TicTacToe TTT = new TTT.TicTacToe();
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! Welcome to Mastermind AI!");
        System.out.println("Powered by Monte Carlo Tree Search!");
        System.out.println("Would you like to play a game? (Y/N)");
        String accept = scan.nextLine().toLowerCase();

        while (accept.equals("y")) {
            System.out.println("Please choose one of the following games:");
            System.out.println("\n\nFor Connect Four, please type 1");
            System.out.println("\nFor TicTacToe, please type 2\n");

            int gameDecision = scan.nextInt();
            if (gameDecision == 1) {
                C4.startGame();
            } else if (gameDecision == 2) {
                TTT.startGame();
            }

            System.out.println("\n Would you like to play again? (Y/N)");
            String response = scan.next().toLowerCase();
            accept = response;
            System.out.println("\n Would you kindly");
        }
    }
}
