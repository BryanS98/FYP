package C4;
public class CheckValidateMove 
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
    	int[][] p1ValidMove = new int[][] {
    		{0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
    	};
    	Test("P1 Valid Move", p1ValidMove, 1, 1, true);
		
    	int[][] p2ValidMove = new int[][] {
    		{0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
    	};
    	Test("P2 Valid Move", p2ValidMove, 2, 2, true);
    	
    	int[][] p1InvalidMove = new int[][] {
    		{2,1,0,0,0,0,0},
    		{2,1,0,0,0,0,0},
            {2,1,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
    	};
    	Test("P1 Invalid Move", p1InvalidMove, 1, 1, false);
    	
    	int[][] p2InvalidMove = new int[][] {
    		{2,1,0,0,0,0,0},
    		{2,1,0,0,0,0,0},
            {2,1,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
    	};
    	Test("P2 Invalid Move", p2InvalidMove, 2, 0, false);
    	
    	int[][] p1InvalidMoveOOB = new int[][] {
    		{2,1,0,0,0,0,0},
    		{2,1,0,0,0,0,0},
            {2,1,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
    	};
    	Test("P1 Invalid Move (Out of bounds)", p1InvalidMoveOOB, 1, 8, false);
    	
    	int[][] p2InvalidMoveOOB = new int[][] {
    		{2,1,0,0,0,0,0},
    		{2,1,0,0,0,0,0},
            {2,1,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
    	};
    	Test("P2 Invalid Move (Out of bounds)", p2InvalidMoveOOB, 2, 8, false);
	}
	
	public static void Test(String testName, int[][] board, int player, int move, boolean expectedResult) 
	{
    	var actualResult = C4ValidateMove.validateMove(board, player, move ,false);
    	
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