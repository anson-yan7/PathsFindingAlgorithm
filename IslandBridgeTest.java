import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IslandBridgeTest {
    private Graph graph;
    @Before
    public void setupGraph() {
        graph = new Graph(6);
        graph.addEdge(0, 1, 0);
        graph.addEdge(1, 3, 0);
        graph.addEdge(3, 1, 0);
        graph.addEdge(2, 0, 0);
        graph.addEdge(0, 2, 0);
        graph.addEdge(2, 5, 0);
        graph.addEdge(3, 5, 0);
        graph.addEdge(5, 3, 0);
        graph.addEdge(5, 4, 0);
        graph.addEdge(4, 3, 0);
    }
    @Test
    public void testTrue() {
        assertTrue(IslandBridge.allNavigable(graph, 3));
        assertTrue(IslandBridge.allNavigable(graph, 1));
    }
    @Test
    public void testFalse() {
        assertFalse(IslandBridge.allNavigable(graph, 0));
        assertFalse(IslandBridge.allNavigable(graph, 2));
    }

}
