@Override
public int evaluate(String expression) throws CalculatorException {
    if (expression == null) {
        throw new CalculatorException(
            CalculatorException.Code.INVALID_EXPRESSION,
            "Expresión null"
        );
    }

    String trimmed = expression.trim();
    if (trimmed.isEmpty()) {
        throw new CalculatorException(
            CalculatorException.Code.INVALID_EXPRESSION,
            "Expresión vacía"
        );
    }

    String[] tokens = trimmed.split("\\s+");

    for (String t : tokens) {
        if (isOperator(t)) {
            if (stack.size() < 2) {
                throw new CalculatorException(
                    CalculatorException.Code.INSUFFICIENT_OPERANDS,
                    "No hay suficientes operandos para realizar la operación."
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
                            "División entre cero."
                        );
                    }
                    r = calc.dividir(a, b);
                }
                default -> throw new CalculatorException(
                    CalculatorException.Code.INVALID_TOKEN,
                    "Token inválido: '" + t + "'",
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
                    "Token inválido: '" + t + "'",
                    t
                );
            }
        }
    }

    if (stack.size() != 1) {
        throw new CalculatorException(
            CalculatorException.Code.INVALID_EXPRESSION,
            "Expresión inválida: sobran operandos u operadores."
        );
    }

    return stack.pop();
}

private boolean isOperator(String t) {
    return t.length() == 1 && "+-*/".contains(t);
}
