/**
 * Client program requesting current date from server.
 *
 */
 
import java.net.*;
import java.io.*;

public class Client
{
	public static void main(String[] args) throws IOException {
		InputStream input = null;
		BufferedReader bin = null;
		Socket sock = null;

		try {
			sock = new Socket("127.0.0.1",6013);
			input = sock.getInputStream();
			bin = new BufferedReader(new InputStreamReader(input));

			String line;
			while( (line = bin.readLine()) != null)
				System.out.println(line);
		}
		catch (Exception e) {
				System.err.println(ioe);
		}
                finally {
                    sock.close();
                }
	}
}
