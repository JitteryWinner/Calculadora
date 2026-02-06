public class CalculatorException extends Exception {

    public enum Code {
        DIVISION_BY_ZERO,
        INVALID_TOKEN,
        INSUFFICIENT_OPERANDS,
        INVALID_EXPRESSION
    }

    private final Code code;
    private final String token; // opcional (solo útil en INVALID_TOKEN)

    public CalculatorException(Code code, String message) {
        super(message);
        this.code = code;
        this.token = null;
    }

    public CalculatorException(Code code, String message, String token) {
        super(message);
        this.code = code;
        this.token = token;
    }

    public Code getCode() {
        return code;
    }

    public String getToken() {
        return token;
    }
}
