package TTT;

import C4.TESTC4GetKids;
import C4.TESTC4ValidateMove;
import C4.TESTC4WinTests;

public class TESTTTT {
	public static TESTTTTGetKids TTTGK = new TESTTTTGetKids();
	public static TESTTTTValidateMove TTTVM = new TESTTTTValidateMove();
	public static TESTTTTGameDecided TTTGD = new TESTTTTGameDecided();
	
	public static void main(String args[]) {
		System.out.println("Running tests on node getKids function:\n");
		TTTGK.runTest();
		System.out.println("\n\n\n\nRunning tests on move validation function:\n");
		TTTVM.runTest();
		System.out.println("\n\n\n\nRunning tests on Win checking function:\n");
		TTTGD.runTest();
	}
}
