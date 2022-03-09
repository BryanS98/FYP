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
                current.getKids(Sim);
                return current.children.get(0);
            } else {
                for (C4Node C4Node : current.children) {
                    C4Node.setUCT();
                }

                Collections.sort(current.children);
                current = current.children.get(0);
                int visits = (int) current.numVisits;
                if (visits == 0) {
                    current.getKids(Sim);
                }
            }
        }

    }


    void backPropagateRollout(C4Node current, int aiPlayer) {
        int finalResult = current.gameState;
        while (true) {
            if (finalResult == C4Sim.DRAW_GAME) {
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

    static int switchPlayer(int player) {
        if (player == 1)
            return 2;
        return 1;
    }
    
    void SetVisits(C4Node current) {
        while (true) {
            if (current.gameState != C4Sim.CONTINUE_GAME) {
                return;
            } else {
                for (C4Node node : current.children) {
                    node.setUCT();
                }
                
                int visits = (int) current.numVisits;
                if (current.children.size()==0) {
                    current.getKids(Sim);
                }
                Collections.sort(current.children);
                current = current.children.get(0);
                
            }
        }
    }

    public int findBestPath(int Sims) {

        C4Node current = null;
        for (int i = 0; i < Sims; i++) {
            if (i == 800) {
                System.out.println("!");
            }
            current = rootNode;
            int won = Sim.simGameFromNode(current);
            backPropagateRollout(current, C4Sim.Player2);
            SetVisits(current);
        }

        double highestUCT = 0.0;

        for (C4Node child : rootNode.children) {
            child.setUCT();
            if (child.UCTValue >= highestUCT) {
                bestPath = child;
                highestUCT = child.UCTValue;
            }
        }
        return bestPath.move;
    }
}