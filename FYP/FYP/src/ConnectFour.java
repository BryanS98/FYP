

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
        boolean win = checkWin(board, Player);
        if(!win) nextMove(board,Player);
        return;
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
        
        int height = board.length-1;
        int width = board[0].length-1;
        int score = 1;
        int i = 1;

        System.out.println("Scores for player: " + Player);

        //Verical Check
        while(lastRow+i<=height && board[lastRow+i][lastCol]==Player){
            score++;
            i++;
            if(score==4) {
                System.out.println("Vertical Win for Player: " + Player);
                return true;
            }
        }
        System.out.println("Vertical score: " + score);
        score = 1;

        //Right Horizontal Check
        while(lastCol+i<=width && board[lastRow][lastCol+i]==Player){
            score++;
            i++;
            if(score==4) {
                System.out.println("Horizontal Win for Player: " + Player);
                return true;
            }
        }
        //Left Horizontal Check
        i = 3-i;
        while(lastCol-i>=0 && i>0 && board[lastRow][lastCol-i]==Player){
            score++;
            i--;
            if(score==4) {
                System.out.println("Horizontal Win for Player: " + Player);
                return true;
            }
        }
        System.out.println("Horizontal score: " + score);
        score = 1;





        
        

        return false;
    }

}
