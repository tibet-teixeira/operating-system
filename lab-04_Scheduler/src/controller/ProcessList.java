package controller;

import java.util.Comparator;

import model.BCP;
import model.queue.Queue;
import utils.FunctionUtils;

public class ProcessList extends ExecutionReport {
    private static String[] HEADER = new String[]{"process_id", "turnaround_time"};

    public ProcessList(Queue terminatedQueue, String algorithm, int quantum) {
        super(terminatedQueue, HEADER, algorithm, quantum);
    }

    @Override
    public void saveResult() {
        super.terminatedQueue.getAll().sort(Comparator.comparingInt(BCP::getFirstUnitTimeExecuted));
        super.terminatedQueue.showAll();

        int arraySize = terminatedQueue.getAll().size();
        int[] idProcessData = new int[arraySize];
        int[] turnaroundTimeData = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            idProcessData[i] = terminatedQueue.get(i).getIdProcess();
            turnaroundTimeData[i] = terminatedQueue.get(i).getTurnaroundTime();
        }

        FunctionUtils.writeFile(super.header, idProcessData, turnaroundTimeData, super.algorithm, "process_list");

    }
}
