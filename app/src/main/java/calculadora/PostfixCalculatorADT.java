package calculadora;

public interface PostfixCalculatorADT {
    int evaluate(String expression) throws CalculatorException;
}
