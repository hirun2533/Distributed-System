

/*--------------------------------------------------------

1. Hirunya Hirunsirisombut /  Date: 04/11/19
   
2. Java version that I used

java version "11.0.1"

3. Run with command:

javac JokeServer.java and then java JokeServer

4. Instructions to run this program:

Run in different shell:

> java JokeServer
> java JokeClient
> java JokeClientAdmin

All acceptable commands are displayed on the various consoles.

I tested to runs across machines. 

> java JokeClient 192.168.1.2
> java JokeClientAdmin 192.168.1.2

5. List of files needed for running the program.

 a. checklist.html
 b. JokeServer.java
 c. JokeClient.java
 d. JokeClientAdmin.java

6. Notes:

My server program will send the joke and proverb cycle when its completed at the same time when
you press enter after the cycle done. It will show up same time with the next Joke and proverbs.
My JokeClient and JokeClientAdmin codes are come from professor resources.


----------------------------------------------------------*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;
import java.util.Random;



public class JokeServer {
	
	
	static boolean StateJokePro = true;  
	static boolean backoffMain = false; 
	public static String genJID;
	public static String genPID;
	
	

	public static void main(String a[]) throws IOException { 
		
		
		int q_len = 6; 
		int portnum = 4545; 
		Socket sock; 

		ForClientAdmin FA = new ForClientAdmin(); 
		
		Thread thread = new Thread(FA);
		thread.start(); 
		ServerSocket servsock = new ServerSocket(portnum, q_len); 

		System.out.println("Hirunya's JokeServer listening at port number:" + portnum);
		while (true) {      
			sock = servsock.accept(); 
			new ForClient(sock).start(); 
		}
	}
}

//** this class forClient thread which is work with client side to connect and have communication to each other**//

class ForClient extends Thread { 
	Socket sock; 
	

	 static ArrayList<String> joke = new ArrayList<String>(); 
	 static ArrayList<String> proverb = new ArrayList<String>(); 
	
  //I got jokes and proverbs from Thai website(pantip.com and englisgbychris.com) in google.
	String jokeA = "If I am a fortuneteller, Do you know what I am going to do? : Buy the lottery!!";
	String jokeB = "I haven’t slept for three days, because that would be too long.";
	String jokeC = "I recently decided to sell my vacuum cleaner, all it was doing was gathering dust.";
	String jokeD = "You know it’s hot when electric bills for A/C are more than the house payment.";
	
	String proverbA = "Fine features make fine birds.";
	String proverbB = "Listeners hear no good of themselves";
	String proverbC = "Who has never tasted bitterness does not know what is sweet";
	String proverbD = "Don’t shrink any task because of its arduousness";	
	
	
	ForClient(Socket s) {
		this.sock = s;
	
	}

	public void run() { 
				
		BufferedReader readin = null; 
		PrintStream writeout = null; 
		try {
			readin = new BufferedReader(new InputStreamReader(sock.getInputStream())); 
			writeout = new PrintStream(sock.getOutputStream()); 
			try {
				String username = readin.readLine(); 
				JokeServer.genJID = readin.readLine();
				JokeServer.genPID = readin.readLine(); 
				
				String TempIn = readin.readLine(); 
			
				if(JokeServer.StateJokePro == true && JokeServer.genJID.equals("1111")) 
																						
				{																		
					JokeServer.genJID = "0000"; 
					System.out.println("Joke cycle completed");


				} 
				
				  
				if(JokeServer.StateJokePro == false && JokeServer.genPID.equals("1111")) 
				
				{
					JokeServer.genPID = "0000";
					System.out.println("Proverb cycle completed");
					
				}
					

				if(JokeServer.backoffMain == true) {   
					writeout.println("Sorry!! we are not available at this time");
					writeout.println(JokeServer.genJID); 
					writeout.println(JokeServer.genPID); 
				}
				else {
				if (JokeServer.StateJokePro == true && ( (TempIn.length() < 1)) || TempIn.indexOf(" ") >= 0) { 
						
																											 
						writeout.println(RandJoke(username)); 
						

						writeout.println(JokeServer.genJID); 
						
						writeout.println(JokeServer.genPID); 
						
						System.out.println(username + " is now playing in mode Joke");

						
					} else  if (JokeServer.StateJokePro == false && ((TempIn.length() < 1))|| TempIn.indexOf(" ") >= 0 ) { 
						writeout.println(RandProverb(username)); 
						
						writeout.println(JokeServer.genJID);
						writeout.println(JokeServer.genPID); 
						
						System.out.println(username + " is now playing mode Proverb");
							
					} else if (TempIn.indexOf("quit") < 0) {  
						writeout.println("You did something wrong with "+ TempIn + "just press 'ENTER' "); 
						
						writeout.println(JokeServer.genJID); 
						
						writeout.println(JokeServer.genPID);
					}
				}
				
			} catch (IOException x) { 
				System.out.println("The Server Down now"); 
				x.printStackTrace();
			}
			
			sock.close(); 
		} catch (IOException ioe) { 
			System.out.println(ioe);/
		}
		
	}	
		
	
	//** randjoke to get random joke from array list and return joke text string to client**//

	public String RandJoke(String username) { 
	
		
		 boolean numValid = false; 
		 Random r = new Random(); 
		 int index = r.nextInt(4); 
	    
		   joke.add("JA: " + " " + jokeA); 
		   joke.add("JB: " + " " + jokeB);
		   joke.add("JC: " + " " + jokeC);
		   joke.add("JD: " + " " + jokeD);	
		   
		while(numValid == false) {
			
		index = r.nextInt(4);
		   
		   if(JokeServer.genJID.charAt(index) == '1')  {  
			   numValid = false;
			   
			 
		   }
		   else {
			   numValid = true;
			   String JIDcopy = JokeServer.genJID;  
			   String tempgenJID = ""; /

			   for (int i = 0; i < 4; i++ ) { 
				   if (i == index) {
					   tempgenJID += '1'; 
				   } 
				   else if (JokeServer.genJID.charAt(i) == '1') { 
					   tempgenJID += '1'; 
					   
				   } else   
				       tempgenJID += '0';     
			   }
			   JokeServer.genJID = tempgenJID; 
		   }
		  
		 }
		return joke.get(index); 
	 	
	}
	
	
	//** randproverb to get random joke from array list and return proverb text string to client**//

	private String RandProverb(String username) {

		boolean numValid = false; 
		 Random r = new Random();
		 int index = r.nextInt(4); 
		 
		   proverb.add("PA: " + " " + proverbA); 
		   proverb.add("PB: " + " " + proverbB);
		   proverb.add("PC: " + " " + proverbC);
		   proverb.add("PD: " + " " + proverbD);	
		     
			while(numValid == false) { 
			index = r.nextInt(4); 
			   
			   if(JokeServer.genPID.charAt(index) == '1')  { 
				   numValid = false;
				  
			   }
			   else {
				   numValid = true;
				   String PIDcopy = JokeServer.genPID; 
				   String tempgenPID = ""; 

				   for (int i = 0; i < 4; i++ ) { 
					   if (i == index) { 
						   tempgenPID += '1';
					   else if (JokeServer.genPID.charAt(i) == '1') { 
						   tempgenPID += '1';
						   
					   } else
					       tempgenPID += '0';    
				   }
				   JokeServer.genPID = tempgenPID;
			   }
			  
			 }
		   
	   return proverb.get(index); 
	   
	   
}

}
//** this thread will work with admin client side to choose the mode from admin client**//
class ForWorkerAdmin extends Thread {

	Socket sock;

	ForWorkerAdmin(Socket s) {
		sock = s;
	}

	public void run() {
		PrintStream writeout = null;
		BufferedReader readin = null;

		try {

			readin = new BufferedReader(new InputStreamReader(sock.getInputStream())); 
			writeout = new PrintStream(sock.getOutputStream());

				String state = readin.readLine(); /
				switch(state.toString()) { 
				case "J":   
					JokeServer.backoffMain = false;
					JokeServer.StateJokePro = true;
					writeout.println("Mode Joke is turn on");
					System.out.println("Mode Joke is ON");
					break;
					
				case "P" :
					JokeServer.backoffMain = false;
					JokeServer.StateJokePro = false; 
					writeout.println("Mode Proverb is turn on");
					System.out.println("Mode Proverb turn on");
					break;
				case "M" : 
					JokeServer.backoffMain = true; 
					writeout.println("Mode Maintenance is ON");
					System.out.println("Mode Maintenance is ON"); 
					break;
					
				case "quit" :  
					writeout.println("Have a wonderful day!!"); 
					System.out.println("user just exit from ClientAdmin"); 
					
					break;
					
				default: 
					writeout.println("Something not quite correct with  "
							+ state + " try 'J' or 'P' or 'M' ");
					break;
					
				}
				
			} catch (IOException ioe) {
				System.out.println(ioe); 
			}
		}
}

//** this function will connect with clientadmin in the port 5050 **//

class ForClientAdmin implements Runnable {


		public void run() { 
			System.out.println("Starting at Client Admin");

			int q_len = 6;
			int port = 5050; 
			Socket sock; 

			try {
				ServerSocket servsock = new ServerSocket(port, q_len); 
				while (true) {
					
					sock = servsock.accept(); 
					new ForWorkerAdmin(sock).start(); 
				}
			} catch (IOException ioe) { 
				System.out.println(ioe);
			}
		}

	}
	
