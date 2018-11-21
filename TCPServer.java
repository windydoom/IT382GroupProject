import java.io.*;
import java.net.*;
import java.util.ArrayList;

class TCPServer {
	private byte[] buffer = null;
	private ServerSocket welcomeSocket = null;
	private Socket socket = null;
	private FileInputStream fileInputStream = null;
	private BufferedInputStream bufferedInputStream = null;
	private BufferedOutputStream bufferedOutputStream = null;
	private ObjectInputStream objectInputStream= null;
	private ObjectOutputStream objectOutputStream= null;
	private DataInputStream dataInputStream = null;
	private ByteArrayOutputStream byteArrayOutputStream= null;
	private DataOutputStream dataOutputStream = null;
	private BufferedReader bufferedReader = null;
	private FileTransferProcessor fileTransferProcessor= null;
	private static ArrayList<String> fileNames = new ArrayList();


	public TCPServer() {
		try {
			//Establish ServerSocket on port 12375
			welcomeSocket = new ServerSocket(12376);
			System.out.println("Server Started\nWaiting for Client...");

			//Establish socket connection
			socket = welcomeSocket.accept();

			//Getting input and output streams
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));


			//Creates a new FileTransferProcessor to actually handle uploading or download a file
			fileTransferProcessor = new FileTransferProcessor(socket);


			//Will receive the client's request to either upload or download a file via String then will be switched
			switch(Integer.parseInt(bufferedReader.readLine())){

				//Download a file
				case 1:
					//Writing the file names available for download
					dataOutputStream.writeBytes(getFileNames());
					dataOutputStream.writeBytes("\n");

					System.out.println("before File");

					File file = new File(bufferedReader.readLine());

					System.out.println(file.toString());

					fileTransferProcessor = new FileTransferProcessor(socket);
					fileTransferProcessor.sendFile(file);




					break;
				//Upload a file
				case 2:
					System.out.println("case 2");
					break;
			}







			//Creating an instance of the file transfer helper class
			//FileTransferProcessor fileTransfer = new FileTransferProcessor(socket);

			//Attempts to receive a test file called test.PNG
			//fileTransfer.receiveFile("test.PNG");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getFileNames(){
		File[] files = new File("C:\\DB").listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null.

			for(File file :files) {
				if (file.isFile()) {
					fileNames.add(file.getName());
				}
			} return (fileNames.toString());
	}


	public static void main(String argv[]) throws Exception{
		TCPServer server = new TCPServer();
	}

  }