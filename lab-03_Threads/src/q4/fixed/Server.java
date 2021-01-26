/**
 * Time-of-day server listening to port 6013.
 *
 */
 
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

class Logger implements Runnable
{
	private PrintWriter PW;
	public Logger(PrintWriter clientWriter){
		this.PW = clientWriter;
	}
	public void run(){
		System.out.println(" Thread Run");
		this.PW.println(new java.util.Date().toString());
		this.PW.close();


	}
}

public class DateServer
{
	public static void main(String[] args) throws IOException {
		Socket client = null;
		ServerSocket sock = null;
		ExecutorService pool = Executors.newFixedThreadPool(4);
		try {
			sock = new ServerSocket(6013);
	
			while (true) {
				
				
				client = sock.accept();

				System.out.println("server = " + sock);

				System.out.println("client = " + client);

				pool.execute(new Logger(new PrintWriter(client.getOutputStream(), true)));

				

			}
		}
		catch (Exception e) {
				System.err.println(e);
		}
		finally {
			if (sock != null)
				sock.close();
				pool.shutdown();
			if (client != null){
				client.close();
			}
				
		}
	}
}
