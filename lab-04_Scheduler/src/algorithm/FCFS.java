package algorithm;

import model.BCP;
import model.Process;
import model.queue.Queue;

public class FCFS extends Algorithm {

    @Override
    public void run(Queue readyQueue, Queue runningQueue, Queue terminatedQueue) {
        BCP bcp;
        Process process;
        int currentUnitTime = 0;
        int waitingTime;

        while (readyQueue.length() > 0) {
            bcp = readyQueue.pop();
            process = bcp.getProcess();
            runningQueue.add(bcp);

            waitingTime = Math.max(currentUnitTime - process.getArrivalTime(), 0);
            bcp.setTotalWaitingTime(waitingTime);
            bcp.setTotalBurstExecuted(process.getBurstTime());
            bcp.setTurnaroundTime(waitingTime + process.getBurstTime());

            currentUnitTime += process.getBurstTime();
            bcp.setLastUnitTimeExecuted(currentUnitTime);

            bcp = runningQueue.pop();
            terminatedQueue.add(bcp);
        }

    }
}
