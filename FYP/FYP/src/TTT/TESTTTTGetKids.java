package TTT;


public class TESTTTTGetKids
{
	
	public static void runTest() 
	{
		
		int[] p1Circle = new int[] 
	     	   {-1,-1,-1,
	     		-1,0,-1,
	     		-1,-1,-1};
	     Test("P1 Circle", p1Circle, 1, 8);
	     	
    	int[] p2Circle = new int[] 
    		   {-1,-1,-1,
    			-1,1,-1,
    			-1,-1,-1};
    	Test("P2 Circle", p2Circle, 0, 8);
    	
    	int[] NoMoves = new int[] 
 	     	   {1,0,1,
 	     		1,0,1,
 	     		0,1,0};
 	     Test("No Moves", NoMoves, 0, 0);
 	     
 	    int[] OneMove = new int[] 
  	     	   {1,0,-1,
  	     		1,0,1,
  	     		0,1,0};
  	     Test("One Move", OneMove, 0, 1);
 	     
		
	}
	
	public static void Test(String testName, int[] board, int player, int expectedResult) 
	{
    	TTTSim sim = new TTTSim();
    	TTTMCTS MCTS = new TTTMCTS();
    	MCTS.rootNode = new TTTNode(player, MCTS.rootNode, board, -1);
    	MCTS.rootNode = new TTTNode(player, MCTS.rootNode, board, -1);
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

