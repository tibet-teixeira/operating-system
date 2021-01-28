package algorithm;

import model.queue.*;

public abstract class Algorithm {
    public void run(Queue readyQueue, Queue runningQueue, Queue terminatedQueue) {
        System.out.println("Implementation defined by the subclasses");
    }
}
