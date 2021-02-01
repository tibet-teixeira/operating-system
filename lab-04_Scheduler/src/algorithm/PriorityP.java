package algorithm;

import model.BCP;
import model.Process;
import model.queue.Queue;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PriorityP extends Algorithm {
    private BCP getPriorityJob(Queue readyQueue, int currentUnitTime) {
        Predicate<BCP> byArrivalTime = bcp -> bcp.getProcess().getArrivalTime() <= currentUnitTime;
        List<BCP> bcps = readyQueue.getAll().stream().filter(byArrivalTime).collect(Collectors.toList());

        if (bcps.size() == 0) {
            return null;
        }

        if (bcps.size() == 1) {
            return bcps.get(0);
        }

        bcps.sort(Comparator.comparingInt(bcp -> bcp.getProcess().getPriority()));

        return bcps.get(0);
    }

    @Override
    public void run(Queue readyQueue, Queue runningQueue, Queue terminatedQueue) {
        BCP bcp;
        Process process;
        int currentUnitTime = 0;
        int waitingTime;

        final int CPU_TIME_EXECUTED = 1;

        while (readyQueue.length() > 0) {
            bcp = getPriorityJob(readyQueue, currentUnitTime);

            if (bcp == null) {
                currentUnitTime += 1;
                continue;
            } else {
                readyQueue.remove(bcp);
            }

            process = bcp.getProcess();
            runningQueue.add(bcp);

            waitingTime = currentUnitTime - bcp.getLastUnitTimeExecuted();

            if (bcp.isFirstExecuted()) {
                waitingTime -= process.getArrivalTime();
                bcp.setFirstExecuted(false);
                bcp.setFirstUnitTimeExecuted(currentUnitTime);
            }

            bcp.setTotalWaitingTime(bcp.getTotalWaitingTime() + waitingTime);
            bcp.setTotalBurstExecuted(bcp.getTotalBurstExecuted() + CPU_TIME_EXECUTED);
            bcp.setTurnaroundTime(bcp.getTotalWaitingTime() + bcp.getTotalBurstExecuted());
            currentUnitTime += CPU_TIME_EXECUTED;
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
