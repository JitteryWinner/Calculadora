import java.util.EmptyStackException;


public class StackVector<T> implements Stack<T> {

    private final Vector<T> vector;

    public StackVector() {
        this.vector = new Vector<>();
    }

    public StackVector(int initialCapacity) {
        this.vector = new Vector<>(initialCapacity);
    }

    @Override
    public void push(T element) {
        vector.add(element);
    }

    @Override
    public T pop() {
        try {
            return vector.removeLast();
        } catch (Exception e) {
            throw new EmptyStackException();
        }
    }

    @Override
    public T peek() {
        if (vector.size() == 0) throw new EmptyStackException();
        return vector.get(vector.size() - 1);
    }

    @Override
    public int size() {
        return vector.size();
    }
}
