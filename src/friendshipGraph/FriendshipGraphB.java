package friendshipGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import person.PersonB;

/**
 * Graph model which can be adapted to the implementation of {@code PersonB}.
 * 
 * <p>Implemented methods {@code getVertexNum}, {@code getEdgeNum}, 
 * {@code addVertex}, {@code addEdge} and {@code getDistance}</p>
 * */
public class FriendshipGraphB {
	
	/* Private fields */
	Set<PersonB> vertexes = new HashSet<>();
	Map<PersonB, Set<PersonB>> edges = new HashMap<>();
	
	/* Public methods */
	/**
	 * Consturctor that does nothing
	 * */
	public FriendshipGraphB() {
		return;
	}
	
	/**
     * Returns the number of vertexes in this graph.  If this
     * graph contains more than {@code Integer.MAX_VALUE} vertexes, returns
     * {@code Integer.MAX_VALUE}.
     * 
     * @return the number of vertexes in the graph.
	 * */
	public int getVertexNum() {
		return vertexes.size();
	}
	
	/**
     * Returns the number of edges in this graph.  If this graph 
     * contains more than {@code Integer.MAX_VALUE} edges, returns
     * {@code Integer.MAX_VALUE}.
     * 
     * @return the number of edges in the graph.
	 * */
	public int getEdgeNum() {
		int sum = 0, temp = 0;
		for (PersonB pB : vertexes) {
			temp = sum + edges.get(pB).size();
			if (temp >= 0)
				sum = temp;
			else 
				return Integer.MAX_VALUE;
		}
		return sum;
	}
	
	/**
	 * Add a new vertex into the graph.
	 * <p><strong>Requires</strong>: The new vertex must not have the 
	 * same name with any existed vertex in the graph.</p>
	 * 
	 * @param the new vertex to be added into the graph
	 * @exception IllegalArgumentException if the new vertex has
	 * the same name with some vertex in the graph
	 * */
	public void addVertex(PersonB pB) {
		for (PersonB p : vertexes) {
			if (p.getName() == pB.getName())
				throw new IllegalArgumentException("Existed vertex");
		}
//		if (vertexes.contains(pB))
//			throw new IllegalArgumentException("Existed vertex");	
		vertexes.add(pB);
		edges.put(pB, new HashSet<>());
	}
	
	/**
	 * <p>Add a new edge into the graph, which represents one person knows another.</p>
	 * 
	 * <p>Obviously, these two vertex should be already in the graph.
	 * And the edge should have not existed in the graph. </p>
	 * 
	 * @param srcA the source of the edge.
	 * @param dstA the destination of the edge.
	 * @exception IllegalArgumentException if srcA or dstA does not exist in the graph
	 * @exception IllegalArgumentException if edge <srcA, dstA> has already existed in the graph
	 * */
	public void addEdge(PersonB srcB, PersonB dstB) {
		boolean flag = false;
		for (PersonB p : vertexes) {
			if (p.getName() == srcB.getName()) {
				flag = true;
				break;
			}
		}
		if (!flag)
//		if (!vertexes.contains(srcB))
			throw new IllegalArgumentException("srcB not existed in the graph");
		flag = false;
		for (PersonB p : vertexes) {
			if (p.getName() == dstB.getName()) {
				flag = true;
				break;
			}
		}
		if (!flag)
//		if (!vertexes.contains(dstB))				// As we guarantee each key has its value.
			throw new IllegalArgumentException("dstB not existed in the graph");
		
		if (!edges.containsKey(srcB)) {		// 如果字典中不包含键值 src
			Set<PersonB> knows = new HashSet<>();	// 新建一项加入到字典中
			knows.add(dstB);
			edges.put(srcB, knows);
		}
		else {
			if (edges.get(srcB).contains(dstB))		// Check if duplicate edges exist
				throw new IllegalArgumentException("There must not be double edges in graph");
			else									// 
				edges.get(srcB).add(dstB);
		}
	}
	
	/**
	 * Calculate the length of the shortest path from node srcA to node srcB
	 * */
	public int getDistance(PersonB srcB, PersonB dstB) {
		throw new IllegalArgumentException("Implementme");
	}
	
	public static void main(String[] args) {
		FriendshipGraphB graphB = new FriendshipGraphB();
		PersonB mike = new PersonB("Mike");
		PersonB bob = new PersonB("Bob");
		PersonB davinci = new PersonB("Davinci");
		
		graphB.addVertex(mike);
		graphB.addEdge(mike, bob);

		graphB.addEdge(mike, davinci);		/* this operation should throw an exception, but not */
		System.out.println();
	}
}
