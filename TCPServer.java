import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.ArrayList;

class TCPServer {
	private ServerSocket welcomeSocket = null;
	private Socket socket = null;
	private ObjectInputStream objectInputStream= null;
	private ObjectOutputStream objectOutputStream= null;
	private static ArrayList<String> fileNames = new ArrayList();


	public TCPServer() {
		try {
			//Establish ServerSocket on port 12375
			welcomeSocket = new ServerSocket(12375);
			System.out.println("Server Started\nWaiting for Client...");

			//Establish socket connection
			socket = welcomeSocket.accept();
			objectInputStream = new ObjectInputStream(socket.getInputStream());
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());




			//Creating an instance of the file transfer helper class
			//FileTransferProcessor fileTransfer = new FileTransferProcessor(socket);

			//Attempts to receive a test file called test.PNG
			//fileTransfer.receiveFile("test.PNG");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<String> getFileNames(String curDirectory){
		File[] files = new File(curDirectory).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null.

			for(File file :files) {
				if (file.isFile()) {
					fileNames.add(file.getName());
				}
			} return (fileNames);
	}


	public static void main(String argv[]) throws Exception{
		//TCPServer server = new TCPServer();
		System.out.println(getFileNames("C:\\DB"));
	}

  }