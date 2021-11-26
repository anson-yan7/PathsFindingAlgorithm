import java.util.*;

/**
 * Returns a widest path between two vertices in an undirected graph. A widest path between two
 * vertices maximizes the weight of the minimum-weight edge in the path.
 * <p/>
 * There are multiple ways to solve this problem. The following algorithms may be helpful:
 * - Kruskal's algorithm using Union Find, or
 * - Prim's algorithm using Binary Min Heap (Priority Queue)
 * Feel free to use any previous algorithms that you have already implemented.
 */
public final class WidestPath {
    private WidestPath() {}

    /**
     * Computes a widest path from {@param src} to {@param tgt} for an undirected graph.
     * If there are multiple widest paths, this method may return any one of them.
     * Input {@param g} guaranteed to be undirected.
     * Input {@param src} and {@param tgt} are guaranteed to be valid and in-bounds.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param g   the graph
     * @param src the vertex from which to start the search
     * @param tgt the vertex to find via {@code src}
     * @return an ordered list of vertices on a widest path from {@code src} to {@code tgt}, or an
     * empty list if there is no such path. The first element is {@code src} and the last
     * element is {@code tgt}. If {@code src == tgt}, a list containing just that element is
     * returned.
     * @implSpec This method should run in worst-case O((n + m) log n) time.
     */

    public static List<Integer> getWidestPath(Graph g, int src, int tgt) {
        if (src == tgt) {
            LinkedList<Integer> output = new LinkedList<>();
            output.add(src);
            return output;
        }
        int n = g.getSize();
        Graph graph = new Graph(n);
        for (int i = 0; i < n; i++) {
            Set<Integer> set = g.outNeighbors(i);
            for (int next : set) {
                graph.addEdge(i, next, g.getWeight(i, next));
            }
        }
//        int[] distance = new int[n];
//        Integer[] parent = new Integer[n];
//        for (int i = 0; i < n; i++) {
//            distance[i] = Integer.MAX_VALUE;
//        }
        BinaryMinHeap<Integer, Node<Integer>> heap = new BinaryMinHeapImpl<>();
        ResizingDeque<Integer> queue = new ResizingDequeImpl<>();
        queue.addLast(src);
        UnionFind<Integer> uf = new UnionFind<>();
        for (int i = 0; i < n; i++) {
            uf.makeSet(i);
        }
        while (queue.size() != 0) {
            Integer curr = queue.pollFirst();
            Set<Integer> set = graph.outNeighbors(curr);
            for (Integer value : set) {
                heap.add(-1 * graph.getWeight(curr, value),
                        new WidestPath.Node<Integer>(curr, value));
                graph.removeEdge(value, curr);
                queue.addLast(value);
            }
        }
        Graph tree = new Graph(n);
        while (!heap.isEmpty()) {
            Node<Integer> curr = heap.extractMin().value;
            int first = curr.getValue();
            int second = curr.getSecond();
            if (!uf.find(first).equals(uf.find(second))) {
                uf.merge(first, second);
                tree.addEdge(first, second, 0);
                tree.addEdge(second, first, 0);
            }
        }
        boolean[] discovered = new boolean[n];
        Integer[] parent = new Integer[n];
        ResizingDeque<Integer> queueTree = new ResizingDequeImpl<>();
        queueTree.addLast(src);
        discovered[src] = true;
        while (queueTree.size() != 0) {
            Integer current = queueTree.pollFirst();
            Set<Integer> set = tree.outNeighbors(current);
            for (Integer next : set) {
                if (!discovered[next]) {
                    discovered[next] = true;
                    queueTree.addLast(next);
                    parent[next] = current;
                }
            }
        }
        if (parent[tgt] == null) {
            return new LinkedList<Integer>();
        }
        Integer current = tgt;
        LinkedList<Integer> output = new LinkedList<>();
        while (!current.equals(src)) {
            output.addFirst(current);
            current = parent[current];
        }
        output.addFirst(src);
        return output;
    }

    public static class UnionFind<E> {
        Map<E, Node<E>> map = new HashMap<>();
        public void makeSet(E x) {
            if (map.containsKey(x)) {
                throw new IllegalArgumentException();
            }
            map.put(x, new Node<>(x));
            map.get(x).setNext(map.get(x));
        }
        public E find(E x) {
            Node<E> curr = map.get(x);
            while (curr.next != null) {
                if (curr.next == curr) {
                    map.get(x).next = curr;
                    return curr.getValue();
                } else {
                    curr = curr.next;
                }
            }
            throw new IllegalArgumentException();
        }
        public void merge(E x, E y) {
            if (find(x) == find(y)) {
                throw new IllegalArgumentException();
            }
            Node<E> xCurr = map.get(x);
            int xLen = 0;
            while (xCurr.next != xCurr) {
                xCurr = xCurr.next;
                xLen++;

            }
            Node<E> yCurr = map.get(y);
            int yLen = 0;
            while (yCurr.next != yCurr) {
                yCurr = yCurr.next;
                xLen++;

            }
            if (xLen <= yLen) {
                xCurr.next = yCurr;
            } else {
                yCurr.next = xCurr;
            }
        }
    }

    public static class Node<E> {
        private E value;
        private E optional;
        private Node<E> next;
        public Node(E value) {
            this.value = value;
        }
        public Node(E value, E optional) {
            this.value = value;
            this.optional = optional;
        }
        public Node<E> getNext() {
            return this.next;
        }
        public void setNext(Node<E> next) {
            this.next = next;
        }
        public E getValue() {
            return this.value;
        }
        public E getSecond() {
            return this.optional;
        }


        public boolean equals(Node<E> other) {
            return this.value.equals(other.getValue()) && this.optional.equals(other.getSecond());
        }


    }
}
