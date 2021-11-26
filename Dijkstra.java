import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Provides access to Dijkstra's algorithm for a weighted graph.
 */
final public class Dijkstra {
    private Dijkstra() {}

    /**
     * Computes the shortest path between two nodes in a weighted graph.
     * Input graph is guaranteed to be valid and have no negative-weighted edges.
     *
     * @param g   the weighted graph to compute the shortest path on
     * @param src the source node
     * @param tgt the target node
     * @return an empty list if there is no path from {@param src} to {@param tgt}, otherwise an
     * ordered list of vertices in the shortest path from {@param src} to {@param tgt},
     * with the first element being {@param src} and the last element being {@param tgt}.
     */
    public static List<Integer> getShortestPath(Graph g, int src, int tgt) {
        if (src == tgt) {
            LinkedList<Integer> output = new LinkedList<>();
            output.add(src);
            return output;
        }
        int n = g.getSize();
        int[] distance = new int[n];
        Integer[] parent = new Integer[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        BinaryMinHeap<Integer, Integer> heap = new BinaryMinHeapImpl<>();
        distance[src] = 0;
        heap.add(0, src);
        while (!heap.isEmpty()) {
            int u = heap.extractMin().value;
            Set<Integer> set = g.outNeighbors(u);
            for (int next : set) {
                if (g.getWeight(u, next) + distance[u] < distance[next]) {
                    parent[next] = u;
                    distance[next] = g.getWeight(u, next) + distance[u];
                    if (heap.containsValue(next)) {
                        heap.decreaseKey(next, distance[next]);
                    } else {
                        heap.add(distance[next], next);
                    }
                }
            }
        }
        LinkedList<Integer> output = new LinkedList<>();
        if (parent[tgt] != null) {
            int current = tgt;
            output.addFirst(current);
            while (parent[current] != null) {
                current = parent[current];
                output.addFirst(current);
            }
        }
        return output;
    }
}
