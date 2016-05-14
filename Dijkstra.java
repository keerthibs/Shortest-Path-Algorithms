import java.util.*;
/*
*This class computes the Dijkstras Algorithm for computing the shortest path for a
* Directed graph.
* 
*/
public class Dijkstra {
	static Vertex first;

	public static void findShortestPath(Graph g) {
		//Indexed heap that holds the graph i.e set of vertices in the graph. Indexed Heap
		MinBinaryHeap hp = new MinBinaryHeap(g);
		
		//initialize all the vertex
		for (Vertex u : g) {
			u.seen = false;
			u.distance = Integer.MAX_VALUE;
			u.parent = null;
		}

		
		
		first = hp.getFirst();
		first.distance=0;
		
		//Process relaxing edges
		while (!hp.isEmpty()) {
			Vertex u = hp.removeMin();
			
			u.seen = true;

			for (Edge e : u.Adj) {
				Vertex v = e.otherEnd(u);
				int tempDistance = u.distance + e.Weight;
				//relaxing the edges 
				if (v.distance > tempDistance && !v.seen) {
					v.distance = tempDistance;
					v.parent = u;
					hp.decreaseKey(v, tempDistance);
				}
			}

		}

		printGraph(g); 
	}
	
/*
This method prints the given graph input in the format specified as below.
Example. DIJ <summation of the weight of the shortest paths>
Vertex shortestPathDistance parent

@param g: Graph that has to be printed in the format as above.
	
	

*/	static public void printGraph(Graph g) {

		int total = 0;

		for (Vertex u : g) {

			Vertex current = u;

			while (current.parent != null) {
				current = current.parent;
			}
			if (current.name != first.name) {
				u.distance = -1;
			} else {
				total += u.distance;
			}

		}
		System.out.print("DIJ " + " " + total);

		System.out.println();
		if (g.verts.size() <= 101) {
			for (Vertex u : g) {

				String shortPath = u.distance != -1 ? String.valueOf(u.distance) : "INF";
				String parent = u.parent == null ? "-" : u.parent.toString();
				System.out.print(u + " " + shortPath + " " + parent);
				System.out.println();
			}
		}
	}

}
