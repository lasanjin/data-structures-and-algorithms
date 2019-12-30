import java.util.*;

public class DirectedGraph<E extends Edge> {
    private List<E>[] edges;
    private int nEdge;

    public DirectedGraph(int noOfNodes) {
        nEdge = noOfNodes + 1;// Finns ingen nod 0. Detta löser problemet.
        edges = new List[nEdge];
        fillList();
    }

    private void fillList() {
        for (int i = 0; i < nEdge; i++) {
            edges[i] = new ArrayList<>();
        }
    }

    public void addEdge(E edge) {
        if (edge == null) {
            throw new NullPointerException();
        }
        if (edge.from < 0 && edge.from <= nEdge) {
            throw new IndexOutOfBoundsException();
        }
        if (edges[edge.from].indexOf(edge) != -1) {

            //TODO Fråga om detta vid nästa labb-tillfälle

            System.out.println(edge + " is a added more than one time");
        }

        edges[edge.from].add(edge);
    }

    /**
     * @param from origin
     * @param to   destination
     * @return Djikstra path, otherwise null (if there no path).
     */
    public Iterator<E> shortestPath(int from, int to) {
        return (new DijkstraPathBuilder(edges, to, from)).getDijkstraPath().iterator();
    }

    /**
     * @return Kruskal graph
     */
    public Iterator<E> minimumSpanningTree() {
        return KruskalGraphBuilder.getKruskalGraph(nEdge, edges).iterator();
    }
}