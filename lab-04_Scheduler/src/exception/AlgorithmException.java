package exception;

public class AlgorithmException extends RuntimeException {

    public AlgorithmException() {
        super("The referred algorithm is invalid, you must use: ");
    }

    public AlgorithmException(String message) {
        super(message);
    }
}
