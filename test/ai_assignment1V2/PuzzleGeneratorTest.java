/*
 * Author: Adrian Pellegrino.
 * Student ID: 2054442.
 */
package ai_assignment1V2;

import java.util.LinkedList;
import org.junit.Test;

/**
 *
 * @author Adrian
 */
public class PuzzleGeneratorTest {

	public PuzzleGeneratorTest() {
	}

	/**
	 * Test of generatePuzzles method, of class PuzzleGenerator.
	 */
	@Test
	public void testGeneratePuzzles() {
		System.out.println("generatePuzzles");
		int numPuzzleCells = 0;
		int maxNumPuzzles = 0;
		Object goal = null;
		Object start = null;
		PuzzleGenerator instance = new PuzzleGenerator();
		LinkedList<FinderAgent<T>> expResult = null;
		LinkedList<FinderAgent<T>> result = instance.generatePuzzles(numPuzzleCells, maxNumPuzzles, goal, start);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

}
