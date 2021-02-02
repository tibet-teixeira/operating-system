package model;

public class BCP {
    private Process process;
    private int totalBurstExecuted;
    private int lastUnitTimeExecuted;
    private int firstUnitTimeExecuted;
    private int totalWaitingTime;
    private int turnaroundTime;
    private boolean firstExecuted;
    private int runningTimes;

    public BCP(Process process) {
        this.process = process;
        this.totalBurstExecuted = 0;
        this.lastUnitTimeExecuted = 0;
        this.firstUnitTimeExecuted = 0;
        this.totalWaitingTime = 0;
        this.turnaroundTime = 0;
        this.firstExecuted = true;
        this.runningTimes = 0;
    }

    public int getIdProcess() {
        return this.process.getId();
    }

    public Process getProcess() {
        return this.process;
    }

    public int getTotalBurstExecuted() {
        return totalBurstExecuted;
    }

    public void setTotalBurstExecuted(int totalBurstExecuted) {
        this.totalBurstExecuted = totalBurstExecuted;
    }

    public int getLastUnitTimeExecuted() {
        return lastUnitTimeExecuted;
    }

    public void setLastUnitTimeExecuted(int lastUnitTimeExecuted) {
        this.lastUnitTimeExecuted = lastUnitTimeExecuted;
    }

    public int getTotalWaitingTime() {
        return totalWaitingTime;
    }

    public void setTotalWaitingTime(int totalWaitingTime) {
        this.totalWaitingTime = totalWaitingTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setTurnaroundTime(int turnaroundTime) {
        this.turnaroundTime = turnaroundTime;
    }

    public int getFirstUnitTimeExecuted() {
        return firstUnitTimeExecuted;
    }

    public void setFirstUnitTimeExecuted(int firstUnitTimeExecuted) {
        this.firstUnitTimeExecuted = firstUnitTimeExecuted;
    }

    public int getResponseTime() {
        return this.getFirstUnitTimeExecuted() - this.process.getArrivalTime();
    }

    public boolean isFirstExecuted() {
        return firstExecuted;
    }

    public void setFirstExecuted(boolean firstExecuted) {
        this.firstExecuted = firstExecuted;
    }

    public void addRunningTimes() {
        this.runningTimes ++;
    }

    public int getRunningTimes() {
        return this.runningTimes;
    }
}
