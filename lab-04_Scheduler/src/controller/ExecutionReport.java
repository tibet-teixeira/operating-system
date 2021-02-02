package controller;

import model.queue.Queue;

public abstract class ExecutionReport {
    protected Queue terminatedQueue;
    protected String algorithm;
    protected int quantum;
    protected String[] header;

    public ExecutionReport(Queue terminatedQueue, String[] header, String algorithm, int quantum) {
        this.terminatedQueue = terminatedQueue;
        this.algorithm = algorithm;
        this.quantum = quantum;
        this.header = header;
    }

    public void saveResult() {
        System.out.println("Implementation defined by the subclasses");
    }

}
