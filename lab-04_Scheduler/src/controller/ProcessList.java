package controller;

import model.queue.Queue;

public class ProcessList extends ExecutionReport {
    public ProcessList(Queue terminatedQueue) {
        super(terminatedQueue);
    }

    @Override
    public void showResult() {
        //TODO: Implement the method that returns the results
        System.out.println("PROCESS LIST");
        super.terminatedQueue.showAll();
    }
}
