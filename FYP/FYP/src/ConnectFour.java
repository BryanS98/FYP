

import java.util.*;

public class ConnectFour {

    static Scanner scan = new Scanner(System.in);
    public static void main(String args[]){
        Connect4(createBoard(),1);
    }

    public static int[][] createBoard(){
        int width = 7;
        int height = 6;
        int[][] board = new int[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = 0;
            }
        }
        return board;
    }

    public static void Connect4(int[][] board,int Player){
        printBoard(board);
        makeMove(board, Player);
        nextMove(board,Player);
    }

    public static int[][] makeMove(int[][] board, int Player){
        System.out.println("Select column 0-6");
        int chosenColumn = scan.nextInt();
        if(chosenColumn > 6){
            System.out.println("Invalid Column");
            chosenColumn=0;
            makeMove(board, Player);
        }
        int columnHeight = board.length-1;
        System.out.println("Column height value " + columnHeight);
        for(int i=0; i<=columnHeight; i++){
            System.out.println("Checking slot at value " + i);
            System.out.println(board[chosenColumn][i]);
            if(board[0][chosenColumn]!=0){
                System.out.println("Column Full, Please choose another Column");
                return makeMove(board, Player);
            }
            else if(board[i][chosenColumn]!=0){
                board[i-1][chosenColumn] = Player;
                return board;
            }
        }
        board[columnHeight][chosenColumn] = Player;
        return board;
        
    }

    public static void printBoard(int[][] board){
        int h = board.length;
        int w = board[0].length;
        for(int i = 0;i < h; i++){
            for(int j = 0;j < w; j++){
                if(j == w-1){
                    System.out.print(board[i][j]);
                }
                else{
                    System.out.print(board[i][j] + " | ");
                }
                
            }
            System.out.println("\n" + "---------------------------");
        }
    }

    public static void nextMove(int[][] board, int Player){
        if(Player == 1){
            Connect4(board,2);
        }
        Connect4(board,1);
    }
}
