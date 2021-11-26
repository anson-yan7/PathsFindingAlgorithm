import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class MazeSolverTest {
    private int[][] maze;
    @Before
    public void setupMaze() {
        maze = new int[][] {
                {0, 0, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 1}
        };
    }
    @Test
    public void testMazeSolver() {
        LinkedList<Coordinate> expected = new LinkedList<>();
        expected.addLast(new Coordinate(0, 0));
        expected.addLast(new Coordinate(1, 0));
        expected.addLast(new Coordinate(2, 0));
        expected.addLast(new Coordinate(2, 1));
        expected.addLast(new Coordinate(2, 2));
        expected.addLast(new Coordinate(1, 2));
        assertEquals(expected, MazeSolver.getShortestPath(maze, new Coordinate(0, 0)
                , new Coordinate(1, 2)));

    }
    @Test
    public void testMazeSolverSingleton() {
        LinkedList<Coordinate> expected = new LinkedList<>();
        expected.addLast(new Coordinate(0, 0));
        assertEquals(expected, MazeSolver.getShortestPath(maze, new Coordinate(0, 0)
                , new Coordinate(0, 0)));

    }
}
