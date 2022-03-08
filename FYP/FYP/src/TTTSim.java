import java.util.ArrayList;
import java.util.Random;

public class TTTSim {

    static final int EMPTY = -1;
    static final int O = 0;
    static final int X = 1;

    static final int DRAW_GAME = 2;
    static final int CONTINUE_GAME = -2;

    private static TTTWinCheck _winChecker = new TTTWinCheck();
    Random rand;

    TTTSim() {

        rand = new Random();
    }

    TTTNode simGameFromNode(TTTNode n) { // do rollout

        TTTNode current = n;
        int player = current.currentPlayer;
        while (true) { // simulate a random game
            if (checkDraw(current.gameBoard)) {
                current.GameState = DRAW_GAME;
                return current;
            }
            ArrayList<Integer> moves = getAllPossibleMoves(current.gameBoard);
            int size = moves.size();
            if (size == 0) {
                System.out.println("Oh No!");
                if (checkDraw(current.gameBoard)) {
                    current.GameState = DRAW_GAME;
                    return current;
                }
            }
            int randomMoveIndex = rand.nextInt(moves.size());
            int moveToMake = moves.get(randomMoveIndex);
            if (current.children.isEmpty()) {
                getKids(current);
            }
            current = current.children.get(randomMoveIndex);
            current.currentPlayer = player;
            int won = GameDecided(current.gameBoard, player);
            if (won != CONTINUE_GAME) {
                current.GameState = won;
                return current;
            }
            if (checkDraw(current.gameBoard)) {
                current.GameState = DRAW_GAME;
                return current;
            }
            player ^= 1;

        }

    }

    void getKids(TTTNode parent) {
        ArrayList<Integer> paths = getAllPossibleMoves(parent.gameBoard);
        for (Integer i : paths) {
            int[] nextGameState = parent.gameBoard.clone();
            nextGameState[i] = parent.currentPlayer;
            TTTNode child = new TTTNode(parent.currentPlayer, parent, nextGameState, i);
            child.winner = GameDecided(child.gameBoard, child.currentPlayer); // check if child is end game node
            child.GameState = -2;
            parent.children.add(child);
        }
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

    public static int GameDecided(int[] gameBoard, int player) {

        if (winCheck(gameBoard, player)) {
            return player;
        }

        if (winCheck(gameBoard, player ^ 1)) {
            return player ^ 1;
        }

        if (checkDraw(gameBoard))
            return DRAW_GAME;

        return CONTINUE_GAME;
    }

    static ArrayList<Integer> getAllPossibleMoves(int[] gameBoard) {
        ArrayList<Integer> allPossibleMoves = new ArrayList<Integer>(9);
        for (int index = 0; index < gameBoard.length; index++) {
            if (validMove(gameBoard, index)) {
                allPossibleMoves.add(index);
            }
        }
        return allPossibleMoves;
    }

    public static void printBoard(int[] gameBoard) { // This method prints the current game board to the console
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i] == O)
                System.out.print(" O ");
            else if (gameBoard[i] == X)
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

    static boolean validMove(int[] board, int move) {
        if (move > 8 || move < 0) {
            return false;
        } else if (board[move] != -1) {
            return false;
        }
        return true;
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