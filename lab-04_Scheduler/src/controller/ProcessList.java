package controller;

import model.BCP;
import model.queue.Queue;

import java.util.Comparator;

public class ProcessList extends ExecutionReport {
    public ProcessList(Queue terminatedQueue) {
        super(terminatedQueue);
    }

    @Override
    public void showResult() {
        System.out.println("PROCESS LIST");
        super.terminatedQueue.getAll().sort(Comparator.comparingInt(BCP::getFirstUnitTimeExecuted));
        super.terminatedQueue.showAll();
    }
}
