package TTT;

public class TTTPrintBoard {
	public static void printBoard(int[] gameBoard) { // This method prints the current game board to the console
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i] == 0)
                System.out.print(" O ");
            else if (gameBoard[i] == 1)
                System.out.print(" X ");
            else
                System.out.print(" - ");

            if (i == 2 | i == 5)
                System.out.println("\n" + "--- --- ---"); // Skip to next line every 3 symbols to create 3x3 grid
            else if (i == 0 | i == 1 | i == 3 | i == 4 | i == 6 | i == 7)
                System.out.print("|"); // Create a vertical line between each symbol
        }
        System.out.println("\n");
    }
}
