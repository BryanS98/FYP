import java.util.ArrayList;
import java.util.Collections;
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
        if (n.winner != CONTINUE_GAME)
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
            ConnectFour.printBoard(currentGameState);
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

class C4MCTS {

    C4Sim Sim;
    static C4Node rootNode;
    C4Node bestPath;

    C4MCTS() {

        Sim = new C4Sim();
    }

    C4Node nodeSelector() {
        C4Node current = rootNode;

        while (true) {
            if (current.winner != C4Sim.CONTINUE_GAME) {
                return current;
            }
            if (current.children.isEmpty()) {
                getKids(current);
                return current.children.get(0);
            } else {
                for (C4Node C4Node : current.children) {
                    C4Node.setUCT();
                }

                Collections.sort(current.children);
                current = current.children.get(0);
                int visits = (int) current.numVisits;
                if (visits == 0) {
                    getKids(current);
                }
            }
        }

    }

    void getKids(C4Node parent) {
        ArrayList<Integer> paths = Sim.getAllPossibleMoves(parent.gameBoard, parent.currentPlayer);
        int p = parent.currentPlayer;
        for (int move : paths) {
            int boardLength = parent.gameBoard.length;
            int[][] nextGameState = new int[boardLength][];
            for (int i = 0; i < boardLength; i++)
                nextGameState[i] = parent.gameBoard[i].clone();

            nextGameState = ConnectFour.makeMove(nextGameState, p, move);
            C4Node child = new C4Node(p, parent, nextGameState, move);
            child.winner = Sim.GameDecided(child.gameBoard, child.currentPlayer); // check if child is end game node
            parent.children.add(child);
        }
    }

    void backPropagateRollout(C4Node current, int won) {
        while (current != null) {
            if (won == C4Sim.DRAW_GAME) {
                current.draws++;
            } else if (current.currentPlayer == won) {
                current.victories++;
            } else {
                current.losses++;
            }

            current = current.parent;
        }
    }

    static int switchPlayer(int player) {
        if (player == 1)
            return 2;
        return 1;
    }

    C4Node findBestPath(int Sims) {

        C4Node current = null;
        for (int i = 0; i < Sims; i++) {
            current = nodeSelector();
            int won = Sim.simGameFromNode(current);
            backPropagateRollout(current, won);
        }

        double visits = 0.0;

        for (C4Node child : rootNode.children) {
            if (child.numVisits >= visits) {
                bestPath = child;
                visits = child.numVisits;
            }
        }

        return bestPath;
    }
}