package utils;

import algorithm.*;
import controller.ExecutionReport;
import controller.ProcessList;
import controller.Statistic;
import exception.*;
import model.BCP;
import model.Process;
import model.queue.Queue;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FunctionUtils {
    private static final String CSV_DIVISOR = ",";
    private static final String CSV_DATA_DIRECTORY = "data";
    private static final String CSV_RESULT_DIRECTORY = "result";
    private static final String SRC_DIRECTORY = "src";
    private static final String EXTENSION_FILE = ".csv";

    public static String getPathProject() {
        Path currentRelativePath = Paths.get("");
        return currentRelativePath.toAbsolutePath().toString();
    }

    public static void parseCSV(String filename, Queue readyQueue) {
        String path = getPathProject() + File.separator
                + SRC_DIRECTORY + File.separator
                + CSV_DATA_DIRECTORY + File.separator;
        path += filename;

        BufferedReader br = null;
        FileReader fileReader = null;
        String[] values;

        try {
            fileReader = new FileReader(path);
            br = new BufferedReader(fileReader);
            String line;

            while ((line = br.readLine()) != null) {
                values = line.split(CSV_DIVISOR);

                createProcess(values, readyQueue);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void createProcess(String[] values, Queue readyQueue) {
        int arrivalTime = Integer.parseInt(values[0]);
        int id = Integer.parseInt(values[1]);
        int burstTime = Integer.parseInt(values[2]);
        int priority = Integer.parseInt(values[3]);

        Process process = new Process(id, priority, arrivalTime, burstTime);

        BCP bcp = new BCP(process);
        readyQueue.add(bcp);
    }

    public static Algorithm defineAlgorithm(String arg) {
        switch (arg.toUpperCase()) {
            case "RR":
                return new RoundRobin();
            case "FCFS":
                return new FCFS();
            case "SJF":
                return new SJF();
            case "SJFP":
                return new SJFP();
            case "PRIORITY":
                return new Priority();
            case "PRIORITYP":
                return new PriorityP();
            default:
                throw new AlgorithmException();
        }
    }

    public static ExecutionReport defineExitType(String arg,
                                                 Queue terminatedQueue,
                                                 String algorithm,
                                                 int quantum) {
        int exitType = Integer.parseInt(arg);

        switch (exitType) {
            case 1:
                return new Statistic(terminatedQueue, algorithm, quantum);
            case 2:
                return new ProcessList(terminatedQueue, algorithm, quantum);
            default:
                throw new ExitTypeException();
        }
    }

    public static int defineQuantum(String arg) {
        int quantum = Integer.parseInt(arg);

        if (quantum < 1) {
            throw new QuantumException();
        }

        return quantum;
    }

    public static void verifyArgs(String[] args) {
        String messageException = "Arguments do not follow the standard. Check the correct way in --help";

        if (((args.length == 1) && !(args[0].toLowerCase().equals("--help")))
                || ((args.length != 1) && (args.length != 3) && (args.length != 4)))
            throw new IllegalArgumentException(messageException);
    }

    public static void writeFile(String[] header, float[] data, String algorithm, String exitType) {
        writeFile(header, data, algorithm, exitType, 0);
    }

    public static void writeFile(String[] header, float[] data, String algorithm, String exitType, int quantum) {
        try {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");

            String path = getPathProject() + File.separator
                    + SRC_DIRECTORY + File.separator
                    + CSV_RESULT_DIRECTORY + File.separator;

            String filename;

            if (algorithm.toLowerCase().equals("rr")) {
                filename = path + exitType + "_"
                        + algorithm + "_quantum_" + quantum + "_"
                        + formatter.format(date) + EXTENSION_FILE;
            } else {
                filename = path + exitType + "_"
                        + algorithm + "_"
                        + formatter.format(date) + EXTENSION_FILE;
            }

            new File(path).mkdir();
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(filename));

            StringBuilder headerStr = new StringBuilder();
            StringBuilder dataStr = new StringBuilder();

            for (int i = 0; i < header.length; i++) {
                headerStr.append(header[i]).append(CSV_DIVISOR);
                dataStr.append(data[i]).append(CSV_DIVISOR);
            }

            headerStr.deleteCharAt(headerStr.length() - 1);
            dataStr.deleteCharAt(dataStr.length() - 1);
            headerStr.append("\n");
            dataStr.append("\n");

            writer.write(String.valueOf(headerStr));
            writer.write(String.valueOf(dataStr));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(String[] header,
                                 int[] data1, int[] data2,
                                 String algorithm, String exitType) {
        writeFile(header, data1, data2, algorithm, exitType, 0);
    }

    public static void writeFile(String[] header,
                                 int[] data1, int[] data2,
                                 String algorithm, String exitType, int quantum) {
        try {
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");

            String path = getPathProject() + File.separator
                    + SRC_DIRECTORY + File.separator
                    + CSV_RESULT_DIRECTORY + File.separator;
            String filename;

            if (algorithm.toLowerCase().equals("rr")) {
                filename = path + exitType + "_"
                        + algorithm + "_quantum_" + quantum + "_"
                        + formatter.format(date) + EXTENSION_FILE;
            } else {
                filename = path + exitType + "_"
                        + algorithm + "_"
                        + formatter.format(date) + EXTENSION_FILE;
            }

            new File(path).mkdir();
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(filename));

            StringBuilder headerStr = new StringBuilder();
            StringBuilder dataStr = new StringBuilder();

            for (String s : header) {
                headerStr.append(s).append(CSV_DIVISOR);
            }

            for (int i = 0; i < data1.length; i++) {
                dataStr.append(data1[i]).append(CSV_DIVISOR).append(data2[i]).append("\n");
            }

            headerStr.deleteCharAt(headerStr.length() - 1);
            headerStr.append("\n");

            writer.write(String.valueOf(headerStr));
            writer.write(String.valueOf(dataStr));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
