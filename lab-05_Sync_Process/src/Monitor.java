public class Monitor {
    public boolean released;
    public boolean producerReleased;

    public Monitor() {
        this.released = true;
        this.producerReleased = true;
    }

    public boolean lock() {
        if (released) {
            this.released = false;
            return true;
        }
        return false;
    }

    public void release() {
        this.released = true;
    }

    public void producerLock() {
        this.producerReleased = false;
    }

    public void producerRelease() {
        this.producerReleased = true;
    }

}
