package C4;

public class C4WinCheck {

    public C4WinCheck() {
    }

    public boolean CheckVerticalWin(int[][] board, int playerId) {
        for (int colId = 0; colId < board[0].length /* columns */; colId++) {
            int columnScore = 0;
            for (int rowId = 0; rowId < board.length; rowId++) {
                if (board[rowId][colId] == playerId) {
                    columnScore++;
                } else {
                    columnScore = 0; // Reset column score if there's a break
                }
                if (columnScore == 4) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean CheckHorizontalWin(int[][] board, int playerId) {
        for (int rowId = 0; rowId < board.length; rowId++) {
            int rowScore = 0;
            for (var colId = 0; colId < board[0].length; colId++) {
                if (board[rowId][colId] == playerId) {
                    rowScore++;
                } else {
                    rowScore = 0; // Reset column score if there's a break
                }

                if (rowScore == 4) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean CheckDiagonalWin(int[][] board, int playerId) {
        // AscendingDiagonalCheck
        for (int i = 3; i < board.length; i++) {
            for (int j = 0; j < board[0].length - 3; j++) {
                if (board[i][j] == playerId && board[i - 1][j + 1] == playerId && board[i - 2][j + 2] == playerId
                        && board[i - 3][j + 3] == playerId) {
                    return true;
                }
            }
        }
        // descendingDiagonalCheck
        for (int i = 3; i < board.length; i++) {
            for (int j = 3; j < board[0].length; j++) {
                if (board[i][j] == playerId && board[i - 1][j - 1] == playerId && board[i - 2][j - 2] == playerId
                        && board[i - 3][j - 3] == playerId) {
                    return true;
                }
            }
        }

        return false;

    }

}