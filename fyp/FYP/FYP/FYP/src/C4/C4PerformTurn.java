package C4;
import java.util.Scanner;


public class C4PerformTurn {
	 public static C4MCTS MCTS = new C4MCTS();
	static Scanner scan = new Scanner(System.in);
	public static boolean performTurn(int[][] board, int playerId) {
        if (playerId == 1) {
            System.out.println("Select column 1-7");
            int move = scan.nextInt() - 1;
            while (!C4ValidateMove.validateMove(board, playerId, move, true)) {
                move = scan.nextInt();
            }
            scan.close();
            MCTS.rootNode = new C4Node(C4Sim.Player1, MCTS.rootNode, board, move);
            C4MakeMove.makeMove(MCTS.rootNode.gameBoard, playerId, move);
            return ConnectFour.checkWin(MCTS.rootNode.gameBoard, playerId);
        } else {
            int numIter = 1000;
            while (true) {
                if (!MCTS.Sim.getAllPossibleMoves(MCTS.rootNode.gameBoard, MCTS.rootNode.currentPlayer).isEmpty()) {
                    int chosenMove = MCTS.findBestPath(numIter);
                    C4MakeMove.makeMove(MCTS.rootNode.gameBoard, playerId, chosenMove);
                    MCTS.rootNode = new C4Node(C4Sim.Player2, MCTS.bestPath, MCTS.bestPath.gameBoard,
                            MCTS.bestPath.move);
                    return ConnectFour.checkWin(MCTS.rootNode.gameBoard, playerId);
                }
                return ConnectFour.checkWin(MCTS.rootNode.gameBoard, playerId);
            }
        }
        
	}
}
