package C4;

public class TESTC4GetKids
{
	public static C4MCTS MCTS = new C4MCTS();
 
	public static void runTest() 
	{
    	int[][] p1OpenBoard = new int[][] {
    		{0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
    	};
    	Test("P1 Open Board", p1OpenBoard, 1, 7);
		
    	int[][] p2OpenBoard = new int[][] {
    		{0,0,0,0,0,0,0},
    		{0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
            {1,2,0,0,0,0,0},
    	};
    	Test("P2 Open Board", p2OpenBoard, 2, 7);
    	
    	int[][] p1ClosedBoard = new int[][] {
    		{2,1,2,1,2,1,2},
    		{2,1,2,1,2,1,2},
            {2,1,2,1,2,2,1},
            {1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1},
    	};
    	Test("P1 Closed Board", p1ClosedBoard, 1, 0);
    	
    	int[][] p2ClosedBoard = new int[][] {
    		{2,1,2,1,2,1,2},
    		{2,1,2,1,2,1,2},
            {2,1,2,1,2,2,1,2},
            {1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1},
    	};
    	Test("P2 Closed Board", p2ClosedBoard, 2, 0);
    	
    	
    	int[][] p1HalfFullBoard = new int[][] {
    		{2,1,0,0,0,1,2},
    		{2,1,2,0,2,1,2},
            {2,1,2,1,2,2,1},
            {1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1},
    	};
    	Test("P1 Half Full Board", p1HalfFullBoard, 1, 3);
    	
    	int[][] p2HalfFullBoard = new int[][] {
    		{2,1,0,0,0,1,2},
    		{2,1,2,0,2,1,2},
            {2,1,2,1,2,2,1},
            {1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1},
            {1,2,1,2,1,2,1},
    	};
    	Test("P2 Half Full Board", p2HalfFullBoard, 2, 3);
    	
	}
	
	public static void Test(String testName, int[][] board, int player, int expectedResult) 
	{
    	C4Sim sim = new C4Sim();
    	MCTS.rootNode = new C4Node(player, MCTS.rootNode, board, -1);
		MCTS.rootNode.getKids(sim, player);
		
		int actualResult = MCTS.rootNode.children.size();
    	
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

