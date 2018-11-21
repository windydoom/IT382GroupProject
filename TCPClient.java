import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPClient {
	private int bufferSize;
	private Socket clientSocket = null;
	private InputStream inputStream = null;
	private BufferedOutputStream bufferedOutputStream = null;
	private FileOutputStream fileOutputStream = null;
	private ObjectInputStream objectInputStream= null;
	private ObjectOutputStream objectOutputStream= null;
	private DataInputStream  dataInputStream   = null;
	private ByteArrayInputStream byteArrayInputStream= null;
	private BufferedReader bufferedReader = null;
	private DataOutputStream dataOutputStream = null;
	private FileTransferProcessor fileTransferProcessor = null;
	private Scanner console = null;

	public TCPClient(){
		try {
			String uploadOrDownload = "";
			String fileName = "";

			console = new Scanner(System.in);
			//Establishing the socket on the given server
			clientSocket = new Socket("localhost", 12376);
			System.out.println("Connected");

			System.out.println("Please choose from the following:\n1: Download a file\n2: upload a file");

			//Getting input and output streams
			bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());

			//Client's choice to upload or download
			uploadOrDownload = console.next();

			//Writing whether the client wants to download or upload
			dataOutputStream.writeBytes(uploadOrDownload + "\n");

			//Creates a new FileTransferProcessor to actually handle uploading or download a file
			fileTransferProcessor = new FileTransferProcessor(clientSocket);


			switch (Integer.parseInt(uploadOrDownload)){
				//Download
				case 1:
					System.out.println("Choose from the following files to download: " + bufferedReader.readLine());
					fileName = console.next();

					//Writing whether the client wants to download or upload
					dataOutputStream.writeBytes(fileName + "\n");

					try {
						fileTransferProcessor = new FileTransferProcessor(clientSocket);
						fileTransferProcessor.receiveFile(fileName);
					} catch(Exception e){
						e.printStackTrace();
					}
					break;

				//Upload
				case 2:
					System.out.println("2");
					break;

			}

			clientSocket.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}


    public static void main(String argv[]) throws Exception{
		TCPClient client = new TCPClient();
    }
	
}
