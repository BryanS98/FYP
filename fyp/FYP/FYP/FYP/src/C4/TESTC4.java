package C4;

public class TESTC4 {
	public static TESTC4GetKids C4GK = new TESTC4GetKids();
	public static TESTC4ValidateMove C4VM = new TESTC4ValidateMove();
	public static TESTC4WinTests C4WT = new TESTC4WinTests();
	
	public static void main(String args[]) {
		System.out.println("Running tests on node getKids function:\n");
		C4GK.runTest();
		System.out.println("\n\n\n\nRunning tests on move validation function:\n");
		C4VM.runTest();
		System.out.println("\n\n\n\nRunning tests on Win checking function:\n");
		C4WT.runTest();
	}
} 
