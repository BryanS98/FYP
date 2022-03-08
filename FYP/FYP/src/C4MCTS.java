import java.util.ArrayList;
import java.util.Collections;

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
            if (current.gameState != C4Sim.CONTINUE_GAME) {
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
            child.gameState = Sim.GameDecided(child.gameBoard, child.currentPlayer); // check if child is end game node
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
            current.numVisits++;
            current = current.parent;
        }
    }

    static int switchPlayer(int player) {
        if (player == 1)
            return 2;
        return 1;
    }

    public C4Node findBestPath(int Sims) {

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
        System.out.println("Best Path Move: " + bestPath.move);
        return bestPath;
    }
}