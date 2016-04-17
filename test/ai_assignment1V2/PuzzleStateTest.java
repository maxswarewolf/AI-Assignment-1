/*
 * Author: Adrian Pellegrino.
 * Student ID: 2054442.
 */
package ai_assignment1V2;

import org.junit.Test;

/**
 *
 * @author Adrian
 */
public class PuzzleStateTest {

	public PuzzleStateTest() {
	}

	/**
	 * Test of getColumns method, of class PuzzleState.
	 */
	@Test
	public void testGetColumns() {
		System.out.println("getColumns");
		PuzzleState instance = null;
		byte expResult = 0;
		byte result = instance.getColumns();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getRows method, of class PuzzleState.
	 */
	@Test
	public void testGetRows() {
		System.out.println("getRows");
		PuzzleState instance = null;
		byte expResult = 0;
		byte result = instance.getRows();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of swap method, of class PuzzleState.
	 */
	@Test
	public void testSwap() {
		System.out.println("swap");
		byte indexStart = 0;
		byte indexEnd = 0;
		PuzzleState instance = null;
		instance.swap(indexStart, indexEnd);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of equals method, of class PuzzleState.
	 */
	@Test
	public void testEquals() {
		System.out.println("equals");
		Object obj = null;
		PuzzleState instance = null;
		boolean expResult = false;
		boolean result = instance.equals(obj);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of hashCode method, of class PuzzleState.
	 */
	@Test
	public void testHashCode() {
		System.out.println("hashCode");
		PuzzleState instance = null;
		int expResult = 0;
		int result = instance.hashCode();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getData method, of class PuzzleState.
	 */
	@Test
	public void testGetData() {
		System.out.println("getData");
		PuzzleState instance = null;
		byte[] expResult = null;
		byte[] result = instance.getData();
		assertArrayEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getIndex method, of class PuzzleState.
	 */
	@Test
	public void testGetIndex() {
		System.out.println("getIndex");
		byte item = 0;
		PuzzleState instance = null;
		byte expResult = 0;
		byte result = instance.getIndex(item);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of deepCopy method, of class PuzzleState.
	 */
	@Test
	public void testDeepCopy() {
		System.out.println("deepCopy");
		Puzzle copy = null;
		PuzzleState instance = null;
		instance.deepCopy(copy);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of returnDeepCopy method, of class PuzzleState.
	 */
	@Test
	public void testReturnDeepCopy() {
		System.out.println("returnDeepCopy");
		PuzzleState instance = null;
		Puzzle expResult = null;
		Puzzle result = instance.returnDeepCopy();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

}
