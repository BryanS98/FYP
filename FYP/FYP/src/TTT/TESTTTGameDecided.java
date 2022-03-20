package TTT;

public class TESTTTGameDecided {
	
	public static void main(String[] args) 
	{
    	int[] p1WinningBoard = new int[] 
    		{1,1,1,
    		-1,0,0,
    		-1,0,-1};
    	Test("P1 Winning Board", p1WinningBoard, 1, 1);
    	
    	int[] p2WinningBoard = new int[] 
        		{1,0,1,
        		-1,0,1,
        		-1,0,-1};
        Test("P2 Winning Board", p2WinningBoard, 0, 0);
        
        int[] p1DiagonalWinningBoard = new int[] 
        		{1,-1,0,
        		-1,1,0,
        		-1,0,1};
        	Test("P1 Diagonal Winning Board", p1DiagonalWinningBoard, 1, 1);
        	
        int[] p2DiagonalWinningBoard = new int[] 
            		{0,-1,1,
            		-1,0,1,
            		-1,1,0};
        Test("P2 Diagonal Winning Board", p2DiagonalWinningBoard, 0, 0);
        
        int[] DrawBoard = new int[] 
        		{1,1,0,
        		 0,0,1,
        		 1,0,1};
        Test("Draw Board", DrawBoard, 0, 2);
        
        int[] noWinner = new int[] 
        		{-1,-1,-1,
        		 0,1,0,
        		 1,0,1};
        Test("Continue Game", noWinner, 0, -2);
        	
	}
	
	public static void Test(String testName, int[] board, int player, int expectedResult) 
	{
		
		int actualResult = TTTGameDecided.GameDecided(board, player);
    	
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
