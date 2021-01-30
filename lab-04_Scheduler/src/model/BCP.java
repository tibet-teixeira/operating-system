package model;

public class BCP {
    private Process process;
    private int totalBurstExecuted;
    private int lastUnitTimeExecuted;
    private int totalWaitingTime;
    private int turnaroundTime;
    private boolean firstExecuted;

    public BCP(Process process) {
        this.process = process;
        this.totalBurstExecuted = 0;
        this.lastUnitTimeExecuted = 0;
        this.totalWaitingTime = 0;
        this.turnaroundTime = 0;
        this.firstExecuted = true;
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

    public boolean isFirstExecuted() {
        return firstExecuted;
    }

    public void setFirstExecuted(boolean firstExecuted) {
        this.firstExecuted = firstExecuted;
    }
}
