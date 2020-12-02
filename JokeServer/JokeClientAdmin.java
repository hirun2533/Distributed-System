
/*--------------------------------------------------------

1. Hirunya Hirunsirisombut /  Date: 04/11/19
   
2. Java version that I used:

java version "11.0.1"

3. Run with command :

javac JokeClientAdmin.java and then java JokeClientAdmin

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


import java.io.*; 
import java.net.*; 

public class JokeClientAdmin 

	
	public static void main (String args[]){
		String servName;
		if(args.length<1) servName = "localhost"; 
		 										  
	
		else servName = args[0]; 
				
		System.out.println("Hello from Client admin" + servName + "at Port: 5050"); 
		
		BufferedReader readin = new BufferedReader(new InputStreamReader(System.in)); 
		String TempIn; 
		
		try{ 
			do{ 
			
			
			System.out.println("Let's choose Jokes('J') or Proverbs('P') or Maintenance('M') or 'quit'"); 
			System.out.flush();
			TempIn = readin.readLine(); 
			
			TakeInfo(TempIn, servName); 
	
			}
			while(TempIn.indexOf("quit") < 0); 
			
			System.out.println ("Good Bye~~~");n
		}
		catch (IOException x ){
            x.printStackTrace();
	
	}
	}

static void TakeInfo (String TempIn, String servName){
	Socket sock; 
	PrintStream toMain; 
	BufferedReader fromMain; 

	
	try
	{
		sock = new Socket(servName, 5050); 
		
		toMain = new PrintStream(sock.getOutputStream()); 
		
		
		fromMain = new BufferedReader(new InputStreamReader(sock.getInputStream())); 
		
		
		toMain.println(TempIn); 
		
		toMain.flush();
		
		System.out.println(fromMain.readLine());
	
		}catch (IOException x) 
	{ System.out.println ("socket is down"); 
	x.printStackTrace ();}
	}	

}