package calculadora;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class PostfixCalculatorTest {

    @Test
    void evaluatesExample() throws CalculatorException {
        PostfixCalculatorADT c = new PostfixCalculator(new StackVector<>(), new BasicCalculator());
        assertEquals(15, c.evaluate("1 2 + 4 * 3 +"));
    }

    @Test
    void divisionByZero() {
        PostfixCalculatorADT c = new PostfixCalculator(new StackVector<>(), new BasicCalculator());

        CalculatorException ex = assertThrows(CalculatorException.class, () -> c.evaluate("4 0 /"));
        assertEquals(CalculatorException.Code.DIVISION_BY_ZERO, ex.getCode());
    }

    @Test
    void invalidToken() {
        PostfixCalculatorADT c = new PostfixCalculator(new StackVector<>(), new BasicCalculator());

        CalculatorException ex = assertThrows(CalculatorException.class, () -> c.evaluate("1 X +"));
        assertEquals(CalculatorException.Code.INVALID_TOKEN, ex.getCode());
    }

    @Test
    void insufficientOperands() {
        PostfixCalculatorADT c = new PostfixCalculator(new StackVector<>(), new BasicCalculator());

        CalculatorException ex = assertThrows(CalculatorException.class, () -> c.evaluate("1 +"));
        assertEquals(CalculatorException.Code.INSUFFICIENT_OPERANDS, ex.getCode());
    }
}
