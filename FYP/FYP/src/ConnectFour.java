import java.util.*;
 
public class ConnectFour {
 
    static Scanner scan = new Scanner(System.in);
    static int lastCol, lastRow;
 
    private static WinChecker _winChecker = new WinChecker();
 
    public static void main(String args[]){
        Connect4(createBoard());
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
 
    public static void Connect4(int[][] board){
 
    	boolean game_running = true;
 
    	while(game_running) 
    	{
            if(performTurn(board, 1))
            {
            	game_running = false;
            	continue; //exit loop
            }
 
            if(performTurn(board, 2)) 
            {
            	game_running = false;
            	continue; //exit loop
            }
    	}
 
        return;
    }
 
    public static boolean performTurn(int[][] board, int playerId) 
    {
        printBoard(board);
        makeMove(board, playerId);
 
        return checkWin(board, playerId);
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
 
 
    public static boolean checkWin(int [][] board, int player){
 
        int height = board.length-1;
        int width = board[0].length-1;
        int score = 1;
        int i = 1;
 
        System.out.println("Scores for player: " + player);
 
        if(_winChecker.CheckHorizontalWin(board, player)) {
        	return true;
        }
        if(_winChecker.CheckVerticalWin(board, player)) {
        	return true;
        }
 
        //L-R Diagonal Check
        while(lastCol+i<=width && lastRow+i<=height && i!=0 && board[lastRow+i][lastCol+i]==player){
            score++;
            i++;
            if(score==4) {
                System.out.println("+L-R Diagonal Win for Player: " + player);
                return true;
            }
        }
 
        i = 3-i;
        while(lastCol-i>=0 && lastRow-i>=0 && i!=0 && board[lastRow-i][lastCol-i]==player){
            score++;
            i--;
            if(score==4) {
                System.out.println("-L-R Diagonal Win for Player: " + player);
                return true;
            }
        }
        System.out.println("L-R Diagonal score: " + score);
        score = 1;
 
 
        //R-L Diagonal Check
        while(lastCol-i>=0 && lastRow+i<=height && i!=0 && board[lastRow+i][lastCol-i]==player){
            score++;
            i++;
            if(score==4) {
                System.out.println("+R-L Diagonal Win for Player: " + player);
                return true;
            }
        }
 
        i = 3-i;
 
        while(lastCol-i>=0 && lastRow+i<=height && i!=0 && board[lastRow+i][lastCol-i]==player){
            score++;
            i--;
            if(score==4) {
                System.out.println("-R-L Diagonal Win for Player: " + player);
                return true;
            }
        }
        System.out.println("R-L Diagonal score: " + score);
        score = 1;
 
        return false;
    }
 
}