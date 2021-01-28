package model;

public class Process {
    private int id;
    private int priority;
    private int burstTime;
    private int arrivalTime;

    public Process(int id, int priority, int arrivalTime, int burstTime) {
        this.id = id;
        this.priority = priority;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }

    public int getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }
}