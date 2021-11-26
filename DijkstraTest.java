import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class DijkstraTest {
    private Graph graph;
    @Before
    public void setupGraph() {
        graph = new Graph(7);
        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 4);
        graph.addEdge(4, 3 , 6);
        graph.addEdge(4, 0, 3);
        graph.addEdge(0, 3, 2);
        graph.addEdge(1,5 ,3);
        graph.addEdge(5, 2, 1);
        graph.addEdge(2, 0, 2);
        graph.addEdge(5, 0, 10);
    }
    @Test
    public void testDijkstra() {
        LinkedList<Integer> expected = new LinkedList<>();
        expected.addLast(1);
        expected.addLast(5);
        expected.addLast(2);
        expected.addLast(3);
        assertEquals(expected, Dijkstra.getShortestPath(graph, 1, 3));
        LinkedList<Integer> expected1 = new LinkedList<>();
        assertEquals(expected1, Dijkstra.getShortestPath(graph, 1, 6));
        LinkedList<Integer> expected2 = new LinkedList<>();
        expected2.addLast(4);
        expected2.addLast(0);
        expected2.addLast(3);
        assertEquals(expected2, Dijkstra.getShortestPath(graph, 4, 3));
        LinkedList<Integer> expected3 = new LinkedList<>();
        expected3.addLast(4);
        assertEquals(expected3, Dijkstra.getShortestPath(graph, 4, 4));
    }

}
