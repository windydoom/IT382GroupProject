import java.io.*;
import java.net.*;

class TCPServer{
	public static void main(String argv[]) throws Exception{
		
		
		
		//Establish ServerSocket on port 12375
		ServerSocket welcomeSocket = new ServerSocket (12375);

		//Establish socket connection
		Socket socket = welcomeSocket.accept();
		
		FileTransferProcessor fileTransfer = new FileTransferProcessor(socket);
		
		

		fileTransfer.receiveFile("test.PNG");
	
	}

  }