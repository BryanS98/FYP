import java.util.Scanner;

public class ConnectFour {

    public static C4MCTS MCTS = new C4MCTS();
    static Scanner scan = new Scanner(System.in);
    static int lastCol, lastRow;
    static int iter = 0;
    private static C4WinCheck _winChecker = new C4WinCheck();

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
        MCTS.rootNode = new C4Node(1, null, board, -1);
        while (game_running) {
            printBoard(board);
            if (performTurn(board, 1)) {
                game_running = false;
                System.out.println("Player 1 wins!");
                printBoard(board);
                continue; // exit loop
            }
            printBoard(board);
            if (performTurn(board, 2)) {
                game_running = false;
                System.out.println("Player 2 wins!");
                printBoard(board);
                continue; // exit loop
            }

        }

        return;
    }

    public static boolean performTurn(int[][] board, int playerId) {
        if (playerId == 1) {
            System.out.println("Select column 1-7");
            int move = scan.nextInt() - 1;
            while (!validateMove(board, playerId, move)) {
                move = scan.nextInt();
            }
            MCTS.rootNode = new C4Node(C4Sim.Player1, MCTS.rootNode, board, move);
            makeMove(MCTS.rootNode.gameBoard, playerId, move);
            return checkWin(MCTS.rootNode.gameBoard, playerId);
        } else {
            int numIter = 1000;
            while (true) {
                if (!MCTS.Sim.getAllPossibleMoves(MCTS.rootNode.gameBoard, MCTS.rootNode.currentPlayer).isEmpty()) {
                    int chosenMove = MCTS.findBestPath(numIter);
                    makeMove(MCTS.rootNode.gameBoard, playerId, chosenMove);
                    MCTS.rootNode = new C4Node(C4Sim.Player2, MCTS.bestPath, MCTS.bestPath.gameBoard,
                            MCTS.bestPath.move);
                    return checkWin(MCTS.rootNode.gameBoard, playerId);
                }
                return checkWin(MCTS.rootNode.gameBoard, playerId);
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