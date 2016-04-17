/*
 * Author: Adrian Pellegrino.
 * Student ID: 2054442.
 */
package ai_assignment1V2;

import java.util.Comparator;
import org.junit.Test;

/**
 *
 * @author Adrian
 */
public class GBFSTest {

	public GBFSTest() {
	}

	/**
	 * Test of getComparator method, of class GBFS.
	 */
	@Test
	public void testGetComparator() {
		System.out.println("getComparator");
		GBFS instance = null;
		Comparator<Node<PuzzleState>> expResult = null;
		Comparator<Node<PuzzleState>> result = instance.getComparator();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getNodeCost method, of class GBFS.
	 */
	@Test
	public void testGetNodeCost() {
		System.out.println("getNodeCost");
		Node<PuzzleState> node = null;
		GBFS instance = null;
		int expResult = 0;
		int result = instance.getNodeCost(node);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of name method, of class GBFS.
	 */
	@Test
	public void testName() {
		System.out.println("name");
		GBFS instance = null;
		String expResult = "";
		String result = instance.name();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getHeursitic method, of class GBFS.
	 */
	@Test
	public void testGetHeursitic() {
		System.out.println("getHeursitic");
		GBFS instance = null;
		EnumHeursitic expResult = null;
		EnumHeursitic result = instance.getHeursitic();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getLimit method, of class GBFS.
	 */
	@Test
	public void testGetLimit() {
		System.out.println("getLimit");
		GBFS instance = null;
		int expResult = 0;
		int result = instance.getLimit();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setLimit method, of class GBFS.
	 */
	@Test
	public void testSetLimit_Node_Node() {
		System.out.println("setLimit");
		Node<PuzzleState> origin = null;
		Node<PuzzleState> goal = null;
		GBFS instance = null;
		instance.setLimit(origin, goal);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of limitReached method, of class GBFS.
	 */
	@Test
	public void testLimitReached() {
		System.out.println("limitReached");
		Node<PuzzleState> a = null;
		GBFS instance = null;
		boolean expResult = false;
		boolean result = instance.limitReached(a);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of search method, of class GBFS.
	 */
	@Test
	public void testSearch() {
		System.out.println("search");
		Node<PuzzleState> start = null;
		Node<PuzzleState> goal = null;
		GBFS instance = null;
		Node<PuzzleState> expResult = null;
		Node<PuzzleState> result = instance.search(start, goal);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setLimit method, of class GBFS.
	 */
	@Test
	public void testSetLimit_int() {
		System.out.println("setLimit");
		int a = 0;
		GBFS instance = null;
		instance.setLimit(a);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

}
