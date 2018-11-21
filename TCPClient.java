import java.io.*;
import java.net.*;

class TCPClient {
	private Socket clientSocket = null;
	private DataInputStream is = null;
	private DataOutputStream out = null;


	public TCPClient(){
		try {
			//Establishing the socket on the given server
			clientSocket = new Socket("192.168.2.30", 12375);
			System.out.println("Connected");



			//Creating an instance of the file transfer helper class
			//FileTransferProcessor fileTransfer = new FileTransferProcessor(clientSocket);


			//Creates new test file entitled test.PNG
			//File file = new File("test.PNG");

			//Calling the sendFile method from FileTransferProcess class
			//fileTransfer.sendFile(file);


			clientSocket.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}


    public static void main(String argv[]) throws Exception{
		TCPClient client = new TCPClient();
		System.out.println("Please choose from the following:\n1: Download a file\n2: upload a file");
    }
	
}
