import java.util.TreeSet;

/**
 * Class that calls for appropriate shortest path algorithm based on the type of
 * directed graph given as input.
 *
 * @author Keerthi Bala Sundram
 *
 */

public class ShortestPath {
	/*
	 * Method that takes graph g as input and redirects the program flow to an
	 * appropriate shortest path algorithm.
	 * 
	 * @param g: The graph, for which the shortest path has to be determined
	 */
	static void find(Graph g) {
		int choice = 0;

		// HashSet that will help to find the condition for determining the
		// shortest path algo

		TreeSet<Integer> set = Graph.setOfEdgeWeights;
		if (set.size() == 1) {

			choice = 1;
		} else {
			boolean value = TopologicalOrdering.TopologicalOrdering2(g);

			if (value) {

				choice = 2; // DAG

			} else {

				if (set.iterator().next().intValue() >= 0) {
					choice = 3; // Dijkstras
				} else
					choice = 4; // Belman Ford

			}
		}
		switch (choice) {

		case 1:
			BFS sp = new BFS();
			sp.BFS(g);
			break;
		case 2:
			DAG.DAG_graph(g.verts.get(1), g);
			break;
		case 3:
			System.out.println("Diksjtras");
			Dijkstra.findShortestPath(g);
		
			break;
		case 4:
			BellmanFord.shortestPath(g);
			break;

		}
	}
}
