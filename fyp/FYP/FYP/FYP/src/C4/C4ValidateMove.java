package C4;

public class C4ValidateMove {
	public static boolean validateMove(int[][] board, int playerId, int move, boolean shouldPrint) {
        if (move > 6) {
        	if(shouldPrint) {
        		System.out.println("Invalid Column");
        	}
            return false;
        } else if (board[0][move] != 0) {
            if(shouldPrint) {
            	System.out.println("Column full");	
            }
            return false;
        }

        return true;
    }
}
