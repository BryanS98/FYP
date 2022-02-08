
import java.util.*;

public class TicTacToe {
    public static void main(String[] args) {

        MCTS f = new MCTS();
        int numIter = 1000;

        int[] initGameBoard = new int[9];
        Arrays.fill(initGameBoard, TTTSim.EMPTY);

        int iter = 0;
        while (true) { // game loop

            if (iter == 0) {
                f.rootNode = new Node(TTTSim.O, null, initGameBoard, -1);
            } else {
                f.rootNode = new Node(f.bestPath.currentPlayer, null, f.bestPath.gameBoard, f.bestPath.move);
            }
            if (!f.Sim.getAllPossibleMoves(f.rootNode.gameBoard).isEmpty())
                f.findBestPath(numIter);
            else
                break;
            iter++;
        }
    }
}

class Node implements Comparable<Node> { // representing the state of the game

    int currentPlayer; // 0 if o's turn has been played, 1 otherwise;
    Node parent; //Parent node of current node
    public int[] gameBoard; // representing the board
    int move; // 0-8
    ArrayList<Node> children; //Child nodes of current node
    double numVisits, UCTValue, victories, draws, losses = 0; //Values for the UCT formula
    int winner = TTTSim.CONTINUE_GAME; // indicates if node is end game node (game is won, lost or drawn)

    Node(int pl, Node p, int[] s, int m) { //Create node object and set parameters
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

class TTTSim {

    static final int EMPTY = -1;
    static final int O = 0;
    static final int X = 1;

    static final int DRAW_GAME = 2;
    static final int CONTINUE_GAME = -2;

    ArrayList<ArrayList<Integer>> winningMoves; // holds all winning moves for look-up;
    Random rand;

    TTTSim() {

        rand = new Random();
        rand.setSeed(1);
        setWinningMoves();
    }

    private void setWinningMoves() {

        winningMoves = new ArrayList<ArrayList<Integer>>(Arrays.asList(
                new ArrayList<Integer>(Arrays.asList(0, 1, 2)),
                new ArrayList<Integer>(Arrays.asList(3, 4, 5)),
                new ArrayList<Integer>(Arrays.asList(6, 7, 8)),
                new ArrayList<Integer>(Arrays.asList(0, 3, 6)),
                new ArrayList<Integer>(Arrays.asList(1, 4, 7)),
                new ArrayList<Integer>(Arrays.asList(2, 5, 8)),
                new ArrayList<Integer>(Arrays.asList(0, 4, 8)),
                new ArrayList<Integer>(Arrays.asList(2, 4, 6))));
    }

    int simGameFromNode(Node n) { // do rollout

        if (n.winner != CONTINUE_GAME)
            return n.winner; // check if game is won and node is terminal
        int player = n.currentPlayer ^ 1; // whose player's turn it is to make a move
        int[] currentGameState = n.gameBoard.clone();

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
        int n = 0;
        loopReset: for(int i = 0; i < winningMoves.size(); i++){
            for(int j =0; j < i; j++){
                if (gameBoard [i] != player) {
                    continue loopReset;
                } else n++;
            }
            if(n == 3) return player;
        }
        ArrayList<Integer> moves = getAllPossibleMoves(gameBoard);
            if (moves.isEmpty()) return DRAW_GAME;
            
            return CONTINUE_GAME;
    }

    ArrayList<Integer> getAllPossibleMoves(int[] gameBoard){
        ArrayList<Integer> allPossibleMoves = new ArrayList<Integer>(9);
        for(int index = 0; index < gameBoard.length;index++){
            if(gameBoard[index] == EMPTY){
                allPossibleMoves.add(index);
            }
        }
        return allPossibleMoves;
    }

    public void printBoard(int[] gameBoard){ //This method prints the current game board to the console
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[i] == O)
                System.out.print(" O ");
            else if (gameBoard[i] == X)
                System.out.print(" X ");
            else
                System.out.print(" - ");

            if (i == 2 | i == 5)
                System.out.println("\n" + "––– ––– –––"); // Skip to next line every 3 symbols to create 3x3 grid
            else if (i == 0 | i == 1 | i == 3| i == 4 | i == 6| i == 7)
                System.out.print("|"); // Create a verical line between each symbol
        }
    }

}

class MCTS {

    TTTSim Sim;
    Node rootNode;
    Node bestPath;
    
    MCTS() {
    
        Sim = new TTTSim();
    }

    Node nodeSelector() {
        Node current = rootNode;

        while (true) {
            if(current.winner != TTTSim.CONTINUE_GAME) {
                return current;
            }
            if(current.children.isEmpty()) {
                getKids(current);
                return current.children.get(0);
            }
            else {
                for(Node node : current.children){
                    node.setUCT();
                }

                Collections.sort(current.children);
                current = current.children.get(0);
                int visits = (int)current.numVisits;
                if(visits == 0){
                    getKids(current);
                }
            }
        }
        
    }

    void getKids(Node parent){
        ArrayList<Integer> paths = Sim.getAllPossibleMoves(parent.gameBoard);
        for (Integer i : paths) {
            int[] nextGameState = parent.gameBoard.clone();
            nextGameState[i] = parent.currentPlayer ^ 1;
            Node child = new Node(parent.currentPlayer ^ 1, parent, nextGameState, i);
            child.winner = Sim.GameDecided(child.gameBoard, child.currentPlayer); // check if child is end game node
            parent.children.add(child);
        }
    }

    void backPropagateRollout(Node current, int won) {
        while(current != null){
            if(won == TTTSim.DRAW_GAME){
                current.draws++;
            }
            else if(current.currentPlayer == won){
                current.victories++;
            }
            else {
                current.losses++;
            }

            current = current.parent;
        }
    }
    
    void findBestPath(int Sims) {

    Node current = null;
    for(int i = 0;i<Sims;i++){
            current = nodeSelector();
            int won = Sim.simGameFromNode(current);
            backPropagateRollout(current, won);
    }

        double visits = 0.0;

        for(Node child: rootNode.children){
            if(child.numVisits >= visits){
                bestPath = child;
                visits = child.numVisits;
            }
        }
        System.out.println(" ");
        Sim.printBoard(bestPath.gameBoard);
        System.out.println(" ");
    }
}
