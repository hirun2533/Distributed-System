/*--------------------------------------------------------

1. Hirunya Hirunsirisombut /  Date: 05/2/19
   
2. Java version that I used

java version "11.0.1"

3. Run with command:

javac MyWebServer.java and then java MyWebServer

4. Instructions to run this program:

> java MyWebServer

5. List of files needed for running the program.

 a. checklist-mywebserver.html
 b. MyWebServer.java
 c. http-streams.txt
 d. serverlog.txt

6. Notes:

I took the main method from my jokeserver and some parts are from ReadFiles.java
I didn't show up content of the file.

----------------------------------------------------------*/


import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.file.Paths;
public class MyWebServer {
	
	public static void main(String args[]){ 
		int q_len = 6; 
		int port = 2540; 
		Socket socket; 
		BufferedReader readin;
		try{ 
			ServerSocket servsocket = new ServerSocket(port, q_len); 
			System.out.println("Hirunya's WebServer is on at port " + port);

			while(true){ 
				socket = servsocket.accept(); 
				readin = new BufferedReader(new InputStreamReader(socket.getInputStream())); 

				String str = ""; String strreq = ""; 
				
				if ((strreq = readin.readLine()) != null) { 
					if (strreq.contentEquals("")) { 
						break; 
					}
					else if (str.contentEquals("")) { 
						str  = strreq; 
					}	
				}
				if (!str.contentEquals("")) { 
					new ForWorkerweb(str, socket).start();
				}
			}
		}
		catch(IOException ex){ 
			System.out.println("Server has been shutdown!");
		}
	}
}


class ForWorkerweb extends Thread { 
	
	Socket sock; 
	String reqclient; 
	private InputStream tempNewFile; 
	private static final int BUFFNUM = 10000; 
	public ForWorkerweb (String textvalid, Socket s) /
	{
		this.sock = s; 
		reqclient = textvalid;
	}

	public void run(){ 
		try{ 
								  
			PrintStream writeout = new PrintStream(sock.getOutputStream()); 
			
			if (!reqclient.endsWith("HTTP/1.1") ||!reqclient.startsWith("GET")) { 
				
				
				System.out.println("");
				System.out.println("Hirunya's Web Server is on");
				System.out.println("Error requested! " + reqclient);
				writeout.println("Something went wrong with your requested.");

			}
			
				
			else { 
					String textvalid = reqclient.substring(4, reqclient.length()-9).trim();	
					textvalid = URLDecoder.decode(textvalid, "UTF-8");
				
					if (textvalid.endsWith("/")) { 
						textvalid = textvalid.substring(0, textvalid.length() - 1); 
					}

					if (textvalid.indexOf(".") >= 0) { 
						
						if (textvalid.indexOf("/cgi/") >= 0) { 
							
							ForAddnumReq(textvalid, writeout);
						}
						else 
						{  if (!textvalid.contains(".ico")&&!textvalid.startsWith("/images/")) { 
								System.out.println();
								System.out.println("Hirunya's Web Server is on");
								System.out.println(reqclient);

							}
							ForDirReq(textvalid, writeout); 
						}
					}
					else { ForGetReq(textvalid, writeout); 
					}
				
				}

			sock.close();
		}
		catch(IOException ex){
			System.out.println(ex);
		}
	}

	private void ForAddnumReq(String textvalid, PrintStream writeout) throws UnsupportedEncodingException, MalformedURLException { 
		
		HashMap<String, String> numaddvalid = new HashMap<String, String>();
		URL urlvalid = new URL("http:/" + textvalid); 
		
		String getqry = urlvalid.getQuery(); 
		String[] splitter  = getqry.split("&");
		
		for (String letter : splitter) { 
			int intvalid = letter.indexOf("="); 
			numaddvalid.put(URLDecoder.decode(letter.substring(0, intvalid), "UTF-8"), 
			URLDecoder.decode(letter.substring(intvalid + 1), "UTF-8"));
		
		}
		
		
		try {
			
		Integer numvalid1 = Integer.parseInt(numaddvalid.get("num1"));
		Integer numvalid2 = Integer.parseInt(numaddvalid.get("num2"));

		if (numvalid1 != null || numvalid2 != null) {
			
			System.out.println(numvalid1 + " + " + numvalid2 + " = " + (numvalid1+numvalid2));
			
			writeout.println(numaddvalid.get("person") + ", your calculation ");
			writeout.println(numaddvalid.get("num1") + " and " + numaddvalid.get("num2") + " is " + numvalid1+numvalid2);
			
		}
		} catch (NumberFormatException e) {
			writeout.println("Your number is wrong! Please try again!");
		}
	}

	private void ForDirReq(String textvalid, PrintStream writeout) throws FileNotFoundException, IOException { 
		String dirroot = ""; 
		try{ 
			File filevalid = new File("."); 
			dirroot = filevalid.getCanonicalPath(); 
		
		String getdir = dirroot;
		
		String temppathdir = Paths.get(getdir, textvalid).toString();
  
		File tempfile = new File(temppathdir); 
		if (!tempfile.exists()) { 
			writeout.println("Invalid data:" + textvalid); 
		}
		else { 
			if (!textvalid.startsWith("/images/")&&!textvalid.contains("/favicon.ico")) {
            
            StringBuilder makestring = new StringBuilder(); 
            makestring.append("HTTP/1.1 200 OK \r\n"); 
            makestring.append("Content-Length: " + tempfile.length() +"\r\n"); 
            makestring.append("Content-Type: "+ ForMIMEType(textvalid)+"\r\n\r\n");
             
           	writeout.println(makestring.toString()); 
			
			System.out.println("HTTP/1.1 200 OK");
			System.out.println("Content-Length: " + tempfile.length());
			System.out.println("Content-Type: "  + ForMIMEType(textvalid));
			}
           
			tempNewFile = new FileInputStream(tempfile); 
			byte[] BuffByte= new byte[BUFFNUM]; 
			while (tempNewFile.available() > 0) { 
				writeout.write(BuffByte, 0, tempNewFile.read(BuffByte));
			}
     
		}
		}
		catch(IOException ex){ 
			ex.printStackTrace();
		}
		
	}

	
	private void ForGetReq(String textvalid, PrintStream writeout) throws IOException  { 
	
		
	try{ 
		
		String dir = textvalid; 
		BufferedWriter tempscreen = new BufferedWriter(new FileWriter("Invalidfile.html")); 
		File tempfile = new File("./" + textvalid + "/"); 	
		File[] tempfileDir = tempfile.listFiles(); 
		tempscreen.write("<h1>Webserver Folder of Hirunya</h1>");  
		tempscreen.write("<h2><a href=\"" + "http://localhost:2540" + "/\">" + "Home" + "</a></h2>"); 
		 
		
		for(File tempfilefordir : tempfileDir) { 
			String tempnamefile = tempfilefordir.getName(); 
			if (tempnamefile.startsWith("Invalidfile.html") || tempnamefile.startsWith(".")) { 
				continue; 
			}	
			
			if (tempfilefordir.isDirectory()) { 
				tempscreen.write("<a href=\"" + tempnamefile + "/\">" + tempnamefile + "</a><br><br/>"); 
			}
			if (tempfilefordir.isFile()) { 
				tempscreen.write("<a href=\"" + tempnamefile + "\">" + tempnamefile + "</a><br><br/>"); 
              
                
				
			}
		if(textvalid.endsWith("/")) { 
				int splitfile = -1; 
				String[] splitter = textvalid.split("/"); 
				splitfile = splitter[1].length() + 1; 
				textvalid = textvalid.substring(0, textvalid.length() - splitfile - 1); 
         
			}	
			tempscreen.flush();
			 
		}
		File tempFile = new File("Invalidfile.html"); 
		InputStream tempstream = new FileInputStream("Invalidfile.html");
    
        
        
		writeout.println("HTTP/1.1 200 OK");
		writeout.println("Content-Length: " + tempFile.length());
		writeout.println("Content-Type: "  + ForMIMEType(textvalid));
		writeout.println("\r\n\r\n");
		System.out.println();
		System.out.println("HTTP/1.1 200 OK");
		System.out.println("Content-Length: " + tempFile.length()); 
		System.out.println("Content-Type: "  + ForMIMEType(textvalid));
		System.out.println("You are in subdirectory: " + dir);
		
        byte[] BuffByte = new byte[BUFFNUM]; 
        int numberOfBytes = tempstream.read(BuffByte); 
        writeout.write(BuffByte, 0, numberOfBytes); 
        tempscreen.close(); 
        tempstream.close();
        writeout.flush(); 
		 tempFile.delete();
		
	}
			catch(IOException ex){
			ex.printStackTrace();
		}
		
	}


	private static String ForMIMEType(String temptype)	{
		if (temptype.equals("") || temptype.lastIndexOf(".") < 0 ) { 
	 		return "text/html"; 
		}
		String typevalid = temptype.substring(temptype.lastIndexOf(".")); 
		if(typevalid.endsWith("html"))
			typevalid = "text/html"; 
		else if(typevalid.endsWith(".java")) 
			typevalid = "text/plain"; 
		else if(typevalid.endsWith(".txt")) 
			typevalid = "text/plain";
		else 
			typevalid = "text/plain"; 
		return typevalid; 
	}

	
}

