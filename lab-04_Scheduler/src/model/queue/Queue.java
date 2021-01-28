package model.queue;

import model.BCP;

import java.util.ArrayList;
import java.util.List;

public abstract class Queue {
    private List<BCP> blocks;

    protected Queue() {
        blocks = new ArrayList<>();
    }

    public void showAll() {
        blocks.forEach(el -> System.out.println("Process id: " + el.getIdProcess()));
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
}
