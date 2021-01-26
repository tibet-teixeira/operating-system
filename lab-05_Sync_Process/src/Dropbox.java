public class Dropbox {
    public Monitor monitor;
    private boolean evenNumber = false;
    private int number = -1;

    public Dropbox(Monitor monitor) {
        this.monitor = monitor;
    }

    public boolean lock(final boolean even) {
        if ((evenNumber == even) && (this.number >= 0)) return monitor.lock();

        return false;
    }

    public void release() {
        this.number = -1;
        monitor.release();
        monitor.producerRelease();
    }

    public boolean released() {
        return monitor.producerReleased;
    }

    public void take(final boolean even) {
        if (evenNumber == even) {
            System.out.format("%s CONSUMIDOR obtem %d.%n", even ? "PAR" : "ÍMPAR", number);
        }
    }

    public void put(int number) {
        monitor.producerLock();

        evenNumber = number % 2 == 0;
        this.number = number;

        System.out.format("PRODUTOR gera %d.%n", number);
    }
}