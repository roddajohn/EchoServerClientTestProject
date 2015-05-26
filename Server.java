import java.util.*;
import java.net.*;
import java.io.*;

public class Server {
    public void runServer() {
	ServerSocket socket = null;
	ArrayList<ClientHandlingThread> threads = new ArrayList<ClientHandlingThread>();

	try {
	    socket = new ServerSocket(23456);
	}
	catch (IOException e) {
	    e.printStackTrace();
	}

	Socket client = null;
	while (1 == 1) {
	    try {
		client = socket.accept();
		ClientHandlingThread c = new ClientHandlingThread(client);
		c.start();
		threads.add(c);
	    }
	    catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }

    private class ClientHandlingThread extends Thread {
	private Socket socket = null;

	public ClientHandlingThread(Socket s) {
	    super("Server Thread");
	    this.socket = s;
	}

	public void run() {
	    try {
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
		    out.println(inputLine);
		    if (inputLine.equals("Bye")) {
			break;
		    }
		}
		socket.close();
		out.close();
		in.close();
	    }
	    catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
}