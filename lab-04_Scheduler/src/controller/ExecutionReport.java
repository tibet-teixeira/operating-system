package controller;

import model.queue.Queue;

public abstract class ExecutionReport {
    protected Queue terminatedQueue;

    public ExecutionReport(Queue terminatedQueue) {
        this.terminatedQueue = terminatedQueue;
    }

    public void showResult() {
        System.out.println("Implementation defined by the subclasses");
    }
}
