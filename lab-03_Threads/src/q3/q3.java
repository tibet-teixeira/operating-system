import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DateServer {
    public static void main(String[] args) {
        try {
            ServerSocket sock = new ServerSocket(6013);
            while (true){
                Socket client = sock.accept();
                new ServerThread(client).start();

            }
        }
    }
}

public class ServerThread extends Thread{
    Socket socket;
    ServerThread(Socket socket){
        this.socket = socket;
    }

    public void run(){
    PrintWriter pout = new PrintWriter(socket.getOutputStream(),true);
    pout.println(new java.util.Date().toString());
    socket.close();


    }
}