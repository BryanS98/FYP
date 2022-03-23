package TTT;

import java.util.ArrayList;
import java.util.Random;

public class TTTSim {

    static final int EMPTY = -1;

    static final int DRAW_GAME = 2;
    static final int CONTINUE_GAME = -2;
    Random rand;

    TTTSim() {

        rand = new Random();
    }

    TTTNode simGameFromNode(TTTNode n) { // do rollout

        TTTNode current = n;
        int player = current.currentPlayer;
        while (true) { // simulate a random game
            ArrayList<Integer> moves = getAllPossibleMoves(current.gameBoard);
            int size = moves.size();
            if (size == 0) {
                if (TTTGameDecided.checkDraw(current.gameBoard)) {
                    current.GameState = DRAW_GAME;
                    return current;
                }
            }
            int randomMoveIndex = rand.nextInt(moves.size());
            if (current.children.isEmpty()) {
                current.getKids(this, player);
            }
            current = current.children.get(randomMoveIndex);
            int won = TTTGameDecided.GameDecided(current.gameBoard, player);
            if (won != CONTINUE_GAME) {
                current.GameState = won;
                return current;
            }
            player ^= 1;

        }

    }

    static ArrayList<Integer> getAllPossibleMoves(int[] gameBoard) {
        ArrayList<Integer> allPossibleMoves = new ArrayList<Integer>(9);
        for (int index = 0; index < gameBoard.length; index++) {
            if (validMove(gameBoard, index)) {
                allPossibleMoves.add(index);
            }
        }
        return allPossibleMoves;
    }

    static boolean validMove(int[] board, int move) {
        if (move > 8 || move < 0) {
            return false;
        } else if (board[move] != -1) {
            return false;
        }
        return true;
    }

}