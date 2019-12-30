import java.util.*;

public class DijkstraPathBuilder<E extends Edge> {
    private PriorityQueue<DijkstraPath> pq;
    private List<E>[] edges;
    private boolean[] visited;
    private int to, from;

    public DijkstraPathBuilder(List<E>[] edges, int to, int from) {
        this.edges = edges;
        this.to = to;
        this.from = from;
        visited = new boolean[edges.length];
        pq = new PriorityQueue<>();
    }

    public ArrayList<E> getDijkstraPath() {
        List<E> currentEdges; // Lista som innehåller current nodes edges.
        pq.add(new DijkstraPath(from, 0, new ArrayList<>()));// Första elementet i PQ.

        while (!pq.isEmpty()) {
            DijkstraPath current = pq.poll();

            if (current.to == to) { // Färdiga!
                return current.path;
            }
            if (visited[current.to]) { // Om besökt så polla ny och börja om.
                continue;
            }

            visited[current.to] = true; // Nod är nu besökt.

            currentEdges = edges[current.to]; // Hämta grannar.
            for (E edge : currentEdges) {
                if (!visited[edge.to]) { // Om nod besökt behöver inget göras.

                    ArrayList<E> newPath = new ArrayList<>(current.path);
                    newPath.add(edge);
                    double newWeight = current.cost + edge.getWeight();

                    pq.add(new DijkstraPath(edge.to, newWeight, newPath)); // Nytt köelement.
                }
            }
        }

        return null;// Detta borde aldrig ske om det finns en väg.
    }

    private class DijkstraPath implements Comparable {
        private int to;
        private double cost;
        private ArrayList<E> path;

        private DijkstraPath(int to, double cost, ArrayList<E> path) {
            this.to = to;
            this.cost = cost;
            this.path = path;
        }

        @Override
        public int compareTo(Object o) {
            return Double.compare(cost, ((DijkstraPath) o).cost);
        }
    }
}