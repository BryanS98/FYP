import java.util.ArrayList;
import java.util.Random;

public class C4AI {
    public C4AI() {
    }
}

class C4Sim {

    static final int DRAW_GAME = -1;
    static final int CONTINUE_GAME = -2;
    static final int GAME_WON = 1;
    static final int Player1 = 1;
    static final int Player2 = 2;

    ArrayList<ArrayList<Integer>> winningMoves; // holds all winning moves for look-up;
    Random rand = new Random();

    C4Sim() {
    }

    int simGameFromNode(C4Node n) { // do roll out

        int boardLength = n.gameBoard.length;
        if (n.winner == 1 || n.winner == 2)
            return n.winner; // check if game is won and C4Node is terminal
        int player = C4MCTS.rootNode.currentPlayer; // whose player's turn it is to make a move
        int[][] currentGameState = new int[boardLength][];
        for (int i = 0; i < boardLength; i++)
            currentGameState[i] = n.gameBoard[i].clone();

        while (true) { // simulate a random game
            player = C4MCTS.switchPlayer(player);
            ArrayList<Integer> moves = getAllPossibleMoves(currentGameState, player);
            if (moves.isEmpty()) {
                return DRAW_GAME;
            }
            int randomMoveIndex = rand.nextInt(moves.size());
            int moveToMake = moves.get(randomMoveIndex);
            ConnectFour.makeMove(currentGameState, player, moveToMake);
            int won = GameDecided(currentGameState, player);
            if (won != CONTINUE_GAME)
                return won;

        }

    }

    int GameDecided(int[][] gameBoard, int player) {
        if (ConnectFour.checkWin(gameBoard, player)) {
            return player;
        }
        return CONTINUE_GAME;
    }

    ArrayList<Integer> getAllPossibleMoves(int[][] gameBoard, int playerId) {

        ArrayList<Integer> allPossibleMoves = new ArrayList<Integer>(6);
        for (int i = 0; i <= 6; i++) {
            if (ConnectFour.validateMove(gameBoard, playerId, i)) {
                allPossibleMoves.add(i);
            }
        }
        return allPossibleMoves;
    }

}
