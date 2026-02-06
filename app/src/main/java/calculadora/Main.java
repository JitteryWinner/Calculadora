package calculadora;
import java.io.BufferedReader;
import java.io.FileReader;


public class Main {
    public static void main(String[] args) {
        String stackType = (args.length > 0) ? args[0].toLowerCase() : "vector";

        Stack<Integer> stack = switch (stackType) {
            case "arraylist" -> new StackArrayList<>();
            case "vector" -> new StackVector<>();
            default -> new StackVector<>();
        };

        PostfixCalculatorADT postfix = new PostfixCalculator(stack, new BasicCalculator());

        try (BufferedReader br = new BufferedReader(new FileReader("datos.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    int result = postfix.evaluate(line);
                    System.out.println("Resultado: " + result);
                } catch (CalculatorException e) {
                    System.out.println("Error: " + e.getMessage() + " | Expresión: " + line);
                }
            }
        } catch (Exception e) {
            System.out.println("No se pudo leer datos.txt: " + e.getMessage());
        }
    }
}
