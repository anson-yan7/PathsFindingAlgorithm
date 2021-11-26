import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class GraphTest {
    private Graph graph;
    @Before
    public void setupGraph() {
        graph = new Graph(4);
    }

    @Test
    public void testSize() {
        assertEquals(4, graph.getSize());
    }
    @Test
    public void testEdge() {
        graph.addEdge(0, 1, 2);
        assertTrue(graph.hasEdge(0, 1));
        assertFalse(graph.hasEdge(1, 0));
        assertEquals(2, graph.getWeight(0, 1));
        Set<Integer> set = graph.outNeighbors(0);
        assertEquals(1, set.size());
        graph.removeEdge(0, 1);
        Set<Integer> set1 = graph.outNeighbors(0);
        assertEquals(0, set.size());
    }
}
