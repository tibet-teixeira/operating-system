package model.queue;

import model.BCP;

import java.util.ArrayList;
import java.util.List;

public abstract class Queue {
    private List<BCP> blocks;

    protected Queue() {
        blocks = new ArrayList<>();
    }

    public List<BCP> getAll() {
        return this.blocks;
    }

    public void showAll() {
        blocks.forEach(el -> {
            System.out.print("Process ID: " + el.getIdProcess());
            System.out.print(" Arrival Time: " + el.getProcess().getArrivalTime());
            System.out.print(" Burst Time: " + el.getProcess().getBurstTime());
            System.out.print(" Turnaround Time: " + el.getTurnaroundTime());
            System.out.print(" Total Waiting Time: " + el.getTotalWaitingTime());
            System.out.println(" Last Unit Time: " + el.getLastUnitTimeExecuted());
        });
    }

    public void add(BCP bcp) {
        blocks.add(bcp);
    }

    public BCP get(int index) {
        return this.blocks.get(index);
    }

    public void remove(BCP bcp) {
        this.blocks.remove(bcp);
    }

    public BCP pop() {
        return this.blocks.remove(0);
    }

    public int length() {
        return this.blocks.size();
    }
}
