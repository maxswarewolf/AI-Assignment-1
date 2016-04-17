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
public class FinderAgentTest {

	public FinderAgentTest() {
	}

	/**
	 * Test of getStart method, of class FinderAgent.
	 */
	@Test
	public void testGetStart() {
		System.out.println("getStart");
		FinderAgent instance = null;
		Node expResult = null;
		Node result = instance.getStart();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getEnd method, of class FinderAgent.
	 */
	@Test
	public void testGetEnd() {
		System.out.println("getEnd");
		FinderAgent instance = null;
		Object expResult = null;
		Object result = instance.getEnd();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getMaxNodes method, of class FinderAgent.
	 */
	@Test
	public void testGetMaxNodes() {
		System.out.println("getMaxNodes");
		FinderAgent instance = null;
		int expResult = 0;
		int result = instance.getMaxNodes();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of toString method, of class FinderAgent.
	 */
	@Test
	public void testToString() {
		System.out.println("toString");
		FinderAgent instance = null;
		String expResult = "";
		String result = instance.toString();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of listAlgoSearch method, of class FinderAgent.
	 */
	@Test
	public void testListAlgoSearch() {
		System.out.println("listAlgoSearch");
		FinderAgent instance = null;
		instance.listAlgoSearch(null);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of search method, of class FinderAgent.
	 */
	@Test
	public void testSearch() {
		System.out.println("search");
		FinderAgent instance = null;
		LinkedList<Node<T>> expResult = null;
		LinkedList<Node<T>> result = instance.search(null);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

}
