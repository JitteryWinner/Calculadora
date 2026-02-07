import java.util.ArrayList;

public class StackArrayList<T> implements Stack<T>{
    public ArrayList<T> datos;
//lista para guardar los datos
    public StackArrayList (){
        datos = new ArrayList<T>();
    }
//agrega un elemento a la pila
    public void push(T item){
        datos.add(item);
    }

//Quita el ultimo elemento de la pila y lo tenorna
    @Override
    public T pop() {
        T ultimo = datos.get(datos.size() - 1);
        datos.remove(datos.size() - 1);
        return ultimo;
    }
//Retorna el ultimo elemento de la pila sin quitarlo
    @Override
    public T peek() {
        return datos.get(datos.size() - 1);
    }
}