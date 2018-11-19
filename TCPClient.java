import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String argv[]) throws Exception{
	//Establishing the socket on the given server
	Socket clientSocket = new Socket("192.168.2.30", 12375);
	
	FileTransferProcessor fileTransfer = new FileTransferProcessor(clientSocket);
	
	
	
	
	File file = new File("test.PNG");
	
	fileTransfer.sendFile(file);
	
	clientSocket.close();	
		
    }
	
}
