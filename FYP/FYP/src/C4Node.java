import java.util.ArrayList;

public class C4Node implements Comparable<C4Node> { // representing the state of the game

    int currentPlayer; // 1 if 1s turn, 2 otherwise;
    C4Node parent; // Parent C4Node of current node
    public int[][] gameBoard; // representing the board
    int move; // 0-6
    ArrayList<C4Node> children; // Child nodes of current node
    double numVisits, UCTValue, victories, draws, losses = 0; // Values for the UCT formula
    int gameState = C4Sim.CONTINUE_GAME; // indicates if C4Node is end game C4Node (game is won, lost or drawn)

    C4Node(int pl, C4Node p, int[][] s, int m) { // Create C4Node object and set parameters
        currentPlayer = pl;
        parent = p;
        gameBoard = s;
        move = m;
        children = new ArrayList<C4Node>();
    }

    @Override
    public int compareTo(C4Node other) { // sort nodes in descending order according to their UCT value

        return Double.compare(other.UCTValue, UCTValue);
    }

    void setUCT() {

        if (numVisits == 0)
            UCTValue = Double.MAX_VALUE; // make sure every child is visited at least once
        else
            UCTValue = ((victories + draws / 2) / numVisits)
                    + Math.sqrt(2) * Math.sqrt(Math.log(parent.numVisits) / numVisits);
    }
    
    void getKids(C4Sim sim, int Player) {
        ArrayList<Integer> paths = sim.getAllPossibleMoves(gameBoard, Player);
        int p = Player;
        for (int move : paths) {
            int boardLength = gameBoard.length;
            int[][] nextGameState = new int[boardLength][];
            for (int i = 0; i < boardLength; i++)
                nextGameState[i] = gameBoard[i].clone();

            nextGameState = ConnectFour.makeMove(nextGameState, p, move);
            C4Node child = new C4Node(p, this, nextGameState, move);
            child.gameState = sim.GameDecided(child.gameBoard, child.currentPlayer); // check if child is end game node
            children.add(child);
        }
    }
}