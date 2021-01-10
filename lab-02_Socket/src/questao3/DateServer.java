/**
 * Time-of-day server listening to port 6013.
 */
package questao3;

import java.net.*;
import java.io.*;

public class DateServer {
    public static void main(String[] args) throws IOException {
        Socket client = null;
        ServerSocket sock = null;
        BufferedReader bin;
        InputStream in;

        try {
            sock = new ServerSocket(6013);
            while (true) {
                client = sock.accept();

                in = client.getInputStream();
                bin = new BufferedReader(new InputStreamReader(in));

                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);

                String line;
                String[] expr = null;
                while( (line = bin.readLine()) != null){
                    expr = line.split(":");
                    pout.println(calculate(Double.parseDouble(expr[0]), expr[1], Double.parseDouble(expr[2])));
                }

                pout.close();
                client.close();
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
        finally {
            if (sock != null)
                sock.close();
            if (client != null)
                client.close();
        }
    }

    private static String calculate(double op1, String operation, double op2) {
        switch (operation) {
            case "+":
                return Double.toString(op1 + op2);
            case "-":
                return Double.toString(op1 - op2);
            case "*":
                return Double.toString(op1 * op2);
            case "/":
                try {
                    return Double.toString(op1 / op2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return "Error";
    }
}
