package controller;

import model.queue.Queue;

public class Statistic extends ExecutionReport {
    public Statistic(Queue terminatedQueue) {
        super(terminatedQueue);
    }

    @Override
    public void showResult() {
        //TODO: Implement the method that returns the results
        System.out.println("STATISTIC");
        super.terminatedQueue.showAll();
    }
}
