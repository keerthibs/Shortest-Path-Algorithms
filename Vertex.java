
/**
 * Class to represent a vertex of a graph
 * 
 *
 */

import java.util.*;

public class Vertex implements Comparable<Vertex>,Comparator<Vertex> {
	public int name; // name of the vertex
	public boolean seen; // flag to check if the vertex has already been visited
	public Vertex parent; // parent of the vertex
	public int distance; // distance to the vertex from the source vertex
	public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or//
									// ArrayList
	public int index;
	private int topologicalOrder;
	//public boolean isInfinite;
	public int count;
	public int inDegree;

	public int getInDegree() {
		return inDegree;
	}

	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}

	public void putIndex(int index) {

		this.index = index;

	}

	public int getIndex() {

		return this.index;

	}

	public int getTopologicalOrder() {
		return topologicalOrder;
	}

	public void setTopologicalOrder(int topologicalOrder) {
		this.topologicalOrder = topologicalOrder;
	}

	/**
	 * Constructor for the vertex
	 * 
	 * @param n
	 *            : int - name of the vertex
	 */
	Vertex(int n) {
		name = n;
		distance = 0;
		seen = false;
		//isInfinite = false;
		parent = null;
		Adj = new ArrayList<Edge>();
		revAdj = new ArrayList<Edge>(); /* only for directed graphs */
	}

	@Override
	public int compareTo(Vertex o2) {
		if (this.distance < o2.distance)
			return -1;
		else if (this.distance > o2.distance)
			return 1;
		else
			return 0;
	}

	@Override
	public int compare(Vertex o1,Vertex o2) {
		if (o1.distance < o2.distance)
			return -1;
		else if (o1.distance > o2.distance)
			return 1;
		else
			return 0;
	}
	
	/**
	 * Method to represent a vertex by its name
	 */
	public String toString() {
		return Integer.toString(name);
	}
}