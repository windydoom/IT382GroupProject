import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String argv[]) throws Exception{
	//Establishing the socket on the given server
	Socket clientSocket = new Socket("192.168.2.30", 12375);
	
	//Creating an instance of the file transfer helper class
	FileTransferProcessor fileTransfer = new FileTransferProcessor(clientSocket);

	
	//Creates new test file entitled test.PNG
	File file = new File("test.PNG");
	
	//Calling the sendFile method from FileTransferProcess class
	fileTransfer.sendFile(file);
	
	
	clientSocket.close();	
		
    }
	
}
