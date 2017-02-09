import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.AccessDeniedException;

public class HttpResponse {
	
	HttpRequest req;
	
	// this is the final response which is generated
	String response;
	
	//root path of the server,folder which contains all file
	String document_root = "/Users/aishwaryapatil/Documents/workspace2/HttpServer/root";

	public HttpResponse(HttpRequest request) throws Exception{
		req = request;
		//we have to open a file mentioned in request
		File f = new File(document_root + req.filename);
		
		try{
			
			response = "HTTP/1.0 200 \r \n"; //version of http and 200 for status code
			response += "Server: Our Java Server /1.0 \r \n"; //identity of server
			response += "Connection :close \r\n";
			response += "Content-Type: text/html \r \n"; // response in html format
			response += "Content-Length:" +f.length() +"\r\n"; //length of response file
			response += "\r\n";
			
			FileInputStream fis = new FileInputStream(f);
			int s;
			while((s = fis.read()) != -1) 
			{
				response += (char) s;
			}
			fis.close();
			
		}catch (FileNotFoundException e){
			response = response.replace("200", "404");
			//System.out.println("File not found");
			
		}catch(AccessDeniedException e){
			response = response.replaceAll("200", "401");
			System.out.println("Access denied");
		}catch(Exception e){
			response = response.replace("200", "400");
			System.out.println("Bad request error");
		}
		
	}
}
