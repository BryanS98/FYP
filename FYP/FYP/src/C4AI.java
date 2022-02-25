import java.util.*;

public class C4AI{
    public C4AI() {}
}

class Node implements Comparable<Node> { // representing the state of the game

    int currentPlayer; // 0 if o's turn has been played, 1 otherwise;
    Node parent; //Parent node of current node
    public int[][] gameBoard; // representing the board
    int move; // 0-6
    ArrayList<Node> children; //Child nodes of current node
    double numVisits, UCTValue, victories, draws, losses = 0; //Values for the UCT formula
    int winner = C4Sim.CONTINUE_GAME; // indicates if node is end game node (game is won, lost or drawn)

    Node(int pl, Node p, int[][] s, int m) { //Create node object and set parameters
        currentPlayer = pl;
        parent = p;
        gameBoard = s;
        move = m;
        children = new ArrayList<Node>();
    }

    @Override
    public int compareTo(Node other) { // sort nodes in descending order according to their UCT value

        return Double.compare(other.UCTValue, UCTValue);
    }

    void setUCT() {

        if (numVisits == 0)
            UCTValue = Double.MAX_VALUE; // make sure every child is visited at least once
        else
            UCTValue = ((victories + draws / 2) / numVisits)
                    + Math.sqrt(2) * Math.sqrt(Math.log(parent.numVisits) / numVisits);
    }
}



class C4Sim {

    static final int DRAW_GAME = 2;
    static final int CONTINUE_GAME = -2;

    ArrayList<ArrayList<Integer>> winningMoves; // holds all winning moves for look-up;
    Random rand;

    C4Sim() {


    }

    private void setWinningMoves() {
        
    }

    int simGameFromNode(Node n) { // do rollout

        if (n.winner != CONTINUE_GAME)
            return n.winner; // check if game is won and node is terminal
        int player = n.currentPlayer ^ 1; // whose player's turn it is to make a move
        int[][] currentGameState = n.gameBoard.clone();

        while (true) { // simulate a random game

            ArrayList<Integer> moves = getAllPossibleMoves(currentGameState);
            int randomMoveIndex = rand.nextInt(moves.size());
            int moveToMake = moves.get(randomMoveIndex);
            currentGameState[moveToMake] = player;
            int won = GameDecided(currentGameState, player);
            if (won != CONTINUE_GAME)
                return won;
            player ^= 1;

        }
    }

    int GameDecided(int[] gameBoard, int player){
        if()
    }

    ArrayList<Integer> getAllPossibleMoves(int[][] gameBoard){
        
        ArrayList<Integer> allPossibleMoves = new ArrayList<Integer>(7);
        for(int i = 0;i<=6;i++){
            if(gameBoard[0][i]==0){
                allPossibleMoves.add(i);
            }
        }
        return allPossibleMoves;
    }

    public void printBoard(int[] gameBoard){ //This method prints the current game board to the console
        
    }

}