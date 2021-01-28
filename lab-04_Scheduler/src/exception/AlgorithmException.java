package exception;

public class AlgorithmException extends RuntimeException {
    private static final long serialVersionUID = 1234L;

    public AlgorithmException() {
        super("The referred algorithm does not exist");
    }

    public AlgorithmException(String message) {
        super(message);
    }
}
