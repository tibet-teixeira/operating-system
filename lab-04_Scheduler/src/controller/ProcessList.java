package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import model.BCP;
import model.queue.Queue;

public class ProcessList extends ExecutionReport {
    public ProcessList(Queue terminatedQueue, String outputFile, String algorithm, int quantum) {
        super(terminatedQueue, outputFile, algorithm, quantum);
    }

    @Override
    public void saveResult() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");

        super.terminatedQueue.getAll().sort(Comparator.comparingInt(BCP::getFirstUnitTimeExecuted));
        super.terminatedQueue.showAll();
        
        try {
            FileWriter writer = new FileWriter(super.resultsFilePath + "process_list_" + this.algorithm + "_" + formatter.format(date) + "_.csv");

            for (BCP bcp : this.terminatedQueue.getAll()) {
                writer.write(bcp.getIdProcess() + "," + bcp.getTurnaroundTime() + "\n");
            }
            
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
      
    }
}
