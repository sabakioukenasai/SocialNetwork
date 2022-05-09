package friendshipGraph;

import java.util.HashSet;
import java.util.Set;

import person.PersonA;

/**
 * Graph model which can be adapted to the implementation of {@code PersonA}.
 * 
 * <p>Implemented methods {@code getVertexNum}, {@code getEdgeNum}, 
 * {@code addVertex}, {@code addEdge} and {@code getDistance}</p>
 * */
public class FriendshipGraphA {

	/* Private fields */
	private Set<PersonA> vertexes = new HashSet<>();
	
	/* Public methods */
	/**
	 * Constructor that does nothing
	 * */
	public FriendshipGraphA() {
		return;
	}
	
	/**
     * Returns the number of vertexes in this graph.  If this
     * graph contains more than {@code Integer.MAX_VALUE} vertexes, returns
     * {@code Integer.MAX_VALUE}.
     * 
     * @return the number of vertexes in the graph.
	 * */
	public int getVertexNum() {		/* Assuming that this method is reliable */
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
		for (PersonA pA : vertexes) {
			temp = sum + pA.knowsNum();
			if (temp >= 0) {
				sum = temp;
			}
			else {
				return Integer.MAX_VALUE;
			}
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
	public void addVertex(PersonA pA) {
//		if (vertexes.contains(pA))
//			throw new IllegalArgumentException("Existed vertex");
		for (PersonA p : vertexes) {
			if (p.getName() == pA.getName())
				throw new IllegalArgumentException("Existed vertex");
		}
		vertexes.add(pA);
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
	public void addEdge(PersonA srcA, PersonA dstA){
		boolean flag = false;
		for (PersonA p : vertexes) {
			if (p.getName() == srcA.getName()) {
				flag = true;
				break;
			}
		}
		if (!flag)
//		if (!vertexes.contains(srcA))
			throw new IllegalArgumentException("srcA not existed in the graph");
		flag = false;
		for (PersonA p : vertexes) {
			if (p.getName() == dstA.getName()) {
				flag = true;
				break;
			}
		}
		if (!flag)
//		if (!vertexes.contains(dstA))
			throw new IllegalArgumentException("dstA not existed in the graph");
		
		if (srcA.isKnows(dstA))
			throw new IllegalArgumentException("Duplicated edge");
		else 
			srcA.addKnows(dstA);
	}
	
	/**
	 * Calculate the length of the shortest path from node srcA to node srcB
	 * */
	public int getDistance(PersonA srcA, PersonA dstA) {
		throw new IllegalArgumentException("Implementme");
	}
}

















