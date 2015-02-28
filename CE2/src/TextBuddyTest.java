import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TextBuddyTest {

	
	String fileName = "mytextfile.txt";
	
	private void executeTestEquals(String testCaseInput, String expectedOutput) {
		
		String result = TextBuddy.runTest(fileName, testCaseInput);
		assertEquals(expectedOutput, result);
	}
	

	@Test
	public void testAdd() {
		String testInput = "add hi there";
		String testOutput = "added to mytextfile.txt: \"hi there\"";
		
		executeTestEquals(testInput, testOutput);
	}
	
	@Test
	public void testAdd1() {
		String testInput = "add alien there";
		String testOutput = "added to mytextfile.txt: \"alien there\"";
		
		executeTestEquals(testInput, testOutput);
	}
	
	@Test
	public void testSort() {
		String testInput = "sort";
		String testOutput = "file sorted";
		
		executeTestEquals(testInput, testOutput);
	}
	
	@Test
	public void testSearchWord() {
		String testInput = "search bye";
		String testOutput = "1. bye bye\n";
		
		executeTestEquals(testInput, testOutput);
	}
	
	@Test
	public void testSearchAfterAddWord() {
		String testInput = "search there";
		String testOutput = "1. alien there\n";
		testOutput += "3. hi there\n";
		executeTestEquals(testInput, testOutput);
	}
	
	@Test
	public void testDisplayList() {
		String testInput = "display";
		String testOutput = "1. alien there\n";
		testOutput += "2. bye bye\n";
		testOutput += "3. hi there\n";
		executeTestEquals(testInput, testOutput);
	}
	
	@Test
	public void testDeleteD1() {
		String testInput = "delete 1";
		String testOutput = "deleted from mytextfile.txt:\"alien there\"";
		
		executeTestEquals(testInput, testOutput);
	}
	
	@Test
	public void testDeleteD2() {
		String testInput = "delete 2";
		String testOutput = "deleted from mytextfile.txt:\"hi there\"";
		
		executeTestEquals(testInput, testOutput);
	}

}
