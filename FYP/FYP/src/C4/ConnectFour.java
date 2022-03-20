package C4;
import java.util.Scanner;

public class ConnectFour {

    public static C4MCTS MCTS = new C4MCTS();
    static Scanner scan = new Scanner(System.in);
    static int lastCol, lastRow;
    static int iter = 0;
    private static C4WinCheck _winChecker = new C4WinCheck();

    public static void startGame() {

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
            C4PrintBoard.printBoard(board);
            if (performTurn(board, 1)) {
                game_running = false;
                System.out.println("Player 1 wins!");
                C4PrintBoard.printBoard(board);
                continue; // exit loop
            }
            C4PrintBoard.printBoard(board);
            if (performTurn(board, 2)) {
                game_running = false;
                System.out.println("Player 2 wins!");
                C4PrintBoard.printBoard(board);
                continue; // exit loop
            }

        }

        return;
    }

    public static boolean performTurn(int[][] board, int playerId) {
        if (playerId == 1) {
            System.out.println("Select column 1-7");
            int move = scan.nextInt() - 1;
            while (!C4ValidateMove.validateMove(board, playerId, move, true)) {
                move = scan.nextInt();
            }
            MCTS.rootNode = new C4Node(C4Sim.Player1, MCTS.rootNode, board, move);
            C4MakeMove.makeMove(MCTS.rootNode.gameBoard, playerId, move);
            return checkWin(MCTS.rootNode.gameBoard, playerId);
        } else {
            int numIter = 1000;
            while (true) {
                if (!MCTS.Sim.getAllPossibleMoves(MCTS.rootNode.gameBoard, MCTS.rootNode.currentPlayer).isEmpty()) {
                    int chosenMove = MCTS.findBestPath(numIter);
                    C4MakeMove.makeMove(MCTS.rootNode.gameBoard, playerId, chosenMove);
                    MCTS.rootNode = new C4Node(C4Sim.Player2, MCTS.bestPath, MCTS.bestPath.gameBoard,
                            MCTS.bestPath.move);
                    return checkWin(MCTS.rootNode.gameBoard, playerId);
                }
                return checkWin(MCTS.rootNode.gameBoard, playerId);
            }
        }

    }


    public static boolean checkWin(int[][] board, int player) {
        if (_winChecker.CheckHorizontalWin(board, player)) {
            return true;
        }
        if (_winChecker.CheckVerticalWin(board, player)) {
            return true;
        }
        if (_winChecker.CheckDiagonalWin(board, player)) {
            return true;
        }

        return false;
    }

}