package devoir2;


import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static ServerSocket server;
	private static Socket client;
	private static int port = 2022;
	
	

	public static void main(String[] args) throws IOException {	 	
		boolean isQuit = false ;
		StringBuffer stringBuffer = new StringBuffer();
		StringBuilder StringBuilder = new StringBuilder();	
		 try {	           
			 server = new ServerSocket(port);		 
			 System.out.println("Server is started...");		 
			 client = server.accept();
			 System.out.println("Server Listening on "  + client.getLocalPort()+ " de port  " + client.getPort()); 
			 DataInputStream input = new DataInputStream(new BufferedInputStream(client.getInputStream()));
			 
			 DataOutputStream output= new DataOutputStream(client.getOutputStream());
			 
			 
			 String InputCommande = "";
			 while(!isQuit) {
				 try { 
					 InputCommande = input.readUTF();
					 isQuit = InputCommande.equalsIgnoreCase("QUIT");
					 File file = new File (InputCommande.toString());
					 
					 if(file.exists()) {
						 if(!file.isDirectory()) {
							 
							 stringBuffer.append("le fichier " + InputCommande+ " est :\n");
							 RandomAccessFile Readfile = new RandomAccessFile(InputCommande.toString(), "r");
							 while (Readfile.getFilePointer() < Readfile.length()) {
					              
								 stringBuffer.append(Readfile.readLine() + "\n" );
					                 
					            }
							 output.writeUTF(stringBuffer.toString());
							 
						 }
						 else {					 
							 StringBuilder.append("les fichiers dans le répertoire " + InputCommande +" sont :\n");
                            for(File f : file.listFiles()) {
								 
								 if(!f.isDirectory())
									 StringBuilder.append(f.getName() + "\n");						 
					        	 }
							 output.writeUTF(StringBuilder.toString());
				
						 }
					 }
					 else {

						 if(isQuit)
							 output.writeUTF("all good");
						 else
						 output.writeUTF(InputCommande+ "Does not exist");
					 }
					 
					 
					 output.flush();
					 
				 }
				 catch (IOException e) {
					isQuit = true;
				}
			 }
			 
			 input.close();
		     output.close();
			 client.close();
				            
	        } catch(Exception e)
	            {
	        	
	                System.out.println(e.toString());
	            }
		
		
	}

}

