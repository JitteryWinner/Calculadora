package calculadora;

/**
    * Es una definición genérica de una estructura de datos de tipo Pila.
 *  @param <T> Es el tipo de elementos que almacenará la pila.
 */

public interface Stack<T> {
    void push(T element);
    T pop();
    T peek();
    int size();
}