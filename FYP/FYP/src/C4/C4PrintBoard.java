package C4;

public class C4PrintBoard {
	public static void printBoard(int[][] board) {
        int h = board.length;
        int w = board[0].length;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (j == w - 1) {
                    System.out.print(board[i][j]);
                } else {
                    System.out.print(board[i][j] + " | ");
                }

            }
            System.out.println("\n" + "---------------------------");
        }
        System.out.println("\n");
    }
}
