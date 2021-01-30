package exception;

public class QuantumException extends RuntimeException {

    public QuantumException() {
        super("The referred quantum is invalid, you must use quantum greater than or equal 1 ( quantum >= 1 )");
    }

    public QuantumException(String message) {
        super(message);
    }
}
