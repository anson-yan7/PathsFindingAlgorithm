import java.util.LinkedList;
import java.util.Set;

final public class IslandBridge {
    private IslandBridge() {}

    /**
     * See question details in the write-up. Input is guaranteed to be valid.
     *
     * @param g the input graph representing the islands as vertices and bridges as directed edges
     * @param x the starting node
     * @return true, if no matter how you navigate through the one-way bridges from node x,
     * there is always a way to drive back to node x, and false otherwise.
     * @implSpec This method should run in worst-case O(n + m) time.
     */
    public static boolean allNavigable(Graph g, int x) {
        int size = g.getSize();
        Graph reversed = new Graph(size);
        for (int i = 0; i < size; i++) {
            Set<Integer> s = g.outNeighbors(i);
            for (Integer j : s) {
                reversed.addEdge(j, i, 0);
            }
        }
        LinkedList<Integer> visited = new LinkedList<>();
        boolean[] discovered = new boolean[size];
        ResizingDeque<Integer> queue = new ResizingDequeImpl<>();
        queue.addLast(x);
        discovered[x] = true;
        visited.add(x);
        while (queue.size() != 0) {
            Integer current = queue.pollFirst();
            Set<Integer> set = g.outNeighbors(current);
            for (Integer next : set) {
                if (!discovered[next]) {
                    visited.add(next);
                    discovered[next] = true;
                    queue.addLast(next);
                }
            }
        }
        boolean[] discoveredReversed = new boolean[size];
        ResizingDeque<Integer> queueReversed = new ResizingDequeImpl<>();
        queueReversed.addLast(x);
        discoveredReversed[x] = true;
        while (queueReversed.size() != 0) {
            Integer current = queueReversed.pollFirst();
            Set<Integer> set = reversed.outNeighbors(current);
            for (Integer next : set) {
                if (!discoveredReversed[next]) {
                    discoveredReversed[next] = true;
                    queueReversed.addLast(next);
                }
            }
        }

        for (int k = 0; k < visited.size(); k++) {
            if (!discoveredReversed[visited.get(k)]) {
                return false;
            }
        }
        return true;
    }
}
