/**
 * ADT para evaluar expresiones en notación Postfix.
 */
public interface PostfixCalculatorADT {
    /**
     * @param expression expresión postfix con tokens separados por espacios.
     * @return resultado entero
     * @throws CalculatorException si hay tokens inválidos, división entre cero,
     *         o faltan operandos.
     */
    int evaluate(String expression) throws CalculatorException;
}
