package ClientSeverSystem;
import java.net.*;
import java.io.*;
public class Server{
	private static int PORT = 8765;
	private ServerSocket serverSocket;
	private String result;
	public void runServer() {
		// Create the server socket
		try {
			serverSocket = new ServerSocket(PORT);
			// Wait for a connection from a client
			while(true) {
				Socket client = serverSocket.accept();
				ClientThread newClient = new ClientThread(client,result);
				newClient.start();
			}
		}catch(Exception e) {
		}finally {
			// close the socket
			try {
				serverSocket.close();
			}catch(Exception e) {
			}
		}
	}

	// The thread that will handle a client
	public static class ClientThread extends Thread {
		// Declare the various attributes of the thread
		private Socket client;
		private PrintWriter out;
		private BufferedReader in;
		private String result;

		public ClientThread(Socket socket,String result) {
			client = socket;
			this.result = result;
		}
		// Override run...
		public void run() {
			try {
				// Create the writer and reader
				out = new PrintWriter(client.getOutputStream(),true);
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				while(true) {
					// wait for a request from the client
					String message = in.readLine();
					String [] messgaeSplit = message.split(",");
					int nu1=Integer.parseInt(messgaeSplit[0]);
					int nu2=Integer.parseInt(messgaeSplit[1]);
					String sign=messgaeSplit[2];
					
					if(message == null){
						break;
					}else if(sign.equals("+")){
						int	sum = nu1+nu2;
						 result=""+sum;
					}else if(sign.equals("-")){
						int	subtract = nu1-nu2;
						 result=""+subtract;
					} else {
						// Unrecognized command!
						result = "SORRY, UNKNOWN COMMAND";
					}
					
					// send the response
					out.println(result);
					// Wait for a response. If none comes, the client is dead
					if(in.readLine() == null) {
						break;
					}
				}
			}catch(Exception e) {

			}finally {
				// Collapse nicely if the server goes
				try {
					in.close();
					out.close();
					client.close();
				}catch(Exception e) {}
			}
		}
	}


	public static void main(String[] args) {
		new Server().runServer();
	}
}