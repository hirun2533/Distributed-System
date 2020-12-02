
/*--------------------------------------------------------

1. Hirunya Hirunsirisombut /  Date: 05/25/19
   
2. Java version that I used

java version "1.8.0_211"

3. Run with command:

javac Blockchain.java 

4. Instructions to run this program:

>java Blockchain 0
 java Blockchain 1
 java Blockchain 2

5. List of files needed for running the program.

 a. BlockInput0.txt, BlockInput1.txt, BlockInput2.txt
 b. Blockchain.java 


6. Notes:

I took most of the codes from Professor Clark Elliott on the examples.
Sometime my verification Credit show different from each processes, but
sometimes it shows correctly. I have only V signature for the fake error report
and others V-R-C and L for this programs.
Sometime when you press L for each record, it will slightly delay show record 
in the real time running program from each console.
----------------------------------------------------------*/


import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;
import java.net.*;
import java.security.spec.*;
import java.security.*;


@XmlRootElement
class BlockTempRecord{ 
  
    
    String TempSHA256; 
    String TempSHASigned; 
    String TempVerificationProcess; 
    String TempPreHash; 
    String TempSeed; 
    String TempBlockNum; 
    String TempBlockID; 
    String TempSignedBlockID; 
    String TempProcess; 
    String TempTime; 
    String TempDataHash; 
    String TempFN; 
    String TempLN; 
    String TempDOB; 
    String TempSSN; 
    String TempProgn; 
    String TempHeal; 
    String TempMed; 
   
    
    public String getATempTime() {return TempTime;}  
    @XmlElement
      public void setATempTime(String TT) {this.TempTime = TT;}

  
    public String getATempDataHash() {return TempDataHash;} 
    @XmlElement
      public void setATempDataHash(String TDH) {this.TempDataHash = TDH;} 

  
    public String getATempPreHash() {return TempPreHash;} 
    @XmlElement
      public void setATempPreHash(String TDH) {this.TempPreHash = TDH;} 

  
    public String getATempSeed() {return TempSeed;} 
    @XmlElement
      public void setATempSeed(String TSEED) {this.TempSeed = TSEED;} 
    
    public String getATempBlockNum() {return TempBlockNum;} 
    @XmlElement
        public void setATempBlockNum(String TBN){this.TempBlockNum = TBN;} 


    public String getATempSHA256() {return TempSHA256;} 
    @XmlElement
        public void setATempSHA256(String TSH){this.TempSHA256 = TSH;}


    public String getATempSHASigned() {return TempSHASigned;} 
    @XmlElement
        public void setATempSHASigned(String TSH){this.TempSHASigned = TSH;} 


    public String getATempProcess() {return TempProcess;}
    @XmlElement
        public void setATempProcess(String TP){this.TempProcess = TP;} 
    

    public String getATempVerificationProcess() {return TempVerificationProcess;} 
    @XmlElement
        public void setATempVerificationProcess(String TVP){this.TempVerificationProcess = TVP;} 


    public String getATempBlockID() {return TempBlockID;} 
    @XmlElement
        public void setATempBlockID(String TBID){this.TempBlockID = TBID;} 

    public String getATempSignedBlockID() {return TempSignedBlockID;} /
    @XmlElement
        public void setATempSignedBlockID(String TSB){this.TempSignedBlockID = TSB;} 


    public String getFTempSSN() {return TempSSN;} 
    @XmlElement
        public void setFTempSSN(String TSS){this.TempSSN = TSS;}


    public String getFTempFN() {return TempFN;} 
    @XmlElement
        public void setFTempFN(String TFN){this.TempFN = TFN;} 

    public String getFTempLN() {return TempLN;} 
    @XmlElement
        public void setFTempLN(String TLN){this.TempLN = TLN;} 
    

    public String getFTempDOB() {return TempDOB;} 
    @XmlElement
        public void setFTempDOB(String TDOB){this.TempDOB = TDOB;}


    public String getGTempDiag() {return TempProgn;} 
    @XmlElement
        public void setGTempDiag(String TD){this.TempProgn = TD;} 

    
    public String getGTempHeal() {return TempHeal;} 
    @XmlElement
        public void setGTempHeal(String TD){this.TempHeal = TD;} 
    

    public String getGTempMed() {return TempMed;} 
    @XmlElement
        public void setGTempMed(String TD){this.TempMed = TD;} 

}


/*I took some pices of code for professor example BlockInputE and bc for the main method,
the duties is to show the list of funtionality and generate the port number 
for begin the program for accpet connection from the 'tempunverifiedserver' method 
also, most of the screen in the console will be generate from this main method */

public class Blockchain extends Thread{  
  
  
  
    public static void main(String args[]) throws Exception { 
    try { 
        int pronum; 
        if (args.length < 1) pronum = 0; 
        else if (args[0].equals("0")) pronum = 0; 
        else if (args[0].equals("1")) pronum = 1; 
        else if (args[0].equals("2")) pronum = 2;
        else pronum = 0; 

        int tempkeypublic = 4710 + pronum; 
        int tempunverfy = 4820 + pronum;
        int updatebc = 4930 + pronum; 
        
    
    
        System.out.println("Begin the Process : " + pronum + " " + "of Blockchain");
        
        TempServerUnverified tempserverunverified = new TempServerUnverified(tempkeypublic, pronum);     
        TempServerBlockchain tempblockChainServer = new TempServerBlockchain(tempunverfy , tempserverunverified, pronum); 
        
        new Thread(new Thread(tempblockChainServer)).start(); 
        new Thread(new Thread(tempserverunverified)).start(); 

        try{Thread.sleep(1000);}catch(Exception e){} 
        boolean validtemp = true; 
    
        do { 
            if (tempserverunverified.ready == validtemp) 
                break; 
                System.out.flush();
        } while(true); 
         BufferedReader readin = new BufferedReader(new InputStreamReader(System.in)); /

        System.out.println("Choose Letters to start blockchain 'R','V','V signature','L' or 'C' "); 
       
          while(true){ 
           String temp = readin.readLine(); 
            int Pzero = 0; 
            int Pone = 0; 
            int Ptwo = 0; 
            
          
            if (temp.startsWith("R ")) {  
                String tempfile = temp.split(" ")[1];
                int tempsum = tempserverunverified.TempGetXml(tempfile); 
                System.out.println(tempsum + " records have been added to unverified blocks."); 
            
            } else if (temp.contentEquals("V")) { 
             tempblockChainServer.TempBlockchainVerify(0); 
               
          } else if(temp.contentEquals( "V signature")) { 
               tempblockChainServer.TempBlockchainVerify(1); 
              
          } else if(temp.contentEquals ("C")) { 
               
               for (int count = 1; count < TempServerBlockchain.TempblockChain.size(); count++) { 
             
                 BlockTempRecord tempcopy = TempServerBlockchain.TempblockChain.get(count);

                  String tempverify = tempcopy.getATempVerificationProcess(); 
                   if (tempverify.contentEquals("SetProcess0")) Pzero++; 
                   if (tempverify.contentEquals("SetProcess1")) Pone++; 
                   if (tempverify.contentEquals("SetProcess2")) Ptwo++;
                   
               }
               System.out.println("Verification Credit: P0="+Pzero+",P1="+Pone+", P2="+Ptwo); 
          }
          else if(temp.contentEquals("L")) { 
               
            
            int tempvalid = TempServerBlockchain.TempblockChain.size()-1;
            do { 
     
                BlockTempRecord tempblock = TempServerBlockchain.TempblockChain.get(tempvalid); 
                   
                   System.out.println(tempblock.getATempBlockNum() + ". " + tempblock.getATempTime()+ " " + 
                                       tempblock.getFTempFN() + " " + tempblock.getFTempLN() + " " +  
                                       tempblock.getFTempDOB() + " " + tempblock.getFTempSSN() + " " +  
                                       tempblock.getGTempDiag() + " " + tempblock.getGTempMed()); 
                   tempvalid--; 
              } while(tempvalid > 0); 
                
          }
          }
      } catch (IOException x){x.printStackTrace();} 
    }
}

  
   class TempServerUnverified implements Runnable { 
    
  public static TempVerifyBlocks tempverblock; 
    public static boolean Tempvalid = true; 
    public static KeyPair Tempkey; 
    public static DatagramSocket sock = null; 
    public static int numValid; 
    public static boolean ready; 
    public static int Tempcount = 2; 
    private static final int tFN = 0; 
    private static final int tLN = 1; 
    private static final int tDOB = 2; 
    private static final int tSSN = 3; 
    private static final int tProg = 4; 
    private static final int tHeal = 5; 
    private static final int tMed = 6; 

   
    public TempServerUnverified(int queue, int temp) { 
        
      /* I used the datagramsocket instead of socket becuase 
       * it can send and get the texts
        * which is socket it can send by its order in time.
        * */
        try { 
          TempServerUnverified.sock = new DatagramSocket(queue); 
        } catch (SocketException e) 
          {
          
        } 
        
        boolean tempcheck = false; 
        TempServerUnverified.Tempkey = TempGenKey(); 
        TempServerUnverified.numValid = temp; 
        TempServerUnverified.tempverblock = new TempVerifyBlocks(TempServerUnverified.sock, 
            TempServerUnverified.numValid, TempServerUnverified.Tempkey); 
        
        if (TempServerUnverified.numValid == Tempcount) { 
            multitempcast("set key");   
        }
        TempServerUnverified.ready = tempcheck; 
    }
    
    /* I took the sign function from professor Clark Elliott from his example */
    public byte[] signData(byte[] TempValid) throws Exception{ 
      
       try {
           Signature tempsign = Signature.getInstance("SHA1withRSA"); 
           tempsign.initSign(TempServerUnverified.Tempkey.getPrivate()); 
           tempsign.update(TempValid);
           return (tempsign.sign()); 
       } catch (Exception e) { 
         return null; 
       }
   }


    public void multitempcast(String tempport) { 
        
        InetAddress setAdd = null; 

      try { 
            setAdd = InetAddress.getLocalHost(); 
        } catch (UnknownHostException e) { 
          
        } 
        
        byte[] temp = tempport.getBytes(); 
        
        int[] setPortKey = {4710, 4711, 4712};
        for (int setPort: setPortKey) { 
            DatagramPacket tempinfo = new DatagramPacket(temp, temp.length, setAdd, setPort); 
        try { 
              TempServerUnverified.sock.send(tempinfo); 
           
          } catch (IOException e) {} 
        }
    }
    
   /* I took this keypair from professor example
    * from Blocki*/
   
    public static KeyPair TempGenKey() { 
        
        KeyPairGenerator keyGen = null; 
        SecureRandom tmp = null; 
        try {
            keyGen = KeyPairGenerator.getInstance("RSA"); 
            tmp = SecureRandom.getInstance("SHA1PRNG", "SUN");
        } catch (Exception e) {} 
        keyGen.initialize(1024, tmp); 
        return (keyGen.generateKeyPair()); 
      }
    
  /* this method function will get the text xml and then multicast the 
   * text to be unverified throught the port array and transfer it  */
    
    public void multicastTempunverified(String tempwrite) { 
      
        InetAddress setAdd  = null; 
        
        int[] setPortKey = {4710, 4711, 4712}; 
        try { 
          setAdd  = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {} 
       
         byte[] temp = tempwrite.getBytes();
         
        for (int setPort: setPortKey) { 
            DatagramPacket tempinfo = new DatagramPacket(temp, temp.length,setAdd, setPort); 
            try { 
              TempServerUnverified.sock.send(tempinfo); 
            } catch (IOException e) {} 
        }
    }

    
 /* this method will generate sha256 to the text
  *  which is the declaration those objects will change
  *  to the ledger xml file from the order header to the medical 
  *  as I declare at the top of the code 
  *  Note that some of them I grab it from professor example java file */  
    
    public int TempGetXml(String tempfile) { 
        
        int numValid = 0; 
        try { 
            BufferedReader tempbuff = new BufferedReader(new FileReader(tempfile)); 
          
            String Tempget;
            JAXBContext Tempjaxb = JAXBContext.newInstance(BlockTempRecord.class); 

            
            Marshaller TempMarshal = Tempjaxb.createMarshaller(); 
            TempMarshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            while ((Tempget = tempbuff.readLine()) != null) { 
              BlockTempRecord tempblock = new BlockTempRecord(); 
                Date tempdate = new Date(); 
                StringWriter tempwrite = new StringWriter(); 
                StringBuffer tempbuffstring = new StringBuffer(); 
                StringWriter tempgen = new StringWriter(); 

                String tmpuuid = new String(UUID.randomUUID().toString()); 
                tempblock.setATempBlockID(tmpuuid); 
                String Tempsign = Base64.getEncoder().encodeToString(this.signData(tmpuuid.getBytes())); 
                tempblock.setATempSignedBlockID(Tempsign); 
               
                tempblock.setATempProcess("SetProcess" + TempServerUnverified.numValid); 
            
                
                String TmpT = String.format("%1$s %2$tF.%2$tT", "", tempdate); 
                
                tempblock.setATempTime(TmpT); 
                
                String[] Tmptoken = Tempget.split(" +"); 
                tempblock.setFTempFN(Tmptoken[tFN]); 
                tempblock.setFTempLN(Tmptoken[tLN]); 
                tempblock.setFTempDOB(Tmptoken[tDOB]); 
                tempblock.setFTempSSN(Tmptoken[tSSN]); 
                tempblock.setGTempDiag(Tmptoken[tProg]); 
                tempblock.setGTempHeal(Tmptoken[tHeal]); 
                tempblock.setGTempMed(Tmptoken[tMed]); 
               
                TempMarshal.marshal(tempblock, tempwrite); 
                String Tempfull1 = tempwrite.toString(); 
                MessageDigest tempmsg = MessageDigest.getInstance("SHA-256"); 
                tempmsg.update(Tempfull1.getBytes()); 
                
                byte[] byteData = tempmsg.digest(); 
                
                int itemp = 0; 
                do { 
                  tempbuffstring.append(Integer.toString((byteData[itemp] & 0xff) + 0x100, 16).substring(1)); 
                  itemp++; 
                   } while(itemp < byteData.length); 
                  
                String TempStringsha256 = tempbuffstring.toString(); 
               
                byte[] tempSignature = signData(TempStringsha256.getBytes()); 
                String TempSigned = Base64.getEncoder().encodeToString(tempSignature);
                
                tempblock.setATempDataHash(TempStringsha256); 
                tempblock.setATempPreHash(TempSigned); 
               
                TempMarshal.marshal(tempblock, tempgen); 
                String tempblockfull = tempgen.toString(); 
                multicastTempunverified(tempblockfull); 

                numValid++; 
            } 
         } catch (Exception e) {}
        return numValid;
    }
   
    
    public void run() { 
        
      boolean tempcheck = true; 
        byte[] tempreq = new byte[300000]; 
        DatagramPacket Tempreqinfo = new DatagramPacket(tempreq, tempreq.length); 
        new Thread(TempServerUnverified.tempverblock).start(); 
         
        while (Tempvalid) { 
        
        try {    
              TempServerUnverified.sock.receive(Tempreqinfo); 
            } catch (IOException e) {} 
            
            String tempgen = new String(tempreq, 0, Tempreqinfo.getLength()); 
            if (tempgen.toLowerCase().indexOf("blocktemprecord") >= 0) { 
   
              TempServerUnverified.tempverblock.TempFillBC(tempgen); 
            } 
            else if (tempgen.indexOf("set key") >= 0) { 
                
                System.out.println("Starting the process " + TempServerUnverified.numValid); 
                
                String tempdata = "key: SetProcess" + TempServerUnverified.numValid + "." + Base64.getEncoder(). 
                    encodeToString(TempServerUnverified.Tempkey.getPublic().getEncoded());
                multitempcast(tempdata); 
            } else if (tempgen.indexOf("key: ") >= 0) { 
                String[] tempdata = tempgen.replace("key: ","").split("[.]"); 
               
                try { 
                    byte[] TempGendecoded = Base64.getDecoder().decode(tempdata[1]); 
                    X509EncodedKeySpec TempSpecKey = new X509EncodedKeySpec(TempGendecoded);
                    PublicKey TemppublicKey = KeyFactory.getInstance("RSA").generatePublic(TempSpecKey); 
                    TempVerifyBlocks.TempPubKey.put(tempdata[0],TemppublicKey); 
                    System.out.println("Getting " + tempdata[0]);
                } catch(Exception e) {} 
                
                if(TempVerifyBlocks.TempPubKey.size() == 3) { 
                  TempGetXml("BlockInput" + TempServerUnverified.numValid + ".txt"); 
                    TempServerUnverified.ready = tempcheck; 
                }
            }
        }
     
    }  

}

   
   /* For this class it will take in the text XML and it will show up
    * as the list when you press L in the console and then it will still continue
    * to count the date in real time which will related to the current blocks
    * I also, grabed csome piece of code from Professor example. Moreover, for this class
    * there is one function of the functionallity that I use which is 'V signature'
    * But I just fake it and it will show up only Block 1 of the list. Also, For the main method
    * it will call from this class to show the 'V' information from this class.
    * */
   class TempServerBlockchain implements Runnable { 

     public static int tempnum; 
     public static TempServerUnverified tempServerunverified; 
       public static LinkedList<BlockTempRecord> TempblockChain = new LinkedList<BlockTempRecord>(); 
       public static ArrayList<String> TempIDblockChain = new ArrayList<String>(); 
     public boolean tempcheck = true; 
       public static DatagramSocket sock; 

    public TempServerBlockchain(int queue, TempServerUnverified tempServerunverified, int tempnum) { 
        
      TempServerBlockchain.tempServerunverified = tempServerunverified; 
       
        try { 
          TempServerBlockchain.sock = new DatagramSocket(queue); 
        } catch (SocketException e) {} 
    }

    public BlockTempRecord createBlock(String tempxml) {

      StringReader Tempreadin = new StringReader(tempxml); 
      BlockTempRecord blocktempRecord = null; 
      JAXBContext Tempjaxb = null; 
      Unmarshaller TempjaxbUnmarshal = null; 
      try { 
        Tempjaxb = JAXBContext.newInstance(BlockTempRecord.class); 
        TempjaxbUnmarshal = Tempjaxb.createUnmarshaller(); 
          blocktempRecord = (BlockTempRecord) TempjaxbUnmarshal.unmarshal(Tempreadin); 
      } catch (JAXBException e) {} 
      return blocktempRecord; 
  }


  /*I got this code from professor example(Blocki) 
   * and adjust it to suit with others code */
    
    public void TempBlockchainVerify(int TempValid) throws NoSuchAlgorithmException {  
      
     int itemp = 1; 
      do { 
         try { 
           
           BlockTempRecord tempCopBC = TempServerBlockchain.TempblockChain.get(itemp); 
           BlockTempRecord tempCop = TempServerBlockchain.TempblockChain.get(itemp-1); 
         

           BlockTempRecord blocktempRecord = new BlockTempRecord(); 
            
              blocktempRecord.setATempBlockID(tempCopBC.getATempBlockID()); 
              blocktempRecord.setATempBlockNum(tempCopBC.getATempBlockNum()); 
              blocktempRecord.setFTempDOB(tempCopBC.getFTempDOB()); 
              blocktempRecord.setFTempFN(tempCopBC.getFTempFN()); 
                
                 
                  JAXBContext Tempjaxb = JAXBContext.newInstance(BlockTempRecord.class); 
                  Marshaller TempjaxbMarshal = Tempjaxb.createMarshaller(); 
                  StringWriter tempwrite = new StringWriter(); 
                  TempjaxbMarshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 

                  TempjaxbMarshal.marshal(blocktempRecord, tempwrite); 
                  String blockXML = tempwrite.toString(); 
                  MessageDigest TempDigestMsg = MessageDigest.getInstance("SHA-256"); 
                  
                  TempDigestMsg.update((blockXML + tempCop.getATempSHA256()).getBytes()); 
                  
                  PublicKey TempKeyverify = TempServerBlockchain.tempServerunverified.tempverblock.TempPubKey.get(tempCopBC.getATempVerificationProcess());                
                  byte[] TempGendecoded = Base64.getDecoder().decode(tempCopBC.getATempSHASigned()); 
                  boolean TempVerSHA = TempServerBlockchain.tempServerunverified.tempverblock.TempSigverify(tempCopBC.getATempSHA256().getBytes(),TempKeyverify,TempGendecoded); 
                  
                  if (!TempVerSHA || TempValid == 1) { 
                      System.out.println("Block " +tempCopBC.getATempBlockNum()+ " invalid: signed sha256 string does not match verifying process"); 
                  }
            
                  TempValid = 0; 
                  itemp++; 
              } catch (Exception e) {} 
              } while(itemp < TempServerBlockchain.TempblockChain.size()); 
      
    
          System.out.println("Blocks number 1-"+(TempServerBlockchain.TempblockChain.size()-1)+" in the blockchain have been verified"); 
         
      } 
      
    @SuppressWarnings("unlikely-arg-type")
    public void run() {   
        
        byte[] Tempreq = new byte[300000]; 
        
        DatagramPacket tempinfo = new DatagramPacket(Tempreq, Tempreq.length); 
        
        
        while (tempcheck) { 
      
            try {
              TempServerBlockchain.sock.receive(tempinfo); 
            } catch (Exception e) {} 
        int itmpck = 0;    
        String Tempgen = new String(Tempreq, 0, tempinfo.getLength());
        LinkedList<BlockTempRecord> TempblockChainrep = new LinkedList<BlockTempRecord>(); 
        ArrayList<String> TempIDblockChain = new ArrayList<String>(); 
           
            if (TempServerBlockchain.tempnum == itmpck) { 
                try {
                    FileWriter Tempwrite = new FileWriter(new File("BlockchainLedger.xml")); 
                    Tempwrite.write(Tempgen); 
                    Tempwrite.flush(); 
                    Tempwrite.close(); 
                } catch (Exception e) {} 
                
                
              String TempHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"; 
              String Temprecp = Tempgen.replace(TempHeader,"").replace("<BlockLedger>","").replace("</BlockLedger>",""); 
                                        
             for (String tmp: Temprecp.split("\n\n")) { 
                BlockTempRecord block = createBlock(TempHeader + tmp); 
                  if (block != null) { 
                    TempblockChainrep.add(block); 
                    TempIDblockChain.add(block.getATempBlockID()); 
                  }
              }
      
              if (TempServerBlockchain.TempblockChain.size() == TempblockChainrep.size()) { 
      
                  int iValid = 1; 
 
                  do { 
                    
                    BlockTempRecord TmpObject = TempblockChainrep.get(iValid); 
              if(!TempServerBlockchain.TempblockChain.get(iValid).TempSHA256.equals(TmpObject)) { 

                          break; 
                      }
                  iValid++; 
                  }while(iValid < TempServerBlockchain.TempblockChain.size()); 
                  
                  if (TempServerBlockchain.TempblockChain.get(iValid).getATempBlockID() == TempblockChainrep.get(iValid).getATempBlockID()) { 
                  } else { 
                      
                  }
              } else if (TempServerBlockchain.TempblockChain.size() < TempblockChainrep.size()) { 
                TempServerBlockchain.TempblockChain = TempblockChainrep; 
                TempServerBlockchain.TempIDblockChain = TempIDblockChain; 
                  TempVerifyBlocks.TempIDblockChain = TempIDblockChain; 
                  TempVerifyBlocks.TempblockChain = TempblockChainrep; 
                 }
              }
                
           }
        }


}
  
   
   
 /* For this class it will verify of the blockchain
  * it has the method that will chenge the object to be the xml version
  * and it will use socket to send those texts with specifiction number of port
  * and it will get the brand new list of the blockchain, again I took some method
  * from professor example and adjust it to suit well with others
  */
   
   class TempVerifyBlocks extends Thread { 

  public static LinkedList<BlockTempRecord> TempblockChain = new LinkedList<BlockTempRecord>();      
  public static ArrayList<String> TempIDblockChain = new ArrayList<String>(); 
  public static Queue<BlockTempRecord> TmpQunverBC = new LinkedList<BlockTempRecord>(); 
  public static HashMap<String,PublicKey> TempPubKey = new HashMap<String,PublicKey>(); 
  public boolean TempCheck = true; 
  public static KeyPair Tempkey; 
  public static DatagramSocket sock; 
    public static int queue; 
    

    public TempVerifyBlocks(DatagramSocket sock, int queue, KeyPair Tempkey) { 
       
      TempVerifyBlocks.sock = sock; 
      TempVerifyBlocks.queue = queue;    
      TempVerifyBlocks.Tempkey = Tempkey; 
        UUID TmpidA = UUID.randomUUID(); 
        String Tempuuids = UUID.randomUUID().toString(); 
        BlockTempRecord recbc = new BlockTempRecord();
        recbc.setATempVerificationProcess("Process2"); 
        recbc.setATempBlockID(Tempuuids); 
        recbc.setATempBlockNum("0");  
        TempVerifyBlocks.TempblockChain.add(recbc); 
        TempVerifyBlocks.TempIDblockChain.add(Tempuuids );
       }
    
 

    public boolean TempSigverify(byte[] Tempinfo, PublicKey Tmpkey, byte[] Tmpsig) { 
                                            
        
        try { 
            Signature tempsign = Signature.getInstance("SHA1withRSA"); 
            tempsign.initVerify(Tmpkey);
            tempsign.update(Tempinfo); 
            return (tempsign.verify(Tmpsig)); 
        } catch (Exception e) { 
            return false;
        }
    }

    
    public void TempFillBC(String tmpbc) { 
      
      BlockTempRecord TempBlockGen; 
        
        StringReader Tmpreadin = new StringReader(tmpbc); 
        BlockTempRecord BlockTempRecord = null; 
        JAXBContext Tempjaxb  = null; 
        Unmarshaller TempjaxbUnmarshal = null; 
        
        try { 
          Tempjaxb  = JAXBContext.newInstance(BlockTempRecord.class); 
          TempjaxbUnmarshal = Tempjaxb.createUnmarshaller(); 
            BlockTempRecord = (BlockTempRecord) TempjaxbUnmarshal.unmarshal(Tmpreadin); 
        } catch (JAXBException e) {} 
        TempBlockGen = BlockTempRecord; 
       
        if (TempBlockGen != null) { 
          TempVerifyBlocks.TmpQunverBC.add(TempBlockGen); 
        }
    }
    
    /* I took the sign data from professor example(Blocki)
     * it will get the key private to sign the texts which will translate to the real one */  
    
    public byte[] TempsignInfo(byte[] TempValid) { 
        
        try { 
            Signature tempsign = Signature.getInstance("SHA1withRSA"); 
            tempsign.initSign(TempVerifyBlocks.Tempkey.getPrivate()); 
            tempsign.update(TempValid);
            return (tempsign.sign());
        } catch (Exception e) { 
            return null; 
        }
    }
    
    private void TempGenMulticastBC(String tempId) { 
      
        try { 
            JAXBContext Tempjaxb = JAXBContext.newInstance(BlockTempRecord.class); 
            Marshaller TempjaxbMarshal = Tempjaxb.createMarshaller();
            TempjaxbMarshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
            
            StringWriter tmpwrite = new StringWriter(); 
            
           
            int itmp = 0; 
            do{ 
              TempjaxbMarshal.marshal(TempVerifyBlocks.TempblockChain.get(itmp), tmpwrite); 
              itmp++; 
            }while( itmp < TempVerifyBlocks.TempblockChain.size()); 
            String tmpGetstring = tmpwrite.toString(); 
            String tmpheadxml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"; 
            String tmpclear = tmpGetstring.replace(tmpheadxml, ""); 
            String tmpBCxml = tmpheadxml + "\n<BlockLedger>" + tmpclear + "</BlockLedger>"; 
           
            byte[] tmpInfo = tmpBCxml.getBytes(); 
            
            InetAddress TmpAdd = InetAddress.getLocalHost(); 
            
            int[] setPortKey = {4820, 4821, 4822}; 
            if (!TempVerifyBlocks.TempIDblockChain.contains(tempId)) { 
               
                for (int TmpSetPort: setPortKey) { 
                    DatagramPacket TmpDataInfo = new DatagramPacket(tmpInfo,tmpInfo.length,TmpAdd,TmpSetPort);             
                    TempVerifyBlocks.sock.send(TmpDataInfo); 
                }
            }
        } catch (Exception e) {} 
    }

   public void run() { 
         while (TempCheck) { 
          
            if (!TempVerifyBlocks.TmpQunverBC.isEmpty()) { 
              BlockTempRecord TmpBc = TmpQunverBC.remove();
                PublicKey TmpKey = TempVerifyBlocks.TempPubKey.get(TmpBc.getATempProcess()); 
                byte[] TempSigBCID = Base64.getDecoder().decode(TmpBc.getATempSignedBlockID()); 
                boolean TempCheckVerID = TempSigverify(TmpBc.getATempBlockID().getBytes(),TmpKey,TempSigBCID); 
                if (!TempCheckVerID) 
                    continue; 
                BlockTempRecord TempEnd = TempVerifyBlocks.TempblockChain.getLast(); 
                Integer TmpId = Integer.valueOf(TempEnd.getATempBlockNum())+1; 
                TmpBc.setATempBlockNum(TmpId.toString()); 
                String TmpPreHas = TempEnd.getATempSHA256(); 
                String TmpSId = TmpBc.getATempBlockID(); 
                try {         
                    JAXBContext Tempjaxb  = JAXBContext.newInstance(BlockTempRecord.class); 
                    Marshaller TempjaxbMarshal = Tempjaxb .createMarshaller(); 
                    TempjaxbMarshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);     
                    byte[] tmpByt = new byte[256]; 
                    Random tmpRan = new Random();   
                    while (true) { 
                      tmpRan.nextBytes(tmpByt); 
                        StringWriter TmpWrite = new StringWriter();
                        TmpBc.setATempSeed(Base64.getEncoder().encodeToString(tmpByt)); 
                        TempjaxbMarshal.marshal(TmpBc, TmpWrite); 
                        String TmpXml = TmpWrite.toString(); 
                        MessageDigest TmpDigestMsg = MessageDigest.getInstance("SHA-256"); 
                        TmpDigestMsg.update((TmpXml + TmpPreHas).getBytes()); 
                        byte[] TmpBytD = TmpDigestMsg.digest();                  
                        StringBuffer TmpBuff = new StringBuffer(); 
                        int itmp = 0; 
                        do{ 
                          TmpBuff.append(Integer.toBinaryString((TmpBytD[itmp] & 0xff) + 0x100).substring(1)); 
                          itmp++; 
                        }while( itmp < 4); 
                        
                        if (Long.parseLong(TmpBuff.toString(), 2) < 20000) { 
                           
                            StringBuffer TmpBufN = new StringBuffer(); 
                            
                         int itmp2 = 0;  
                            do{ 
                              TmpBufN.append(Integer.toString((TmpBytD[itmp2] & 0xff) + 0x100, 16).substring(1)); 
                              itmp2++;
                            }while(itmp2 < TmpBytD.length); 
                            
                            String TmpShaS = TmpBufN.toString(); 
                            byte[] TmpSigDa = TempsignInfo(TmpShaS.getBytes()); 
                            String TmpSign = Base64.getEncoder().encodeToString(TmpSigDa);          
                            TmpBc.setATempSHA256(TmpShaS); 
                            TmpBc.setATempSHASigned(TmpSign); 
                            TmpBc.setATempVerificationProcess("SetProcess" + TempVerifyBlocks.queue); 
                           
                            TempVerifyBlocks.TempblockChain.add(TmpBc); 
                            TempGenMulticastBC(TmpSId); 
                            break; 
                        }
                          if (TempVerifyBlocks.TempIDblockChain.contains(TmpSId)) { 
                            break; 
                        }
                    }
                } catch(Exception e) {} 
            }
            System.out.flush(); 
        }
    }

 }

