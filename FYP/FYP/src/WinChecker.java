
public class WinChecker {
 
    public WinChecker() {}
     
    public boolean CheckVerticalWin(int[][] board, int playerId) 
    {
        for(int colId = 0; colId < board[0].length /*columns*/; colId++) {
            int columnScore = 0;
            for(int rowId = 0; rowId < board.length; rowId++) {
               if(board[rowId][colId] == playerId) { // columnScore += (board[rowId][columnId] == player) ? 1 : 0; //Crazy ternary operator
                    columnScore++;
                }
                else{
                    columnScore = 0; //Reset column score if there's a break
                }
                if(columnScore == 4) {
                        System.out.println("Vertical Win for Player: " + playerId + " on column #" + colId);
                        return true;
                    }
                }
            }	
     
            return false;
        }
     
    public boolean CheckHorizontalWin(int[][] board, int playerId)
    {
        for(int rowId = 0; rowId < board.length; rowId++)
        {
            int rowScore = 0;
            for(var colId = 0; colId < board[0].length; colId++) 
            {
                    if(board[rowId][colId] == playerId) { // columnScore += (board[rowId][columnId] == player) ? 1 : 0; //Crazy ternary operator
                       rowScore++;
                }
                else{
                    rowScore = 0; //Reset column score if there's a break
                }			
     
                    if(rowScore == 4) {
                    System.out.println("Horizontal Win for Player: " + playerId + " on row #" + rowId);
                    return true;
                }
               }
        }
     
        return false;
    }
    
    public boolean CheckDiagonalWin(int[][] board, int playerId, int lastRow, int lastCol) 
    {
    	var score = 0;
    	
    	//CheckDiagonalWinRec(board, playerId, lastRow, lastCol, score);
    			
    	// Left Right Down
    	    	
        for(int rowId = lastRow+1; rowId < lastRow + 4; rowId++)
        {
            for(var colId = lastCol+1; colId < lastCol + 4; colId++) 
            {
            	if(board[rowId][colId] == playerId)
            	{
            		score++;
            	}
            	
            	if(score == 3) 
            	{
            		return true;
            	}            	
            	
            }
        }
        
        return false;
    	
    }
    
    /*public boolean CheckDiagonalWinRec(int[][] board, int playerId, int rowId, int colId, int score) 
    {
    	if(score == 4) {
    		return true;
    	}
    	
    	while(rowId <= board.length) 
    	{
    		
    	}
    	
    	return false;
    }*/

   
    
}