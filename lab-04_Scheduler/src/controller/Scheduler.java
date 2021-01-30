package controller;

import algorithm.Algorithm;
import model.queue.Queue;
import model.queue.RunningQueue;
import model.queue.TerminatedQueue;

import java.util.Comparator;

public class Scheduler {
    private Algorithm algorithm;
    private Queue readyQueue;
    private Queue runningQueue;
    private Queue terminatedQueue;

    public Scheduler(Algorithm algorithm, Queue readyQueue) {
        this.algorithm = algorithm;
        this.readyQueue = readyQueue;
        this.runningQueue = new RunningQueue();
        this.terminatedQueue = new TerminatedQueue();
    }

    private void sortProcessesList() {
        this.readyQueue.getAll().sort(Comparator.comparingInt(o -> o.getProcess().getArrivalTime()));
    }

    public void run() {
        sortProcessesList();
        algorithm.run(readyQueue, runningQueue, terminatedQueue);
    }

    public Queue getTerminatedQueue() {
        return this.terminatedQueue;
    }
}
