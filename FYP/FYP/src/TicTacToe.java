
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {

        TTTMCTS f = new TTTMCTS();
        int numIter = 2000;
        Scanner scan = new Scanner(System.in);
        int[] GameBoard = new int[9];
        Arrays.fill(GameBoard, TTTSim.EMPTY);
        f.rootNode = new TTTNode(TTTSim.O, null, GameBoard, -1);

        int iter = 0;
        int player = 1;
        while (true) { // game loop
            TTTSim.printBoard(GameBoard);
            if (player == 1) {
                System.out.println("Choose move 1-9");
                int move = scan.nextInt() - 1;
                while (!TTTSim.validMove(GameBoard, move)) {
                    System.out.println("Invalid move, please choose anotherS");
                    move = scan.nextInt() - 1;
                }
                GameBoard[move] = 1;
                f.rootNode = new TTTNode(0, f.rootNode, GameBoard, move);
                if (TTTSim.GameDecided(GameBoard, 1) == 1) {
                    System.out.println("Player 1 Wins!");
                    break;
                }
            } else {
                if (!f.Sim.getAllPossibleMoves(f.rootNode.gameBoard).isEmpty())
                    GameBoard[f.findBestPath(numIter)] = 0;
                else {
                    break;
                }
                f.rootNode = new TTTNode(f.bestPath.currentPlayer, null, f.bestPath.gameBoard, f.bestPath.move);
            }
            TTTSim.checkDraw(GameBoard);
            player = switchPlayer(player);
        }
    }

    static int switchPlayer(int player) {
        if (player == 1)
            return 2;
        return 1;
    }
}