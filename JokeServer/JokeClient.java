

/*--------------------------------------------------------

1.Hirunya Hirunsirisombut /  Date: 04/11/19
   
2. Java version that I used

java version "11.0.1"

3. Run with command :

javac JokeClient.java and then java JokeClient

4. Instructions to run this program:

Run in different shell:

> java JokeServer
> java JokeClient
> java JokeClientAdmin

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


public class JokeClient { 
	
	
	
   static String genJID; 
   static String genPID; 
   
  
	public static void main (String args[]) {

		boolean numValid = true;
		String servN; 
		
		if(args.length < 1) 
			servN = "localhost"; 
		else servN = args[0]; 
		
		System.out.println("Hirunya's Joke Client" + servN + "at port No. 4545");
		
		BufferedReader readin = new BufferedReader(new InputStreamReader(System.in));
		genJID = "0000"; 
        genPID = "0000"; 
	
		
       String urname = null; 
       
       
       try { 
       	do{ 
       	System.out.print("Hello! Let me know your name:  ");
			urname = readin.readLine(); 
			if(urname.length() < 0)  
			{
				System.out.println("Oops! just type something(name)!");	
			} 
			else 
			  break; 
			
			
       	} while(numValid); 
		} catch (IOException error) { 
			error.printStackTrace();
		}
      
       
      
		try{ 
			String tempValid; 
			do{ 						
				System.out.print("Please hit enter to read funnyjokes or boringproverbs or BackofficeMaintenance or 'quit' to close connection: "); 
				System.out.flush(); 
				tempValid = readin.readLine(); 
				
				TakeInfo(tempValid, servN, urname); 
				
				
			}while (tempValid.indexOf("quit") < 0); 
			
			System.out.println ("User just exit!!"); 

		
           
		}catch (IOException ex) 
		{ex.printStackTrace();}
	}
	 static void TakeInfo (String tempValid, String servN, String urname ){
			
			Socket s = null; 
			PrintStream toMain; 
			String txtFromServ; 
			BufferedReader fromMain; 
			
			try{

				s = new Socket(servN, 4545); 
				
				
				fromMain = new BufferedReader(new InputStreamReader(s.getInputStream())); 
				toMain = new PrintStream(s.getOutputStream());
				
				toMain.println(urname); 
				
				toMain.println(genJID); 
				
				toMain.println(genPID); 
				
				toMain.println(tempValid); 
		
				
				toMain.flush();

				txtFromServ = fromMain.readLine(); 
					
					genJID = fromMain.readLine(); 
					
					genPID = fromMain.readLine(); 
					
					if(txtFromServ != null)System.out.println("\n " + urname + "  " + txtFromServ + "\n"); 
 
					if(genJID != null) System.out.println("Joke mode ID of "+ urname + " is: "+ genJID); 
					
					
					
					if(genPID != null)System.out.println("Proverbs mode ID of "+ urname + " is: "+ genPID); 
					
					
					
				s.close(); 
				}catch (IOException x) 
				{ System.out.println ("socket is down."); 
				
			}
		}

	
   
   
	
	
}
