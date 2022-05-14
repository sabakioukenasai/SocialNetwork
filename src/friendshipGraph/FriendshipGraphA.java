package friendshipGraph;

import java.util.HashSet;
import java.util.Iterator;
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
	private final Set<PersonA> vertexes = new HashSet<>();
	
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
		if (vertexes.contains(pA))
			throw new IllegalArgumentException("Existed vertex");
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
		if (!vertexes.contains(dstA))
			throw new IllegalArgumentException("dstA not existed in the graph");
		
		PersonA src = null;			/* Find the certain instance stored in Vertexes */
		for (PersonA item : vertexes)
			if (item.equals(srcA))
				src = item;
		if (src == null)			/* NotFound throw exception */
			throw new IllegalArgumentException("srcA not existed in the graph");
		
		if (src.isKnows(dstA))		/* Check if duplicate edges exist */
			throw new IllegalArgumentException("Duplicated edge");
		else 
			src.addKnows(dstA);
	}
	
	/**
	 * Calculate the length of the shortest path from node src to node dst
	 * 
	 * @param srcA source of the path
	 * @param dstA destination of the path
	 * @return length of the path if existed, 0 if srcA == dstA, else -1.
	 * @return IllegalArgument if srcA or srcB is null or not existed in the graph
	 * */
	public int getDistance(PersonA srcA, PersonA dstA) {
		if (srcA == null || dstA == null)
			throw new IllegalArgumentException("Null Person");
		if (!vertexes.contains(srcA))
			throw new IllegalArgumentException("'"+srcA.getName()+"' not in the graph");
		if (!vertexes.contains(dstA))
			throw new IllegalArgumentException("'"+dstA.getName()+"' not in the graph");
		
		if (srcA.equals(dstA))		/* when src == dst return 0 */
			return 0;
		
		int dis = 0;
		Set<PersonA> unvisited = new HashSet<>(vertexes);	/* unvisited nodes */
		Set<PersonA> preAs = new HashSet<>();
		Set<PersonA> nowAs = new HashSet<>();

		unvisited.remove(srcA);
		for (PersonA tempA : vertexes) {
			if (tempA.equals(srcA)) {
				preAs.add(tempA);
				break;
			}
		}
		
		boolean find = false;						// not find dstA
		
		FINDER:
		while (!preAs.isEmpty()) {					// while we have new vertexes to visit
			++dis;
			for (PersonA item : preAs) {			// start from each vertex
				Iterator<PersonA> ite = unvisited.iterator();
				while(ite.hasNext()) {
					PersonA unv = ite.next();
					if (item.isKnows(unv)) {
						ite.remove();
						nowAs.add(unv);
						if (unv.equals(dstA)) {
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
}

















