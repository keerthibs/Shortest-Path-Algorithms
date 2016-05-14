import java.util.LinkedList;
import java.util.Queue;
/*
@author Keerthi Bala Sundram
This class does the Bellman Ford algorithm and finds the shortest paths from the root to the other nodes.
* Date : 03/27/2016
*/
public class BellmanFord {
/*
This method finds the shortest path of the given graph g by incorporating the 
take 3(faster algorithm) of Bellman-Ford algorithm.

	@param g: Graph for which the shortest path has to be found
*/	
	static void shortestPath(Graph g) {

		// Queue to hold the vertices
		Queue<Vertex> queue = new LinkedList<>();

		//Below for loop initializes the vertices
		for (Vertex u : g.verts) {
			u.distance = Integer.MAX_VALUE;
			u.parent = null;
			u.count = 0;
			u.seen = false;
		}

		Vertex root = g.verts.get(1);
		root.distance = 0;
		root.seen = true;
		queue.add(root);

		while (!queue.isEmpty()) {
			Vertex u = queue.remove();
			u.seen = false;
			u.count = u.count + 1;
			if (u.count >= g.verts.size() - 1) {
				System.out.println("Unable to solve problem. Graph has a negative cycle");
				break;
			}

			for (Edge e : u.Adj) {
				Vertex v = e.otherEnd(u);
				
				//relaxing of edges
				int temp = u.distance + e.Weight;
				if (v.distance > temp) {
					v.distance = temp;
					v.parent = u;
					if (!v.seen) {
						queue.add(v);
						v.seen = true;
					}
				}

			}

		}

		printGraph(g);
	}

/*	
This method prints the given graph input in the format specified as below.
Example. B-F <summation of the weight of the shortest paths>
Vertex shortestPathDistance parent

	@param g: Graph that has to be printed in the format as above.
	
*/	
	static void printGraph(Graph g) {

		int total = 0;

		for (Vertex u : g) {

			Vertex current = u;

			while (current.parent != null) {
				current = current.parent;
			}
			if (current.name != g.verts.get(1).name) {
				u.distance = -1;
			} else {
				total += u.distance;
			}

		}
		System.out.print("B-F " + " " + total);

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
