import java.io.*;
import java.net.*;
import java.util.ArrayList;

class TCPServer {
    private static boolean repeat = true;
	private String fileName = "";
	private ServerSocket welcomeSocket = null;
	private Socket socket = null;
	private DataOutputStream dataOutputStream = null;
	private BufferedReader bufferedReader = null;
	private FileTransferProcessor fileTransferProcessor= null;
	private static ArrayList<String> fileNames = null;


	public TCPServer() {
	    repeat = false;
	    fileNames = new ArrayList<>();
		try {
			//Establish ServerSocket on port 12375
			welcomeSocket = new ServerSocket(12374);
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

				//Download a file to client
				case 1:
					//Writing the file names available for download
					dataOutputStream.writeBytes(getFileNames());
					dataOutputStream.writeBytes("\n");

					//Getting file name from client and creating String with absolute path
					fileName = bufferedReader.readLine();
					fileName = "C:\\DB\\"+fileName;

					//Creating File object with fileName
					File file = new File(fileName);

					//initializing fileTransferProcessor over the socket to server as helper class and then send the file
					try {
						fileTransferProcessor = new FileTransferProcessor(socket);
						fileTransferProcessor.sendFile(file);
					} catch (Exception e){
						e.printStackTrace();
					}
					break;

				//Upload a file from client
				case 2:
                    //Getting file name from client and creating String with absolute path
                    fileName = bufferedReader.readLine();
                    fileName = "C:\\DB\\" + fileName;

                    //initializing fileTransferProcessor over the socket to server as helper class and then receive the file
                    try {
                        fileTransferProcessor = new FileTransferProcessor(socket);
                        fileTransferProcessor.receiveFile(fileName);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                    System.out.println(fileName);
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

        try {
            dataOutputStream.close();
            bufferedReader.close();
            socket.close();
            welcomeSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        repeat = true;
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


	public static void main(String argv[]){
	    while (repeat) {
            TCPServer server = new TCPServer();
        }
	}

  }