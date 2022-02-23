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
        int num = 0;
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
            System.out.println("fucking kill me");
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
        lastCol = chosenColumn;
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
        System.out.println("Check win");
        if(_winChecker.CheckHorizontalWin(board, player)) {
        	return true;
        }
        if(_winChecker.CheckVerticalWin(board, player)) {
        	return true;
        }
        if(_winChecker.CheckDiagonalWin(board, player, lastRow, lastCol)) {
        	return true;
        }
        System.out.println("Not Diagonal");
 
        return false;
    }
 
}