package TTT;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {

        TTTMCTS MCTS = new TTTMCTS();
        int numIter = 2000;
        Scanner scan = new Scanner(System.in);
        int[] GameBoard = new int[9];
        Arrays.fill(GameBoard, TTTSim.EMPTY);
        MCTS.rootNode = new TTTNode(0, null, GameBoard, -1);
        int player = 1;

        while (true) { // game loop
        	TTTPrintBoard.printBoard(GameBoard);
            if (player == 1) {
                System.out.println("Choose move 1-9");
                int move = scan.nextInt() - 1;
                while (!TTTSim.validMove(GameBoard, move)) {
                    System.out.println("Invalid move, please choose another");
                    move = scan.nextInt() - 1;
                }
                GameBoard[move] = 1;
                MCTS.rootNode = new TTTNode(0, MCTS.rootNode, GameBoard, move);
                if (TTTGameDecided.GameDecided(GameBoard, 1) == 1) {
                    TTTPrintBoard.printBoard(GameBoard);
                    System.out.println("Player 1 Wins!");
                    break;
                }
            } else {
                if (!MCTS.Sim.getAllPossibleMoves(MCTS.rootNode.gameBoard).isEmpty())
                    GameBoard[MCTS.findBestPath(numIter)] = 0;
                else {
                    break;
                }
                if (TTTGameDecided.GameDecided(GameBoard, 0) == 0) {
                	TTTPrintBoard.printBoard(GameBoard);
                    System.out.println("Player 2 Wins!");
                    break;
                }
                MCTS.rootNode = new TTTNode(MCTS.bestPath.currentPlayer, null, MCTS.bestPath.gameBoard, MCTS.bestPath.move);
            }
            TTTGameDecided.checkDraw(GameBoard);
            player = switchPlayer(player);
        }

    }

    static int switchPlayer(int player) {
        if (player == 1)
            return 2;
        return 1;
    }
}