/*--------------------------------------------------------

1. Hirunya Hirunsirisombut /  Date: 05/31/19
   
2. Java version that I used

java version "1.8.0_211"

3. Run with command:

javac HostServer.java 

4. Instructions to run this program:

 java HostServer

5. List of files needed for running the program.

 -

6. Notes:

-

----------------------------------------------------------*/



import java.io.*; //import java libs
import java.net.*;

/**
 * This program will implement the migrate that will be used for change the port and server
 * to the other one of them. As I test this program, it can connect with the different
 * browser except IE browser. It can be connected one than one localhost number 1565.
 */

/* For this class, agentworker it will implement the request that comes from agentlistener
 * which is get the request whenever it see the migrate, implement too. That is, will change to
 * the other port that was connected instead the provious one
 */ 
 
class AgentWorker extends Thread { //class for agaentworker
	
	Socket sock;  //declare socket for connect to other side(client)
	
	agentHolder parentAgentHolder; //get construct state to take care of socket 
	
	int localPort; //declare the new port for get connection from client
	
	
	AgentWorker (Socket s, int prt, agentHolder ah) { //declare the constructor
		sock = s; //declare the socket for constructor
		localPort = prt; //declare the localport for constructor
		parentAgentHolder = ah; //declare state for constructor
	}
	public void run() {
		
		
		PrintStream out = null; //declare new printstream 
		BufferedReader in = null; //declare new buffer reader
		
		String NewHost = "localhost"; //declare new string which is specific for this declaration
		
		int NewHostMainPort = 1565; //declare the specific port number		
		String buf = ""; //declare new sting which is just space
		int newPort; //declare new variable
		Socket clientSock; //declare socket name
		BufferedReader fromHostServer; //declare bufferreader name
		PrintStream toHostServer; //declare printstream name
		
		try {
			out = new PrintStream(sock.getOutputStream()); //get the information that will pass argument throught socket and send to the client
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));  //get the information from the client pass through argument socket. 
			
		
			String inLine = in.readLine(); //declare string for get the input
			
			//It's use for every browser excpet IE, which specify the length of the content
			//that will comes up with html content and easy to see their information
			StringBuilder htmlString = new StringBuilder(); //declare string of builder
			
			System.out.println(); //print new line
			System.out.println("Request line: " + inLine); //it will show the console terminal to get input from user
			
			if(inLine.indexOf("migrate") > -1) { //the if statement that check to see the migrate
				//that is will see that if user type this word, so it will allow the brandnew port for user
			
				
				clientSock = new Socket(NewHost, NewHostMainPort); //declare the new socket with argument from main to work with port 1565
				fromHostServer = new BufferedReader(new InputStreamReader(clientSock.getInputStream())); //get the input from server
				
				toHostServer = new PrintStream(clientSock.getOutputStream()); //it will get the new port by sent asking to 1565 port
				toHostServer.println("Please host me. Send my port! [State=" + parentAgentHolder.agentState + "]"); //show the information to the browser
				toHostServer.flush(); //it will flush
				
				//wait for the response and read a response until we find what should be a port
				for(;;) { //it will get the correct response
					
					buf = fromHostServer.readLine(); //implement the information
					if(buf.indexOf("[Port=") > -1) { //then check for the legal port
						break; //break it
					}
				}
				
				
				String tempbuf = buf.substring( buf.indexOf("[Port=")+6, buf.indexOf("]", buf.indexOf("[Port=")) ); //separate those string from the port 
				
				newPort = Integer.parseInt(tempbuf); //put the request for this declaration int with has a new port information
				
				System.out.println("newPort is: " + newPort); //show it to the screen console terminal
				
				
				htmlString.append(AgentListener.sendHTMLheader(newPort, NewHost, inLine)); //get string html to show at browser
			
				htmlString.append("<h3>We are migrating to host " + newPort + "</h3> \n"); //show the information of the migrate when it was asked
				htmlString.append("<h3>View the source of this page to see how the client is informed of the new location.</h3> \n"); //show this information at the browser
				htmlString.append(AgentListener.sendHTMLsubmit()); //send string to be finihed

				
				System.out.println("Killing parent listening loop."); //it will show information of kill server which is waiting in the console terminal 

				ServerSocket ss = parentAgentHolder.sock; //take the last socket paste it to the parentAgentHolder
				
				ss.close(); //port will be close
				
				
			} else if(inLine.indexOf("person") > -1) { //other case, it will increment the number integer to return the result
				parentAgentHolder.agentState++; //keep count it
				
				htmlString.append(AgentListener.sendHTMLheader(localPort, NewHost, inLine)); //show the information html at the browser for the state of agaent
				htmlString.append("<h3>We are having a conversation with state   " + parentAgentHolder.agentState + "</h3>\n"); //show the information at the browser
				htmlString.append(AgentListener.sendHTMLsubmit()); //send string to be finished

			} else { //error case, that the information is not correct
					
				htmlString.append(AgentListener.sendHTMLheader(localPort, NewHost, inLine)); //show the information html at the browser 
				htmlString.append("You have not entered a valid request!\n"); //show the information html at the browser that input is not correct
				htmlString.append(AgentListener.sendHTMLsubmit()); //send string to be finished		
				
		
			}
			
			AgentListener.sendHTMLtoStream(htmlString.toString(), out);  //it will send this output information html
			
			
			
			sock.close();//socket will be close
			
			
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
	
}


///This class name agentHolder will be take care of information and data of state
///thta is easy to catch up those informations and pass argument to the port
class agentHolder { //class name
	
	ServerSocket sock; //declare server socket
	
	int agentState; //declare new variable
	
	
	agentHolder(ServerSocket s) { sock = s;} //build the construct
}

//This class will keep look every port and answer those requests
//The hostserver will made it when it see a new asking on the port 1565

class AgentListener extends Thread { //class name
	
	Socket sock; //declare socket
	int localPort; //declare new variable
	
	//basic constructor
	AgentListener(Socket As, int prt) { //build a construct
		sock = As; //declare socket for constructor
		localPort = prt;  //declare port for constructor
	}
	
	int agentState = 0; //declare new variable and set to zero
	
	
	public void run() { //it will be run when get a request
		BufferedReader in = null; //set bufferreader to null
		PrintStream out = null; //set printstream to null
		String NewHost = "localhost"; //declare new string and specify string
		System.out.println("In AgentListener Thread"); //show the information to user at the console terminal		 
		try {
			String buf; //declare new string
			out = new PrintStream(sock.getOutputStream()); //get the information that will pass argument throught socket and send to the client
			in =  new BufferedReader(new InputStreamReader(sock.getInputStream())); //get the information from the client pass through argument socket.
			
			
			buf = in.readLine(); //take input and read it
			
			
			if(buf != null && buf.indexOf("[State=") > -1) { //if statement when its see specify string then get request keep it
				
				String tempbuf = buf.substring(buf.indexOf("[State=")+7, buf.indexOf("]", buf.indexOf("[State="))); //separate the string from buf
				
				agentState = Integer.parseInt(tempbuf); //then use parseInt 
				
				System.out.println("agentState is: " + agentState); //then show the information to the console terminal
					
			}
			
			System.out.println(buf); //then show the information to the console terminal of buf
			
			StringBuilder htmlResponse = new StringBuilder(); //declare string to build html
			
			//show the port and display the form. we know agentstate is 0 since game hasnt been started
			htmlResponse.append(sendHTMLheader(localPort, NewHost, buf)); //it will display html to the browser of the output
			htmlResponse.append("Now in Agent Looper starting Agent Listening Loop\n<br />\n"); //show the information to the browser
			htmlResponse.append("[Port="+localPort+"]<br/>\n");//show the information to the browser
			htmlResponse.append(sendHTMLsubmit()); //send those string 
			
			sendHTMLtoStream(htmlResponse.toString(), out); //send those output and show it
			
			
			ServerSocket servsock = new ServerSocket(localPort,2); //turn the connect
			
			agentHolder agenthold = new agentHolder(servsock); //declare a new agentHolder and keep arguments of socket and state
			agenthold.agentState = agentState; //sedn information to the agentState
			
			 
			while(true) { //whenever is true or have connection
				sock = servsock.accept(); //it will wait the connection
				
				System.out.println("Got a connection to agent at port " + localPort); //show at the screen terminal
				
				new AgentWorker(sock, localPort, agenthold).start(); //then start the connection after declare AgentWorker
			}
		
		} catch(IOException ioe) { //get error if possible
			
			System.out.println("Either connection failed, or just killed listener loop for agent at port " + localPort); //show the information to the terminal
			System.out.println(ioe); ////show the information to the terminal
		}
	}
	
	//the header html will be implemented over here
	
	static String sendHTMLheader(int localPort, String NewHost, String inLine) { //declare string static
		
		StringBuilder htmlString = new StringBuilder(); //declare string for build html

		htmlString.append("<html><head> </head><body>\n"); //it will create html header
		htmlString.append("<h2>This is for submission to PORT " + localPort + " on " + NewHost + "</h2>\n"); //it will create information about port
		htmlString.append("<h3>You sent: "+ inLine + "</h3>"); //it will create information
		htmlString.append("\n<form method=\"GET\" action=\"http://" + NewHost +":" + localPort + "\">\n"); //it will create information Get method
		htmlString.append("Enter text or <i>migrate</i>:"); //it will create input bullet for input 
		htmlString.append("\n<input type=\"text\" name=\"person\" size=\"20\" value=\"YourTextInput\" /> <p>\n"); //it will create html body
		
		return htmlString.toString(); //and then return those string builder
	}
	
	static String sendHTMLsubmit() { //declare string static
		return "<input type=\"submit\" value=\"Submit\"" + "</p>\n</form></body></html>\n"; //then return the header of html
	}
	
	static void sendHTMLtoStream(String html, PrintStream out) { //declare void method for information content type length
		
		out.println("HTTP/1.1 200 OK"); //it will send this header information to the browser
		out.println("Content-Length: " + html.length()); //it will send this information length
		out.println("Content-Type: text/html"); //it will send this information content type
		out.println(""); //it will send this information to the browser		 
		out.println(html); //it will send those strings to the browser
	}
	
}


//the main class which will listen the specific port number 
//that is less than 3000 will be accept

public class HostServer { //program name
	
	public static int NextPort = 3000; //declare the port for listen
	
	public static void main(String[] a) throws IOException { //the main class
		int q_len = 6; //declare the number length of queue
		int port = 1565; //declare specific port number
		Socket sock; //declare the socket
		
		ServerSocket servsock = new ServerSocket(port, q_len); //get the new serversocket and pass argument of port and length of queue
		System.out.println("Hirunya's DIA Master receiver started at port 1565."); //show information at the console terminal
		System.out.println("Connect from 1 to 3 browsers using \"http:\\\\localhost:1565\"\n"); //show information at the console terminal
		
		while(true) {
			NextPort = NextPort + 1; //plus one for the next port
			
			sock = servsock.accept(); //get request
			
			System.out.println("Starting AgentListener at port " + NextPort); //show at the console terminal
			
			new AgentListener(sock, NextPort).start(); //build the new AgentListener
		}
		
	}
}
