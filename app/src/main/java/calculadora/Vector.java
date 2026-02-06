import java.util.Arrays;
import java.util.NoSuchElementException;

public class Vector<T> {
    private Object[] data;
    private int size;

    public Vector() {
        this(10);
    }

    public Vector(int initialCapacity) {
        if (initialCapacity < 1) initialCapacity = 1;
        this.data = new Object[initialCapacity];
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T value) {
        ensureCapacity(size + 1);
        data[size++] = value;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        rangeCheck(index);
        return (T) data[index];
    }

    @SuppressWarnings("unchecked")
    public T removeLast() {
        if (size == 0) throw new NoSuchElementException("Vector vacío");
        T value = (T) data[size - 1];
        data[size - 1] = null;
        size--;
        return value;
    }

    private void ensureCapacity(int needed) {
        if (needed <= data.length) return;
        int newCap = Math.max(needed, data.length * 2);
        data = Arrays.copyOf(data, newCap);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);
        }
    }
}
