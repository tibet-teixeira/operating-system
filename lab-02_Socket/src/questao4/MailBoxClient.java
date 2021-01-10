package questao4;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MailBoxClient {
    public static void main(String[] args) throws IOException {
        InputStream in;
        BufferedReader bin;
        Socket sock = null;
        String messageSent = null;
        Scanner reader = new Scanner(System.in);

        try {
            sock = new Socket("127.0.0.1", 6013);
            PrintWriter pout = new PrintWriter(sock.getOutputStream(), true);

            while (true) {
                System.out.println("Informe o seu identificador");
                String senderId = reader.next();

                System.out.println("Você deseja ENVIAR ou RECEBER a mensagem");
                String action = reader.next().toLowerCase();

                if (action.equals("enviar")) {
                    System.out.println("Digite a mensagem");
                    String message = reader.next();

                    System.out.println("Informe o identificador do destinatário");
                    String receiverId = reader.next();
                    System.out.println("Mensagem enviada\n\n");

                    messageSent = action + ":" + message + ":" + senderId + ":" + receiverId;
                } else if (action.equals("receber")) {
                    messageSent = action + ":" + senderId;
                } else {
                    System.err.println("Operação inválida");
                }

                pout.println(messageSent);

                in = sock.getInputStream();
                bin = new BufferedReader(new InputStreamReader(in));

                String line;
                if (action.equals("receber")) {
                    while ((line = bin.readLine()) != null) {
                        System.out.println("\t" + line);
                    }
                }
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        } finally {
            sock.close();
        }
    }
}
