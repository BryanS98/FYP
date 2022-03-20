public class CheckWinTests 
{
    /* EMPTY BOARD
    
    	//7,6
    	int[][] board = new int[][] {
    		{0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
    	};
    */
	
	public static void main(String[] args) 
	{
    	int[][] p1VerticalWin = new int[][] {
    		{0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0},
            {1,0,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
    	};
    	Test("P1 Vertical Win", p1VerticalWin, 1, true);
		
    	int[][] p1HorizontalWin = new int[][] {
    		{0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {2,2,2,0,0,0,0},
            {1,1,1,1,0,0,0},
    	};
    	Test("P1 Horizontal Win", p1HorizontalWin, 1, true);

    	int[][] p1NoWin1 = new int[][] {
    		{0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {2,2,0,0,0,0,0},
            {1,1,1,0,0,0,0},
    	};
    	Test("Player 1 No Win 1", p1NoWin1, 1, false);
	
    	int[][] p1NoWin2 = new int[][] {
    		{0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {2,1,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
    	};
    	Test("Player 1 No Win 2", p1NoWin1, 1, false);
    	
    	int[][] p1NoWinHorBreak = new int[][] {
    		{0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {2,0,2,0,0,0,0},
            {2,2,2,0,0,0,0},
            {1,1,1,0,1,1,0},
    	};
    	Test("Player 1 No Win with Horizontal Break", p1NoWinHorBreak, 1, false);

    	int[][] p2NoWin = new int[][] {
    		{0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {2,2,2,0,0,0,0},
            {1,1,1,0,0,0,0},
    	};
    	Test("Player 2 No Win", p2NoWin, 1, false);	
    	
    	int[][] p1WinDiagonalSimple = new int[][] {
    		{0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0},
            {0,0,0,1,0,0,0},
            {0,0,1,0,0,0,0},
            {0,1,0,0,2,0,0},
            {1,0,0,0,0,0,0},
    	};
    	Test("Player 1 Win Diagonal Simple", p1WinDiagonalSimple, 1, true);	
    	
    	int[][] p1WinDiagonal = new int[][] {
    		{0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0},
            {0,0,0,1,0,0,0},
            {0,1,1,2,0,0,0},
            {2,1,1,2,0,0,0},
            {1,2,2,2,0,0,0},
    	};
    	Test("Player 1 Win Diagonal", p1WinDiagonal, 1, true);	
	}
	
	public static void Test(String testName, int[][] board, int player, boolean expectedResult) 
	{
    	var actualResult = ConnectFour.checkWin(board, player);
    	
    	var success = actualResult == expectedResult;
    	
    	System.out.println("--------------------------------------------------------------");
    	if(success) {
    		System.out.println("Test " + testName+ " Succeded");
    	}
    	else {
    		System.out.println("Test "  + testName + " Failed" + 
			". ExpectedValue= " + expectedResult + 
			". ActualValue= " + actualResult);
    	}
    	System.out.println("--------------------------------------------------------------");
	}
}
