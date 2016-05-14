import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
*@author: Aastha Dixit
*
* This class computes the shortest path for DAG's for the given graph.
* Pre condition : Graph is acyclic. Otherwise output is arbitary 
*/
public class DAG {
/*
This method computes the shortest paths of all the vertices and prints them. 	
	
*/	public static void DAG_graph(Vertex s, Graph g) {
		reset(g);
		topological_Sort(g); //performs topological sort to get the order to process the vertices
		reset(g);
		g.verts.get(1).distance = 0;
		for (Vertex m : g.topological) {
			for (Edge e : m.Adj) {
				Vertex end = e.otherEnd(m);
				//relaxing the edges
				if (end.distance > m.distance + e.Weight) {
					end.distance = m.distance + e.Weight;
					end.parent = m;
				}
			}
		}
		printGraph(g);
	}


/*
This method prints the given graph input in the format specified as below.
Example. DAG <summation of the weight of the shortest paths>
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
			if (current.name != g.topological.getFirst().name) {
				u.distance = Integer.MIN_VALUE;
			} else {
				total += u.distance;
			}
		}
		System.out.print("DAG " + " " + total);
		System.out.println();
		if (g.verts.size() <= 101) {
			for (Vertex u : g) {
				
				String shortPath = u.distance != Integer.MIN_VALUE ? String.valueOf(u.distance) : "INF";
				String parent = u.parent == null ? "-" : u.parent.toString();
				System.out.print(u + " " + shortPath + " " + parent);
				System.out.println();
			}
		}
	}
	
/*	
*	This method takes the graph and resets all the vertices 
*
*
*/	
	public static void reset(Graph g) {
		for (Vertex u : g) {
			u.seen = false;
			u.parent = null;
			u.distance = Integer.MAX_VALUE;
		}
	}

	
/*	
*	This method computes the topological sort of the given graph and adds the sorted order
*   to the List (which stores topological order) in the graph class.
*
*/	
	public static void topological_Sort(Graph g) {
		setIndegree(g);
		Queue<Vertex> top = new LinkedList<>();
		for (Vertex initial : g) {
			if (initial.inDegree == 0)
				top.add(initial);
		}
		int find = 0;
		while (!top.isEmpty()) {
			Vertex u = top.poll();
			if (u == g.verts.get(1)) {
				find = 1;
			}
			if (find == 1)
				g.topological.addLast(u);
			for (Edge e : u.Adj) {
				Vertex v = e.otherEnd(u);
				v.inDegree--;
				if (v.inDegree == 0)
					top.add(v);
			}
		}
	}
/*
*This method sets the indegree of the graph based on the edges of the directed graph	
*	
*/	public static void setIndegree(Graph g) {
		for (Vertex v : g) {
			for (Edge e : v.revAdj) {
				v.inDegree++;
			}
		}
	}

	
}
