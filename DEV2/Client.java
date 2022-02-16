package devoir2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
		Scanner Scanner = new Scanner(System.in);
		try
        {
		
			InetAddress serverAddress = InetAddress.getLocalHost();
            Socket socket = new Socket(serverAddress.getHostName(), 2022);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            
            String InputCommande = "";
            String ServerResponse = "";
            
            while (!ServerResponse.equalsIgnoreCase("all good")) {
            	
            	try {
            		
            		InputCommande = Scanner.nextLine();
            		output.writeUTF(InputCommande);
            		output.flush();
            		ServerResponse = input.readUTF();
            		System.out.println( ServerResponse);
            		
            	}
            	catch (IOException e) {
					System.out.println(e);
			
            	}
            	
            }
            output.close();
            
        }
        
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

	}

	}
