package devoir2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadTask extends Thread{
	public static void main(String[] args) throws IOException  {
		 try 
		 {
		   try (ServerSocket server = new ServerSocket(2023)) {
			System.out.println("Server is started...");
			 while (true){
			 Socket client = server.accept();
			 System.out.println("New Thread is borning ! ");
			 Thread Th = new ThreadedServer(client);
			 Th.start();
			
			 }
		}

	     }catch (Exception e) 
		 {
		  System.out.println(e);
	     }
	}

}
