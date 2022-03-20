package TTT;
public class TTTWinCheck {

    public TTTWinCheck() {
    }

    public boolean CheckVerticalWin(int[] board, int playerId) {
        if (board[0] == playerId && board[3] == playerId && board[6] == playerId) {
            return true;
        }
        if (board[1] == playerId && board[4] == playerId && board[7] == playerId) {
            return true;
        }
        if (board[2] == playerId && board[5] == playerId && board[8] == playerId) {
            return true;
        } else
            return false;
    }

    public boolean CheckHorizontalWin(int[] board, int playerId) {
        if (board[0] == playerId && board[1] == playerId && board[2] == playerId) {
            return true;
        }
        if (board[3] == playerId && board[4] == playerId && board[5] == playerId) {
            return true;
        }
        if (board[6] == playerId && board[7] == playerId && board[8] == playerId) {
            return true;
        } else
            return false;
    }

    public boolean CheckDiagonalWin(int[] board, int playerId) {
        if (board[0] == playerId && board[4] == playerId && board[8] == playerId) {
            return true;
        }
        if (board[2] == playerId && board[4] == playerId && board[6] == playerId) {
            return true;
        } else
            return false;
    }
}