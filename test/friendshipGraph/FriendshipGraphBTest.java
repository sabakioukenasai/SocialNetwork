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

		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Duplicated edge");
		graphB.addEdge(mike1, bob);		/* this operation will throw an exception */
		
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
	
	// Test getDistance strategy
		// 	 src = null, not in the graph, in the graph
		//	 dst = null, not in the graph, in the graph
		//	 path = not exist, src == dst, single path, multiple paths

		@Test public void testGetDistanceNullPerson() {
			FriendshipGraphB graphB = new FriendshipGraphB();

			boolean flag = false;
			try {
				graphB.getDistance(null, null);
			} catch (IllegalArgumentException e) {
				flag = e.getMessage().equals("Null Person");
			} finally {
				assert flag;
			}
		}
		
		@Test public void testGetDistanceExcludedPerson1() {
			FriendshipGraphB graphB = new FriendshipGraphB();
			boolean flag = false;
			try {
				graphB.getDistance(new PersonB("Apple"), new PersonB("Grape"));
			} catch (IllegalArgumentException e) {
				flag = e.getMessage().equals("'Apple' not in the graph");
			} finally {
				assert flag;
			}
		}
		
		@Test public void testGetDistanceExcludedPerson2() {
			FriendshipGraphB graphB = new FriendshipGraphB();
			graphB.addVertex(new PersonB("Apple"));
			boolean flag = false;
			try {
				graphB.getDistance(new PersonB("Apple"), new PersonB("Grape"));
			} catch (IllegalArgumentException e) {
				flag = e.getMessage().equals("'Grape' not in the graph");
			} finally {
				assert flag;
			}
		}
		
		@Test public void testGetDistanceNoPath() {
			FriendshipGraphB graphB = new FriendshipGraphB();
			graphB.addVertex(new PersonB("Pen"));
			graphB.addVertex(new PersonB("Pencil"));
			assertEquals(-1, graphB.getDistance(new PersonB("Pen"), new PersonB("Pencil")));
		}
		
		@Test public void testGetDistanceOnePath() {
			FriendshipGraphB graphB = new FriendshipGraphB();
			graphB.addVertex(new PersonB("Pen"));
			graphB.addVertex(new PersonB("Pencil"));
			graphB.addVertex(new PersonB("Eraser"));
			graphB.addVertex(new PersonB("Ruler"));
			graphB.addEdge(new PersonB("Pen"), new PersonB("Pencil"));
			graphB.addEdge(new PersonB("Pencil"), new PersonB("Eraser"));
			graphB.addEdge(new PersonB("Pencil"), new PersonB("Ruler"));
			
			assertEquals(2, graphB.getDistance(new PersonB("Pen"), new PersonB("Eraser")));
		}
		
		@Test public void testGetDistanceMultiplePaths() {
			FriendshipGraphB graphB = new FriendshipGraphB();
			graphB.addVertex(new PersonB("A"));
			graphB.addVertex(new PersonB("B"));
			graphB.addVertex(new PersonB("C"));
			graphB.addVertex(new PersonB("D"));
			graphB.addVertex(new PersonB("E"));
			graphB.addVertex(new PersonB("F"));
			graphB.addEdge(new PersonB("A"), new PersonB("B"));
			graphB.addEdge(new PersonB("B"), new PersonB("C"));
			graphB.addEdge(new PersonB("C"), new PersonB("F"));
			graphB.addEdge(new PersonB("E"), new PersonB("D"));
			graphB.addEdge(new PersonB("B"), new PersonB("D"));
			graphB.addEdge(new PersonB("D"), new PersonB("C"));
			graphB.addEdge(new PersonB("A"), new PersonB("E"));
			graphB.addEdge(new PersonB("F"), new PersonB("B"));
			
			assertEquals(3, graphB.getDistance(new PersonB("A"), new PersonB("F")));
		}
}
