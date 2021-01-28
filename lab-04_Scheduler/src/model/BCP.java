package model;

public class BCP {
    private Process process;
    private int totalRuntime;
    private int lastUnitTimeExecuted;
    private int totalWaitingTime;

    public BCP(Process process) {
        this.process = process;
        this.totalRuntime = 0;
        this.lastUnitTimeExecuted = 0;
        this.totalWaitingTime = 0;
    }

    public int getIdProcess() {
        return this.process.getId();
    }

    public Process getProcess() {
        return this.process;
    }

    public void setTotalRuntime(int totalRuntime) {
        this.totalRuntime = totalRuntime;
    }

    public void setLastUnitTimeExecuted(int lastUnitTimeExecuted) {
        this.lastUnitTimeExecuted = lastUnitTimeExecuted;
    }

    public void setTotalWaitingTime(int totalWaitingTime) {
        this.totalWaitingTime = totalWaitingTime;
    }
}
