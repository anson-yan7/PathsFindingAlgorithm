import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class WidestPathTest {
    private Graph graph;
    private Graph triangle;
    @Before
    public void setupGraph() {
        graph = new Graph(7);
        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 1, 5);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 2, 4);
        graph.addEdge(4, 3 , 6);
        graph.addEdge(3, 4 , 6);
        graph.addEdge(4, 0, 3);
        graph.addEdge(0, 4, 3);
        graph.addEdge(0, 3, 2);
        graph.addEdge(3, 0, 2);
        graph.addEdge(1,5 ,4);
        graph.addEdge(5,1 ,4);
        graph.addEdge(5, 2, 1);
        graph.addEdge(2, 5, 1);
        graph.addEdge(2, 0, 2);
        graph.addEdge(0, 2, 2);
        graph.addEdge(5, 0, 10);
        graph.addEdge(0, 5, 10);
        triangle = new Graph(3);
        triangle.addEdge(0, 1, 6);
        triangle.addEdge(1, 0, 6);
        triangle.addEdge(1, 2, 4);
        triangle.addEdge(2, 1, 4);
        triangle.addEdge(0, 2, 3);
        triangle.addEdge(2, 0, 3);
    }
    @Test
    public void testWidestPath() {
        LinkedList<Integer> expected = new LinkedList<>();
        expected.addLast(1);
        expected.addLast(5);
        expected.addLast(0);
        assertEquals(expected, WidestPath.getWidestPath(graph, 1, 0));
        LinkedList<Integer> expected1 = new LinkedList<>();
        assertEquals(expected1, WidestPath.getWidestPath(graph, 1, 6));
    }
    @Test
    public void testTriangle() {
        LinkedList<Integer> expected = new LinkedList<>();
        expected.addFirst(0);
        expected.addFirst(1);
        assertEquals(expected, WidestPath.getWidestPath(triangle, 1, 0));
    }

    @Test
    public void testSingleton() {
        LinkedList<Integer> expected = new LinkedList<>();
        expected.addFirst(0);
        assertEquals(expected, WidestPath.getWidestPath(triangle, 0, 0));
    }
}
