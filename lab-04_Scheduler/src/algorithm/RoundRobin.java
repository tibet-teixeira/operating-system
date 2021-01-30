package algorithm;

import model.BCP;
import model.Process;
import model.queue.Queue;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RoundRobin extends Algorithm {
    int quantum;

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    private BCP getNextJob(Queue readyQueue, int currentUnitTime) {
        Predicate<BCP> byArrivalTime = bcp -> bcp.getProcess().getArrivalTime() <= currentUnitTime;
        List<BCP> bcps = readyQueue.getAll().stream().filter(byArrivalTime).collect(Collectors.toList());

        if (bcps.size() == 0) {
            return null;
        }

        return bcps.get(0);
    }

    @Override
    public void run(Queue readyQueue, Queue runningQueue, Queue terminatedQueue) {
        BCP bcp;
        Process process;
        int currentUnitTime = 0;
        int totalRemainingBurst;
        int waitingTime;

        while (readyQueue.length() > 0) {
            bcp = getNextJob(readyQueue, currentUnitTime);

            if (bcp == null) {
                currentUnitTime += 1;
                continue;
            } else {
                readyQueue.remove(bcp);
            }

            process = bcp.getProcess();
            runningQueue.add(bcp);
            totalRemainingBurst = process.getBurstTime() - bcp.getTotalBurstExecuted();


            waitingTime = currentUnitTime - bcp.getLastUnitTimeExecuted();

            if (bcp.isFirstExecuted()) {
                waitingTime -= process.getArrivalTime();
                bcp.setFirstExecuted(false);
            }

            bcp.setTotalWaitingTime(bcp.getTotalWaitingTime() + waitingTime);
            bcp.setTotalBurstExecuted(bcp.getTotalBurstExecuted() + Math.min(totalRemainingBurst, quantum));
            bcp.setTurnaroundTime(bcp.getTotalWaitingTime() + bcp.getTotalBurstExecuted());
            currentUnitTime += Math.min(totalRemainingBurst, quantum);
            bcp.setLastUnitTimeExecuted(currentUnitTime);

            bcp = runningQueue.pop();

            if (bcp.getTotalBurstExecuted() == process.getBurstTime()) {
                terminatedQueue.add(bcp);
            } else {
                readyQueue.add(bcp);
            }
        }
    }
}
