package friendshipGraph;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import person.PersonA;

public class FriendshipGraphATest {

	/**
	 * Test that assertions are enabled
	 * */
	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false;
	}
	
	@SuppressWarnings("deprecation")
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	/**
	 * Test addVertex
	 * */
	@Test
	public void addVertexTest() {
		FriendshipGraphA graphA = new FriendshipGraphA();
		PersonA p1 = new PersonA("Mike");
		PersonA p2 = new PersonA("John");
		PersonA p3 = new PersonA("Kiki");

		assertEquals(0, graphA.getVertexNum());
		
		graphA.addVertex(p1);
		assertEquals(1, graphA.getVertexNum());

		graphA.addVertex(p2);
		assertEquals(2, graphA.getVertexNum());
		
		graphA.addVertex(p3);
		assertEquals(3, graphA.getVertexNum());
		
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Existed vertex");
		graphA.addVertex(p3);
	}
	
	/**
	 * Test addEdge
	 * */
	@Test
	public void addEdgeTest() {
		FriendshipGraphA graphA = new FriendshipGraphA();
		PersonA p1 = new PersonA("Mike");
		PersonA p2 = new PersonA("John");
		PersonA p3 = new PersonA("Kiki");
		PersonA p4 = new PersonA("Dong");
		
		graphA.addVertex(p1);
		graphA.addVertex(p2);
		graphA.addVertex(p3);

		/* Xiao Chen's test case */

		PersonA mike = new PersonA("Mike");
		PersonA mike1 = new PersonA("Mike");
		PersonA bob = new PersonA("Bob");
		
		graphA.addVertex(bob);
		graphA.addEdge(mike, bob);

		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Duplicated edge");
		graphA.addEdge(mike1, bob);		/* this operation will should throw an exception */
		
		/* Test */
		assertEquals(0, graphA.getEdgeNum());
		
		graphA.addEdge(p3, p1);
		assertEquals(1, graphA.getEdgeNum());

		graphA.addEdge(p2, p1);
		assertEquals(2, graphA.getEdgeNum());

		graphA.addEdge(p1, p2);
		assertEquals(3, graphA.getEdgeNum());
		
		/* not exist vertex case */
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("srcA not existed in the graph");
		graphA.addEdge(p4, p2);
		assertEquals(3, graphA.getEdgeNum());

		/* duplicated edge case */
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Duplicated edge");
		graphA.addEdge(p1, p2);
		assertEquals(3, graphA.getEdgeNum());
	}
	
	// Test getDistance strategy
	// 	 src = null, not in the graph, in the graph
	//	 dst = null, not in the graph, in the graph
	//	 path = not exist, src == dst, single path, multiple paths

	@Test public void testGetDistanceNullPerson() {
		FriendshipGraphA graphA = new FriendshipGraphA();

		boolean flag = false;
		try {
			graphA.getDistance(null, null);
		} catch (IllegalArgumentException e) {
			flag = e.getMessage().equals("Null Person");
		} finally {
			assert flag;
		}
	}
	
	@Test public void testGetDistanceExcludedPerson1() {
		FriendshipGraphA graphA = new FriendshipGraphA();
		boolean flag = false;
		try {
			graphA.getDistance(new PersonA("Apple"), new PersonA("Grape"));
		} catch (IllegalArgumentException e) {
			flag = e.getMessage().equals("'Apple' not in the graph");
		} finally {
			assert flag;
		}
	}
	
	@Test public void testGetDistanceExcludedPerson2() {
		FriendshipGraphA graphA = new FriendshipGraphA();
		graphA.addVertex(new PersonA("Apple"));
		boolean flag = false;
		try {
			graphA.getDistance(new PersonA("Apple"), new PersonA("Grape"));
		} catch (IllegalArgumentException e) {
			flag = e.getMessage().equals("'Grape' not in the graph");
		} finally {
			assert flag;
		}
	}
	
	@Test public void testGetDistanceNoPath() {
		FriendshipGraphA graphA = new FriendshipGraphA();
		graphA.addVertex(new PersonA("Pen"));
		graphA.addVertex(new PersonA("Pencil"));
		assertEquals(-1, graphA.getDistance(new PersonA("Pen"), new PersonA("Pencil")));
	}
	
	@Test public void testGetDistanceOnePath() {
		FriendshipGraphA graphA = new FriendshipGraphA();
		graphA.addVertex(new PersonA("Pen"));
		graphA.addVertex(new PersonA("Pencil"));
		graphA.addVertex(new PersonA("Eraser"));
		graphA.addVertex(new PersonA("Ruler"));
		graphA.addEdge(new PersonA("Pen"), new PersonA("Pencil"));
		graphA.addEdge(new PersonA("Pencil"), new PersonA("Eraser"));
		graphA.addEdge(new PersonA("Pencil"), new PersonA("Ruler"));
		
		assertEquals(2, graphA.getDistance(new PersonA("Pen"), new PersonA("Eraser")));
	}
	
	@Test public void testGetDistanceMultiplePaths() {
		FriendshipGraphA graphA = new FriendshipGraphA();
		graphA.addVertex(new PersonA("A"));
		graphA.addVertex(new PersonA("B"));
		graphA.addVertex(new PersonA("C"));
		graphA.addVertex(new PersonA("D"));
		graphA.addVertex(new PersonA("E"));
		graphA.addVertex(new PersonA("F"));
		graphA.addEdge(new PersonA("A"), new PersonA("B"));
		graphA.addEdge(new PersonA("B"), new PersonA("C"));
		graphA.addEdge(new PersonA("C"), new PersonA("F"));
		graphA.addEdge(new PersonA("E"), new PersonA("D"));
		graphA.addEdge(new PersonA("B"), new PersonA("D"));
		graphA.addEdge(new PersonA("D"), new PersonA("C"));
		graphA.addEdge(new PersonA("A"), new PersonA("E"));
		graphA.addEdge(new PersonA("F"), new PersonA("B"));
		
		assertEquals(3, graphA.getDistance(new PersonA("A"), new PersonA("F")));
	}
}
