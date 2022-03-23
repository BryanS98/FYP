package TTT;

public class TTTGameDecided {
	
	private static TTTWinCheck _winChecker = new TTTWinCheck();
	
	public static int GameDecided(int[] gameBoard, int player) {

        if (winCheck(gameBoard, player)) {
            return player;
        }

        if (winCheck(gameBoard, player ^ 1)) {
            return player ^ 1;
        }

        if (checkDraw(gameBoard))
            return TTTSim.DRAW_GAME;

        return TTTSim.CONTINUE_GAME;
    }
	
	public static boolean winCheck(int[] board, int player) {
        if (_winChecker.CheckVerticalWin(board, player)) {
            return true;
        } else if (_winChecker.CheckDiagonalWin(board, player)) {
            return true;
        } else if (_winChecker.CheckHorizontalWin(board, player)) {
            return true;
        }
        return false;
    }
	
	static boolean checkDraw(int[] gameBoard) {
        int empty = 0;
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i] == -1) {
                empty++;
            }
        }
        if (empty == 0) {
            return true;
        }
        return false;
    }
}
