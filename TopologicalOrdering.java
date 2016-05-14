
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class that contains two methods that perform Topological Ordering of a given graph
 *
 * @author monishkumar
 *
 */

public class TopologicalOrdering {
    /**
     * @function TopologicalOrdering1
     *
     * @param G
     *
     * @return List of Vertices
     *
     * @description Takes a Graph and returns the list of vertices in topological order if it is a DAG
     *              else returns null
     *
     */
    public static List<Vertex> TopologicalOrdering1(Graph G) {
        List<Vertex> topOrder = new ArrayList();
        Queue<Vertex> vertexQueue = new LinkedList<>();
        int top = 1;
        Vertex u, k;
        for(Vertex v : G) {
            if (v.getInDegree() == 0) {
                vertexQueue.add(v);
            }
        }
        while(!vertexQueue.isEmpty()) {
            u = vertexQueue.remove();
            u.setTopologicalOrder( top++);
            topOrder.add(u);
            for(Edge e : u.Adj) {
                k = e.otherEnd(u);
                
                k.setInDegree(k.getInDegree()-1);
                if(k.getInDegree() == 0)
                    vertexQueue.add(k);
            }
        }

        for(Vertex j : G) {
            if(j.getTopologicalOrder() == 0) {
                System.out.println("Graph is not a DAG");
                return null;
            }
        }
        return topOrder;
    }

    /**
     * @function TopologicalOrdering2
     *
     * @param G
     *
     * @return Stack of Vertices
     *
     * @description Takes a Graph and returns a true if it is a DAG else returns false
     *
     */
    static Stack<Vertex> topOrder; 
    public static boolean TopologicalOrdering2(Graph G) {
       
    	topOrder = new Stack<>();
        for(Vertex u : G) {
            u.seen = false;
            u.parent = null;
        }


        boolean inProgress[] = new boolean[G.numNodes + 1];
        for(Vertex u : G) {
            if(!u.seen)
                if(!DFSVisit(u, topOrder, inProgress))
                    return false;
        }

        return true;
    }

    /**
     * @function DFSVisit
     *
     * @param u
     *          Vertex being visited
     *
     * @param S
     *          Stack of Vertices to push the vertex after visiting
     *
     * @return Stack of Vertices
     *
     * @description Part of the DFS algorithm
     *
     */
    public static boolean DFSVisit(Vertex u, Stack<Vertex> S, boolean isProgress[]) {
        u.seen = true;
        isProgress[u.name] = true;

        for(Edge e : u.Adj) {
            Vertex v = e.otherEnd(u);
            if(!v.seen) {
                v.parent = u;
                if (!DFSVisit(v, S, isProgress))
                    return false;
            }
            else if(isProgress[v.name]){
                //System.out.println("Graph has a Cycle");
                return false;
            }
        }

        S.push(u);
        isProgress[u.name] = false;
        return true;
    }
/*
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            input = new Scanner(inputFile);
        } else {
            input = new Scanner(System.in);
        }
        Graph G = Graph.readGraph(input, true);

        List<Vertex> TopOrder1 = TopologicalOrdering.TopologicalOrdering1(G);
        Stack<Vertex> TopOrder2 = TopologicalOrdering.TopologicalOrdering2(G);

        System.out.println("Topological Sort 1");
        System.out.println(TopOrder1);

        System.out.println("\nTopological Sort 2 (Reverse Stack Order)");
        System.out.println(TopOrder2);

    }
*/}
