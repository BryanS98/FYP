package TTT;
import java.util.ArrayList;
import java.util.Collections;

import C4.C4Node;
import C4.C4Sim;

public class TTTMCTS {

    TTTSim Sim = new TTTSim();
    TTTNode rootNode;
    TTTNode bestPath;

    TTTMCTS() {

        Sim = new TTTSim();
    }

    public void FillChildren(TTTNode node) // They're hungry!
    {
        if (node.children.isEmpty()) {
            getKids(node);
        }
    }

    void SetVisits(TTTNode current) {
        while (true) {
            if (current.GameState != TTTSim.CONTINUE_GAME) {
                return;
            } else {

                if (current.children.size() == 0) {
                    current.getKids(Sim, TicTacToe.switchPlayer(current.currentPlayer));
                }
                for (TTTNode node : current.children) {
                    node.setUCT();
                }
                Collections.sort(current.children);
                current = current.children.get(0);

            }
        }
    }

    void getKids(TTTNode parent) {
        ArrayList<Integer> paths = Sim.getAllPossibleMoves(parent.gameBoard);
        for (Integer i : paths) {
            int[] nextGameState = parent.gameBoard.clone();
            nextGameState[i] = parent.currentPlayer;
            TTTNode child = new TTTNode(parent.currentPlayer, parent, nextGameState, i);
            child.winner = TTTGameDecided.GameDecided(child.gameBoard, child.currentPlayer); // check if child is end game node
            parent.children.add(child);
        }
    }

    void backPropagateRollout(TTTNode current, int aiPlayer) {
        int finalResult = current.GameState;
        while (true) {
            if (finalResult == TTTSim.DRAW_GAME) {
                current.draws++;
            } else if (finalResult == aiPlayer) {
                current.victories++;
            } else {
                current.losses++;
            }
            if (current.parent == null) { // This runs all the way to the top of the board scoring nodes, even past
                                          // moves already made, not ideal but doesn't make a difference
                return;
            }
            current.numVisits++;
            current = current.parent;
        }
    }

    int findBestPath(int Sims) {

    	TTTNode current = null;
        
        for (int i = 0; i < Sims; i++) {
            if (i == 800) {
                i = 800;
            }
            current = rootNode;
            current = Sim.simGameFromNode(current);
            backPropagateRollout(current, 0);
            SetVisits(current);
        }

        double highestUCT = 0.0;

        for (TTTNode child : rootNode.children) {
            child.setUCT();
            if (child.UCTValue > highestUCT) {
                bestPath = child;
                highestUCT = child.UCTValue;
            }
        }
        return bestPath.move;
    }
}
