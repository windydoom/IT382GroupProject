import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPClient {
	private static int number;
    private static boolean repeat = true;
	private Socket clientSocket = null;
	private BufferedReader bufferedReader = null;
	private DataOutputStream dataOutputStream = null;
	private FileTransferProcessor fileTransferProcessor = null;
	private Scanner console = null;

	public TCPClient(){
	    repeat = false;
		try {
			String uploadOrDownload = "";
			String fileName = "";

			console = new Scanner(System.in);
			//Establishing the socket on the given server
			clientSocket = new Socket("localhost", 12375);

			display(number);
			System.out.println("Please choose from the following:\n1: Download a file\n2: Upload a file");

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

                    //initializing fileTransferProcessor over the socket to server as helper class and then receive the file
					try {
						fileTransferProcessor = new FileTransferProcessor(clientSocket);
						fileTransferProcessor.receiveFile(fileName);
						System.out.println("Successfully downloaded: " + fileName);
					} catch(Exception e){
						e.printStackTrace();
					}
					break;

				//Upload
				case 2:
					System.out.println("Please specify the name or path of the file you want to upload");

                    //Getting file name from client and creating String with absolute path
                    fileName = console.next();

                    //Writing the file names available for download
                    dataOutputStream.writeBytes(fileName + "\n");

                    //Creating File object with fileName
                    File file = new File(fileName);

                    //initializing fileTransferProcessor over the socket to server as helper class and then send the file
                    try {
                        fileTransferProcessor = new FileTransferProcessor(clientSocket);
                        fileTransferProcessor.sendFile(file);
						System.out.println("Successfully uploaded: " + file.toString());
                    } catch (Exception e){
                        e.printStackTrace();
                    }
					break;

			}

			dataOutputStream.close();
			bufferedReader.close();
			clientSocket.close();
		} catch(Exception e){
			e.printStackTrace();
		}


		System.out.println("\n");
		repeat = true;
		number = 1;
	}

	public void display(int turn){
		if (turn == 0)
			System.out.println("Connected");
	}

    public static void main(String argv[]){
		number = 0;
	    while(repeat) {
            TCPClient client = new TCPClient();
        }
    }
	
}
