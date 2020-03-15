import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 Amazon wants to increse the reliability of its network and is piloting hardware
 enhancements in one of the data centers prior to a full scale
 roll out. To facilitate the routing of the incoming packets, there is a
 network of N routers in data center. Every router is connected to every other router
 of network either through a direct link or via some other router.

 To increase the fault tolerance of network. Amazon wants to identity routers which would result
 in disconnected networks if they went down.
 https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/

 */

public class NetworkReliabilityGraph {
    private static ArrayList<Integer> criticalRouter(int numRouters, int numLinks, ArrayList<ArrayList<Integer>> links) {
        Graph g1 = new Graph(numRouters + 1);
        for (ArrayList<Integer> integerArrayList : links) {
            g1.addEdge(integerArrayList.get(0), integerArrayList.get(1));
        }
        return g1.AP();
    }

    private static class Graph {
        private int V; // No. of vertices
        private LinkedList<Integer> adj[];
        int time = 0;
        static final int NIL = -1;

        // Constructor
        public Graph(int v) {
            V = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; ++i)
                adj[i] = new LinkedList();
        }

        //Function to add an edge into the graph
        public void addEdge(int v, int w) {
            adj[v].add(w); // Add w to v's list.
            adj[w].add(v); //Add v to w's list
        }

        public void APUtil(int u, boolean visited[], int disc[],
                           int low[], int parent[], boolean ap[]) {

            int children = 0;
            visited[u] = true;
            disc[u] = low[u] = ++time;
            Iterator<Integer> i = adj[u].iterator();
            while (i.hasNext()) {
                int v = i.next(); // v is current adjacent of u
                if (!visited[v]) {
                    children++;
                    parent[v] = u;
                    APUtil(v, visited, disc, low, parent, ap);
                    low[u] = Math.min(low[u], low[v]);
                    if (parent[u] == NIL && children > 1)
                        ap[u] = true;
                    if (parent[u] != NIL && low[v] >= disc[u])
                        ap[u] = true;
                } else if (v != parent[u])
                    low[u] = Math.min(low[u], disc[v]);
            }
        }

        private ArrayList<Integer> AP() {
            // Mark all the vertices as not visited
            ArrayList<Integer> output = new ArrayList<>();
            boolean visited[] = new boolean[V];
            int disc[] = new int[V];
            int low[] = new int[V];
            int parent[] = new int[V];
            boolean ap[] = new boolean[V]; // To store articulation points

            for (int i = 0; i < V; i++) {
                parent[i] = NIL;
                visited[i] = false;
                ap[i] = false;
            }

            for (int i = 0; i < V; i++)
                if (visited[i] == false)
                    APUtil(i, visited, disc, low, parent, ap);

            for (int i = 0; i < V; i++)
                if (ap[i] == true)
                    output.add(i);

            return output;
        }

    }

    public static void main(String args[]) {
        int numRouters = 7;
        int numLinks = 7;
        ArrayList<ArrayList<Integer>> links = new ArrayList<>();
        ArrayList<Integer> one = new ArrayList<>();
        one.add(1);
        one.add(2);
        links.add(one);
        one = new ArrayList<>();
        one.add(1);
        one.add(3);
        links.add(one);
        one = new ArrayList<>();
        one.add(2);
        one.add(4);
        links.add(one);
        one = new ArrayList<>();
        one.add(3);
        one.add(4);
        links.add(one);
        one = new ArrayList<>();
        one.add(3);
        one.add(6);
        links.add(one);
        one = new ArrayList<>();
        one.add(6);
        one.add(7);
        links.add(one);
        one = new ArrayList<>();
        one.add(4);
        one.add(5);
        links.add(one);
        // Create graphs given in above diagrams
        ArrayList<Integer> output = criticalRouter(numRouters, numLinks, links);
        System.out.println(output);
        numRouters = 6;
        numLinks = 5;
        links = new ArrayList<>();
        one = new ArrayList<>();
        one.add(1);
        one.add(2);
        links.add(one);
        one = new ArrayList<>();
        one.add(2);
        one.add(3);
        links.add(one);
        one = new ArrayList<>();
        one.add(3);
        one.add(4);
        links.add(one);
        one = new ArrayList<>();
        one.add(4);
        one.add(5);
        links.add(one);
        one = new ArrayList<>();
        one.add(6);
        one.add(3);
        links.add(one);
        // Create graphs given in above diagrams
        output  = criticalRouter(numRouters, numLinks, links);
        System.out.println(output);
    }


}
