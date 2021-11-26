import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class ResizingDequeImplTest {
    private ResizingDeque<Integer> queue;
    private ResizingDeque<Integer> queueDone;
    @Before
    public void setUpDeque() {
        queue = new ResizingDequeImpl<>();
        queueDone = new ResizingDequeImpl<>();
        queueDone.addFirst(1);
        queueDone.addFirst(4);
        queueDone.addFirst(3);
        queueDone.addFirst(5);
        queueDone.addFirst(6);
    }

    @Test
    public void testAddFirst() {
        queue.addFirst(1);
        Integer[] expected = {1, null};
        assertEquals(expected, queue.getArray());
        queue.addFirst(4);
        expected[1] = 4;
        assertEquals(expected, queue.getArray());
        queue.addFirst(3);
        Integer[] expected1 = {4, 1, null, 3};
        assertEquals(expected1, queue.getArray());
        queue.addFirst(5);
        expected1[2] = 5;
        assertEquals(expected1, queue.getArray());
        queue.addFirst(6);
        Integer[] expected2 = {5, 3, 4, 1, null, null, null, 6};
        assertEquals(expected2, queue.getArray());
    }
    @Test
    public void testAddLast() {
        queue.addLast(1);
        Integer[] expected = {1, null};
        assertEquals(expected, queue.getArray());
        queue.addLast(4);
        expected[1] = 4;
        assertEquals(expected, queue.getArray());
        queue.addLast(3);
        Integer[] expected1 = {1, 4, 3, null};
        assertEquals(expected1, queue.getArray());
        queue.addLast(5);
        expected1[3] = 5;
        assertEquals(expected1, queue.getArray());
        queue.addLast(6);
        Integer[] expected2 = {1, 4, 3, 5, 6, null, null, null};
        assertEquals(expected2, queue.getArray());
    }

    @Test
    public void testPollLast() {
        int output = queueDone.pollLast();
        assertEquals(output, 1);
        Integer[] expected = {5, 3, 4, null, null, null, null, 6};
        assertEquals(expected, queueDone.getArray());
        int output1 = queueDone.pollLast();
        assertEquals(output1, 4);
        expected[2] = null;
        assertEquals(expected, queueDone.getArray());
        int output2 = queueDone.pollLast();
        assertEquals(output2, 3);
        expected[1] = null;
        assertEquals(expected, queueDone.getArray());
        int output3 = queueDone.pollLast();
        assertEquals(output3, 5);
        Integer[] expected1 = {6, null, null, null};
        assertEquals(expected1, queueDone.getArray());
        int output4 = queueDone.pollLast();
        assertEquals(output4, 6);
        assertEquals(0, queueDone.size());
    }

    @Test
    public void testPollFirst() {
        int output = queueDone.pollFirst();
        assertEquals(output, 6);
        Integer[] expected = {5, 3, 4, 1, null, null, null, null};
        assertEquals(queueDone.getArray(), expected);
        int output1 = queueDone.pollFirst();
        assertEquals(output1, 5);
        expected[0] = null;
        assertEquals(queueDone.getArray(), expected);
        int output2 = queueDone.pollFirst();
        assertEquals(output2, 3);
        expected[1] = null;
        assertEquals(queueDone.getArray(), expected);
        int output3 = queueDone.pollFirst();
        assertEquals(output3, 4);
        Integer[] expected1 = {1, null, null, null};
        assertEquals(queueDone.getArray(), expected1);
        int output4 = queueDone.pollFirst();
        assertEquals(output4, 1);
        assertEquals(0, queueDone.size());
    }
    @Test
    public void testPeek() {
        int first = queueDone.peekFirst();
        assertEquals(6, first);
        int last = queueDone.peekLast();
        assertEquals(1, last);
    }
    @Test
    public void testIterator() {
        Iterator<Integer> iter = queueDone.iterator();
        assertEquals(5, queueDone.size());
        assertTrue(iter.hasNext());
        assertEquals((Integer) 6, iter.next());
        assertTrue(iter.hasNext());
        assertEquals((Integer) 5, iter.next());
        assertTrue(iter.hasNext());
        assertEquals((Integer) 3, iter.next());
        assertTrue(iter.hasNext());
        assertEquals((Integer) 4, iter.next());
        assertTrue(iter.hasNext());
        assertEquals((Integer) 1, iter.next());
        assertFalse(iter.hasNext());
        queue.addFirst(4);
        queue.addFirst(1);
        Iterator<Integer> iter1 = queue.iterator();
        assertTrue(iter1.hasNext());
        assertEquals((Integer) 1, iter1.next());
        assertTrue(iter1.hasNext());
        assertEquals((Integer) 4, iter1.next());
        assertFalse(iter1.hasNext());
    }

    @Test
    public void testLessThan() {
        queue.addFirst(4);
        queue.addFirst(3);
        queue.addFirst(1);
        queue.addFirst(2);
        queue.addFirst(7);
        queue.addLast(2);
        queue.addLast(2);
        queue.addLast(2);
        queue.addFirst(7);
        queue.pollLast();
        queue.pollLast();
        queue.pollLast();
        queue.pollLast();
        queue.pollLast();
        queue.pollLast();
        queue.pollLast();
        queue.pollLast();
        assertEquals(1, queue.size());
        queue.addFirst(7);
        queue.addLast(2);
        queue.addLast(2);
        queue.addLast(2);
        assertEquals(5, queue.size());
    }
}
