package model.queue;

public class TerminatedQueue extends Queue {
    @Override
    public void showAll() {
        super.getAll().forEach(el -> {
            System.out.print("Process ID: " + el.getIdProcess());
            System.out.print(" Arrival Time: " + el.getProcess().getArrivalTime());
            System.out.print(" Burst Time: " + el.getProcess().getBurstTime());
            System.out.print(" Turnaround Time: " + el.getTurnaroundTime());
            System.out.print(" Total Waiting Time: " + el.getTotalWaitingTime());
            System.out.println(" Last Unit Time: " + el.getLastUnitTimeExecuted());
        });


    }
}
