package friendshipGraph;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import person.PersonB;

public class FriendshipGraphBTest {

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
		FriendshipGraphB graphB = new FriendshipGraphB();
		PersonB p1 = new PersonB("Mike");
		PersonB p2 = new PersonB("John");
		PersonB p3 = new PersonB("Kiki");

		assertEquals(0, graphB.getVertexNum());
		
		graphB.addVertex(p1);
		assertEquals(1, graphB.getVertexNum());

		graphB.addVertex(p2);
		assertEquals(2, graphB.getVertexNum());
		
		graphB.addVertex(p3);
		assertEquals(3, graphB.getVertexNum());
		
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Existed vertex");
		graphB.addVertex(p3);
	}
	
	/**
	 * Test addEdge
	 * */
	@Test
	public void addEdgeTest() {
		FriendshipGraphB graphB = new FriendshipGraphB();
		PersonB p1 = new PersonB("Mike");
		PersonB p2 = new PersonB("John");
		PersonB p3 = new PersonB("Kiki");
		PersonB p4 = new PersonB("Dong");
		
		graphB.addVertex(p1);
		graphB.addVertex(p2);
		graphB.addVertex(p3);
		
		/* Xiao Chen's test case */

		PersonB mike = new PersonB("Mike");
		PersonB mike1 = new PersonB("Mike");
		PersonB bob = new PersonB("Bob");
		
		graphB.addVertex(bob);
		graphB.addEdge(mike, bob);

		graphB.addEdge(mike1, bob);		/* this operation should throw an exception, but not */
		
		/* Test */
		assertEquals(0, graphB.getEdgeNum());
		
		graphB.addEdge(p3, p1);
		assertEquals(1, graphB.getEdgeNum());

		graphB.addEdge(p2, p1);
		assertEquals(2, graphB.getEdgeNum());

		graphB.addEdge(p1, p2);
		assertEquals(3, graphB.getEdgeNum());
		
		/* not exist vertex case */
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("srcB not existed in the graph");
		graphB.addEdge(p4, p2);
		assertEquals(3, graphB.getEdgeNum());

		/* duplicate edge case */
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Duplicated edge");
		graphB.addEdge(p1, p2);
		assertEquals(3, graphB.getEdgeNum());
	}
	
	/**
	 * Test getDistance
	 * */
	@Test
	public void getDistanceTest() {
		assert true;
	}
}
