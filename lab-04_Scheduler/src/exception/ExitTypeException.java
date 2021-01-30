package exception;

public class ExitTypeException extends RuntimeException {

    public ExitTypeException() {
        super("The referred exitType is invalid, you must use: \n1 = Statistic or 2 = List of processes");
    }

    public ExitTypeException(String message) {
        super(message);
    }
}
