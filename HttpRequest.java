
public class HttpRequest {
	
	//1-request 2-filename 3-httpversion
	
	//only filename is important
	String filename;
	
	// we to create a constructor to accept string
	public HttpRequest(String request){
		
		//now we have to request 
		String lines[] = request.split("\n");
		// we got all the lines of request
		
		//splitting the first line using space
		// and selects the second item which is filename
		filename = lines[0].split(" ")[1];
		
	}
}
