import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A {@link java.util.Deque} implemented by a resizing array.
 *
 * @param <E> the type of the elements in the deque
 */

public interface ResizingDeque<E> extends Iterable<E> {

    /**
     * Returns the size of this list.
     *
     * @return the number of elements in this list
     */
    int size();

    /**
     * @return The underlying array of the deque implementation directly.
     */
    E[] getArray();

    /**
     * Inserts the specified element at the front of this deque.
     *
     * @param e the element to add
     */
    void addFirst(E e);

    /**
     * Inserts the specified element at the end of this deque.
     *
     * @param e the element to add
     */
    void addLast(E e);

    /**
     * Retrieves and removes the first element of this deque.
     *
     * @throws NoSuchElementException if the deque is empty.
     */
    E pollFirst();

    /**
     * Retrieves and removes the last element of this deque.
     *
     * @throws NoSuchElementException if the deque is empty.
     */
    E pollLast();

    /**
     * Retrieves, but does not remove, the first element of this deque.
     *
     * @throws NoSuchElementException if the deque is empty.
     */
    E peekFirst();

    /**
     * Retrieves, but does not remove, the last element of this deque
     *
     * @throws NoSuchElementException if the deque is empty.
     */
    E peekLast();

    /**
     * Returns an iterator over the elements in this deque, ordered from
     * first to last.
     *
     * @return an iterator over the elements in this deque
     */
    Iterator<E> iterator();
}
