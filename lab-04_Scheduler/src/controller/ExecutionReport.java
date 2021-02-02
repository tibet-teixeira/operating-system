package controller;

import java.io.File;

import model.queue.Queue;
import utils.FunctionUtils;

public abstract class ExecutionReport {
    protected Queue terminatedQueue;
    protected String algorithm;
    protected int quantum;
    protected String outputFile;
    protected String resultsFilePath = FunctionUtils.getPathProject() + File.separator + "results" + File.separator;

    public ExecutionReport(Queue terminatedQueue, String outputFile, String algorithm, int quantum) {
        this.terminatedQueue = terminatedQueue;
        this.algorithm = algorithm;
        this.outputFile = outputFile;
        this.quantum = quantum;
    }

    public void showResult() {
        System.out.println("Implementation defined by the subclasses");
    }
}
