# IT382GroupProject

**By Default port 1237x will be used in both client and server. This can easily be changed in both TCPClient.java and TCPServer.java but 
it must remain consistent**

## SERVER
* Server needs to run on Windows based machine <br />
* Directory C:\DB must exhist as this is where Server references its local database <br />
* The server will automattically append "C:\DB\" to the file you want to upload so the file name should be passed with just the name and extension



## CLIENT
* Client will refer to localhost by default unless changed in TCPClient.java <br />
* Only able to upload files located in the same directly as TCPClient.class <br />
* Downloads files to same directory as TCPClient.class
