import algorithm.*;
import controller.ExecutionReport;
import controller.Scheduler;
import model.queue.Queue;
import model.queue.ReadyQueue;
import utils.FunctionUtils;

public class Main {

    public static void main(String[] args) {
        FunctionUtils.verifyArgs(args);

        if (args.length == 1) {
            System.out.println(
                    "Usage: java escalonador filename.csv algorithm exit_type [quantum] (e.g. java escalonador processes.csv FCFS 1)"
                            + "\n   Exit Type: 1 = Statistic or 2 = List of processes "
                            + "\n   Algorithms: FCFS, SJF, SJFP, Priority, PriorityP and RR");
            return;
        }

        String filename = args[0];
        Algorithm algorithm = FunctionUtils.defineAlgorithm(args[1]);
        int quantum = 0;
    
        if (algorithm instanceof RoundRobin) {
            if (args.length == 3)
                ((RoundRobin) algorithm).setQuantum(quantum = 1);
            else
                ((RoundRobin) algorithm).setQuantum(quantum = FunctionUtils.defineQuantum(args[3]));
        }

        Queue readyQueue = new ReadyQueue();
        FunctionUtils.parseCSV(filename, readyQueue);
        Scheduler scheduler = new Scheduler(algorithm, readyQueue);
        scheduler.run();

        ExecutionReport executionReport = FunctionUtils.defineExitType(args[2], scheduler.getTerminatedQueue(), filename, args[1], quantum);
        executionReport.showResult();
        executionReport.saveResult();
    }
}
