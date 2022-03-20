package TTT;
import java.util.ArrayList;

public class TTTNode implements Comparable<TTTNode> { // representing the state of the game

    int currentPlayer; // 0 if o's turn has been played, 1 otherwise;
    TTTNode parent; // Parent node of current node
    public int[] gameBoard; // representing the board
    int move; // 0-8
    ArrayList<TTTNode> children; // Child nodes of current node
    double numVisits, UCTValue, victories, draws, losses = 0; // Values for the UCT formula
    int winner = TTTSim.CONTINUE_GAME; // indicates if node is end game node (game is won, lost or drawn)
    public int GameState;

    TTTNode(int pl, TTTNode p, int[] s, int m) { // Create node object and set parameters
        currentPlayer = pl;
        parent = p;
        gameBoard = s;
        move = m;
        children = new ArrayList<TTTNode>();
    }

    @Override
    public int compareTo(TTTNode other) { // sort nodes in descending order according to their UCT value

        return Double.compare(other.UCTValue, UCTValue);
    }

    void setUCT() {

        if (numVisits == 0)
            UCTValue = Double.MAX_VALUE; // make sure every child is visited at least once
        else
            UCTValue = ((victories + draws / 2) / numVisits)
                    + Math.sqrt(2) * Math.sqrt(Math.log(parent.numVisits) / numVisits);
    }
    
    void getKids(TTTSim sim, int Player) {
        ArrayList<Integer> paths = sim.getAllPossibleMoves(gameBoard);
        int p = Player;
        for (int move : paths) {
            int boardLength = gameBoard.length;
            int[] nextGameState = new int[boardLength];
            nextGameState = gameBoard.clone();
            nextGameState[move] = Player; 
            TTTNode child = new TTTNode(p, this, nextGameState, move);
            child.GameState = TTTGameDecided.GameDecided(child.gameBoard, child.currentPlayer); // check if child is end game node
            children.add(child);
        }
    }
}