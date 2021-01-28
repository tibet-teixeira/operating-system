package controller;

import algorithm.Algorithm;
import model.queue.Queue;
import model.queue.RunningQueue;
import model.queue.TerminatedQueue;

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

    public void run() {
        System.out.println("Algorithm: " + algorithm);
        readyQueue.showAll();
    }
}
