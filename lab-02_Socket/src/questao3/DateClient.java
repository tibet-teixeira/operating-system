/**
 * Client program requesting current date from server.
 */
package questao3;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class DateClient {
    public static void main(String[] args) throws IOException {
        InputStream in;
        BufferedReader bin;
        PrintWriter pout;
        Socket sock = null;
		Scanner reader = new Scanner(System.in);

		try {
            sock = new Socket("127.0.0.1", 6013);
            pout = new PrintWriter(sock.getOutputStream(), true);

			System.out.println("Informe a operação");
			String operation = reader.next();

			System.out.println("Informe o primeiro operando");
			String op1 = reader.next();

			System.out.println("Informe o segundo operando");
			String op2 = reader.next();

			String expr = op1 + ":" + operation + ":" + op2;
			pout.println(expr);

			in = sock.getInputStream();
            bin = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = bin.readLine()) != null)
                System.out.println("Resultado: " + line);
        } catch (IOException ioe) {
            System.err.println(ioe);
        } finally {
            sock.close();
        }
    }
}
