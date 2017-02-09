
import java.io.IOException;
import java.net.*;

public class Main {
	
	ServerSocket serverSocket;
	static int port = 8109;
	
	public static void main(String [] args) throws Exception
	{
		new Main().runServer(); // to avoid problem with static field
	}

	
	public void runServer() throws Exception{
		System.out.println("Server has started");
		serverSocket = new ServerSocket(port);
		acceptRequest();
		
	}
	private void acceptRequest() throws Exception{
		while (true)// to accept all the request
		{
			// connection to client is in the form of socket which contains the stream 
			//for input and output
			Socket s = serverSocket.accept();
			ConnectionHandler ch = new ConnectionHandler(s);
			
			ch.start();
		}
 	}
}

