import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @param <V>   {@inheritDoc}
 * @param <Key> {@inheritDoc}
 */
public class BinaryMinHeapImpl<Key extends Comparable<Key>, V> implements BinaryMinHeap<Key, V> {
    
    private ArrayList<Entry<Key, V>> array;
    private HashMap<V, Key> reverseMapping;
    private HashMap<V, Integer> valueToIndex;
    
    
    public BinaryMinHeapImpl() {
        array = new ArrayList<Entry<Key, V>>();
        reverseMapping = new HashMap<V, Key>();
        valueToIndex = new HashMap<V, Integer>();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return array.size();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(V value) {
        return reverseMapping.containsKey(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Key key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        } else if (reverseMapping.containsKey(value)) {
            throw new IllegalArgumentException("value already in heap");
        }
        
        array.add(new Entry<Key, V>(key, value));
        reverseMapping.put(value, key);
        valueToIndex.put(value, array.size() - 1);
        addHelper();
    }
    
    void addHelper() {
        int pointer = array.size() - 1;
        while (pointer > 0) {
            if (array.get((pointer - 1) / 2).key.compareTo(array.get(pointer).key) > 0) {
                this.swap(pointer, (pointer - 1) / 2);
                
                valueToIndex.put(array.get((pointer - 1) / 2).value, pointer);
                valueToIndex.put(array.get(pointer).value, (pointer - 1) / 2);
                
                pointer = (pointer - 1) / 2;
            } else {
                break;
            }
        }
    }
    
    void minHeapify(int index) {
        int l = 2 * index + 1;
        int r = 2 * index + 2;
        int smallest = Integer.MAX_VALUE;
        if (l < array.size() && array.get(l).key.compareTo(array.get(index).key) < 0) {
            smallest = l;
        } else {
            smallest = index;
        }
        if (r < array.size() && array.get(r).key.compareTo(array.get(smallest).key) < 0) {
            smallest = r;
        }
        if (smallest != index) {
            this.swap(index, smallest);
            
            valueToIndex.put(array.get(smallest).value, index);
            valueToIndex.put(array.get(index).value, smallest);
            
            minHeapify(smallest);
        }
    }
    
    void swap(int a, int b) {
        Key k1 = array.get(a).key;
        V v1 = array.get(a).value;
        Key k2 = array.get(b).key;
        V v2 = array.get(b).value;
        
        array.set(b, new Entry<Key, V>(k1, v1));
        array.set(a, new Entry<Key, V>(k2, v2));
        
        valueToIndex.put(v1, b);
        valueToIndex.put(v2, a);
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseKey(V value, Key newKey) {
        if (!reverseMapping.containsKey(value)) {
            throw new NoSuchElementException("value not in heap");
        } else if (newKey == null) {
            throw new IllegalArgumentException("newKey is null");
        } else if (reverseMapping.get(value).compareTo(newKey) < 0) {
            throw new IllegalArgumentException("newKey is greater than current key");
        }
        
        reverseMapping.put(value, newKey);
        int index = valueToIndex.get(value);
        array.set(index, new Entry<Key, V>(newKey, value));
        
        int pointer = index;
        while (pointer > 0) {
            if (array.get((pointer - 1) / 2).key.compareTo(array.get(pointer).key) > 0) {
                this.swap(pointer, (pointer - 1) / 2);
                
                valueToIndex.put(array.get((pointer - 1) / 2).value, pointer);
                valueToIndex.put(array.get(pointer).value, (pointer - 1) / 2);
                
                pointer = (pointer - 1) / 2;
            } else {
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<Key, V> peek() {
        if (array.isEmpty()) {
            throw new NoSuchElementException("heap is empty");
        }
        return array.get(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<Key, V> extractMin() {
        if (array.isEmpty()) {
            throw new NoSuchElementException("heap is empty");
        }
        this.swap(0, array.size() - 1);
        Entry<Key, V> min = array.remove(array.size() - 1);
        reverseMapping.remove(min.value);
        valueToIndex.remove(min.value);
        minHeapify(0);
        return min;
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<V> values() {
        return reverseMapping.keySet();
    }
}