package calculadora;

/**
 * Este código es una implementación de una calculadora que evalúa expresiones en notación de postfix.
 * Para hacerlo utiliza una pila para el almacenamiento temporal de operandos y una instancia de {@link Calculator}
para realizar las operaciones aritméticas.
 * * @author Diego Ayala, David Berganza
 * @version 1.1
 */

public class PostfixCalculator implements PostfixCalculatorADT {

    private final Stack<Integer> stack;
    private final Calculator calc;

/**
     * Se construye una nueva calculadora postfix.
     * @param stack Es la implementación de pila que se va a utilizar. No debe ser null.
     * @param calc Es la instancia que realizará las operaciones básicas. No debe ser null.
     * @throws IllegalArgumentException Agrega una excepsión si stack o calc son null.
*/

    public PostfixCalculator(Stack<Integer> stack, Calculator calc) {
        if (stack == null) throw new IllegalArgumentException("stack: No puede ser null");
        if (calc == null) throw new IllegalArgumentException("calc: No puede ser null");
        this.stack = stack;
        this.calc = calc;
    }

/**
     * Se evalúa una cadena de texto que contiene una expresión postfix.
     * @param expression Es la cadena con la expresión a utilizar.
     * @return Retorna el resultado de la operación.
     * @throws CalculatorException Agrega una excepción si la expresión es inválida.
*/

    @Override
    public int evaluate(String expression) throws CalculatorException {
        // Se limpia la pila por si se reutiliza el mismo objeto para varias las lineas
        while (stack.size() > 0) stack.pop();

        if (expression == null) {
            throw new CalculatorException(
                    CalculatorException.Code.INVALID_EXPRESSION,
                    "Expresion: null"
            );
        }

        String trimmed = expression.trim();
        if (trimmed.isEmpty()) {
            throw new CalculatorException(
                    CalculatorException.Code.INVALID_EXPRESSION,
                    "Expresion: vacia"
            );
        }

        String[] tokens = trimmed.split("\\s+");

        for (String t : tokens) {
            if (isOperator(t)) {
                if (stack.size() < 2) {
                    throw new CalculatorException(
                            CalculatorException.Code.INSUFFICIENT_OPERANDS,
                            "Hubo un error. No hay suficientes operandos para realizar la operacion."
                    );
                }

                int b = stack.pop();
                int a = stack.pop();

                int r;
                switch (t) {
                    case "+" -> r = calc.sumar(a, b);
                    case "-" -> r = calc.restar(a, b);
                    case "*" -> r = calc.multiplicar(a, b);
                    case "/" -> {
                        if (b == 0) {
                            throw new CalculatorException(
                                    CalculatorException.Code.DIVISION_BY_ZERO,
                                    "Error. Hubo una division entre cero."
                            );
                        }
                        r = calc.dividir(a, b);
                    }
                    default -> throw new CalculatorException(
                            CalculatorException.Code.INVALID_TOKEN,
                            "Token invalido: '" + t + "'",
                            t
                    );
                }
                stack.push(r);

            } else {
                try {
                    int value = Integer.parseInt(t);
                    stack.push(value);
                } catch (NumberFormatException e) {
                    throw new CalculatorException(
                            CalculatorException.Code.INVALID_TOKEN,
                            "Token invalido: '" + t + "'",
                            t
                    );
                }
            }
        }

        if (stack.size() != 1) {
            throw new CalculatorException(
                    CalculatorException.Code.INVALID_EXPRESSION,
                    "Expresion invalida: sobran operandos u operadores."
            );
        }

        return stack.pop();
    }

/**
     * Se determina si un token dado representa un operador aritmético válido.
     * @param t El token a evaluar.
     * @return Retorna un true si el símbolo es "+", "-", "*" o "/"; false en caso de que sea inválido.
*/

    private boolean isOperator(String t) {
        return t != null && t.length() == 1 && "+-*/".contains(t);
    }
}