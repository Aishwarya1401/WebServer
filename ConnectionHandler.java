import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//this class basically handles all the connection which contains request
public class ConnectionHandler extends Thread{ //by extending thread , this class can become thread
	
	Socket s;
	
	PrintWriter pw; //for sending output to client
	
	BufferedReader br; // for getting input from client
	
	
	//constructor
	//which accepts as socket
	public ConnectionHandler(Socket s) throws Exception{
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		pw = new PrintWriter(s.getOutputStream());
	}
	
	//thread class contains a method run which is automatically run which thread is called
	//in this method we have to read the request and give the response
	public void run(){
		//super.run();
		String reqS ="";
		//here we get the request string and give this string to httpRequest class
		
		//from br we have to read our request
		//read until request not found
		try {
			while(br.ready() || reqS.length() == 0)
			{
				reqS += (char) br.read();
			}
			System.out.println(reqS); //for display
			
			HttpRequest req = new HttpRequest(reqS);
			HttpResponse  res = new HttpResponse(req);
			
			pw.write(res.response.toCharArray());
			pw.close();
			br.close();
			s.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	

}
