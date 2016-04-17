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
public class NodeTest {

	public NodeTest() {
	}

	/**
	 * Test of getData method, of class Node.
	 */
	@Test
	public void testGetData() {
		System.out.println("getData");
		Node instance = new Node();
		Object expResult = null;
		Object result = instance.getData();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getRoot method, of class Node.
	 */
	@Test
	public void testGetRoot() {
		System.out.println("getRoot");
		Node instance = new Node();
		Node expResult = null;
		Node result = instance.getRoot();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getDISTANCE_COST method, of class Node.
	 */
	@Test
	public void testGetDISTANCE_COST() {
		System.out.println("getDISTANCE_COST");
		Node instance = new Node();
		int expResult = 0;
		int result = instance.getDISTANCE_COST();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getHEURSITIC_COST method, of class Node.
	 */
	@Test
	public void testGetHEURSITIC_COST() {
		System.out.println("getHEURSITIC_COST");
		Node instance = new Node();
		int expResult = 0;
		int result = instance.getHEURSITIC_COST();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getFINAL_COST method, of class Node.
	 */
	@Test
	public void testGetFINAL_COST() {
		System.out.println("getFINAL_COST");
		Node instance = new Node();
		int expResult = 0;
		int result = instance.getFINAL_COST();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getDirection method, of class Node.
	 */
	@Test
	public void testGetDirection() {
		System.out.println("getDirection");
		Node instance = new Node();
		EnumDir expResult = null;
		EnumDir result = instance.getDirection();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getNumNeighbours method, of class Node.
	 */
	@Test
	public void testGetNumNeighbours() {
		System.out.println("getNumNeighbours");
		Node instance = new Node();
		byte expResult = 0;
		byte result = instance.getNumNeighbours();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setHEURSITIC_COST method, of class Node.
	 */
	@Test
	public void testSetHEURSITIC_COST() {
		System.out.println("setHEURSITIC_COST");
		int HEURSITIC_COST = 0;
		Node instance = new Node();
		instance.setHEURSITIC_COST(HEURSITIC_COST);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of grid method, of class Node.
	 */
	@Test
	public void testGrid() {
		System.out.println("grid");
		Node instance = new Node();
		String expResult = "";
		String result = instance.grid();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of toString method, of class Node.
	 */
	@Test
	public void testToString() {
		System.out.println("toString");
		Node instance = new Node();
		String expResult = "";
		String result = instance.toString();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of equals method, of class Node.
	 */
	@Test
	public void testEquals() {
		System.out.println("equals");
		Object obj = null;
		Node instance = new Node();
		boolean expResult = false;
		boolean result = instance.equals(obj);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of hashCode method, of class Node.
	 */
	@Test
	public void testHashCode() {
		System.out.println("hashCode");
		Node instance = new Node();
		int expResult = 0;
		int result = instance.hashCode();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of genNeighbours method, of class Node.
	 */
	@Test
	public void testGenNeighbours() {
		System.out.println("genNeighbours");
		Node instance = new Node();
		Node[] expResult = null;
		Node[] result = instance.genNeighbours();
		assertArrayEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of genNeighbour method, of class Node.
	 */
	@Test
	public void testGenNeighbour() {
		System.out.println("genNeighbour");
		int i = 0;
		Node instance = new Node();
		Node expResult = null;
		Node result = instance.genNeighbour(i);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of isGoal method, of class Node.
	 */
	@Test
	public void testIsGoal() {
		System.out.println("isGoal");
		Node instance = new Node();
		boolean expResult = false;
		boolean result = instance.isGoal(null);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to fail.
		fail("The test case is a prototype.");
	}

}
