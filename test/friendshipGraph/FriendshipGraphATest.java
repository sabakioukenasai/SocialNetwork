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

		graphA.addEdge(mike1, bob);		/* this operation should throw an exception, but not */
		
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
	
	/**
	 * Test getDistance
	 * */
	@Test
	public void getDistanceTest() {
		assert true;
	}
}
