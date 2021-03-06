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
	private final Set<PersonB> vertexes = new HashSet<>();
	private final Map<PersonB, Set<PersonB>> edges = new HashMap<>();
	
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
	 * @param pB the new vertex to be added into the graph
	 * @exception IllegalArgumentException if the new vertex has
	 * the same name with some vertex in the graph
	 * */
	public void addVertex(PersonB pB) {
		if (vertexes.contains(pB))
			throw new IllegalArgumentException("Existed vertex");	
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
		if (!vertexes.contains(srcB))
			throw new IllegalArgumentException("srcB not existed in the graph");
		if (!vertexes.contains(dstB))
			throw new IllegalArgumentException("dstB not existed in the graph");
		
		if (edges.get(srcB).contains(dstB))		// Check if duplicate edges exist
			throw new IllegalArgumentException("Duplicated edge");
		else
			edges.get(srcB).add(dstB);
		
	}
	
	/**
	 * Calculate the length of the shortest path from node src to node dst
	 * 
	 * @param srcB source of the path
	 * @param dstB destination of the path
	 * @return length of the path if existed, 0 if srcA == dstA, else -1.
	 * @return IllegalArgument if srcA or srcB is null or not existed in the graph
	 * */
	public int getDistance(PersonB srcB, PersonB dstB) {
		if (srcB == null || dstB == null)
			throw new IllegalArgumentException("Null Person");
		if (!vertexes.contains(srcB))
			throw new IllegalArgumentException("'"+srcB.getName()+"' not in the graph");
		if (!vertexes.contains(dstB))
			throw new IllegalArgumentException("'"+dstB.getName()+"' not in the graph");
		
		if (srcB.equals(dstB))		/* when src == dst return 0 */
			return 0;
		
		int dis = 0;
		Set<PersonB> unvisited = new HashSet<>(vertexes);	/* unvisited nodes */
		Set<PersonB> preAs = new HashSet<>();
		Set<PersonB> nowAs = new HashSet<>();

		unvisited.remove(srcB);
		preAs.add(srcB);
		
		boolean find = false;						// not find dstA
		
		FINDER:
		while (!preAs.isEmpty()) {					// while we have new vertexes to visit
			++dis;
			for (PersonB item : preAs) {
				for (PersonB dst : edges.get(item)) {
					if (unvisited.remove(dst)) {
						nowAs.add(dst);
						if (dst.equals(dstB)) {
							find = true;
							break FINDER;
						}
					}
				}
			}
			preAs.clear();
			preAs.addAll(nowAs);
			nowAs.clear();
		}
		if (!find)
			return -1;
		return dis;	
	}
	
	public static void main(String[] args) {
		FriendshipGraphB graphB = new FriendshipGraphB();
		PersonB mike = new PersonB("Mike");
		PersonB bob = new PersonB("Bob");
		PersonB davinci = new PersonB("Davinci");
		
		graphB.addVertex(mike);
		graphB.addEdge(mike, bob);

		graphB.addEdge(mike, davinci);		/* this operation will throw an exception */
		System.out.println();
	}
}
