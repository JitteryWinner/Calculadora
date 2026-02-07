import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import calculadora.BasicCalculator;
import calculadora.PostfixCalculator;
import calculadora.PostfixCalculatorADT;

public class CompatibilityTest {

    static class OtherStackToMyStackAdapter<T> implements calculadora.Stack<T> {
        private final Stack<T> other;
        private int count = 0;

        OtherStackToMyStackAdapter(Stack<T> other) {
            this.other = other;
        }

        @Override
        public void push(T element) {
            other.push(element);
            count++;
        }

        @Override
        public T pop() {
            T v = other.pop();
            count--;
            return v;
        }

        @Override
        public T peek() {
            return other.peek();
        }

        @Override
        public int size() {
            return count;
        }
    }

    static class MyStackToOtherStackAdapter<T> implements Stack<T> {
        private final calculadora.Stack<T> mine;

        MyStackToOtherStackAdapter(calculadora.Stack<T> mine) {
            this.mine = mine;
        }

        @Override
        public void push(T item) {
            mine.push(item);
        }

        @Override
        public T pop() {
            return mine.pop();
        }

        @Override
        public T peek() {
            return mine.peek();
        }
    }

    @Test
    void miPostfixCalculatorFuncionaConLaPilaDelOtroGrupo() throws Exception {
        Stack<Integer> otherStack = new StackArrayList<>();
        calculadora.Stack<Integer> adapted = new OtherStackToMyStackAdapter<>(otherStack);

        PostfixCalculatorADT myCalc = new PostfixCalculator(adapted, new BasicCalculator());

        assertEquals(15, myCalc.evaluate("1 2 + 4 * 3 +"));
        assertEquals(7, myCalc.evaluate("3 4 +"));
        assertEquals(14, myCalc.evaluate("5 1 2 + 4 * + 3 -"));
    }

    @Test
    void laCalculadoraDelOtroGrupoFuncionaConMiPila() {
        calculadora.Stack<Integer> myStack = new calculadora.StackArrayList<>();
        Stack<Integer> adapted = new MyStackToOtherStackAdapter<>(myStack);

        Calculadora otherCalc = new Calculadora(adapted);

        assertEquals(15, otherCalc.Operate("1 2 + 4 * 3 +"));
        assertEquals(7, otherCalc.Operate("3 4 +"));
    }

    @Test
    void laCalculadoraDelOtroGrupoNoEsCompatibleConNumerosDeMasDeUnDigito() {
        Stack<Integer> otherStack = new StackArrayList<>();
        Calculadora otherCalc = new Calculadora(otherStack);

        assertThrows(Exception.class, () -> otherCalc.Operate("10 5 +"));
    }
}
