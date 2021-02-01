package model.queue;

public class TerminatedQueue extends Queue {
    @Override
    public void showAll() {
        super.getAll().forEach(el -> System.out.println("Process ID: " + el.getIdProcess()
                + " Turnaround Time: " + el.getTurnaroundTime()));
    }
}
