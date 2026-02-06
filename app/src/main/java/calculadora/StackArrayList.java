package calculadora;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class StackArrayList<T> implements Stack<T> {
    private final ArrayList<T> list = new ArrayList<>();

    @Override
    public void push(T element) {
        list.add(element);
    }

    @Override
    public T pop() {
        if (list.isEmpty()) throw new EmptyStackException();
        return list.remove(list.size() - 1);
    }

    @Override
    public T peek() {
        if (list.isEmpty()) throw new EmptyStackException();
        return list.get(list.size() - 1);
    }

    @Override
    public int size() {
        return list.size();
    }
}
