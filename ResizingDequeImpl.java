import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @param <E>   {@inheritDoc}
 */
public class ResizingDequeImpl<E> implements ResizingDeque<E> {

    private E[] array;
    private int first;
    private int last;
    private int size;
    private int length;

    public ResizingDequeImpl() {
        array = (E[]) new Object[2];
        size = 0;
        length = 2;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E[] getArray() {
        E[] output = (E[]) new Object[length];
        for (int i = 0; i < length; i++) {
            output[i] = array[i];
        }
        return output;
    }

    @Override
    public void addFirst(E e) {
        if (size == 0) {
            size++;
            array[0] = e;
            first = 0;
            last = 0;
        } else if (size == length) {
            E[] newArray = (E[]) new Object[2 * length];
            if (first == 0) {
                for (int i = 0; i < length; i++) {
                    newArray[i] = array[i];
                }
            } else {
                for (int i = first; i < length; i++) {
                    newArray[i - first] = array[i];
                }
                for (int i = 0; i <= last; i++) {
                    newArray[i + length - first] = array[i];
                }
            }
            last = length - 1;
            size++;
            length = 2 * length;
            first = length - 1;
            array = newArray;
            array[length - 1] = e;
        } else {
            if (first == 0) {
                first = length - 1;
            } else {
                first = first - 1;
            }
            array[first] = e;
            size++;
        }
    }

    @Override
    public void addLast(E e) {
        if (size == 0) {
            size++;
            array[0] = e;
            first = 0;
            last = 0;
        } else if (size == length) {
            E[] newArray = (E[]) new Object[2 * length];
            if (first == 0) {
                for (int i = 0; i < length; i++) {
                    newArray[i] = array[i];
                }
            } else {
                for (int i = first; i < length; i++) {
                    newArray[i - first] = array[i];
                }
                for (int i = 0; i <= last; i++) {
                    newArray[i + length - first] = array[i];
                }
            }
            first = 0;
            last = length;
            length = 2 * length;
            size++;
            array = newArray;
            array[last] = e;
        } else {
            if (last == length - 1) {
                last = 0;
            } else {
                last = last + 1;
            }
            array[last] = e;
            size++;
        }
    }

    @Override
    public E pollFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E output = array[first];
        array[first] = null;
        size = size - 1;
        if (first == length - 1) {
            first = 0;
        } else {
            first = first + 1;
        }
        if (size < length / 4) {
            if (length == 4) {
                array = (E[]) new Object[2];
                length = length / 2;
                int first;
                int last;
            } else if (length != 2) {
                E[] newArray = (E[]) new Object[length / 2];
                if (first > last) {
                    for (int i = first; i < length; i++) {
                        newArray[i - first] = array[i];
                    }
                    for (int i = 0; i < last; i++) {
                        newArray[i + length - first] = array[i];
                    }
                } else {
                    for (int i = first; i <= last; i++) {
                        newArray[i - first] = array[i];
                    }
                }
                length = length / 2;
                array = newArray;
                first = 0;
                last = size - 1;
            }
        }
        return output;
    }

    @Override
    public E pollLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E output = array[last];
        array[last] = null;
        size = size - 1;
        if (last == 0) {
            last = length - 1;
        } else {
            last = last - 1;
        }
        if (size < length / 4) {
            if (length == 4) {
                array = (E[]) new Object[2];
                length = length / 2;
                int first;
                int last;
            } else if (length != 2) {
                E[] newArray = (E[]) new Object[length / 2];
                if (first > last) {
                    for (int i = first; i < length; i++) {
                        newArray[i - first] = array[i];
                    }
                    for (int i = 0; i < last; i++) {
                        newArray[i + length - first] = array[i];
                    }
                } else {
                    for (int i = first; i <= last; i++) {
                        newArray[i - first] = array[i];
                    }
                }
                length = length / 2;
                array = newArray;
                first = 0;
                last = size - 1;
            }
        }
        return output;
    }

    @Override
    public E peekFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return array[first];
    }

    @Override
    public E peekLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return array[last];
    }

    @Override
    public Iterator<E> iterator() {
        boolean has = !(size == 0);
        return new QueueIterator<>(first, has);
    }

    public class QueueIterator<E> implements Iterator<E> {
        private int curr;
        private boolean has;
        private boolean past = false;

        public QueueIterator(int curr, boolean has) {
            this.curr = curr;
            this.has = has;
        }

        @Override
        public boolean hasNext() {
            return has;
        }

        @Override
        public E next() {

            if (has) {
                if (curr == array.length - 1) {
                    curr = 0;
                    if (curr == last + 1 && past) {
                        has = false;
                    }
                    if (!past) {
                        past = true;
                    }
                    return (E) array[array.length - 1];
                } else {
                    curr = curr + 1;
                    if (curr == last + 1 && past) {
                        has = false;
                    }
                    if (!past) {
                        past = true;
                    }
                    return (E) array[curr - 1];
                }
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
