import java.io.*;
import java.net.*;

class TCPClient {
	private Socket clientSocket = null;
	private ObjectInputStream objectInputStream= null;
	private ObjectOutputStream objectOutputStream= null;
	private ByteArrayInputStream byteArrayInputStream= null;
	private BufferedReader bufferedReader = null;
	private DataOutputStream dataOutputStream = null;

	public TCPClient(){
		try {
			//Establishing the socket on the given server
			clientSocket = new Socket("localhost", 12375);
			System.out.println("Connected");

			bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

			System.out.println(bufferedReader.readLine());

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
		//System.out.println("Please choose from the following:\n1: Download a file\n2: upload a file");
    }
	
}
