package C4;
import java.util.ArrayList;
import java.util.Random;

public class C4Sim {

    static final int DRAW_GAME = -1;
    static final int CONTINUE_GAME = -2;
    static final int GAME_WON = 1;
    static final int Player1 = 1;
    static final int Player2 = 2;
    static C4MCTS MCTS = new C4MCTS();

    ArrayList<ArrayList<Integer>> winningMoves; // holds all winning moves for look-up;
    Random rand = new Random();

    C4Sim() {
    }

    C4Node simGameFromNode(C4Node n) { // do rollout

        C4Node current = n;
        int player = current.currentPlayer;
        while (true) { // simulate a random game
        	player = switchPlayer(player);
        	if (checkDraw(current.gameBoard, current.currentPlayer)) {
        		current.gameState = DRAW_GAME;
                return current;
            }
            ArrayList<Integer> moves = getAllPossibleMoves(current.gameBoard, current.currentPlayer);
            int size = moves.size();
            int randomMoveIndex = rand.nextInt(size);
            if (current.children.isEmpty()) {
                current.getKids(this, switchPlayer(current.currentPlayer));
            }
            current = current.children.get(randomMoveIndex);
            current.currentPlayer = player;
            int won = GameDecided(current.gameBoard, player);
            if (won != CONTINUE_GAME) {
            	current.gameState = won;
                return current;
            }
            
        }

    }

    public boolean checkDraw(int[][] board, int player) {
        for (int i = 0; i < board.length; i++) {
            if (board[0][i] == 0)
                return false;
        }
        return true;
    }

    int GameDecided(int[][] gameBoard, int player) {
        if (ConnectFour.checkWin(gameBoard, player)) {
            return player;
        }
        return CONTINUE_GAME;
    }

    static int switchPlayer(int player) {
        if (player == 1)
            return 2;
        return 1;
    }

    ArrayList<Integer> getAllPossibleMoves(int[][] gameBoard, int playerId) {

        ArrayList<Integer> allPossibleMoves = new ArrayList<Integer>(6);
        for (int i = 0; i <= 6; i++) {
            if (C4ValidateMove.validateMove(gameBoard, playerId, i, false)) {
                allPossibleMoves.add(i);
            }
        }
        return allPossibleMoves;
    }

}
