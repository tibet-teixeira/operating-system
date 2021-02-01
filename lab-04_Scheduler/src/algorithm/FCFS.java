package algorithm;

import model.BCP;
import model.Process;
import model.queue.Queue;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FCFS extends Algorithm {

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

            waitingTime = Math.max(currentUnitTime - process.getArrivalTime(), 0);

            if (bcp.isFirstExecuted()) {
                bcp.setFirstExecuted(false);
                bcp.setFirstUnitTimeExecuted(currentUnitTime);
            }

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
