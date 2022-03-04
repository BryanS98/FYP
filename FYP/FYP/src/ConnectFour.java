import java.util.Scanner;

public class ConnectFour {

    public static C4MCTS C = new C4MCTS();
    static Scanner scan = new Scanner(System.in);
    static int lastCol, lastRow;

    private static WinChecker _winChecker = new WinChecker();

    public static void main(String args[]) {
        Connect4(createBoard());
    }

    public static int[][] createBoard() {
        int width = 7;
        int height = 6;
        int[][] board = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = 0;
            }
        }
        return board;
    }

    public static void Connect4(int[][] board) {

        boolean game_running = true;
        while (game_running) {
            printBoard(board);
            if (performTurn(board, 1)) {
                game_running = false;
                System.out.println("Player 1 wins!");
                continue; // exit loop
            }
            if (performTurn(board, 2)) {
                game_running = false;
                System.out.println("Player 2 wins!");
                continue; // exit loop
            }

        }

        return;
    }

    public static boolean performTurn(int[][] board, int playerId) {
        if (playerId == 1) {
            System.out.println("Select column 0-6");
            int move = scan.nextInt();
            while (!validateMove(board, playerId, move)) {
                move = scan.nextInt();
            }
            makeMove(board, playerId, move);
            return checkWin(board, playerId);
        } else {
            int numIter = 1;
            int iter = 0;
            while (true) {
                if (iter == 0) {
                    C.rootNode = new C4Node(C4Sim.Player2, null, board, 1);
                } else {
                    C.rootNode = new C4Node(C.bestPath.currentPlayer, C.bestPath, C.bestPath.gameBoard,
                            C.bestPath.move);
                }
                iter++;
                if (!C.Sim.getAllPossibleMoves(C.rootNode.gameBoard, C.rootNode.currentPlayer).isEmpty()) {
                    C.findBestPath(numIter);
                    makeMove(board, playerId, C.rootNode.move);
                    return checkWin(board, playerId);
                } else
                    return checkWin(board, playerId);
            }
        }

    }

    public static boolean validateMove(int[][] board, int playerId, int move) {
        if (move > 6) {
            System.out.println("Invalid Column");
            return false;
        } else if (board[0][move] != 0) {
            System.out.println("Column full");
            return false;
        }

        return true;
    }

    public static int[][] makeMove(int[][] board, int Player, int chosenColumn) {
        lastCol = chosenColumn;
        int columnHeight = board.length - 1;
        lastRow = columnHeight;
        for (int i = 0; i <= columnHeight; i++) {
            if (board[i][chosenColumn] != 0) {
                board[i - 1][chosenColumn] = Player;
                lastRow = i - 1;
                return board;
            }
        }
        board[columnHeight][chosenColumn] = Player;
        return board;

    }

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
        System.out.println("\n\n");
    }

    public static boolean checkWin(int[][] board, int player) {
        if (_winChecker.CheckHorizontalWin(board, player)) {
            return true;
        }
        if (_winChecker.CheckVerticalWin(board, player)) {
            return true;
        }
        if (_winChecker.CheckDiagonalWin(board, player, lastRow, lastCol)) {
            return true;
        }

        return false;
    }

}