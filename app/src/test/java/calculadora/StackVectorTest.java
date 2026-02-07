package calculadora;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class StackVectorTest {

    @Test
    void pushYPopFuncionan() {
        Stack<Integer> s = new StackVector<>();
        s.push(10);
        s.push(20);
        assertEquals(2, s.size());
        assertEquals(20, s.pop());
        assertEquals(10, s.pop());
        assertEquals(0, s.size());
    }

    @Test
    void peekFunciona() {
        Stack<String> s = new StackVector<>();
        s.push("A");
        s.push("B");
        assertEquals("B", s.peek());
        assertEquals(2, s.size());
    }

    @Test
    void popEnPilaVaciaLanzaExcepcion() {
        Stack<Integer> s = new StackVector<>();
        assertThrows(EmptyStackException.class, s::pop);
    }

    @Test
    void peekEnPilaVaciaLanzaExcepcion() {
        Stack<Integer> s = new StackVector<>();
        assertThrows(EmptyStackException.class, s::peek);
    }
}
