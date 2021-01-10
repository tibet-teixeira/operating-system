package questao4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MailBoxServer {
    public static void searchInMailBox(String id, ArrayList<String> mailbox, PrintWriter pout){
        String[] messageReceived;
        int i = 0;

        while(i < mailbox.size()){
            messageReceived = mailbox.get(i).split(":");
            if(Integer.parseInt(messageReceived[3]) == Integer.parseInt(id)){
                pout.println("Mensagem: " + messageReceived[1] + "\nEnviado por: " + messageReceived[2] + "\n");
            }
            i++;
        }
    }

    public static void verifyMailBox(String mensagem, ArrayList<String> mailbox, PrintWriter pout){
        String[] messageReceived = mensagem.split(":");
        String action = messageReceived[0].toLowerCase();
        if (action.equals("enviar")) {
            mailbox.add(mensagem);
        } else if (action.equals("receber")) {
            pout.println("\n\nCaixa de entrada\n");
            searchInMailBox(messageReceived[1], mailbox, pout);
        } else {
            pout.println("Operação inválida");
        }
    }

    public static void main(String[] args) throws IOException {
        Socket client = null;
        ServerSocket sock = null;
        BufferedReader bin;
        InputStream in;
        ArrayList<String> mailbox = new ArrayList<>();
        String line;

        try {
            sock = new ServerSocket(6013);

            while (true) {
                client = sock.accept();

                in = client.getInputStream();
                bin = new BufferedReader(new InputStreamReader(in));
                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);

                while((line = bin.readLine()) != null){
                    verifyMailBox(line, mailbox, pout);
                }

                pout.close();
                client.close();
            }
        } catch (IOException ioe) {
            System.err.println(ioe);
        } finally {
            if (sock != null)
                sock.close();
            if (client != null)
                client.close();
        }
    }
}
