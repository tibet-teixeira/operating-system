package exception;

public class AlgorithmException extends RuntimeException {

    public AlgorithmException() {
        super("The referred algorithm is invalid, you must use: Algorithms: FCFS, SJF, SJFP, Priority, PriorityP and RR");
    }

    public AlgorithmException(String message) {
        super(message);
    }
}
