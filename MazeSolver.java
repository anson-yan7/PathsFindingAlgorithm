import java.util.LinkedList;
import java.util.List;
import java.util.Set;

final public class MazeSolver {
    private MazeSolver() {}

    /**
     * Returns a list of coordinates on the shortest path from {@code src} to {@code tgt}
     * by executing a breadth-first search on the graph represented by a 2D-array of size m x n.
     * Please note, you MUST use your ResizingDeque implementation as the BFS queue for this method.
     * <p>
     * Input {@code maze} guaranteed to be a non-empty and valid matrix.
     * Input {@code src} and {@code tgt} are guaranteed to be valid, in-bounds, and not blocked.
     * <p>
     * Do NOT modify this method header.
     *
     * @param maze the input maze, which is a 2D-array of size m x n
     * @param src The starting Coordinate of the path on the matrix
     * @param tgt The target Coordinate of the path on the matrix
     * @return an empty list if there is no path from {@param src} to {@param tgt}, otherwise an
     * ordered list of vertices in the shortest path from {@param src} to {@param tgt},
     * with the first element being {@param src} and the last element being {@param tgt}.
     * @implSpec This method should run in worst-case O(m x n) time.
     */
    public static List<Coordinate> getShortestPath(int[][] maze, Coordinate src, Coordinate tgt) {
        if (src.equals(tgt)) {
            LinkedList<Coordinate> output = new LinkedList<>();
            output.add(src);
            return output;
        }
        int m = maze.length;
        int n = maze[0].length;
        int size = m * n;
        Graph map = new Graph(size);
        for (int i = 0; i < size; i++) {
            int top = i - n;
            int right = i + 1;
            int left = i - 1;
            int bottom = i + n;
            if (maze[i / n][i % n] == 0) {
                if (i % n == 0) {
                    if (top >= 0 && maze[top / n][top % n] == 0) {
                        map.addEdge(i, top, 0);
                    }
                    if (maze[right / n][right % n] == 0) {
                        map.addEdge(i, right, 0);
                    }
                    if (bottom < size && maze[bottom / n][bottom % n] == 0) {
                        map.addEdge(i, bottom, 0);
                    }
                } else if (i % n == n - 1) {
                    if (top >= 0 && maze[top / n][top % n] == 0) {
                        map.addEdge(i, top, 0);
                    }
                    if (bottom < size && maze[bottom / n][bottom % n] == 0) {
                        map.addEdge(i, bottom, 0);
                    }
                    if (maze[left / n][left % n] == 0) {
                        map.addEdge(i, left, 0);
                    }
                } else {
                    if (top >= 0 && maze[top / n][top % n] == 0) {
                        map.addEdge(i, top, 0);
                    }
                    if (bottom < size && maze[bottom / n][bottom % n] == 0) {
                        map.addEdge(i, bottom, 0);
                    }
                    if (maze[left / n][left % n] == 0) {
                        map.addEdge(i, left, 0);
                    }
                    if (maze[right / n][right % n] == 0) {
                        map.addEdge(i, right, 0);
                    }
                }
            }
//            if (maze[i / n][i % n] == 0) {
//                if (i == 0) {
//                    if (maze[0][1] == 0) {
//                        map.addEdge(0, 1, 0);
//                    }
//                    if (maze[1][0] == 0) {
//                        map.addEdge(n, 0, 0);
//                    }
//                } else if (i == n - 1) {
//                    if (maze[0][n - 2] == 0) {
//                        map.addEdge(n - 1, n - 2, 0);
//                    }
//                    if (maze[1][n - 1] == 0) {
//                        map.addEdge(n - 1, 2 * n - 1, 0);
//                    }
//                } else if (i == size - 1) {
//                    if (maze[m - 1][n - 2] == 0) {
//                        map.addEdge(size - 1, size - 2, 0);
//                    }
//                    if (maze[m - 2][n - 1] == 0) {
//                        map.addEdge(size - 1, size - 1 - n, 0);
//                    }
//                } else if (i == size - n) {
//                    if (maze[m - 1][1] == 0) {
//                        map.addEdge(size - n, size - n + 1, 0);
//                    }
//                    if (maze[m - 2][0] == 0) {
//                        map.addEdge(size - n, size - 2 * n, 0);
//                    }
//                } else {
//                    if ()
//                }
//            }
        }
        boolean[] discovered = new boolean[size];
        Integer[] parent = new Integer[size];
        ResizingDeque<Integer> queue = new ResizingDequeImpl<>();
        Integer start = src.getX() + src.getY() * n;
        Integer end = tgt.getX() + tgt.getY() * n;
        queue.addLast(start);
        discovered[start] = true;
        while (queue.size() != 0 && !discovered[end]) {
            Integer current = queue.pollFirst();
            Set<Integer> set = map.outNeighbors(current);
            for (Integer next : set) {
                if (!discovered[next]) {
                    discovered[next] = true;
                    queue.addLast(next);
                    parent[next] = current;
                }
            }
        }
        if (parent[end] == null) {
            return new LinkedList<Coordinate>();
        }
        Integer current = end;
        LinkedList<Coordinate> output = new LinkedList<>();
        while (!current.equals(start)) {
            output.addFirst(new Coordinate(current % n, current / n));
            current = parent[current];
        }
        output.addFirst(src);
        return output;
    }

}