import java.util.*;

public class test {

    public static void main(String args[]) {
        Connect4();
    }

    public static void Connect4() {
        int width = 7;
        int height = 6;
        int Player1 = 1;
        int Player2 = 2;
        int[][] board = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = 0;
            }
        }
        printBoard(board, height, width);
        makeMove(board, Player1);
        printBoard(board, height, width);
    }

    public static int[][] makeMove(int[][] board, int Player) {
        System.out.println("Select column 0-6");
        Scanner scan = new Scanner(System.in);
        int chosenColumn = scan.nextInt();
        scan.close();
        int columnHeight = board.length - 1;
        for (int i = 0; i <= columnHeight; i++) {
            System.out.println(i);
            if (board[0][chosenColumn] != 0) {
                System.out.println("Column Full, Please choose another Column");
                return makeMove(board, Player);
            } else if (board[i][chosenColumn] != 0) {
                System.out.println("Regular");
                board[i - 1][chosenColumn] = Player;
                return board;
            } else {
                System.out.println("Bottom");
                board[columnHeight][chosenColumn] = Player;
                return board;
            }
        }

        return board;
    }

    public static void printBoard(int[][] board, int h, int w) {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (j == w - 1) {
                    System.out.print(board[i][j]);
                } else {
                    System.out.print(board[i][j] + " | ");
                }

            }
            System.out.println("\n" + "---------------------------");
        }
    }
}