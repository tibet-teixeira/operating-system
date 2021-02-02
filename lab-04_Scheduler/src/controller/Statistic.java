package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.BCP;
import model.queue.Queue;

public class Statistic extends ExecutionReport {
    public Statistic(Queue terminatedQueue, String outputFile, String algorithm, int quantum) {
        super(terminatedQueue, outputFile, algorithm, quantum);
    }


    @Override
    public void saveResult() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");

        String quantum = Integer.toString(this.quantum);
        if(this.quantum == 0) 
            quantum = "No quantum used";
        
        String statisticHeader = "-- Algorithm: " + this.algorithm + " -- Quantum: " + quantum + " -- Output file: " + this.outputFile + " --\n";

        int    timeTotalProcessed = 0;
        float  cpuUsage;

        float    throughputTotal = 0;
        float  throughputMean;

        float    turnaroundTotal = 0;
        float  turnaroundMean;

        float    waitingTimeTotal = 0;
        float  waitingTimeMean;

        float    responseTimeTotal = 0;
        float  responseTimeMean;   

        float    contextSwitchTotal = 0;
        float  contextSwitchMean;

        int    numberOfProcesses = 0;

        for (BCP bcp : this.terminatedQueue.getAll()) {
            timeTotalProcessed += bcp.getTotalBurstExecuted();
            turnaroundTotal    += bcp.getTurnaroundTime();
            waitingTimeTotal   += bcp.getTotalWaitingTime();
            responseTimeTotal  += bcp.getResponseTime();
            contextSwitchTotal += bcp.getRunningTimes();
            numberOfProcesses ++;
        }

        contextSwitchTotal = ( contextSwitchTotal - 1 );
        cpuUsage          = ((timeTotalProcessed - contextSwitchTotal) / timeTotalProcessed) * 100;
        throughputMean    = numberOfProcesses / numberOfProcesses;
        turnaroundMean    = turnaroundTotal / numberOfProcesses;
        waitingTimeMean   = waitingTimeTotal / numberOfProcesses;
        responseTimeMean  = responseTimeTotal / numberOfProcesses;
        contextSwitchMean = contextSwitchTotal / timeTotalProcessed;

        String statisticBody = "Time Total Processed %d - CPU Usage %.2f%% - Throughput Mean %.2f\n"
        + "Turnaround Mean %.2f - Waiting Time Mean %.2f - Response Time Mean %.2f \n"
        + "Context Switch Mean %.2f - Number of Processes %d\n";

        System.out.println(statisticHeader);
        System.out.printf(statisticBody, timeTotalProcessed, cpuUsage, throughputMean, turnaroundMean, waitingTimeMean, responseTimeMean, contextSwitchMean, numberOfProcesses);

        try {
            FileWriter writer = new FileWriter(super.resultsFilePath + "statistic_" + this.algorithm + "_" + formatter.format(date) + "_.csv");

            writer.write("time_total,cpu_usage,throughput_mean,turnaround_mean,waiting_time_mean,response_time_mean,context_switch_mean,number_of_processes\n");
            writer.write(timeTotalProcessed + "," + cpuUsage + "," + throughputMean + "," + turnaroundMean + "," + waitingTimeMean 
                        + "," + responseTimeMean + "," + contextSwitchMean + "," + numberOfProcesses + "\n");

            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
      
    }
}
