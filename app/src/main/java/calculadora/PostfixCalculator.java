package calculadora;

public class PostfixCalculator implements PostfixCalculatorADT {

    private final Stack<Integer> stack;
    private final Calculator calc;

    public PostfixCalculator(Stack<Integer> stack, Calculator calc) {
        if (stack == null) throw new IllegalArgumentException("stack no puede ser null");
        if (calc == null) throw new IllegalArgumentException("calc no puede ser null");
        this.stack = stack;
        this.calc = calc;
    }

    @Override
    public int evaluate(String expression) throws CalculatorException {
        // Limpiar la pila por si se reutiliza el mismo objeto para varias lineas
        while (stack.size() > 0) stack.pop();

        if (expression == null) {
            throw new CalculatorException(
                    CalculatorException.Code.INVALID_EXPRESSION,
                    "Expresion null"
            );
        }

        String trimmed = expression.trim();
        if (trimmed.isEmpty()) {
            throw new CalculatorException(
                    CalculatorException.Code.INVALID_EXPRESSION,
                    "Expresion vacia"
            );
        }

        String[] tokens = trimmed.split("\\s+");

        for (String t : tokens) {
            if (isOperator(t)) {
                if (stack.size() < 2) {
                    throw new CalculatorException(
                            CalculatorException.Code.INSUFFICIENT_OPERANDS,
                            "No hay suficientes operandos para realizar la operacion."
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
                                    "Division entre cero."
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

    private boolean isOperator(String t) {
        return t != null && t.length() == 1 && "+-*/".contains(t);
    }
}
