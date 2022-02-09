

import java.util.*;

public class ConnectFour {

    static Scanner scan = new Scanner(System.in);
    static int lastCol, lastRow;
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
        checkWin(board, Player);
        nextMove(board,Player);
    }

    public static int[][] makeMove(int[][] board, int Player){
        System.out.println("Select column 0-6");
        int chosenColumn = scan.nextInt();
        int lastCol = chosenColumn;
        if(chosenColumn > 6){
            System.out.println("Invalid Column");
            chosenColumn=0;
            makeMove(board, Player);
        }
        int columnHeight = board.length-1;
        System.out.println("Column height value " + columnHeight);
        for(int i=0; i<=columnHeight; i++){
            if(board[0][chosenColumn]!=0){
                System.out.println("Column Full, Please choose another Column");
                return makeMove(board, Player);
            }
            else if(board[i][chosenColumn]!=0){
                board[i-1][chosenColumn] = Player;
                lastRow = i-1;
                return board;
            }
        }
        board[columnHeight][chosenColumn] = Player;
        lastRow = columnHeight;
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

     
    public static boolean checkWin(int [][] board, int Player){
        //LastCol and LastRow
        int lastCounter = board[lastRow][lastCol];
        int height = board.length;
        int width = board[0].length;
        int score = 0;
        //Horizontal Check
        int i = 0;
        while(board[lastCol][lastRow+i]==Player){
            score++;
            i++;
            System.out.println("score: " + score);
            if(score==4) {
                System.out.println("Horizontal win");
                return true;
            }
        }
        while(board[lastCol][lastRow-i]==Player){
            score++;
            i--;
            System.out.println("score: " + score);
            if(score==4) {
                System.out.println("You Win!");
                return true;
            }
        }
        
        //reset score and i value
        i = 0;
        score = 0;
        //Vertical Check
        while(board[lastCol+i][lastRow]==Player){
            score++;
            i++;
            System.out.println("score: " + score);
            if(score==4) {
                System.out.println("You Win!");
                return true;
            }
        }
        while(board[lastCol-i][lastRow]==Player){
            score++;
            i--;
            System.out.println("score: " + score);
            if(score==4) {
                System.out.println("You Win!");
                return true;
            }
        }

        //Reset score and i value
        i = 0;
        score = 0;
        //Diagonal Check
        while(board[lastCol+i][lastRow+i]==Player){
            score++;
            i++;
            System.out.println("score: " + score);
            if(score==4) {
                System.out.println("You Win!");
                return true;
            }
        }
        while(board[lastCol-i][lastRow-i]==Player){
            score++;
            i--;
            System.out.println("score: " + score);
            if(score==4) {
                System.out.println("You Win!");
                return true;
            }
        }

        score = 0;
        
        return false;
    }

}
