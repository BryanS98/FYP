package TTT;

public class TESTTTTValidateMove {
	public static void runTest() 
	{
    	int[] OpenBoard = new int[] 
    		{-1,-1,-1,
    		-1,-1,-1,
    		-1,-1,-1};
    	Test("Open Board", OpenBoard, 1, true);
    	
    	int[] InvalidMove = new int[] 
        		{-1,-1,-1,
        		-1,-1,-1,
        		-1,-1,-1};
        Test("Invalid Move", OpenBoard, 10, false);
        
        int[] OccupiedPosition = new int[] 
            	{1,-1,-1,
            	-1,-1,-1,
            	-1,-1,-1};
        Test("Occupied Position", OccupiedPosition, 0, false);
    	  	
	}
	
	public static void Test(String testName, int[] board, int move, boolean expectedResult) 
	{
		
		boolean actualResult = TTTSim.validMove(board, move);
    	
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
