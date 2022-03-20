package C4;

public class C4MakeMove {
	public static int[][] makeMove(int[][] board, int Player, int chosenColumn) {
        int lastCol = chosenColumn;
        int columnHeight = board.length - 1;
        int lastRow = columnHeight;
        for (int i = 1; i <= columnHeight; i++) {
            if (board[i][chosenColumn] != 0) {
                board[i - 1][chosenColumn] = Player;
                lastRow = i - 1;
                return board;
            }
        }
        board[columnHeight][chosenColumn] = Player;
        return board;

    }
}
